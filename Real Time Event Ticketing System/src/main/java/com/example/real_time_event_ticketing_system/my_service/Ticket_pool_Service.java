package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ticket_pool_Service {

    private final For_Ticket_Repo for_Ticket_Repo;

    private Ticket_pool_Service(For_Ticket_Repo for_Ticket_Repo) {
        this.for_Ticket_Repo = for_Ticket_Repo;
    }

    @Transactional
    public synchronized void addTicket(int Current_ticket_number,String vendor_details)throws InterruptedException {
        Tickets ticket1=new Tickets();
        ticket1.setTickets_Number(Current_ticket_number);
        ticket1.setStatus_of_ticket(true);
        for_Ticket_Repo.save(ticket1);

        System.out.println("Ticket_Number "+Current_ticket_number+"added to the pool by "+vendor_details);

    }

    @Transactional
    public synchronized int Release_Ticket(String customer_details)throws InterruptedException {
        while (for_Ticket_Repo.List_for_unsold_tickets().isEmpty()){
            wait();
        }
        List<Tickets>List_for_available_tickets=for_Ticket_Repo.List_for_unsold_tickets();
        Tickets tickets=List_for_available_tickets.get(0);
        tickets.setStatus_of_ticket(false);
        for_Ticket_Repo.save(tickets);

        System.out.println("Ticket_Number "+tickets.getTickets_Number()+"brought from the pool "+customer_details);
        return tickets.getTickets_Number();
    }


}
