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
    }
    public List<CategoryProduct> getCategoryProductProduct() {
        List<CategoryProduct> cproduct = new ArrayList();
        try {
            String query ="select distinct category_name from CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                CategoryProduct cp = new CategoryProduct(-1, rs.getString("category_name"), null,null);
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
        System.out.println(db.getCategoryProductProduct());
    }
}