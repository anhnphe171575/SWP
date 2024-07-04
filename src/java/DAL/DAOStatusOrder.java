/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Customer;
import Entity.Security;
import Entity.StatusOrder;
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
public class DAOStatusOrder extends DBContext{
    public StatusOrder getStatusID(int id) {
        StatusOrder sl =null;
        String sql = "SELECT * from Status_Order WHERE Status_OrderID ="+id; 
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int status_orderid = rs.getInt("status_orderid");
                String status_name = rs.getString("status_name");
                sl = new StatusOrder(status_orderid, status_name);
            }
        } catch (SQLException ex) {

        }
        return sl;
    }
    public int updateStatus(StatusOrder obj) {
        int n = 0;
        String sql = "UPDATE [Status_Order]\n"
                + "   SET \n"
                + "           [Status_Name]=?\n"
                + " WHERE [Status_OrderID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, obj.getStatus_name());
            pre.setInt(2, obj.getStatus_orderid());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    public Vector<StatusOrder> getStatus(String sql) {
        Vector<StatusOrder> vector = new Vector<StatusOrder>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int status_orderid = rs.getInt("status_orderid");
                String status_name = rs.getString("status_name");
                StatusOrder cus = new StatusOrder(status_orderid, status_name);
                 vector.add(cus); // Added to the vector
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return vector;
    }
    public static void main(String[] args) {
        DAOStatusOrder dao = new DAOStatusOrder();
        System.out.println(dao.getStatusID(2));
    }
}
