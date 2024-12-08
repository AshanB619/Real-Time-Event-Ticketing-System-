package com.example.real_time_event_ticketing_system.my_models;

import org.springframework.stereotype.Component;

@Component
public class system_details {
    private int Total_Number_of_Tickets;
    private int Tickets_Release_rate;
    private int Customer_Retrieval_Rate;
    private int Maximum_Ticket_Capacity;

    public int getTotal_Number_of_Tickets() {
        return Total_Number_of_Tickets;
    }
    public void setTotal_Number_of_Tickets(int total_Number_of_Tickets) {
        Total_Number_of_Tickets = total_Number_of_Tickets;
    }
    public int getTickets_Release_rate() {
        return Tickets_Release_rate;
    }
    public void setTickets_Release_rate(int tickets_Release_rate) {
        Tickets_Release_rate = tickets_Release_rate;
    }
    public int getCustomer_Retrieval_Rate() {
        return Customer_Retrieval_Rate;
    }
    public void setCustomer_Retrieval_Rate(int customer_Retrieval_Rate) {
        Customer_Retrieval_Rate = customer_Retrieval_Rate;
    }
    public int getMaximum_Ticket_Capacity() {
        return Maximum_Ticket_Capacity;
    }
    public void setMaximum_Ticket_Capacity(int maximum_Ticket_Capacity) {
        Maximum_Ticket_Capacity = maximum_Ticket_Capacity;
    }
    
}
