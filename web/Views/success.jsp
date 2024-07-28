<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vn">
    <head>
        <meta charset="UTF-8">
        <title>Đăng Ký Thành Công</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                text-align: center;
            }
            .container h1 {
                color: #4CAF50;
            }
            .container p {
                font-size: 18px;
                margin: 20px 0;
            }
            .back-button {
                text-decoration: none;
                color: #ffffff;
                background-color: #4CAF50;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }
            .back-button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Đăng Ký Thành Công</h1>
            <p>Đăng ký của bạn đã thành công!</p>
            <a href="LoginCusController" class="back-button">Quay lại Trang Chính</a>
        </div>
    </body>
</html>
