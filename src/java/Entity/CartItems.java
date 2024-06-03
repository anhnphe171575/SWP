/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phuan
 */
public class CartItems {
    int CartItemID;
    Cart cart;
    Product product;
    int quantity;

    public CartItems(int CarItemID, Cart cart, Product product, int quantity) {
        this.CartItemID = CarItemID;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public int getCarItemID() {
        return CartItemID;
    }

    public void setCarItemID(int CarItemID) {
        this.CartItemID = CarItemID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItems{" + "CarItemID=" + CartItemID + ", cart=" + cart + ", product=" + product + ", quantity=" + quantity + '}';
    }
    
}
