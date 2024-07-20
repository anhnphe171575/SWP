/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAL.DAOSetting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Entity.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Nguyễn Đăng
 */
public class AdminSetting extends HttpServlet {
   
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
        DAOSetting dao = new DAOSetting();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllSetting";
        }
        if(service.equals("AdminSettingDetails")) {
            String submit = request.getParameter("submit");
        Vector<Setting> vector = null;
        if (submit == null) {                            
        request.setAttribute("setting", dao.getBySettingID(Integer.parseInt(request.getParameter("settingid"))));
        
        //request.setAttribute("list", vector);
        //request.setAttribute("viewDetailsSlider", vector);
        request.getRequestDispatcher("/Views/AdminSettingDetails.jsp").forward(request, response);
        }
        }
        if(service.equals("sort")) {
            String sort = request.getParameter("sort");
            if ("settingID".equals(sort)) {
                request.setAttribute("listAllSetting", dao.sortByID());
            } else if ("type".equals(sort)) {
                request.setAttribute("listAllSetting", dao.sortByType());
            } else if ("value".equals(sort)) {
                request.setAttribute("listAllSetting", dao.sortByValue());
            } else if ("order".equals(sort)) {
                request.setAttribute("listAllSetting", dao.sortByOrder());
            } else if ("description".equals(sort)) {
                request.setAttribute("listAllSetting", dao.sortByDes());
            } else {
                request.setAttribute("listAllSetting", dao.sortByStatus());
            }
             request.getRequestDispatcher("/Views/AdminSettingList.jsp").forward(request, response);
            
        }
        if (service.equals("filter")) {
            String statusno = request.getParameter("status");
            int statuss = Integer.parseInt(statusno);
             Vector<Setting> list = new Vector<>();
             DAOSetting db = new DAOSetting();
            if (!statusno.equalsIgnoreCase("3")) {
              String status = " where s.Status = " + statuss;
              list = db.getByStatus(status);
            }
            else{
               list = db.getByStatus("");
            }
            request.setAttribute("listAllSetting", list);
             request.getRequestDispatcher("/Views/AdminSettingList.jsp").forward(request, response);
        }
//        if (service.equals("addSetting")) {
//            String submit = request.getParameter("submit");
//            if (submit == null) {
//                request.getRequestDispatcher("/Views/addSetting.jsp").forward(request, response);
//            } else {
//
//               
//                String type = request.getParameter("type");
//                String value = request.getParameter("value");
//                String order = request.getParameter("order");
//                String description = request.getParameter("description");
//                String status = request.getParameter("status");
//                int ty = Integer.parseInt(type);
//                int orderr = Integer.parseInt(order);
//                int sta = Integer.parseInt(status);
//                Setting sl = new Setting(0, ty, value, orderr, description, sta);
//                dao.addSetting(sl);
//                response.sendRedirect("AdminSettingURL");
//            }
//        }
//        if (service.equals("updateSetting")) {
//            String submit = request.getParameter("submit");
//            DAOSetting db = new DAOSetting();
//            if (submit == null) {
//                int id_raw = 0;
//                try {
//                    String id = request.getParameter("settingid");
//                    id_raw = Integer.parseInt(id);
//                } catch (Exception e) {
//                    request.getRequestDispatcher("AdminSettingURL").forward(request, response);
//                }
//                request.setAttribute("Setting", db.getBySettingID(id_raw));
//                request.getRequestDispatcher("/Views/EditSetting.jsp").forward(request, response);
//            } else {
//                String settingID = request.getParameter("settingID");
//                String type = request.getParameter("type");
//                String value = request.getParameter("value");
//                String order = request.getParameter("order");
//                LocalDate localDate = LocalDate.now();
//                Date description = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                String status = request.getParameter("status");
//                int settingid = Integer.parseInt(settingID);
//                int ty = Integer.parseInt(type);
//                
//                int sta = Integer.parseInt(status);
//                String sql = "select * from Setting where settingID=" + settingid;
//                Vector<Setting> vector = dao.getSetting(sql);
//                if (vector.size() > 0) {
//                    Setting sl = new Setting(settingid, ty, value, order, description, sta);
//                    dao.editSetting(sl);
//                    response.sendRedirect("AdminSettingURL");
//                } else {
//                    request.setAttribute("error", "error");
//                    request.getRequestDispatcher("/Views/AdminSettingList.jsp").forward(request, response);
//                }
//            }
//        }
        if (service.equals("status")) {
            String settingID = request.getParameter("settingid");
            String statuss = request.getParameter("status");
            try {
                int st = Integer.parseInt(statuss);
                int sli = Integer.parseInt(settingID);
                dao.hideShow(sli, st);
                response.sendRedirect("AdminSettingURL");
            } catch (Exception e) {

            }
        }
        if (service.equals(
                "listAllSetting")) {
            String submit = request.getParameter("submit");
            Vector<Setting> vector = null;
            if (submit == null) {
                vector = dao.getSetting("select * from Setting");
            } else {
                String value = request.getParameter("value");
                vector = dao.getSetting("select * from Setting where value like'%" + value + "%'");
            }

            request.setAttribute("listAllSetting", vector);
            request.getRequestDispatcher("/Views/AdminSettingList.jsp").forward(request, response);
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
