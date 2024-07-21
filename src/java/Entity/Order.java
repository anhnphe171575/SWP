/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import DAL.DAOOrder;
import java.util.*;
import java.lang.*;

public class Order {

    private int orderID;
    private StatusOrder status;
    private Customer customer;
    private Date shipped_date;
    private Date order_date;
    private Staff staff;
    private Receiver receivers;
    private String notes;
    private String PaymentMethod;
    public Order() {
    }

    public Order(int orderID, StatusOrder status, Customer customer, Date shipped_date, Date order_date, Staff staff, Receiver receivers, String notes, String PaymentMethod) {
        this.orderID = orderID;
        this.status = status;
        this.customer = customer;
        this.shipped_date = shipped_date;
        this.order_date = order_date;
        this.staff = staff;
        this.receivers = receivers;
        this.notes = notes;
        this.PaymentMethod = PaymentMethod;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    
   public int Salesdivision(){
       DAOOrder db = new DAOOrder();
        HashMap<Integer, Integer> countMap = db.Countorder();
        int minCount = Integer.MAX_VALUE;
        for (int count : countMap.values()) {
            if (count < minCount) {
                minCount = count;
            }
        }
        List<Integer> userIdsWithMinCount = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == minCount) {
                userIdsWithMinCount.add(entry.getKey());
            }
        }
        int randomIndex = new Random().nextInt(userIdsWithMinCount.size());
        int randomUserID = userIdsWithMinCount.get(randomIndex);
        return randomUserID;
   } 

   
   
}


    
    

