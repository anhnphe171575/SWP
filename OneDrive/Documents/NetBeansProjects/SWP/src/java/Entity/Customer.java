/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Date;

/**
 *
 * @author Nguyễn Đăng
 */
public class Customer {
    private int customerID;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String address;
    private String username;
    private String password;
    private Date dob;
    private boolean gender;
    private int status;
    private Security security;
    private String secutityAnswer;

    public Customer() {
    }

    public Customer(int customerID, String first_name, String last_name, String phone, String email, String address, String username, String password, Date dob, boolean gender, int status, Security security, String secutityAnswer) {
        this.customerID = customerID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.status = status;
        this.security = security;
        this.secutityAnswer = secutityAnswer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public String getSecutityAnswer() {
        return secutityAnswer;
    }

    public void setSecutityAnswer(String secutityAnswer) {
        this.secutityAnswer = secutityAnswer;
    }
      
    

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", username=" + username + ", password=" + password + ", dob=" + dob + ", gender=" + gender + ", status=" + status + ", secutityID=" + security + ", secutityAnswer=" + secutityAnswer + '}';
    }
    

}