/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOOrder;
import DAL.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Nguyễn Đăng
 */
public class SaleDashboard extends HttpServlet {

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
        DAOOrder dao = new DAOOrder();
        DAOUser dao1 = new DAOUser();

        String service = request.getParameter("service");
        if (service != null) {
            if (service.equals("date")) {
                String dateinput = (String) request.getParameter("dateinput");
                LocalDate localDate1 = LocalDate.now();
                Date date_create_by1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = formatter1.format(date_create_by1);
                request.setAttribute("date", date1);
                request.setAttribute("dataOrder", dao.trend7dayTotalOrder(dateinput));
                request.setAttribute("dataSuccess", dao.trend7dayTotalSuccess());
                request.setAttribute("dataReject", dao.trend7dayTotalReject());
                request.setAttribute("dataPacking", dao.trend7dayTotalPacking());
                request.setAttribute("dataDelivering", dao.trend7dayTotalDelivering());
                request.setAttribute("dataSubmit", dao.trend7dayTotalSubmit());
                request.setAttribute("dataFail", dao.trend7dayTotalFail());
                request.setAttribute("dataDone", dao.trend7dayTotalDone());

                request.setAttribute("total", dao.TotalOrder());
                request.setAttribute("sale", dao1.getUser("select * from [User] where RoleID = 4"));
                request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
            } else {
                String saleid = request.getParameter("sale");
                String startdate = request.getParameter("startdate");
                String enddate = request.getParameter("enddate");
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = formatter.format(date_create_by);
                if (saleid != null) {
                    request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataReject1", dao.trend7dayTotalReject1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataPacking1", dao.trend7dayTotalPacking1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataDelivering1", dao.trend7dayTotalDelivering1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataSubmit1", dao.trend7dayTotalSubmit1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataFail1", dao.trend7dayTotalFail1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataDone1", dao.trend7dayTotalDone1(Integer.parseInt(saleid), startdate, enddate));
                } else {

                    request.setAttribute("dataOrder1", dao.trend7dayTotalOrder(date));
                    //request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess());
                    request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataReject1", dao.trend7dayTotalReject1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataPacking1", dao.trend7dayTotalPacking1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataDelivering1", dao.trend7dayTotalDelivering1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataSubmit1", dao.trend7dayTotalSubmit1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataFail1", dao.trend7dayTotalFail1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("dataDone1", dao.trend7dayTotalDone1(Integer.parseInt(saleid), startdate, enddate));
                    request.setAttribute("total1", dao.TotalOrder());
                }
                request.setAttribute("sale", dao1.getUser("select * from [User] where RoleID = 4"));

                request.setAttribute("date", date);
                //request.setAttribute("dataOrder", dao.trend7dayTotalOrder(date));
                request.setAttribute("dataOrder", dao.trend7dayTotalOrder(date));
                request.setAttribute("dataSuccess", dao.trend7dayTotalSuccess());
                request.setAttribute("dataReject", dao.trend7dayTotalReject());
                request.setAttribute("dataPacking", dao.trend7dayTotalPacking());
                request.setAttribute("dataDelivering", dao.trend7dayTotalDelivering());
                request.setAttribute("dataSubmit", dao.trend7dayTotalSubmit());
                request.setAttribute("dataFail", dao.trend7dayTotalFail());
                request.setAttribute("dataDone", dao.trend7dayTotalDone());
                request.setAttribute("total", dao.TotalOrder());
                request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
            }
        } else {
//            String saleid1 = request.getParameter("sale");
//            int saleid = Integer.parseInt(saleid1);
            int saleid = dao1.getUser("select * from [User] where RoleID = 4").get(0).getUserID();
            //String saleid = request.getParameter(dao1.getUser("select * from [User] where RoleID = 4").get(0).getUserID());

            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");

            LocalDate localDate = LocalDate.now();
            enddate = localDate.toString();

// Set startdate to 7 days before the current date
            startdate = localDate.minusDays(7).toString();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(date_create_by);

            request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(saleid, startdate, enddate));
            request.setAttribute("dataReject1", dao.trend7dayTotalReject1(saleid, startdate, enddate));
            request.setAttribute("dataPacking1", dao.trend7dayTotalPacking1(saleid, startdate, enddate));
            request.setAttribute("dataDelivering1", dao.trend7dayTotalDelivering1(saleid, startdate, enddate));
            request.setAttribute("dataSubmit1", dao.trend7dayTotalSubmit1(saleid, startdate, enddate));
            request.setAttribute("dataFail1", dao.trend7dayTotalFail1(saleid, startdate, enddate));
            request.setAttribute("dataDone1", dao.trend7dayTotalDone1(saleid, startdate, enddate));
            request.setAttribute("total1", dao.TotalOrder1(saleid, startdate, enddate));
            request.setAttribute("dataOrder1", dao.trend7dayTotalOrderBySale(saleid, startdate, enddate));
//            request.setAttribute("dataOrder1", dao.trend7dayTotalOrderBySale(2, "2024-06-01", "2024-06-07"));
//            request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(2, "2024-06-01", "2024-06-07"));
//            request.setAttribute("dataCancelled1", dao.trend7dayTotalCancelled1(2, "2024-06-01", "2024-06-07"));
//            request.setAttribute("daoPending1", dao.trend7dayTotalPending1(2, date, date));
//            request.setAttribute("dataOrder1", dao.trend7dayTotalOrder(date));
//            request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess());
//            request.setAttribute("dataCancelled1", dao.trend7dayTotalCancelled());
//            request.setAttribute("daoPending1", dao.trend7dayTotalPending());
            //request.setAttribute("total1", dao.TotalOrder());
            request.setAttribute("sale", dao1.getUser("select * from [User] where RoleID = 4"));
            request.setAttribute("date", date);
            request.setAttribute("dataOrder", dao.trend7dayTotalOrder(date));
            //request.setAttribute("dataOrder", dao.trend7dayTotalOrder(dateinput));
            request.setAttribute("dataSuccess", dao.trend7dayTotalSuccess());
            request.setAttribute("dataReject", dao.trend7dayTotalReject());
            request.setAttribute("dataPacking", dao.trend7dayTotalPacking());
            request.setAttribute("dataDelivering", dao.trend7dayTotalDelivering());
            request.setAttribute("dataSubmit", dao.trend7dayTotalSubmit());
            request.setAttribute("dataFail", dao.trend7dayTotalFail());
            request.setAttribute("dataDone", dao.trend7dayTotalDone());
            request.setAttribute("total", dao.TotalOrder());
            request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
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
        processRequest(request, response);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
