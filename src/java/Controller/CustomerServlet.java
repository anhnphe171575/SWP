/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCustomer;
import DAL.DAOSecurityQuestion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Customer;
import Entity.Security;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Nguyễn Đăng
 */
public class CustomerServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DAOCustomer dao = new DAOCustomer();
        String service = request.getParameter("service");
        
        if (service == null) {
            service = "listAllCustomer";
        }
        if (service.equals("ViewDetailCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customer> vector = null;

            try {
                if (submit == null) {
                    // Retrieve the slider details
                    int sliderId = Integer.parseInt(request.getParameter("customerid"));
                    Customer sliderDetail = dao.getCustomerID(sliderId);

                    if (sliderDetail != null) {
                        request.setAttribute("listAllCustomer", sliderDetail);
                    } else {
                        // Handle the case where no slider was found
                        request.setAttribute("list", vector);
                        request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);
                    }

                    //request.setAttribute("list", vector);
                    // Forward to the JSP page
                    request.setAttribute("list", vector);
                    request.getRequestDispatcher("/Views/ViewDetailCustomer.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {

                if (submit == null) {
                    vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID");

                } else {
                    String fname = request.getParameter("first_name");
                    String lname = request.getParameter("last_name");
                    vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID where c.first_name like '%" + fname + "%'");
                }
                request.setAttribute("listAllCustomer", vector);
                request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);

            }
        } else if (service.equals("sort")) {
            String sort = request.getParameter("sort");
            if ("name".equals(sort)) {
                request.setAttribute("listAllCustomer", dao.sortByFullname());
            } else if ("email".equals(sort)) {
                request.setAttribute("listAllCustomer", dao.sortByEmail());
            } else if ("phone".equals(sort)) {
                request.setAttribute("listAllCustomer", dao.sortByPhone());
            } else {
                request.setAttribute("listAllCustomer", dao.sortByActivityHistory());
            }
            request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);

        } else if (service.equals("filter")) {
            String statusno = request.getParameter("status");
            int statuss = Integer.parseInt(statusno);
            ArrayList<String> list = new ArrayList<>();
            Map<String, String> aa1 = new LinkedHashMap<>();
            if (!statusno.equalsIgnoreCase("3")) {
                list.add("c.Status = ?");
                aa1.put("status", statusno);
            }
            Vector<Integer> vec4 = dao.getStatus("select status from Customer group by status");
            request.setAttribute("status", vec4);
            request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);
        } else if (service.equals("checkDuplicateUpdate")) {
            String field = request.getParameter("field");
            String value = request.getParameter("value");
            int customerID = Integer.parseInt(request.getParameter("customerID"));

            boolean isDuplicate = dao.checkDuplicateUpdate(field, value, customerID);

            response.setContentType("text/plain");
            response.getWriter().write(isDuplicate ? "duplicate" : "ok");
            return;
        } else if (service.equals("updateCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customer> vector = null;
             
            if (submit == null) {
                try {
                    int customerId = Integer.parseInt(request.getParameter("customerid"));
                    request.setAttribute("customer", dao.getCustomerID(customerId));
                    DAOSecurityQuestion db = new DAOSecurityQuestion();
                    request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
                    request.getRequestDispatcher("/Views/updateCustomer.jsp").forward(request, response);
                } catch (Exception e) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, e);
                    vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID");
                    request.setAttribute("listAllCustomer", vector);
                    request.setAttribute("error", "Unable to load customer details.");
                    request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);
                }
            } else {
                
                try {
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
                    Date date1 = formatter1.parse(date);
                    String gender = request.getParameter("gender");
                    boolean gen = Boolean.parseBoolean(gender);

                    String securityid = request.getParameter("security");
                    int securityId = Integer.parseInt(securityid);
                    String securityAnswer = request.getParameter("securityAnswer");
                    String image = request.getParameter("image");

                    LocalDate localDate = LocalDate.now();
                    Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    String securityque = request.getParameter("security_question");
                    Security sq = new Security(securityId, securityque);
                    int customerid = Integer.parseInt(customerID);

                    Customer cus = new Customer(customerid, fname, lname, phone, email, address, username, password, date1, gen, date_create_by, sq, securityAnswer, image);
                    dao.updateCustomer(cus);
                    response.sendRedirect("CustomerServletURL");
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", "Invalid date format.");
                    request.getRequestDispatcher("/Views/updateCustomer.jsp").forward(request, response);
                } catch (Exception e) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, e);
                    request.setAttribute("error", "An error occurred while updating the customer.");
                    request.getRequestDispatcher("/Views/updateCustomer.jsp").forward(request, response);
                }
            }
        } else if (service.equals("checkDuplicate")) {
            String field = request.getParameter("field");
            String value = request.getParameter("value");
            boolean isDuplicate = dao.checkDuplicateInCustomerAndStaff(field, value);
            response.setContentType("text/plain");
            response.getWriter().write(isDuplicate ? "duplicate" : "ok");
            return;
        } // Existing code...
        else if (service.equals("addCustomer")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                DAOSecurityQuestion db = new DAOSecurityQuestion();
                request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
                request.getRequestDispatcher("/Views/addCustomer.jsp").forward(request, response);
            } else {
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
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                String gender = request.getParameter("gender");
                boolean gen = Boolean.parseBoolean(gender);

                String securityid = request.getParameter("security");
                int securityId = Integer.parseInt(securityid);
                String sercurityquestion = request.getParameter("security_question");
                Security sq = new Security();
                sq.setSecurityID(securityId);
                sq.setSecurity_question(sercurityquestion);
                String securityAnswer = request.getParameter("secutityAnswer");
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Customer cus = new Customer(-1, fname, lname, phone, email, address, username, password, date1, gen, date_create_by, sq, securityAnswer, null);

                try {
                    dao.insertCustomer(cus);
                    response.sendRedirect("CustomerServletURL");
                } catch (IllegalArgumentException e) {
                    request.setAttribute("error", e.getMessage());
                    DAOSecurityQuestion db = new DAOSecurityQuestion();
                    request.setAttribute("security", db.getAll("select * from SecurityQuestion"));
                    request.getRequestDispatcher("/Views/addCustomer.jsp").forward(request, response);
                }
            }
        } else if (service.equals("listAllCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customer> vector = null;
            if (submit == null) {
                vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID");
            } else {
                String fname = request.getParameter("first_name");
                String lname = request.getParameter("last_name");
                vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID where c.first_name like '%" + fname + "%'");
            }
//            int page = 0;
//        int numberOfPage = 6;
//        String xpage = request.getParameter("page");
//        int size = vector.size();
//        int num = (size % numberOfPage == 0 ? (size / numberOfPage) : ((size / numberOfPage) + 1));
//        if (xpage == null) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(xpage);
//        }
//        int start, end;
//        start = (page - 1) * numberOfPage;
//        end = Math.min(page * numberOfPage, vector.size());
//        Vector<Customer> list = dao.getListByPage(vector, start, end);
//        request.setAttribute("post", list);
//        request.setAttribute("numpage", num);
//        request.setAttribute("page", page);
//        request.setAttribute("numberOfPage", numberOfPage);

            Vector<Integer> vec4 = dao.getStatus("select status from Customer group by status");
            Vector<String> vec2 = dao.getFname("select first_name from Customer group by first_name");
            Vector<String> vec3 = dao.getPhone("select phone from Customer group by phone");
            Vector<String> vec5 = dao.getEmail("select email from Customer group by email");

            request.setAttribute("first_name", vec2);
            request.setAttribute("phone", vec3);
            request.setAttribute("status", vec4);
            request.setAttribute("email", vec5);

            request.setAttribute("listAllCustomer", vector);
            request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);

        } else {
            String submit = request.getParameter("submit");
            Vector<Customer> vector = null;
            if (submit == null) {
                vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID");
            } else {
                String fname = request.getParameter("first_name");
                String lname = request.getParameter("last_name");
                vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name, c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.activity_history, c.securityID, sq.security_question, c.securityAnswer, c.image from Customer c inner join SecurityQuestion sq on c.securityID = sq.securityID where c.first_name like '%" + fname + "%'");
            }
//            int page = 0;
//        int numberOfPage = 6;
//        String xpage = request.getParameter("page");
//        int size = vector.size();
//        int num = (size % numberOfPage == 0 ? (size / numberOfPage) : ((size / numberOfPage) + 1));
//        if (xpage == null) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(xpage);
//        }
//        int start, end;
//        start = (page - 1) * numberOfPage;
//        end = Math.min(page * numberOfPage, vector.size());
//        Vector<Customer> list = dao.getListByPage(vector, start, end);
//        request.setAttribute("post", list);
//        request.setAttribute("numpage", num);
//        request.setAttribute("page", page);
//        request.setAttribute("numberOfPage", numberOfPage);

            Vector<Integer> vec4 = dao.getStatus("select status from Customer group by status");
            Vector<String> vec2 = dao.getFname("select first_name from Customer group by first_name");
            Vector<String> vec3 = dao.getPhone("select phone from Customer group by phone");
            Vector<String> vec5 = dao.getEmail("select email from Customer group by email");

            request.setAttribute("first_name", vec2);
            request.setAttribute("phone", vec3);
            request.setAttribute("status", vec4);
            request.setAttribute("email", vec5);

            request.setAttribute("listAllCustomer", vector);
            request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);

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
        processRequest(request, response);
    }
//        DAOCustomer dao = new DAOCustomer();
//        Vector<Customer> vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender, c.status, c.securityID, sq.security_question, c.securityAnswer from Customer c\n"
//                + "inner join SecurityQuestion sq on c.securityID = sq.securityID");
//        request.setAttribute("listAllCustomer", vector);
//        request.getRequestDispatcher("/Views/ViewCustomer.jsp").forward(request, response);

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
        processRequest(request, response);

//        DAOCustomer dao = new DAOCustomer();
//        Vector<Customer> vector = dao.getCustomer("select c.customerID, c.first_name, c.last_name,c.phone, c.email, c.address, c.username, c.password, c.dob, c.gender from Customer c\n"
//                + "inner join SecurityQuestion sq on c.securityID = sq.securityID");
//        int customerID = Integer.parseInt(request.getParameter("customerID"));
//        String fname = request.getParameter("first_name");
//        String lname = request.getParameter("last_name");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        String address = request.getParameter("address");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String dob = request.getParameter("dob");
//        String gender = request.getParameter("gender");
//        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
//        Date date1 = null;
//        try {
//            date1 = formatter1.parse(dob);
//        } catch (ParseException ex) {
//            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (gender.equals("1")) {
//            dao.updateCustomer(customerID, fname, lname, phone, email, address, username, password, date1, true);
//        } else {
//            dao.updateCustomer(customerID, fname, lname, phone, email, address, username, password, date1, false);
//        }
//        request.setAttribute("listCustomer", vector);
//        request.getRequestDispatcher("/Views/updateCustomer.jsp").forward(request, response);
//        request.setAttribute("listAllCustomer", vector);
//        response.sendRedirect("CustomerServletURL");
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
