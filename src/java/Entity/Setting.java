/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Nguyễn Đăng
 */
public class Setting {
    private int settingID;
    private String value;
    private String description;
    private Date edit_log;
    private int status;

    public Setting() {
    }

    public Setting(int settingID, String value, String description, Date edit_log, int status) {
        this.settingID = settingID;
        this.value = value;
        this.description = description;
        this.edit_log = edit_log;
        this.status = status;
    }

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEdit_log() {
        return edit_log;
    }

    public void setEdit_log(Date edit_log) {
        this.edit_log = edit_log;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingID=" + settingID + ", value=" + value + ", description=" + description + ", edit_log=" + edit_log + ", status=" + status + '}';
    }

    
    

   
}
