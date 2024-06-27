/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOOrder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MANH VINH
 */
public class OrderStatus extends HttpServlet {

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
// response.setContentType("application/json");
//        HttpSession session = request.getSession();
//        
//        String orderID = request.getParameter("statusOrderid"); // Retrieve the orderID parameter from the request
//        System.out.println("Received orderID: " + orderID); // Debugging statement
//        
//        DAOOrder daoOrder = new DAOOrder();
//        List<String> statuses = daoOrder.getStatusById(Integer.parseInt("statusOrderid")); // Pass orderID to the method if needed
//        System.out.println("Fetched statuses: " + statuses); // Debugging statement
//        
//        Gson gson = new Gson();
//        JsonElement element = gson.toJsonTree(statuses);
//        JsonArray jsonArray = element.getAsJsonArray();
//
//        PrintWriter out = response.getWriter();
//        out.print(jsonArray);
//        out.flush();

        response.setContentType("application/json");
        String statusOrderid = request.getParameter("statusOrderid"); 
        int statusid = Integer.parseInt(statusOrderid);
        DAOOrder daoOrder = new DAOOrder();
        List<String> statuses = new ArrayList<>();
        try {
            if (statusid == 1) {
                statuses.add("Submit");
                statuses.add("Reject");
                statuses.add("Approve");

            } else if (statusid == 2) {
                statuses.add("Reject");
                statuses.add("Submit");
            } else if (statusid == 3) {
                statuses.add("Approve");
                statuses.add("Packing");
            } else if (statusid == 4) {
                statuses.add("Packing");
                statuses.add("Delivering");
            } else if (statusid == 5) {
                statuses.add("Delivering");
                statuses.add("Success");
                statuses.add("Fail");
            }
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(statuses);
            JsonArray jsonArray = element.getAsJsonArray();

            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace to server logs
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Internal Server Error\"}");
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
        processRequest(request, response);
    }

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
            out.println("<title>Servlet OrderStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
