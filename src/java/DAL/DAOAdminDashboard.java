/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;

/**
 *
 * @author admin
 */
public class DAOAdminDashboard extends DBContext {

    public LinkedHashMap<Double, String> trendRevenues() {
        LinkedHashMap<Double, String> linkedHashMap = new LinkedHashMap<>();
        String sql = "SELECT SUM(oi.list_price * oi.quantity) AS total, cp.category_name AS category\n"
                + "FROM Order_items oi\n"
                + "INNER JOIN Product p ON oi.productID = p.productID\n"
                + "INNER JOIN CategoryProduct cp ON cp.category_productID = p.category_productID\n"
                + "WHERE p.category_productID BETWEEN cp.category_productID AND cp.category_productID\n"
                + "GROUP BY cp.category_name;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getDouble("total"), rs.getString("category"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<Integer, Integer> trendCusNew() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "Select \n"
                + "(SELECT COUNT(*) AS newly_registered_customers\n"
                + "FROM Customer\n"
                + "WHERE activity_history >= DATEADD(DAY, -5, GETDATE())) as 'newly_customer',\n"
                + "\n"
                + "(SELECT COUNT(DISTINCT o.customerID) AS customers_with_recent_purchases\n"
                + "FROM [Order] o\n"
                + "WHERE o.order_date >= DATEADD(DAY, -1, GETDATE())) as 'newly_order';";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getInt("newly_customer"), rs.getInt("newly_order"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> trendO_Sucess2(int month, int year) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @month INT = " + month + "; \n"
                + "DECLARE @year INT = " + year + ";\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEFROMPARTS(@year, @month, 1) AS date_created\n"
                + "    UNION ALL\n"
+ "    SELECT DATEADD(DAY, 1, date_created)\n"
                + "    FROM DateRange\n"
                + "    WHERE MONTH(DATEADD(DAY, 1, date_created)) = @month\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(o.orderID) AS success_count\n"
                + "FROM DateRange dr\n"
                + "LEFT JOIN [order] o \n"
                + "    ON CAST(dr.date_created AS DATE) = CAST(o.order_date AS DATE)\n"
                + "    AND o.Status_OrderID = 6\n"
                + "    AND MONTH(o.order_date) = @month\n"
                + "    AND YEAR(o.order_date) = @year\n"
                + "LEFT JOIN [Status_Order] s ON s.Status_OrderID = o.Status_OrderID\n"
                + "GROUP BY dr.date_created\n"
                + "ORDER BY dr.date_created;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("success_count"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> trendO_Sucess1() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @today DATE = GETDATE();\n"
                + "DECLARE @start DATE = DATEFROMPARTS(YEAR(@today), MONTH(@today), 1); -- Ngày đầu tiên của tháng hiện tại\n"
                + "DECLARE @end DATE = DATEADD(DAY, -1, DATEADD(MONTH, 1, @start)); -- Ngày cuối cùng của tháng hiện tại\n"
                + "\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(o.orderID) AS success_count\n"
                + "FROM (\n"
                + "    SELECT DATEADD(DAY, number - 1, @start) AS date_created\n"
                + "    FROM (\n"
                + "        SELECT TOP (DATEDIFF(DAY, @start, @end) + 1) \n"
                + "               ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS number\n"
                + "        FROM [master].dbo.spt_values\n"
                + "    ) AS numbers\n"
                + ") dr\n"
                + "LEFT JOIN [order] o \n"
                + "    ON dr.date_created = CAST(o.order_date AS DATE)\n"
                + "    AND o.Status_OrderID = 6\n"
                + "    AND o.order_date >= @start \n"
                + "    AND o.order_date <= @end\n"
                + "LEFT JOIN [Status_Order] s ON s.Status_OrderID = o.Status_OrderID\n"
                + "GROUP BY dr.date_created\n"
                + "ORDER BY dr.date_created;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("success_count"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> trendAll1() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @today DATE = GETDATE();\n"
                + "DECLARE @start DATE = DATEFROMPARTS(YEAR(@today), MONTH(@today), 1); -- Ngày đầu tiên của tháng hiện tại\n"
                + "DECLARE @end DATE = DATEADD(DAY, -1, DATEADD(MONTH, 1, @start)); -- Ngày cuối cùng của tháng hiện tại\n"
                + "\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(o.orderID) AS total_orders\n"
                + "FROM (\n"
                + "    SELECT DATEADD(DAY, number - 1, @start) AS date_created\n"
                + "    FROM (\n"
                + "        SELECT TOP (DATEDIFF(DAY, @start, @end) + 1) \n"
                + "               ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS number\n"
                + "        FROM [master].dbo.spt_values\n"
                + "    ) AS numbers\n"
                + ") dr\n"
                + "LEFT JOIN [order] o \n"
                + "    ON dr.date_created = CAST(o.order_date AS DATE)\n"
                + "    AND o.order_date >= @start \n"
                + "    AND o.order_date <= @end\n"
                + "GROUP BY dr.date_created\n"
                + "ORDER BY dr.date_created;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_orders"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> Order_Status() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "SELECT COALESCE(COUNT(o.OrderID), 0) AS order_count, s.Status_Name\n"
                + "FROM [Order] o\n"
                + "RIGHT JOIN Status_Order s ON s.Status_OrderID = o.Status_OrderID\n"
                + "GROUP BY s.Status_Name;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("Status_Name"), rs.getInt("order_count"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }
public LinkedHashMap<String, Double> trendFeedbackstar() {
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "SELECT c.category_name, AVG(CAST(f.rate_star AS DECIMAL(4,2))) AS total_stars\n"
                + "FROM CategoryProduct c\n"
                + "join Product p on c.category_productID=p.category_productID\n"
                + "JOIN  Feedback f ON p.productID = f.productID\n"
                + "GROUP BY c.category_name;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("category_name"), rs.getDouble("total_stars"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }
  public double trendFeedbackStarAver() {
        double averageRateStar = 0.0;
        String sql = "SELECT AVG(rate_star) AS average_rate_star FROM [n7].[dbo].[Feedback];";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                averageRateStar = rs.getDouble("average_rate_star");
            }
        } catch (Exception ex) {
            System.out.println("Query failed: " + ex.getMessage());
        }
        return averageRateStar;
    }

    public LinkedHashMap<String, Integer> trendAll2(int month, int year) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @selectedMonth INT = "+month+"; -- Ví dụ: chọn tháng 6\n"
                + "DECLARE @selectedYear INT = "+year+"; -- Ví dụ: chọn năm 2024\n"
                + "\n"
                + "-- Ngày đầu tiên và ngày cuối cùng của tháng\n"
                + "DECLARE @start DATE = DATEFROMPARTS(@selectedYear, @selectedMonth, 1);\n"
                + "DECLARE @end DATE = DATEADD(DAY, -1, DATEADD(MONTH, 1, @start));\n"
                + "\n"
                + "-- Tạo một bảng tạm thời với tất cả các ngày trong tháng\n"
                + ";WITH DateRange AS (\n"
                + "    SELECT @start AS the_date\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, the_date)\n"
                + "    FROM DateRange\n"
                + "    WHERE the_date < @end\n"
                + ")\n"
                + "\n"
                + "-- Subquery để lấy tất cả các giá trị của Status_OrderID từ bảng Status_Order\n"
                + ", AllStatus AS (\n"
                + "    SELECT DISTINCT Status_OrderID\n"
                + "    FROM Status_Order\n"
                + ")\n"
                + "\n"
                + "SELECT \n"
                + "    dr.the_date,\n"
                + "    COUNT(o.orderID) AS success_count\n"
                + "FROM DateRange dr\n"
                + "LEFT JOIN [order] o ON dr.the_date = CAST(o.order_date AS DATE)\n"
                + "                    AND o.Status_OrderID IN (SELECT Status_OrderID FROM AllStatus)\n"
                + "GROUP BY dr.the_date\n"
                + "ORDER BY dr.the_date;";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
linkedHashMap.put(rs.getString("the_date"), rs.getInt("success_count"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linkedHashMap;
    }

    public static void main(String[] args) {
        DAOAdminDashboard dao = new DAOAdminDashboard();
        System.out.println(dao.trendFeedbackStarAver());
    }
}
