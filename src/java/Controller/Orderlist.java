/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOOrder;
import DAL.DAOProduct;
import Entity.OrderItems;
import Entity.Product;
import Entity.StatusOrder;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MANH VINH
 */
public class Orderlist extends HttpServlet {

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
            out.println("<title>Servlet Orderlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Orderlist at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOOrder d = new DAOOrder();
        ArrayList<OrderItems> list = d.getOrderInfor();
        ArrayList<OrderItems> orderItems = d.getOrderInfor();
        Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
        List<String> st = d.getStatusOrder();
        request.setAttribute("sale", d.getSaleName());
        request.setAttribute("list1", orderItems);
        request.setAttribute("quantity", quantity);
        request.setAttribute("status", st);
        request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
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
        DAOOrder d = new DAOOrder();
        String service = request.getParameter("service");
        if (service == null) {
            doGet(request, response);
        } else if (service.equals("search")) {
            String id = request.getParameter("id");
            String name = request.getParameter("customer");
            if (id != null && !id.isEmpty() && (name == null || name.isEmpty())) {
                ArrayList<OrderItems> orderItems = d.getOrderById(id);
                Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("list1", orderItems);
                request.setAttribute("quantity", quantity);
                request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
            } else if (name != null && !name.isEmpty() && (id == null || id.isEmpty())) {
                ArrayList<OrderItems> orderItems = d.getOrderByFullName(name);
                Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("list1", orderItems);
                request.setAttribute("quantity", quantity);
                request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
            } else {
                doGet(request, response);
            }
        } else if (service.equals("filter")) {
            Date from = formatDate(request.getParameter("fromDate"));
            Date to = formatDate(request.getParameter("toDate"));
            String sale = request.getParameter("sale");
            String status = request.getParameter("status");
            ArrayList<OrderItems> orderItems;
            Map<Integer, Integer> quantity;

// Check if date range is provided
            if (from != null && to != null) {
                orderItems = d.getOrderbyDate(from, to);
            } else if (sale != null && !sale.isEmpty()) { // Check if sale name is provided
           //     orderItems = d.getOrderbySaleName(sale);
            } else if (status != null && !status.isEmpty()) { // Check if status is provided
             //   orderItems = d.getOrderbyStatus(status);
            } else { // Default case if no parameters are provided
                orderItems = new ArrayList<>(); // Or you can fetch a default list
            }

            //quantity = d.getOrderQuantities(orderItems);

            request.setAttribute("sale", d.getSaleName());
//            request.setAttribute("list1", orderItems);
//            request.setAttribute("quantity", quantity);
            request.setAttribute("status", d.getStatusOrder());

            request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
        } else {
            doHead(request, response);
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
