package Filter;

import Entity.Customer;
import Entity.Role;
import Entity.Staff;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AutheticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AutheticationFilter.class.getName());
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public AutheticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AutheticationFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AutheticationFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (url.endsWith(".scss") || url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".gif")) {
            chain.doFilter(request, response);
            return;
        }

        if (url.toLowerCase().endsWith(".jsp")) {
            req.getRequestDispatcher("/AccsessDenied").forward(request, response);
            return;
        }

        System.out.println("Requested Resource::" + url);
        log("Requested Resource::" + url);
        HttpSession session = req.getSession();
        Role role = (Role) session.getAttribute("role");
        Staff user = (Staff) session.getAttribute("user");
        Customer cus = (Customer) session.getAttribute("cus");

        String[] customerUrls = {"AddToCart", "CartDetails", "CartContact", "MyOrderURL", "CancelOrder", "ChangePassword", "MyOrderDetailURL", "PayAgain", "editProfileCustomerURL"};
        String[] publicUrls = {"ProductsListPublic", "EditReceive", "BlogDetail", "BlogController", "HomePage", "payments.png", "ProductDetailsPublic", "LoginController", "LoginCusController", "ResetPassword", "NewPassword", "SearchHome", "signup", "verify", "LogOut"};
        String[] customerAuthUrls = {"CartDetails", "ChangePassword", "CartContact", "editProfileCustomerURL", "ProductsListPublic", "LogOut", "BlogController", "MyOrderURL", "CancelOrder", "MyOrderDetailURL", "HomePage", "AddToCart", "CartCompletion", "payments.png", "ProductDetailsPublic", "vnpayajax", "BlogDetail", "PayAgain"};

        Map<Integer, String[]> roleUrls = new HashMap<>();
        roleUrls.put(1, new String[]{"MKTDashboard", "DBcus", "DBpost", "DBpro", "DBfeedback", "editp", "EditPost", "editProductDetails", "productslist", "SliderServletURL", "PostController", "CustomerServletURL", "addp", "StatusFeedBack", "FeedBackList", "update", "view", "FeedbackDetail", "deleteProduct", "editProfileUserURL", "PostDetail", "turnfeatured"});
        roleUrls.put(2, new String[]{"updatestatusorder", "orderlist", "orderstatus", "orderdetails", "mktcss", "LoginController", "editProfileUserURL"});
        roleUrls.put(3, new String[]{"SaleDashboardURL", "orderlist", "mktcss", "orderdetails", "updatesale", "sales", "LoginController", "editProfileUserURL"});
        roleUrls.put(4, new String[]{"orderlist", "mktcss", "TransactionURL", "orderdetails", "updatestatusorder", "orderstatus", "LoginController", "editProfileUserURL", "productslist", "updateQuantity", "updatePrice", "updateSalePrice"});
        roleUrls.put(5, new String[]{"AdminDashboard", "editProfileUserURL", "userList", "userDetail", "AddUser", "updateUser", "SecurityQuestion", "EditSQ", "editRoleURL", "editStatusOrderURL", "LoginController","CategoryProductURL", "AdminSettingURL", "CategoryServletURL"});

        if (isUrlInArrays(url, customerUrls, publicUrls, customerAuthUrls, roleUrls) &&
            !url.contains("LoginController") && !url.contains("LoginCusController") &&
            !url.contains("ResetPassword") && !url.contains("verify") &&
            !url.contains("signup") && !url.contains("NewPassword")) {
            session.setAttribute("position", url);
                    System.out.println("position" + url);

            log("position" + url);
        }

        if (url.equals("LoginController") || url.equals("LoginCusController") || url.equals("ResetPassword") || url.equals("verify") || url.equals("signup") || url.equals("NewPassword")) {
            String position = (String) session.getAttribute("position");
            if (position != null && position.length() > 0) {
                res.sendRedirect("/SWP/" + position);
                System.out.println("aaaa");
                return;
            }
        }

        if (cus == null && matchesAny(url, customerUrls)) {
            request.getRequestDispatcher("LoginCusController").forward(request, response);
            return;
        }

        if (matchesAny(url, publicUrls)) {
            session.removeAttribute("user");
            session.removeAttribute("role");
            chain.doFilter(request, response);
            return;
        }

        if (cus != null && matchesAny(url, customerAuthUrls)) {
            chain.doFilter(request, response);
            return;
        }

        if (role != null && user != null) {
            int roleId = role.getRoleID();
            if (roleUrls.containsKey(roleId)) {
                if (!matchesAny(url, roleUrls.get(roleId))) {
                    request.getRequestDispatcher(getRedirectURL(roleId)).forward(request, response);
                    return;
                }
            } else {
                request.getRequestDispatcher("AccsessDenied").forward(request, response);
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        if (req.getRequestDispatcher("/AccsessDenied") != null) {
            req.getRequestDispatcher("/AccsessDenied").forward(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private boolean isUrlInArrays(String url, String[] customerUrls, String[] publicUrls, String[] customerAuthUrls, Map<Integer, String[]> roleUrls) {
        if (matchesAny(url, customerUrls) || matchesAny(url, publicUrls) || matchesAny(url, customerAuthUrls)) {
            return true;
        }
        for (String[] urls : roleUrls.values()) {
            if (matchesAny(url, urls)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesAny(String url, String[] patterns) {
        for (String pattern : patterns) {
            if (url.endsWith(pattern)) {
                return true;
            }
        }
        return false;
    }

    private String getRedirectURL(int roleId) {
        switch (roleId) {
            case 1:
                return "MKTDashboard";
            case 2:
                return "orderlist";
            case 3:
                return "orderlist";
            case 4:
                return "orderlist";
            case 5:
                return "LoginController";
            default:
                return "LoginController";
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AutheticationFilter:Initializing filter");
            }
        }
    }

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
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>");
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