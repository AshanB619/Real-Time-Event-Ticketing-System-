package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_models.system_details;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/config")
public class System_details_config {
    private final system_details system_details;

    public System_details_config(system_details system_details) {
        this.system_details = system_details;
    }

    @PostMapping("/set/details")
    public void set_system_details(
            @RequestBody int Total_Number_of_Tickets,
            @RequestBody int Tickets_Release_rate,
            @RequestBody int Customer_Retrieval_Rate,
            @RequestBody int Maximum_Ticket_Capacity
    ) {
        system_details.setTotal_Number_of_Tickets(Total_Number_of_Tickets);
        system_details.setTickets_Release_rate(Tickets_Release_rate);
        system_details.setCustomer_Retrieval_Rate(Customer_Retrieval_Rate);
        system_details.setMaximum_Ticket_Capacity(Maximum_Ticket_Capacity);
    }
}
