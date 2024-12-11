package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class Ticket_pool_Service {

    private final For_Ticket_Repo forTicketRepo;
    private final log_details_service log_details_service;

    @Autowired
    public Ticket_pool_Service(For_Ticket_Repo forTicketRepo, log_details_service log_details_service) {
        this.forTicketRepo = forTicketRepo;
        this.log_details_service = log_details_service;
    }

    public synchronized void addTicket(int ticketNumber, Vendor vendor) {
        Tickets ticket = new Tickets();
        ticket.setTickets_Number(ticketNumber);
        ticket.setStatus_of_ticket(false);
        forTicketRepo.save(ticket);

        int availableTickets = forTicketRepo.current_ticket_availability(false);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String logAdd = String.format("Ticket_Number %d added to the pool by Vendor ID: %d, Vendor Name: %s | %d Tickets available | %s",
                ticket.getTicket_id(), vendor.getVendor_Id(), vendor.getVendor_Name(), availableTickets, timestamp);

        log_details_service.add_details(logAdd);
    }

    public synchronized boolean Release_Ticket(Customer customer) {
        Optional<Tickets> optionalTicket = forTicketRepo.to_find_first_ticket();
        if (optionalTicket.isPresent()) {
            Tickets ticket = optionalTicket.get();
            ticket.setStatus_of_ticket(true);
            forTicketRepo.save(ticket);

            int availableTickets = forTicketRepo.current_ticket_availability(false);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String logRelease = String.format("Ticket_Number %d bought from the pool by Customer ID: %d, Customer Name: %s | %d Tickets available | %s",
                    ticket.getTicket_id(), customer.getCustomer_Id(), customer.getCustomer_Name(), availableTickets, timestamp);

            log_details_service.add_details(logRelease);
            return true;
        } else {
            return false;
        }
    }
}