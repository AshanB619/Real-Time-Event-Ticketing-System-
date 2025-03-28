package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.Vendor;
import com.example.real_time_event_ticketing_system.my_models.system_details;
import com.example.real_time_event_ticketing_system.my_repository.For_Customer_Repo;
import com.example.real_time_event_ticketing_system.my_repository.For_Vendor_Repo;
import com.example.real_time_event_ticketing_system.my_service.Ticket_pool_Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/simulation/config")
public class Ticket_simulation_config {
    private final Ticket_pool_Service ticket_pool_service;
    private final For_Vendor_Repo for_Vendor_Repo;
    private final For_Customer_Repo for_Customer_Repo;
    private final system_details system_details;
    private final Object ticket_lock = new Object();
    private volatile boolean simulation_check = true;

    public Ticket_simulation_config(Ticket_pool_Service ticketPoolService, For_Vendor_Repo forVendorRepo, For_Customer_Repo forCustomerRepo, system_details systemDetails) {
        this.ticket_pool_service = ticketPoolService;
        this.for_Vendor_Repo = forVendorRepo;
        this.for_Customer_Repo = forCustomerRepo;
        this.system_details = systemDetails;
    }

    @PostMapping("/start/simulation")
    public void start_Simulation() {
        simulation_check = true;

        List<Vendor> for_vendor_List = for_Vendor_Repo.findAll();
        for (Vendor vendor : for_vendor_List) {
            new Thread(() -> {
                for (int i = 1; i <= vendor.getTotal_Ticket_By_Vendor(); i++) {
                    if (!simulation_check) {
                        return;
                    }
                    try {
                        synchronized (ticket_lock) {
                            ticket_pool_service.addTicket(i, vendor);
                            ticket_lock.notifyAll();
                        }
                        Thread.sleep(1000L * system_details.getTickets_Release_rate());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }

        PriorityQueue<Customer> customer_order = new PriorityQueue<>(for_Customer_Repo.get_vip_order());

        new Thread(() -> {
            while (!customer_order.isEmpty() && simulation_check) {
                Customer currentCustomer = customer_order.peek();
                for (int i = 1; i <= currentCustomer.getTotal_Ticket_By_Customer(); i++) {
                    if (!simulation_check) {
                        return;
                    }
                    synchronized (ticket_lock) {
                        while (!ticket_pool_service.Release_Ticket(currentCustomer)) {
                            try {
                                ticket_lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        Thread.sleep(system_details.getCustomer_Retrieval_Rate() * 1000L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                customer_order.poll();
            }
        }).start();
    }

    @PostMapping("/stop/simulation")
    public String stop_Simulation() {
        simulation_check = false;
        synchronized (ticket_lock) {
            ticket_lock.notifyAll();
        }
        return "Ticket Simulation Stopped";
    }
}
