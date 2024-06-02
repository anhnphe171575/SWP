/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOProduct;

import Entity.CategoryProduct;
import Entity.Post;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        request.setAttribute("list", d.getProduct());
        request.setAttribute("category", cp.getCategoryProductName());
        request.setAttribute("status", cp.getCategoryStatus());
        request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);
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
                request.setAttribute("list", d.getProductbyCategoryName(category));
            } else {
                request.setAttribute("list", d.getProductStatusbyID(status));
            }
            request.setAttribute("category", cp.getCategoryProductName());
            request.setAttribute("status", cp.getCategoryStatus());
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);

        } else {
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
