package Controller;

import DAL.DAOAdminDashboard;
import DAL.DAOOrder;
import DAL.DAOStaff;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaleDashboard
 */
public class SaleDashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleDashboard() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        DAOOrder dao = new DAOOrder();
//        DAOUser dao1 = new DAOUser();
//
//        String service = request.getParameter("service");
//        LocalDate localDate = LocalDate.now();
//        Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String date = formatter.format(date_create_by);
//        request.setAttribute("date", date);
//
//        request.setAttribute("sale", dao1.getUser("select * from [User] where RoleID = 2"));
//
//        String saleid = request.getParameter("sale");
//        String startdate = request.getParameter("startdate");
//        String enddate = request.getParameter("enddate");
//        if (service != null && service.equals("date")) {
//            String dateinput = request.getParameter("dateinput");
//
//            request.setAttribute("dataOrder", dao.trend7dayTotalOrder(dateinput));
//            request.setAttribute("dataSuccess", dao.trend7dayTotalSuccess());
//            request.setAttribute("dataReject", dao.trend7dayTotalReject());
//            request.setAttribute("dataPacking", dao.trend7dayTotalPacking());
//            request.setAttribute("dataDelivering", dao.trend7dayTotalDelivering());
//            request.setAttribute("dataSubmit", dao.trend7dayTotalSubmit());
//            request.setAttribute("dataFail", dao.trend7dayTotalFail());
//            request.setAttribute("dataDone", dao.trend7dayTotalDone());
//            request.setAttribute("total", dao.TotalOrder());
//
//           // request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
//        }
//        else{
//              
//        request.setAttribute("dataOrder", dao.trend7dayTotalOrder(date));
//        request.setAttribute("dataSuccess", dao.trend7dayTotalSuccess());
//        request.setAttribute("dataReject", dao.trend7dayTotalReject());
//        request.setAttribute("dataPacking", dao.trend7dayTotalPacking());
//        request.setAttribute("dataDelivering", dao.trend7dayTotalDelivering());
//        request.setAttribute("dataSubmit", dao.trend7dayTotalSubmit());
//        request.setAttribute("dataFail", dao.trend7dayTotalFail());
//        request.setAttribute("dataDone", dao.trend7dayTotalDone());
//        request.setAttribute("total", dao.TotalOrder());
//
//        }
//        
//
//
//        if (saleid != null && startdate != null && enddate != null) {
//            int saleId = Integer.parseInt(saleid);
//
//            request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(saleId, startdate, enddate));
//            request.setAttribute("dataReject1", dao.trend7dayTotalReject1(saleId, startdate, enddate));
//            request.setAttribute("dataPacking1", dao.trend7dayTotalPacking1(saleId, startdate, enddate));
//            request.setAttribute("dataDelivering1", dao.trend7dayTotalDelivering1(saleId, startdate, enddate));
//            request.setAttribute("dataSubmit1", dao.trend7dayTotalSubmit1(saleId, startdate, enddate));
//            request.setAttribute("dataFail1", dao.trend7dayTotalFail1(saleId, startdate, enddate));
//            request.setAttribute("dataDone1", dao.trend7dayTotalDone1(saleId, startdate, enddate));
//            request.setAttribute("total1", dao.TotalOrder1(saleId, startdate, enddate));
//            request.setAttribute("dataOrder1", dao.trend7dayTotalOrderBySale(saleId, startdate, enddate));
//        } else {
//
//            saleid = String.valueOf(dao1.getUser("select * from [User] where RoleID = 2").get(0).getUserID());
//
//            int saleId = Integer.parseInt(saleid);
//            startdate = localDate.minusDays(7).toString();
//            enddate = localDate.toString();
//
//            request.setAttribute("dataSuccess1", dao.trend7dayTotalSuccess1(saleId, startdate, enddate));
//            request.setAttribute("dataReject1", dao.trend7dayTotalReject1(saleId, startdate, enddate));
//            request.setAttribute("dataPacking1", dao.trend7dayTotalPacking1(saleId, startdate, enddate));
//            request.setAttribute("dataDelivering1", dao.trend7dayTotalDelivering1(saleId, startdate, enddate));
//            request.setAttribute("dataSubmit1", dao.trend7dayTotalSubmit1(saleId, startdate, enddate));
//            request.setAttribute("dataFail1", dao.trend7dayTotalFail1(saleId, startdate, enddate));
//            request.setAttribute("dataDone1", dao.trend7dayTotalDone1(saleId, startdate, enddate));
//            request.setAttribute("total1", dao.TotalOrder1(saleId, startdate, enddate));
//            request.setAttribute("dataOrder1", dao.trend7dayTotalOrderBySale(saleId, startdate, enddate));
//        }
//
//        request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAdminDashboard daoAd = new DAOAdminDashboard();
        DAOOrder dao = new DAOOrder();
        DAOStaff dao1 = new DAOStaff();
        request.setAttribute("revenues", daoAd.trendRevenues());
        request.setAttribute("newCus", daoAd.trendCusNew());
        request.setAttribute("sucess", daoAd.trendO_Sucess1());
        request.setAttribute("all", daoAd.trendAll1());
        request.setAttribute("fb", daoAd.trendFeedbackstar());
        request.setAttribute("order_status", daoAd.Order_Status());
        request.setAttribute("total", dao.TotalOrder());

        request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        int month1 = Integer.parseInt(month);
        int year1 = Integer.parseInt(year);

        DAOAdminDashboard daoAd = new DAOAdminDashboard();
        request.setAttribute("revenues", daoAd.trendRevenues());
        request.setAttribute("newCus", daoAd.trendCusNew());
        request.setAttribute("sucess", daoAd.trendO_Sucess2(month1, year1));
        request.setAttribute("all", daoAd.trendAll2(month1, year1));
        request.setAttribute("fb", daoAd.trendFeedbackstar());
        request.setAttribute("order_status", daoAd.Order_Status());

        request.getRequestDispatcher("Views/SaleDashboard.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SaleDashboard servlet for handling sales data and trends.";
    }
}
