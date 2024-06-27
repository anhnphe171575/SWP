/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOFeedback;
import DAL.DAOProduct;
import Entity.CategoryProduct;
import Entity.Customer;
import Entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author phuan
 */
public class ProductDetailsPublic extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailsPublic</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailsPublic at " + request.getContextPath() + "</h1>");
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
        int pid_raw=0;
        DAOProduct db = new DAOProduct();
        DAOFeedback db1  = new DAOFeedback();
        try{
         String pid = request.getParameter("pid");
         pid_raw = Integer.parseInt(pid);
                 }
        catch(Exception e){
            request.getRequestDispatcher("ProductsListPublic").forward(request, response);
        }
        CategoryProduct cp = new CategoryProduct();
        DAOCategoryProduct db2 = new DAOCategoryProduct();
        cp = db2.getCategoryProductbyPID(pid_raw);
        request.setAttribute("feedback", db1.getFeedBackByProID(pid_raw));
        request.setAttribute("product", db.getProductByID(pid_raw));
        request.setAttribute("qreview", db1.CountFeedback(pid_raw));
        request.setAttribute("relatedP", db.getProductbyCategoryName(cp.getCategory_name()));
        request.getRequestDispatcher("Views/ProductPublicDetails.jsp").forward(request, response);
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
      int star = Integer.parseInt(request.getParameter("rating"));
  
      String comment = request.getParameter("comment");
       HttpSession session = request.getSession();
      Customer cus = (Customer) session.getAttribute("cus");
      int Cusid = cus.getCustomerID();
      int pid = Integer.parseInt(request.getParameter("pid"));
      String image = request.getParameter("image");
      LocalDate localDate = LocalDate.now();
      Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      DAOFeedback db = new DAOFeedback();
      db.InsertFeedBack(pid, Cusid, comment, star, image, 1, date_create_by);
        doGet(request, response);
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
