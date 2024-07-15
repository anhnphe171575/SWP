/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOProduct;
import DAL.DAOUser;

import Entity.CategoryProduct;
import Entity.Post;
import Entity.Product;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author MANH VINH
 */
public class ProductsList extends HttpServlet {

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
            out.println("<title>Servlet ProductsList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsList at " + request.getContextPath() + "</h1>");
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
        DAOProduct d = new DAOProduct();
        DAOCategoryProduct cp = new DAOCategoryProduct();
        DAOUser du = new DAOUser();
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") != null) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                user = du.getUserByLogin((String) session.getAttribute("username"));
                session.setAttribute("user", user);
            }
            request.setAttribute("list", d.getProduct1());
            request.setAttribute("category", cp.getCategoryProductName());
            request.setAttribute("status", cp.getCategoryStatus());
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);
        } else {
            response.sendRedirect("LoginController");
        }
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
        String service = request.getParameter("service");
        DAOProduct d = new DAOProduct();
        DAOCategoryProduct cp = new DAOCategoryProduct();
        if (service == null) {
            doGet(request, response);
        } else if (service.equals("search")) {
            String title = request.getParameter("title");
            String brief = request.getParameter("brief");
            if (title != null && !title.isEmpty()) {
                request.setAttribute("list", d.getProductByTitle(title));
            }
            if (brief != null && !brief.isEmpty()) {
                request.setAttribute("list", d.getProductByBrief(brief));
            }
            request.setAttribute("category", cp.getCategoryProductName());
            request.setAttribute("status", cp.getCategoryStatus());
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);
        } else if (service.equals("filter")) {
            String category = request.getParameter("category");
            String status_raw = request.getParameter("status");
            int status = Integer.parseInt(status_raw);
            if (!"3".equals(category) && status != 3) {
                request.setAttribute("list", d.getProductbyCategoryandStatus(category, status));
            } else if (!"3".equals(category) && status == 3) {
                request.setAttribute("list", d.getProductbyCategoryName(category));
            } else if ("3".equals(category) && status != 3) {
                request.setAttribute("list", d.getProductbyStatus(status_raw));
            } else if ("3".equals(category) && status == 3) {
                request.setAttribute("list", d.getProduct1());
            }
            request.setAttribute("category", cp.getCategoryProductName());
            request.setAttribute("status", cp.getCategoryStatus());
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);

        } else if (service.equals("sort")) {
            String sort = request.getParameter("sort");
            if ("title".equals(sort)) {
                request.setAttribute("list", d.sortByTitle());
            } else if ("category".equals(sort)) {
                request.setAttribute("list", d.sortByCategory());
            } else if ("price".equals(sort)) {
                request.setAttribute("list", d.sortByPrice("SELECT * FROM Product ORDER BY price"));
            } else if ("saleprice".equals(sort)) {
                request.setAttribute("list", d.sortBySalePrice());
            } else if ("featured".equals(sort)) {
                request.setAttribute("list", d.sortByFeatured());
            } else {
                request.setAttribute("list", d.sortByStatus());
            }
            request.setAttribute("category", cp.getCategoryProductName());
            request.setAttribute("status", cp.getCategoryStatus());
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);
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
    }// </editor-fold
}
