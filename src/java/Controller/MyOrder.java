package Controller;

import DAL.DAOFeedback;
import DAL.DAOOrder;
import Entity.OrderItems;
import Entity.StatusOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MyOrder Servlet Handles the order-related requests and responses.
 */
public class MyOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Sample output can be placed here for debugging
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOOrder d = new DAOOrder();
        String customerid = request.getParameter("customerid");
        String status = request.getParameter("status");
        ArrayList<OrderItems> orderItems;
        List<StatusOrder> st = d.getStatusOrder1();

        if (status == null) {
            orderItems = d.getOrderInforByCustomer(customerid);
        } else {
            orderItems = d.getOrderInforByCustomerByStatus(customerid, status);
        }

        Map<Integer, Integer> quantity = d.getOrderQuantities(orderItems);
        DAOFeedback db1 = new DAOFeedback();
        request.setAttribute("feedback", db1.getFeedBackOrder(Integer.parseInt(customerid)));
        request.setAttribute("status1", status);
        request.setAttribute("customerid", customerid);
        request.setAttribute("sale", d.getSaleName());
        request.setAttribute("list1", orderItems);
        request.setAttribute("quantityOrder", quantity);
        request.setAttribute("status", st);

        // Forward to the JSP
        request.getRequestDispatcher("Views/MyOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOOrder d = new DAOOrder();
        String service = request.getParameter("service");

        if (service == null || service.isEmpty()) {
            doGet(request, response);

        } else if (service.equals("search")) {
            String id = request.getParameter("id");
            String name = request.getParameter("product_name");
            String cus_id = request.getParameter("customerid");
            String status1 = request.getParameter("status1");

            ArrayList<OrderItems> orderItems = new ArrayList<>();
            Map<Integer, Integer> quantity;

            
            if(status1.length() != 0 && id.length() ==0 && name.length()==0)
            {
                 if (status1.length() != 0) {
                    orderItems = d.getOrderInforByCustomerByStatus(cus_id, status1);
                } else {
                   orderItems = d.getOrderInforByCustomer(cus_id);
                }
            }
            else if (status1.length() != 0) {
                if (id != null && !id.isEmpty() && (name == null || name.isEmpty())) {
                    orderItems = d.getOrderByIdByStatus(id, status1, cus_id);
                } else if (name != null && !name.isEmpty() && (id == null || id.isEmpty())) {
                    orderItems = d.getOrderByProductByStatus(status1, name, cus_id);
                }
            } 
            else if (id.length()!=0 || name.length()!=0) {
                if (id != null && !id.isEmpty() && (name == null || name.isEmpty())) {
                    orderItems = d.getOrderById(id);
                } else if (name != null && !name.isEmpty() && (id == null || id.isEmpty())) {
                    orderItems = d.getOrderByProduct(name);
                }
            }
            else{
                 if (status1.length() != 0) {
                    orderItems = d.getOrderInforByCustomerByStatus(cus_id, status1);
                } else {
                   orderItems = d.getOrderInforByCustomer(cus_id);
                }
            }
           

            quantity = d.getOrderQuantities(orderItems);

            request.setAttribute("list1", orderItems);
            request.setAttribute("quantityOrder", quantity);
            request.setAttribute("status1", status1);
            request.setAttribute("customerid", cus_id);
            request.setAttribute("sale", d.getSaleName());
            request.setAttribute("status", d.getStatusOrder1());

            request.getRequestDispatcher("Views/MyOrder.jsp").forward(request, response);

        }
          
    }

    private Date formatDate(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dob);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
