<%-- 
    Document   : listPost
    Created on : May 18, 2024, 5:40:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <link rel="stylesheet" href="/qcss/style.css">
        <style>
            td img {
                width: 100px; /* Sets the width of the image */
                height: auto; /* Maintains the aspect ratio */
                border: 2px solid #ccc; /* Adds a border around the image */
                border-radius: 5px; /* Rounds the corners of the image */
                padding: 5px; /* Adds padding inside the border */
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* Adds a shadow effect */
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
                min-width: 100%;
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
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Manage <b>Post</b></h2>
                            </div>
                            <div style="text-align: right"class="col-sm-3">
                                <form action="SliderServletURL" method="get">
                                    <p>
                                        Search title: <input type="text" name="title"> 
                                        <input type="submit" name="submit" value="search">
                                        <input type="reset" value="Clear"></p>
                                    <input type="hidden" name="service" value="listAllSlider">
                                </form>				
                            </div>

                            <div class="col-sm-6">
                                <a href="#Filter" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#XE15C;</i> <span>Filter</span></a>
                              
                                         
                                
                                <h5>${sessionScope.username}</h5>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                               <th>SliderID</th>
                                <th>title</th>
                                <th>image</th>
                                <th>link</th>
                                <th>status</th>
                            </tr>
                        </thead>
                        <tbody>
                              <c:forEach items="${requestScope.listAllSlider}" var="c">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox2" name="options[]" value="1">
                                            <label for="checkbox2"></label>
                                        </span>
                                    </td>
                                    <td>${c.sliderID}</td>
                                    <td>${c.title}</td> 
                                    <td><img src="${c.image}" alt="Image"/></td>
                                    <td>${c.link}</td>
                                    <td>
                                        <c:if test="${c.status == '1'}">Show</c:if>
                                        <c:if test="${c.status != '1'}">Hide</c:if>
                                        </td>
                                      
                                   
                                    <td>
                                        <c:if test="${c.status == '1'}">
                                            <a href="SliderServletURL?service=status&sliderID=${c.sliderID}&status=0"  class="fa fa-eye"></a>
                                        </c:if>
                                        <c:if test="${c.status != '1'}">

                                            <a href="SliderServletURL?service=status&sliderID=${c.sliderID}&status=1" class="fa fa-eye-slash"></a>
                                        </c:if>
                                        <a href="SliderServletURL?service=updateSlider&sliderid=${c.sliderID}" title="update" ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="SliderServletURL?service=viewDetailsSlider&sliderid=${c.sliderID}" title="ViewDetails" data-toggle="tooltip"><i class="material-icons">&#xE8B6;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

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
                        <form action="SliderServletURL" method="post">
                            <div class="modal-body">
                                <!-- Category Filter -->
                                <div class="form-group">
                                    <label for="category-select">Status:</label>
                                    <select id="category-select" class="form-control" name="status">
                                        <option value="3">ALL</option>
                                   
                                            <option value="1">Show</option>
                                            <option value="0">Hide</option>
                                    </select>
                                </div>
                                <!-- Author Filter -->
                              
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">OK</button>
                                <input type="hidden" name="service" value="filter">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Add Modal HTML -->
            <div id="Add" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="PostController" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">New Post</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>title</label>
                                    <input type="text" class="form-control" name="title" required>
                                </div>
                                <div class="form-group">
                                    <label>thumbnail</label>
                                    <input type="text" class="form-control" name="thumbnail" >
                                </div>
                                <div class="form-group">
                                    <label>Category Post</label>
                                    <select id="category-select" class="form-control" name="category_post">                                   
                                        <c:forEach items="${requestScope.category_product}" var="c">
                                            <option value="${c.category_productID}">${c.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>featured</label>
                                    <input type="text" class="form-control" name="featured" required>
                                </div>

                                <div class="form-group">
                                    <label>brief_information</label>
                                    <input type="text" class="form-control" name="brief_information" required>
                                </div>
                                <div class="form-group">
                                    <label>description</label>
                                    <input type="text" class="form-control" name="description" required>
                                </div>
                            </div>
                            <div class="modal-footer">

                                <input type="submit" class="btn btn-info" value="OK">
                                <input type="hidden" name="service" value="add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Sort -->
            <div id="Sort" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="PostController" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Sort</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Option:</label>
                                    <select id="category-select" class="form-control" name="sort">                                   
                                        <option value="p.title">Title</option>
                                        <option value="cpr.category_name">Category</option>
                                        <option value="u.first_name">Author</option>
                                        <option value="p.featured">Featured</option>
                                        <option value="p.status">Status</option>
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
            <!<!-- comment -->
            <div id="Edit" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="PostController" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Post</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>title</label>
                                    <input type="text" class="form-control" name="title" required>
                                </div>
                                <div class="form-group">
                                    <label>thumbnail</label>
                                    <input type="text" class="form-control" name="thumbnail" >
                                </div>
                                <div class="form-group">
                                    <label>Category Post</label>
                                    <select id="category-select" class="form-control" name="category_post">                                   
                                        <c:forEach items="${requestScope.category_product}" var="c">
                                            <option value="${c.category_productID}">${c.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>featured</label>
                                    <input type="text" class="form-control" name="featured" required>
                                </div>
                                <!--                            <div class="form-group">
                                                                <label>status</label>
                                                                <select id="status-select" class="form-control" name="status">
                                <c:forEach items="${requestScope.status}" var="s">
                                    <option value="${s}">${s}</option>
                                </c:forEach>
                            </select>
                        </div>-->
                                <div class="form-group">
                                    <label>brief_information</label>
                                    <input type="text" class="form-control" name="brief_information" required>
                                </div>
                                <div class="form-group">
                                    <label>description</label>
                                    <input type="text" class="form-control" name="description" required>
                                </div>
                            </div>
                            <div class="modal-footer">

                                <input type="submit" class="btn btn-info" value="OK">
                                <input type="hidden" name="service" value="add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Delete Modal HTML -->
            <div id="deleteEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">						
                                <h4 class="modal-title">Delete Employee</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <p>Are you sure you want to delete these Records?</p>
                                <p class="text-warning"><small>This action cannot be undone.</small></p>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </body>
</html>

