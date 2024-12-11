package com.example.real_time_event_ticketing_system.my_controller;
import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_repository.For_Customer_Repo;
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
        return "customer "+customer.getCustomer_Name()+" added successfully ";
    }

    @DeleteMapping("/remove/customer/{customer_id}")
    public String Remove_vendors(@PathVariable int customer_id) {
        for_Customer_Repo.deleteById(customer_id);
        return "Customer related to id number "+customer_id+" deleted successfully " ;
    }
}
