/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.Date;
import Entity.CategoryProduct;
import Entity.Receiver;
import Entity.Customer;
import Entity.Order;
import Entity.OrderItems;
import Entity.Product;
import Entity.Role;
import Entity.StatusOrder;
import Entity.User;
import java.sql.Statement;
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;

public class DAOOrder extends DBContext {

    public void updateSaleName(int SaleID, int OrderID) {
        try {
            String query = "UPDATE [Order] SET [UserID] = ? WHERE orderID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, SaleID);
            stm.setInt(2, OrderID);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSalebIDbyName(String name) {
        int userID = -1;
        try {
            // SQL query to retrieve UserID based on concatenated first_name and last_name
            String query = "SELECT [UserID] FROM [User] WHERE CONCAT(first_name,' ',last_name) = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (Exception e) {
        }
        return userID;
    }

    public ArrayList<OrderItems> getOrderbyUserID(int UserID) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = " SELECT  \n"
                    + "    o.orderID, \n"
                    + "    o.order_date, \n"
                    + "    c.first_name AS customer_first_name, \n"
                    + "    c.last_name AS customer_last_name,\n"
                    + "    STRING_AGG(p.product_name, ', ') AS product_names, \n"
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost, \n"
                    + "    st.Status_OrderID, \n"
                    + "    st.Status_Name, \n"
                    + "    u.first_name, \n"
                    + "    u.last_name\n"
                    + "FROM \n"
                    + "    [Order] o \n"
                    + "INNER JOIN \n"
                    + "    Order_items ot ON o.orderID = ot.orderID \n"
                    + "INNER JOIN \n"
                    + "    Customer c ON o.customerID = c.customerID \n"
                    + "INNER JOIN \n"
                    + "    Status_Order st ON o.Status_OrderID = st.Status_OrderID \n"
                    + "INNER JOIN \n"
                    + "    [User] u ON u.UserID = o.UserID\n"
                    + "INNER JOIN \n"
                    + "    Product p ON ot.productID = p.productID \n"
                    + "WHERE \n"
                    + "    o.UserID = ?\n"
                    + "GROUP BY \n"
                    + "    o.orderID, \n"
                    + "    o.order_date, \n"
                    + "    c.first_name, \n"
                    + "    c.last_name, \n"
                    + "    st.Status_OrderID, \n"
                    + "    st.Status_Name, \n"
                    + "    u.first_name, \n"
                    + "    u.last_name\n"
                    + "ORDER BY \n"
                    + "    o.order_date DESC;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, UserID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );
                User u = new User(0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        "",
                        "",
                        "",
                        "",
                        "",
                        null,
                        true,
                        0,
                        null,
                        null,
                        "",
                        "");
                Customer c = new Customer(
                        0, // Assuming customerID is not needed for this object creation
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name"),
                        null, null, null, null, null, null, false, null, null, null, null
                );

                Product p = new Product(
                        0, // Assuming productID is not needed for this object creation
                        rs.getString("product_names"),
                        0, 0, null, 0, null, null, 0, 0, null, null, null, null, 0
                );

                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null, // Assuming order type or related information is not needed
                        rs.getDate("order_date"),
                        u, null, null, null
                );

                OrderItems ot = new OrderItems(
                        0, // Assuming orderItemID is not needed for this object creation
                        o,
                        p,
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is not needed for this object creation
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getPackingOrder() {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "    o.orderID,\n"
                    + "    o.order_date,\n"
                    + "    c.first_name AS customer_first_name,\n"
                    + "    c.last_name AS customer_last_name,\n"
                    + "	u.first_name,\n"
                    + "	u.last_name,\n"
                    + "    STRING_AGG(p.product_name, ', ') AS product_names,\n"
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost,\n"
                    + "    st.Status_OrderID,\n"
                    + "    st.Status_Name\n"
                    + "FROM\n"
                    + "    [Order] o\n"
                    + "    INNER JOIN Order_items ot ON o.orderID = ot.orderID\n"
                    + "    INNER JOIN Customer c ON o.customerID = c.customerID\n"
                    + "    INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID\n"
                    + "    INNER JOIN [User] u ON u.UserID = o.UserID\n"
                    + "    INNER JOIN Product p ON ot.productID = p.productID\n"
                    + "GROUP BY\n"
                    + "    o.orderID,\n"
                    + "    o.order_date,\n"
                    + "    c.first_name,\n"
                    + "    c.last_name,\n"
                    + "    st.Status_OrderID,\n"
                    + "    st.Status_Name,\n"
                    + "	u.first_name,\n"
                    + "	u.last_name\n"
                    + "ORDER BY o.order_date DESC;";

            // Prepare the statement
            PreparedStatement stm = conn.prepareStatement(query);
            // Execute the query
            ResultSet rs = stm.executeQuery();

            // Process the result set
            while (rs.next()) {
                StatusOrder statusOrder = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                Customer customer = new Customer(
                        0, // Assuming customerID is not needed for this object creation
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name"),
                        null, null, null, null, null, null, false, null, null, null, null
                );
                User user = new User(0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        "",
                        "",
                        "",
                        "",
                        "",
                        null,
                        false,
                        0,
                        null,
                        null,
                        "",
                        "");
                Product product = new Product(
                        0, // Assuming productID is not needed for this object creation
                        rs.getString("product_names"),
                        0, 0, null, 0, null, null, 0, 0, null, null, null, null, 0
                );

                Order order = new Order(
                        rs.getInt("orderID"),
                        statusOrder,
                        customer,
                        null, // Assuming order type or related information is not needed
                        rs.getDate("order_date"),
                        user, null, null, null
                );

                OrderItems orderItem = new OrderItems(
                        0, // Assuming orderItemID is not needed for this object creation
                        order,
                        product,
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is not needed for this object creation
                );

                list.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public OrderItems getOrderDetails1(int orderid) {
        OrderItems ot = null;
        try {
            String query = "SELECT "
                    + "    o.orderID, "
                    + "    CONCAT(c.first_name, ' ', c.last_name) AS customer_name, "
                    + "    c.email, "
                    + "    c.phone, "
                    + "    o.order_date, "
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "    CONCAT(u.first_name, ' ', u.last_name) AS sales_name, "
                    + "    st.status_name AS status, "
                    + "    o.PaymentMethod, "
                    + "    st.Status_OrderID "
                    + "FROM [Order] o "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN [User] u ON o.UserID = u.UserID "
                    + "INNER JOIN [Role] r ON r.RoleID = u.RoleID "
                    + "INNER JOIN Status_Order st ON st.Status_OrderID = o.Status_OrderID "
                    + "WHERE o.orderID = ? "
                    + "GROUP BY "
                    + "    o.orderID, "
                    + "    CONCAT(c.first_name, ' ', c.last_name), "
                    + "    c.email, "
                    + "    c.phone, "
                    + "    o.order_date, "
                    + "    CONCAT(u.first_name, ' ', u.last_name), "
                    + "    st.status_name, "
                    + "    o.PaymentMethod, "
                    + "    st.Status_OrderID";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder statusOrder = new StatusOrder(rs.getInt("Status_OrderID"),
                        rs.getString("status")
                );
                Customer customer = new Customer(0,
                        rs.getString("customer_name"),
                        null,
                        rs.getString("phone"),
                        rs.getString("email"),
                        null, // Add other necessary fields for Customer
                        null,
                        null,
                        null,
                        false,
                        null,
                        null,
                        null,
                        null);

                User user = new User(0,
                        rs.getString("sales_name"),
                        null,
                        null, // Add other necessary fields for User
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        0, // Update this to correct status field if needed
                        null,
                        null,
                        null,
                        null);

                Order order = new Order(rs.getInt("orderID"),
                        statusOrder,
                        customer,
                        null,
                        rs.getDate("order_date"),
                        user,
                        null,
                        null,
                        null);
                ot = new OrderItems(0,
                        order,
                        null,
                        rs.getFloat("total_cost"),
                        0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ot;
    }

    public void RestoreOrderQuantity(int orderID) {
        try {
            String query = "UPDATE Product \n"
                    + "SET quantity = quantity + \n"
                    + "(SELECT SUM(oi.quantity) \n"
                    + " FROM Order_items oi \n"
                    + " WHERE Product.productID = oi.productID \n"
                    + "   AND oi.orderID = ?)\n"
                    + "WHERE EXISTS (\n"
                    + "    SELECT 1 \n"
                    + "    FROM Order_items oi \n"
                    + "    WHERE Product.productID = oi.productID \n"
                    + "      AND oi.orderID = ?);";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderID);
            stm.setInt(2, orderID);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<StatusOrder> getStatusOrder1() {
        List<StatusOrder> status = new ArrayList<>();
        try {
            String query = "select * from Status_Order";

            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                status.add(st);
            }
        } catch (Exception e) {
        }
        return status;
    }

    public ArrayList<OrderItems> getOrderByIdByStatus(String orderId, String status, String cusid) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, \n"
                    + "                     STRING_AGG(p.product_name, ', ') AS Product_name, \n"
                    + "                     SUM(ot.list_price * ot.quantity) AS total_cost, \n"
                    + "                     st.Status_OrderID, st.Status_Name, o.PaymentMethod, c.customerID\n"
                    + "                     FROM [Order] o \n"
                    + "                     INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "                     INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "                     INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID \n"
                    + "                     INNER JOIN Product p ON ot.productID = p.productID \n"
                    + "                     WHERE c.customerID='" + cusid + "' and o.orderID = '" + orderId + "' and o.Status_OrderID='" + status + "' \n"
                    + "                     GROUP BY o.orderID, o.order_date, st.Status_OrderID, st.Status_Name, o.PaymentMethod, c.customerID";

            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create Customer object
                Customer c = new Customer(
                        rs.getInt("customerID"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"),
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
                        null, 0
                );

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null, null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getOrderByProductByStatus(String status, String fullName, String cusid) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, \n"
                    + "                     STRING_AGG(p.product_name, ', ') AS Product_name, \n"
                    + "                     SUM(ot.list_price * ot.quantity) AS total_cost, \n"
                    + "                     st.Status_OrderID, st.Status_Name, o.PaymentMethod \n"
                    + "                     FROM [Order] o \n"
                    + "                     INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "                     INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "                     INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID \n"
                    + "                     INNER JOIN Product p ON ot.productID = p.productID \n"
                    + "                     WHERE o.customerID='" + cusid + "'and o.Status_OrderID='" + status + "' and p.product_name LIKE '" + fullName + "' \n"
                    + "                     GROUP BY o.orderID, o.order_date, st.Status_OrderID, st.Status_Name, o.PaymentMethod";

            PreparedStatement stm = conn.prepareStatement(query);
            //String searchPattern = "%" + fullName + "%";
            //stm.setString(1, searchPattern);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create Customer object
                Customer c = new Customer(
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"),
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
                        null, 0
                );

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null,
                        rs.getString("PaymentMethod")
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getOrderInforByCustomerByStatus(String customerid, String status) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, STRING_AGG(p.product_name, ', ') AS Product_name, STRING_AGG(p.productID, ',') AS Product_id, SUM(ot.list_price * ot.quantity) AS total_cost,\n"
                    + "                                                                   st.Status_OrderID ,st.Status_Name, o.PaymentMethod, c.customerID\n"
                    + "                                                                  FROM \n"
                    + "                                                                       [Order] o\n"
                    + "                                                                   INNER JOIN \n"
                    + "                                                                      Order_items ot ON o.orderID = ot.orderID\n"
                    + "                                                                   INNER JOIN \n"
                    + "                                                                    Customer c ON o.customerID = c.customerID\n"
                    + "                                                                   INNER JOIN \n"
                    + "                                                                      Status_Order st ON o.Status_OrderID = st.Status_OrderID\n"
                    + "                                                                   INNER JOIN \n"
                    + "                                                                     Product p ON ot.productID = p.productID\n"
                    + "                          where o.Status_OrderID='" + status + "' and c.customerID ='" + customerid + "'\n"
                    + "                                                           GROUP BY \n"
                    + "                                                               o.orderID, o.order_date ,st.Status_OrderID,st.Status_Name, o.PaymentMethod, c.customerID ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("status_orderid"),
                        rs.getString("status_name")
                );

                // Create Customer object
                Customer c = new Customer(
                        rs.getInt("customerID"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );
                Product p = new Product(0,
                        rs.getString("Product_name"),
                        0,
                        0,
                        rs.getString("Product_id"),
                        0,
                        null,
                        null,
                        0,
                        0,
                        null,
                        null,
                        null,
                        null, 0);
                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null,
                        rs.getString("PaymentMethod")
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getReceiverInfor(int orderid) {
        List<Order> od = new ArrayList<>();
        try {
            String query = "select ri.ReceiverFullName, ri.ReceiverAddress, ri.ReceiverMobile from [Order] o inner join Receiver_Information ri on ri.ReceiverID = o.ReceiverID where o.orderID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Receiver r = new Receiver(0,
                        rs.getString("ReceiverFullName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        null);
                Order o = new Order(0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        r,
                        null,
                        null);
                od.add(o);
            }
        } catch (Exception e) {

        }
        return od;
    }

    public void UpdateStatus(int orderID, int statusid, LocalDate sDate) {
        String sql = "UPDATE [Order]\n"
                + "   SET \n"
                + "           [Status_OrderID]=?\n"
                + "           ,[shipped_date]=?\n"
                + " WHERE [orderID] = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, statusid);
            pre.setDate(2, java.sql.Date.valueOf(sDate));
            pre.setInt(3, orderID);

            pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<OrderItems> getOrderInforByCustomer(String customerid) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, c.first_name, c.last_name,STRING_AGG(p.productID, ',') AS Product_id,STRING_AGG(p.quantity, ',') AS Product_quantity, STRING_AGG(p.product_name, ', ') AS Product_name, SUM(ot.list_price * ot.quantity) AS total_cost,\n"
                    + "                                        st.Status_OrderID ,st.Status_Name, o.PaymentMethod \n"
                    + "                                       FROM \n"
                    + "                                            [Order] o\n"
                    + "                                        INNER JOIN \n"
                    + "                                           Order_items ot ON o.orderID = ot.orderID\n"
                    + "                                        INNER JOIN \n"
                    + "                                         Customer c ON o.customerID = c.customerID\n"
                    + "                                        INNER JOIN \n"
                    + "                                           Status_Order st ON o.Status_OrderID = st.Status_OrderID\n"
                    + "                                        INNER JOIN \n"
                    + "                                          Product p ON ot.productID = p.productID\n"
                    + "	                                       	where o.customerID='" + customerid + "'\n"
                    + "                                        GROUP BY \n"
                    + "                                           o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID,st.Status_Name, o.PaymentMethod ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("status_orderid"),
                        rs.getString("status_name")
                );

                // Create Customer object
                Customer c = new Customer(
                        0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );
                Product p = new Product(0,
                        rs.getString("Product_name"),
                        0,
                        0,
                        rs.getString("Product_id"),
                        0,
                        null,
                        rs.getString("Product_quantity"),
                        0,
                        0,
                        null,
                        null,
                        null,
                        null, 0);
                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null,
                        rs.getString("PaymentMethod")
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getOrderByProduct(String fullName) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, "
                    + "STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "st.Status_OrderID, st.Status_Name, o.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID "
                    + "INNER JOIN Product p ON ot.productID = p.productID "
                    + "WHERE p.product_name LIKE '" + fullName + "' "
                    + "GROUP BY o.orderID, o.order_date, st.Status_OrderID, st.Status_Name, o.PaymentMethod";

            PreparedStatement stm = conn.prepareStatement(query);
            //String searchPattern = "%" + fullName + "%";
            //stm.setString(1, searchPattern);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create Customer object
                Customer c = new Customer(
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"),
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
                        null, 0
                );

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null,
                        rs.getString("PaymentMethod")
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedHashMap<String, Double> trend7dayTotalOrderBySale(int saleid, String startdate, String enddate) {
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @startDate DATE;\n"
                + "DECLARE @endDate DATE;\n"
                + "DECLARE @saleID INT;\n"
                + "\n"
                + "SET @startDate = '" + startdate + "'; \n"
                + "SET @endDate = '" + enddate + "'; \n"
                + "SET @saleID = '" + saleid + "';\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT @startDate AS order_date\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, order_date)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, order_date) <= @endDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.order_date AS order_date,\n"
                + "    SUM(oi.list_price * oi.quantity) AS Total_Revenue\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [Order] o ON dr.order_date = o.order_date\n"
                + "LEFT JOIN \n"
                + "    Order_items oi ON o.orderID = oi.orderID\n"
                + "WHERE\n"
                + "    o.UserID = @saleID\n"
                + "GROUP BY \n"
                + "    dr.order_date\n"
                + "ORDER BY \n"
                + "    dr.order_date DESC\n"
                + "OPTION (MAXRECURSION 0);";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("order_date"), rs.getDouble("Total_Revenue"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public int TotalOrder() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS Total_Orders\n"
                + "FROM \n"
                + "    [Order];";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("Total_Orders");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int TotalOrder1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT\n"
                + "                    COUNT(*) AS Total_Orders\n"
                + "                FROM \n"
                + "                    [Order] o where o.UserID = '" + saleid + "' AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("Total_Orders");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalSuccess() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS Total_RevenueSuccess\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '5'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("Total_RevenueSuccess");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalSuccess1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS Total_RevenueSuccess\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '5'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("Total_RevenueSuccess");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalSubmit() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalSubmit\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '1'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalSubmit");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalSubmit1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalSubmit\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '1'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalSubmit");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalReject() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalReject\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '2'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalReject");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalReject1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalReject\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '2'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalReject");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalPacking() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalPacking\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '3'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalPacking");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalPacking1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalPacking\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '3'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalPacking");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalDelivering() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalDelivering\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '4'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalDelivering");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalDelivering1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalDelivering\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '4'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalDelivering");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalFail() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalFail\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '6'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalFail");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalFail1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalFail\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '6'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalFail");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalDone() {
        int n = 0;
        String sql = "SELECT \n"
                + "    COUNT(*) AS TotalDone\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "WHERE\n"
                + "    o.Status_OrderID = '7'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalDone");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public int trend7dayTotalDone1(int saleid, String startdate, String enddate) {
        int n = 0;
        String sql = "SELECT COUNT(*) AS TotalDone\n"
                + "FROM [Order] o\n"
                + "WHERE o.Status_OrderID = '7'\n"
                + "AND o.UserID = '" + saleid + "'\n"
                + "AND o.order_date BETWEEN '" + startdate + "' AND '" + enddate + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("TotalDone");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public LinkedHashMap<String, Double> trend7dayTotalOrder(String date) {
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @selectedDate DATE;\n"
                + "\n"
                + "SET @selectedDate = '" + date + "'; \n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, @selectedDate) AS order_date\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, order_date)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, order_date) < @selectedDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.order_date AS order_date,\n"
                + "    SUM(oi.list_price * oi.quantity) AS Total_Revenue\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [Order] o ON dr.order_date = o.order_date\n"
                + "LEFT JOIN \n"
                + "    Order_items oi ON o.orderID = oi.orderID\n"
                + "WHERE\n"
                + "    dr.order_date BETWEEN DATEADD(DAY, -7, @selectedDate) AND DATEADD(DAY, -1, @selectedDate)\n"
                + "GROUP BY \n"
                + "    dr.order_date\n"
                + "ORDER BY \n"
                + "    dr.order_date DESC\n"
                + "OPTION (MAXRECURSION 0);";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("order_date"), rs.getDouble("Total_Revenue"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public ArrayList<OrderItems> getOrder() {
        ArrayList<OrderItems> orderitems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, o.Status_OrderID, o.shipped_date, \n"
                    + "                        c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, \n"
                    + "                        c.username, c.password, c.dob, c.gender, c.activity_history, \n"
                    + "                        u.UserID, u.first_name, u.last_name, \n"
                    + "                        u.phone, u.email, u.address, \n"
                    + "                        u.username AS user_username, u.password, u.dob, \n"
                    + "                        u.gender, u.status,ri.ReceiverID, ri.ReceiverFullName, ri.ReceiverMobile, ri.ReceiverAddress, \n"
                    + "                        p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
                    + "                        p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price, \n"
                    + "                        p.brand, p.update_date, p.status AS product_status, st.Status_Name, u.RoleID, \n"
                    + "                        ot.orderitemID, ot.list_price, ot.quantity \n"
                    + "                        FROM [Order] o \n"
                    + "                        INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "                        INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "                        INNER JOIN Status_Order so ON o.Status_OrderID = so.Status_OrderID \n"
                    + "                        INNER JOIN [User] u ON o.UserID = u.UserID \n"
                    + "                        INNER JOIN Receiver_Information ri ON ri.ReceiverID = o.ReceiverID \n"
                    + "                        INNER JOIN Product p ON ot.productID = p.productID"
                    + "                        INNER JOIN Status_Order st on st.Status_OrderID = o.Status_OrderID;";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt("RoleID"),
                        null);
                StatusOrder st = new StatusOrder(rs.getInt("status_orderid"),
                        rs.getString("status_name")
                );
                User u = new User(
                        rs.getInt("UserID"),
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
                        r,
                        null,
                        null,
                        null);

                Product p = new Product(
                        rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        null,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("product_status"), 0);

                Customer c = new Customer(
                        rs.getInt("customerID"),
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
                        null,
                        null);
                Receiver ri = new Receiver(rs.getInt("ReceiverID"),
                        rs.getString("ReceiverFullName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        c);
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        rs.getDate("shipped_date"),
                        rs.getDate("order_date"),
                        u,
                        ri, null, null);

                OrderItems ot = new OrderItems(
                        rs.getInt("orderitemID"),
                        o,
                        p,
                        rs.getFloat("list_price"),
                        rs.getInt("quantity"));

                orderitems.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderitems;
    }

    public ArrayList<OrderItems> getOrderByOrderID(int orderID) {
        ArrayList<OrderItems> orderitems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, o.Status_OrderID, o.shipped_date, \n"
                    + "                        c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, \n"
                    + "                        c.username, c.password, c.dob, c.gender, c.activity_history, \n"
                    + "                        u.UserID, u.first_name, u.last_name, \n"
                    + "                        u.phone, u.email, u.address, \n"
                    + "                        u.username AS user_username, u.password, u.dob, \n"
                    + "                        u.gender, u.status, ri.ReceiverID,ri.ReceiverFullName, ri.ReceiverMobile, ri.ReceiverAddress, \n"
                    + "                        p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
                    + "                        p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price, \n"
                    + "                        p.brand, p.update_date, p.status AS product_status, st.Status_Name, u.RoleID, \n"
                    + "                        ot.orderitemID, ot.list_price, ot.quantity as QuantityO\n"
                    + "                        FROM [Order] o \n"
                    + "                        INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "                        INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "                        INNER JOIN Status_Order so ON o.Status_OrderID = so.Status_OrderID \n"
                    + "                        INNER JOIN [User] u ON o.UserID = u.UserID \n"
                    + "                        INNER JOIN Receiver_Information ri ON ri.ReceiverID = o.ReceiverID \n"
                    + "                        INNER JOIN Product p ON ot.productID = p.productID"
                    + "                        INNER JOIN Status_Order st on st.Status_OrderID = o.Status_OrderID where o.orderID =?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt("RoleID"),
                        null);
                StatusOrder st = new StatusOrder(rs.getInt("status_orderid"),
                        rs.getString("status_name")
                );
                User u = new User(
                        rs.getInt("UserID"),
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
                        r,
                        null,
                        null,
                        null);

                Product p = new Product(
                        rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        null,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("product_status"), 0);

                Customer c = new Customer(
                        rs.getInt("customerID"),
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
                        null,
                        null);
                Receiver ri = new Receiver(rs.getInt("ReceiverID"),
                        rs.getString("ReceiverFullName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        c);
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        rs.getDate("shipped_date"),
                        rs.getDate("order_date"),
                        u,
                        ri, null, null);

                OrderItems ot = new OrderItems(
                        rs.getInt("orderitemID"),
                        o,
                        p,
                        rs.getFloat("list_price"),
                        rs.getInt("QuantityO"));

                orderitems.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderitems;
    }

    public ArrayList<Integer> getOrder1() {
        ArrayList<Integer> oid = new ArrayList<>();
        try {
            String query = "select * from [Order]";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                oid.add(rs.getInt("orderID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oid;
    }

    public ArrayList<OrderItems> getOrderInfor() {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  \n"
                    + "    o.orderID, \n"
                    + "    o.order_date, \n"
                    + "    c.first_name AS customer_first_name, \n"
                    + "    c.last_name AS customer_last_name, \n"
                    + "    STRING_AGG(p.product_name, ', ') AS Product_name, \n"
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost,\n"
                    + "    st.Status_OrderID,\n"
                    + "    st.Status_Name,\n"
                    + "    u.first_name AS user_first_name,\n"
                    + "    u.last_name AS user_last_name\n"
                    + "FROM \n"
                    + "    [Order] o\n"
                    + "INNER JOIN \n"
                    + "    Order_items ot ON o.orderID = ot.orderID\n"
                    + "INNER JOIN \n"
                    + "    Customer c ON o.customerID = c.customerID\n"
                    + "INNER JOIN \n"
                    + "    Status_Order st ON o.Status_OrderID = st.Status_OrderID\n"
                    + "INNER JOIN \n"
                    + "    Product p ON ot.productID = p.productID\n"
                    + "INNER JOIN \n"
                    + "    [User] u ON u.UserID = o.UserID\n"
                    + "GROUP BY \n"
                    + "    o.orderID, \n"
                    + "    o.order_date, \n"
                    + "    c.first_name, \n"
                    + "    c.last_name, \n"
                    + "    st.Status_OrderID,\n"
                    + "    st.Status_Name,\n"
                    + "    u.first_name,\n"
                    + "    u.last_name\n"
                    + "ORDER BY \n"
                    + "    o.order_date DESC";

            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create User object
                User u = new User(0,
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        "",
                        "",
                        "",
                        "",
                        "",
                        null,
                        false,
                        0,
                        null,
                        null,
                        null,
                        null);

                // Create Customer object
                Customer c = new Customer(
                        0,
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name"),
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(0,
                        rs.getString("Product_name"),
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
                        null, 0);

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        u,
                        null,
                        null, null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNumberOfProducts(int orderID) {
        int totalQuantity = 0;
        try {
            String query = "SELECT Sum(quantity) AS Total FROM Order_items WHERE orderID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalQuantity = rs.getInt("Total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public Map<Integer, Integer> getOrderQuantities(ArrayList<OrderItems> orderItems) {
        Map<Integer, Integer> orderQuantities = new HashMap<>();
        for (OrderItems item : orderItems) {
            int orderID = item.getOrder().getOrderID();
            if (!orderQuantities.containsKey(orderID)) {
                int totalQuantity = getNumberOfProducts(orderID);
                orderQuantities.put(orderID, totalQuantity);
            }
        }
        return orderQuantities;
    }

    public ArrayList<OrderItems> getOrderById(String orderId) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, c.first_name, c.last_name, "
                    + "STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "st.Status_OrderID, st.Status_Name "
                    + "FROM [Order] o "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID "
                    + "INNER JOIN Product p ON ot.productID = p.productID "
                    + "WHERE o.orderID = ? "
                    + "GROUP BY o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID, st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, orderId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create Customer object
                Customer c = new Customer(
                        0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"),
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
                        null, 0
                );

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null, null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getOrderByFullName(String fullName) {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, c.first_name, c.last_name, "
                    + "STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "st.Status_OrderID, st.Status_Name "
                    + "FROM [Order] o "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID "
                    + "INNER JOIN Product p ON ot.productID = p.productID "
                    + "WHERE CONCAT(c.first_name, ' ', c.last_name) LIKE ? "
                    + "GROUP BY o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID, st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            String searchPattern = "%" + fullName + "%";
            stm.setString(1, searchPattern);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                // Create Customer object
                Customer c = new Customer(
                        0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null, // Assuming customer username is not used here
                        null, // Assuming customer password is not used here
                        null, // Assuming customer dob is not used here
                        false, // Assuming customer gender is not used here
                        null, // Assuming customer activity history is not used here
                        null,
                        null,
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"),
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
                        null, 0
                );

                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null, null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p, // Assuming product is handled differently
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is handled differently
                );

                list.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderItems> getOrder(Map<String, String> aa1, String all) {
        ArrayList<OrderItems> orderItems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, c.first_name, c.last_name, \n"
                    + "       STRING_AGG(p.product_name, ', ') AS Product_name, \n"
                    + "       SUM(ot.list_price * ot.quantity) AS total_cost, \n"
                    + "       st.Status_OrderID, st.Status_Name \n"
                    + "FROM [Order] o \n"
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID \n"
                    + "INNER JOIN Product p ON ot.productID = p.productID \n"
                    + "INNER JOIN [User] u ON u.UserID = o.UserID \n"
                    + all
                    + "  GROUP BY o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID, st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            int i = 1;
            for (Map.Entry<String, String> item : aa1.entrySet()) {
                stm.setString(i, item.getValue());
                i++;
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name")
                );

                Customer c = new Customer(
                        0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        null,
                        null,
                        null,
                        null
                );

                Product p = new Product(
                        0,
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
                        null, 0
                );

                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null, null
                );

                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p,
                        rs.getFloat("total_cost"),
                        0
                );

                orderItems.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception properly
        }
        return orderItems;
    }

    public List<String> getStatusOrder() {
        List<String> status = new ArrayList<>();
        try {
            String query = "  select Status_Name from Status_Order";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Status_Name");
                status.add(name);
            }
        } catch (Exception e) {
        }
        return status;
    }

    public List<StatusOrder> getStatusOrder2() {
        List<StatusOrder> status = new ArrayList<>();
        try {
            String query = "select * from Status_Order ";
            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Status_OrderID");
                String name = rs.getString("Status_Name");
                StatusOrder so = new StatusOrder(id, name);
                status.add(so);
            }
        } catch (Exception e) {
        }
        return status;
    }

    public List<String> getSaleName() {
        List<String> names = new ArrayList<>();
        String query = "SELECT u.first_name, u.last_name FROM [User] u WHERE u.RoleID = 2;";
        try (PreparedStatement stm = conn.prepareStatement(query); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String fullName = firstName + " " + lastName;
                names.add(fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    public List<OrderItems> getOrderDetails(int orderid) {
        List<OrderItems> orderList = new ArrayList<>();
        try {
            String query = "SELECT "
                    + "    o.orderID, "
                    + "    c.first_name, c.last_name, "
                    + "    c.email, "
                    + "    c.phone, "
                    + "    o.order_date, "
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "    u.first_name, u.last_name, "
                    + "    st.status_name AS status, o.PaymentMethod, st.Status_OrderID "
                    + "FROM [Order] o "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN [User] u ON o.UserID = u.UserID "
                    + "INNER JOIN Status_Order st ON st.Status_OrderID = o.Status_OrderID "
                    + "WHERE o.orderID = ? "
                    + "GROUP BY o.orderID, c.first_name, c.last_name, c.email, c.phone, o.order_date, u.first_name, u.last_name, st.status_name, o.PaymentMethod,st.Status_OrderID";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder statusOrder = new StatusOrder(rs.getInt("Status_OrderID"),
                        rs.getString("status")
                );
                Customer customer = new Customer(0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        null, // Add other necessary fields for Customer
                        null,
                        null,
                        null,
                        false,
                        null,
                        null,
                        null,
                        null);

                User user = new User(0,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null, // Add other necessary fields for User
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        0, // Update this to correct status field if needed
                        null,
                        null,
                        null,
                        null);

                Order order = new Order(rs.getInt("orderID"),
                        statusOrder,
                        customer,
                        null,
                        rs.getDate("order_date"),
                        user,
                        null,
                        null,
                        rs.getString("PaymentMethod"));
                OrderItems ot = new OrderItems(0,
                        order,
                        null,
                        rs.getFloat("total_cost"),
                        0);
                orderList.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public HashMap<Integer, ArrayList<String>> CountorderSale() {
        HashMap<Integer, ArrayList<String>> count = new HashMap<>();
        try {
            String query = "select Count(o.orderID) as count , u.first_name, u.last_name, u.UserID\n"
                    + "from [Order] o right join [User] u on o.UserID = u.UserID \n"
                    + "where u.RoleID =2\n"
                    + "group by u.UserID, u.first_name, u.last_name ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String fullName = firstName + " " + lastName;
                ArrayList<String> a = new ArrayList();
                a.add(rs.getString("count"));
                a.add(fullName);
                count.put(rs.getInt("UserID"), a);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return count;
    }

    public HashMap<Integer, Integer> Countorder() {
        HashMap<Integer, Integer> count = new HashMap<>();
        try {
            String query = "select Count(o.orderID) as count , u.UserID \n"
                    + "from [Order] o right join [User] u on o.UserID = u.UserID \n"
                    + "where u.RoleID =2\n"
                    + "group by u.UserID,u.first_name, u.last_name ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                count.put(rs.getInt("UserID"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return count;
    }

    public int getSalebIDyName(String name) {
        int userID = -1; // Initialize with a default value or handle null case
        try {
            // SQL query to retrieve UserID based on concatenated first_name and last_name
            String query = "SELECT [UserID] FROM [User] WHERE CONCAT(first_name, ' ', last_name) = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);

            // Execute query and retrieve results
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (Exception e) {
        }
        return userID;
    }

    public void updateStatusOrder(int Status_OrderID, int OrderID) {
        try {
            String query = "UPDATE [Order] SET Status_OrderID = ? WHERE orderID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, Status_OrderID);
            stm.setInt(2, OrderID);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStatusOrderIdByStatusName(String statusName) {
        int statusOrderId = -1;
        try {
            String query = "SELECT Status_OrderID FROM Status_Order WHERE Status_Name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, statusName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                statusOrderId = resultSet.getInt("Status_OrderID");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return statusOrderId;
    }

    public List<OrderItems> getOrderedProduct(int orderid) {
        List<OrderItems> orderItemsList = new ArrayList<>();
        try {
            String query = "SELECT "
                    + "p.productID, "
                    + "p.thumbnail, "
                    + "p.product_name, "
                    + "p.sale_price, "
                    + "cp.category_name, "
                    + "od.list_price, "
                    + "od.quantity, "
                    + "(od.list_price * od.quantity) AS TotalCost "
                    + "FROM [Order_items] od "
                    + "INNER JOIN [Order] o ON od.orderID = o.orderID "
                    + "INNER JOIN Product p ON p.productID = od.productID "
                    + "INNER JOIN CategoryProduct cp ON cp.category_productID = p.category_productID "
                    + "WHERE o.orderID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct categoryProduct = new CategoryProduct(
                        0,
                        rs.getString("category_name"),
                        "",
                        ""
                );

                Product product = new Product(
                        rs.getInt("productID"),
                        rs.getString("product_name"),
                        0,
                        0,
                        "",
                        0,
                        rs.getString("thumbnail"),
                        "",
                        0,
                        rs.getFloat("sale_price"),
                        categoryProduct,
                        "",
                        null,
                        false, 0
                );

                OrderItems orderItem = new OrderItems(0,
                        null,
                        product,
                        rs.getFloat("TotalCost"),
                        rs.getInt("quantity")
                );

                orderItemsList.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

    public void addOrder(int orderId, int Status_OrderID, int customerID, Date shipped_date, Date order_date, int UserID, int ReceiverId, String notes, String PaymentMethod) {
        try {
            String query = "INSERT INTO [dbo].[Order]\n"
                    + "           ([Status_OrderID]\n"
                    + "           ,[customerID]\n"
                    + "           ,[shipped_date]\n"
                    + "           ,[order_date]\n"
                    + "           ,[UserID]\n"
                    + "           ,[ReceiverID]\n"
                    + "           ,[Notes]\n"
                    + "           ,[PaymentMethod]"
                    + "           , [orderID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setInt(1, Status_OrderID);
            stm.setInt(2, customerID);
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String shipped_date1 = spd.format(shipped_date);
            String order_dater1 = spd.format(order_date);
            stm.setDate(3, java.sql.Date.valueOf(shipped_date1));
            stm.setDate(4, java.sql.Date.valueOf(order_dater1));
            stm.setInt(5, UserID);
            stm.setInt(6, ReceiverId);
            stm.setString(7, notes);
            stm.setString(8, PaymentMethod);
            stm.setInt(9, orderId);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder1(int orderId, int Status_OrderID, int customerID, Date order_date, int UserID, int ReceiverId, String notes, String PaymentMethod) {
        try {
            String query = "INSERT INTO [dbo].[Order]\n"
                    + "           ([Status_OrderID]\n"
                    + "           ,[customerID]\n"
                    + "           ,[order_date]\n"
                    + "           ,[UserID]\n"
                    + "           ,[ReceiverID]\n"
                    + "           ,[Notes]\n"
                    + "           ,[PaymentMethod]"
                    + "           , [orderID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setInt(1, Status_OrderID);
            stm.setInt(2, customerID);

            // Parsing the formatted string back into a java.util.Date
            // Converting java.util.Date to java.sql.Date
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(order_date.getTime());
            stm.setTimestamp(3, sqlDate);
            stm.setInt(4, UserID);
            stm.setInt(5, ReceiverId);
            stm.setString(6, notes);
            stm.setString(7, PaymentMethod);
            stm.setInt(8, orderId);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderItems(int orderitemID, int orderID, int productID, double list_price, int quantity) {
        try {
            String query = "INSERT INTO [dbo].[Order_items]\n"
                    + "           ([orderitemID]\n"
                    + "           ,[orderID]\n"
                    + "           ,[productID]\n"
                    + "           ,[list_price]\n"
                    + "           , [quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setInt(1, orderitemID);
            stm.setInt(2, orderID);

            stm.setInt(3, productID);
            stm.setDouble(4, list_price);
            stm.setInt(5, quantity);

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        DAOOrder db = new DAOOrder();
        System.out.println(db.getOrderbyUserID(3));
//        HashMap<Integer, Integer> countMap = db.Countorder();
//        int minCount = Integer.MAX_VALUE;
//
//// Tìm số lượng nhỏ nhất
//        for (int count : countMap.values()) {
//            if (count < minCount) {
//                minCount = count;
//            }
//        }
//
//// Tìm userID có count bằng giá trị nhỏ nhất
//        List<Integer> userIdsWithMinCount = new ArrayList<>();
//        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
//            if (entry.getValue() == minCount) {
//                userIdsWithMinCount.add(entry.getKey());
//            }
//        }
//
//// In ra userID có count ít nhất
//        System.out.println("UserID(s) có số lượng đơn hàng ít nhất:");
//        for (int userId : userIdsWithMinCount) {
//            System.out.println("UserID: " + userId);
//        }
//        int randomIndex = new Random().nextInt(userIdsWithMinCount.size());
//        int randomUserID = userIdsWithMinCount.get(randomIndex);
//
//// In ra userID được chọn
//        System.out.println("UserID được chọn ngẫu nhiên: " + randomUserID);
        //  db.addOrder(0, 4, 1,java.sql.Date.valueOf("2024-06-01"), java.sql.Date.valueOf("2024-05-20"), 3, 1, "Deliver before noon", null);
//        Map<String, String> parameters = new LinkedHashMap<>();
//        parameters.put("sale", "Bob Vrown");
// 
//
//        String conditions = " Where CONCAT(u.first_name,' ', u.last_name) LIKE ?";
//
//        // Gọi phương thức getOrder
//        DAOOrder test = new DAOOrder();
//        ArrayList<OrderItems> orderItems = test.getOrder(parameters, conditions);
//
//        // In kết quả
//        LocalDate localDate = LocalDate.now();
        // Chuyển đổi LocalDate thành Date
//        Date order_date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        // Định dạng Date thành chuỗi với định dạng "yyyy-MM-dd hh:mm:ss
//        java.util.Date date = new java.util.Date();
//        SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String formattedDate = spd.format(date);
//        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        // In ra kết quả
//        System.out.println(sqlDate);
//         db.addOrder1(27, 8, 1,date, 3, 1, "kk", "vnpay");

    }
}
