package DAL;

import Entity.CategoryProduct;
import Entity.Receiver;
import Entity.Customer;
import Entity.Order;
import Entity.OrderItems;
import Entity.Product;
import Entity.Role;
import Entity.StatusOrder;
import Entity.User;
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAOOrder extends DBContext {

    public ArrayList<OrderItems> getOrder() {
        ArrayList<OrderItems> orderitems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, o.Status_OrderID, o.shipped_date, \n"
                    + "                        c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, \n"
                    + "                        c.username, c.password, c.dob, c.gender, c.activity_history, \n"
                    + "                        u.UserID, u.first_name, u.last_name, \n"
                    + "                        u.phone, u.email, u.address, \n"
                    + "                        u.username AS user_username, u.password, u.dob, \n"
                    + "                        u.gender, u.status, ri.ReceiverFullName, ri.ReceiverMobile, ri.ReceiverAddress, \n"
                    + "                        p.productID, p.product_name, p.quantity, p.year, p.product_description, \n"
                    + "                        p.featured, p.thumbnail, p.brief_information, p.original_price, p.sale_price, \n"
                    + "                        p.brand, p.update_date, p.status AS product_status, st.Status_Name, st.RoleID, \n"
                    + "                        ot.orderitemID, ot.list_price, ot.quantity, ot.discount \n"
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
                        rs.getString("status_name"),
                        r);
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
                        rs.getBoolean("product_status"));

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
                        ri, null);

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

    public ArrayList<OrderItems> getOrderInfor() {
        ArrayList<OrderItems> list = new ArrayList<>();
        try {
            String query = "SELECT  o.orderID, o.order_date, c.first_name, c.last_name, STRING_AGG(p.product_name, ', ') AS Product_name, SUM(ot.list_price * ot.quantity) AS total_cost,\n"
                    + "                    st.Status_OrderID ,st.Status_Name\n"
                    + "                    FROM \n"
                    + "                        [Order] o\n"
                    + "                    INNER JOIN \n"
                    + "                       Order_items ot ON o.orderID = ot.orderID\n"
                    + "                    INNER JOIN \n"
                    + "                     Customer c ON o.customerID = c.customerID\n"
                    + "                    INNER JOIN \n"
                    + "                       Status_Order st ON o.Status_OrderID = st.Status_OrderID\n"
                    + "                    INNER JOIN \n"
                    + "                      Product p ON ot.productID = p.productID\n"
                    + "                    GROUP BY \n"
                    + "                        o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID,st.Status_Name";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("status_orderid"),
                        rs.getString("status_name"),
                        null
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
                        null
                );
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
                        null);
                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null
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
                        rs.getString("Status_Name"),
                        null
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
                        null
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
                        null
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
                        rs.getString("Status_Name"),
                        null
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
                        null
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
                        null
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

    public ArrayList<OrderItems> getOrderbyDate(Date fromDate, Date toDate) {
        ArrayList<OrderItems> orderItems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, c.first_name, c.last_name, STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "st.Status_OrderID, st.Status_Name "
                    + "FROM [Order] o "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID "
                    + "INNER JOIN Product p ON ot.productID = p.productID "
                    + "WHERE (o.order_date BETWEEN ? AND ?) "
                    + "GROUP BY o.orderID, o.order_date, c.first_name, c.last_name, st.Status_OrderID, st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setDate(1, new java.sql.Date(fromDate.getTime()));
            stm.setDate(2, new java.sql.Date(toDate.getTime()));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("status_orderid"),
                        rs.getString("status_name"),
                        null
                );

                // Create Customer object
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
                        null
                );
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
                        null);
                // Create Order object
                Order o = new Order(
                        rs.getInt("orderID"),
                        st,
                        c,
                        null,
                        rs.getDate("order_date"),
                        null,
                        null,
                        null
                );

                // Create OrderItems object
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
            e.printStackTrace();
        }
        return orderItems;
    }

    public ArrayList<OrderItems> getOrderbySaleName(String name) {
        ArrayList<OrderItems> orderItems = new ArrayList<>();
        try {
            String query = "SELECT o.orderID, o.order_date, c.first_name, c.last_name, STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "st.Status_OrderID, st.Status_Name "
                    + "FROM [Order] o \n"
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID \n"
                    + "INNER JOIN Customer c ON o.customerID = c.customerID \n"
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID \n"
                    + "INNER JOIN Product p ON ot.productID = p.productID \n"
                    + "INNER JOIN [User] u on u.UserID = o.UserID\n"
                    + "WHERE CONCAT(u.first_name, ' ', u.last_name) = ?\n"
                    + "GROUP BY \n"
                    + "    o.orderID, \n"
                    + "    o.order_date, \n"
                    + "    c.first_name, \n"
                    + "    c.last_name, \n"
                    + "    st.Status_OrderID, \n"
                    + "    st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name"),
                        null // assuming no need for a third parameter based on your usage
                );

                // Create Customer object
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
                        null
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
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"), // concatenated product names
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
                        null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p,
                        rs.getFloat("total_cost"),
                        0 // assuming quantity is not needed here
                );

                orderItems.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public ArrayList<OrderItems> getOrderbyStatus(String status) {
        ArrayList<OrderItems> orderItems = new ArrayList<>();
        try {
            String query = "SELECT "
                    + "    o.orderID, "
                    + "    o.order_date, "
                    + "    c.first_name, "
                    + "    c.last_name, "
                    + "    STRING_AGG(p.product_name, ', ') AS Product_name, "
                    + "    SUM(ot.list_price * ot.quantity) AS total_cost, "
                    + "    st.Status_OrderID, "
                    + "    st.Status_Name "
                    + "FROM [Order] o "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Status_Order st ON o.Status_OrderID = st.Status_OrderID "
                    + "INNER JOIN Product p ON ot.productID = p.productID "
                    + "INNER JOIN [User] u ON u.UserID = o.UserID "
                    + "WHERE st.Status_Name = ? "
                    + "GROUP BY "
                    + "    o.orderID, "
                    + "    o.order_date, "
                    + "    c.first_name, "
                    + "    c.last_name, "
                    + "    st.Status_OrderID, "
                    + "    st.Status_Name";

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Create StatusOrder object
                StatusOrder st = new StatusOrder(
                        rs.getInt("Status_OrderID"),
                        rs.getString("Status_Name"),
                        null // Adjust if there's a third parameter needed
                );

                // Create Customer object
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
                        null
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
                        null
                );

                // Create Product object
                Product p = new Product(
                        0,
                        rs.getString("Product_name"), // concatenated product names
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
                        null
                );

                // Create OrderItems object
                OrderItems ot = new OrderItems(
                        0,
                        o,
                        p,
                        rs.getFloat("total_cost"),
                        0 // Assuming quantity is not needed here
                );

                orderItems.add(ot);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<String> getSaleName() {
        List<String> names = new ArrayList<>();
        try {
            String query = "SELECT u.first_name, u.last_name FROM [User] u;";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
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
                    + "    st.status_name AS status "
                    + "FROM [Order] o "
                    + "INNER JOIN Customer c ON o.customerID = c.customerID "
                    + "INNER JOIN Order_items ot ON o.orderID = ot.orderID "
                    + "INNER JOIN [User] u ON o.UserID = u.UserID "
                    + "INNER JOIN Status_Order st ON st.Status_OrderID = o.Status_OrderID "
                    + "WHERE o.orderID = ? "
                    + "GROUP BY o.orderID, c.first_name, c.last_name, c.email, c.phone, o.order_date, u.first_name, u.last_name, st.status_name";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusOrder statusOrder = new StatusOrder(0,
                        rs.getString("status"),
                        null);

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
                        null);

                Order order = new Order(rs.getInt("orderID"),
                        statusOrder,
                        customer,
                        null,
                        rs.getDate("order_date"),
                        user,
                        null,
                        query);
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
                        null);
                od.add(o);
            }
        } catch (Exception e) {

        }
        return od;
    }

    public List<OrderItems> getOrderedProduct(int orderid) {
        List<OrderItems> orderItemsList = new ArrayList<>();
        try {
            String query = "SELECT "
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
                        0,
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
                        false
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

    public List<OrderItems> getListbyPage(List<OrderItems> list, int start, int end) {
        ArrayList<OrderItems> l = new ArrayList();
        for (int i = start; i < end; i++) {
            l.add(list.get(i));
        }
        return l;
    }

    public static void main(String[] args) {
        DAOOrder d = new DAOOrder();
        System.out.println(d.getSaleName().toString());
    }
}
