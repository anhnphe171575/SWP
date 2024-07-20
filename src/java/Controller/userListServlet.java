/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOStaff;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Role;
import Entity.Security;
import Entity.Staff;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nitro
 */
@MultipartConfig
public class userListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "C:\\Users\\admin\\OneDrive\\Documents\\GitHub\\SWP\\web\\imgUserProfile";

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
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStaff daoU = new DAOStaff();
        List<Staff> list = new ArrayList<>();
        PrintWriter out = response.getWriter();
        String service = request.getParameter("service");

        if (service == null) { //run direct servlet
            service = "listAllUsers";
        }
        if (service.equals("deleteUser")) {
            //get id
            int id = Integer.parseInt(request.getParameter("userID"));
            //delete
//            daoU.removeUser(id);
            response.sendRedirect("userList");

        } else if (service.equals("updateUser")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                //call model -> store, manager

                DAOSecurityQuestion db = new DAOSecurityQuestion();
                request.setAttribute("error", request.getParameter("error"));
                request.setAttribute("question", db.getSecurtityQuestion("select * from SecurityQuestion"));
                request.setAttribute("role", daoU.getRole("select * from Role"));
                request.setAttribute("user", daoU.getStaffsByID(Integer.parseInt(request.getParameter("UserID"))));
                request.getRequestDispatcher("/Views/updateUsers.jsp").forward(request, response);

            } else {
                Part filePart = request.getPart("thumbnail");
                // Get the file name
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Create a file path                
                File file = new File(UPLOAD_DIR, fileName);
                // Write the file to the specified directory
                filePart.write(file.getAbsolutePath());
                String fileUrl = "imgUserProfile/" + fileName;

                String UserID = request.getParameter("UserID");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String status = request.getParameter("status");
                String roleID = request.getParameter("role");
                int roleid = Integer.parseInt(roleID);
                Role role = new Role(roleid, "");
                String securityQuestion = request.getParameter("securirtyQuestion");
                int securityid = Integer.parseInt(securityQuestion);
                Security sq = new Security(securityid, "");
                String securityAnswer = request.getParameter("securityAnswer");
                int status1 = Integer.parseInt(status);
                int UserId = Integer.parseInt(UserID);
                boolean gender1 = Boolean.parseBoolean(gender);
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                try {
                    date1 = formatter1.parse(dob);
                } catch (ParseException ex) {
                    Logger.getLogger(userListServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                Boolean a = true;
                String error = "";
                if (daoU.notStaffbyEmail(daoU.getStaffsByID(UserId).getEmail(), email) != null) {
                    error += "Error Mail";
                    a = false;
                }
                if (daoU.notStaffbyUsername(daoU.getStaffsByID(UserId).getUsername(), username) != null) {
                    error += "Error UserName";
                    a = false;

                }
                if (a == false) {
                    //request.setAttribute("error", error);
                    //  request.getRequestDispatcher("userList?service=updateUser&UserID=" + UserID).forward(request, response);
                    response.sendRedirect("userList?service=updateUser&UserID=" + UserID + "&error=" + error);
                } else {
                    Staff user = new Staff(UserId, fname, lname, phone, email, address, username, password, date1, gender1, status1, role, sq, securityAnswer, fileUrl);
                    daoU.UpdateStaff(user);
                    response.sendRedirect("userList");
                }

            }
        } else if (service.equals("sort")) {
            String sort = request.getParameter("sort");
            request.setAttribute("data", daoU.sort(sort));
            RequestDispatcher dispath = request.getRequestDispatcher("/Views/ViewUsers.jsp");
            //run
            dispath.forward(request, response);
        } else if (service.equals("filter")) {
            String status_raw = request.getParameter("status");
            String gender = request.getParameter("gender");
            String role = request.getParameter("role");
            Map<String, String> list1 = new LinkedHashMap<>();
            ArrayList<String> list2 = new ArrayList<>();

            if (!status_raw.equalsIgnoreCase("all")) {
                list2.add("u.status = ?");
                list1.put("status", status_raw);
            }
            if (gender != null && !gender.equalsIgnoreCase("all")) {
                list2.add("u.gender = ?");
                list1.put("gender", gender.equals("Male") ? "true" : "false");
            }
            if (!role.equalsIgnoreCase("all")) {
                list2.add("u.RoleID = ?");
                list1.put("role", role);
            }

            if (list2.isEmpty()) {
                list = daoU.getStaff("", new LinkedHashMap<>());
            } else {
                StringBuilder queryBuilder = new StringBuilder("SELECT * FROM [User] u ");
                for (int i = 0; i < list2.size(); i++) {
                    if (i == 0) {
                        queryBuilder.append("WHERE ");
                    }
                    queryBuilder.append(list2.get(i));
                    if (i < list2.size() - 1) {
                        queryBuilder.append(" AND ");
                    }
                }
                list = daoU.getStaff(queryBuilder.toString(), list1);
            }
        } else if (service.equals("listAllUsers")) {
            //check sumit
            String submit = request.getParameter("submit");
            //get data
            Vector<Staff> vector = null;
            if (submit == null) {
                vector = daoU.getStaffs();
            } else {
                String action = request.getParameter("action");
                String fname = request.getParameter("first_name");
                String lname = request.getParameter("last_name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                if (action.equals("search1")) {
                    vector = daoU.getStaffs();
                } else if (action.equals("search2")) {
                    vector = daoU.getStaffs();
                } else if (action.equals("search3")) {
                    vector = daoU.getStaffs();
                }

            }
            DAOSecurityQuestion db = new DAOSecurityQuestion();

            String titlePage = "Users Manager";
            String titleTable = "List of Users";
            //set data for view

            //          request.setAttribute("user", daoU.getUsers(""));
            request.setAttribute("list", list);
            request.setAttribute("data", vector);
            request.setAttribute("page", titlePage);
            request.setAttribute("role", daoU.getRole("select * from Role"));
            request.setAttribute("question", db.getSecurtityQuestion("select * from SecurityQuestion"));
            request.setAttribute("titleTable", titleTable);
            //select view
            RequestDispatcher dispath = request.getRequestDispatcher("/Views/ViewUsers.jsp");
            //run
            dispath.forward(request, response);

        }
    }

    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOStaff daoU = new DAOStaff();

        Part filePart = request.getPart("thumbnail");
        // Get the file name
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        // Create a file path
        File file = new File(UPLOAD_DIR, fileName);
        // Write the file to the specified directory
        filePart.write(file.getAbsolutePath());
        String fileUrl = "imgUserProfile/" + fileName;
        String UserID = request.getParameter("UserID");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String status = request.getParameter("status");
        String roleID = request.getParameter("role");
        int roleid = Integer.parseInt(roleID);
        Role role = new Role(roleid, "");
        String securityQuestion = request.getParameter("securirtyQuestion");
        int securityid = Integer.parseInt(securityQuestion);
        Security sq = new Security(securityid, "");
        String securityAnswer = request.getParameter("securityAnswer");
        int status1 = Integer.parseInt(status);
        int UserId = Integer.parseInt(UserID);
        boolean gender1 = Boolean.parseBoolean(gender);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = formatter1.parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(userListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Boolean a = true;
        String error = "";
        if (daoU.notStaffbyEmail(daoU.getStaffsByID(UserId).getEmail(), email) != null) {
            error += "Error Mail";
            a = false;
        }
        if (daoU.notStaffbyUsername(daoU.getStaffsByID(UserId).getUsername(), username) != null) {
            error += "Error UserName";
            a = false;

        }
        if (a == false) {
            //request.setAttribute("error", error);
            //  request.getRequestDispatcher("userList?service=updateUser&UserID=" + UserID).forward(request, response);
            response.sendRedirect("userList?service=updateUser&UserID=" + UserID + "&error=" + error);
        } else {
            Staff user = new Staff(UserId, fname, lname, phone, email, address, username, password, date1, gender1, status1, role, sq, securityAnswer, fileUrl);
            daoU.UpdateStaff(user);
            response.sendRedirect("userList");
        }

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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(userListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
