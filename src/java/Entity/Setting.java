/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Nguyễn Đăng
 */
public class Setting {
    private int settingID;
    private int type;
    private String value;
    private int order;
    private String description;
    private int status;

    public Setting() {
    }

    public Setting(int settingID, int type, String value, int order, String description, int status) {
        this.settingID = settingID;
        this.type = type;
        this.value = value;
        this.order = order;
        this.description = description;
        this.status = status;
    }

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingID=" + settingID + ", type=" + type + ", value=" + value + ", order=" + order + ", description=" + description + ", status=" + status + '}';
    }

   
}
