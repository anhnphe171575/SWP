/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Vector;
import Entity.Post;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Security;
import Entity.User;

/**
 *
 * @author admin
 */
public class DAOPost extends DBContext {
     public Post getPostById(int id) {
        Post p = null;
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID where p.postID=" + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"), "");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
            }
        } catch (Exception ex) {

        }
        return p;
    } 
      public Vector<Post> getPostByCPname(String name) {

        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n" +
"                                p.featured,p.status,p.brief_information,\n" +
"                                 p.description,p.flag, p.date_create_by,\n" +
"                				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n" +
"                				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n" +
"                				 cp.category_postID,cp.category_productID,\n" +
"                				 cpr.category_productID,cpr.category_name,cpr.category_description\n" +
"                				 from Post p \n" +
"                               inner join CategoryPost cp on p.category_postID=cp.category_postID\n" +
"                               inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n" +
"                                inner join [User] u on p.UserID = u.UserID where cpr.category_name='"+ name+"'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"), "");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return vector;
    }public void editPost(Post obj) {
        String sql = "UPDATE [dbo].[Post]\n"
                + "   SET [thumbnail] =?\n"
                + "      ,[title] = ?\n"
                + "      ,[category_postID] = ?\n"
                + "      ,[featured] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[brief_information] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[flag] = ?\n"
                + "\n"
                + " WHERE postID =?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, obj.getThumbnail());
            pre.setString(2, obj.getTitle());
            pre.setInt(3, obj.getCp().getCategory_postID());
            pre.setInt(4, obj.getFeatured());
            pre.setInt(5, obj.getStatus());
            pre.setString(6, obj.getBrief_information());
            pre.setString(7, obj.getDescription());
            pre.setInt(8, obj.getFlag());
            pre.setInt(9, obj.getPostID());

            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Vector<Post> getLastBlog() {
        Vector<Post> vector = new Vector<>();
        String sql = "WITH LatestDate AS (\n"
                + "    SELECT MAX(date_create_by) AS max_date\n"
                + "    FROM post\n"
                + ")"
                + "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID where date_create_by = (SELECT max_date FROM LatestDate)";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"), "");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {

        }
        return vector;

    }

    public Vector<Post> getBlog() {
        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID ORDER BY  date_create_by DESC";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"), "");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"), "");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {

        }
        return vector;

    }
 public void hideShow(int id, int status) {
        try {
            String sql = "UPDATE [dbo].[Post]\n"
                    + "   SET \n"
                    + "      [status] = "+status+"\n"
                    + "\n"
                    + " WHERE postID="+id;

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Vector<Post> sort(String option) {
        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID"
                + "                  order by " + option + " ASC";

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security sq = new Security(rs.getInt("securityID"),
                        null);
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),"");
                User u = new User(rs.getInt("UserID"),
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
                        rs.getInt("role"),
                        sq,
                        rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"),
                        cpr);
                Post p = new Post(rs.getInt("postID"),
                        rs.getString("thumbnail"),
                        rs.getString("title"),
                        cp,
                        rs.getInt("featured"),
                        rs.getInt("status"),
                        rs.getString("brief_information"),
                        rs.getString("description"),
                        rs.getInt("flag"),
                        u,
                        rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return vector;

    }

    public void addPost(Post obj) {
        try {
            String sql = "INSERT INTO [dbo].[Post]\n"
                    + "           ([thumbnail]\n"
                    + "           ,[title]\n"
                    + "           ,[category_postID]\n"
                    + "           ,[featured]\n"
                    + "           ,[status]\n"
                    + "           ,[brief_information]\n"
                    + "           ,[description]\n"
                    + "           ,[flag]\n"
                    + "           ,[UserID]\n"
                    + "           ,[date_create_by])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getThumbnail());
            pre.setString(2, obj.getTitle());
            pre.setInt(3, obj.getCp().getCategory_postID());
            pre.setInt(4, obj.getFeatured());
            pre.setInt(5, obj.getStatus());
            pre.setString(6, obj.getBrief_information());
            pre.setString(7, obj.getDescription());
            pre.setInt(8, obj.getFlag());
            pre.setInt(9, obj.getUser().getUserID());
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = spd.format(obj.getDate_create_by());

            pre.setDate(10, Date.valueOf(date1));

            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Integer> getStatus(String sql) {
        Vector<Integer> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                vector.add(rs.getInt("status"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }

    public Vector<String> getAllNameCategory(String sql) {
        Vector<String> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                vector.add(rs.getString("category_name"));
            }
        } catch (Exception ex) {
        }

        return vector;
    }

    public Vector<Post> search(String title) {
        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID where title like '%" + title + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security();
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"),"");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {

        }
        return vector;

    }

    public Vector<Post> getAll1(Map <String, String> aa1, String all) {

        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID " + all;
//                + "WHERE p.Status = ? \n"
//                + "AND (u.first_name + ' ' + u.last_name) = ?\n"
//                + "and cpr.category_name = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setInt(1, status);
            int i = 1;
            for (Map.Entry<String, String> item : aa1.entrySet()) {
                if (item.getKey().equals("status")) {
                    pre.setInt(i, Integer.parseInt(item.getValue()));
                } else {
                    pre.setString(i, item.getValue());
                }
                i++;
            }
//            pre.setString(3, catagory);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Security se = new Security();
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"),"");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Post> getAll() {

        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID ";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"),"");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"),"");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {

        }
        return vector;
    }
    public Vector<Post> HotPost() {

        Vector<Post> vector = new Vector<>();
        String sql = "select p.postID,p.thumbnail,p.title,cpr.category_name,\n"
                + "                p.featured,p.status,p.brief_information,\n"
                + "                 p.description,p.flag, p.date_create_by,\n"
                + "				 u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "				 u.role,u.dob,u.gender,u.status,u.securityID,u.securityAnswer, \n"
                + "				 cp.category_postID,cp.category_productID,\n"
                + "				 cpr.category_productID,cpr.category_name,cpr.category_description\n"
                + "				 from Post p \n"
                + "                inner join CategoryPost cp on p.category_postID=cp.category_postID\n"
                + "                inner join CategoryProduct cpr on cpr.category_productID = cp.category_productID\n"
                + "                inner join [User] u on p.UserID = u.UserID where p.featured = 1 AND p.status = 1";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Security se = new Security(rs.getInt("securityID"),"");
                CategoryProduct cpr = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), rs.getString("category_description"),"");
                User u = new User(rs.getInt("UserID"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("username"), rs.getString("password"),
                        rs.getDate("dob"), rs.getBoolean("gender"), rs.getInt("status"), rs.getInt("role"), se, rs.getString("securityAnswer"));
                CategoryPost cp = new CategoryPost(rs.getInt("category_postID"), cpr);
                Post p = new Post(rs.getInt("postID"), rs.getString("thumbnail"), rs.getString("title"), cp, rs.getInt("featured"), rs.getInt("status"), rs.getString("brief_information"),
                        rs.getString("description"), rs.getInt("flag"), u, rs.getDate("date_create_by"));
                vector.add(p);
            }
        } catch (Exception ex) {

        }
        return vector;
    }

    public Vector<CategoryProduct> getCp(String sql) {
        Vector<CategoryProduct> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int category_productID = rs.getInt(1);
                String category_name = rs.getString(2);
                String category_description = rs.getString(3);
                CategoryProduct obj = new CategoryProduct(category_productID, category_name, category_description,"");
                vector.add(obj);
            }
        } catch (Exception ex) {

        }
        return vector;
    }

    public static void main(String[] args) {
        DAOPost daoP = new DAOPost();
        LocalDate localDate = LocalDate.now();
        java.util.Date date_create_by = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        CategoryProduct cp1 = new CategoryProduct();
        CategoryPost cp = new CategoryPost(1, cp1);
        User u = new User(2, "", "", "", "", "", "", "", null, true, 0, 0, null, "");
       Post obj = new Post(0,"aaa","aaa", cp, 1, 1, "aaa", "aaa", 1,u , date_create_by);
    daoP.addPost(obj);

//        String category = "all";
//        String author = "all";
//        String status_raw = "1";
//        int status = Integer.parseInt(status_raw);
//        Map<String, String> aa1 = new LinkedHashMap<>();
    
      
      

// Print entries
//for (Map.Entry<String, String> item : aa1.entrySet()) {
//    System.out.println(item.getKey() + " - " + item.getValue());
//}
//        ArrayList<String> list = new ArrayList<>();
//        if (!status_raw.equalsIgnoreCase("all")) {
//            list.add("p.Status = ?");
//             aa1.put("status", status_raw);
//        }
//        if (!author.equalsIgnoreCase("all")) {
//            list.add("(u.first_name + ' ' + u.last_name) = ?");
//            aa1.put("author", author);
//        }
//        if (!category.equalsIgnoreCase("all")) {
//            list.add("cpr.category_name = ?");
//             aa1.put("category", category);
//        }
//        Vector<Post> vector = new Vector<>();
//        if (list.isEmpty()) {
//            vector = daoP.getAll1(aa1, "");
//        } else {
//            String all1 = "where ";
//            for (int i = 0; i < list.size(); i++) {
//                if (i == list.size() - 1) {
//                    all1 += list.get(i);
//                } else {
//                    all1 += list.get(i) + " AND ";
//                }
//            }
//            vector = daoP.getAll1(aa1, all1);
//            System.out.println(all1);
//            System.out.println(aa1);
//           
//        }
//          System.out.println(vector);
//    }
}
}
