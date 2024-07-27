/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOInventoryTransaction;
import DAL.DAOOrder;
import DAL.DAOProduct;
import Entity.Customer;
import Entity.InventoryTransaction;
import Entity.OrderItems;
import Entity.Product;
import Entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
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
 * @author MANH VINH
 */
public class UpdateStatusOrder extends HttpServlet {

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
            out.println("<title>Servlet UpdateStatusOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStatusOrder at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException, UnsupportedEncodingException {
        DAOOrder d = new DAOOrder();
        HttpSession session = request.getSession();

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        ArrayList<OrderItems> order = d.getOrderByOrderID(orderID);
        int newID = Integer.parseInt(request.getParameter("newStatus"));
        Staff user = (Staff) session.getAttribute("user");
        System.out.println(orderID);
        System.out.println(newID);
        DAOProduct db2 = new DAOProduct();
        DAOInventoryTransaction db3 = new DAOInventoryTransaction();
        if (newID == 2) {
            for (int i = 0; i < d.getOrderByOrderID(orderID).size(); i++) {
                Product p = db2.getProductByID(d.getOrderByOrderID(orderID).get(i).getProduct().getProductID());
                int quantity1 = p.getQuantity_hold() - d.getOrderByOrderID(orderID).get(i).getQuantity();
                db2.UpdateQuantityHold(quantity1, p.getProductID());
            }
        }
        if (newID == 5) {
            for (int i = 0; i < d.getOrderByOrderID(orderID).size(); i++) {
                Product p = db2.getProductByID(d.getOrderByOrderID(orderID).get(i).getProduct().getProductID());
                int quantity = p.getQuantity() - d.getOrderByOrderID(orderID).get(i).getQuantity();
                int quantity1 = p.getQuantity_hold() - d.getOrderByOrderID(orderID).get(i).getQuantity();
                System.out.println("quantity order:" + d.getOrderByOrderID(orderID).get(i).getQuantity());
                System.out.println("quantity" + quantity);
                db2.UpdateQuantity(quantity, p.getProductID());

                db2.UpdateQuantityHold(quantity1, p.getProductID());
            }
        }
        if (newID == 7) {
            for (int i = 0; i < d.getOrderByOrderID(orderID).size(); i++) {
                Product p = db2.getProductByID(d.getOrderByOrderID(orderID).get(i).getProduct().getProductID());
                int quantity = d.getOrderByOrderID(orderID).get(i).getQuantity();
                System.out.println(quantity);
                LocalDate localDate = LocalDate.now();
                Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                db2.UpdateQuantity(p.getQuantity() + quantity,p.getProductID());
                int quantity1 = db2.getProductByID(p.getProductID()).getQuantity();
                String note = request.getParameter("note");
                db3.AddInventoryTransaction(p.getProductID(), orderID, quantity, "Return", user.getStaffID(), date_create_by, note, quantity1);
            }
        }
        if (newID == 6) {
            try {
                sendEmail("Feedback Product", "Thanks for shopping. Please click to the following link to feedback in My Order", order.get(0).getOrder().getCustomer().getEmail());
            } catch (MessagingException ex) {
                Logger.getLogger(UpdateStatusOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        d.updateStatusOrder(newID, orderID);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Cập nhật trạng thái đơn hàng thành công!\"}");
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