/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.CategoryProduct;
import Entity.Product;
import java.lang.System.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

/**
 *
 * @author phuan
 */
public class DAOCategoryProduct extends DBContext{
    public boolean isCategoryNameDuplicate(String categoryName) {
        boolean isDuplicate = false;
        String sql = "SELECT COUNT(*) FROM CategoryProduct WHERE category_name = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoryName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    isDuplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }
    
    public int updateCategory(CategoryProduct obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[CategoryProduct] SET [category_name] = ? ,[category_description] = ?, [image] = ? WHERE category_productID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getCategory_name());
            pre.setString(2, obj.getCategory_description());
            pre.setString(3, obj.getImage());
            pre.setInt(4, obj.getCategory_productID());
            
          

            n = pre.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        return n;
    }
    public int addCategory(CategoryProduct obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CategoryProduct] ([category_name] ,[category_description] ,[image]) VALUES (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getCategory_name());
            pre.setString(2, obj.getCategory_description());
            pre.setString(3, obj.getImage());
            
          

            n = pre.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        return n;
    }
     public List<String> getCategoryProductName() {
        List<String> categoryNames = new ArrayList();
        try {
            String query = "SELECT category_name FROM CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                categoryNames.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryNames;
    }

    public List<Integer> getCategoryStatus() {
    List<Integer> categoryStatusList = new ArrayList();
    try {
        String query = "SELECT status FROM Product group by status";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int status = rs.getInt("status");
            categoryStatusList.add(status);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return categoryStatusList;
}
          public Vector<CategoryProduct> getAll(String sql) {
        Vector<CategoryProduct> vector = new Vector<>();
        try {
      
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int category_productID = rs.getInt(1);
                String category_name = rs.getString(2);
                String category_description = rs.getString(3);
                String image = rs.getString(4);
                CategoryProduct obj = new CategoryProduct(category_productID, category_name, category_description,image);
                vector.add(obj);
            }
        } catch (Exception ex) {
        

        }
        return vector;
    }
          public CategoryProduct getCategoryProductbyID(int id){
        String sql="select * from categoryproduct where category_productID =?";
        CategoryProduct cpr =null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
             cpr = new CategoryProduct(rs.getInt("category_productID"),
                    rs.getString("category_name"),
                    rs.getString("category_description"),rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return cpr;
    }public CategoryProduct getCategoryProductbyName(String name){
        String sql="select * from categoryproduct where category_name =?";
        CategoryProduct cpr =null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
             cpr = new CategoryProduct(rs.getInt("category_productID"),
                    rs.getString("category_name"),
                    rs.getString("category_description"),rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return cpr;
    }
            public CategoryProduct getCategoryProductbyPID(int pid){
        String sql="select * from CategoryProduct cp inner join Product p on cp.category_productID = p.category_productID\n" +
              "where p.productID = ?";
        CategoryProduct cpr =null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
             cpr = new CategoryProduct(rs.getInt("category_productID"),
                    rs.getString("category_name"),
                    rs.getString("category_description"),rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return cpr;
    }
    public List<CategoryProduct> getCategoryProductProduct() {
        List<CategoryProduct> cproduct = new ArrayList();
        try {
            String query ="  select distinct category_name, category_productID from CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                CategoryProduct cp = new CategoryProduct(rs.getInt("category_productID"), rs.getString("category_name"), null,null);
                cproduct.add(cp);
            }
        }
        catch(SQLException e){
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, e);
        }
        return  cproduct;
}
    public static void main(String[] args) {
        DAOCategoryProduct db = new DAOCategoryProduct();
        System.out.println(db.getAll("select * from CategoryProduct"));
    }
}