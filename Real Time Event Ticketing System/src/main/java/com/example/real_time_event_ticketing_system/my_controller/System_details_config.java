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
    public void set_system_details(@RequestBody system_details details) {
        this.system_details.setTotal_Number_of_Tickets(details.getTotal_Number_of_Tickets());
        this.system_details.setTickets_Release_rate(details.getTickets_Release_rate());
        this.system_details.setCustomer_Retrieval_Rate(details.getCustomer_Retrieval_Rate());
        this.system_details.setMaximum_Ticket_Capacity(details.getMaximum_Ticket_Capacity());
    }


}
