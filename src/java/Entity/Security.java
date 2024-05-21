/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phuan
 */
public class Security {
    private int securityID;
    private String security_question;

    public Security(){
        
    }
    public Security(int securityID, String security_question) {
        this.securityID = securityID;
        this.security_question = security_question;
    }

    public int getSecurityID() {
        return securityID;
    }

    public void setSecurityID(int securityID) {
        this.securityID = securityID;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    @Override
    public String toString() {
        return "Security{" + "securityID=" + securityID + ", security_question=" + security_question + '}';
    }
    
}
