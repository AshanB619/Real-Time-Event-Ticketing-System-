package com.example.real_time_event_ticketing_system.my_service;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Service
@Scope("singleton")
public class log_details_service {
    private final List<String> log_details = Collections.synchronizedList(new ArrayList<>());

    public synchronized void add_details(String log_details) {
        this.log_details.add(log_details);
    }

    public synchronized List<String> get_details() {
        return new ArrayList<>(log_details); // Return a copy to prevent concurrent modification
    }
}