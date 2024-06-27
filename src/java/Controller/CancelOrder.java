/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOOrder;
import DAL.DAOProduct;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyễn Đăng
 */
public class CancelOrder extends HttpServlet {

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
            out.println("<title>Servlet CancelOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelOrder at " + request.getContextPath() + "</h1>");
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
        int oid = -1;
        int sid = -1;
        int cusid = -1;

        try {
            oid = Integer.parseInt(request.getParameter("oid"));
            sid = Integer.parseInt(request.getParameter("sid"));
            cusid = Integer.parseInt(request.getParameter("cusid"));

        } catch (Exception e) {

        }
        DAOOrder db = new DAOOrder();
        DAOProduct db1 = new DAOProduct();
        String[] pids = request.getParameterValues("pid");
        String[] quantities = request.getParameterValues("quantity");

        if (pids != null && quantities != null) {
            for (int i = 0; i < pids.length; i++) {
                try {
                    int pid = Integer.parseInt(pids[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    Product p = db1.getProductByID(pid);
                    // Cập nhật số lượng sản phẩm
                     quantity += p.getQuantity();
                    db1.UpdateQuantity(quantity, pid);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        db.UpdateStatus(oid, 2,null);
        request.getRequestDispatcher("MyOrderDetailURL?id=" + oid + "&customerid=" + cusid).forward(request, response);
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
