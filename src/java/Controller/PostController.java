/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.Vector;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Post;
import Entity.User;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@MultipartConfig
public class PostController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "C:\\Users\\admin\\Downloads\\20t6\\SWP\\web\\imgPost";

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
            out.println("<title>Servlet PostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostController at " + request.getContextPath() + "</h1>");
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
        DAOPost daoP = new DAOPost();
        DAOUser dao1 = new DAOUser();
        DAOCategoryProduct dao2 = new DAOCategoryProduct();

        Vector<Post> vec1 = daoP.getAll();
        //   Vector<Integer> vec4 = dao.getStatus("select status from Post group by status");
        Vector<String> vec2 = daoP.getAllNameCategory("select category_name from CategoryProduct group by category_name");
        Vector<User> vec3 = dao1.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "u.dob,u.gender,u.status, u.RoleID,u.securityID,u.securityAnswer,s.security_question from [User] u\n"
                + "inner join SecurityQuestion s on u.securityID=s.securityID");
        Vector<CategoryProduct> vec5 = dao2.getAll("select * from CategoryProduct");
        Vector<Integer> vec4 = daoP.getStatus("select status from Post group by status");
        int page = 0;
        int numberOfPage = 6;
        String xpage = request.getParameter("page");
        int size = vec1.size();
        int num = (size % numberOfPage == 0 ? (size / numberOfPage) : ((size / numberOfPage) + 1));
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberOfPage;
        end = Math.min(page * numberOfPage, vec1.size());
        Vector<Post> list = daoP.getListByPage(vec1, start, end);
        request.setAttribute("post", list);
        request.setAttribute("numpage", num);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("status", vec4);
        request.setAttribute("category", vec2);
        request.setAttribute("user", vec3);
        request.setAttribute("category_product", vec5);
        request.getRequestDispatcher("Views/listPost.jsp").forward(request, response);
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
        String service = request.getParameter("service");
        DAOPost daoP = new DAOPost();
        DAOUser daoU = new DAOUser();
        DAOCategoryProduct daoCPR = new DAOCategoryProduct();
        Vector<Post> listPost = new Vector<>();
        if (service == null) {
            doGet(request, response);
        } else if (service.equals("search")) {
            String title = request.getParameter("title");
            listPost = daoP.search(title);
        } else if (service.equals("sort")) {
            String sort = request.getParameter("sort");
            listPost = daoP.sort(sort);

        } else if (service.equals("filter")) {
            String category = request.getParameter("category");
            String author = request.getParameter("author");
            String status_raw = request.getParameter("status");
            int status = Integer.parseInt(status_raw);
            ArrayList<String> list = new ArrayList<>();
            Map<String, String> aa1 = new LinkedHashMap<>();
            if (!status_raw.equalsIgnoreCase("3")) {
                list.add("p.Status = ?");
                aa1.put("status", status_raw);
            }
            if (!author.equalsIgnoreCase("all")) {
                list.add("(u.first_name + ' ' + u.last_name) = ?");
                aa1.put("author", author);
            }
            if (!category.equalsIgnoreCase("all")) {
                list.add("cpr.category_name = ?");
                aa1.put("category", category);
            }
            Vector<Post> vector = new Vector<>();
            if (list.isEmpty()) {
                vector = daoP.getAll1(aa1, "");
            } else {
                String all1 = "where ";
                for (int i = 0; i < list.size(); i++) {
                    if (i == list.size() - 1) {
                        all1 += list.get(i);
                    } else {
                        all1 += list.get(i) + " AND ";
                    }
                }
                vector = daoP.getAll1(aa1, all1);
            }
            listPost = vector;

        } else if (service.equals("add")) {
            try {
                handleAdd(request, response, session);
                return;
            } catch (Exception ex) {
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Vector<String> vec2 = daoP.getAllNameCategory("select category_name from CategoryProduct group by category_name");
        Vector<User> vec3 = daoU.getUser("select u.UserID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                + "u.dob,u.gender,u.status, u.RoleID,u.securityID,u.securityAnswer,s.security_question from [User] u\n"
                + "inner join SecurityQuestion s on u.securityID=s.securityID");
        Vector<CategoryProduct> vec5 = daoCPR.getAll("select * from CategoryProduct");
        int page = 0;
        int numberOfPage = 6;
        String xpage = request.getParameter("page");
        int size = listPost.size();
        int num = (size % numberOfPage == 0 ? (size / numberOfPage) : ((size / numberOfPage) + 1));
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberOfPage;
        end = Math.min(page * numberOfPage, listPost.size());
        Vector<Post> list = daoP.getListByPage(listPost, start, end);
        request.setAttribute("post", list);
        request.setAttribute("lastPost", daoP.getLastBlog());
        request.setAttribute("numpage", num);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("category", vec2);
        request.setAttribute("user", vec3);
        request.setAttribute("category_product", vec5);
        request.getRequestDispatcher("Views/listPost.jsp").forward(request, response);

    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        DAOPost daoP = new DAOPost();
        DAOUser daoU = new DAOUser();
        DAOCategoryProduct daoCPR = new DAOCategoryProduct();
        Part filePart = request.getPart("thumbnail");
        // Get the file name
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        // Create a file path
        File file = new File(UPLOAD_DIR, fileName);
        // Write the file to the specified directory
        filePart.write(file.getAbsolutePath());
        String fileUrl = "imgPost/" + fileName;
        String title = request.getParameter("title");
        String category_post_raw = request.getParameter("category_post");
        String featured_raw = request.getParameter("featured");
        String brief_information = request.getParameter("brief_information");
        String description = request.getParameter("description");
        try {
            int category_post_id = Integer.parseInt(category_post_raw);
            int status = 1;
            String username = (String) session.getAttribute("username");
            User u = daoU.getUserByLogin(username);
            LocalDate localDate = LocalDate.now();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int featured = Integer.parseInt(featured_raw);
            CategoryPost cp = new CategoryPost(category_post_id, daoCPR.getCategoryProductbyID(category_post_id));
            Post post = new Post(0, fileUrl, title, cp, featured, status, brief_information, description, u, date_create_by);
            daoP.addPost(post);
            response.sendRedirect("PostController");
        } catch (Exception e) {
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
