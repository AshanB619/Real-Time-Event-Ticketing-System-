package com.example.real_time_event_ticketing_system.my_repository;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface For_Customer_Repo extends JpaRepository<Customer, Integer> {

    @Query("SELECT cus FROM Customer cus ORDER BY cus.vip_cus DESC")
    List<Customer> get_vip_order();
}
