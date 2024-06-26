/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class StatusOrder {
    private int status_orderid;
    private String status_name;

    public StatusOrder() {
    }

    public StatusOrder(int status_orderid, String status_name) {
        this.status_orderid = status_orderid;
        this.status_name = status_name;
    }

    public int getStatus_orderid() {
        return status_orderid;
    }

    public void setStatus_orderid(int status_orderid) {
        this.status_orderid = status_orderid;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return "StatusOrder{" + "status_orderid=" + status_orderid + ", status_name=" + status_name + '}';
    }

    
    
}
