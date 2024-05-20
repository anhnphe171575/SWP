/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Customer;
import Entity.Security;
import java.util.Vector;
import org.apache.catalina.util.CustomObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author phuan
 */
public class DAOCustomer extends DBContext {
    public Customer getCusByEmail(String email) {
        Customer c = null;
        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.email,c.address,c.username,c.password,c.dob,c.gender,c.status,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question from Customer c\n"
                    + "inner join  securityQuestion sq on c.securityID = sq.securityID\n"
                    + "where email=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"),
                        rs.getString("security_question"));
                c = new Customer(rs.getInt("customerID"),
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
                        sq,
                        rs.getString("securityAnswer"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }
    public Customer getCus(String username) {
        Customer c = null;
        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.email,c.address,c.username,c.password,c.dob,c.gender,c.status,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question from Customer c\n"
                    + "inner join  securityQuestion sq on c.securityID = sq.securityID\n"
                    + "where username=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"),
                        rs.getString("security_question"));
                c = new Customer(rs.getInt("customerID"),
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
                        sq,
                        rs.getString("securityAnswer"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }

    public boolean loginCus(String username, String password) {
        Vector<Customer> vector = new Vector<>();
        boolean flag = false;

        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.email,c.address,c.username,c.password,c.dob,c.gender,c.status,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question from Customer c\n"
                    + "inner join  securityQuestion sq on c.securityID = sq.securityID\n"
                    + "where username=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"),
                        rs.getString("security_question"));
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
                        rs.getInt("status"),
                        sq,
                        rs.getString("securityAnswer"));
                vector.add(c);
                flag = true;

            }
        } catch (Exception e) {
        }
        return flag;
    }
   public void UpdateNewPass(String email, String passWord){
        String query = "UPDATE Customer SET password=? WHERE email=?";
        try {
            
         PreparedStatement pre = conn.prepareStatement(query);
            
            pre.setString(1, passWord);
            pre.setString(2, email);
            pre.executeUpdate();
        } catch (Exception e) {

        }
        //return false;
   }
   
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        boolean check = dao.loginCus("user", "123");
        System.out.println(check);
    }
}
