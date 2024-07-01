/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCart;
import DAL.DAOProduct;
import Entity.CartItems;
import Entity.Customer;
import Entity.Product;
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
        String quantity = request.getParameter("quantity");
        int pid_raw = 0;
        try {
            pid_raw = Integer.parseInt(pid);
        } catch (Exception e) {
            request.getRequestDispatcher("HomePage").forward(request, response);
        }
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("cus");
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        DAOCart db = new DAOCart();
        System.out.println(pid);
        int quantity1 = 1;
        if (quantity != null) {
            quantity1 = Integer.parseInt(quantity);
        }
        DAOProduct db1 = new DAOProduct();
        if (db.checkCart(cus.getCustomerID()) == null) {
            db.Add2Cart(cus.getCustomerID());
            db.Add2CartItem(1, db.checkCart(cus.getCustomerID()).getCartID(), pid_raw, quantity1);
            request.setAttribute("error", "error2");
        } else {
            if (db.chekcProductInCart(pid_raw) == null) {
                int cartItemsID = db.getCartItemsByCartID(cart.get(0).getCart().getCartID()).size() + 1;
                db.Add2CartItem(cartItemsID, db.checkCart(cus.getCustomerID()).getCartID(), pid_raw, quantity1);
                request.setAttribute("error", "error");
            } else {
                Product p = new Product();
                int quantity2= 1;
                p =  db1.getProductByID(pid_raw);
                if((db.chekcProductInCart(pid_raw).getQuantity() + quantity1) > (p.getQuantity() - p.getQuantity_hold())){
                    quantity2 = p.getQuantity() - p.getQuantity_hold();
                }
                else{
                    quantity2  = db.chekcProductInCart(pid_raw).getQuantity() + quantity1;
                }
                db.UpdateCartQuantity(db.checkCart(cus.getCustomerID()).getCartID(), quantity2, pid_raw);
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
