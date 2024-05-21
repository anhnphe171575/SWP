
import java.util.Date;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phuan
 */
public class NewClass {
    public static void main(String[] args) {
        
//        System.out.println("Generating OTP using random() : ");
//        System.out.print("You OTP is : ");

        // Using numeric values 
        String numbers = "0123456789";

        // Using random method 
        Random rndm_method = new Random();
        int len = 4;
        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            otp[i]
                    = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        String opt1 = otp.toString();
        System.out.println(opt1);
    
    }
}
