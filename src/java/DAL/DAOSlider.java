/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Slider;
import Entity.User;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author phuan
 */
public class DAOSlider extends DBContext {

    public Vector<Slider> getSlider(String sql) {
        Vector<Slider> vector = new Vector<Slider>();
        try {
            Statement sta = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                int sliderID = rs.getInt("sliderID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String link = rs.getString("link");
                int status = rs.getInt("status");
                String notes = rs.getString("notes");
                int userID = rs.getInt("userID");
                int page_order = rs.getInt("page_order");
                Date update = rs.getDate("slider_date_createby");
                User us = new User();

                Slider sl = new Slider(sliderID, title, image, link, status, notes, us, page_order,update);
                vector.add(sl);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        return vector;

    }
    
    public void hideShow(int id, int status) {
        try {
            String sql = "UPDATE [dbo].[Slider]\n"
                    + "   SET \n"
                    + "      [status] = " + status + "\n"
                    + "\n"
                    + " WHERE sliderID=" + id;

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public int updateSlider(Slider obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Slider]\n"
                + "   SET \n [title] = ?"
                + "      ,[image] = ?\n"
                + "      ,[link] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[notes] = ?\n"
                + "      ,[page_order] = ?\n"
                + " WHERE [sliderID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getTitle());
            pre.setString(2, obj.getImage());
            pre.setString(3, obj.getLink());
            pre.setInt(4, obj.getStatus());
            pre.setString(5, obj.getNotes());
            pre.setInt(6, obj.getPage_order());
            pre.setInt(7, obj.getSliderID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOSlider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return n;
    }
    public Vector<Slider> getByStatus(String status_1) {
     
        Vector<Slider> slider = new Vector<>();
        String sql = "select s.sliderID, s.title, s.image, s.link, s.status, s.notes, s.page_order, s.slider_date_createby, us.UserID from Slider s\n"
                                + "inner join [user] us on us.UserID = s.UserID" + status_1; 
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()) {
                int sliderID = rs.getInt("sliderID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String link = rs.getString("link");
                int status = rs.getInt("status");
                String notes = rs.getString("notes");
                int page_order = rs.getInt("page_order");
                Date slider_date_createby = rs.getDate("slider_date_createby");
                int userID = rs.getInt("userID");
                User us = new User();
                us.setUserID(userID);
                Slider sl = new Slider(sliderID, title, image, link, status, notes, us, page_order, slider_date_createby);
                
              slider.add(sl);
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return slider;
    }
     public Slider getBySliderID(int id) {
        Slider sl =null;
        String sql = "select s.sliderID, s.title, s.image, s.link, s.status, s.notes, s.page_order, s.slider_date_createby, us.UserID from Slider s\n"
                                + "inner join [user] us on us.UserID = s.UserID where sliderID="+id; 
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()) {
                int sliderID = rs.getInt("sliderID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String link = rs.getString("link");
                int status = rs.getInt("status");
                String notes = rs.getString("notes");
                int page_order = rs.getInt("page_order");
                Date slider_date_createby = rs.getDate("slider_date_createby");
                int userID = rs.getInt("userID");
                User us = new User();
                us.setUserID(userID);
                 sl = new Slider(sliderID, title, image, link, status, notes, us, page_order, slider_date_createby);
                
              
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return sl;
    }
      public Vector<Integer> getStatus(String sql) {
        Vector<Integer> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                vector.add(rs.getInt("status"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }
    public static void main(String[] args) {
        DAOSlider db = new DAOSlider();
        System.out.println(db.getSlider(" SELECT top 1 * FROM Slider ORDER BY page_order"));
    }
}
