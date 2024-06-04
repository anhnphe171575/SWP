/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class Order {
    private int orderID;
    private int Status_OrderID;
    private Customer customer;
    private Date shipped_date;
    private Date order_date;
    private User user;
    private AddressOrder addressorder;

    public Order() {
    }

    public Order(int orderID, int Status_OrderID, Customer customer, Date shipped_date, Date order_date, User user, AddressOrder addressorder) {
        this.orderID = orderID;
        this.Status_OrderID = Status_OrderID;
        this.customer = customer;
        this.shipped_date = shipped_date;
        this.order_date = order_date;
        this.user = user;
        this.addressorder = addressorder;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getStatus_OrderID() {
        return Status_OrderID;
    }

    public void setStatus_OrderID(int Status_OrderID) {
        this.Status_OrderID = Status_OrderID;
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

    public AddressOrder getAddressorder() {
        return addressorder;
    }

    public void setAddressorder(AddressOrder addressorder) {
        this.addressorder = addressorder;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", Status_OrderID=" + Status_OrderID + ", customer=" + customer + ", shipped_date=" + shipped_date + ", order_date=" + order_date + ", user=" + user + ", addressorder=" + addressorder + '}';
    }
    
}
