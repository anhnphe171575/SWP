/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCustomer;
import DAL.DAOSecurityQuestion;
import Entity.Customer;
import Entity.Security;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nguyễn Đăng
 */
@MultipartConfig
public class editProfileCustomer extends HttpServlet {
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
        HttpSession session = request.getSession();
        Customer cus = (Customer)session.getAttribute("cus");
        DAOCustomer dao = new DAOCustomer();
        Vector<Customer> vector
                = (Vector<Customer>) dao.getCustomer("select c.image, c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer from Customer c\n"
                        + "inner join SecurityQuestion sq on c.securityID = sq.securityID where customerID="
                        + cus.getCustomerID());
        System.out.println(vector);
        request.setAttribute("vector", vector);
        DAOSecurityQuestion db = new DAOSecurityQuestion();
        request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
        request.getRequestDispatcher("/Views/editProfileCustomer.jsp").forward(request, response);
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
    DAOCustomer dao = new DAOCustomer();
    String applicationPath = request.getServletContext().getRealPath("");
    // Construct the path for the upload directory
    String uploadFilePath = applicationPath + File.separator + "imgCustomer";

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
        String newFileName = "customer_" + System.currentTimeMillis() + fileExtension;

        // Create a file path
        File file = new File(uploadFilePath, newFileName);

        // Write the file to the specified directory
        filePart.write(file.getAbsolutePath());

        // Construct the file URL relative to the web application
        fileUrl = "imgCustomer" + File.separator + newFileName;
    } else {
        // If no new file is uploaded, use the existing image URL
        fileUrl = request.getParameter("existingImage");
    }

    String customerID = request.getParameter("customerID");
    String fname = request.getParameter("first_name");
    String lname = request.getParameter("last_name");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String username = request.getParameter("username");
    String date = request.getParameter("dob");

    // Validate phone number and email
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
        request.setAttribute("customerID", customerID);
        request.setAttribute("first_name", fname);
        request.setAttribute("last_name", lname);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("address", address);
        request.setAttribute("username", username);
        request.setAttribute("dob", date);
        request.setAttribute("securityAnswer", request.getParameter("securityAnswer"));
        request.setAttribute("existingImage", fileUrl);

        DAOSecurityQuestion db = new DAOSecurityQuestion();
        request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
        request.getRequestDispatcher("/Views/editProfileCustomer.jsp").forward(request, response);
        return;
    }

    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = null;
    try {
        date1 = formatter1.parse(date);
    } catch (ParseException ex) {
        ex.printStackTrace();
        // Handle date parsing error
        request.setAttribute("errorMessage", "Invalid date format.");
        request.getRequestDispatcher("/Views/editProfileCustomer.jsp").forward(request, response);
        return;
    }

    String gender = request.getParameter("gender");
    boolean gen = Boolean.parseBoolean(gender);

    String securityid = request.getParameter("security");
    int securityId = Integer.parseInt(securityid);
    String securityque = request.getParameter("security_question");

    Security sq = new Security(securityId, securityque);

    String securityAnswer = request.getParameter("securityAnswer");
    int customerid = Integer.parseInt(customerID);

    LocalDate localDate = LocalDate.now();
    Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    Customer cus = new Customer(customerid, fname, lname, phone, email, address, username, "", date1, gen, date_create_by, sq, securityAnswer, fileUrl);
    dao.updateCustomer(cus);
    response.sendRedirect("editProfileCustomerURL?customerid=" + customerID);
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
