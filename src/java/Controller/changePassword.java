/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAL.DAOSecurityQuestion;
import DAL.DAOCustomer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Entity.Customer;


/**
 *
 * @author Nitro
 */
public class changePassword extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changePassword</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changePassword at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DAOSecurityQuestion dao = new DAOSecurityQuestion();
        request.setAttribute("security_question", dao.getAll("select * from SecurityQuestion"));
      request.getRequestDispatcher("Views/changePassword.jsp").forward(request, response);
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
     String username = request.getParameter("username");
     String opassword = request.getParameter("opassword");
     String npassword = request.getParameter("npassword");
     String rpassword = request.getParameter("rpassword");
     String securityAnswer = request.getParameter("securityAnswer");
     String securityid = request.getParameter("security_question");
     int id = Integer.parseInt(securityid);
        DAOCustomer d = new DAOCustomer();
        Customer c = d.check(username,opassword);
        
        if( !npassword.equals(rpassword)){
            String ms ="repassword is wrong";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("Views/changePassword.jsp").forward(request, response);
                    }   
        else if(opassword.isEmpty() || npassword.isEmpty() || rpassword.isEmpty() ){
           String ms ="please input in the blank";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("Views/changePassword.jsp").forward(request, response);
        }
        else if(!c.getPassword().equals(opassword)){
            String ms ="Current password is wrong";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("Views/changePassword.jsp").forward(request, response);
        }
        else if(d.checkAnswer(id, securityAnswer, username, opassword) == null) {
            
            request.setAttribute("ms", "SQ wrong");
            request.getRequestDispatcher("Views/changePassword.jsp").forward(request, response);
        }
        else{ 
            d.change(username,npassword);
            HttpSession session= request.getSession();
            session.setAttribute("acc", c);
            response.sendRedirect("Views/ChangeSuccess.jsp");
        }
     
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
