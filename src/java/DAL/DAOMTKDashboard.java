/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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
        System.out.println(dao.trendCus7day("2024-06-01"));
    }
}
