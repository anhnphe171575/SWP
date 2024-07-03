/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Customer;
import Entity.Receiver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuan
 */
public class DAOReceiver extends DBContext {

    public List<Receiver> getReceiver(int cusid) {
        List<Receiver> list = new ArrayList<>();
        try {
            String sql = "select c.customerID,c.dob,c.activity_history, c.address, c.email, c.first_name, c.gender, c.last_name, c.password\n"
                    + "                    ,c.phone, c.securityAnswer, c.username,re.ReceiverID,re.ReceiverFullName,re.ReceiverMobile,re.ReceiverAddress\n"
                    + "					from Customer c inner join Receiver_Information re\n"
                    + "					on c.customerID = re.customerID where c.customerID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cusid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                        null,
                        rs.getString("securityAnswer"),
                        null);

                Receiver r = new Receiver(rs.getInt("ReceiverID"),
                        rs.getString("ReceiverFullName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"), c);
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Receiver getReceiverByID(int cusid, int ReceiverID) {
        Receiver r = null;
        try {
            String sql = "select c.customerID,c.dob,c.activity_history, c.address, c.email, c.first_name, c.gender, c.last_name, c.password\n"
                    + "                    ,c.phone, c.securityAnswer, c.username,re.ReceiverID,re.ReceiverFullName,re.ReceiverMobile,re.ReceiverAddress\n"
                    + "					from Customer c inner join Receiver_Information re\n"
                    + "					on c.customerID = re.customerID where c.customerID = ? and re.ReceiverID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cusid);
            stm.setInt(2, ReceiverID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                        null,
                        rs.getString("securityAnswer"),
                        null);
                r = new Receiver(rs.getInt("ReceiverID"),
                        rs.getString("ReceiverFullName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"), c);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public void addReceive(Receiver obj) {
        try {
            String sql
                    = "\n"
                    + "INSERT INTO [dbo].[Receiver_Information]\n"
                    + "           ([ReceiverFullName]\n"
                    + "           ,[ReceiverMobile]\n"
                    + "           ,[ReceiverAddress]\n"
                    + "           ,[customerID])\n"
                    + "       VALUES(?,?,?,?)";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getReceiverFullName());
            pre.setString(2, obj.getReceiverMobile());
            pre.setString(3, obj.getReceiverAddress());
            pre.setInt(4, obj.getCustomer().getCustomerID());
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteRecei(int id) {
        try {
            String sql
                    = "DELETE FROM [dbo].[Receiver_Information]\n"
                    + "      WHERE ReceiverID =" + id;

            PreparedStatement pre = conn.prepareStatement(sql);;
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editRecei(Receiver obj) {
        try {
            String sql
                    = "UPDATE [dbo].[Receiver_Information]\n"
                    + "   SET [ReceiverFullName] = ?\n"
                    + "      ,[ReceiverMobile] = ?\n"
                    + "      ,[ReceiverAddress] = ?"
                    + " WHERE ReceiverID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getReceiverFullName());
            pre.setString(2, obj.getReceiverMobile());
            pre.setString(3, obj.getReceiverAddress());
            pre.setInt(4, obj.getReceiverID());

            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOReceiver db = new DAOReceiver();
        Customer c = new Customer(1, null, "", "", "", "", "", "", null, true, null, null, "", "");
        db.editRecei(new Receiver(24, "phu thuan", "112", "hn", c));
        List<Receiver> l = db.getReceiver(1);
        for (Receiver receiver : l) {
            System.out.println(receiver);
        }
    }
}
