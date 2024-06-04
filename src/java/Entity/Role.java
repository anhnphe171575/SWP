/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.util.*;
import java.lang.*;
public class Role {
    int RoleID;
    int Role_Name;

    public Role() {
    }

    public Role(int RoleID, int Role_Name) {
        this.RoleID = RoleID;
        this.Role_Name = Role_Name;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public int getRole_Name() {
        return Role_Name;
    }

    public void setRole_Name(int Role_Name) {
        this.Role_Name = Role_Name;
    }

    @Override
    public String toString() {
        return "Role{" + "RoleID=" + RoleID + ", Role_Name=" + Role_Name + '}';
    }
    
}
