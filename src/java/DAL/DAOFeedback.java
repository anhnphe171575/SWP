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
/**
 *
 * @author phuan
 */
public class DAOFeedback extends DBContext{
    public int CountFeedback(int pid){
        int count = -1;
        try {
            
            PreparedStatement stm = conn.prepareStatement("select count(feedbackID) as [count] from Feedback where productID = ? And status = 1");
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                 count = rs.getInt("count");
            }
    }
        catch(Exception e){
            e.printStackTrace();;
        }
        return  count;
    }
    
    public void InsertFeedBack(int pid, int cusid, String comment, int rate_star, String image, int status, Date date_create){
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
                  stm.setDate(7,  java.sql.Date.valueOf(date1));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Feedback> getFeedBackByProID(int pid){
         List<Feedback> list = new ArrayList<>();
        try {
            String sql="select * from Feedback fb \n" +
"inner join Customer c on fb.customerID = c.customerID where fb.productID = ? and fb.status = 1 order by fb.update_date_feedback";
            PreparedStatement stm = conn.prepareStatement(sql);
              stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
              Customer cus = new Customer(0, rs.getString("first_name"), rs.getString("last_name"), "", "", "", "", "", null, true, null, null, "");
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
    public static void main(String[] args) {
        DAOFeedback db = new DAOFeedback();
        System.out.println(db.getFeedBackByProID(12));
    }
}
