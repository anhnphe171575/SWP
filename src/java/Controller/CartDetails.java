/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCart;
import DAL.DAOCategoryProduct;
import DAL.DAOProduct;
import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author phuan
 */
public class CartDetails extends HttpServlet {

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
            out.println("<title>Servlet CartDetails</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartDetails at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("cus");
        DAOCart db = new DAOCart();
        DAOProduct db1 = new DAOProduct();
        DAOCategoryProduct db2 = new DAOCategoryProduct();    
        session.setAttribute("cart", db.getListCart(cus.getCustomerID()));
        request.setAttribute("Cate1", db2.getCategoryProductProduct());
        request.setAttribute("CategoryB", db1.ListCatogoryAndBrand());
        request.setAttribute("list", db.getListCart(cus.getCustomerID()));
        request.setAttribute("quantity", db1.CountProductByProduct());
        request.getRequestDispatcher("Views/CartDetails.jsp").forward(request, response);
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
       String Quantity = request.getParameter("quantity");
       String CardID = request.getParameter("cartid");
       String pid = request.getParameter("pid");
       String delete = request.getParameter("delete");
       DAOCart db = new DAOCart();
       if(Quantity !=null && CardID !=null){
        
       db.UpdateCartQuantity(Integer.parseInt(CardID), Integer.parseInt(Quantity),Integer.parseInt(pid));
       }
      String CartItem = request.getParameter("cartitemid");
      
       if(CardID != null && CartItem !=null && delete != null){
           db.DeleteCardItems(Integer.parseInt(CardID), Integer.parseInt(CartItem));
       }
       request.setAttribute("scroll", "scroll");
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
