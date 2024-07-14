<%-- 
    Document   : loginCus.jsp
    Created on : May 20, 2024, 6:18:19 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Responsive Login and Signup Form </title>

        <!-- CSS -->
        <link rel="stylesheet" href="loginCus/css/style.css">

        <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>

        <style>

            .body{
            }
            .container{
                background-color: white
            }
        </style>
    </head>
    <body>
        <section class="container forms" style="background-color: #D19C97"">
            <div class="form login">
                <div class="form-content">
                    <header>Đăng Nhập</header>
                    <form action="LoginCusController" method="post">
                        <div class="field input-field">
                            <input type="text" name="username" placeholder="Tên Đăng Nhập" class="input">
                        </div>
                        <div class="field input-field">
                            <input type="password" name="password" placeholder="Mật Khẩu" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>
                        <p style="color: red">${error}</p>
                        <div class="form-link">
                            <a href="ResetPassword?role=1" class="cl" style="color: #D19C97">Quên mật khẩu?</a>
                        </div>
                        <div class="field button-field" >
                            <button style="background-color:  #D19C97">Đăng Nhập</button>
                        </div>
                    </form>
                    <div class="form-link">
                        <span>Bạn chưa có tài khoản? <a href="signup" class="cl" style="color: #D19C97">Đăng Ký</a></span>
                    </div>
                    <div class="form-link">
                        <a href="HomePage" class="forgot-pass" style="color: #D19C97">Quay lại trang chủ</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- JavaScript -->
        <script src="js/script.js"></script>
    </body>
</html>