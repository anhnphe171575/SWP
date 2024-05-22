/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.CategoryProduct;
import Entity.Product;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
/**
 *
 * @author phuan
 */
public class DAOProduct extends DBContext{
         
    public List<Product> getProductFeature() {
        List<Product> product = new ArrayList();
        try {
            String query = "select p.productID,p.product_name,p.price,p.quantity,p.year,p.product_description,\n" +
"                    p.featured,p.thumbnail,p.brief_information,p.original_price,p.sale_price,p.category_productID, p.brand, p.update_date,\n" +
"                    cp.category_name,cp.category_description from [Product] p inner join CategoryProduct cp \n" +
"                    on p.category_productID = cp.category_productID where p.featured ='1' and p.quantity > 0";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"));
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date")
                        );
                product.add(p);
            }
        } catch (SQLException e) {
             java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;
        
    }
    public List<Product> ListCatogoryAndBrand(){
      List<Product> product = new ArrayList();
        try {
            String query = " select p.brand,p.category_productID,cp.category_name   \n" +
"				  from [Product] p inner join CategoryProduct cp on p.category_productID = cp.category_productID\n" +
"				  group by p.brand,p.category_productID,cp.category_name ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), null);
                Product p = new Product(-1,
                        null,
                        -1,
                        -1,
                        -1,
                       null,
                      -1,
                        null,
                        null,
                      -1,
                        -1,
                        cp,
                        rs.getString("brand"),
                        null
                        );
                product.add(p);
            }
        } catch (SQLException e) {
             java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;
        
}
    public HashMap<String,Integer> CountProductByCategory(){
     HashMap<String,Integer> product = new HashMap<>();
        try {
            String query = "select count(p.productID) as idProduct, cp.category_name from Product p \n" +
"					inner join CategoryProduct cp on p.category_productID = cp.category_productID\n" +
"					 group by cp.category_name";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               product.put(rs.getString("category_name"), rs.getInt("idProduct"));
            }
        } catch (SQLException e) {
             java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;
        
}
    public HashMap<String,String> ImageByCategory(){
     HashMap<String,String> image = new HashMap<>();
        try {
            String query = "select category_name, [image] from CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               image.put(rs.getString("category_name"), rs.getString("image"));
            }
        } catch (SQLException e) {
             java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return image;
        
}
    
    public static void main(String[] args) {
        DAOProduct p = new DAOProduct();
        
        
    }
}
