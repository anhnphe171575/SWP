<%-- 
    Document   : loginUser
    Created on : May 19, 2024, 4:12:56 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login & Registration Form</title>
        <!---Custom CSS File--->
        <link rel="stylesheet" href="qcss/quynhstyle.css">
    </head>
    <body>
        <div class="container">
            <input type="checkbox" id="check">
            <div class="login form">
                <header>Đăng nhập Nhân Viên</header>
                <form action="LoginController" method="post">
                    <input type="text" name="username" placeholder="Enter your email">
                    <input type="password" name="password" placeholder="Enter your password">
                                <p style="text-align: center; color: red" >${error}</p>
                    <a href="ResetPassword?role=2">Quên Mật Khẩu?</a>
                    <input type="hidden" name="service" value="login">
                    <input type="submit" class="button" value="Login">
                </form>
                
            </div>
                  
        </div>
    </body>
</html>

