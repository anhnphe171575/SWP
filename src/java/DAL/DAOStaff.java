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
import Entity.Role;
import Entity.Security;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOStaff extends DBContext {

    public Role getRoleName(int id) {
        Role r = null;
        String sql = "select * from [Role] where RoleID=" + id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                r = new Role(rs.getInt("RoleID"), rs.getString("Role_Name"));
            }
        } catch (Exception e) {
        }
        return r;
    }

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
                Role role = new Role(rs.getInt("RoleID"), "");
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
                        role,
                        se,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return u;
    }

    public void UpdateNewPass(String email, String passWord) {
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
                + "u.dob,u.gender,u.status, u.RoleID,u.securityID,r.Role_Name,u.securityAnswer,s.security_question,u.image from [User] u\n"
                + "inner join SecurityQuestion s on u.securityID=s.securityID "
                + "inner join [Role] r on r.RoleID=u.RoleID"
                + " where username =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                Role role = new Role(rs.getInt("RoleID"), "Role_Name");
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
                        role,
                        sq,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
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
                Role role = new Role(rs.getInt("RoleID"), "");
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
                        role,
                        se,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));
                vector.add(obj);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return vector;
    }

    public int insertUser(User obj) {
        int n = 0;
        String sql = "INSERT INTO [User]\n"
                + "           ([first_name],[last_name],[phone]\n"
                + "           ,[email],[address],[username],[password],[dob],[gender],[status],[RoleID],[securityID],[securityAnswer],[image]\n)"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(obj.getDob());
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername()); // Thêm username vào đây
            pre.setString(7, obj.getPassword());
            pre.setDate(8, java.sql.Date.valueOf(date1));
            pre.setBoolean(9, obj.isGender());
            pre.setInt(10, obj.getStatus());
            pre.setInt(11, obj.getRole().getRoleID());
            pre.setInt(12, obj.getSecurity().getSecurityID());
            pre.setString(13, obj.getSecurityAnswer());
            pre.setString(14, obj.getImage());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void UpdateUser(User obj) {
        String sql = "UPDATE [User] \n"
                + "SET \n"
                + "    [first_name] = ?,\n"
                + "    [last_name] = ?,\n"
                + "    [phone] = ?,\n"
                + "    [email] = ?,\n"
                + "    [address] = ?,\n"
                + "    [username] = ?,\n"
                + "    [password] = ?,\n"
                + "    [dob] = ?,\n"
                + "    [gender] = ?,\n"
                + "    [status] = ?,\n"
                + "    [RoleID] = ?,\n"
                + "    [securityID] = ?,\n"
                + "    [securityAnswer] = ?,\n"
                + "    [image] = ?\n"
                + "WHERE [UserID] = ?;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDatatype(indexOf?,parameterValue);
            // java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = mySimpleDateFormat.format(obj.getDob());
            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            pre.setString(7, obj.getPassword());
            pre.setDate(8, java.sql.Date.valueOf(date1));
            pre.setBoolean(9, obj.isGender());
            pre.setInt(10, obj.getStatus());
            pre.setInt(11, obj.getRole().getRoleID());
            pre.setInt(12, obj.getSecurity().getSecurityID());
            pre.setString(13, obj.getSecurityAnswer());
            pre.setString(14, obj.getImage());
            pre.setInt(15, obj.getUserID());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAll() {
        String sql = "select * from [User]";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int UserID = rs.getInt(1);

                String first_name = rs.getString(2);

                String last_name = rs.getString(3),
                        phone = rs.getString(4),
                        email = rs.getString(5),
                        address = rs.getString(6),
                        username = rs.getString(7),
                        password = rs.getString(8);
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");

                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                Role role = new Role(rs.getInt("roleID"), "");
                Security securityID = new Security(rs.getInt("securityID"), "");
                User obj = new User(UserID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                System.out.println(obj);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<User> getUsers() {
        Vector<User> vector = new Vector<User>();
        String sql = "select u.UserID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [User] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int UserID = rs.getInt("UserID");
                // int staff_id=rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                // String first_name=rs.getString(2);
                String last_name = rs.getString("last_name"),
                        phone = rs.getString("phone"),
                        email = rs.getString("email"),
                        address = rs.getString("address"),
                        username = rs.getString("username"),
                        password = rs.getString("password");
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), "security_question");
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                User obj = new User(UserID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<User> getUser(String query, Map<String, String> parameters) {
        Vector<User> vector = new Vector<User>();
        String sql = "SELECT u.UserID, u.first_name, u.last_name, u.phone, u.email, u.address, "
                + "u.username, u.password, u.dob, u.gender, u.status, u.RoleID, u.securityID, u.securityAnswer, u.image, "
                + "r.Role_Name, s.security_question "
                + "FROM [User] u "
                + "INNER JOIN [Role] r ON u.RoleID = r.RoleID "
                + "INNER JOIN SecurityQuestion s ON s.securityID = u.securityID " + query;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            for (String key : parameters.keySet()) {
                ps.setString(index++, parameters.get(key));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
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
                    Role role = new Role(rs.getInt("RoleID"), rs.getString("Role_Name"));
                    Security securityID = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                    String securityAnswer = rs.getString("securityAnswer");
                    String image = rs.getString("image");
                    User obj = new User(UserID, first_name, last_name, phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                    vector.add(obj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeUser(int UserID) {
        //check foreign key
        ResultSet rsSlider = this.getData("select * from Slider " + " where UserID=" + UserID);
        ResultSet rsSetting = this.getData("select * from Setting " + " where UserID=" + UserID);
        ResultSet rsOrder = this.getData("select * from Order " + " where UserID=" + UserID);
        ResultSet rsPost = this.getData("select * from Post " + " where UserID=" + UserID);
        ResultSet rsSaleCategory = this.getData("select * from SaleCategory " + " where UserID=" + UserID);

        int n = 0;
        try {
            //if exist
            if (rsSlider.next() && rsSetting.next() && rsOrder.next() && rsPost.next() && rsSaleCategory.next()) {
                return n;

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

//        ResultSet rsCustomer = this.getData("select * from customers "
//                + " where "+CustomerID+" in (select distinct customer_id from customers)");
//        
//        //check manager
//        try {
//            //if exist
//            if(rsCustomer.next()){
//                return n;
//            
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);        
//        }
        String sql = "delete from User where UserID=" + UserID;
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<User> sort(String option) {
        Vector<User> vector = new Vector<>();
        String sql = "select * from [User] u inner join [Role] r on u.RoleID=r.RoleID \n"
                + "order by  " + option + " ASC";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("[Role_Name]"));
                Security securityID = new Security(rs.getInt("securityID"), "");
                User u = new User(rs.getInt("UserID"),
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
                        role,
                        securityID,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));

                vector.add(u);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return vector;

    }

    public User getUsersByID(int id) {
        User u = null;
        String sql = "select u.UserID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [User] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID where u.UserID=" + id;
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int UserID = rs.getInt("UserID");
                // int staff_id=rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                // String first_name=rs.getString(2);
                String last_name = rs.getString("last_name"),
                        phone = rs.getString("phone"),
                        email = rs.getString("email"),
                        address = rs.getString("address"),
                        username = rs.getString("username"),
                        password = rs.getString("password");
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                u = new User(UserID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public User getUsersByemail(String email1) {
        User u = null;
        String sql = "select u.UserID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [User] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID where u.email='" + email1 + "'";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int UserID = rs.getInt("UserID");
                // int staff_id=rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                // String first_name=rs.getString(2);
                String last_name = rs.getString("last_name"),
                        phone = rs.getString("phone"),
                        email = rs.getString("email"),
                        address = rs.getString("address"),
                        username = rs.getString("username"),
                        password = rs.getString("password");
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                u = new User(UserID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public User getUsersByUsername(String username1) {
        User u = null;
        String sql = "select u.UserID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [User] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID where u.username='" + username1 + "'";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int UserID = rs.getInt("UserID");
                // int staff_id=rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                // String first_name=rs.getString(2);
                String last_name = rs.getString("last_name"),
                        phone = rs.getString("phone"),
                        email = rs.getString("email"),
                        address = rs.getString("address"),
                        username = rs.getString("username"),
                        password = rs.getString("password");
                Date dob = rs.getDate("dob");
                Boolean gender = rs.getBoolean("gender");
                int status = rs.getInt("status");
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                String securityAnswer = rs.getString("securityAnswer");
                String image = rs.getString("image");
                u = new User(UserID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public Vector<Role> getRole(String sql) {
        Vector<Role> vector = new Vector<Role>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                // System.out.println(obj);
                Role obj = new Role(rs.getInt("roleID"), rs.getString("role_name"));
                vector.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return vector;

    }

    public User notUserbyEmail(String emailU, String emailI) {
        User u = null;
        String sql = "	SELECT \n"
                + "    u.UserID, \n"
                + "    u.first_name, \n"
                + "    u.last_name, \n"
                + "    u.phone, \n"
                + "    u.email, \n"
                + "    u.address,\n"
                + "    u.username, \n"
                + "    u.password, \n"
                + "    u.dob, \n"
                + "    u.gender, \n"
                + "    u.status, \n"
                + "    u.RoleID, \n"
                + "    u.securityID, \n"
                + "    u.securityAnswer, \n"
                + "    u.image,\n"
                + "    r.Role_Name, \n"
                + "    s.security_question \n"
                + "FROM [User] u \n"
                + "INNER JOIN [Role] r ON u.RoleID = r.RoleID\n"
                + "INNER JOIN SecurityQuestion s ON s.securityID = u.securityID \n"
                + "WHERE u.email <> '" + emailU + "'\n"
                + "  AND u.email = '" + emailI + "';";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), "");
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
                        role,
                        securityID,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return u;

    }

    public User notUserbyUsername(String usernameU, String usernameI) {
        User u = null;
        String sql = "	SELECT \n"
                + "    u.UserID, \n"
                + "    u.first_name, \n"
                + "    u.last_name, \n"
                + "    u.phone, \n"
                + "    u.email, \n"
                + "    u.address,\n"
                + "    u.username, \n"
                + "    u.password, \n"
                + "    u.dob, \n"
                + "    u.gender, \n"
                + "    u.status, \n"
                + "    u.RoleID, \n"
                + "    u.securityID, \n"
                + "    u.securityAnswer, \n"
                + "    u.image,\n"
                + "    r.Role_Name, \n"
                + "    s.security_question \n"
                + "FROM [User] u \n"
                + "INNER JOIN [Role] r ON u.RoleID = r.RoleID\n"
                + "INNER JOIN SecurityQuestion s ON s.securityID = u.securityID \n"
                + "WHERE u.username <>'" + usernameU + "'\n"
                + "  AND u.username ='" + usernameI + "';";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), "");
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
                        role,
                        securityID,
                        rs.getString("securityAnswer"),
                        rs.getString("image"));

                
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return u;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        System.out.println(dao.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n" +
"                u.dob,u.gender,u.status,u.image, u.RoleID,u.securityID,u.securityAnswer,s.security_question from [User] u\n" +
"                 inner join SecurityQuestion s on u.securityID=s.securityID\n" +
"                inner join [Role] r on r.RoleID = u.RoleID"));
//        System.out.println(dao.getUsersByID(2));
    }
}
