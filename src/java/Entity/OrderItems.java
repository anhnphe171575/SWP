/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class OrderItems {
    private int orderitemID;
    private Order order;
    private Product product;
    private float list_price;
    private int quantity;
    private float discount;

    public OrderItems() {
    }

    public OrderItems(int orderitemID, Order order, Product product, float list_price, int quantity, float discount) {
        this.orderitemID = orderitemID;
        this.order = order;
        this.product = product;
        this.list_price = list_price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getOrderitemID() {
        return orderitemID;
    }

    public void setOrderitemID(int orderitemID) {
        this.orderitemID = orderitemID;
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

    public float getList_price() {
        return list_price;
    }

    public void setList_price(float list_price) {
        this.list_price = list_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderItems{" + "orderitemID=" + orderitemID + ", order=" + order + ", product=" + product + ", list_price=" + list_price + ", quantity=" + quantity + ", discount=" + discount + '}';
    }
}
