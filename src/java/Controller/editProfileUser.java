/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOStatusOrder;
import DAL.DAOUser;
import Entity.Role;
import Entity.Security;
import Entity.StatusOrder;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyễn Đăng
 */
@MultipartConfig
public class editProfileUser extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "D:\\SWPShopping\\web\\img";

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
            out.println("<title>Servlet editProfileUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfileUser at " + request.getContextPath() + "</h1>");
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
        DAOUser dao = new DAOUser();
        Vector<User> vector
                = (Vector<User>) dao.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password, u.dob,u.gender,u.status, u.RoleID,r.Role_Name,u.securityID,u.securityAnswer,s.security_question,u.image from [User] u\n"
                        + "                inner join SecurityQuestion s on u.securityID=s.securityID \n"
                        + "                inner join [Role] r on r.RoleID=u.RoleID where u.UserID="
                        + Integer.parseInt(request.getParameter("userid")));
        request.setAttribute("vector", vector);
        DAOSecurityQuestion db = new DAOSecurityQuestion();
        request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
        //request.setAttribute("role", dao.getRole("select * from Role"));
        request.getRequestDispatcher("/Views/editProfileUser.jsp").forward(request, response);
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
       DAOUser dao = new DAOUser();

        Part filePart = request.getPart("file");
        String fileUrl = "";
        
        try {
            // Get the file name
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Create a file path
            File file = new File(UPLOAD_DIR, fileName);

            // Write the file to the specified directory
            filePart.write(file.getAbsolutePath());
            fileUrl = "imgProfile/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file upload error
            // You can add appropriate error handling logic here, like logging or showing an error message to the user
        }

        String UserID = request.getParameter("UserID");
        String fname = request.getParameter("first_name");
        String lname = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String status = request.getParameter("status");
        String roleID = request.getParameter("RoleID");
        String rolename = request.getParameter("role");
        int roleid = Integer.parseInt(roleID.trim());
        Role role = new Role(roleid, rolename);
        String securityid = request.getParameter("security");
        int securityId = Integer.parseInt(securityid);
        
        Security sq = new Security(securityId, "");
        String securityAnswer = request.getParameter("securityAnswer");
        String image = request.getParameter("image");
        // check data: empty, length...
        //check primary key 

        boolean gender1 = Boolean.parseBoolean(gender);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = formatter1.parse(dob);
        } catch (ParseException ex) {
            ex.printStackTrace();
            // Handle date parsing error
            // You can add appropriate error handling logic here, like logging or showing an error message to the user
        }
        int status1 = Integer.parseInt(status);
        int UserId = Integer.parseInt(UserID);

        User user = new User(UserId, fname, lname, phone, email, address, username, password, date1, gender1, status1, role, sq, securityAnswer, fileUrl);
        dao.UpdateUser(user);
        response.sendRedirect("editProfileUserURL?userid=" + UserID);
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
