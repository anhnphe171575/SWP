/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.InventoryTransaction;
import Entity.Order;
import Entity.Product;
import Entity.Staff;
//import Entity.Staff;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phuan
 */
public class DAOInventoryTransaction extends DBContext {

    private static final Logger logger = Logger.getLogger(DAOInventoryTransaction.class.getName());

    public void AddInventoryTransaction(int productID, int orderID, int quantityChange,
            String transactionType, int StaffID,
            Date transactionDate, String note, int quantityCurrent) {
        try {
            String query = "INSERT INTO InventoryTransaction(\n"
                    + "    productID,\n"
                    + "    orderID,\n"
                    + "    quantity_change,\n"
                    + "    transaction_type,\n"
                    + "    StaffID,\n"
                    + "    transaction_date,\n"
                    + "    note,\n"
                    + "    quantity_current\n"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);\n";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, productID);
            stm.setInt(2, orderID);
            stm.setInt(3, quantityChange);
            stm.setString(4, transactionType);
            stm.setInt(5, StaffID);
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(transactionDate);
            stm.setDate(6, java.sql.Date.valueOf(date1));
            stm.setString(7, note);
            stm.setInt(8, quantityCurrent);
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("aaaa");
        }

    }

    public void AddInventoryTransaction1(int productID, int quantityChange,
            String transactionType, int StaffID,
            Date transactionDate, String note, int quantityCurrent) {
        try {
            String query = "INSERT INTO InventoryTransaction(\n"
                    + "    productID,\n"
                    + "    quantity_change,\n"
                    + "    transaction_type,\n"
                    + "    StaffID,\n"
                    + "    transaction_date,\n"
                    + "    note,\n"
                    + "    quantity_current\n"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?);\n";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, productID);
            stm.setInt(2, quantityChange);
            stm.setString(3, transactionType);
            stm.setInt(4, StaffID);
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(transactionDate);
            stm.setDate(5, java.sql.Date.valueOf(date1));
            stm.setString(6, note);
            stm.setInt(7, quantityCurrent);
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<InventoryTransaction> getAll() {
        List<InventoryTransaction> l = new ArrayList<>();
        try {
            String query = "select it.transactionID,o.orderID,u.last_name,u.first_name,p.product_name, it.quantity_change,it.quantity_current,it.transaction_date,it.transaction_type,it.note from [InventoryTransaction] \n"
                    + "								 it inner join Product p on it.productID = p.productID\n"
                    + "									inner join Staff u on it.StaffID = u.StaffID\n"
                    + "									left join [Order] o on it.orderID = o.orderID";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getInt("orderID"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                Product p = new Product(0,
                        rs.getString("product_name"),
                        0,
                        0,
                        null,
                        0,
                        null,
                        null,
                        0,
                        0,
                        null,
                        null,
                        null,
                        Boolean.TRUE,
                        0);
                Staff u = new Staff(0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        true,
                        0,
                        null,
                        null,
                        null,
                        null);
                InventoryTransaction it = new InventoryTransaction(rs.getInt("transactionID"),
                        p,
                        o,
                        rs.getInt("quantity_change"),
                        rs.getString("transaction_type"),
                        u,
                        rs.getDate("transaction_date"),
                        rs.getString("note"),
                        rs.getInt("quantity_current"));
                l.add(it);
            }
        } catch (Exception e) {
            e.printStackTrace(); // It's good practice to at least print the stack trace in a catch block
        }
        return l;
    }

    public static void main(String[] args) {
        DAOInventoryTransaction db1 = new DAOInventoryTransaction();
//        LocalDate localDate = LocalDate.now();
//        Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        db1.AddInventoryTransaction(1, 1, 1, "return", 3, date_create_by, "note", 0);
//    }
        System.out.println(db1.getAll());
    }
}
