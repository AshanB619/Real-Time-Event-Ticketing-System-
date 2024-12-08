package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_repository.For_Customer_Repo;
import com.example.real_time_event_ticketing_system.my_repository.For_Vendor_Repo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class customer_config {

    private final For_Customer_Repo for_Customer_Repo;

    public customer_config(For_Customer_Repo for_Customer_Repo) {
        this.for_Customer_Repo = for_Customer_Repo;
    }

    @PostMapping("/add/customers")
    public String Add_customers(@RequestBody Customer customer) {
        for_Customer_Repo.save(customer);
        return "Vendor"+customer.getCustomer_Name()+" added successfully ";
    }

    @DeleteMapping("/remove/customer/{id}")
    public String Remove_vendors(@PathVariable int customer_id) {
        for_Customer_Repo.deleteById(customer_id);
        return "Vendor related to"+customer_id+" deleted successfully " ;
    }
}
