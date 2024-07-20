/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Customer;
import Entity.Feedback;
import Entity.Order;
import Entity.OrderItems;
import Entity.Product;
import Entity.Receiver;
import Entity.Role;
import Entity.StatusOrder;
//import Entity.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public Feedback getFeedBackByFeedbackID(int id) {
        Feedback fb = null;
        try {
            String sql = "select * from Feedback fb \n"
                    + "inner join Customer c on fb.customerID = c.customerID where fb.feedbackID = ? order by fb.update_date_feedback";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer cus = new Customer(0, rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), "", "", "", null, true, null, null, "", "");
                fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
                        null,
                        cus,
                        rs.getString("comment"),
                        rs.getInt("rate_star"),
                        rs.getString("image"),
                        rs.getInt("status"),
                        rs.getDate("update_date_feedback")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fb;
    }

    public ArrayList<Feedback> getFeedBackOrder(int cusID) {
        ArrayList<Feedback> fb = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    o.orderID, \n"
                    + "    p.productID, \n"
                    + "    f.feedbackID,\n"
                    + "    f.comment, \n"
                    + "    c.customerID\n"
                    + " FROM  \n"
                    + "    [Order] o\n"
                    + "INNER JOIN \n"
                    + "    Order_items ot ON o.orderID = ot.orderID\n"
                    + "INNER JOIN \n"
                    + "    Product p ON ot.productID = p.productID\n"
                    + "inner JOIN \n"
                    + "    Feedback f ON f.orderID =  o.orderID AND f.productID = ot.productID\n"
                    + "INNER JOIN \n"
                    + "    Customer c ON c.customerID = o.customerID\n"
                    + "WHERE \n"
                    + "    f.feedbackID IS NOT NULL and c.customerID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cusID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("productID"),
                        "",
                        0,
                        0,
                        "",
                        0,
                        null,
                        "",
                        0,
                        0,
                        null,
                        null,
                        null,
                        null,0);

                Customer c = new Customer(
                        rs.getInt("customerID"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                Order o = new Order(
                        rs.getInt("orderID"),
                        null,
                        c,
                        null,
                        null,
                        null,
                        null, null, null);
               Feedback f = new Feedback(rs.getInt("feedbackID"), o, p, c, "", 0, "", 0, null);
              fb.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fb;
    }

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

    public void InsertFeedBack(int pid, int cusid, int orderID,String comment, int rate_star, String image, int status, Date date_create) {
        try {
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(date_create);
            String query = "INSERT INTO FeedBack (productID, customerID, comment, rate_star, image, status, update_date_feedback, orderID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, pid);
            stm.setInt(2, cusid);
            stm.setString(3, comment);
            stm.setInt(4, rate_star);
            stm.setString(5, image);
            stm.setInt(6, status);
            stm.setDate(7, java.sql.Date.valueOf(date1));
            stm.setInt(8, orderID);

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
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
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
                        rs.getBoolean("status"),0);
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "", "");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
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
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name,  p.quantity, p.year, p.product_description, \n"
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
                        rs.getBoolean("status"),0);
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "", "");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
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
            String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
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
                        rs.getBoolean("status"),0);
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "", "");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
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

    public List<Feedback> search(String title) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedbackID,f.comment,f.customerID,f.rate_star,f.status,f.update_date_feedback,f.image, p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
                + "                                   c.first_name, c.last_name,f.customerID ,p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price,p.brand,p.status,p.update_date                               \n"
                + "                                 FROM Feedback f inner join Product p on f.productID = p.productID\n"
                + "								 inner join Customer c on f.customerID = c.customerID where c.first_name like '%" + title + "%' or c.last_name like '%" + title + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
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
                        rs.getBoolean("status"),0);
                Customer cus = new Customer(rs.getInt("customerID"), rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "", "");
                Feedback fb = new Feedback(
                        rs.getInt("feedbackID"),
                        null,
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
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;

    }

    public static void main(String[] args) {
        DAOFeedback db = new DAOFeedback();
        System.out.println(db.getFeedBackOrder(1));
    }
}
