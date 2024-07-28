<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Xác thực Tài Khoản</title>

        <!-- Inline CSS -->
        <style>
            /* General styles */
            body {
                font-family: 'Open Sans', sans-serif;
                background-color: #f8f9fa;
                color: #333;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            .title {
               
                margin-bottom: 20px;
                color: #007bff;
            }

            .customer-info {
                margin-bottom: 20px;
            }

            .customer-info p {
                margin: 5px 0;
                font-size: 16px;
            }

            .customer-info strong {
                color: #007bff;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
                text-decoration: none;
            }

            .btn--red {
                background-color: #dc3545;
            }

            .btn--red:hover {
                background-color: #c82333;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h2 class="title">Thông Tin Khách Hàng</h2>
            <form method="post" action="verify" class="verify-form">
                <c:if test="${sessionScope.cus != null}">

                    <div class="customer-info">

                        <p><strong>Họ và tên:</strong> ${cus.first_name} ${cus.last_name}</p>
                        
                        <p><strong>Số điện thoại:</strong> ${cus.phone}</p>
                        <p><strong>Email:</strong> ${cus.email}</p>
                        <p><strong>Địa chỉ:</strong> ${cus.address}</p>
                        <p><strong>Tên đăng nhập:</strong> ${cus.username}</p>
                        <p><strong>Ngày sinh:</strong> <fmt:formatDate value="${cus.dob}" pattern="dd/MM/yyyy" /></p>
                        <p><strong>Giới tính:</strong> ${cus.gender ? 'Nam' : 'Nữ'}</p>
                        <p><strong>Ngày đăng ký:</strong> <fmt:formatDate value="${cus.activity_history}" pattern="dd/MM/yyyy" /></p>
                        <p><strong>Câu hỏi bảo mật:</strong> ${cus.security.security_question}</p>
                        <p><strong>Câu trả lời:</strong> ${cus.secutityAnswer}</p>
                    </div>
                    <button type="submit" class="btn btn--red">Xác thực tài khoản</button>

                </c:if>
            </form>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Main JS-->
        <script src="vnjs/global.js"></script>
    </body>

</html>
