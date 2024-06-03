/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phuan
 */
public class Cart {
    int CartID;
     Customer customer;
    public Cart(){
        
    }
    public Cart(int CartID, Customer customer) {
        this.CartID = CartID;
        this.customer = customer;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" + "CartID=" + CartID + ", customer=" + customer + '}';
    }
    
}
