/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOStaff;
import Entity.Role;
import Entity.Security;
import Entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author admin
 */
public class AddUser extends HttpServlet {

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
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");
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
        DAOSecurityQuestion db = new DAOSecurityQuestion();
        DAOStaff daoU = new DAOStaff();

        request.setAttribute("question", db.getSecurtityQuestion("select * from SecurityQuestion"));
        request.setAttribute("role", daoU.getRole("select * from [Role]"));
        request.getRequestDispatcher("Views/addUser.jsp").forward(request, response);

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
        DAOStaff daoU = new DAOStaff();
        DAOSecurityQuestion db = new DAOSecurityQuestion();

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String roleID = request.getParameter("role");
        int roleid = Integer.parseInt(roleID);
        int status1 = 0;
        String securityidd = request.getParameter("securityQuestion");
        int securityid = Integer.parseInt(securityidd);
        String securityAnswer = request.getParameter("securityAnswer");
        boolean hasError = false;
        String errorMessage = "";

        if ((daoU.checkPhone()).contains(phone)) {
            errorMessage = "số điện thoại đã tồn tại";
            hasError = true;
        }else
        // Check for duplicate email
        if ((daoU.checkEmail()).contains(email)) {
            errorMessage = "Email đã tồn tại";
            hasError = true;
        } else // Check for duplicate username
        if ((daoU.checkUsername()).contains(username)) {
            errorMessage = "Tài khoản đã tồn tại";
            hasError = true;
        } 
        if (hasError) {
            // Set error attribute and redirect to user list
            request.setAttribute("error", errorMessage);
        } else {

            // Parse date and boolean values
            boolean gender1 = Boolean.parseBoolean(gender);
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            try {
                date1 = formatter1.parse(dob);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            // Create User object
            Role role = new Role(roleid, "");
            Security sq = new Security(securityid, "");
            Staff user = new Staff(0, fname, lname, phone, email, address, username, password, date1, gender1, status1, role, sq, securityAnswer, null);

            // Send email with reset link
            String resetLink = "Bạn đã được thêm vào công ty ESHOP với vai trò " + daoU.getRoleName(roleid).getRole_Name() + ". Sau đây là tài khoản của bạn:\n"
                    + "Tài Khoản: " + username + " Mật Khẩu: " + password;
            try {
                sendEmail("New User", resetLink, email);
            } catch (MessagingException ex) {
                Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Insert new user
       daoU.insertStaff(user);
            response.sendRedirect("userList");
            return;
        }
        request.setAttribute("question", db.getSecurtityQuestion("select * from SecurityQuestion"));
        request.setAttribute("role", daoU.getRole("select * from [Role]"));
                    request.getRequestDispatcher("Views/addUser.jsp").forward(request, response);

    }

    public void sendEmail(String subject, String body, String to) throws MessagingException, UnsupportedEncodingException {
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
        javax.mail.Session session = javax.mail.Session.getInstance(props, auth);

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
