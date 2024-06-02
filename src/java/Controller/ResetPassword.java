/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCustomer;
import DAL.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuan
 */
public class ResetPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = request.getParameter("role");
        request.setAttribute("role", role);
        request.getRequestDispatcher("Views/ResetPassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        DAOCustomer db = new DAOCustomer();
        DAOUser db1 = new DAOUser();
        String role = request.getParameter("role");
        if(role.equals("1") && db.getCusByEmail(email) == null){  
            request.setAttribute("role",role);
            request.setAttribute("errorMessage", "Email not registed!");
            request.getRequestDispatcher("Views/ResetPassword.jsp").forward(request, response);
        
        }
        else if(!role.equals("1")){
             request.setAttribute("role",role);
             if(db1.getUserByLogin(email, "select * from [User] where email =?") == null){
             request.setAttribute("errorMessage", "Email not registed!");
              request.getRequestDispatcher("Views/ResetPassword.jsp").forward(request, response);
        }
        }
        else{
        String otp1 = OTP(4);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(15*60);
        request.getSession().setAttribute(email + "_reset_otp", otp1);
        request.getSession().setAttribute(email + "_reset_time", getExpiredTime());
        request.getSession().setAttribute("ep", getExpiredTime());
        String resetLink = "http://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/NewPassword?otp=" + otp1 + "&email=" + email + "&role=" + role;
        try {
            request.setAttribute("role",role);
            sendEmail("Reset PassWord", resetLink, email);
            request.setAttribute("errorMessage", "An email was sent!");
            request.getRequestDispatcher("Views/ResetPassword.jsp").forward(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
   public void sendEmail(String subject,String body, String to) throws MessagingException, UnsupportedEncodingException{
        final String fromEmail = "anhnphe171575@fpt.edu.vn";
        // Mat khai email cua ban
        final String password = "jull jeex qjzb cdtn";
        // dia chi email nguoi nhan
        final String toEmail = to;



        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
      
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);


        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

        msg.setReplyTo(InternetAddress.parse(fromEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }
   
    static String OTP(int len) {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        // Using numeric values 
        String numbers = "0123456789";

        // Using random method 
        Random rndm_method = new Random();

        char[] otp = new char[len];
         String otp1 ="";
        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            otp[i]
                    = numbers.charAt(rndm_method.nextInt(numbers.length()));
            otp1 += otp[i];
        }
        return otp1;
    }

    public String getExpiredTime() {
        Calendar calendar = Calendar.getInstance();
        //Date currentTime = calendar.getTime();

        // Add n minutes
        calendar.add(Calendar.MINUTE, 2);
        Date newTime = calendar.getTime();

        // Format the result as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = dateFormat.format(newTime);
        return result;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
