/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCart;
import Entity.CartItems;
import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author phuan
 */
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
      String pid = request.getParameter("pid");
      int pid_raw = 0;
      try{
          pid_raw = Integer.parseInt(pid);
      }
      catch(Exception e){
          request.getRequestDispatcher("HomePage").forward(request, response);
      }
      HttpSession session = request.getSession();
      Customer cus = (Customer)session.getAttribute("cus");
      List<CartItems> cart = ( List<CartItems>) session.getAttribute("cart");
      DAOCart db = new DAOCart();
     if(db.checkCart(cus.getCustomerID()) == null){
         db.Add2Cart(cus.getCustomerID());
         db.Add2CartItem(1, db.checkCart(cus.getCustomerID()).getCartID(), pid_raw, 1);
          request.setAttribute("error", "error2");
     }
     else{
         if(db.chekcProductInCart(pid_raw) == null){
         int cartItemsID = db.getCartItemsByCartID(cart.get(0).getCart().getCartID()).size() +1;
         db.Add2CartItem(cartItemsID,db.checkCart(cus.getCustomerID()).getCartID(), pid_raw, 1);
         request.setAttribute("error", "error");
         }
         else{
             db.UpdateCartQuantity(db.checkCart(cus.getCustomerID()).getCartID(),db.chekcProductInCart(pid_raw).getQuantity() + 1 , pid_raw);
           request.setAttribute("error", "error1");        
         }
     }
  
  response.sendRedirect("CartDetails");
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
