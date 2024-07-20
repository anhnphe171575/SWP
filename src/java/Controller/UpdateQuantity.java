/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOInventoryTransaction;
import DAL.DAOPost;
import DAL.DAOProduct;
import Entity.Product;
import Entity.User;
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
 * @author MANH VINH
 */
public class UpdateQuantity extends HttpServlet {

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
            out.println("<title>Servlet UpdateQuantity</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateQuantity at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        DAOProduct d = new DAOProduct();
        DAOInventoryTransaction db3 = new DAOInventoryTransaction();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String type = request.getParameter("type");
        String note = request.getParameter("note");
        boolean isSuccess = true;
        String message = "";

        try {
            if (type.equals("in")) {
                Product p = d.getProductByID(productId);
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                d.UpdateQuantity(p.getQuantity() + quantity, productId);
                Product p1 = d.getProductByID(productId);
                db3.AddInventoryTransaction1(p.getProductID(), quantity, type, user.getUserID(), date_create_by, note, p1.getQuantity());
            } else if (type.equals("out")) {
                Product p = d.getProductByID(productId);
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (p.getQuantity() - quantity >= 0) {
                    d.UpdateQuantity(p.getQuantity() - quantity, productId);
                    Product p1 = d.getProductByID(productId);
                    db3.AddInventoryTransaction1(p.getProductID(), quantity, type, user.getUserID(), date_create_by, note, p1.getQuantity());
                } else {
                    isSuccess = false;
                    message = "Not enough available quantity to perform this operation.";
                }
            } else if (type.equals("update")) {
                Product p = d.getProductByID(productId);
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (quantity - p.getQuantity_hold() >= 0) {
                    d.UpdateQuantity(quantity, productId);
                    Product p1 = d.getProductByID(productId);
                    db3.AddInventoryTransaction1(p.getProductID(), quantity, type, user.getUserID(), date_create_by, note, p1.getQuantity());
                } else {
                    isSuccess = false;
                    message = "Not enough available quantity to perform this operation.";
                }
            }

            if (isSuccess) {
                Product updatedProduct = d.getProductByID(productId);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String jsonResponse = String.format("{\"status\":\"success\",\"newQuantity\":%d}", updatedProduct.getQuantity());
                response.getWriter().write(jsonResponse);
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String jsonResponse = String.format("{\"status\":\"error\",\"message\":\"%s\"}", message);
                response.getWriter().write(jsonResponse);
            }
        } catch (Exception e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse = String.format("{\"status\":\"error\",\"message\":\"%s\"}", "An error occurred while processing the request.");
            response.getWriter().write(jsonResponse);
            e.printStackTrace();
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
