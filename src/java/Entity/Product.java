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
public class Product {
       private int productID;
    private String product_name;
    private float price;
    private int quantity;
    private int year;
    private String product_description;
    private int featured;
    private String thumbnail;
    private String brief_information;
    private float original_price;
    private float sale_price;
    private CategoryProduct categoryProduct;
    private String brand;
    private Date update_date;
    private  Boolean status;

    public Product(int productID, String product_name, float price, int quantity, int year, String product_description, int featured, String thumbnail, String brief_information, float original_price, float sale_price, CategoryProduct categoryProduct, String brand, Date update_date, Boolean status) {
        this.productID = productID;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.year = year;
        this.product_description = product_description;
        this.featured = featured;
        this.thumbnail = thumbnail;
        this.brief_information = brief_information;
        this.original_price = original_price;
        this.sale_price = sale_price;
        this.categoryProduct = categoryProduct;
        this.brand = brand;
        this.update_date = update_date;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBrief_information() {
        return brief_information;
    }

    public void setBrief_information(String brief_information) {
        this.brief_information = brief_information;
    }

    public float getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(float original_price) {
        this.original_price = original_price;
    }

    public float getSale_price() {
        return sale_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
  

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", product_name=" + product_name + ", price=" + price + ", quantity=" + quantity + ", year=" + year + ", product_description=" + product_description + ", featured=" + featured + ", thumbnail=" + thumbnail + ", brief_information=" + brief_information + ", original_price=" + original_price + ", sale_price=" + sale_price + ", categoryProduct=" + categoryProduct + ", brand=" + brand + ", update_date=" + update_date + '}';
    }

   
    
             
}
