<%-- 
    Document   : OrderSuccess
    Created on : Jun 13, 2024, 9:23:11 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt Hàng Thành Công</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f8f8f8;
            padding: 50px;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        h1 {
            color: #4CAF50;
        }
        p {
            font-size: 18px;
        }
        .button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Đặt Hàng Thành Công</h1>
        <p>Cảm ơn bạn đã mua hàng! Đơn hàng của bạn đã được đặt thành công.</p>
        <a href="HomePage" class="button">Quay lại Trang chủ</a>
    </div>
</body>
</html>

