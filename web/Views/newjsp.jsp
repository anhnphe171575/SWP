<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Checkbox values received:</h2>
        <ul>
            <%
                String[] checkboxValues = (String[]) request.getAttribute("checkboxValues");
                if (checkboxValues != null) {
                    for (String value : checkboxValues) {
                        out.println("<li>" + value + "</li>");
                    }
                } else {
                    out.println("<li>No checkbox selected</li>");
                }
            %>
        </ul>
    </body>
</html>
