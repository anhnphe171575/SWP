/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAOSlider;
import DAL.DAOStaff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import Entity.Slider;
import java.time.ZoneId;
import Entity.Staff;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Nguyễn Đăng
 */
@MultipartConfig
public class SliderServlet extends HttpServlet {

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
        DAOSlider dao = new DAOSlider();
        DAOStaff daoU = new DAOStaff();
        HttpSession session = request.getSession();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllSlider";
        }
        if (service.equals("viewDetailsSlider")) {
            String submit = request.getParameter("submit");
            Vector<Slider> vector = null;
            if (submit == null) {
                request.setAttribute("sliderDetail", dao.getBySliderID(Integer.parseInt(request.getParameter("sliderid"))));
                request.setAttribute("list", vector);
                //request.setAttribute("viewDetailsSlider", vector);
                request.getRequestDispatcher("/Views/ViewDetailsSlider.jsp").forward(request, response);
            }
        }
        if (service.equals("status")) {
            String slideridd = request.getParameter("sliderID");
            String statuss = request.getParameter("status");
            try {
                int st = Integer.parseInt(statuss);
                int sli = Integer.parseInt(slideridd);
                dao.hideShow(sli, st);
                response.sendRedirect("SliderServletURL");
            } catch (Exception e) {

            }
        }
        if (service.equals("addSlider")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                DAOStaff daoo = new DAOStaff();
                request.setAttribute("Staff", daoo.getStaff("select * from Staff"));

                request.getRequestDispatcher("/Views/addSlider.jsp").forward(request, response);
            } else {
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

                String title2 = request.getParameter("title");
                String image2 = request.getParameter("image");
                String link2 = request.getParameter("link");
                String status2 = request.getParameter("status");
               
                
                String username = (String) session.getAttribute("username");
                Staff u = daoU.getStaffByLogin(username);
                String note2 = request.getParameter("notes");
                String page_order = request.getParameter("page_order");
                LocalDate localDate = LocalDate.now();
                Date sliderdatecreateby = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                int status3 = Integer.parseInt(status2);
                int pagrorder = Integer.parseInt(page_order);
                
                Slider sl = new Slider(-1, title2, fileUrl, link2, status3, note2, u, pagrorder, sliderdatecreateby);
                dao.addSlider(sl);
                response.sendRedirect("SliderServletURL");
            }
        }
        if (service.equals("filter")) {
            String statusno = request.getParameter("status");
            int statuss = Integer.parseInt(statusno);
            Vector<Slider> list = new Vector<>();
            DAOSlider db = new DAOSlider();
            if (!statusno.equalsIgnoreCase("3")) {
                String status = " where s.Status = " + statuss;
                list = db.getByStatus(status);
            } else {
                list = db.getByStatus("");
            }
            request.setAttribute("listAllSlider", list);
            request.getRequestDispatcher("/Views/SliderList.jsp").forward(request, response);
        }
        if (service.equals("updateSlider")) {
            String submit = request.getParameter("submit");
            DAOSlider db = new DAOSlider();
            if (submit == null) {
                int id_raw = 0;
                try {
                    String id = request.getParameter("sliderid");
                    id_raw = Integer.parseInt(id);

                } catch (Exception e) {
                    request.getRequestDispatcher("SliderServletURL").forward(request, response);
                }
                request.setAttribute("Slider", db.getBySliderID(id_raw));
                request.getRequestDispatcher("/Views/EditSlider.jsp").forward(request, response);
            } else {
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
                String sliderid2 = request.getParameter("sliderID");
                String title2 = request.getParameter("title");
                String image2 = request.getParameter("image");
                String link2 = request.getParameter("link");
                String status2 = request.getParameter("status");
                String note2 = request.getParameter("notes");
                String page_order = request.getParameter("page_order");
                LocalDate localDate = LocalDate.now();
                Date sliderdatecreateby = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                int sliderid11 = Integer.parseInt(sliderid2);
                int status3 = Integer.parseInt(status2);
                int pagrorder = Integer.parseInt(page_order);
                String sql = "select * from slider where sliderID=" + sliderid2;
                Vector<Slider> vector = dao.getSlider(sql);
                if (vector.size() > 0) {
                    Slider sl = new Slider(sliderid11, title2, fileUrl, link2, status3, note2, null, pagrorder, sliderdatecreateby);
                    dao.updateSlider(sl);
                    response.sendRedirect("SliderServletURL");
                } else {
                    request.setAttribute("error", "error");
                    request.getRequestDispatcher("/Views/SliderList.jsp").forward(request, response);
                }
            }
        }
        if (service.equals(
                "listAllSlider")) {
            String submit = request.getParameter("submit");
            Vector<Slider> vector = null;
            if (submit == null) {
                vector = dao.getSlider("select s.sliderID, s.title, s.image, s.link, s.status, s.notes, s.page_order, s.slider_date_createby, us.StaffID from Slider s\n"
                        + "inner join Staff us on us.StaffID = s.StaffID");
            } else {
                String title = request.getParameter("title");
                vector = dao.getSlider("select s.sliderID, s.title, s.image, s.link, s.status, s.notes, s.page_order, s.slider_date_createby, us.StaffID from Slider s\n"
                        + "inner join Staff us on us.StaffID = s.StaffID where title like'%" + title + "%'");
            }

            request.setAttribute("listAllSlider", vector);
            request.getRequestDispatcher("/Views/SliderList.jsp").forward(request, response);
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
//        DAOSlider dao = new DAOSlider();
//        Vector<Slider> vector = dao.getSlider("select*from slider");
//        request.setAttribute("listAllSlider", vector);
//        request.getRequestDispatcher("/Views/ViewSlider.jsp").forward(request, response);
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
