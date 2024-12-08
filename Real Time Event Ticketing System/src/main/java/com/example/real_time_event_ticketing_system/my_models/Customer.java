package com.example.real_time_event_ticketing_system.my_models;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int customer_Id;
    private String customer_Name;
    @Column(name ="Total_Tickets")
    private int total_Ticket_By_Customer;

    public Customer() {

    }

    public int getCustomer_Id() {
        return customer_Id;
    }
    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }
    public String getCustomer_Name() {
        return customer_Name;
    }
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public int getTotal_Ticket_By_Customer() {
        return total_Ticket_By_Customer;
    }
    public void setTotal_Ticket_By_Customer(int total_Ticket_By_Customer) {
        this.total_Ticket_By_Customer = total_Ticket_By_Customer;

    }

}
