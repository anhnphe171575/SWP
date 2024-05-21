/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class CategoryPost {
    private int category_postID;
    private CategoryProduct category_product;
    

    public CategoryPost() {
    }

    public CategoryPost(int category_postID, CategoryProduct category_product) {
        this.category_postID = category_postID;
        this.category_product = category_product;
    }

    public int getCategory_postID() {
        return category_postID;
    }

    public void setCategory_postID(int category_postID) {
        this.category_postID = category_postID;
    }

    public CategoryProduct getCategory_product() {
        return category_product;
    }

    public void setCategory_product(CategoryProduct category_product) {
        this.category_product = category_product;
    }

    @Override
    public String toString() {
        return "CategoryPost{" + "category_postID=" + category_postID + ", category_product=" + category_product + '}';
    }
    
    

    
    
}
