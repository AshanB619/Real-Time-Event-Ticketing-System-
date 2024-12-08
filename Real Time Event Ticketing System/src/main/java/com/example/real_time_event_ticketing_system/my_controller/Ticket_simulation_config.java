package com.example.real_time_event_ticketing_system.my_controller;

import com.example.real_time_event_ticketing_system.my_models.Customer;
import com.example.real_time_event_ticketing_system.my_models.system_details;
import com.example.real_time_event_ticketing_system.my_repository.For_Customer_Repo;
import com.example.real_time_event_ticketing_system.my_repository.For_Vendor_Repo;
import com.example.real_time_event_ticketing_system.my_service.Ticket_pool_Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.PriorityQueue;

@RestController
@RequestMapping("/simulation/config")
public class Ticket_simulation_config {
    private final Ticket_pool_Service ticket_pool_service;
    private final For_Vendor_Repo for_Vendor_Repo;
    private final For_Customer_Repo for_Customer_Repo;
    private final system_details system_details;

    public Ticket_simulation_config(Ticket_pool_Service ticketPoolService, For_Vendor_Repo forVendorRepo, For_Customer_Repo forCustomerRepo,system_details systemDetails) {
        this.ticket_pool_service = ticketPoolService;
        this.for_Vendor_Repo = forVendorRepo;
        this.for_Customer_Repo = forCustomerRepo;
        this.system_details = systemDetails;
    }
    @PostMapping("/start/simulation")
    public void startSimulation() {
        new Thread(()->{
            for (int i=1;i<=system_details.getTotal_Number_of_Tickets();i++){
                try {
                    ticket_pool_service.addTicket(i,"system");
                    Thread.sleep(1000L *system_details.getTickets_Release_rate());
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        PriorityQueue<Customer>customers_queue=new PriorityQueue<>(for_Customer_Repo.get_vip_order());
        while (!customers_queue.isEmpty()){
            Customer customer=customers_queue.poll();
            new Thread(()->{
                for (int i=1;i<=customer.getTotal_Ticket_By_Customer();i++){
                    try {
                        ticket_pool_service.Release_Ticket(customer.getCustomer_Name());
                        Thread.sleep(1000L * system_details.getTickets_Release_rate());
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}


