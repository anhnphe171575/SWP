/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.CategoryProduct;

import DAL.DAOCategoryProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

/**
 *
 * @author phuan
 */
@MultipartConfig
public class CategoryServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DAOCategoryProduct dao = new DAOCategoryProduct();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllSetting";
        }
         if ("updateCategory".equals(service)) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                // Handle initial form display
                String id = request.getParameter("categoryid");
                int id_raw = Integer.parseInt(id);
                request.setAttribute("Slider", dao.getCategoryProductbyID(id_raw));
                request.getRequestDispatcher("/Views/updateCategory.jsp").forward(request, response);

            } else {
                // Handle form submission
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadFilePath = applicationPath + File.separator + "imgCategory";
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                Part filePart = request.getPart("file");
                String fileUrl = request.getParameter("existingImage");

                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String newFileName = "user_" + System.currentTimeMillis() + fileExtension;
                    File file = new File(uploadFilePath, newFileName);
                    filePart.write(file.getAbsolutePath());
                    fileUrl = "imgCategory" + File.separator + newFileName;
                }

                String id = request.getParameter("category_productID");
                int cateid = Integer.parseInt(id);
                String name = request.getParameter("category_name");
                String description = request.getParameter("category_description");

                // Check for duplicate category names
                boolean isDuplicate = dao.isCategoryNameDuplicate(name);
                if (isDuplicate) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"isDuplicate\": true}");
                    return;
                }

                // Update category
                String sql = "select * from CategoryProduct where category_productID=" + cateid;
                Vector<CategoryProduct> vector = dao.getAll(sql);
                if (vector.size() > 0) {
                    CategoryProduct sl = new CategoryProduct(cateid, name, description, fileUrl);
                    dao.updateCategory(sl);
                    response.sendRedirect("CategoryServletURL");
                } else {
                    request.setAttribute("error", "Slider not found.");
                    request.getRequestDispatcher("/Views/ViewCategory.jsp").forward(request, response);
                }
            }
        } else if ("/checkDuplicateCategory".equals(request.getServletPath())) {
            String categoryName = request.getParameter("category_name");
            boolean isDuplicate = dao.isCategoryNameDuplicate(categoryName);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"isDuplicate\":" + isDuplicate + "}");
        }
            
    
        if (service.equals("addCategory")) {
            String submit = request.getParameter("submit");
            System.out.println(submit);

            if (submit == null) {
                request.getRequestDispatcher("/Views/addCategory.jsp").forward(request, response);
            } else {
                String applicationPath = request.getServletContext().getRealPath("");
                // Construct the path for the upload directory
                String uploadFilePath = applicationPath + File.separator + "imgCategory";

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
                    String newFileName = "user_" + System.currentTimeMillis() + fileExtension;

                    // Create a file path
                    File file = new File(uploadFilePath, newFileName);

                    // Write the file to the specified directory
                    filePart.write(file.getAbsolutePath());

                    // Construct the file URL relative to the web application
                    fileUrl = "imgCategory" + File.separator + newFileName;
                } else {
                    // If no new file is uploaded, use the existing image URL
                    fileUrl = request.getParameter("existingImage");
                }

                String name = request.getParameter("category_name");
                String description = request.getParameter("category_description");
                CategoryProduct sl = new CategoryProduct(-1, name, description, fileUrl);
                dao.addCategory(sl);
                response.sendRedirect("CategoryServletURL");
            }
        }else if ("/checkDuplicateCategory".equals(request.getServletPath())) {
            String categoryName = request.getParameter("category_name");
            boolean isDuplicate = dao.isCategoryNameDuplicate(categoryName);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"isDuplicate\":" + isDuplicate + "}");
        }

        if (service.equals(
                "listAllSetting")) {
            String submit = request.getParameter("submit");
            Vector<CategoryProduct> vector = new Vector<>();
            if (submit == null) {
                vector = dao.getAll("select * from CategoryProduct");
            } else {
                String value = request.getParameter("category_name");
                vector = dao.getAll("select * from CategoryProduct where value like'%" + value + "%'");
            }
            request.setAttribute("listAllSetting", vector);
            request.getRequestDispatcher("/Views/ViewCategory.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
