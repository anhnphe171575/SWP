package Controller;

import DAL.DAOCart;
import DAL.DAOProduct;
import Entity.CartItems;
import Entity.Customer;
import Entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("cus");
        if (cus == null) {
            response.sendRedirect("LoginCusController");
            return;
        }

        String pid = request.getParameter("pid");
        String quantity = request.getParameter("quantity");
        int pid_raw;
        try {
            pid_raw = Integer.parseInt(pid);
        } catch (NumberFormatException e) {
            response.sendRedirect("HomePage");
            return;
        }

        DAOCart daoCart = new DAOCart();
        DAOProduct daoProduct = new DAOProduct();
        int quantity1 = quantity != null ? Integer.parseInt(quantity) : 1;

        if (daoCart.checkCart(cus.getCustomerID()) == null) {
            daoCart.Add2Cart(cus.getCustomerID());
            daoCart.Add2CartItem(1, daoCart.checkCart(cus.getCustomerID()).getCartID(), pid_raw, quantity1);
            request.setAttribute("message", "New cart created and item added.");
        } else {
            CartItems existingItem = daoCart.chekcProductInCart(pid_raw);
            if (existingItem == null) {
                int cartItemsID = daoCart.getCartItemsByCartID(daoCart.checkCart(cus.getCustomerID()).getCartID()).size() + 1;
                daoCart.Add2CartItem(cartItemsID, daoCart.checkCart(cus.getCustomerID()).getCartID(), pid_raw, quantity1);
                request.setAttribute("message", "New item added to cart.");
            } else {
                Product product = daoProduct.getProductByID(pid_raw);
                int availableQuantity = product.getQuantity() - product.getQuantity_hold();
                int newQuantity = Math.min(existingItem.getQuantity() + quantity1, availableQuantity);
                daoCart.UpdateCartQuantity(daoCart.checkCart(cus.getCustomerID()).getCartID(), newQuantity, pid_raw);
                request.setAttribute("message", "Item quantity updated in cart.");
            }
        }
                  DAOCart db = new DAOCart();

        session.setAttribute("cart", db.getListCart(cus.getCustomerID()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddToCart Servlet";
    }
}
