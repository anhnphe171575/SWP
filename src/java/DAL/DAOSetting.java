/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Setting;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nguyễn Đăng
 */
public class DAOSetting extends DBContext {

    public List<String> getEditHistory() {
        List<String> history = new ArrayList<>();
        try {
            String query = "SELECT Status_Name FROM Status_Order";
            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                String description = rs.getString("Status_Name");
                history.add(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public List<Setting> sortByDes() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY description";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public List<Setting> sortByStatus() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY status";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public List<Setting> sortByOrder() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY [order]";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                 int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public List<Setting> sortByValue() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY value";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public List<Setting> sortByType() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY type";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public List<Setting> sortByID() {
        List<Setting> se = new ArrayList<>();
        try {
            String query = "SELECT * FROM Setting ORDER BY settingID";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

               int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                se.add(sl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return se;
    }

    public Vector<Setting> getByStatus(String status_1) {
        Vector<Setting> slider = new Vector<>();
        String sql = "select *from Setting s" + status_1;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);

                slider.add(sl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return slider;
    }

    public Setting getBySettingID(int id) {
        Setting sl = null;
        String sql = "select * from Setting where settingID=" + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                //Setting sl = new Setting(settingID, value, description, edit_log, status);
                sl = new Setting(settingID, value, description, edit_log, status);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sl;
    }

    public void hideShow(int id, int status) {
        try {
            String sql = "UPDATE [dbo].[Setting]\n"
                    + "   SET \n"
                    + "      [status] = " + status + "\n"
                    + "\n"
                    + " WHERE settingID=" + id;

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int editSetting(Setting obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Setting]\n"
                + "   SET [value] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[edit_log] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE settingID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, obj.getValue());
            pre.setString(2, obj.getDescription());
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = spd.format(obj.getEdit_log());
            pre.setDate(3, java.sql.Date.valueOf(date2));
            pre.setInt(4, obj.getStatus());
            pre.setInt(5, obj.getSettingID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addSetting(Setting obj) {
        int n = 0;
        String sql = "INSERT INTO [Setting] "
                + "([value], [description], [edit_log], [status]) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pre.setString(1, obj.getValue());
            pre.setString(2, obj.getDescription());
            SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = spd.format(obj.getEdit_log());
            pre.setDate(3, new java.sql.Date (obj.getEdit_log().getTime()));
            pre.setInt(4, 1);
            n = pre.executeUpdate();

            // Retrieve the generated settingID
            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setSettingID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Setting> getSetting(String sql) {
        Vector<Setting> vector = new Vector<Setting>();
        try {
            Statement sta = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                 int settingID = rs.getInt("settingID");
                String value = rs.getString("value");
                String description = rs.getString("description");
                Date edit_log = rs.getDate("edit_log");
                int status = rs.getInt("status");
                Setting sl = new Setting(settingID, value, description, edit_log, status);
                vector.add(sl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    public static void main(String[] args) {
        DAOSetting dao = new DAOSetting();
        SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //System.out.println(dao.getBySettingID(24));
        //Setting newSetting = new Setting(24, 1, "phonemm", 2, "catephone", 1);
        Setting sl = new Setting(-1, "edit", "kkk", date_create_by, 1);
        System.out.println(dao.addSetting(sl));
    }
}
