package com.example.real_time_event_ticketing_system.my_models;

import jakarta.persistence.*;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long ticket_id;
    private String status_of_ticket;
    private int vendor_id;


    public Tickets() {

    }

    public Tickets(String status_of_ticket, int vendor_id) {
        this.status_of_ticket = status_of_ticket;
        this.vendor_id = vendor_id;
    }

    public Long getTicket_id() {
        return ticket_id;
    }
    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }
    public String getStatus_of_ticket() {
        return status_of_ticket;
    }
    public void setStatus_of_ticket(String status_of_ticket) {
        this.status_of_ticket = status_of_ticket;

    }
    public int getVendor_id() {
        return vendor_id;
    }
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }



}
