package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_service.log_details_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class log_details_config {
    private final log_details_service log_details_service;


    @Autowired
    public log_details_config(com.example.real_time_event_ticketing_system.my_service.log_details_service logDetailsService) {
        this.log_details_service = logDetailsService;
    }

    @GetMapping("/show")
    public List<String>to_log(){
        return log_details_service.get_details();
    }
}
