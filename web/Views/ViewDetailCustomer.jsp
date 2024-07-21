<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Customer Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            /* Your CSS styles */
        </style>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Thông tin <b>Khách </b></h2>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Khác hàng</th>
                                <th>Thông tin</th>
                            </tr>
                        </thead>
                        <tbody>
    <tr>
        <td>ID</td>
        <td>${listAllCustomer.customerID}</td>
    </tr>
    <tr>
        <td>Họ</td>
        <td>${listAllCustomer.first_name}</td>
    </tr>
    <tr>
        <td>Tên</td>
        <td>${listAllCustomer.last_name}</td>
    </tr>
    <tr>
        <td>Số điện thoại</td>
        <td>${listAllCustomer.phone}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${listAllCustomer.email}</td>
    </tr>
    <tr>
        <td>Địa chỉ</td>
        <td>${listAllCustomer.address}</td>
    </tr>
    <tr>
        <td>Tài khoản</td>
        <td>${listAllCustomer.username}</td>
    </tr>
    <tr>
        <td>Mật khẩu</td>
        <td>${listAllCustomer.password}</td>
    </tr>
    <tr>
        <td>Ngày sinh</td>
        <td>${listAllCustomer.dob}</td>
    </tr>
    <tr>
        <td>Giới tính</td>
        <td>${listAllCustomer.gender ? 'Nam' : 'Nữ'}</td>
    </tr>
    <tr>
        <td>Lịch sử hoạt động</td>
        <td>${listAllCustomer.activity_history}</td>
    </tr>
    <tr>
        <td>Câu hỏi bảo mật</td>
        <td>${listAllCustomer.security.security_question}</td>
    </tr>
    <tr>
        <td>Trả lời</td>
        <td>${listAllCustomer.secutityAnswer}</td>
    </tr>
    <tr>
        <td colspan="2">
            <a href="CustomerServletURL?service=updateCustomer&customerid=${listAllCustomer.customerID}" class="btn btn-primary" title="update" data-toggle="tooltip">Chỉnh sửa</a>
            <a href="CustomerServletURL" class="button-field">Quay lại    </a>
        </td>
    </tr>
</tbody>

                    </table>
                </div>
            </div>
                                
        </div>
    </body>
</html>
