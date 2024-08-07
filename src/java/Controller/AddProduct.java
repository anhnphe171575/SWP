/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOProduct;
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
import java.util.List;

/**
 *
 * @author MANH VINH
 */
@MultipartConfig
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        DAOProduct d = new DAOProduct();
        DAOCategoryProduct cp = new DAOCategoryProduct();
        request.setAttribute("brand", d.getAllBrand());
        request.setAttribute("category", cp.getCategoryProductProduct());
        request.getRequestDispatcher("Views/addProduct.jsp").forward(request, response);
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
         String applicationPath = request.getServletContext().getRealPath("");
    // Construct the path for the upload directory
    String uploadFilePath = applicationPath + File.separator + "img";

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
        fileUrl = "img" + File.separator + newFileName;
    } else {
        // If no new file is uploaded, use the existing image URL
        fileUrl = request.getParameter("existingImage");
    }
            String productName = request.getParameter("productName");
            int hold = Integer.parseInt(request.getParameter("hold"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int year = Integer.parseInt(request.getParameter("year"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            String description = request.getParameter("description");
            int featured = Integer.parseInt(request.getParameter("featured"));
            String briefInfo = request.getParameter("briefInfo");
            float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
            float salePrice = Float.parseFloat(request.getParameter("salePrice"));
            String brand = request.getParameter("brand");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            DAOProduct d = new DAOProduct();
            d.addProduct(productName, hold, quantity, year, categoryID, description, featured, fileUrl, briefInfo, originalPrice, salePrice, brand, status);
            request.setAttribute("list", d.getProduct1());
            request.setAttribute("msg", "Add product successfull!");
            request.getRequestDispatcher("Views/productslist.jsp").forward(request, response);
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
