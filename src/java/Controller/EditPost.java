/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOUser;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Post;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class EditPost extends HttpServlet {

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
            out.println("<title>Servlet EditPost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPost at " + request.getContextPath() + "</h1>");
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
        DAL.DAOPost dao = new DAOPost();
        int id = Integer.parseInt(request.getParameter("postID"));
        request.setAttribute("post", dao.getPostById(id));
        request.getRequestDispatcher("Views/editpost.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        DAOUser daoU = new DAOUser();
        DAOCategoryProduct daoCPR = new DAOCategoryProduct();
        DAOPost daoP = new DAOPost();
        
        int id = Integer.parseInt(request.getParameter("postID"));
        String title = request.getParameter("title");
        String thumbnail = request.getParameter("thumbnail");
        int category_postID = Integer.parseInt(request.getParameter("category_postID"));
        int featured = Integer.parseInt(request.getParameter("featured"));
        int status = Integer.parseInt(request.getParameter("status"));
        String brief_information = request.getParameter("brief_information");
        String description = request.getParameter("description");
        int flag = Integer.parseInt(request.getParameter("flag"));
          
        String username = (String) session.getAttribute("username");
        User u = daoU.getUserByLogin(username);
        LocalDate localDate = LocalDate.now();
        Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        CategoryPost cp = new CategoryPost(category_postID, daoCPR.getCategoryProductbyID(category_postID));
        Post post = new Post(id, thumbnail, title, cp, featured, status, brief_information, description, flag, u, date_create_by);
        daoP.editPost(post);

        Vector<Post> vec1 = daoP.getAll();
        Vector<Integer> vec4 = daoP.getStatus("select status from Post group by status");
        Vector<String> vec2 = daoP.getAllNameCategory("select category_name from CategoryProduct group by category_name");
        Vector<User> vec3 = daoU.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "u.dob,u.gender,u.status, u.role,u.securityID,u.securityAnswer,s.security_question from [User] u\n"
                + "inner join SecurityQuestion s on u.securityID=s.securityID");

        request.setAttribute("post", vec1);
        request.setAttribute("category", vec2);
        request.setAttribute("user", vec3);
        request.setAttribute("status", vec4);
        request.setAttribute("category_product", daoCPR.getCategoryProductProduct());
        request.getRequestDispatcher("Views/listPost.jsp").forward(request, response);

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
