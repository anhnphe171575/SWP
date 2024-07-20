/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author phuan
 */
public class InventoryTransaction {
    private int transactionID;
    private Product product;
    private Order order;
    private int quantitychange;
    private String transaction_type;
    private Staff staff;
    private Date transaction_date;
    private String note;
    private int quantity_current;

    public InventoryTransaction(int transactionID, Product product, Order order, int quantitychange, String transaction_type, Staff staff, Date transaction_date, String note, int quantity_current) {
        this.transactionID = transactionID;
        this.product = product;
        this.order = order;
        this.quantitychange = quantitychange;
        this.transaction_type = transaction_type;
        this.staff = staff;
        this.transaction_date = transaction_date;
        this.note = note;
        this.quantity_current = quantity_current;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantitychange() {
        return quantitychange;
    }

    public void setQuantitychange(int quantitychange) {
        this.quantitychange = quantitychange;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity_current() {
        return quantity_current;
    }

    public void setQuantity_current(int quantity_current) {
        this.quantity_current = quantity_current;
    }

    @Override
    public String toString() {
        return "InventoryTransaction{" + "transactionID=" + transactionID + ", product=" + product + ", order=" + order + ", quantitychange=" + quantitychange + ", transaction_type=" + transaction_type + ", staff=" + staff + ", transaction_date=" + transaction_date + ", note=" + note + ", quantity_current=" + quantity_current + '}';
    }

  
    

    
    
    
}
