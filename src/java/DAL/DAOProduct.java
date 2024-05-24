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
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
/**
 *
 * @author phuan
 */
public class DAOProduct extends DBContext{
         
     public void updateProduct(int productID, String product_name, float price,
            int quantity, int year, int category_productID, String product_description, int featured, String thumbnail,
            String brief_information, float original_price, float sale_price, Date update_date, String brand, Boolean status) {
        try {
            String query = "UPDATE Product SET product_name = ?, price = ?, quantity = ?, year = ?, category_productID = ?, "
                    + "product_description = ?, featured = ?, thumbnail = ?, brief_information = ?, original_price = ?, sale_price = ?, "
                    + "update_date = ?, brand = ?, status = ? WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            java.sql.Date sqlDate = new java.sql.Date(update_date.getTime());
            stm.setString(1, product_name);
            stm.setFloat(2, price);
            stm.setInt(3, quantity);
            stm.setInt(4, year);
            stm.setInt(5, category_productID);
            stm.setString(6, product_description);
            stm.setInt(7, featured);
            stm.setString(8, thumbnail);
            stm.setString(9, brief_information);
            stm.setFloat(10, original_price);
            stm.setFloat(11, sale_price);
            stm.setDate(12, sqlDate);
            stm.setString(13, brand);
            stm.setBoolean(14, status);
            stm.setInt(15, productID);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Product> getProductFeature() {
        List<Product> product = new ArrayList();
        try {
            String query = "select p.productID,p.product_name,p.price,p.quantity,p.year,p.product_description,\n" +
"                    p.featured,p.thumbnail,p.brief_information,p.original_price,p.sale_price,p.category_productID, p.brand, p.update_date,p.status,\n" +
"                    cp.category_name,cp.category_description from [Product] p inner join CategoryProduct cp \n" +
"                    on p.category_productID = cp.category_productID where p.featured ='1' and p.quantity  > 0 and p.status = 1";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"),"");
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
                        rs.getDate("update_date"),
                        rs.getBoolean("status") );
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
                        rs.getString("category_name"), null,"");
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
                        null,
                       null );
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
     public Product getProductByID(int productID) {
       Product p =null;
        try {
            String query = "SELECT * FROM Product WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, productID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                 p = new Product(
                        rs.getInt("productID"),
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
                        null,
                        rs.getString("brand"),
                          rs.getDate("update_date"),
                        rs.getBoolean("status")
                        );
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
     public List<Product> getProduct() {
        List<Product> product = new ArrayList();
        try {
            String query = "Select p.productID, p.product_name,p.price, p.quantity, p.year,p.category_productID, p.product_description, p.featured, p.thumbnail, "
                    + "p.brief_information,p.original_price,p.sale_price,p.update_date,p.brand, p.status, cp.category_name, cp.category_name, cp.category_description, cp.image "
                    + "from Product p inner join CategoryProduct cp "
                    + "on p.category_productID =cp.category_productID";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"), rs.getString("image"));
                Product p =  new Product(
                        rs.getInt("productID"),
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
                          rs.getDate("update_date"),
                        rs.getBoolean("status")
                        );
                product.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return product;
    }
     public void addProduct(String product_name, float price, int quantity, int year, int category_productID, String product_description, int featured, String thumbnail, String brief_information, float original_price, float sale_price, String brand) {
        try {
            String query = "INSERT INTO Product(product_name, price, quantity, year, category_productID, product_description, featured, thumbnail, brief_information, original_price, sale_price, update_date, brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, product_name);
            stm.setFloat(2, price);
            stm.setInt(3, quantity);
            stm.setInt(4, year);
            stm.setInt(5, category_productID);
            stm.setString(6, product_description);
            stm.setInt(7, featured);
            stm.setString(8, thumbnail);
            stm.setString(9, brief_information);
            stm.setFloat(10, original_price);
            stm.setFloat(11, sale_price);
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stm.setDate(12, currentDate);
            stm.setString(13, brand);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean getProductStatusbyID(int id) {
        boolean status = false;
        String query = "SELECT status FROM Product WHERE productID = ?";
        try (PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                status = rs.getBoolean("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public void updateProductStatus(int productID, boolean status) {
        try {
            String query = "UPDATE Product SET status = ? WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setBoolean(1, status);
            stm.setInt(2, productID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOProduct p = new DAOProduct();
        
        
    }
}
