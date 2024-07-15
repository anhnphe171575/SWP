/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.CategoryProduct;
import Entity.Product;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

/**
 *
 * @author phuan
 */
public class DAOProduct extends DBContext {
//    public void addProduct(String product_name,  int quantity, int year, int category_productID, String product_description, int featured, String thumbnail, String brief_information, float original_price, float sale_price, String brand) {
//        try {
//            String query = "INSERT INTO Product(product_name, quantity, year, category_productID, product_description, featured, thumbnail, brief_information, original_price, sale_price, update_date, brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement stm = conn.prepareStatement(query);
//            stm.setString(1, product_name);
//
//            stm.setInt(2, quantity);
//            stm.setInt(3, year);
//            stm.setInt(4, category_productID);
//            stm.setString(5, product_description);
//            stm.setInt(6, featured);
//            stm.setString(7, thumbnail);
//            stm.setString(8, brief_information);
//            stm.setFloat(9, original_price);
//            stm.setFloat(10, sale_price);
//            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//            stm.setDate(11, currentDate);
//            stm.setString(12, brand);
//            stm.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void UpdatePrice(int productID, float price) {
        try {
            String query = "UPDATE Product\n"
                    + "   SET \n"
                    + "      [original_price] = ?\n"
                    + " WHERE productID = ?\n";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setFloat(1, price);
            stm.setInt(2, productID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateSalePrice(int productID, float saleprice) {
        try {
            String query = "UPDATE Product\n"
                    + "   SET \n"
                    + "      [sale_price] = ?\n"
                    + " WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setFloat(1, saleprice);
            stm.setInt(2, productID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllBrand() {
        List l = new ArrayList();
        String brand = null;
        try {
            String query = "select distinct p.brand from Product p";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                brand = rs.getString("brand");
                l.add(brand);
            }
        } catch (Exception e) {
        }
        return l;
    }

    public List<Product> getProduct1() {
        ArrayList<Product> l = new ArrayList();
        try {
            String query = "  select p.productID,p.product_name,p.thumbnail,p.quantity,p.quantity_hold,p.featured,p.original_price,\n"
                    + "                    p.sale_price,p.featured,p.status from Product p ORDER BY p.productID DESC";
            PreparedStatement stm = conn.prepareCall(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        0,
                        "",
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        "",
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        null,
                        null,
                        null,
                        rs.getBoolean("status"),
                        rs.getInt("quantity_hold"));
                l.add(p);
            }
        } catch (Exception e) {
        }
        return l;
    }

    public List<String> getBrand(int category_productID) {
        List<String> list = new ArrayList<>();
        try {
            String query = "select distinct brand from Product p "
                    + "where p.category_productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setInt(1, category_productID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("brand"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> sortByTitle() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product ORDER BY product_name";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> LastedProduct(String sql) {
        List<Product> list = new ArrayList<>();
        try {

            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductByTitle(String title) {
        List<Product> p = new ArrayList();
        try {
            String query = "SELECT * FROM Product WHERE product_name = ? ORDER BY productID DESC;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, title);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductByTitleByCid(String title, int cid) {
        List<Product> p = new ArrayList();
        try {
            String query = "SELECT * FROM Product WHERE product_name like '%" + title + "%' and category_productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductFeatureByTitleByCid(String title, int cid, int feature) {
        List<Product> p = new ArrayList();
        try {
            String query = "SELECT * FROM Product WHERE product_name like '%" + title + "%' and category_productID = ? and featured = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cid);
            stm.setInt(2, feature);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductFeatureByTitle(String title, int feature) {
        List<Product> p = new ArrayList();
        try {
            String query = "SELECT * FROM Product WHERE product_name like '%" + title + "%'  and featured = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, feature);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductFeatureByCid(int cid, int feature) {
        List<Product> p = new ArrayList();
        try {
            String query = "SELECT * FROM Product WHERE  category_productID = ? and featured = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cid);
            stm.setInt(2, feature);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductByBrief(String Brief) {
        List<Product> p = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product WHERE brief_information = ? ORDER BY productID DESC;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, Brief);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pr = new Product(
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
                        rs.getBoolean("status"), 0
                );
                p.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductbyCategoryandStatus(String name, int status) {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * \n"
                    + "FROM \n"
                    + "    Product p \n"
                    + "INNER JOIN \n"
                    + "    CategoryProduct cp \n"
                    + "ON \n"
                    + "    p.category_productID = cp.category_productID\n"
                    + "WHERE \n"
                    + "    cp.category_name = ? \n"
                    + "AND \n"
                    + "    p.status = ?;"
                    + "ORDER BY p.productID";

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);
            stm.setInt(2, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),
                        rs.getString("image"));
                Product product = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), 0);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductbyStatus(String status) {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product WHERE status = ? ORDER BY productID";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product product = new Product(
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
                        rs.getBoolean("status"), 0
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductbyCategoryName(String category) {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "select * from Product p inner join CategoryProduct cp on p.category_productID = cp.category_productID "
                    + "where cp.category_name = ? ORDER BY p.productID";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, category);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),
                        rs.getString("image"));
                Product product = new Product(
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
                        cp, // Chưa có CategoryProduct trong ResultSet, bạn có thể thêm sau
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), 0
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductbyCategoryID(int categoryid) {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "  select * from Product p inner join CategoryProduct cp on p.category_productID = cp.category_productID "
                    + "where p.category_productID = ? ";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, categoryid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),
                        rs.getString("image"));
                Product product = new Product(
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
                        cp, // Chưa có CategoryProduct trong ResultSet, bạn có thể thêm sau
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> sortByCategory() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product p inner join CategoryProduct cp on p.category_productID = cp.category_productID\n"
                    + "ORDER BY (SELECT category_name FROM CategoryProduct WHERE category_productID = p.category_productID)";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),
                        rs.getString("image"));
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> sortByPrice(String query) {
        List<Product> productList = new ArrayList<>();
        try {

            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> sortBySalePrice() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product ORDER BY sale_price";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> sortByFeatured() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product ORDER BY featured";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> sortByStatus() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product ORDER BY status";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Product p = new Product(rs.getInt("productID"),
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
                        rs.getBoolean("status"), 0);
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void updateProduct(int productID, String product_name,
            int quantity, int year, int category_productID, String product_description, int featured, String thumbnail,
            String brief_information, float original_price, float sale_price, Date update_date, String brand, Boolean status) {
        try {
            String query = "UPDATE Product SET product_name = ?, quantity = ?, year = ?, category_productID = ?, "
                    + "product_description = ?, featured = ?, thumbnail = ?, brief_information = ?, original_price = ?, sale_price = ?, "
                    + "update_date = ?, brand = ?, status = ? WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            java.sql.Date sqlDate = new java.sql.Date(update_date.getTime());
            stm.setString(1, product_name);
            stm.setInt(2, quantity);
            stm.setInt(3, year);
            stm.setInt(4, category_productID);
            stm.setString(5, product_description);
            stm.setInt(6, featured);
            stm.setString(7, thumbnail);
            stm.setString(8, brief_information);
            stm.setFloat(9, original_price);
            stm.setFloat(10, sale_price);
            stm.setDate(11, sqlDate);
            stm.setString(12, brand);
            stm.setBoolean(13, status);
            stm.setInt(14, productID);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> getProductFeature() {
        List<Product> product = new ArrayList();
        try {
            String query = "select p.productID,p.product_name,p.quantity,p.year,p.product_description,\n"
                    + "                    p.featured,p.thumbnail,p.brief_information,p.quantity_hold,p.original_price,p.sale_price,p.category_productID, p.brand, p.update_date,p.status,\n"
                    + "                    cp.category_name,cp.category_description from [Product] p inner join CategoryProduct cp \n"
                    + "                    on p.category_productID = cp.category_productID where p.featured ='1' and p.status = 1";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"), "");
                Product p = new Product(rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("year"),
                        rs.getString("product_description"),
                        rs.getInt("featured"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_information"),
                        rs.getFloat("original_price"),
                        rs.getFloat("sale_price"),
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), rs.getInt("quantity_hold"));
                product.add(p);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;

    }

    public List<Product> ListCatogoryAndBrand() {
        List<Product> product = new ArrayList();
        try {
            String query = " select p.brand,p.category_productID,cp.category_name   \n"
                    + "				  from [Product] p inner join CategoryProduct cp on p.category_productID = cp.category_productID\n"
                    + "				  group by p.brand,p.category_productID,cp.category_name ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), null, "");
                Product p = new Product(-1,
                        null,
                        -1,
                        -1,
                        null,
                        -1,
                        null,
                        null,
                        -1,
                        -1,
                        cp,
                        rs.getString("brand"),
                        null,
                        null, 0);
                product.add(p);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;

    }

    public HashMap<String, Integer> CountProductByCategory() {
        HashMap<String, Integer> product = new HashMap<>();
        try {
            String query = "select count(p.productID) as idProduct, cp.category_name from Product p \n"
                    + "					inner join CategoryProduct cp on p.category_productID = cp.category_productID\n"
                    + "					 group by cp.category_name";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                product.put(rs.getString("category_name"), rs.getInt("idProduct"));
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;

    }

    public HashMap<Integer, Integer> CountProductByProduct() {
        HashMap<Integer, Integer> product = new HashMap<>();
        try {
            String query = "SELECT p.productID, (p.quantity - p.quantity_hold) AS available_quantity\n"
                    + "FROM Product p\n"
                    + "GROUP BY p.productID, p.quantity, p.quantity_hold;";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                product.put(rs.getInt("productID"), rs.getInt("available_quantity"));
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;

    }

    public HashMap<String, String> ImageByCategory() {
        HashMap<String, String> image = new HashMap<>();
        try {
            String query = "select category_name, [image] from CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                image.put(rs.getString("category_name"), rs.getString("image"));
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return image;

    }

    public Product getProductByID(int productID) {
        Product p = null;
        try {
            String query = "SELECT * FROM Product p "
                    + "INNER JOIN CategoryProduct cp ON p.category_productID = cp.category_productID "
                    + "WHERE p.productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, productID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(
                        rs.getInt("category_productID"),
                        rs.getString("category_name"),
                        rs.getString("category_description"),
                        rs.getString("image")
                );
                p = new Product(
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
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProduct() {
        List<Product> product = new ArrayList();
        try {
            String query = "Select p.productID, p.product_name, p.quantity, p.year,p.category_productID, p.product_description, p.featured, p.thumbnail, "
                    + "p.brief_information,p.original_price,p.sale_price,p.update_date,p.brand, p.status, cp.category_name, cp.category_name, cp.category_description, cp.image "
                    + "from Product p inner join CategoryProduct cp "
                    + "on p.category_productID =cp.category_productID  ";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"), rs.getString("image"));
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
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), 0
                );
                product.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return product;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getProductBySorted() {
        List<Product> product = new ArrayList();
        try {
            String query = "Select p.productID, p.product_name, p.quantity_hold,p.quantity, p.year,p.category_productID, p.product_description, p.featured, p.thumbnail, "
                    + "p.brief_information,p.original_price,p.sale_price,p.update_date,p.brand, p.status, cp.category_name, cp.category_name, cp.category_description, cp.image "
                    + "from Product p inner join CategoryProduct cp "
                    + "on p.category_productID =cp.category_productID Order by p.update_date";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"),
                        rs.getString("category_name"), rs.getString("category_description"), rs.getString("image"));
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
                        cp,
                        rs.getString("brand"),
                        rs.getDate("update_date"),
                        rs.getBoolean("status"), rs.getInt("quantity_hold")
                );
                product.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return product;
    }

    public void addProduct(String product_name, int hold, int quantity, int year, int category_productID, String product_description, int featured, String thumbnail, String brief_information, float original_price, float sale_price, String brand, Boolean status) {
        try {
            String query = "INSERT INTO Product(\n"
                    + "    product_name,\n"
                    + "    quantity_hold,\n"
                    + "    quantity,\n"
                    + "    year,\n"
                    + "    category_productID,\n"
                    + "    product_description,\n"
                    + "    featured,\n"
                    + "    thumbnail,\n"
                    + "    brief_information,\n"
                    + "    original_price,\n"
                    + "    sale_price,\n"
                    + "    update_date,\n"
                    + "    brand,\n"
                    + "    status\n"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);\n";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, product_name);
            stm.setInt(2, hold);
            stm.setInt(3, quantity);
            stm.setInt(4, year);
            stm.setInt(5, category_productID);
            stm.setString(6, product_description);
            stm.setInt(7, featured);
            stm.setString(8, thumbnail);
            stm.setString(9, brief_information);
            stm.setFloat(10, original_price);
            stm.setFloat(111, sale_price);
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stm.setDate(12, currentDate);
            stm.setString(13, brand);
            stm.setBoolean(14, status);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getProductStatusbyID(int id) {
        boolean status = false;
        String query = "SELECT status FROM Product WHERE productID = ?";
        try (PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                status = rs.getBoolean("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void updateProductStatus(int productID, boolean status) {
        try {
            String query = "UPDATE Product SET status = ? WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setBoolean(1, status);
            stm.setInt(2, productID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateQuantityHold(int quantity, int productID) {
        String sql = "UPDATE [Product]\n"
                + "   SET \n"
                + "           [quantity_hold]=?\n"
                + " WHERE productID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, productID);
            pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateQuantity(int quantity, int productID) {
        String sql = "UPDATE [Product]\n"
                + "   SET \n"
                + "           [quantity]=?\n"
                + " WHERE productID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, productID);
            pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getProductFeaturedbyID(int id) {
        int featured = 0;
        try {
            String query = "SELECT featured FROM Product WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                featured = rs.getInt("featured");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return featured;
    }

    public void updateProductFeatured(int productID, int featured) {
        try {
            String query = "UPDATE Product SET featured = ? WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, featured);
            stm.setInt(2, productID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkProductNameExists(String productName) {
        boolean exists = false;
        try {
            String query = "SELECT 1 FROM Product WHERE product_name = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, productName);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public Date formatDate(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dob);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public void DeleteProduct(int id) {
        try {
            String query = " DELETE FROM Product\n"
                    + "      WHERE productID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public static void main(String[] args) {
        DAOProduct p = new DAOProduct();
//        p.DeleteProduct(39);
        System.out.println(p.getProductbyStatus("1"));
    }
}
