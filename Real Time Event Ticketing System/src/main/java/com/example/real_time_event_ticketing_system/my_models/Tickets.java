package com.example.real_time_event_ticketing_system.my_models;

import jakarta.persistence.*;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int ticket_id;
    private int tickets_Number;
    private boolean status_of_ticket;

    public Tickets() {
    }

    public int getTicket_id() {
        return ticket_id;
    }
    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }
    public boolean isStatus_of_ticket() {
        return status_of_ticket;
    }

    public void setStatus_of_ticket(boolean status_of_ticket) {
        this.status_of_ticket = status_of_ticket;
    }
    public int getTickets_Number() {
        return tickets_Number;
    }
    public void setTickets_Number(int tickets_Number) {
        this.tickets_Number = tickets_Number;
    }

}
