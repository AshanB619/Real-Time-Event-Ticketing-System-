package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_models.system_details;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ticket_pool_Service {

    private final For_Ticket_Repo for_Ticket_Repo;
    private final system_details system_details;

    private Ticket_pool_Service(For_Ticket_Repo for_Ticket_Repo,system_details system_details) {
        this.for_Ticket_Repo = for_Ticket_Repo;
        this.system_details = system_details;
    }

    @Transactional
    public synchronized void addTicket(int Current_ticket_number,String vendor_details)throws InterruptedException {
        while (system_details.getMaximum_Ticket_Capacity()<= for_Ticket_Repo.current_ticket_availability(true)){
            wait();
        }
        System.out.println("Ticket_Number "+Current_ticket_number+"added to the pool by "+vendor_details);
        Tickets tickets = new Tickets();
        tickets.setTickets_Number(Current_ticket_number);
        tickets.setStatus_of_ticket(true);
        for_Ticket_Repo.save(tickets);
        System.out.println("Ticket_Number "+Current_ticket_number+"added to the pool by "+vendor_details);
        notifyAll();

    }

    @Transactional
    public synchronized int Release_Ticket(String customer_details)throws InterruptedException {
        while (!for_Ticket_Repo.to_find_first_ticket().isPresent()){
            wait();
        }
        Tickets tickets = for_Ticket_Repo.to_find_first_ticket().get();
        tickets.setStatus_of_ticket(false);
        for_Ticket_Repo.save(tickets);

        System.out.println("Ticket_Number "+tickets.getTickets_Number()+"brought from the pool "+customer_details);
        notifyAll();
        return tickets.getTickets_Number();
    }


}
