<%-- 
    Document   : OrderFail.jsp
    Created on : Jun 18, 2024, 8:50:07 AM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thanh Toán Thất Bại</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #e74c3c;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #fff;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thanh Toán Thất Bại</h1>
        <p>Giao dịch của bạn không thể hoàn thành do một lỗi nào đó.</p>
        <p>Vui lòng thử lại hoặc liên hệ bộ phận hỗ trợ khách hàng.</p>
        <a href="HomePage">Thử Lại</a>
    </div>
</body>
</html>

