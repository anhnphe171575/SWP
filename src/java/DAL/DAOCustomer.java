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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     public Boolean addCustomer(String first_name, String last_name, String phone, String email, String address, String username, String password, Date dob, Boolean gender, int status, int securityID, String securityAnswer) {
        try {
            //java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(dob);
            String query = "INSERT INTO Customer (first_name, last_name, phone, email, address, username, password, dob, gender, status, "
                    + "securityID, securityAnswer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, first_name);
            stm.setString(2, last_name);
            stm.setString(3, phone);
            stm.setString(4, email);
            stm.setString(5, address);
            stm.setString(6, username);
            stm.setString(7, password);
            stm.setDate(8, java.sql.Date.valueOf(date1));
            stm.setBoolean(9, gender);
            stm.setInt(10, status);
            stm.setInt(11, securityID);
            stm.setString(12, securityAnswer);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }
    
   public int getLastCustomerID() {
        Integer lastCustomerID = null;
        try {
            String query = "SELECT MAX(customerID) AS lastID FROM Customer";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lastCustomerID = rs.getInt("lastID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (lastCustomerID != null) ? lastCustomerID : 9;
    }
   public List<Customer> getCustomer() {
        List<Customer> customer = new ArrayList<>();
        try {
            String query = "Select c.customerID, c.first_name, c.last_name,c.phone,c.email,c.address,"
                    + "c.username,c.password,c.dob,c.gender,c.status,c.securityID,sq.security_question,c.securityAnswer \n"
                    + "from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Security s = new Security(rs.getInt("securityID"), rs.getString("security_question"));
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
                        
                        s,rs.getString("securityAnswer"));
                customer.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return customer;
    }
   
   
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        boolean check = dao.loginCus("user", "123");
        System.out.println(check);
    }
}
