/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSecurityQuestion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import Entity.Security;

/**
 *
 * @author admin
 */
public class SecurityQuestionController extends HttpServlet {

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
            out.println("<title>Servlet SecurityQuestionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SecurityQuestionController at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("service");
        DAOSecurityQuestion dao = new DAOSecurityQuestion();

        if (service == null) {
            Vector<Security> vec = dao.getAll("select * from SecurityQuestion");
            request.setAttribute("listsq", vec);
            request.getRequestDispatcher("Views/listSecurityQuestion.jsp").forward(request, response);
        } else if (service.equals("delete")) {
            String id = request.getParameter("id");
            int id1 = Integer.parseInt(id);
            dao.removeSecurityQuestion(id1);
            response.sendRedirect("SecurityQuestion");
        }
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
        String service = request.getParameter("service");
        DAOSecurityQuestion dao = new DAOSecurityQuestion();
        Vector<Security> vec = dao.getAll("select * from SecurityQuestion");
//        Vector<SecurityQuestion> vec1 = dao.getAll("select * from SecurityQuestion where security_question like '%"+security_question+"%'");
        if (service.equals("add")) {
            String security_question = request.getParameter("security_question");
            Security obj = new Security(0, security_question);
            dao.addSecurityQuestion(obj);
            response.sendRedirect("SecurityQuestion");
        } else if (service.equals("search")) {
            String title = request.getParameter("title");
            request.setAttribute("listsq", dao.search(title));
            request.getRequestDispatcher("Views/listSecurityQuestion.jsp").forward(request, response);

        } else if (service.equals("edit")) {
            String security_question = request.getParameter("security_question");
            Security obj = new Security(0, security_question);
            dao.addSecurityQuestion(obj);
            response.sendRedirect("SecurityQuestion");
        } else {
            doGet(request, response);

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
