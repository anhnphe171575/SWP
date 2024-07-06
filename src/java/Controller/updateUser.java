/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOUser;
import Entity.Role;
import Entity.Security;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@MultipartConfig
public class updateUser extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUser at " + request.getContextPath() + "</h1>");
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
        DAOUser daoU = new DAOUser();
        DAOSecurityQuestion db = new DAOSecurityQuestion();
        request.setAttribute("error", request.getParameter("error"));
        request.setAttribute("question", db.getSecurtityQuestion("select * from SecurityQuestion"));
        request.setAttribute("role", daoU.getRole("select * from Role"));
        request.setAttribute("user", daoU.getUsersByID(Integer.parseInt(request.getParameter("UserID"))));
        request.getRequestDispatcher("/Views/updateUsers.jsp").forward(request, response);
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
        DAOUser daoU = new DAOUser();
        Part filePart = request.getPart("file");
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
        if (daoU.notUserbyEmail(daoU.getUsersByID(UserId).getEmail(), email) != null) {
            error += "Error Mail";
            a = false;
        }
        if (daoU.notUserbyUsername(daoU.getUsersByID(UserId).getUsername(), username) != null) {
            error += "Error UserName";
            a = false;

        }
        if (a == false) {
            //request.setAttribute("error", error);
            //  request.getRequestDispatcher("userList?service=updateUser&UserID=" + UserID).forward(request, response);
            response.sendRedirect("userList?service=updateUser&UserID=" + UserID + "&error=" + error);
        } else {
            User user = new User(UserId, fname, lname, phone, email, address, username, password, date1, gender1, status1, role, sq, securityAnswer, fileUrl);
            daoU.UpdateUser(user);
            response.sendRedirect("userList");
        }

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
