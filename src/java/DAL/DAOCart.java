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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuan
 */
public class DAOCart extends DBContext {

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
      public void UpdateCart(int cardid , int quantity) {
           String sql = "UPDATE [CartItems]\n"
                + "   SET \n"
                + "           [quantity]=?\n"
                + " WHERE [cartID] = ?";
           try {
                PreparedStatement pre = conn.prepareStatement(sql);
               pre.setInt(1, quantity);
                pre.setInt(2, cardid);
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
        System.out.println(db.getListCart(1));
    }
}
