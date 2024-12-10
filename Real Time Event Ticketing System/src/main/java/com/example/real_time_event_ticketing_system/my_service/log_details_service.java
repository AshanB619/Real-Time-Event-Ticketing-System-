package com.example.real_time_event_ticketing_system.my_service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class log_details_service {
    private final List<String>details=new ArrayList<>();

    public void add_details(String log_details) {
        details.add(log_details);
    }

    public List<String> get_details() {
        return details;
    }
}
