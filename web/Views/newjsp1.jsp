<%-- 
    Document   : newjsp1
    Created on : May 30, 2024, 9:34:58 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="myForm" action="NewServlet" method="post">
            <input type="checkbox" name="checkbox" value="value1"> Checkbox 1<br>
            <input type="checkbox" name="checkbox" value="value2"> Checkbox 2<br>
        </form>
   
        <script>
            document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
                checkbox.addEventListener('change', function() {
                    document.getElementById('myForm').submit();
                });
            });
        </script>
    </body>
</html>
