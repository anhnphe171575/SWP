<%-- 
    Document   : orderdetails
    Created on : Jun 9, 2024, 3:07:57 PM
    Author     : MANH VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>My Order Detail</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            td img {
                width: 100px; /* Sets the width of the image */
                height: auto; /* Maintains the aspect ratio */
                border: 2px solid #ccc; /* Adds a border around the image */
                border-radius: 5px; /* Rounds the corners of the image */
                padding: 5px; /* Adds padding inside the border */
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* Adds a shadow effect */
            }
            .button-field {
                display: inline-block;
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 20px;
                font-size: 17px;
                margin-bottom: 10px;
            }

            .button-field:hover {
                background-color: #0056b3;
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
            table.table th, table.table td {
                text-align: center; /* Canh giá»¯a ná»i dung trong cÃ¡c cá»t */
            }

            /* Náº¿u báº¡n muá»n canh giá»¯a cáº£ tiÃªu Äá» cá»§a cá»t */
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
            .pagination  a {
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
            function validateForm() {
                var orderID = document.forms["searchForm"]["id"].value;
                var customerName = document.forms["searchForm"]["customer"].value;

                if (orderID && customerName) {
                    alert("Please enter only one search criteria.");
                    return false;
                }
                if (!orderID && !customerName) {
                    alert("Please enter a search criteria.");
                    return false;
                }
                return true;
            }
        </script>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <header>

            <jsp:include page="headerHomePage.jsp"></jsp:include>
            </header>

            <div class="container order-details">

                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-2">
                            <h2>Chi tiết đơn hàng</h2>
                        </div>
                    </div>
                </div>

                <!-- Basic Order Information -->
                <div class="card">
                    <div class="card-header">Thông tin</div>
                    <div class="card-body">
                        <div class="row">
                        <c:forEach items="${requestScope.list1}" var="item">
                            <div class="col-md-6">
                                <p><strong>ID:</strong> ${item.order.orderID}</p>
                                <p><strong>Tên khách hàng:</strong> ${item.order.customer.first_name} ${item.order.customer.last_name}</p>
                                <p><strong>Email:</strong> ${item.order.customer.email}</p>
                                <p><strong>Số điện thoại:</strong> ${item.order.customer.phone}</p>
                            </div>
                            <div class="col-md-6">
                                <p><strong>Ngày đặt:</strong> ${item.order.order_date}</p>
                                <p><strong>Tổng tiền:</strong> ${item.list_price}</p>
                                <p><strong>Người bán:</strong> ${item.order.staff.first_name} ${item.order.staff.last_name}</p>
                                <p><strong>Trạng thái:</strong> ${item.order.status.status_name}</p>
                                <p><strong>Phương thức thanh toán:</strong> ${item.order.getPaymentMethod()}

                                    <c:set value="${item.order.getPaymentMethod()}" var="Payment"></c:set>
                                    <c:set value="${item.order.status.status_orderid}" var="statusid"></c:set>
                                    <c:set value="${item.order.orderID}" var="oid"></c:set>

                                </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!-- Receiver Information -->
            <div class="card">
                <div class="card-header">Địa chỉ nhận hàng</div>
                <div class="card-body">
                    <c:forEach items="${requestScope.list2}" var="item">
                        <p><strong>Tên người nhận:</strong> ${item.receivers.getReceiverFullName()}</p>
                        <p><strong>Số điện thoại:</strong> ${item.receivers.getReceiverMobile()}</p>
                        <p><strong>Địa chỉ:</strong> ${item.receivers.getReceiverAddress()}</p>
                    </c:forEach>
                    <c:if test="${Payment == 'delivery' && statusid == 1}">

                    </c:if>
                </div>
            </div>

            <!-- List of Ordered Products -->
            <div class="card">
                <div class="card-header">Đơn hàng</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Sản phẩm </th>
                                    <th>Thể loại</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tổng tiền</th>
                                    <th>Mua lại</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${requestScope.list3}">
                                    <tr>
                                        <td><img src="${item.product.thumbnail}" /></td>
                                        <td>${item.product.product_name}</td>
                                        <td>${item.product.categoryProduct.category_name}</td>
                                        <td>${item.product.sale_price}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.list_price}</td>
                                        <c:forEach var="item1" items="${requestScope.list4}">
                                            <c:if test="${item1.productID == item.product.productID && item1.quantity - item1.quantity_hold > 0}">                                            
                                                <td>                                                <button class="add-to-cart-btn" data-product-id="${item.product.productID}">Mua lại</button>
                                                    </button></td>
                                                </c:if>               
                                            </c:forEach>
                                <form id="cancelForm" method="GET" action="CancelOrder">
                                    <input type="hidden" name="pid" value="${item.product.productID}" />
                                    <input type="hidden" name="quantity" value="${item.quantity}" />

                                    </tr>
                                </c:forEach>
                                </tbody>
                        </table>
                        <c:if test="${Payment == 'delivery' && statusid == 1}">

                            <input type="hidden" name="sid" value="${statusid}" />
                            <input type="hidden" name="oid" value="${oid}" />
                            <input type="hidden" name="cusid" value="${requestScope.customerid}" />
                            <button type="submit">Cancel</button>

                        </c:if>
                        </form>
                    </div>
                </div>
            </div>

            <a href="MyOrderURL?customerid=${requestScope.customerid}" class="button-field">Back To My Order</a>

        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');

                addToCartButtons.forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault(); // Ngăn chặn hành động mặc định của nút

                        // Lấy ID sản phẩm từ thuộc tính data
                        const productId = this.getAttribute('data-product-id');

                        // Tạo hiệu ứng thêm vào giỏ hàng
                        const cartIcon = document.querySelector('.fa-shopping-cart');

                        // Tạo bản sao của hình ảnh sản phẩm
                        ;

                        // Lấy vị trí của hình ảnh sản phẩm và giỏ hàng
                        const cartIconRect = cartIcon.getBoundingClientRect();

                        $.ajax({
                            url: 'AddToCart',
                            method: 'GET',
                            data: {pid: productId},
                            success: function (response) {
                                // Xử lý phản hồi thành công, ví dụ cập nhật giỏ hàng
                                alert('Sản phẩm đã được thêm vào giỏ hàng!');
                            },
                            error: function (error) {
                                // Xử lý lỗi
                                alert('Có lỗi xảy ra. Vui lòng thử lại.');
                            }
                        });
                    }, 1100);
                });
            });

        </script>
    </body>
</html>
