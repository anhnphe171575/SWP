/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class AddressOrder {
    private int AddressID;
    private String AddressOrder;

    public AddressOrder() {
    }

    public AddressOrder(int AddressID, String AddressOrder) {
        this.AddressID = AddressID;
        this.AddressOrder = AddressOrder;
    }

    public int getAddressID() {
        return AddressID;
    }

    public String getAddressOrder() {
        return AddressOrder;
    }

    public void setAddressID(int AddressID) {
        this.AddressID = AddressID;
    }

    public void setAddressOrder(String AddressOrder) {
        this.AddressOrder = AddressOrder;
    }

    @Override
    public String toString() {
        return "AddressOrder{" + "AddressID=" + AddressID + ", AddressOrder=" + AddressOrder + '}';
    }    
}
