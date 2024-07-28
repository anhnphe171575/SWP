/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOStatusOrder;
import DAL.DAOStaff;
import Entity.Customer;
import Entity.Role;
import Entity.Security;
import Entity.StatusOrder;
import Entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nguyễn Đăng
 */
@MultipartConfig
public class editProfileUser extends HttpServlet {

    public class InputValidator {

        // Regex for validating phone numbers
        private static final String PHONE_REGEX = "^[0-9]{10}$"; // Adjust the regex according to your phone number format
        private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

        // Regex for validating emails
        private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
        private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        public static boolean isValidPhoneNumber(String phoneNumber) {
            Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
            return matcher.matches();
        }

        public static boolean isValidEmail(String email) {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            return matcher.matches();
        }
    }

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
        DAOStaff dao = new DAOStaff();
        HttpSession session = request.getSession();

        Staff cus = (Staff) session.getAttribute("user");
        Vector<Staff> vector
                = (Vector<Staff>) dao.getStaff("select u.StaffID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password, u.dob,u.gender,u.status, u.RoleID,r.Role_Name,u.securityID,u.securityAnswer,s.security_question,u.image from [Staff] u\n"
                        + "                inner join SecurityQuestion s on u.securityID=s.securityID \n"
                        + "                inner join [Role] r on r.RoleID=u.RoleID where u.StaffID="
                        + cus.getStaffID());
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
        DAOStaff dao = new DAOStaff();

        String applicationPath = request.getServletContext().getRealPath("");
        // Construct the path for the upload directory
        String uploadFilePath = applicationPath + File.separator + "img";

        // Create the upload directory if it doesn't exist
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        // Get the file part from the request
        Part filePart = request.getPart("file");
        String originalFileName = "";
        String fileUrl = "";

        if (filePart != null && filePart.getSize() > 0) {
            // Get the original file name
            originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Create a new file name
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = "user_" + System.currentTimeMillis() + fileExtension;

            // Create a file path
            File file = new File(uploadFilePath, newFileName);

            // Write the file to the specified directory
            filePart.write(file.getAbsolutePath());

            // Construct the file URL relative to the web application
            fileUrl = "img" + File.separator + newFileName;
        } else {
            // If no new file is uploaded, use the existing image URL
            fileUrl = request.getParameter("existingImage");
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
        boolean gen = Boolean.parseBoolean(gender);  

        String status = request.getParameter("status");
        String roleID = request.getParameter("RoleID");
        String rolename = request.getParameter("role");
        int roleid = Integer.parseInt(roleID.trim());
        Role role = new Role(roleid, rolename);
        String securityid = request.getParameter("security");
        int securityId = Integer.parseInt(securityid);
        String securityque = request.getParameter("security_question");

        Security sq = new Security(securityId, securityque);
        String securityAnswer = request.getParameter("securityAnswer");
        String image = request.getParameter("image");
        boolean isValid = true;
        if (!InputValidator.isValidPhoneNumber(phone)) {
            request.setAttribute("phoneError", "Invalid phone number format. Please enter a 10-digit phone number.");
            isValid = false;
        }

        if (!InputValidator.isValidEmail(email)) {
            request.setAttribute("emailError", "Invalid email format.");
            isValid = false;
        }

        if (!isValid) {
            // Set the previously entered values to repopulate the form
            request.setAttribute("UserID", UserID);
            request.setAttribute("first_name", fname);
            request.setAttribute("last_name", lname);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("dob", dob);
            request.setAttribute("gender", gender);
            request.setAttribute("status", status);
            request.setAttribute("RoleID", roleID);
            request.setAttribute("role", rolename);
            request.setAttribute("security", securityid);
            request.setAttribute("securityAnswer", securityAnswer);
            request.setAttribute("existingImage", fileUrl);
        }

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

        Staff user = new Staff(UserId, fname, lname, phone, email, address, username, password, date1, gen, status1, role, sq, securityAnswer, fileUrl);
        dao.UpdateStaff(user);
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