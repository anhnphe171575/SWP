/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MANH VINH
 */
public class EditProductDetails extends HttpServlet {

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
            out.println("<title>Servlet EditProductDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductDetails at " + request.getContextPath() + "</h1>");
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
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String product_name = request.getParameter("product_name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int year = Integer.parseInt(request.getParameter("year"));
            int category_productID = Integer.parseInt(request.getParameter("category_productID"));
            String product_description = request.getParameter("product_description");
            int featured = Integer.parseInt(request.getParameter("featured"));
            String thumbnail = request.getParameter("thumbnail");
            String brief_information = request.getParameter("brief_information");
            float original_price = Float.parseFloat(request.getParameter("original_price"));
            float sale_price = Float.parseFloat(request.getParameter("sale_price"));
            Date update_date = formatDate(request.getParameter("update_date"));
            String brand = request.getParameter("brand");
            Boolean status = Boolean.valueOf(request.getParameter("status"));
            DAOProduct dao = new DAOProduct();
            dao.updateProduct(productID, product_name, quantity, year, category_productID, product_description, featured, thumbnail, brief_information, original_price, sale_price, update_date, brand, status);
            response.sendRedirect("view?vid=" + productID);
        } catch (IOException | NumberFormatException e) {
        }
    }

    private Date formatDate(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dob);
            return date;
        } catch (ParseException e) {
            return null;
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
