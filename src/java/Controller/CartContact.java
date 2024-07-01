/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCart;
import DAL.DAOOrder;
import DAL.DAOProduct;
import DAL.DAOReceiver;
import Entity.CartItems;
import Entity.Customer;
import Entity.Order;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author phuan
 */
public class CartContact extends HttpServlet {

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
            out.println("<title>Servlet CartContact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartContact at " + request.getContextPath() + "</h1>");
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
        DAOReceiver db1 = new DAOReceiver();
        HttpSession session = request.getSession(true);
        String[] carItemIDsStr;
        String[] cartIDsStr;
        String receiverId;
         carItemIDsStr = request.getParameterValues("carItemIDs");
            cartIDsStr = request.getParameterValues("cartIDs");
            receiverId = request.getParameter("selectedAddress");
        if (carItemIDsStr == null  && cartIDsStr == null) {
            carItemIDsStr = (String[]) session.getAttribute("carItemIDs1");
             receiverId = (String) session.getAttribute("rid1");
             cartIDsStr = (String[]) session.getAttribute("cartIDs1");     
        } 
        session.setAttribute("carItemIDs1", carItemIDsStr);
        session.setAttribute("rid1", receiverId);
        session.setAttribute("cartIDs1", cartIDsStr);
        session.setMaxInactiveInterval(15*60);
        List<Integer> carItemIDs = new ArrayList<>();
        List<Integer> cartIDs = new ArrayList<>();
        if (carItemIDsStr != null && cartIDsStr != null) {
            for (String carItemIDStr : carItemIDsStr) {
                List<String> items = Arrays.asList(carItemIDStr.split("\\s*,\\s*"));
                for (String item : items) {
                    carItemIDs.add(Integer.parseInt(item));
                }
            }
            for (String cartIDStr : cartIDsStr) {
                List<String> items = Arrays.asList(cartIDStr.split("\\s*,\\s*"));
                for (String item : items) {
                    cartIDs.add(Integer.parseInt(item));
                }
            }
        }

        // Xử lý dữ liệu
        List<CartItems> list1 = new ArrayList<>();
        if (!carItemIDs.isEmpty() && !cartIDs.isEmpty()) {
            DAOCart db = new DAOCart();
            for (int i = 0; i < Math.min(carItemIDs.size(), cartIDs.size()); i++) {
                list1.add(db.getCartItemsSelect(cartIDs.get(i), carItemIDs.get(i)));
            }
        }

        Customer cus = (Customer) session.getAttribute("cus");

        int check = 1;
        DAOReceiver db = new DAOReceiver();
        if (receiverId != null) {
            check = Integer.parseInt(receiverId);
            request.setAttribute("info", db.getReceiverByID(cus.getCustomerID(), check));
        } else {
            request.setAttribute("info", db.getReceiverByID(cus.getCustomerID(), check));
        }
        if (request.getParameter("scroll") != null) {
            request.setAttribute("scroll", "scroll");
        }
        request.setAttribute("check", check);
        request.setAttribute("carItemIDs", carItemIDsStr);
        request.setAttribute("cartIDs", cartIDsStr);
        request.setAttribute("list1", db1.getReceiver(cus.getCustomerID()));
        request.setAttribute("list", list1);
        request.getRequestDispatcher("Views/CartContact.jsp").forward(request, response);
       
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
        HttpSession session = request.getSession(true);
        String paymentMethod = request.getParameter("paymentMethod");
        String total = request.getParameter("total");
        String notes = request.getParameter("notes");
        String RecieverID = request.getParameter("recieverid");
        String[] productIDs = request.getParameterValues("productID");
        String[] productNames = request.getParameterValues("productName");
        String[] productPrices = request.getParameterValues("productPrice");
        String[] productQuantities = request.getParameterValues("productQuantity");
        String[] productCosts = request.getParameterValues("productCost");
        int result = (int) Math.round(Double.parseDouble(total));
        if (paymentMethod != null && paymentMethod.equals("vnpay")) {
            request.setAttribute("total", result);
            session.setAttribute("notes", notes);
            session.setAttribute("RecieverID", RecieverID);
            session.setAttribute("productIDs", new ArrayList<>(Arrays.asList(productIDs)));
            session.setAttribute("productNames", new ArrayList<>(Arrays.asList(productNames)));
            session.setAttribute("productPrices", new ArrayList<>(Arrays.asList(productPrices)));
            session.setAttribute("productQuantities", new ArrayList<>(Arrays.asList(productQuantities)));
            session.setAttribute("productCosts", new ArrayList<>(Arrays.asList(productCosts)));
            session.setMaxInactiveInterval(15 * 60);
            request.getRequestDispatcher("Views/vnpay_pay.jsp").forward(request, response);
        } else {
            Order o = new Order();
            DAOOrder db = new DAOOrder();
            DAOCart db1 = new DAOCart();
            Customer cus = (Customer) session.getAttribute("cus");
            int cus_id = cus.getCustomerID();
            int user_id = o.Salesdivision();
            LocalDate localDate = LocalDate.now();

            // Chuyển đổi LocalDate thành Date
            Date order_date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Cộng thêm 3 ngày vào order_date để tính shipped_date
            LocalDate shippedLocalDate = localDate.plusDays(3);
            Date shipped_date = Date.from(shippedLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<CartItems> list = (List<CartItems>) session.getAttribute("cart");
            ArrayList<Integer> orderList = db.getOrder1();
            int maxElement = 1;
            // Kiểm tra danh sách không rỗng
            if (orderList != null && !orderList.isEmpty()) {
                // Tìm phần tử lớn nhất
                maxElement = Collections.max(orderList);

                // In ra phần tử lớn nhất
                System.out.println("Phần tử lớn nhất là: " + maxElement);
            }
            db.addOrder(maxElement + 1, 1, cus_id, shipped_date, order_date, user_id, Integer.parseInt(RecieverID), notes, paymentMethod);
            for (int i = 0; i < productIDs.length; i++) {
                String productID = productIDs[i];
                String productPrice = productPrices[i];
                String productQuantity = productQuantities[i];
                for (int j = 0; j < list.size(); j++) {
                    if (Integer.parseInt(productID) == list.get(i).getProduct().getProductID()) {
                        db1.DeleteCardItems(list.get(0).getCart().getCartID(), list.get(i).getCarItemID());
                    }
                }

                db.addOrderItems(i + 1, maxElement + 1, Integer.parseInt(productID), Double.parseDouble(productPrice), Integer.parseInt(productQuantity));

            }
            if (db1.getCartItemsByCartID(list.get(0).getCart().getCartID()).isEmpty()) {
                db1.DeleteCard(list.get(0).getCart().getCartID());
            } else {
                for (int i = 0; i < db1.getCartItemsByCartID(list.get(0).getCart().getCartID()).size(); i++) {
                    db1.UpdateCartItemID(list.get(0).getCart().getCartID(), i + 1, db1.getCartItemsByCartID(list.get(0).getCart().getCartID()).get(i).getProduct().getProductID());
                }
            }
            DAOProduct db2 = new DAOProduct();
            for (int i = 0; i < db.getOrderByOrderID(maxElement + 1).size(); i++) {
                Product p = db2.getProductByID(db.getOrderByOrderID(maxElement + 1).get(i).getProduct().getProductID());
                int quantity = p.getQuantity_hold() + db.getOrderByOrderID(maxElement + 1).get(i).getQuantity();
                 System.out.println("quantity order:" + db.getOrderByOrderID(maxElement + 1).get(i).getQuantity());
                    System.out.println("quantity" +quantity);
                db2.UpdateQuantityHold(quantity, p.getProductID());
            }
            request.getRequestDispatcher("Views/OrderSuccess.jsp").forward(request, response);
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
