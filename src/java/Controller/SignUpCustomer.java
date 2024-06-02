/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCustomer;
import DAL.DAOSecurityQuestion;
import DAL.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Entity.Customer;
import Entity.Security;
import Entity.Security;
import Entity.User;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MANH VINH
 */
public class SignUpCustomer extends HttpServlet {

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
            out.println("<title>Servlet SignUpCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpCustomer at " + request.getContextPath() + "</h1>");
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
        DAOSecurityQuestion d = new DAOSecurityQuestion();
        List<String> question = d.getSecurityQuestions();
        request.setAttribute("securityQuestions", question);
        request.getRequestDispatcher("Views/signup.jsp").forward(request, response);
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
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String gen = request.getParameter("gender");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repass");
        String ques = request.getParameter("question");
        String ans = request.getParameter("answer");

        DAOCustomer db = new DAOCustomer();
        DAOSecurityQuestion db1 = new DAOSecurityQuestion();
        DAOUser dbu = new DAOUser();
        boolean checkPhone = true;
        boolean checkEmail = true;
        boolean checkUsername = true;
        boolean checkPhoneUser = true;
        boolean checkEmailUser = true;
        boolean checkUsernameUser = true;
        boolean checkPass = true;

        if (isValidPhoneNumber(phone)) {
            for (Customer c : db.getCustomer()) {
                if (phone.equals(c.getPhone())) {
                    checkPhone = false;
                    request.setAttribute("msgPhone", "Phone number already registered!");
                    break;
                }
            }
            for (User u : dbu.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,u.dob,u.gender,u.status,"
                    + "u.role,u.securityID,sq.security_question,u.securityAnswer from \"User\" u inner join SecurityQuestion sq "
                    + "on u.securityID=sq.securityID;")) {
                if (phone.equals(u.getPhone())) {
                    checkPhoneUser = false;
                    request.setAttribute("msgPhone", "Phone number already registered!");
                    break;
                }
            }
        } else {
            checkPhone = false;
            checkPhoneUser = false;
            request.setAttribute("msgPhone", "Wrong phone format!");
        }

        if (isValidEmail(email)) {
            for (Customer c : db.getCustomer()) {
                if (email.equals(c.getEmail())) {
                    checkEmail = false;
                    request.setAttribute("msgEmail", "Email already registered!");
                    break;
                }
            }
            for (User u : dbu.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,u.dob,u.gender,u.status,"
                    + "u.role,u.securityID,sq.security_question,u.securityAnswer from \"User\" u inner join SecurityQuestion sq "
                    + "on u.securityID=sq.securityID;")) {
                if (email.equals(u.getEmail())) {
                    checkEmailUser = false;
                    request.setAttribute("msgEmail", "Email already registered!");
                    break;
                }
            }

        } else {
            checkEmail = false;
            request.setAttribute("msgEmail", "Wrong email format!");
        }

        if (isValidUsername(user)) {
            for (Customer c : db.getCustomer()) {
                if (user.equals(c.getUsername())) {
                    checkUsername = false;
                    request.setAttribute("msgUser", "Username already registered!");
                    break;
                }
            }
            for (User u : dbu.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,u.dob,u.gender,u.status,"
                    + "u.role,u.securityID,sq.security_question,u.securityAnswer from \"User\" u inner join SecurityQuestion sq "
                    + "on u.securityID=sq.securityID;")) {
                if (user.equals(u.getUsername())) {
                    checkUsernameUser = false;
                    request.setAttribute("msgUser", "Username already registered!");
                    break;
                }
            }
        } else {
            checkUsername = false;
            request.setAttribute("msgUser", "Wrong username format!");
        }

        if (!pass.equals(repass)) {
            checkPass = false;
            request.setAttribute("msgRepass", "Passwords do not match!");
        }

        boolean check = checkPhone && checkEmail && checkUsername && checkPass && checkPhoneUser && checkEmailUser && checkUsernameUser;

        if (!check) {
            doGet(request, response);
        } else {
            HttpSession session = request.getSession(false);
            session.setMaxInactiveInterval(15 * 60);

            // Tính toán thời gian hết hạn của phiên
            long currentTimeMillis = System.currentTimeMillis();
            long expirationTimeMillis = currentTimeMillis + (session.getMaxInactiveInterval() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String expirationTimeStr = sdf.format(new Date(expirationTimeMillis));

            int id = db.getLastCustomerID();
            int secu_id = db1.getSecurityQuestionID(ques);
            Security sq = new Security(secu_id, ques);
            Customer cus = new Customer(id, firstName, lastName, phone, email, address, user, pass, formatDate(dob),
                    Boolean.valueOf(gen), 1, sq, ans);
            session.setAttribute("cus", cus);

            // Gửi email xác minh với thời gian hết hạn
            sendVerificationEmail(email, expirationTimeStr);
            doGet(request, response);
        }
    }

    private void sendVerificationEmail(String recipientEmail, String expirationTime) {
        final String fromEmail = "anhnphe171575@fpt.edu.vn";
        final String password = "jull jeex qjzb cdtn";
        //set properties for mail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "NoReply"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            msg.setSubject("Account Verification");
            msg.setText("Click the following link to verify your account: http://localhost:8080/SWP/verify?token=" + recipientEmail
                    + "\nThis link will expire at: " + expirationTime);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Date formatDate(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dob);
        } catch (ParseException e) {
            return null;
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean isValidUsername(String username) {
        String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9]{2,14}$";
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (username == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
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
