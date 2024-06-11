/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Cart;
import Entity.CartItems;
import Entity.Customer;
import Entity.Product;
import Entity.Security;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuan
 */
public class DAOCart extends DBContext {
    public void Add2CartItem(int cartItemID, int cartID, int productID, int quantity){
         try {
            String sql = "INSERT INTO [dbo].[CartItems]\n"
                    + "           ([cartItemID]\n"
                    + "           ,[cartID]\n"
                    + "           ,[productID]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cartItemID);
            pre.setInt(2, cartID);
            pre.setInt(3, productID);
            pre.setInt(4, quantity);


            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    public void Add2Cart( int customerID){
         try {
            String sql = "INSERT INTO [dbo].[Cart]\n"
                    + "           ,[customerID]\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<CartItems> getCartItemsByCartID( int cartID){
        List<CartItems> list = new ArrayList<>();
        try {
            String sql ="select * from CartItems where cartID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cartID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                CartItems p = new CartItems(rs.getInt("cartItemID"),
                        null,
                  
                        null,
                        rs.getInt("quantity"));
                      
               list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public CartItems getCartItemsSelect( int cartID, int cartItemID){
        CartItems ci = null;
        try {
            String sql ="select c.customerID,c.dob,c.activity_history, c.address, c.email, c.first_name, c.gender, c.last_name, c.password\n"
                    + ",c.phone, c.securityAnswer, c.username, p.brand,p.brief_information,p.category_productID,p.featured,p.original_price\n"
                    + ",p.product_description,p.product_name,p.productID,p.quantity as 'pquantity',p.sale_price,p.status,\n"
                    + "p.thumbnail,p.update_date, p.year, ci.cartID,ci.cartItemID,ci.quantity from CartItems ci \n"
                    + "inner join Cart ca on ci.cartID = ca.cartID\n"
                    + "inner join Product p on p.productID = ci.productID\n"
                    + "inner join Customer c on c.customerID = ca.customerID\n"
                    + "where ci.cartID = ? and ci.cartItemID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cartID);
            stm.setInt(2, cartItemID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Customer c = new Customer(rs.getInt("customerID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("dob"),
                        rs.getBoolean("gender"),
                        rs.getDate("activity_history"),
                        null,
                        rs.getString("securityAnswer"));

                Cart ca = new Cart(rs.getInt("cartID"), c);
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("pquantity"),
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
                        rs.getBoolean("status"));
                 ci = new CartItems(rs.getInt("cartItemID"), ca, p, rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ci;
    }
    public Cart checkCart(int cusid){
         Cart c = null;
        try{
             String sql = "select * from Cart where customerID = ?";
                 PreparedStatement stm = conn.prepareStatement(sql);
                   stm.setInt(1, cusid);
                     ResultSet rs = stm.executeQuery();
              while (rs.next()) {
                  c = new Cart(rs.getInt("cartID"), null);
                  
              }
        }
        catch(Exception e){
            
        }
        return c;
    }
    public CartItems chekcProductInCart(int pid) {
        CartItems ci =null;
        try {
            String query = "select c.customerID,c.dob,c.activity_history, c.address, c.email, c.first_name, c.gender, c.last_name, c.password\n"
                    + ",c.phone, c.securityAnswer, c.username, p.brand,p.brief_information,p.category_productID,p.featured,p.original_price\n"
                    + ",p.product_description,p.product_name,p.productID,p.quantity as 'pquantity',p.sale_price,p.status,\n"
                    + "p.thumbnail,p.update_date, p.year, ci.cartID,ci.cartItemID,ci.quantity from CartItems ci \n"
                    + "inner join Cart ca on ci.cartID = ca.cartID\n"
                    + "inner join Product p on p.productID = ci.productID\n"
                    + "inner join Customer c on c.customerID = ca.customerID\n"
                    + "where p.productID = ? ";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, pid);
     
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Customer c = new Customer(rs.getInt("customerID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("dob"),
                        rs.getBoolean("gender"),
                        rs.getDate("activity_history"),
                        null,
                        rs.getString("securityAnswer"));

                Cart ca = new Cart(rs.getInt("cartID"), c);
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("pquantity"),
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
                        rs.getBoolean("status"));
                 ci = new CartItems(rs.getInt("cartItemID"), ca, p, rs.getInt("quantity"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ci;
    }
    public List<CartItems> getListCart(int cusid) {
        List<CartItems> list = new ArrayList<>();
        try {
                String query = "select c.customerID,c.dob,c.activity_history, c.address, c.email, c.first_name, c.gender, c.last_name, c.password\n"
                        + ",c.phone, c.securityAnswer, c.username, p.brand,p.brief_information,p.category_productID,p.featured,p.original_price\n"
                        + ",p.product_description,p.product_name,p.productID,p.quantity as 'pquantity',p.sale_price,p.status,\n"
                        + "p.thumbnail,p.update_date, p.year, ci.cartID,ci.cartItemID,ci.quantity from CartItems ci \n"
                        + "inner join Cart ca on ci.cartID = ca.cartID\n"
                        + "inner join Product p on p.productID = ci.productID\n"
                        + "inner join Customer c on c.customerID = ca.customerID\n"
                        + "where c.customerID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cusid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Customer c = new Customer(rs.getInt("customerID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("dob"),
                        rs.getBoolean("gender"),
                        rs.getDate("activity_history"),
                        null,
                        rs.getString("securityAnswer"));

                Cart ca = new Cart(rs.getInt("cartID"), c);
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("pquantity"),
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
                        rs.getBoolean("status"));
                CartItems ci = new CartItems(rs.getInt("cartItemID"), ca, p, rs.getInt("quantity"));
                list.add(ci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
      public void UpdateCartQuantity(int cardid , int quantity, int pid) {
           String sql = "UPDATE [CartItems]\n"
                + "   SET \n"
                + "           [quantity]=?\n"
                + " WHERE [cartID] = ? and productID = ?";
           try {
                PreparedStatement pre = conn.prepareStatement(sql);
               pre.setInt(1, quantity);
                pre.setInt(2, cardid);
                 pre.setInt(3, pid);
                pre.executeUpdate();
          
           }
           catch(Exception e){
               e.printStackTrace();
           }
           
      }
       public void DeleteCardItems(int CID, int CartItemID) {
        String sql = "DELETE FROM [dbo].[CartItems]\n"
                + "      WHERE cartID=? and cartItemID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, CID);
            st.setInt(2, CartItemID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        }
    }
    public static void main(String[] args) {
        DAOCart db = new DAOCart();
        System.out.println(db.getCartItemsByCartID(1).size());
    }
}
