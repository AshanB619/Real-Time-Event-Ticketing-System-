package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import com.example.real_time_event_ticketing_system.my_service.log_details_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class Ticket_pool_Service {

    private final For_Ticket_Repo forTicketRepo;
    private final log_details_service logDetailsService;

    @Autowired
    public Ticket_pool_Service(For_Ticket_Repo forTicketRepo, log_details_service logDetailsService) {
        this.forTicketRepo = forTicketRepo;
        this.logDetailsService = logDetailsService;
    }

    public synchronized void addTicket(int ticketNumber, Vendor vendor) {
        Tickets ticket = new Tickets();
        ticket.setStatus_of_ticket("unsold");
        forTicketRepo.save(ticket);

        String logEntry = String.format(
                "Ticket_Number %d Added to the pool by Vendor %s",
                ticket.getTicket_id(), vendor.getVendor_Name()
        );
        logDetailsService.add_details(logEntry); // Add log entry
    }

    public synchronized boolean Release_Ticket(Customer customer) {
        Optional<Tickets> optionalTicket = forTicketRepo.to_find_first_ticket();
        if (optionalTicket.isPresent()) {
            Tickets ticket = optionalTicket.get();
            ticket.setStatus_of_ticket("sold");
            forTicketRepo.save(ticket);

            String logEntry = String.format(
                    "Ticket_Number %d bought from the pool by %s",
                    ticket.getTicket_id(), customer.getCustomer_Name()
            );
            logDetailsService.add_details(logEntry); // Add log entry

            return true;
        } else {
            return false; // No tickets available
        }
    }
}
