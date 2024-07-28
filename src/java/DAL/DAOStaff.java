/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.DBContext;
import java.sql.PreparedStatement;
import java.util.Vector;
import Entity.Staff;
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
        String sql = "  select * from Staff where username =? and password =? and status = 1";
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

    public Staff getStaffByLogin(String var1, String sql) {
        Staff u = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, var1);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                Role role = new Role(rs.getInt("RoleID"), "");
                u = new Staff(rs.getInt("StaffID"),
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
        String query = "UPDATE Staff SET password=? WHERE email=?";
        try {

            PreparedStatement pre = conn.prepareStatement(query);

            pre.setString(1, passWord);
            pre.setString(2, email);
            pre.executeUpdate();
        } catch (Exception e) {

        }
        //return false;
    }

    public Staff getStaffByLogin(String username) {
        Staff u = null;
        String sql = "SELECT u.StaffID, u.first_name, u.last_name, u.phone, u.email, u.address, u.username, u.password,\n"
                + "       u.dob, u.gender, u.status, u.RoleID, u.securityID, r.Role_Name, u.securityAnswer, s.security_question, u.image\n"
                + "FROM [Staff] u\n"
                + "INNER JOIN SecurityQuestion s ON u.securityID = s.securityID\n"
                + "INNER JOIN [Role] r ON r.RoleID = u.RoleID\n"
                + "WHERE username = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"), rs.getString("security_question"));
                Role role = new Role(rs.getInt("RoleID"), "Role_Name");
                u = new Staff(rs.getInt("StaffID"),
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

    public Vector<Staff> getStaff(String sql) {
        Vector<Staff> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                Role role = new Role(rs.getInt("RoleID"), "");
                Staff obj = new Staff(rs.getInt("StaffID"),
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

    public int insertStaff(Staff obj) {
        int n = 0;
        String sql = "INSERT INTO [Staff]\n"
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
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void UpdateStaff(Staff obj) {
        String sql = "UPDATE [Staff] \n"
                + "SET \n"
                + "    [first_name] = ?,\n"
                + "    [last_name] = ?,\n"
                + "    [phone] = ?,\n"
                + "    [email] = ?,\n"
                + "    [address] = ?,\n"
                + "    [username] = ?,\n"
                + "    [dob] = ?,\n"
                + "    [gender] = ?,\n"
                + "    [status] = ?,\n"
                + "    [RoleID] = ?,\n"
                + "    [securityID] = ?,\n"
                + "    [securityAnswer] = ?,\n"
                + "    [image] = ?\n"
                + "WHERE [StaffID] = ?;";
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
            pre.setDate(7, java.sql.Date.valueOf(date1));
            pre.setBoolean(8, obj.isGender());
            pre.setInt(9, obj.getStatus());
            pre.setInt(10, obj.getRole().getRoleID());
            pre.setInt(11, obj.getSecurity().getSecurityID());
            pre.setString(12, obj.getSecurityAnswer());
            pre.setString(13, obj.getImage());
            pre.setInt(14, obj.getStaffID());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAll() {
        String sql = "select * from [Staff]";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int StaffID = rs.getInt(1);
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
                Staff obj = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                System.out.println(obj);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Staff> getStaffs() {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = "select u.StaffID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [Staff] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int StaffID = rs.getInt("StaffID");
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
                Staff obj = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Staff> searchStaffByFullName(String fullName) {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = "select u.StaffID, u.first_name, u.last_name, u.phone, u.email, u.address, \n"
                + "       u.username, u.password, u.dob, u.gender, u.status, u.RoleID, u.securityID, \n"
                + "       u.securityAnswer, u.image, r.Role_Name, s.security_question \n"
                + "from [Staff] u \n"
                + "inner join [Role] r on u.RoleID = r.RoleID \n"
                + "inner join SecurityQuestion s on s.securityID = u.securityID \n"
                + "where CONCAT(u.first_name, ' ', u.last_name) = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int StaffID = rs.getInt("StaffID");
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

                Staff obj = new Staff(StaffID, first_name, last_name, phone, email, address,
                        username, password, dob, gender, status, role, securityID,
                        securityAnswer, image);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Staff> searchStaffByEmail(String emailfind) {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = "select u.StaffID, u.first_name, u.last_name, u.phone, u.email, u.address, "
                + "u.username, u.password, u.dob, u.gender, u.status, u.RoleID, u.securityID, "
                + "u.securityAnswer, u.image, r.Role_Name, s.security_question "
                + "from [Staff] u "
                + "inner join [Role] r on u.RoleID = r.RoleID "
                + "inner join SecurityQuestion s on s.securityID = u.securityID "
                + "where u.email LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + emailfind + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int StaffID = rs.getInt("StaffID");
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
                Staff obj = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);

                vector.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Staff> searchStaffByPhone(String phonefind) {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = "select u.StaffID, u.first_name, u.last_name, u.phone, u.email, u.address, "
                + "u.username, u.password, u.dob, u.gender, u.status, u.RoleID, u.securityID, "
                + "u.securityAnswer, u.image, r.Role_Name, s.security_question "
                + "from [Staff] u "
                + "inner join [Role] r on u.RoleID = r.RoleID "
                + "inner join SecurityQuestion s on s.securityID = u.securityID "
                + "where u.phone LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phonefind);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int StaffID = rs.getInt("StaffID");
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
                Staff obj = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);

                vector.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Staff> getStaff1(String query, Map<String, String> parameters) {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = query;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            for (String key : parameters.keySet()) {
                ps.setString(index++, parameters.get(key));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int StaffID = rs.getInt("StaffID");
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
                    Staff obj = new Staff(StaffID, first_name, last_name, phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                    vector.add(obj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Staff> getStaff(String query, Map<String, String> parameters) {
        Vector<Staff> vector = new Vector<Staff>();
        String sql = "select * from Staff s inner join [Role] r on s.RoleID = r.RoleID inner join SecurityQuestion sq on s.securityID = sq.[securityID]";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int StaffID = rs.getInt("StaffID");
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
                    Staff obj = new Staff(StaffID, first_name, last_name, phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                    vector.add(obj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

//    public int removeStaff(int StaffID) {
//        //check foreign key
//        ResultSet rsSlider = this.getData("select * from Slider " + " where StaffID=" + StaffID);
//        ResultSet rsSetting = this.getData("select * from Setting " + " where SettingID=" + StaffID);
//        ResultSet rsOrder = this.getData("select * from Order " + " where StaffID=" + StaffID);
//        ResultSet rsPost = this.getData("select * from Post " + " where StaffID=" + StaffID);
//        ResultSet rsSaleCategory = this.getData("select * from SaleCategory " + " where StaffID=" + StaffID);
//
//        int n = 0;
//        try {
//            //if exist
//            if (rsSlider.next() && rsSetting.next() && rsOrder.next() && rsPost.next() && rsSaleCategory.next()) {
//                return n;
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
////        ResultSet rsCustomer = this.getData("select * from customers "
////                + " where "+CustomerID+" in (select distinct customer_id from customers)");
////        
////        //check manager
////        try {
////            //if exist
////            if(rsCustomer.next()){
////                return n;
////            
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);        
////        }
//        String sql = "delete from Staff where StaffID=" + StaffID;
//        System.out.println(sql);
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return n;
//    }
    public Vector<Staff> sort(String option) {
        Vector<Staff> vector = new Vector<>();
        String sql = "select * from [Staff] u inner join [Role] r on u.RoleID=r.RoleID \n"
                + "order by  " + option + " ASC";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("[Role_Name]"));
                Security securityID = new Security(rs.getInt("securityID"), "");
                Staff u = new Staff(rs.getInt("StaffID"),
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

    public Staff getStaffsByID(int id) {
        Staff u = null;
        String sql = "select u.StaffID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "r.Role_Name,s.security_question from [Staff] u \n"
                + "inner join [Role] r on u.RoleID=r.RoleID\n"
                + "inner join SecurityQuestion s on s.securityID=u.securityID where u.StaffID=" + id;
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int StaffID = rs.getInt("StaffID");
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
                u = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public Vector<String> checkEmail() {
        Vector<String> email = new Vector<>();
        String sql = "SELECT email FROM Staff "
                + "UNION ALL "
                + "SELECT email FROM Customer;";
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                email.add(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo ResultSet và Statement được đóng để tránh rò rỉ tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return email;
    }

    public Vector<String> checkEmail1(String emailInput) {
        Vector<String> email = new Vector<>();
        String sql = "DECLARE @excludeEmail VARCHAR(255) = '" + emailInput + "';\n"
                + "\n"
                + "SELECT email FROM Staff WHERE email <> @excludeEmail\n"
                + "UNION ALL \n"
                + "SELECT email FROM Customer WHERE email <> @excludeEmail;";
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                email.add(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo ResultSet và Statement được đóng để tránh rò rỉ tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return email;
    }

    public Vector<String> checkUsername() {
        Vector<String> email = new Vector<>();
        String sql = "SELECT username FROM Staff\n"
                + "UNION ALL\n"
                + "SELECT username FROM Customer;";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                email.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public Vector<String> checkUsername1(String usename) {
        Vector<String> email = new Vector<>();
        String sql = "DECLARE @excludeEmail VARCHAR(255) = '" + usename + "';\n"
                + "\n"
                + "SELECT username FROM Staff WHERE username <> @excludeEmail\n"
                + "UNION ALL \n"
                + "SELECT username FROM Customer WHERE username <> @excludeEmail;";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                email.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public Vector<String> checkPhone() {
        Vector<String> phone = new Vector<>();
        String sql = "SELECT phone FROM Staff\n"
                + "UNION ALL\n"
                + "SELECT phone FROM Customer;";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);

                // String first_name=rs.getString(2);
                phone.add(rs.getString("phone"));
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phone;
    }

    public Vector<String> checkPhone1(String phone1) {
        Vector<String> phone = new Vector<>();
        String sql = "DECLARE @excludeEmail VARCHAR(255) = '" + phone1 + "';\n"
                + "\n"
                + "SELECT phone FROM Staff WHERE phone <> @excludeEmail\n"
                + "UNION ALL \n"
                + "SELECT phone FROM Customer WHERE phone <> @excludeEmail;";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);

                // String first_name=rs.getString(2);
                phone.add(rs.getString("phone"));
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phone;
    }

    public Staff getStaffsByemail(String email1) {
        Staff u = null;
        String sql = "select u.StaffID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "                u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "                r.Role_Name,s.security_question from [Staff] u \n"
                + "                inner join [Role] r on u.RoleID=r.RoleID\n"
                + "                inner join SecurityQuestion s on s.securityID=u.securityID where u.email = '" + email1 + "'";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int StaffID = rs.getInt("StaffID");
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
                u = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public Staff getStaffsByUsername(String username1) {
        Staff u = null;
        String sql = "select u.StaffID,u.first_name, u.last_name,u.phone,u.email,u.address,\n"
                + "                u.username,u.password,u.dob,u.gender,u.status,u.RoleID,u.securityID,u.securityID,u.securityAnswer,u.image,\n"
                + "                r.Role_Name,s.security_question from [Staff] u \n"
                + "                inner join [Role] r on u.RoleID=r.RoleID\n"
                + "                inner join SecurityQuestion s on s.securityID=u.securityID where u.username='" + username1 + "'";
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getdataType(index,fieldName);
                int StaffID = rs.getInt("StaffID");
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
                u = new Staff(StaffID, first_name, last_name,
                        phone, email, address, username, password, dob, gender, status, role, securityID, securityAnswer, image);
                // System.out.println(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
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

    public Staff notStaffbyPhone(String phoneU, String phoneI) {
        Staff u = null;
        String sql = "	SELECT \n"
                + "    u.StaffID, \n"
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
                + "FROM [Staff] u \n"
                + "INNER JOIN [Role] r ON u.RoleID = r.RoleID\n"
                + "INNER JOIN SecurityQuestion s ON s.securityID = u.securityID \n"
                + "WHERE u.phone <> '" + phoneU + "'\n"
                + "  AND u.emphoneail = '" + phoneI + "';";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("Role_Name"));
                Security securityID = new Security(rs.getInt("securityID"), "");
                u = new Staff(rs.getInt("StaffID"),
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

    public Staff notStaffbyEmail(String emailU, String emailI) {
        Staff u = null;
        String sql = "	SELECT \n"
                + "    u.StaffID, \n"
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
                + "FROM [Staff] u \n"
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
                u = new Staff(rs.getInt("StaffID"),
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

    public Staff notStaffbyUsername(String usernameU, String usernameI) {
        Staff u = null;
        String sql = "	SELECT \n"
                + "    u.StaffID, \n"
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
                + "FROM [Staff] u \n"
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
                u = new Staff(rs.getInt("StaffID"),
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
        DAOStaff dao = new DAOStaff();
        System.out.println(dao.getStaff("select u.StaffID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "                u.dob,u.gender,u.status,u.image, u.RoleID,u.securityID,u.securityAnswer,s.security_question from [Staff] u\n"
                + "                 inner join SecurityQuestion s on u.securityID=s.securityID\n"
                + "                inner join [Role] r on r.RoleID = u.RoleID where u.RoleID=1"  ));
    }
}
