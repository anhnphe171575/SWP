/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author Nguyễn Đăng
 */
public class DAORole extends DBContext{
    public Role getRoleID(int id) {
        Role sl =null;
        String sql = "SELECT * from Role WHERE RoleID ="+id; 
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int roleid = rs.getInt("RoleID");
                String rolename = rs.getString("Role_Name");
                sl = new Role(roleid, rolename);
            }
        } catch (SQLException ex) {

        }
        return sl;
    }
    public int updateRole(Role obj) {
        int n = 0;
        String sql = "UPDATE [Role]\n"
                + "   SET \n"
                + "           [Role_Name]=?\n"
                + " WHERE [RoleID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, obj.getRole_Name());
            pre.setInt(2, obj.getRoleID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    public Vector<Role> getRole(String sql) {
        Vector<Role> vector = new Vector<Role>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int roleid = rs.getInt("RoleID");
                String rolename = rs.getString("Role_Name");
                Role cus = new Role(roleid, rolename);
                 vector.add(cus); // Added to the vector
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return vector;
    }
    public static void main(String[] args) {
        DAORole dao = new DAORole();
        System.out.println(dao.getRole("select * from Role"));
    }
}
