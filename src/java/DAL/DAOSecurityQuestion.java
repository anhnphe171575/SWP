/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.DBContext;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Security;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> getSecurityQuestions() {
        List<String> securityQuestions = new ArrayList<>();
        try {
            String query = "SELECT security_question FROM SecurityQuestion";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String question = rs.getString("security_question");
                securityQuestions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return securityQuestions;
    }
     public int getSecurityQuestionID(String questionText) {
        try {
            String query = "SELECT securityID FROM SecurityQuestion WHERE security_question = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, questionText);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("securityID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
