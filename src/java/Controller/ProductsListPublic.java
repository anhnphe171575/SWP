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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Entity.Product;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author phuan
 */
public class ProductsListPublic extends HttpServlet {

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
            out.println("<title>Servlet ProductsListPublic</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsListPublic at " + request.getContextPath() + "</h1>");
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
        DAOProduct db = new DAOProduct();
        DAOCategoryProduct db1 = new DAOCategoryProduct();
        HttpSession session = request.getSession();
        String service = request.getParameter("service");

        List<Product> listProduct = new ArrayList<>();
        String search = request.getParameter("search");
        String cid = request.getParameter("cid");
        int cid_raw = 0;
        if (cid != null && !cid.isBlank()) {
            try {

                cid_raw = Integer.parseInt(cid);

            } catch (Exception e) {

                request.getRequestDispatcher("HomePage").forward(request, response);
            }
        }
        String cname = request.getParameter("cname");
        String feature = request.getParameter("feature");

        if (cname != null) {
            cid_raw = db1.getCategoryProductbyName(cname).getCategory_productID();
        }
        if ((cid == null || cid.isBlank()) && service == null && (search == null || search.isBlank()) && (cname == null || cname.isBlank()) && feature!= null &&feature.equals("no")
                ) {
            listProduct = db.getProductBySorted();
            request.setAttribute("feature", "no");
        } else if (cid != null && !cid.isBlank() && search != null && !search.isBlank() && feature != null && !feature.isBlank()) {
            if (feature.equals("yes")) {
                listProduct = db.getProductFeatureByTitleByCid(search, cid_raw, 1);

                request.setAttribute("feature", "yes");
            } else {
                listProduct = db.getProductFeatureByTitleByCid(search, cid_raw, 0);
                request.setAttribute("feature", "no");
            }
            request.setAttribute("search1", search);
            request.setAttribute("cid", cid_raw);
        } else if (cid != null && !cid.isBlank() && feature != null && !feature.isBlank()) {
            if (feature.equals("yes")) {
                listProduct = db.getProductFeatureByCid(cid_raw, 1);

                request.setAttribute("feature", "yes");
            } else {
                listProduct = db.getProductFeatureByCid(cid_raw, 0);
                request.setAttribute("feature", "no");
            }
            request.setAttribute("cid", cid_raw);
        } else if (search != null && !search.isBlank() && feature != null && !feature.isBlank()) {
            if (feature.equals("yes")) {
                listProduct = db.getProductFeatureByTitle(search, 1);

                request.setAttribute("feature", "yes");
            } else {
                listProduct = db.getProductFeatureByTitle(search, 0);
                request.setAttribute("feature", "no");
            }
            request.setAttribute("search1", search);
        } else if (cid != null && !cid.isBlank() && search != null && !search.isBlank()) {
            listProduct = db.getProductByTitleByCid(search, cid_raw);
            request.setAttribute("search1", search);
            request.setAttribute("cid", cid_raw);
            request.setAttribute("feature", "no");
        } else if (feature != null && !feature.isBlank()) {
            if (feature.equals("yes")) {
                listProduct = db.getProductFeature();
                request.setAttribute("feature", "yes");
            } else {
                listProduct = db.getProductBySorted();
                request.setAttribute("feature", "no");
            }
        } else if (cid != null && !cid.isBlank()) {
            listProduct = db.getProductbyCategoryID(cid_raw);
            request.setAttribute("cid", cid);
            request.setAttribute("feature", "no");
        } else if (cname != null && !cname.isBlank()) {
            // Assuming you have a method to get products by category name if needed.
            listProduct = db.getProductbyCategoryID(cid_raw);
            request.setAttribute("cid", cid_raw);
            request.setAttribute("feature", "no");
        } else if (search != null && !search.isBlank()) {
            listProduct = db.getProductByTitle(search);
            request.setAttribute("search1", search);
            request.setAttribute("feature", "no");
        } else {
            listProduct = db.getProductBySorted();
            request.setAttribute("feature", "no");
        }

        int page = 0;
        int numberOfPage = 6;
        String xpage = request.getParameter("page");
        int size = listProduct.size();
        int num = (size % numberOfPage == 0 ? (size / numberOfPage) : ((size / numberOfPage) + 1));
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberOfPage;
        end = Math.min(page * numberOfPage, listProduct.size());
        List<Product> list = db.getListByPage(listProduct, start, end);
        request.setAttribute("LatedProducts", db.LastedProduct("SELECT top 5* FROM Product where featured = 1 and quantity > 0 and status = 1 ORDER BY update_date,price"));
        request.setAttribute("Cate1", db1.getCategoryProductProduct());
        request.setAttribute("CategoryB", db.ListCatogoryAndBrand());
        request.setAttribute("numpage", num);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("ListProduct", list);
        request.setAttribute("brand", db.getBrand(1));

        request.getRequestDispatcher("Views/ProductListPublic.jsp").forward(request, response);
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
