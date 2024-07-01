/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOOrder;
import DAL.DAOProduct;
import DAL.DAOUser;
import Entity.OrderItems;
import Entity.Product;
import Entity.StatusOrder;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
        DAOUser du = new DAOUser();
        HttpSession session = request.getSession(false);

        if (session.getAttribute("username") != null) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                user = du.getUserByLogin((String) session.getAttribute("username"));
                session.setAttribute("user", user);
            }
            if (user.getRole().getRoleID() == 2) {
                int id = user.getUserID();
                ArrayList<OrderItems> orderItems = d.getOrderbyUserID(id);
                request.setAttribute("list1", orderItems);
                Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("saleid", id);
                request.setAttribute("quantity", quantity);
            } else if (user.getRole().getRoleID() == 3) {
                ArrayList<OrderItems> orderItems = d.getOrderInfor();
                request.setAttribute("sale", d.getSaleName());
                request.setAttribute("list1", orderItems);
                Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("quantity", quantity);
            } else if (user.getRole().getRoleID() == 4) {
                ArrayList<OrderItems> orderItems = d.getPackingOrder();
                request.setAttribute("list1", orderItems);
                Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("quantity", quantity);
            }
            List<String> st = d.getStatusOrder();
                       

            request.setAttribute("status", st);
                  

            request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
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
        DAOOrder d = new DAOOrder();
        String service = request.getParameter("service");
        if (service == null || service.equals("search")) {
            String id = request.getParameter("id");
            String name = request.getParameter("customer");
            ArrayList<OrderItems> orderItems = null;
            Map<Integer, Integer> quantity = null;

            if (id != null && !id.isEmpty() && (name == null || name.isEmpty())) {
                orderItems = d.getOrderById(id);
            } else if (name != null && !name.isEmpty() && (id == null || id.isEmpty())) {
                orderItems = d.getOrderByFullName(name);
            }
            if (orderItems != null) {
                quantity = d.getOrderQuantities(orderItems);
                request.setAttribute("list1", orderItems);
                request.setAttribute("quantity", quantity);
                request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
            }
        } else if (service.equals("filter")) {
            String from = request.getParameter("fromDate");
            String to = request.getParameter("toDate");
            String saleName = request.getParameter("salename");
            String status = request.getParameter("status");
            String sale_ID = request.getParameter("saleid");
            Map<String, String> list1 = new LinkedHashMap<>();
            ArrayList<String> list2 = new ArrayList<>();

            if (from.length() != 0 && to.length() != 0) {
                list2.add("o.order_date BETWEEN ? AND ?");
                list1.put("fromDate", from);
                list1.put("toDate", to);
            }
            if(sale_ID != null){
                list2.add("u.UserID = ?");
                list1.put("saleid", sale_ID);
            }
            if (saleName != null && !saleName.equalsIgnoreCase("all")) {
                list2.add("CONCAT(u.first_name, ' ', u.last_name) LIKE ?");
                list1.put("sale", saleName);
            }
            if (!status.equalsIgnoreCase("all")) {
                list2.add("st.Status_Name = ?");
                list1.put("status", status);
            }

            ArrayList<OrderItems> orderItems;
             String all1 = " WHERE ";
            if (list2.isEmpty()) {
                orderItems = d.getOrderInfor();
            } else {
                for (int i = 0; i < list2.size(); i++) {
                    if (i == list2.size() - 1) {
                        all1 += list2.get(i);
                    } else {
                        all1 += list2.get(i) + " AND ";
                    }
                }
                orderItems = d.getOrder(list1, all1);
            }                          
            request.setAttribute("saleid", sale_ID);
            request.setAttribute("sale", d.getSaleName());
            request.setAttribute("list1", orderItems);
            request.setAttribute("status", d.getStatusOrder());
            request.getRequestDispatcher("Views/orderlist.jsp").forward(request, response);
         
//         request.setAttribute("a", all1);
//          request.getRequestDispatcher("Views/newjsp4.jsp").forward(request, response);
        } else {
            doGet(request, response);
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
