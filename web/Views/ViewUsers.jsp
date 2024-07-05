<%-- 
    Document   : ViewStaff
    Created on : Apr 16, 2024, 9:35:37 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Vector,Entity.User,Entity.Role"%>

<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
            <link rel="stylesheet" href="styles.css">
            <!-- Montserrat Font -->
            <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

            <!-- Material Icons -->
            <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
            <link rel="stylesheet" href="./mktcss/styles.css">

            <style>
                .table-responsive{
                    width: 1200px;
                }
                body {
                    color: #566787;
                    background: #f5f5f5;
                    font-family: 'Varela Round', sans-serif;
                    font-size: 13px;
                }
                .table-responsive {
                    margin: 30px 0;
                }
                .table-wrapper {
                    background: #fff;
                    padding: 20px 25px;
                    border-radius: 3px;
                    min-width: 1000px;
                    box-shadow: 0 1px 1px rgba(0,0,0,.05);
                }
                .table-title {
                    padding-bottom: 15px;
                    background: #435d7d;
                    color: #fff;
                    padding: 16px 30px;
                    min-width: 135%;
                    margin: -20px -25px 10px;
                    border-radius: 3px 3px 0 0;
                }
                .table-title h2 {
                    margin: 5px 0 0;
                    font-size: 24px;
                }
                .table-title .btn-group {
                    float: right;
                }
                .table-title .btn {
                    color: #fff;
                    float: right;
                    font-size: 13px;
                    border: none;
                    min-width: 50px;
                    border-radius: 2px;
                    border: none;
                    outline: none !important;
                    margin-left: 10px;
                }
                .table-title .btn i {
                    float: left;
                    font-size: 21px;
                    margin-right: 5px;
                }
                .table-title .btn span {
                    float: left;
                    margin-top: 2px;
                }
                table.table tr th, table.table tr td {
                    border-color: #e9e9e9;
                    padding: 12px 15px;
                    vertical-align: middle;
                }
                table.table tr th:first-child {
                    width: 60px;
                }
                table.table tr th:last-child {
                    width: 100px;
                }
                table.table-striped tbody tr:nth-of-type(odd) {
                    background-color: #fcfcfc;
                }
                table.table-striped.table-hover tbody tr:hover {
                    background: #f5f5f5;
                }
                table.table th i {
                    font-size: 13px;
                    margin: 0 5px;
                    cursor: pointer;
                }
                table.table td:last-child i {
                    opacity: 0.9;
                    font-size: 22px;
                    margin: 0 5px;
                }
                table.table td a {
                    font-weight: bold;
                    color: #566787;
                    display: inline-block;
                    text-decoration: none;
                    outline: none !important;
                }
                table.table td a:hover {
                    color: #2196F3;
                }
                table.table td a.edit {
                    color: #FFC107;
                }
                table.table td a.delete {
                    color: #F44336;
                }
                table.table td i {
                    font-size: 19px;
                }
                table.table .avatar {
                    border-radius: 50%;
                    vertical-align: middle;
                    margin-right: 10px;
                }
                .pagination {
                    float: right;
                    margin: 0 0 5px;
                }
                .pagination li a {
                    border: none;
                    font-size: 13px;
                    min-width: 30px;
                    min-height: 30px;
                    color: #999;
                    margin: 0 2px;
                    line-height: 30px;
                    border-radius: 2px !important;
                    text-align: center;
                    padding: 0 6px;
                }
                .pagination li a:hover {
                    color: #666;
                }
                .pagination li.active a, .pagination li.active a.page-link {
                    background: #03A9F4;
                }
                .pagination li.active a:hover {
                    background: #0397d6;
                }
                .pagination li.disabled i {
                    color: #ccc;
                }
                .pagination li i {
                    font-size: 16px;
                    padding-top: 6px
                }
                .hint-text {
                    float: left;
                    margin-top: 10px;
                    font-size: 13px;
                }
                /* Custom checkbox */
                .custom-checkbox {
                    position: relative;
                }
                .custom-checkbox input[type="checkbox"] {
                    opacity: 0;
                    position: absolute;
                    margin: 5px 0 0 3px;
                    z-index: 9;
                }
                .custom-checkbox label:before{
                    width: 18px;
                    height: 18px;
                }
                .custom-checkbox label:before {
                    content: '';
                    margin-right: 10px;
                    display: inline-block;
                    vertical-align: text-top;
                    background: white;
                    border: 1px solid #bbb;
                    border-radius: 2px;
                    box-sizing: border-box;
                    z-index: 2;
                }
                .custom-checkbox input[type="checkbox"]:checked + label:after {
                    content: '';
                    position: absolute;
                    left: 6px;
                    top: 3px;
                    width: 6px;
                    height: 11px;
                    border: solid #000;
                    border-width: 0 3px 3px 0;
                    transform: inherit;
                    z-index: 3;
                    transform: rotateZ(45deg);
                }
                .custom-checkbox input[type="checkbox"]:checked + label:before {
                    border-color: #03A9F4;
                    background: #03A9F4;
                }
                .custom-checkbox input[type="checkbox"]:checked + label:after {
                    border-color: #fff;
                }
                .custom-checkbox input[type="checkbox"]:disabled + label:before {
                    color: #b8b8b8;
                    cursor: auto;
                    box-shadow: none;
                    background: #ddd;
                }
                /* Modal styles */
                .modal .modal-dialog {
                    max-width: 400px;
                }
                .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                    padding: 20px 30px;
                }
                .modal .modal-content {
                    border-radius: 3px;
                    font-size: 14px;
                }
                .modal .modal-footer {
                    background: #ecf0f1;
                    border-radius: 0 0 3px 3px;
                }
                .modal .modal-title {
                    display: inline-block;
                }
                .modal .form-control {
                    border-radius: 2px;
                    box-shadow: none;
                    border-color: #dddddd;
                }
                .modal textarea.form-control {
                    resize: vertical;
                }
                .modal .btn {
                    border-radius: 2px;
                    min-width: 100px;
                }
                .modal form label {
                    font-weight: normal;
                }
            </style>
            <script>
                $(document).ready(function () {
                    // Activate tooltip
                    $('[data-toggle="tooltip"]').tooltip();

                    // Select/Deselect checkboxes
                    var checkbox = $('table tbody input[type="checkbox"]');
                    $("#selectAll").click(function () {
                        if (this.checked) {
                            checkbox.each(function () {
                                this.checked = true;
                            });
                        } else {
                            checkbox.each(function () {
                                this.checked = false;
                            });
                        }
                    });
                    checkbox.click(function () {
                        if (!this.checked) {
                            $("#selectAll").prop("checked", false);
                        }
                    });
                });
            </script>
        </head>
        <body>
            <div class="grid-container">

                <!-- Header -->
                <jsp:include page="header.jsp"></jsp:include>
                    <!-- End Header -->

                    <!-- Sidebar -->
                <jsp:include page="sidebarAdmin.jsp"></jsp:include>

                    <div class="container-xl">
                        <div class="table-responsive">
                            <div class="table-wrapper">
                                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h2>Manage <b>Users</b></h2>
                                        </div>
                                        <div style="text-align: right" class="col-sm-9">
                                            <div>
                                                <div class="col-sm-3">
                                                    <form action="userList" method="post" style="display: flex; justify-content: space-between;">
                                                        <div>
                                                            <p>Search first_name: <input type="text" name="first_name"></p> 
                                                            <input type="submit" name="submit" value="Search">
                                                            <input type="reset" value="Clear">
                                                            <input type="hidden" name="action" value="search1">
                                                            <input type="hidden" name="service" value="listAllUsers">
                                                        </div>

                                                    </form>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <a href="#Filter" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#XE15C;</i> <span>Filter</span></a>
                                            <a href="AddUser" class="btn btn-success"  ><i class="material-icons">&#xE147;</i> <span>New User</span></a>
                                            <a href="#Sort" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xe164;</i> <span>Sort</span></a>

                                        </div>
                                    </div>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>

                                            <th>UserID</th>
                                            <th>first_name</th>
                                            <th>last_name</th>
                                            <th>phone</th>
                                            <th>email</th>
                                            <th>dob</th>
                                            <th>gender</th>
                                            <th>status</th>
                                            <th>role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                            Vector<User> vector=(Vector<User>)request.getAttribute("data");
                             for (User obj : vector) {
                                    %>
                                    <tr>
                                        <td><%=obj.getUserID()%></td>
                                        <td><%=obj.getFirst_name()%></td>
                                        <td><%=obj.getLast_name()%></td>
                                        <td><%=obj.getPhone()%></td>
                                        <td><%=obj.getEmail()%></td>
                                        <td><%=obj.getDob()%></td>
                                        <td><%=obj.isGender() ? "Male" : "Female" %></td>
                                        <td><%=obj.getStatus() == 1 ? "Active" : "Inactive" %></td>
                                        <td><%=obj.getRole().getRole_Name()%></td>
                                        <td>
                                            <a href="userDetail?UserID=<%=obj.getUserID()%>" class="fa fa-eye"></a>
                                            <a href="updateUser?&UserID=<%=obj.getUserID()%>"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        </td>
                                    </tr>
                                    <%}%>

                                </tbody>
                            </table>
                            <div class="clearfix">
                                <ul class="pagination">
                                    <li class="page-item disabled"><a href="#">Previous</a></li>
                                    <li class="page-item"><a href="#" class="page-link">1</a></li>
                                    <li class="page-item"><a href="#" class="page-link">Next</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>        
                </div>
                <!-- Filter Modal HTML -->
                <div id="Filter" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Bộ lọc</h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <form action="userList" >

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">OK</button>
                                    <input type="hidden" name="service" value="filter">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Add Modal HTML -->
              
          


                <!-- Sort -->
                <div id="Sort" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="userList" enctype="multipart/form-data">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Sort</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>Option:</label>
                                        <select id="category-select" class="form-control" name="sort">                                   
                                            <option value="UserID">userID</option>
                                            <option value="first_name">first_name</option>
                                            <option value="phone">phone</option>
                                            <option value="email">email</option>
                                            <option value="status">Status</option>
                                            <option value="role">role</option>
                                            <option value="gender">gender</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">

                                    <input type="submit" class="btn btn-info" value="OK">
                                    <input type="hidden" name="service" value="sort">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Delete Modal HTML -->
            </div>
        </body>
    </html>

