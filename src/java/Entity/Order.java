/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.*;
import java.lang.*;

public class Order {

    private int orderID;
    private StatusOrder status;
    private Customer customer;
    private Date shipped_date;
    private Date order_date;
    private User user;
    private Receiver receivers;
    private String notes;

    public Order() {
    }

    public Order(int orderID, StatusOrder status, Customer customer, Date shipped_date, Date order_date, User user, Receiver receivers, String notes) {
        this.orderID = orderID;
        this.status = status;
        this.customer = customer;
        this.shipped_date = shipped_date;
        this.order_date = order_date;
        this.user = user;
        this.receivers = receivers;
        this.notes = notes;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(Date shipped_date) {
        this.shipped_date = shipped_date;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Receiver getReceivers() {
        return receivers;
    }

    public void setReceivers(Receiver receivers) {
        this.receivers = receivers;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", status=" + status + ", customer=" + customer + ", shipped_date=" + shipped_date + ", order_date=" + order_date + ", user=" + user + ", receivers=" + receivers + ", notes=" + notes + '}';
    }

}


    
    

