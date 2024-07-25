<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : MANH VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageSize" value="2" />
<c:set var="totalItems" value="${fn:length(list1)}" />
<c:set var="totalPages" value="${(totalItems + pageSize - 1) / pageSize}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>My Order</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Style cho table-title */
            .table-title {
                padding: 15px;
                background: #f4f4f4;
                border-bottom: 1px solid #ddd;
            }

            .table-title .row {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .table-title h2 {
                margin: 0;
                font-size: 24px;
            }

            /* Style cho form */
            form[name="searchForm"] {
                display: flex;
                align-items: center;
            }

            form[name="searchForm"] input[type="text"] {
                padding: 5px;
                margin-right: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            form[name="searchForm"] input[type="submit"] {
                padding: 5px 10px;
                background: #5cb85c;
                color: white;
                border: none;
                border-radius: 4px;
            }

            /* Style cho các tab trạng thái đơn hàng */
            .order-status-tabs {
                display: flex;
                justify-content: space-around;
                margin: 20px 0;
                padding: 10px 0;
                background: #fff;
                border-bottom: 1px solid #ddd;
            }

            .order-status-tabs a {
                text-decoration: none;
                color: #333;
                padding: 10px 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                transition: all 0.3s ease;
            }

            .order-status-tabs a:hover,
            .order-status-tabs a.active {
                color: red;
                border-color: red;
            }

            /* Style for other parts of the page */
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

            table.table th, table.table td {
                text-align: center; /* Canh giữa nội dung trong các cột */
            }

            /* Nếu bạn muốn canh giữa cả tiêu đề của cột */
            table.table th {
                text-align: center;
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
                display: inline-block;
            }

            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination a:hover:not(.active) {
                background: #27A4F2;
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

            .custom-checkbox label:before {
                width: 18px;
                height: 18px;
            }

            .custom-checkbox label:before {
                content: '';
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


        </style>
        <script>
            function validateForm() {
                var orderID = document.forms["searchForm"]["id"].value;
                var customerName = document.forms["searchForm"]["product_name"].value;

                if (orderID && customerName) {
                    alert("Please enter only one search criteria.");
                    return false;
                }

                return true;
            }
            $(document).ready(function () {
                $('.table').DataTable({
                    "paging": true,
                    "pageLength": 10,
                    "searching": false,
                    "ordering": true,
                    "info": true
                });
            });
            document.addEventListener('DOMContentLoaded', function () {
                var form = document.forms["searchForm"];
                form.onsubmit = function () {
                    var customerid = form["customerid"].value;
                    return validateForm();
                };
            });
        </script>

        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <header>

            <jsp:include page="headerHomePage.jsp"></jsp:include>
            </header>
            <div class="container">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-2">
                                    <h2>Đơn hàng</h2>
                                </div>
                                <div class="col-sm-6 text-right">
                                    <form name="searchForm" action="MyOrderURL" method="post" onsubmit="return validateForm();">
                                        <input type="text" name="id" placeholder="ID">
                                        <input type="text" name="product_name" placeholder="Sản phẩm">
                                        <input type="submit" name="service" value="search">
                                        <input type="hidden" name="status1" value="${requestScope.status1}">
                                    <input type="hidden" name="customerid" value="${requestScope.customerid}">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="order-status-tabs">
                        <a href="MyOrderURL?customerid=${requestScope.customerid}" 
                           class="<c:out value="${empty status1 ? 'active' : ''}" />">All</a>
                        <c:forEach items="${status}" var="st1">
                            <c:choose>
                                <c:when test="${st1.status_orderid == status1}">
                                    <a href="MyOrderURL?customerid=${requestScope.customerid}&status=${st1.status_orderid}" class="active">${st1.status_name}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="MyOrderURL?customerid=${requestScope.customerid}&status=${st1.status_orderid}">${st1.status_name}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <div>
                        <!-- Additional content such as order list goes here -->
                    </div>
                    <div class="container">
                        <form action="MyOrderURL"method="post">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                </div>
                                <div class="col-md-4">
                                </div>
                                <div class="col-md-2">                            
                                </div>                                
                            </div>
                        </form>
                    </div>
                     <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Ngày tạo</th>
                                <th>Sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Tổng giá</th>
                                <th>Trạng thái</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list1}" var="item" varStatus="status">
                                <tr>
                                    <td><a href="MyOrderDetailURL?id=${item.order.orderID}&customerid=${customerid}">${item.order.orderID}</a></td>
                                    <td><fmt:formatDate value="${item.order.order_date}" pattern="dd-MM-yyyy"/></td>
                                    <td>${item.product.product_name.split(',')[0]}</td>  
                                    <td>${quantityOrder[item.order.orderID]}</td>
                                    <td><fmt:formatNumber value="${item.list_price}"/></td>   
                                    <td>${item.order.status.status_name}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.order.status.status_orderid == 6}">
                                                <!-- Feedback button for Success status -->
                                                <c:set var="hasFeedbackProducts" value="false" />
                                                <c:forEach items="${item.product.product_name.split(',')}" var="product" varStatus="pStatus">
                                                    <c:set var="productId" value="${item.product.product_description.split(',')[pStatus.index]}" />
                                                    <c:set var="hasFeedback" value="false" />
                                                    <c:forEach items="${requestScope.feedback}" var="fb">
                                                        <c:if test="${fb.product.productID == productId && fb.order.orderID == item.order.orderID}">
                                                            <c:set var="hasFeedback" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${!hasFeedback}">
                                                        <c:set var="hasFeedbackProducts" value="true" />
                                                    </c:if>
                                                </c:forEach>

                                                <c:choose>
                                                    <c:when test="${hasFeedbackProducts}">
                                                        <a href="#Add" class="btn btn-success" data-toggle="modal" data-orderid="${item.order.orderID}" data-products="${item.product.product_name}" data-descriptions="${item.product.product_description}">
                                                            <i class="material-icons">&#xE147;</i> <span>FeedBack</span>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-muted">All products reviewed</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${item.order.status.status_orderid == 8}">
                                                <!-- Pay Again button for Draft status -->
                                                <button onclick="location.href = 'PayAgain?total=${item.list_price}&orderid=${item.order.orderID}'">Pay Again</button>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- No action for other statuses -->
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="Add" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ProductDetailsPublic" method="get">
                        <div class="modal-header">						
                            <h4 class="modal-title">Choose Product FeedBack</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body"><div class="form-group">
                                <label>Products</label>
                                <select id="productSelect" name="pid" class="form-control">
                                    <!-- Options will be populated here -->
                                </select>
                            </div>                          
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-info" value="OK">
                            <input type="hidden" name="activate" value="activate">
                            <input type="hidden" id="orderIdInput" name="orderid">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $('#Add').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                var orderId = button.data('orderid');
                var products = button.data('products');
                var descriptions = button.data('descriptions');
                var modal = $(this);

                modal.find('#orderIdInput').val(orderId);

                var productSelect = modal.find('#productSelect');
                productSelect.empty();

                var productArray = products.split(',');
                var descriptionArray = descriptions.split(',');
                var hasProductsToReview = false;

                for (var i = 0; i < productArray.length; i++) {
                    var shouldAddOption = true;
            <c:forEach items="${requestScope.feedback}" var="fb">
                    if (descriptionArray[i] === '${fb.product.getProductID()}' && orderId === ${fb.order.getOrderID()}) {
                        shouldAddOption = false;
                    }
            </c:forEach>
                    if (shouldAddOption) {
                        var option = $('<option></option>').attr('value', descriptionArray[i]).text(productArray[i]);
                        productSelect.append(option);
                        hasProductsToReview = true;
                    }
                }

                if (!hasProductsToReview) {
                    modal.find('.modal-body').html('<p>You have already provided feedback for all products in this order.</p>');
                    modal.find('.modal-footer input[type="submit"]').hide();
                } else {
                    modal.find('.modal-footer input[type="submit"]').show();
                }
            });

        </script>
    </body>
</html>