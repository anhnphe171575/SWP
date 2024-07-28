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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author phuan
 */
public class DAOCustomer extends DBContext {

    public boolean checkDuplicateUpdate(String field, String value, int customerID) {
        String sqlCustomer = "SELECT COUNT(*) FROM Customer WHERE " + field + " = ? AND customerID != ?";
        String sqlStaff = "SELECT COUNT(*) FROM Staff WHERE " + field + " = ?";

        try (PreparedStatement psCustomer = conn.prepareStatement(sqlCustomer); PreparedStatement psStaff = conn.prepareStatement(sqlStaff)) {

            // Check in Customer table
            psCustomer.setString(1, value);
            psCustomer.setInt(2, customerID);
            try (ResultSet rsCustomer = psCustomer.executeQuery()) {
                if (rsCustomer.next() && rsCustomer.getInt(1) > 0) {
                    return true; // Duplicate found in Customer
                }
            }

            // Check in Staff table
            psStaff.setString(1, value);
            try (ResultSet rsStaff = psStaff.executeQuery()) {
                if (rsStaff.next() && rsStaff.getInt(1) > 0) {
                    return true; // Duplicate found in Staff
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicateInCustomerAndStaff(String field, String value) {
        String sqlCustomer = "SELECT COUNT(*) FROM Customer WHERE " + field + " = ?";
        String sqlStaff = "SELECT COUNT(*) FROM Staff WHERE " + field + " = ?";
        boolean isDuplicate = false;

        try (
                PreparedStatement psCustomer = conn.prepareStatement(sqlCustomer); PreparedStatement psStaff = conn.prepareStatement(sqlStaff)) {
            psCustomer.setString(1, value);
            psStaff.setString(1, value);

            try (ResultSet rsCustomer = psCustomer.executeQuery()) {
                if (rsCustomer.next()) {
                    isDuplicate = rsCustomer.getInt(1) > 0;
                }
            }

            if (!isDuplicate) { // If not found in Customer, check in Staff
                try (ResultSet rsStaff = psStaff.executeQuery()) {
                    if (rsStaff.next()) {
                        isDuplicate = rsStaff.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    public boolean checkDuplicate(String field, String value) {
        String sql = "SELECT COUNT(*) FROM Customer WHERE " + field + " = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicate1(String field, String value) {
        String sql = "SELECT COUNT(*) FROM Staff WHERE " + field + " = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vector<Customer> getListByPage(Vector<Customer> list, int start, int end) {
        Vector<Customer> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Customer> sortByActivityHistory() {
        List<Customer> custo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Customer ORDER BY activity_history";
            PreparedStatement stm = conn.prepareStatement(query);
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                //String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                //sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
                custo.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custo;
    }

    public List<Customer> sortByPhone() {
        List<Customer> custo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Customer ORDER BY phone";
            PreparedStatement stm = conn.prepareStatement(query);
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                //String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                //sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
                custo.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custo;
    }

    public List<Customer> sortByEmail() {
        List<Customer> custo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Customer ORDER BY email";
            PreparedStatement stm = conn.prepareStatement(query);
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                //String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                //sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
                custo.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custo;
    }

    public List<Customer> sortByFullname() {
        List<Customer> custo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Customer ORDER BY first_name + last_name";
            PreparedStatement stm = conn.prepareStatement(query);
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                //String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                //sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
                custo.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custo;
    }

    public Customer getCustomerID(int id) {
        Customer sl = null;
        String sql = "SELECT c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image FROM Customer c INNER JOIN SecurityQuestion sq ON c.securityID = sq.securityID WHERE c.customerID =" + id;
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                sl = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);

            }
        } catch (SQLException ex) {

        }
        return sl;
    }

    public Customer getCusByEmail(String email) {
        Customer c = null;
        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.activity_history,c.email,c.address,c.username,c.password,c.dob,c.gender,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question, c.image from Customer c\n"
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
                        rs.getDate("activity_history"),
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }

    public Customer getCusByUserName(String UserName) {
        Customer c = null;
        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.image,c.phone,c.activity_history,c.email,c.address,c.username,c.password,c.dob,c.gender,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question, c.image from Customer c\n"
                    + "inner join  securityQuestion sq on c.securityID = sq.securityID\n"
                    + "where c.username=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, UserName);
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
                        rs.getDate("activity_history"),
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }

    public int updateCustomerActivity(int cusid, Date activity) {
        int n = 0;
        String sql = "UPDATE [Customer]\n"
                + "   SET \n"
                + "           [activity_history]=?\n"
                + " WHERE [customerID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(activity);
            pre.setDate(1, java.sql.Date.valueOf(date1));
            pre.setInt(2, cusid);

            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
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
                + "           ,[dob]=?\n"
                + "           ,[gender]=?\n"
                + "           ,[securityID]=?\n"
                + "           ,[securityAnswer]=?\n"
                + "           ,[image]=?\n"
                + " WHERE [customerID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(obj.getDob());
            pre.setDate(7, java.sql.Date.valueOf(date1));
            pre.setBoolean(8, obj.isGender());
            pre.setInt(9, obj.getSecurity().getSecurityID());
            pre.setString(10, obj.getSecutityAnswer());
            pre.setString(11, obj.getImage());
            pre.setInt(12, obj.getCustomerID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public Customer getCus(String username) {
        Customer c = null;
        try {
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.email,c.address,c.username,c.password,c.dob,c.gender,c.activity_history,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question, c.image from Customer c\n"
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
                        rs.getDate("activity_history"),
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
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
            String sql = "select c.customerID,c.first_name,c.last_name,c.phone,c.email,c.address,c.activity_history,c.username,c.password,c.dob,c.gender,c.securityID,c.securityAnswer,\n"
                    + "sq.security_question, c.image from Customer c\n"
                    + "inner join  securityQuestion sq on c.securityID = sq.securityID\n"
                    + "where c.username=? and c.password=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);

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
                        rs.getDate("activity_history"),
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
                vector.add(c);
                flag = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void UpdateNewPass(String email, String passWord) {
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

    public void addCustomer(String first_name, String last_name, String phone, String email, String address, String username, String password, Date dob, Boolean gender, Date activity, int securityID, String securityAnswer) {
        try {
            //java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(dob);
            String date2 = mySimpleDateFormat.format(activity);
            String query = "INSERT INTO Customer (first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, "
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
            stm.setDate(10, java.sql.Date.valueOf(date2));
            stm.setInt(11, securityID);
            stm.setString(12, securityAnswer);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertCustomer(Customer obj) {
        int n = 0;
        if (checkDuplicate("email", obj.getEmail())
                || checkDuplicate("username", obj.getUsername())
                || checkDuplicate("phone", obj.getPhone())) {
            throw new IllegalArgumentException("Duplicate email, username, or phone number");
        }
        String sql = "INSERT INTO [Customer]\n"
                + "           ([first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[securityID]\n"
                + "           ,[securityAnswer])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            pre.setString(7, obj.getPassword());
            pre.setDate(8, new java.sql.Date(obj.getDob().getTime())); // Convert java.util.Date to java.sql.Date
            pre.setBoolean(9, obj.isGender());
            pre.setInt(10, obj.getSecurity().getSecurityID());
            pre.setString(11, obj.getSecutityAnswer());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Or use a logger to log the exception
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Or use a logger to log the exception
                }
            }
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
                    + "c.username,c.password,c.dob,c.gender,c.activity_history,c.securityID,sq.security_question,c.securityAnswer, c.image \n"
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
                        rs.getDate("activity_history"),
                        s, rs.getString("securityAnswer"),
                        rs.getString("image"));
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
                Date activity_history = rs.getDate("activity_history");
                int securityID = rs.getInt("securityID");
                String sercurityquestion = rs.getString("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityID);
                sq.setSecurity_question(sercurityquestion);
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Customer cus = new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
                vector.add(cus); // Added to the vector
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

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
        String sql = "select c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer from Customer c\n"
                + "inner join SecurityQuestion sq on c.securityID = sq.securityID order by" + option + "ACS";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                        rs.getDate("activity_history"),
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image")
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
                + "c.last_name,c.phone,c.email,c.email,c.address,c.username,c.password,c.dob,c.gender,c.activity_history,c.securityID, c.image from Customer c \n"
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
                Date activity_history = rs.getDate("activity_history");

                String securityAnswer = rs.getString("securityAnswer");
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                String image = rs.getString("image");

                return new Customer(customerID, first_name, last_name, phone, email, address, username, pass, dob, gender, activity_history, sq, securityAnswer, image);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Customer check(String username, String password) {
        String sql = "select sq.security_question,c.securityAnswer,c.customerID,c.first_name,\n"
                + "c.last_name,c.phone,c.email,c.email,c.address,c.username,c.password,c.dob,c.gender,c.activity_history,c.securityID, c.image from Customer c \n"
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
                Date activity_history = rs.getDate("activity_history");

                String securityAnswer = rs.getString("securityAnswer");
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                String image = rs.getString("image");

                return new Customer(customerID, first_name, last_name, phone, email, address, username, password, dob, gender, activity_history, sq, securityAnswer, image);
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
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            DAOCustomer dao = new DAOCustomer();
            String date1 = "2003-11-08";
            String date2 = "2003-11-07";
            Date dat1 = dateFormat.parse(date1);
            Date dat2 = dateFormat.parse(date2);
            dao.addCustomer("david", "nguyen", "0946129916", "vinhnm", "hn", "vinh123", "123", dat1, Boolean.TRUE, dat2, 1, "Anna");
        } catch (ParseException e) {

        }

    }

}
