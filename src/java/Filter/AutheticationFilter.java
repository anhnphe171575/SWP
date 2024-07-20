/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import Entity.Customer;
import Entity.Role;
import Entity.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author phuan
 */
public class AutheticationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AutheticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AutheticationFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AutheticationFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        String url = req.getServletPath();
//        System.out.println("Requested Resource::" + url);
//
//        HttpSession session = req.getSession();
//        Role role = (Role) session.getAttribute("role");
//        User user = (User) session.getAttribute("user");
//        Customer cus = (Customer) session.getAttribute("cus");
//
//        if (cus == null && (url.endsWith("AddToCart")
//                || url.endsWith("CartDetails")
//                || url.endsWith("CartContact")
//                || url.endsWith("MyOrderURL")
//                || (url.endsWith("CancelOrder")
//                || (url.endsWith("MyOrderDetailURL"))
//                || (url.endsWith("PayAgain"))
//                || (url.endsWith("editProfileCustomerURL"))))) {
//            request.getRequestDispatcher("LoginCusController").forward(request, response);
//            return;
//        }
//        if ((url.endsWith("ProductsListPublic")
//                || (url.endsWith("BlogController"))
//                || (url.endsWith("HomePage"))
//                || (url.endsWith("payments.png"))
//                || (url.endsWith("HomePage"))
//                || (url.endsWith("ProductDetailsPublic"))
//                || (url.endsWith("LoginController"))
//                || (url.endsWith("LoginCusController"))
//                || (url.endsWith("ResetPassword"))
//                || (url.endsWith("NewPassword"))
//                || (url.endsWith("SearchHome"))
//                || (url.endsWith("signup"))
//                || (url.endsWith("verify"))
//                || (url.endsWith("LogOut")))) {
//            session.removeAttribute("user");
//            session.removeAttribute("role");
//
//            System.out.println("aa");
//            chain.doFilter(request, response);
//            return;
//
//        }
//        if (cus != null && ((url.endsWith("CartDetails"))
//                || (url.endsWith("CartContact"))
//                || (url.endsWith("editProfileCustomerURL"))
//                || (url.endsWith("ProductsListPublic"))
//                || (url.endsWith("LogOut"))
//                || (url.endsWith("BlogController"))
//                || (url.endsWith("MyOrderURL"))
//                || (url.endsWith("CancelOrder"))
//                || (url.endsWith("MyOrderDetailURL"))
//                || (url.endsWith("HomePage"))
//                || (url.endsWith("AddToCart"))
//                || (url.endsWith("CartCompletion"))
//                || (url.endsWith("payments.png"))
//                || (url.endsWith("HomePage"))
//                || (url.endsWith("ProductDetailsPublic"))
//                || (url.endsWith("vnpayajax"))
//                || (url.endsWith("BlogDetail"))
//                || (url.endsWith("PayAgain")))) {
//            System.out.println("bb");
//            System.out.println(cus);
//            chain.doFilter(request, response);
//
//        } else if (role != null && user != null) {
//            System.out.println(role.getRoleID());
//            if (role.getRoleID() == 1 && !(url.endsWith("MKTDashboard")
//                    || url.endsWith("DBcus")
//                    || url.endsWith("DBpost")
//                    || url.endsWith("DBpro")
//                    || url.endsWith("DBfeedback")
//                    || url.endsWith("editp")
//                    || url.endsWith("EditPost")
//                    || url.endsWith("editProductDetails")
//                    || url.endsWith("productslist")
//                    || url.endsWith("SliderServletURL")
//                    || url.endsWith("PostController")
//                    || url.endsWith("CustomerServletURL")
//                    || url.endsWith("addp")
//                    || url.endsWith("StatusFeedBack")
//                    || url.endsWith("FeedBackList")
//                    || url.endsWith("update")
//                    || url.endsWith("view")
//                    || url.endsWith("FeedbackDetail")
//                    || url.endsWith("deleteProduct")
//                    || url.endsWith("vncss/vn3.css")
//                    || url.endsWith("editProfileUserURL"))) {
//                System.out.println("cc");
//                request.getRequestDispatcher("MKTDashboard").forward(request, response);
//            } else if (role.getRoleID() == 2 && !(url.endsWith("updatestatusorder")
//                    || url.endsWith("orderlist")
//                    || url.endsWith("orderstatus")
//                    || url.endsWith("orderdetails")
//                    || url.contains("mktcss")
//                    || url.endsWith("LoginController")
//                    || url.endsWith("editProfileUserURL"))) {
//                System.out.println("dd");
//
//                request.getRequestDispatcher("orderlist").forward(request, response);
//
//            }
//            else if (role.getRoleID() == 4 && !(url.endsWith("orderlist")
//                    || url.contains("mktcss")
//                    || url.endsWith("orderdetails")
//                    || url.endsWith("updatestatusorder")
//                    || url.endsWith("orderstatus")
//                    || url.endsWith("LoginController")
//                    || url.endsWith("editProfileUserURL") 
//                    || url.endsWith("productslist") 
//                     || url.endsWith("updateQuantity") 
//                    || url.endsWith("updatePrice")
//                     || url.endsWith("updateSalePrice")
//                    )) {
//                System.out.println("ee");
//
//                request.getRequestDispatcher("orderlist").forward(request, response);
//
//            } 
//            else if (role.getRoleID() == 3 && !(url.endsWith("SaleDashboardURL")
//                    || url.endsWith("orderlist")
//                    || url.contains("mktcss")
//                    || url.endsWith("orderdetails")
//                    || url.endsWith("updatesale")
//                    || url.endsWith("sales")
//                    || url.endsWith("LoginController")
//                    || url.endsWith("editProfileUserURL"))) {
//                System.out.println("gg");
//
//                request.getRequestDispatcher("orderlist").forward(request, response);
//            } 
//            else if (role.getRoleID() == 5
//                    && !(url.endsWith("AdminDashboard")
//                    || url.endsWith("userList")
//                    || url.endsWith("userDetail")
//                    || url.endsWith("AddUser")
//                    || url.endsWith("updateUser")
//                    || url.endsWith("SecurityQuestion")
//                    || url.endsWith("EditSQ")
//                    || url.endsWith("updateUser")
//                    || url.endsWith("editRoleURL")
//                    || url.endsWith("editStatusOrderURL")
//                    || url.endsWith("styles.css")
//                    || url.endsWith("LoginController"))) {
//                System.out.println("ff");
//                request.getRequestDispatcher("LoginController").forward(request, response);
//            } 
//            else //request.getRequestDispatcher("LoginController").forward(request, response);
//            {
//                chain.doFilter(request, response);
//            }
//            return;
//        } else {
          //  request.getRequestDispatcher("LoginController").forward(request, response);
        //}
        chain.doFilter(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AutheticationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AutheticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AutheticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}