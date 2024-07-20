/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAL.DAORole;
import DAL.DAOSetting;
import Entity.Role;
import Entity.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Nguyễn Đăng
 */
public class editRole extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAORole dao = new DAORole();
        DAOSetting db = new DAOSetting();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllCustomer";
        }
        if (service.equals("updateRole")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int customerId = Integer.parseInt(request.getParameter("roleid"));
                request.setAttribute("customer", dao.getRoleID(customerId));
                request.getRequestDispatcher("/Views/updateRole.jsp").forward(request, response);
            } else {
                String customerID = request.getParameter("RoleID");
                String fname = request.getParameter("Role_Name");
                String fname0 = request.getParameter("status_nameo");
               int customerid = Integer.parseInt(customerID);
                String sql = "select * from Role where RoleID=" + customerID;
                Vector<Role> vector = dao.getRole(sql);
                if (vector.size() > 0) {
                     SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
                     LocalDate localDate = LocalDate.now();
                     Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Role status = new Role(customerid, fname);
                    Setting sl = new Setting(-1, "Edit Role",fname0 + "=>"+ fname, date_create_by, 1);
                    db.addSetting(sl);
                    dao.updateRole(status);
                    response.sendRedirect("editRoleURL");

                }
            }
        }
        if (service.equals("listAllCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Role> vector = null;
            if (submit == null) {
                vector = dao.getRole("select * from Role");
            } 
        
       

        request.setAttribute("listAllCustomer", vector);
        request.getRequestDispatcher("/Views/ViewRole.jsp").forward(request, response);

    }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
