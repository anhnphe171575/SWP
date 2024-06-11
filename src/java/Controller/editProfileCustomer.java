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
import jakarta.servlet.http.Part;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Nguyễn Đăng
 */
@MultipartConfig
public class editProfileCustomer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "D:\\SWPOnline\\SWP\\SWP\\web\\image";

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
       
        DAOCustomer dao = new DAOCustomer();

      
            Vector<Customer> vector
                    = (Vector<Customer>) dao.getCustomer("select c.image, c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer from Customer c\n"
                            + "inner join SecurityQuestion sq on c.securityID = sq.securityID where customerID="
                            + Integer.parseInt(request.getParameter("customerid")));
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
        Part filePart = request.getPart("file");
       

        // Get the file name
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Create a file path
        File file = new File(UPLOAD_DIR, fileName);

        // Write the file to the specified directory
        filePart.write(file.getAbsolutePath());
         String fileUrl = "image/" + fileName;
       
        String customerID = request.getParameter("customerID");
        String fname = request.getParameter("first_name");
        String lname = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String date = request.getParameter("dob");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = formatter1.parse(date);
        } catch (ParseException ex) {
            //Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String gender = request.getParameter("gender");
        boolean gen = Boolean.parseBoolean(gender);

        String securityid = request.getParameter("security");
        int securityId = Integer.parseInt(securityid);

        Security sq = new Security(securityId, "");

        String securityAnswer = request.getParameter("securityAnswer");
        //Customer cus = new Customer(, username, username, phone, email, address, username, password, dob, true, 0, sq, securityAnswer);
        int customerid = Integer.parseInt(customerID);
        
       
        
            LocalDate localDate = LocalDate.now();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Customer cus = new Customer(customerid, fname, lname, phone, email, address, username, password, date1, gen, date_create_by, sq, securityAnswer, fileUrl);
            dao.updateCustomer(cus);
                   response.sendRedirect("editProfileCustomerURL?customerid="+customerID);
//            request.setAttribute("cus1", cus);
//            request.getRequestDispatcher("Views/editProfileCustomer.jsp").forward(request, response);
        
    }
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
