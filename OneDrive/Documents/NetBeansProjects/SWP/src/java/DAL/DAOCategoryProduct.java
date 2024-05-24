/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.CategoryProduct;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author phuan
 */
public class DAOCategoryProduct extends DBContext{
          
    public List<CategoryProduct> getCategoryProductProduct() {
        List<CategoryProduct> cproduct = new ArrayList();
        try {
            String query ="select distinct category_name from CategoryProduct";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                CategoryProduct cp = new CategoryProduct(-1, rs.getString("category_name"), null);
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