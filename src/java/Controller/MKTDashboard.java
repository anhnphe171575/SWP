package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAL.DAOMTKDashboard;
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

/**
 *
 * @author admin
 */
public class MKTDashboard extends HttpServlet {

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
        DAOMTKDashboard daoMTK = new DAOMTKDashboard();
        String submit = request.getParameter("submit");
        if (submit != null) {
            String service = request.getParameter("service");
            if (service.equals("date")) {
                String dateinput = (String) request.getParameter("dateinput");
                LocalDate localDate1 = LocalDate.now();
                Date date_create_by1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = formatter1.format(date_create_by1);
                request.setAttribute("date1", dateinput);
                request.setAttribute("date", date1);
                request.setAttribute("dataProduct", daoMTK.trendProduct7day(dateinput));
                request.setAttribute("dataPost", daoMTK.trendPost7day(dateinput));
                request.setAttribute("dataCus", daoMTK.trendCus7day(dateinput));
                request.setAttribute("dataFeedback", daoMTK.trendFeedback7day(dateinput));
                request.getRequestDispatcher("Views/MKTDashboard.jsp").forward(request, response);
            }

        } else {
            LocalDate localDate = LocalDate.now();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(date_create_by);
            request.setAttribute("date", date);
            request.setAttribute("date1", "null");
            request.setAttribute("dataProduct", daoMTK.trendProduct7day(date));
            request.setAttribute("dataPost", daoMTK.trendPost7day(date));
//                request.setAttribute("dataCus", daoMTK.trendCus7day(dateinput));
            request.setAttribute("dataFeedback", daoMTK.trendFeedback7day(date));
            request.getRequestDispatcher("Views/MKTDashboard.jsp").forward(request, response);
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
