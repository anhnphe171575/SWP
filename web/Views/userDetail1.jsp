<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : QUANG VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,Entity.Staff,Entity.Role"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Product Details</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            /* Custom CSS */
            .container {
                margin-top: 50px;
            }

            .table-wrapper {
                background: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
            }

            .table-title {
                padding-bottom: 10px;
                margin-top: 0;
                margin-bottom: 20px;
            }

            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }

            .table-striped tbody tr:nth-of-type(odd) {
                background-color: #f3f3f3;
            }

            .table-hover tbody tr:hover {
                background-color: #f5f5f5;
            }

            .modal-dialog {
                max-width: 600px;
            }

            .modal-content {
                padding: 20px;
            }

            .modal-body .form-group {
                margin-bottom: 20px;
            }

            .modal-body .form-group label {
                font-weight: bold;
            }

            .modal-footer {
                border-top: none;
                padding-top: 0;
            }

            .btn-default {
                background-color: #ccc;
                color: #fff;
            }

            .btn-success {
                background-color: #28a745;
                border-color: #28a745;
                color: #fff;
            }

            .btn-default:hover,
            .btn-success:hover {
                opacity: 0.8;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Thông tin chi tiết</h2></div>
                            <div class="col-sm-4">
                              

                            </div>                       

                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Thuộc tính</th>
                                <th>Giá trị</th>
                            </tr>
                        </thead>
                        <tbody>     
                            <tr>
                                <td>Ảnh</td>
                                <td><img style="width: 150px" src="${user.image}" alt="Thumbnail" class="thumbnail"></td>
                            </tr>
                            <tr>
                                <td>Họ và tên</td>
                                <td>${user.first_name} ${user.last_name}</td>                                    
                            </tr>
                            <tr>
                                <td>Giới tính</td>
                                <td>${user.gender ? 'Nam' : 'Nữ'}</td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td>Số điện thoại</td>
                                <td>${user.phone}</td>
                            </tr>
                            <tr>
                                <td>Vai trò</td>
                                <td>${user.role.role_Name}</td>
                            </tr>                                  
                            <tr>
                                <td>Địa chỉ</td>
                                <td>${user.address}</td>
                            </tr>
                            <tr>
                                <td>Trạng thái</td>
                                <td>${user.status ==1 ? 'Hoạt động' : 'Không hoạt động'}</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-right">
                                    <button><a href="userList">Quay về trang chính</a></button>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->

     
        
    </body>
</html>
