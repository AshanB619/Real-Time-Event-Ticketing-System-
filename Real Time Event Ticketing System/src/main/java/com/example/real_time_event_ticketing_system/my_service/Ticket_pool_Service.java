package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Ticket_pool_Service {

    private final For_Ticket_Repo forTicketRepo;

    @Autowired
    public Ticket_pool_Service(For_Ticket_Repo forTicketRepo) {
        this.forTicketRepo = forTicketRepo;
    }

    public void addTicket(int ticketNumber,String vendor_name) {
        Tickets ticket = new Tickets();
        ticket.setTickets_Number(ticketNumber);
        ticket.setStatus_of_ticket(false);
        forTicketRepo.save(ticket);
        System.out.println("Ticket_Number " + ticket.getTicket_id() + "  Added to the pool  by "+vendor_name );
    }
    public void Release_Ticket(String customerName) {
        Optional<Tickets> optionalTicket = forTicketRepo.to_find_first_ticket();
        if (optionalTicket.isPresent()) {
            Tickets ticket = optionalTicket.get();
            ticket.setStatus_of_ticket(true);
            forTicketRepo.save(ticket);
            System.out.println("Ticket_Number " + ticket.getTicket_id() + "  bought from the pool by " + customerName);
        }
    }
}
