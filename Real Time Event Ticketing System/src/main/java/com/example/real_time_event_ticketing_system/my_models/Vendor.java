package com.example.real_time_event_ticketing_system.my_models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendor_Id;
    private String vendor_Name;
    private String vendor_Email;
    private int total_Ticket_By_Vendor;

    public Vendor(){

    }
    public int getVendor_Id() {
        return vendor_Id;
    }
    public void setVendor_Id(int vendor_Id) {
        this.vendor_Id = vendor_Id;
    }
    public String getVendor_Name() {
        return vendor_Name;
    }
    public void setVendor_Name(String vendor_Name) {
        this.vendor_Name = vendor_Name;
    }
    public String getVendor_Email() {
        return vendor_Email;
    }
    public void setVendor_Email(String vendor_Email) {
        this.vendor_Email = vendor_Email;
    }
    public int getTotal_Ticket_By_Vendor() {
        return total_Ticket_By_Vendor;
    }
    public void setTotal_Ticket_By_Vendor(int total_Ticket_By_Vendor) {
        this.total_Ticket_By_Vendor = total_Ticket_By_Vendor;
    }
    
}
