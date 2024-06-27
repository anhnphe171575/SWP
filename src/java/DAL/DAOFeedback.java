/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Customer;
import Entity.Feedback;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phuan
 */
public class DAOFeedback extends DBContext {

    public int CountFeedback(int pid) {
        int count = -1;
        try {

            PreparedStatement stm = conn.prepareStatement("select count(feedbackID) as [count] from Feedback where productID = ? And status = 1");
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return count;
    }
 public void hideShow(int id, int status) {
        try {
            String sql = "UPDATE [dbo].[FeedBack]\n"
                    + "   SET \n"
                    + "      [status] = " + status + "\n"
                    + "\n"
                    + " WHERE feedbackID=" + id;

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void InsertFeedBack(int pid, int cusid, String comment, int rate_star, String image, int status, Date date_create) {
        try {
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(date_create);
            String query = "INSERT INTO FeedBack (productID, customerID, comment, rate_star, image, status, update_date_feedback) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, pid);
            stm.setInt(2, cusid);
            stm.setString(3, comment);
            stm.setInt(4, rate_star);
            stm.setString(5, image);
            stm.setInt(6, status);
            stm.setDate(7, java.sql.Date.valueOf(date1));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> getFeedBackByProID(int pid) {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "select * from Feedback fb \n"
                    + "inner join Customer c on fb.customerID = c.customerID where fb.productID = ? and fb.status = 1 order by fb.update_date_feedback";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer cus = new Customer(0, rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "", "");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
                        cus,
                        rs.getString("comment"),
                        rs.getInt("rate_star"),
                        rs.getString("image"),
                        rs.getInt("status"),
                        rs.getDate("update_date_feedback")
                );
                list.add(fb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getFeedBack() {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.price, p.quantity, p.year, p.product_description, \n"
                    + "                                   c.first_name, c.last_name,f.customerID ,p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price,p.brand,p.status,p.update_date                               \n"
                    + "                                 FROM Feedback f inner join Product p on f.productID = p.productID\n"
                    + "								 inner join Customer c on f.customerID = c.customerID\n"
                    + "                               ";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
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
                        rs.getBoolean("status"));
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "","");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        p,
                         cus,
                        rs.getString("comment"),
                        rs.getInt("rate_star"),
                        rs.getString("image"),
                        rs.getInt("status"),
                        rs.getDate("update_date_feedback")
                );
                list.add(fb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
     public List<Feedback> getFeedBack(Map<String, String> aa1, String all) {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.price, p.quantity, p.year, p.product_description, \n"
                    + "                                   c.first_name, c.last_name,f.customerID ,p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price,p.brand,p.status,p.update_date                               \n"
                    + "                                 FROM Feedback f inner join Product p on f.productID = p.productID\n"
                    + "								 inner join Customer c on f.customerID = c.customerID\n"
                    + "                               " + all;
            PreparedStatement stm = conn.prepareStatement(sql);
               int i = 1;
             for (Map.Entry<String, String> item : aa1.entrySet()) {
                  stm.setInt(i, Integer.parseInt(item.getValue()));
                  i++;
             }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
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
                        rs.getBoolean("status"));
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "","");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        p,
                         cus,
                        rs.getString("comment"),
                        rs.getInt("rate_star"),
                        rs.getString("image"),
                        rs.getInt("status"),
                        rs.getDate("update_date_feedback")
                );
                list.add(fb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
     
     public List<Feedback> Sort(String option) {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.price, p.quantity, p.year, p.product_description, \n"
                    + "                                   c.first_name, c.last_name,f.customerID ,p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price,p.brand,p.status,p.update_date                               \n"
                    + "                                 FROM Feedback f inner join Product p on f.productID = p.productID\n"
                    + "								 inner join Customer c on f.customerID = c.customerID\n"
                    + "                  order by " + option + " ASC";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
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
                        rs.getBoolean("status"));
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "","");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        p,
                         cus,
                        rs.getString("comment"),
                        rs.getInt("rate_star"),
                        rs.getString("image"),
                        rs.getInt("status"),
                        rs.getDate("update_date_feedback")
                );
                list.add(fb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        DAOFeedback db = new DAOFeedback();
        System.out.println(db.Sort("f.rate_star"));
    }
}
