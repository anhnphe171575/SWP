/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOStaff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import Entity.CategoryProduct;
import Entity.Post;
import Entity.Staff;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Views/loginUser.jsp").forward(request, response);
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
        DAOStaff dao = new DAOStaff();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service.equals("login")) {
            boolean check = dao.login(username, password);
            if (check) {
                session.setAttribute("username", username);
                Staff user = dao.getStaffByLogin(username);
                session.setAttribute("role", user.getRole());
                if (user.getRole().getRoleID() == 1) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(15*60);
                    response.sendRedirect("MKTDashboard");
                } else if (user.getRole().getRoleID() == 3) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(15*60);    
                    response.sendRedirect("SaleDashboardURL");
                } 
                else if (user.getRole().getRoleID() == 2 || user.getRole().getRoleID() == 4) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(15*60);
                    response.sendRedirect("orderlist");
                } 
                else if (user.getRole().getRoleID() == 5) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(15*60);
                    response.sendRedirect("AdminSettingURL");
                } 
              else {
                    session.setAttribute("user", user);
                    response.sendRedirect("");
                }
            } else if (service.equals("logout")) {
                session.invalidate(); // Hủy bỏ session hiện tại
                doGet(request, response);

            } else {
                request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
                request.getRequestDispatcher("Views/loginUser.jsp").forward(request, response);
            }
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
