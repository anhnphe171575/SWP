/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DAL.DBContext;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Security;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DAOSecurityQuestion extends DBContext {
    public int removeSecurityQuestion(int securityID){
        int n = 0;
         try {
            String sql = "delete from SecurityQuestion where securityID =" + securityID;
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSecurityQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateSecurityQuestion(Security obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[SecurityQuestion]\n"
                + "   SET [security_question] = ?\n"
                + " WHERE [securityID] =?;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getSecurity_question());
            pre.setInt(2, obj.getSecurityID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector<Security> getAll(String sql) {
        Vector<Security> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int securityID = rs.getInt(1);
                String security_question = rs.getString(2);
                Security obj = new Security(securityID, security_question);
                vector.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOSecurityQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int addSecurityQuestion(Security obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[SecurityQuestion]\n"
                + "           ([securityID]\n"
                + "           ,[security_question])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, obj.getSecurityID());
            pre.setString(2, obj.getSecurity_question());
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public static void main(String[] args) {
        DAOSecurityQuestion dao = new DAOSecurityQuestion();
//        SecurityQuestion obj = new SecurityQuestion(1, " what old is your dog?");
//        int n = dao.updateSecurityQuestion(obj);
//    dao.removeSecurityQuestion(20);
        Vector<Security> vec = dao.getAll("select * from SecurityQuestion");
        for (Security securityQuestion : vec) {
            System.out.println(securityQuestion);
        }

    }
}
