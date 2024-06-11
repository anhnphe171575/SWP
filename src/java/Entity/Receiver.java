/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class Receiver {
    private int ReceiverID;
    private String ReceiverFullName;
     private String ReceiverMobile;
     private String ReceiverAddress;
     private Customer customer;
    public Receiver() {
    }

    public Receiver(int ReceiverID, String ReceiverFullName, String ReceiverMobile, String ReceiverAddress, Customer customer) {
        this.ReceiverID = ReceiverID;
        this.ReceiverFullName = ReceiverFullName;
        this.ReceiverMobile = ReceiverMobile;
        this.ReceiverAddress = ReceiverAddress;
        this.customer = customer;
    }

    public int getReceiverID() {
        return ReceiverID;
    }

    public void setReceiverID(int ReceiverID) {
        this.ReceiverID = ReceiverID;
    }

    public String getReceiverFullName() {
        return ReceiverFullName;
    }

    public void setReceiverFullName(String ReceiverFullName) {
        this.ReceiverFullName = ReceiverFullName;
    }

    public String getReceiverMobile() {
        return ReceiverMobile;
    }

    public void setReceiverMobile(String ReceiverMobile) {
        this.ReceiverMobile = ReceiverMobile;
    }

    public String getReceiverAddress() {
        return ReceiverAddress;
    }

    public void setReceiverAddress(String ReceiverAddress) {
        this.ReceiverAddress = ReceiverAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Receiver{" + "ReceiverID=" + ReceiverID + ", ReceiverFullName=" + ReceiverFullName + ", ReceiverMobile=" + ReceiverMobile + ", ReceiverAddress=" + ReceiverAddress + ", customer=" + customer + '}';
    }

    
}
