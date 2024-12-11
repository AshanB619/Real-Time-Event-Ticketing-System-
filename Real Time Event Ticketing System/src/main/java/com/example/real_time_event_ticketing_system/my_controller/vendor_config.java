package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_repository.For_Vendor_Repo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class vendor_config {

    private final For_Vendor_Repo forVendorRepo;

    public vendor_config(For_Vendor_Repo forVendorRepo) {
        this.forVendorRepo = forVendorRepo;
    }

    @PostMapping("/add/vendors")
    public String Add_vendors(@RequestBody Vendor vendor) {
        forVendorRepo.save(vendor);
        return "Vendor "+vendor.getVendor_Name()+" added successfully ";
    }

    @DeleteMapping("/remove/vendors/{vendor_id}")
    public String Remove_vendors(@PathVariable int vendor_id) {
        forVendorRepo.deleteById(vendor_id);
        return "Vendor related to id number "+vendor_id+" deleted successfully " ;
    }
}
