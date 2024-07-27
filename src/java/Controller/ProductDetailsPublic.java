package Controller;

import DAL.DAOCategoryProduct;
import DAL.DAOFeedback;
import DAL.DAOProduct;
import Entity.CategoryProduct;
import Entity.Customer;
import Entity.Feedback;
import java.io.IOException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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

@MultipartConfig
public class ProductDetailsPublic extends HttpServlet {

 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductDetailsPublic</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailsPublic at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pid_raw = 0;
        DAOProduct db = new DAOProduct();
        DAOFeedback db1 = new DAOFeedback();

        try {
            String pid = request.getParameter("pid");
            pid_raw = Integer.parseInt(pid);
        } catch (Exception e) {
            request.getRequestDispatcher("ProductsListPublic").forward(request, response);
        }
        String activate = request.getParameter("activate");
        String orderid = request.getParameter("orderid");
        CategoryProduct cp = new CategoryProduct();
        DAOCategoryProduct db2 = new DAOCategoryProduct();
        cp = db2.getCategoryProductbyPID(pid_raw);
                request.setAttribute("Cate1", db2.getCategoryProductProduct());

        request.setAttribute("orderid", orderid);
        request.setAttribute("activate", activate);
        request.setAttribute("feedback", db1.getFeedBackByProID(pid_raw));
        request.setAttribute("product", db.getProductByID(pid_raw));
        request.setAttribute("qreview", db1.CountFeedback(pid_raw));
        request.setAttribute("relatedP", db.getProductbyCategoryName(cp.getCategory_name()));
        request.getRequestDispatcher("Views/ProductPublicDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the application's real path
        String applicationPath = request.getServletContext().getRealPath("");
        // Construct the path for the upload directory
        String uploadFilePath = applicationPath + File.separator + "imgfeedback";

        // Create the upload directory if it doesn't exist
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        // Get the file part from the request
        Part filePart = request.getPart("file");

        // Get the original file name
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        int star = Integer.parseInt(request.getParameter("rating"));
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        String comment = request.getParameter("comment");
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("cus");
        int Cusid = cus.getCustomerID();
        int pid = Integer.parseInt(request.getParameter("pid"));
        LocalDate localDate = LocalDate.now();
        Date date_create_by = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        DAOFeedback db = new DAOFeedback();

        // Get the next FeedbackID
        int max = 0;
        for (Feedback fb : db.getFeedBack()) {
            max = Math.max(max, fb.getFeedbackID());
        }
        int newFeedbackId = max + 1;

        // Modify the file name
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."))
                + "_" + newFeedbackId + fileExtension;

        // Create a file path
        File file = new File(uploadFilePath, newFileName);

        // Write the file to the specified directory
        filePart.write(file.getAbsolutePath());

        // Construct the file URL relative to the web application
        String fileUrl = "imgfeedback" + File.separator + newFileName;

        // Insert the feedback with the new file URL
        db.InsertFeedBack(pid, Cusid, orderid, comment, star, fileUrl, 1, date_create_by);

        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
