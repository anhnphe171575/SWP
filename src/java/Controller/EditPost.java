/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOPost;
import DAL.DAOStaff;
import Entity.CategoryPost;
import Entity.CategoryProduct;
import Entity.Post;
import Entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author admin
 */
@MultipartConfig
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
        DAOCategoryProduct dao2 = new DAOCategoryProduct();
  try {
            // Retrieve and validate postID parameter
            String postIDParam = request.getParameter("postID");
            if (postIDParam == null) {
                throw new IllegalArgumentException("Missing post ID parameter.");
            }
            int id = Integer.parseInt(postIDParam);

            // Retrieve and validate detail parameter
            String detailParam = request.getParameter("detail");
            if (detailParam == null) {
                throw new IllegalArgumentException("Missing detail parameter.");
            }
            int detail = Integer.parseInt(detailParam);

            // Fetch data
            Vector<CategoryProduct> vec5 = dao2.getAll("select * from CategoryProduct");
            request.setAttribute("category_product", vec5);
            request.setAttribute("post", dao.getPostById(id));
            request.setAttribute("detail", detail);

            // Forward to the JSP
            request.getRequestDispatcher("Views/editpost.jsp").forward(request, response);
        }catch (Exception e)  {
            // Handle other exceptions
            request.getRequestDispatcher("/PostController").forward(request, response);
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
        HttpSession session = request.getSession();
        DAOStaff daoU = new DAOStaff();
        DAOCategoryProduct daoCPR = new DAOCategoryProduct();
        DAOPost daoP = new DAOPost();
        String service = request.getParameter("service");
        if (service.equals("editDetail")) {
            int id = Integer.parseInt(request.getParameter("postID"));
            String title = request.getParameter("title");
            String applicationPath = request.getServletContext().getRealPath("");
            // Construct the path for the upload directory
            String uploadFilePath = applicationPath + File.separator + "imgPost";

            // Create the upload directory if it doesn't exist
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            // Get the file part from the request
            Part filePart = request.getPart("file");
            String originalFileName = "";
            String fileUrl = "";

            if (filePart != null && filePart.getSize() > 0) {
                // Get the original file name
                originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Create a new file name
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String newFileName = "customer_" + System.currentTimeMillis() + fileExtension;

                // Create a file path
                File file = new File(uploadFilePath, newFileName);

                // Write the file to the specified directory
                filePart.write(file.getAbsolutePath());

                // Construct the file URL relative to the web application
                fileUrl = "imgPost" + File.separator + newFileName;
            } else {
                // If no new file is uploaded, use the existing image URL
                fileUrl = request.getParameter("existingImage");
            }
            int category_postID = Integer.parseInt(request.getParameter("category_postID"));
            int featured = Integer.parseInt(request.getParameter("featured"));
            int status = Integer.parseInt(request.getParameter("status"));
            String brief_information = request.getParameter("brief_information");
            String description = request.getParameter("description");
            String username = (String) session.getAttribute("username");
            Staff u = daoU.getStaffByLogin(username);
            LocalDate localDate = LocalDate.now();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            CategoryPost cp = new CategoryPost(category_postID, daoCPR.getCategoryProductbyID(category_postID));
            Post post = new Post(id, fileUrl, title, cp, featured, status, brief_information, description, u, date_create_by);
            daoP.editPost(post);

            Vector<Post> vec1 = daoP.getAll();
            Vector<Integer> vec4 = daoP.getStatus("select status from Post group by status");
            Vector<String> vec2 = daoP.getAllNameCategory("select category_name from CategoryProduct group by category_name");
            Vector<Staff> vec3 = daoU.getStaff("select u.StaffID,u.first_name,u.last_name,u.phone,u.email,u.address,u.username,u.password,\n"
                    + "u.dob,u.gender,u.status, u.RoleID,u.securityID,u.image, u.securityAnswer,s.security_question from [Staff] u\n"
                    + "inner join SecurityQuestion s on u.securityID=s.securityID");

            request.setAttribute("post", vec1);
            request.setAttribute("category", vec2);
            request.setAttribute("user", vec3);
            request.setAttribute("status", vec4);
            request.setAttribute("category_product", daoCPR.getCategoryProductProduct());
            request.getRequestDispatcher("PostDetail?service=viewDetail&postID=" + id).forward(request, response);
        } else if (service.equals("editList")) {
             String applicationPath = request.getServletContext().getRealPath("");
            // Construct the path for the upload directory
            String uploadFilePath = applicationPath + File.separator + "imgCustomer";

            // Create the upload directory if it doesn't exist
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            // Get the file part from the request
            Part filePart = request.getPart("file");
            String originalFileName = "";
            String fileUrl = "";

            if (filePart != null && filePart.getSize() > 0) {
                // Get the original file name
                originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Create a new file name
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String newFileName = "customer_" + System.currentTimeMillis() + fileExtension;

                // Create a file path
                File file = new File(uploadFilePath, newFileName);

                // Write the file to the specified directory
                filePart.write(file.getAbsolutePath());

                // Construct the file URL relative to the web application
                fileUrl = "imgCustomer" + File.separator + newFileName;
            } else {
                // If no new file is uploaded, use the existing image URL
                fileUrl = request.getParameter("existingImage");
            }
            int id = Integer.parseInt(request.getParameter("postID"));
            String title = request.getParameter("title");
            String thumbnail = request.getParameter("thumbnail");
            int category_postID = Integer.parseInt(request.getParameter("category_postID"));
            int featured = Integer.parseInt(request.getParameter("featured"));
            int status = Integer.parseInt(request.getParameter("status"));
            String brief_information = request.getParameter("brief_information");
            String description = request.getParameter("description");

            String username = (String) session.getAttribute("username");
            Staff u = daoU.getStaffByLogin(username);
            LocalDate localDate = LocalDate.now();
            Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            CategoryPost cp = new CategoryPost(category_postID, daoCPR.getCategoryProductbyID(category_postID));
            Post post = new Post(id, fileUrl, title, cp, featured, status, brief_information, description, u, date_create_by);
            daoP.editPost(post);

            response.sendRedirect("PostController");

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
