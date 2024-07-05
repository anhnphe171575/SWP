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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@MultipartConfig
public class EditProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "C:\\Users\\MANH VINH\\OneDrive\\Documents\\GitHub\\SWP\\web\\imgProducts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOProduct d = new DAOProduct();
        DAOCategoryProduct cp = new DAOCategoryProduct();
        int id = Integer.parseInt(request.getParameter("eid"));
        request.setAttribute("product", d.getProductByID(id));
        request.setAttribute("brand", d.getAllBrand());
        request.setAttribute("category", cp.getCategoryProductProduct());
        request.getRequestDispatcher("Views/editp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(UPLOAD_DIR, fileName);
            try {
                filePart.write(file.getAbsolutePath());
            } catch (IOException e) {
                throw new ServletException("Cannot write uploaded file to disk! " + e.getMessage());
            }

            String fileUrl = "imgProducts/" + fileName;
            DAOProduct d = new DAOProduct();
            int id = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int year = Integer.parseInt(request.getParameter("year"));
            int category = Integer.parseInt(request.getParameter("category"));
            String description = request.getParameter("description");
            int featured = Integer.parseInt(request.getParameter("featured"));
//            String image = request.getParameter("image");
            String briefInfo = request.getParameter("briefInfo");
            float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
            float salePrice = Float.parseFloat(request.getParameter("salePrice"));
            String updateDate = request.getParameter("updateDate");
            String brand = request.getParameter("brand");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            Date formattedDate = formatDate(updateDate);
            if (status) {
                d.updateProduct(id, productName, quantity, year, category, description, featured, fileUrl, briefInfo, originalPrice, salePrice, formattedDate, brand, true);
            } else {
                d.updateProduct(id, productName, quantity, year, category, description, featured, fileUrl, briefInfo, originalPrice, salePrice, formattedDate, brand, false);
            }

            response.sendRedirect("productslist");
        } else {
            response.getWriter().write("No file uploaded or file size is zero.");
        }
    }

    private Date formatDate(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dob);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
