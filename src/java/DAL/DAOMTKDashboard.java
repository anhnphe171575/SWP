/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Post;
import Entity.Security;
import Entity.User;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class DAOMTKDashboard extends DBContext {

    public LinkedHashMap<String, Integer> trendPost7day(String date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @selectedDate DATE;\n"
                + "\n"
                + "SET @selectedDate = '" + date + "'; \n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, @selectedDate) AS date_create_by\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, date_create_by)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_create_by) < @selectedDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_create_by AS post_date,\n"
                + "    COUNT(p.date_create_by) AS total_posts\n"
                + "\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Post] p\n"
                + "    ON dr.date_create_by = p.date_create_by\n"
                + "WHERE\n"
                + "    dr.date_create_by BETWEEN DATEADD(DAY, -7, @selectedDate) AND DATEADD(DAY, -1, @selectedDate)\n"
                + "GROUP BY \n"
                + "    dr.date_create_by\n"
                + "ORDER BY \n"
                + "    dr.date_create_by DESC\n"
                + "OPTION (MAXRECURSION 0);";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_posts"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public int allProduct() {
        int n = 0;
        String sql = "SELECT SUM( quantity) AS total_quantity\n"
                + "FROM [n7].[dbo].[Product];";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("total_quantity");
            }
        } catch (Exception ex) {

        }
        return n;

    }
    public int allPost() {
        int n = 0;
        String sql = "select count (*) as post_total from Post";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("post_total");
            }
        } catch (Exception ex) {

        }
        return n;

    }
    public int allCustomer() {
        int n = 0;
        String sql = "select count (*) as post_total from Customer";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("post_total");
            }
        } catch (Exception ex) {

        }
        return n;

    }
    public int allFeedback() {
        int n = 0;
        String sql = "select count (*) as post_total from Feedback";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt("post_total");
            }
        } catch (Exception ex) {

        }
        return n;

    }

    public LinkedHashMap<String, Integer> trendProduct7day(String date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @selectedDate DATE;\n"
                + "\n"
                + "SET @selectedDate = '" + date + "'; \n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, @selectedDate) AS date_create_by\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, date_create_by)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_create_by) < @selectedDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_create_by AS post_date,\n"
                + "    COUNT(p.update_date) AS total_product\n"
                + "\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Product] p\n"
                + "    ON dr.date_create_by = p.update_date\n"
                + "WHERE\n"
                + "    dr.date_create_by BETWEEN DATEADD(DAY, -7, @selectedDate) AND DATEADD(DAY, -1, @selectedDate)\n"
                + "GROUP BY \n"
                + "    dr.date_create_by\n"
                + "ORDER BY \n"
                + "    dr.date_create_by DESC\n"
                + "OPTION (MAXRECURSION 0);";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_product"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public LinkedHashMap<String, Integer> trendPostAutoday(String start_date, String end_date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @startDate DATE = ?;\n"
                + "DECLARE @endDate DATE = ?;\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT CAST(@endDate AS DATE) AS date_created\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, 1, date_created) AS DATE)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_created) <= @startDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(p.date_create_by) AS total_product\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Post] p\n"
                + "    ON dr.date_created = CONVERT(DATE, p.date_create_by)\n"
                + "GROUP BY \n"
                + "    dr.date_created\n"
                + "ORDER BY \n"
                + "    dr.date_created;"; // Order by ascending dates

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set startDate and endDate properly
            pstmt.setString(1, start_date);
            pstmt.setString(2, end_date);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_product"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> trendProAutoday(String start_date, String end_date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql
                = "DECLARE @startDate DATE = ?;\n"
                + "DECLARE @endDate DATE = ?;\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT CAST(@endDate AS DATE) AS date_created\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, 1, date_created) AS DATE)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_created) <= @startDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(p.update_date) AS total_product\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Product] p\n"
                + "    ON dr.date_created = CONVERT(DATE, p.update_date)\n"
                + "GROUP BY \n"
                + "    dr.date_created\n"
                + "ORDER BY \n"
                + "    dr.date_created;"; // Order by ascending dates

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set startDate and endDate properly
            pstmt.setString(1, start_date);
            pstmt.setString(2, end_date);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_product"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return linkedHashMap;
    }

    public LinkedHashMap<String, Integer> trendCusAutoday(String start_date, String end_date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @startDate DATE = ?;\n"
                + "DECLARE @endDate DATE = ?;\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT CAST(@endDate AS DATE) AS date_created\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, 1, date_created) AS DATE)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_created) <= @startDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(p.activity_history) AS total_product\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Customer] p\n"
                + "    ON dr.date_created = CONVERT(DATE, p.activity_history)\n"
                + "GROUP BY \n"
                + "    dr.date_created\n"
                + "ORDER BY \n"
                + "    dr.date_created;";
        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set startDate and endDate properly
            pstmt.setString(1, start_date);
            pstmt.setString(2, end_date);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_product"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return linkedHashMap;

    }

    public LinkedHashMap<String, Integer> trendFeedbackAutoday(String start_date, String end_date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @startDate DATE = ?;\n"
                + "DECLARE @endDate DATE = ?;\n"
                + "\n"
                + "WITH DateRange AS (\n"
                + "    SELECT CAST(@endDate AS DATE) AS date_created\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, 1, date_created) AS DATE)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_created) <= @startDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_created AS post_date,\n"
                + "    COUNT(p.update_date_feedback) AS total_product\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Feedback] p\n"
                + "    ON dr.date_created = CONVERT(DATE, p.update_date_feedback)\n"
                + "GROUP BY \n"
                + "    dr.date_created\n"
                + "ORDER BY \n"
                + "    dr.date_created;";
        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set startDate and endDate properly
            pstmt.setString(1, start_date);
            pstmt.setString(2, end_date);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_product"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return linkedHashMap;

    }

    public LinkedHashMap<String, Integer> trendCus7day(String date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @selectedDate DATE;\n"
                + "SET @selectedDate = '" + date + "'; \n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, @selectedDate) AS date_create_by\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, date_create_by)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_create_by) < @selectedDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_create_by AS post_date,\n"
                + "    COUNT(p.activity_history) AS total_posts\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Customer] p\n"
                + "    ON dr.date_create_by = p.activity_history\n"
                + "WHERE\n"
                + "    dr.date_create_by BETWEEN DATEADD(DAY, -7, @selectedDate) AND DATEADD(DAY, -1, @selectedDate)\n"
                + "GROUP BY \n"
                + "    dr.date_create_by\n"
                + "ORDER BY \n"
                + "    dr.date_create_by DESC\n"
                + "OPTION (MAXRECURSION 0);\n"
                + "";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_posts"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public LinkedHashMap<String, Integer> trendFeedback7day(String date) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        String sql = "DECLARE @selectedDate DATE;\n"
                + "\n"
                + "SET @selectedDate = '" + date + "'; \n"
                + "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, @selectedDate) AS date_create_by\n"
                + "    UNION ALL\n"
                + "    SELECT DATEADD(DAY, 1, date_create_by)\n"
                + "    FROM DateRange\n"
                + "    WHERE DATEADD(DAY, 1, date_create_by) < @selectedDate\n"
                + ")\n"
                + "SELECT \n"
                + "    dr.date_create_by AS post_date,\n"
                + "    COUNT(p.update_date_feedback) AS total_posts\n"
                + "\n"
                + "FROM \n"
                + "    DateRange dr\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Feedback] p\n"
                + "    ON dr.date_create_by = p.update_date_feedback\n"
                + "WHERE\n"
                + "    dr.date_create_by BETWEEN DATEADD(DAY, -7, @selectedDate) AND DATEADD(DAY, -1, @selectedDate)\n"
                + "GROUP BY \n"
                + "    dr.date_create_by\n"
                + "ORDER BY \n"
                + "    dr.date_create_by DESC\n"
                + "OPTION (MAXRECURSION 0);";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                linkedHashMap.put(rs.getString("post_date"), rs.getInt("total_posts"));
            }
        } catch (Exception ex) {

        }
        return linkedHashMap;

    }

    public static void main(String[] args) {
        DAOMTKDashboard dao = new DAOMTKDashboard();
        System.out.println(dao.allProduct());
    }
}
