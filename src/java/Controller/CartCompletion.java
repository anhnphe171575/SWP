/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCart;
import DAL.DAOOrder;
import DAL.DAOPost;
import DAL.DAOProduct;
import Entity.CartItems;
import Entity.Customer;
import Entity.Order;
import Entity.OrderItems;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author phuan
 */
@WebServlet
public class CartCompletion extends HttpServlet {

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
            out.println("<title>Servlet CartCompletion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartCompletion at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException, UnsupportedEncodingException {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);

        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_PayDate = request.getParameter("vnp_PayDate");
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
        DAOOrder db = new DAOOrder();

        HttpSession session = request.getSession(true);
        if (session.getAttribute("payagain") == null) {
            if (vnp_TransactionStatus.equals("00")) {
                Order o = new Order();
                DAOCart db1 = new DAOCart();
                Customer cus = (Customer) session.getAttribute("cus");
                int cus_id = cus.getCustomerID();
                int user_id = o.Salesdivision();
                LocalDate localDate = LocalDate.now();
                String RecieverID = (String) session.getAttribute("RecieverID");
                ArrayList<String> productIDs = (ArrayList<String>) session.getAttribute("productIDs");
                ArrayList<String> productPrices = (ArrayList<String>) session.getAttribute("productPrices");
                ArrayList<String> productQuantities = (ArrayList<String>) session.getAttribute("productQuantities");
                ArrayList<String> productCosts = (ArrayList<String>) session.getAttribute("productCosts");
                String notes = (String) session.getAttribute("notes");
                List<CartItems> list = (List<CartItems>) session.getAttribute("cart");
                // Chuyển đổi LocalDate thành Date
                Date order_date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Cộng thêm 3 ngày vào order_date để tính shipped_date
                LocalDate shippedLocalDate = localDate.plusDays(3);
                Date shipped_date = Date.from(shippedLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                ArrayList<Integer> orderList = db.getOrder1();

                int maxElement = 1;
                // Kiểm tra danh sách không rỗng
                if (orderList != null && !orderList.isEmpty()) {
                    // Tìm phần tử lớn nhất
                    maxElement = Collections.max(orderList);

                    // In ra phần tử lớn nhất
                    System.out.println("Phần tử lớn nhất là: " + maxElement);
                }
                db.addOrder(maxElement + 1, 1, cus_id, shipped_date, order_date, user_id, Integer.parseInt(RecieverID), notes, "vnpay");

                for (int i = 0; i < productIDs.size(); i++) {
                    String productID = productIDs.get(i);
                    String productPrice = productPrices.get(i);
                    String productQuantity = productQuantities.get(i);
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
                    int quantity = p.getQuantity() - db.getOrderByOrderID(maxElement + 1).get(i).getQuantity();
                    System.out.println("quantity order:" + db.getOrderByOrderID(maxElement + 1).get(i).getQuantity());
                    System.out.println("quantity" +quantity);
                    db2.UpdateQuantity(quantity, p.getProductID());
                }
                try {
                    sendEmail("Your Order has been Successfully Placed!",
                            "Thank you for your purchase! Your order has been placed successfully.", cus.getEmail());
                } catch (MessagingException ex) {
                    Logger.getLogger(CartCompletion.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getRequestDispatcher("Views/OrderSuccess.jsp").forward(request, response);
            } else {
                Order o = new Order();
                DAOCart db1 = new DAOCart();
                Customer cus = (Customer) session.getAttribute("cus");
                int cus_id = cus.getCustomerID();
                int user_id = o.Salesdivision();
                LocalDate localDate = LocalDate.now();
                String RecieverID = (String) session.getAttribute("RecieverID");
                ArrayList<String> productIDs = (ArrayList<String>) session.getAttribute("productIDs");
                ArrayList<String> productPrices = (ArrayList<String>) session.getAttribute("productPrices");
                ArrayList<String> productQuantities = (ArrayList<String>) session.getAttribute("productQuantities");
                ArrayList<String> productCosts = (ArrayList<String>) session.getAttribute("productCosts");
                String notes = (String) session.getAttribute("notes");
                List<CartItems> list = (List<CartItems>) session.getAttribute("cart");
                // Chuyển đổi LocalDate thành Date
                Date order_date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Cộng thêm 3 ngày vào order_date để tính shipped_date
                LocalDate shippedLocalDate = localDate.plusDays(3);

                ArrayList<Integer> orderList = db.getOrder1();

                int maxElement = 1;
                // Kiểm tra danh sách không rỗng
                if (orderList != null && !orderList.isEmpty()) {
                    // Tìm phần tử lớn nhất
                    maxElement = Collections.max(orderList);

                    // In ra phần tử lớn nhất
                    System.out.println("Phần tử lớn nhất là: " + maxElement);
                }
                db.addOrder1(maxElement + 1, 8, cus_id, order_date, user_id, Integer.parseInt(RecieverID), notes, "vnpay");

                for (int i = 0; i < productIDs.size(); i++) {
                    String productID = productIDs.get(i);
                    String productPrice = productPrices.get(i);
                    String productQuantity = productQuantities.get(i);
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
                    int quantity = p.getQuantity() - db.getOrderByOrderID(maxElement + 1).get(i).getQuantity();
                    db2.UpdateQuantity(quantity, p.getProductID());
                }
                request.getRequestDispatcher("Views/OrderFail.jsp").forward(request, response);
            }
        } else {
            if (vnp_TransactionStatus.equals("00")) {
                LocalDate localDate = LocalDate.now();    
                // Cộng thêm 3 ngày vào order_date để tính shipped_date
                LocalDate shippedLocalDate = localDate.plusDays(3);
               
                String order_id = (String) session.getAttribute("orderid_payagain");
                db.UpdateStatus(Integer.parseInt(order_id), 1,shippedLocalDate);
                session.removeAttribute("payagain");
                request.getRequestDispatcher("Views/OrderSuccess.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("Views/OrderFail.jsp").forward(request, response);
            }
        }
    }

    public void sendEmail(String subject, String body, String to) throws MessagingException, UnsupportedEncodingException {
        final String fromEmail = "anhnphe171575@fpt.edu.vn";
        // Mat khai email cua ban
        final String password = "jull jeex qjzb cdtn";
        // dia chi email nguoi nhan
        final String toEmail = to;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

        msg.setReplyTo(InternetAddress.parse(fromEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
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

//        response.getWriter().write("Dữ liệu đã nhận được từ trang JSP: " + txnRef);
        //request.getRequestDispatcher("Views/newjsp1.jsp").forward(request, response);
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
//        request.setAttribute("vnp_TxnRef", vnp_TxnRef);
//        request.setAttribute("vnp_Amount", vnp_Amount);
//        request.setAttribute("vnp_OrderInfo", vnp_OrderInfo);
//        request.setAttribute("vnp_ResponseCode", vnp_ResponseCode);
//        request.setAttribute("vnp_TransactionNo", vnp_TransactionNo);
//        request.setAttribute("vnp_BankCode", vnp_BankCode);
//        request.setAttribute("vnp_PayDate", vnp_PayDate);
//        request.setAttribute("vnp_TransactionStatus", vnp_TransactionStatus);
}
