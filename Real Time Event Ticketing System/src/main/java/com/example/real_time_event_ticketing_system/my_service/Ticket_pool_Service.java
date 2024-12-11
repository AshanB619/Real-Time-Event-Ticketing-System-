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
        Tickets ticket1 = new Tickets();
        ticket1.setStatus_of_ticket("unsold");
        forTicketRepo.save(ticket1);

        int find_Available_Tickets = forTicketRepo.current_ticket_availability("unsold");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String logAdd = String.format("Ticket_Number %d added to the pool by Vendor ID: %d, Vendor Name: %s | %d Tickets available | %s",
                ticket1.getTicket_id(), vendor.getVendor_Id(), vendor.getVendor_Name(), find_Available_Tickets, timestamp);

        log_details_service.add_details(logAdd);
    }

    public synchronized boolean Release_Ticket(Customer customer) {
        Optional<Tickets> Ticket_Optional = forTicketRepo.to_find_first_ticket();
        if (Ticket_Optional.isPresent()) {
            Tickets ticket2 = Ticket_Optional.get();
            ticket2.setStatus_of_ticket("sold");
            forTicketRepo.save(ticket2);

            int find_Available_Tickets = forTicketRepo.current_ticket_availability("unsold");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String for_login_release = String.format("Ticket_Number %d bought from the pool by Customer ID: %d, Customer Name: %s | %d Tickets available | %s",
                    ticket2.getTicket_id(), customer.getCustomer_Id(), customer.getCustomer_Name(), find_Available_Tickets, timestamp);

            log_details_service.add_details(for_login_release);
            return true;
        } else {
            return false;
        }
    }
}