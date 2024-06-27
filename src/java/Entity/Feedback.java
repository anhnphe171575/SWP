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
public class Feedback {
    int feedbackID;
    Order order;
    Product product;
    Customer customer;
    String comment;
    int rate_star;
    String image;
        int status;
    Date update_date_feedback;

    public Feedback(int feedbackID, Order order, Product product, Customer customer, String comment, int rate_star, String image, int status, Date update_date_feedback) {
        this.feedbackID = feedbackID;
        this.order = order;
        this.product = product;
        this.customer = customer;
        this.comment = comment;
        this.rate_star = rate_star;
        this.image = image;
        this.status = status;
        this.update_date_feedback = update_date_feedback;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate_star() {
        return rate_star;
    }

    public void setRate_star(int rate_star) {
        this.rate_star = rate_star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdate_date_feedback() {
        return update_date_feedback;
    }

    public void setUpdate_date_feedback(Date update_date_feedback) {
        this.update_date_feedback = update_date_feedback;
    }

  

   
    
}
