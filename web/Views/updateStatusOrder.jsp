<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Vector,Entity.Customer"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Status</title>
    <link rel="stylesheet" href="vncss/vn5.css"/>
</head>
<body>
    <div class="container">
        <h2>Edit Status</h2>
        <form action="editStatusOrderURL" method="post">
            <div class="form-group">
                <label>Status ID:</label>
                <input type="text" name="status_orderid" value="${customer.status_orderid}" required readonly>
            </div>

            <div class="form-group">
                <label>Status Name:</label>
                <input type="text" name="status_name" value="${customer.status_name}" class="form-control" required>
            </div>

            
            <input type="submit" name="submit" value="Save">
            <input type="hidden" name="service" value="updateStatusOrder">
        </form>
    </div>
</body>
</html>
