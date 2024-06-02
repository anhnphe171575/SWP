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
import java.sql.SQLException;
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
    public int updateCustomer(Customer obj) {
        int n = 0;
        String sql = "UPDATE [Customer]\n"
                + "   SET \n"
                + "           [first_name]=?\n"
                + "           ,[last_name]=?\n"
                + "           ,[phone]=?\n"
                + "           ,[email]=?\n"
                + "           ,[address]=?\n"
                + "           ,[username]=?\n"
                + "           ,[password]=?\n"
                + "           ,[dob]=?\n"
                + "           ,[gender]=?\n"
                + "           ,[status]=?\n"
                + "           ,[securityID]=?\n"
                + "           ,[securityAnswer]=?\n"
                + " WHERE [customerID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            pre.setString(7, obj.getPassword());
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(obj.getDob());
            pre.setDate(8, java.sql.Date.valueOf(date1));
            pre.setBoolean(9, obj.isGender());
            pre.setInt(10, obj.getStatus());
            pre.setInt(11, obj.getSecurity().getSecurityID());
            pre.setString(12, obj.getSecutityAnswer());
            pre.setInt(13, obj.getCustomerID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
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
     public int insertCustomer(Customer obj) {
        int n = 0;
        String sql = "INSERT INTO [Customer]\n"
                + "           ([customerID]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[status]\n"
                + "           ,[securityID]\n"
                + "           ,[securityAnswer])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, obj.getCustomerID());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getPhone());
            pre.setString(5, obj.getEmail());
            pre.setString(6, obj.getAddress());
            pre.setString(7, obj.getUsername());
            pre.setString(8, obj.getPassword());
            pre.setDate(9, (java.sql.Date) obj.getDob());
            pre.setBoolean(10, obj.isGender());
            pre.setInt(11, obj.getStatus());
            pre.setInt(12, obj.getSecurity().getSecurityID());
            pre.setString(13, obj.getSecutityAnswer());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
           
        }

        return n;
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
   public Vector<Customer> getCustomer(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        try {
               PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
                int securityID = rs.getInt("securityID");
                String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, true, status, sq, securityAnswer);
                vector.add(cus); // Added to the vector
            }
        } catch (SQLException ex) {
           
        }
        return vector;
    }
   public Vector<Integer> getStatus(String sql) {
        Vector<Integer> vector = new Vector<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vector.add(rs.getInt("status"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }
     public Vector<String> getEmail(String sql) {
        Vector<String> vector = new Vector<>();
        try {
             PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vector.add(rs.getString("email"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }

    public Vector<String> getPhone(String sql) {
        Vector<String> vector = new Vector<>();
        try {
             PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vector.add(rs.getString("phone"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }

    public Vector<String> getFname(String sql) {
        Vector<String> vector = new Vector<>();
        try {
              PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vector.add(rs.getString("first_name"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }
    
     public Vector<Customer> sort(String option) {
        Vector<Customer> vector = new Vector<Customer>();
        String sql = "select c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.status, c.securityID, sq.security_question, c.securityAnswer from Customer c\n"
                + "inner join SecurityQuestion sq on c.securityID = sq.securityID order by"+option+ "ACS";
        try {
             PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Security sq = new Security(rs.getInt("securityID"),
                        null);
                Customer cus = new Customer(rs.getInt("customerID"),
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
                        rs.getString("securityAnswer")
                );
                vector.add(cus);
            }
        } catch (SQLException ex) {
         ex.printStackTrace();
        }
        return vector;
    }
     public Customer checkAnswer(int id, String answer, String username, String pass) {
        String sql = "select sq.security_question,c.securityAnswer,c.customerID,c.first_name,\n"
                + "c.last_name,c.phone,c.email,c.email,c.address,c.username,c.password,c.dob,c.gender,c.status,c.securityID from Customer c \n"
                + "inner join SecurityQuestion sq on c.securityID = sq.securityID where c.securityID=? and c.securityanswer=? and c.username=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, answer);
            st.setString(3, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //      return new Customer(rs.getInt("CustomerID"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("phone"),
                //    rs.getString("email"),rs.getString("address"),username,password,rs.getDate("dob"),rs.getBoolean("gender"),rs.getInt("securityID"),rs.getString("securityAnswer"));
                int customerID = rs.getInt("customerID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
     

                String securityAnswer = rs.getString("securityAnswer");
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));

                return new Customer(customerID, first_name, last_name, phone, email, address, username, pass, dob, gender, status, sq, securityAnswer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
      public Customer check(String username, String password) {
        String sql = "select sq.security_question,c.securityAnswer,c.customerID,c.first_name,\n"
                + "c.last_name,c.phone,c.email,c.email,c.address,c.username,c.password,c.dob,c.gender,c.status,c.securityID from Customer c \n"
                + "inner join SecurityQuestion sq on c.securityID = sq.securityID where username=  ? and password = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //      return new Customer(rs.getInt("CustomerID"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("phone"),
                //    rs.getString("email"),rs.getString("address"),username,password,rs.getDate("dob"),rs.getBoolean("gender"),rs.getInt("securityID"),rs.getString("securityAnswer"));
                int customerID = rs.getInt("customerID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
              

                String securityAnswer = rs.getString("securityAnswer");
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));

                return new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, status, sq, securityAnswer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
      public void change(String user, String pass) {
        String sql = "update Customer set password = ? where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, user);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        boolean check = dao.loginCus("user", "123");
        System.out.println(check);
    }
}