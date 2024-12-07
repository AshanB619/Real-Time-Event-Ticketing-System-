package com.example.real_time_event_ticketing_system.my_service;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import com.example.real_time_event_ticketing_system.my_repository.For_Ticket_Repo;
import jakarta.transaction.Transactional;

public class Ticket_pool_Service {

    private final For_Ticket_Repo for_Ticket_Repo;

    private Ticket_pool_Service(For_Ticket_Repo for_Ticket_Repo) {
        this.for_Ticket_Repo = for_Ticket_Repo;
    }

    @Transactional
    public synchronized void addTicket(int Current_ticket_number,String vendor_details) {
        Tickets ticket1=new Tickets();
        ticket1.setTotal_tickets(Current_ticket_number);
        ticket1.setStatus_of_ticket(true);
        for_Ticket_Repo.save(ticket1);

        System.out.println("Ticket_Number "+Current_ticket_number+"added to the pool by "+vendor_details);

    }


}
