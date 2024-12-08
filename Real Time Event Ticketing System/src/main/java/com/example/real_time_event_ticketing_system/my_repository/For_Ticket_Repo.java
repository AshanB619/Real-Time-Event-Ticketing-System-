package com.example.real_time_event_ticketing_system.my_repository;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface For_Ticket_Repo extends JpaRepository<Tickets, Integer> {
    int current_ticket_availability(boolean availability);
    
}
