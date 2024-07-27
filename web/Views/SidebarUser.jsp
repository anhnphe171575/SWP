<%-- 
    Document   : SidebarUser
    Created on : Jul 26, 2024, 4:09:36 AM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sidebar Đẹp với Thư viện</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .sidebar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #D19C97;
            padding-top: 20px;
            transition: 0.3s;
        }
        .sidebar a {
            padding: 15px 25px;
            text-decoration: none;
            font-size: 18px;
            color: #ecf0f1;
            display: block;
            transition: 0.3s;
        }
        .sidebar a:hover {
            background-color: #34495e;
        }
        .sidebar .logo {
            text-align: center;
            padding: 20px 0;
            color: #ecf0f1;
            font-size: 24px;
            font-weight: bold;
        }
        .sidebar-toggle {
            position: absolute;
            top: 10px;
            right: -40px;
            background-color: #2c3e50;
            color: #ecf0f1;
            border: none;
            padding: 10px;
            cursor: pointer;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
            transition: 0.3s;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="logo">Logo</div>
        <a href="HomePage"><i class="fas fa-home"></i> Trang chủ</a>
        <a href="editProfileCustomerURL"><i class="fas fa-user"></i> Hồ sơ</a>
        <a href="ChangePassword"><i class="fas fa-envelope"></i> Thay đổi mật khẩu</a>
       
    </div>


   
</body>
</html>