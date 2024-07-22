<%-- Document : listPost Created on : May 18, 2024, 5:40:58 PM Author : admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Post Controller</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="./mktcss/styles.css">
        <link rel="stylesheet" href="/qcss/style.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">

        <style>
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
            /* DataTables specific styles */
            .dataTables_wrapper .dataTables_paginate .paginate_button {
                padding: 0.5em 1em;
                margin-left: 2px;
            }
            .dataTables_wrapper .dataTables_filter {
                float: right;
                text-align: right;
            }
            .container-xl {
                width: 1400px;
            }
            .material-icons-outlined {
                vertical-align: middle;
                line-height: 1px;
                font-size: 35px;
            }
            td img {
                width: 100px;
                height: auto;
                border: 2px solid #ccc;
                border-radius: 5px;
                padding: 5px;
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
            }
        </style>
    </head>

    <body>
        <div class="grid-container">
            <!-- Header -->
            <jsp:include page="header.jsp"></jsp:include>
                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>

                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h2>Quản lý bài đăng</h2>
                                    </div>
                                     <div style="text-align: right" class="col-sm-3">
                                            <form action="PostController" method="post">
                                                <input type="text" name="title"><!-- comment -->
                                                <input type="submit" name="submit" value="Tìm"><!-- comment -->
                                                <input type="hidden" name="service" value="search">
                                            </form>
                                        </div>
                                        <div class="col-sm-6">
                                            <a href="#Add" class="btn btn-success" data-toggle="modal"><i
                                                    class="material-icons">&#xE147;</i> <span>Tạo bài mới</span></a>
                                            
                                        </div>
                                       
                                </div>
                                
                            </div>
                            <form action="PostController" method="post">
                                <div class="filter-container d-flex flex-wrap align-items-center">
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Thể Loại:</span>
                                            </div>
                                            <select id="product-filter" name="category" class="form-control">
                                                <option value="all">Tất cả</option>
                                            <c:forEach items="${requestScope.category}" var="p">
                                                <option value="${p}">${p}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Người đăng:</span>
                                        </div>
                                        <select class="custom-select" id="salename" name="author">
                                            <option value="all">Tất cả</option>
                                            <c:forEach items="${requestScope.user}" var="u">
                                                <option value="${u.first_name} ${u.last_name}">${u.first_name}
                                                    ${u.last_name}</option>
                                                </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Trạng thái:</span>
                                        </div>
                                        <select class="custom-select" id="status" name="status">
                                            <option value="3">Tất cả</option>
                                            <option value="0">Ẩn</option>
                                            <option value="1">Hiện</option>
                                        </select>
                                    </div>
                                </div>

                                <input type="hidden" value="filter" name="service">
                                <div class="col-md-3">
                                    <input type="submit" value="Lọc" class="btn btn-primary"
                                           style="margin-top: 0px">
                                </div>

                            </div>
                        </form>
                            <table id="customerTable" class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Ảnh</th>
                                        <th>Tiêu đề</th>
                                        <th>Thể loại</th>
                                        <th>Người đăng</th>
                                        <th>Nổi bật</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.post}" var="p">
                                    <tr>
                                        <td>${p.postID}</td>
                                        <td><img src="${p.thumbnail}" alt="Image" /></td>
                                        <td>${p.title}</td>
                                        <td>${p.cp.category_product.category_name}</td>
                                        <td>${p.staff.first_name} ${p.staff.last_name}</td>
                                        <td>
                                            <c:if test="${p.featured == '1'}">Có</c:if>
                                            <c:if test="${p.featured != '1'}">Không</c:if>
                                            </td>
                                            <td>
                                            <c:if test="${p.status == '1'}">Hiện</c:if>
                                            <c:if test="${p.status != '1'}">Ẩn</c:if>
                                            </td>
                                            <td>
                                            <c:if test="${p.status == '1'}">
                                                <a href="Status?service=status&postID=${p.postID}&status=0" class="fa fa-eye" style="color: red;"></a>
                                            </c:if>
                                            <c:if test="${p.status != '1'}">
                                                <a href="Status?service=status&postID=${p.postID}&status=1" class="fa fa-eye-slash" style="color: red;"></a>
                                            </c:if>
                                            <a href="EditPost?postID=${p.postID}&detail=1" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                            <a href="PostDetail?service=viewDetail&postID=${p.postID}"><i class="material-icons" data-toggle="tooltip" title="view" style="color: blue;">&#xE8B6;</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Modal HTML -->
        <!-- Add Modal HTML -->
        <div id="Add" class="modal fade">
            <div class="modal-dialog" style="color: black">
                <div class="modal-content">
                    <form action="PostController" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h4 class="modal-title">Bài Viết Mới</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Tiêu đề</label>
                                <input type="text" class="form-control" name="title" required>
                            </div>
                            <div class="form-group">
                                <label>Ảnh</label>
                                <input type="file" id="file" name="file" class="form-control-file" required>
                            </div>
                            <div class="form-group">
                                <label>Thể loại</label>
                                <select id="category-select" class="form-control" name="category_post">
                                    <c:forEach items="${requestScope.category_product}" var="c">
                                        <option value="${c.category_productID}">${c.category_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Nổi bật</label>
                                <input type="text" class="form-control" name="featured" required>
                            </div>

                            <div class="form-group">
                                <label>Tóm tắt</label>
                                <input type="text" class="form-control" name="brief_information" required>
                            </div>
                            <div class="form-group">
                                <label>Chi tiết</label>
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
        <!-- comment -->
        <div id="Sort1" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="PostController" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h4 class="modal-title">Sort</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
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
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">
                            <h4 class="modal-title">Delete Employee</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>

                        <!-- Sort Modal HTML -->
                        <div id="Sort1" class="modal fade">
                            <!-- Sort modal content here -->
                        </div>

                        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
                        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
                        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
                        <script src="./mktjs/scripts.js"></script>
                        <script>
                            $(document).ready(function () {
                                $('#customerTable').DataTable({
                                    "pageLength": 10,
                                    "lengthChange": false,
                                    "searching": false,
                                    "ordering": true,
                                    "info": true,
                                    "autoWidth": false,
                                    "language": {
                                        
                                        "paginate": {
                                            "first": "Đầu",
                                            "last": "Cuối",
                                            "next": "Tiếp",
                                            "previous": "Trước"
                                        },
                                        "emptyTable": "Không có dữ liệu trong bảng",
                                        "zeroRecords": "Không tìm thấy dữ liệu phù hợp"
                                    }
                                });

                                // Kích hoạt tooltip
                                $('[data-toggle="tooltip"]').tooltip();

                                // Chọn/Bỏ chọn tất cả các checkbox
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
                        </body>

                        </html>