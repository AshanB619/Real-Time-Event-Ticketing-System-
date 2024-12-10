package com.example.real_time_event_ticketing_system.my_repository;

import com.example.real_time_event_ticketing_system.my_models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface For_Ticket_Repo extends JpaRepository<Tickets, Integer> {

    @Query("SELECT COUNT(t) FROM Tickets t WHERE t.status_of_ticket = :availability")
    int current_ticket_availability(@Param("availability") boolean availability);

    @Query("SELECT t FROM Tickets t WHERE t.status_of_ticket = false ORDER BY t.ticket_id ASC")
    List<Tickets> To_find_availble_tickets();

    default Optional<Tickets> to_find_first_ticket() {
        return To_find_availble_tickets().stream().findFirst();
    }
}


