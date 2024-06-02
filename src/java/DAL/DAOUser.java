/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.DBContext;
import java.sql.PreparedStatement;
import java.util.Vector;
import Entity.User;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Stack;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Security;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class DAOUser extends DBContext {

    public boolean login(String username, String password) {
        boolean flag = false;
        String sql = "select * from [User] where username =? and password =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
        }
        return flag;
    }

    public User getUserByLogin(String var1, String sql) {
        User u = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, var1);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                  Security se = new Security(rs.getInt("securityID"), "");
                 u = new User(rs.getInt("UserID"),
                         rs.getString("first_name"),
                         rs.getString("last_name"),
                         rs.getString("phone"),
                        rs.getString("email"),
                         rs.getString("address"),
                         rs.getString("username"),
                         rs.getString("password"),
                        rs.getDate("dob"),
                         rs.getBoolean("gender"),
                         rs.getInt("status"),
                         rs.getInt("role"),
                        se,
                         rs.getString("securityAnswer"));
            }
        } catch (Exception e) {
        }
        return u;
    }
      public void UpdateNewPass(String email, String passWord){
        String query = "UPDATE User SET password=? WHERE email=?";
        try {
            
         PreparedStatement pre = conn.prepareStatement(query);
            
            pre.setString(1, passWord);
            pre.setString(2, email);
            pre.executeUpdate();
        } catch (Exception e) {

        }
        //return false;
   }
        public User getUserByLogin(String username) {
        User u = null;
        String sql = "select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "u.dob,u.gender,u.status, u.role,u.securityID,u.securityAnswer,s.security_question from [User] u\n"
                + "inner join SecurityQuestion s on u.securityID=s.securityID where username =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                u = new User(rs.getInt("UserID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("dob"),
                        rs.getBoolean("gender"),
                        rs.getInt("status"),
                        rs.getInt("role"),
                        sq,
                        rs.getString("securityAnswer"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }
    public Vector<User> getUser(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                User obj = new User(rs.getInt("UserID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("dob"),
                        rs.getBoolean("gender"),
                        rs.getInt("status"),
                        rs.getInt("role"),
                        se,
                        rs.getString("securityAnswer"));
                vector.add(obj);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return vector;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        boolean check = dao.login("user", "123");
        System.out.println(check);
    }
}
