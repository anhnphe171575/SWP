/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOFeedback;
import DAL.DAOProduct;
import Entity.Feedback;
import Entity.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author phuan
 */
public class FeedBackList extends HttpServlet {

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
            out.println("<title>Servlet FeedBackList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedBackList at " + request.getContextPath() + "</h1>");
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
        DAOFeedback db = new DAOFeedback();
        DAOProduct db1 = new DAOProduct();
        request.setAttribute("list", db.getFeedBack());
        request.setAttribute("product", db1.getProduct());
        request.getRequestDispatcher("Views/FeedBackList.jsp").forward(request, response);
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
        DAOFeedback db = new DAOFeedback();
          DAOProduct db1 = new DAOProduct();
        List<Feedback> list = new ArrayList<>();
        if (service.equals("sort")) {
            String sort = request.getParameter("sort");
            list = db.Sort(sort);

        } else if(service.equals("search")){
            String name = request.getParameter("name");
            list= db.search(name);
        } else if (service.equals("filter")) {
            String status_raw = request.getParameter("status");
            String star = request.getParameter("star");
            String pro = request.getParameter("proid");
            Map<String, String> list1 = new LinkedHashMap<>();
            ArrayList<String> list2 = new ArrayList<>();
            if (!status_raw.equalsIgnoreCase("all")) {
                list2.add("f.status = ?");
                list1.put("status", status_raw);
            }
            if (!star.equalsIgnoreCase("all")) {
                list2.add("f.rate_star = ?");
                list1.put("star", star);
            }
            if (!pro.equalsIgnoreCase("all")) {
                list2.add("p.productID = ?");
                list1.put("pro", pro);
            }
           
            if (list2.isEmpty()) {
                list = db.getFeedBack(list1, "");
            } else {
                String all1 = "where ";
                for (int i = 0; i < list2.size(); i++) {
                    if (i == list2.size() - 1) {
                        all1 += list2.get(i);
                    } else {
                        all1 += list2.get(i) + " AND ";
                    }
                }
                list = db.getFeedBack(list1, all1);
            }
        }

        request.setAttribute("list", list);
        request.setAttribute("product", db1.getProduct());
        request.getRequestDispatcher("Views/FeedBackList.jsp").forward(request, response);
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
