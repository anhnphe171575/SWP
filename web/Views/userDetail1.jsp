<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : QUANG VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,Entity.User,Entity.Role"%>
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
                            <div class="col-sm-8"><h2>Users <b>Details</b></h2></div>
                            <div class="col-sm-4">
                                <a href="#Add" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>New User</span></a>
                                <a href="userList?service=updateUser&UserID=${user.userID}" class="btn btn-danger" ><i class="material-icons">&#XE15C;</i> <span>Edit</span></a>

                            </div>                       

                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Attributes</th>
                                <th>Values</th>
                            </tr>
                        </thead>
                        <tbody>     
                            <tr>
                                <td>Avatar</td>
                                <td><img style="width: 150px" src="${user.image}" alt="Thumbnail" class="thumbnail"></td>
                            </tr>
                            <tr>
                                <td>Full name</td>
                                <td>${user.first_name} ${user.last_name}</td>                                    
                            </tr>
                            <tr>
                                <td>Gender</td>
                                <td>${user.gender ? 'Male' : 'Female'}</td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td>Mobile</td>
                                <td>${user.phone}</td>
                            </tr>
                            <tr>
                                <td>Role</td>
                                <td>${user.role.role_Name}</td>
                            </tr>                                  
                            <tr>
                                <td>Address</td>
                                <td>${user.address}</td>
                            </tr>
                            <tr>
                                <td>Status:</td>
                                <td>${user.status ==1 ? 'Active' : 'Not Active'}</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-right">
                                    <button><a href="userList">Back To User List</a></button>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->

        <div id="Add" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="userList" enctype="multipart/form-data">
                                <div class="modal-header">						
                                    <h4 class="modal-title">New User</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>fname</label>
                                        <input type="text" class="form-control" name="fname" >
                                    </div>
                                    <div class="form-group">
                                        <label>lname</label>
                                        <input type="text" class="form-control" name="lname" >
                                    </div>
                                    <div class="form-group">
                                        <label>phone</label>
                                        <input type="text" class="form-control" name="phone" >
                                    </div>
                                    <div class="form-group">
                                        <label>email</label>
                                        <input type="text" class="form-control" name="email" >
                                    </div>
                                    <div class="form-group">
                                        <label>address</label>
                                        <input type="text" class="form-control" name="address" >
                                    </div>
                                    <div class="form-group">
                                        <label>username</label>
                                        <input type="text" class="form-control" name="username" >
                                    </div>
                                    <div class="form-group">
                                        <label>password</label>
                                        <input type="text" class="form-control" name="password" >
                                    </div> 
                                    <div class="form-group">
                                        <label>dob</label>
                                        <input type="date" class="form-control" name="dob" >

                                        <div class="form-group" style="display: flex; justify-content: space-between; ">
                                            <label>gender</label>
                                            <input type="radio" class="form-control" name="gender" value="true" id="male">
                                            <label for="male">Male</label>
                                            <input type="radio" class="form-control" name="gender" value="false" id="female">
                                            <label for="female">Female</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>role</label>
                                        <select name="role">
                                            <c:forEach items="${role}" var="r">
                                                <option   value="${r.roleID}">${r.role_Name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>securityQuestion</label>
                                        <select name="securirtyQuestion">
                                            <c:forEach items="${question}" var="q">
                                                <option value="${q.securityID}">${q.security_question}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>securityAnswer</label>
                                        <input type="text" class="form-control" name="securityAnswer" >
                                    </div>



                                    <div class="modal-footer">

                                        <input type="submit" name="submit" value="add Users">
                                        <input type="reset" value="reset">
                                        <input type="hidden" name="service" value="insertUser">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
