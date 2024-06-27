/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOAdminDashboard;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class AdminDashboard extends HttpServlet {

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
            out.println("<title>Servlet AdminDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboard at " + request.getContextPath() + "</h1>");
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
//        request.getRequestDispatcher("Views/AdminDashboard.jsp").forward(request, response);
        DAOAdminDashboard daoAd = new DAOAdminDashboard();
        request.setAttribute("revenues", daoAd.trendRevenues());
        request.setAttribute("newCus", daoAd.trendCusNew());
        request.setAttribute("sucess", daoAd.trendO_Sucess1());
        request.setAttribute("all", daoAd.trendAll1());
        request.setAttribute("fb", daoAd.trendFeedbackstar());
        request.setAttribute("order_status", daoAd.Order_Status());

        request.getRequestDispatcher("Views/AdminDashboard.jsp").forward(request, response);

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
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        int month1 = Integer.parseInt(month);
        int year1 = Integer.parseInt(year);

        DAOAdminDashboard daoAd = new DAOAdminDashboard();
        request.setAttribute("revenues", daoAd.trendRevenues());
        request.setAttribute("newCus", daoAd.trendCusNew());
        request.setAttribute("sucess", daoAd.trendO_Sucess2(month1, year1));
        request.setAttribute("all", daoAd.trendAll2(month1, year1));
        request.setAttribute("fb", daoAd.trendFeedbackstar());
        request.setAttribute("order_status", daoAd.Order_Status());

        request.getRequestDispatcher("Views/AdminDashboard.jsp").forward(request, response);
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
