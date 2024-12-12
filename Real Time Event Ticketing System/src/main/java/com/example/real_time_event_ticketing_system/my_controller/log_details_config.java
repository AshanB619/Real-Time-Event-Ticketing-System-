package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_service.log_details_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class log_details_config {
    private final log_details_service logDetailsService;

    @Autowired
    public log_details_config(log_details_service logDetailsService) {
        this.logDetailsService = logDetailsService;
    }

    @GetMapping("/show")
    public List<String> to_log() {
        return logDetailsService.get_details(); // Return all logs
    }
}