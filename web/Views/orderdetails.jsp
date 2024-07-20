<%-- 
    Document   : orderdetails
    Created on : Jun 9, 2024, 3:07:57 PM
    Author     : MANH VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Order Details</title>
        <!-- CSS Libraries -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

        <!-- JS Libraries -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>

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
            $(document).on('click', '.edit-sale-icon', function () {
                var orderID = $(this).data('order-id');
                fetchSaleOptions(orderID);
            });

            $(document).on('click', '.edit-status-icon', function () {
                var statusOrderid = $(this).data('status-orderid');
                var orderID = $(this).data('order-id');
                console.log("Status Order ID:", statusOrderid); // Debugging statement
                console.log("Order ID:", orderID);
                fetchOrderStatuses(statusOrderid, orderID);
            });
            function fetchSaleOptions(orderID) {
                $.ajax({
                    url: 'sales',
                    method: 'GET',
                    dataType: 'json',
                    success: function (sales) {
                        console.log('Sales data received:', sales); // Debugging line
                        var dropdown = '<select id="saleDropdown-' + orderID + '" class="form-control">';
                        $.each(sales, function (saleID, saleDetails) {
                            var saleName = saleDetails[1];
                            var orderCount = saleDetails[0];
                            dropdown += '<option value="' + saleID + '">' + saleName + ' (' + orderCount + ' orders)</option>';
                        });
                        dropdown += '</select>';
                        var form = '<form id="editSaleForm-' + orderID + '" style="display: inline-block;">' +
                                dropdown +
                                '<button type="submit" class="btn btn-primary">Save</button>' +
                                '</form>';
                        $('#saleContainer-' + orderID).html(form);

                        // Add event listener for the newly created form
                        $('#editSaleForm-' + orderID).on('submit', function (e) {
                            e.preventDefault();
                            var selectedSale = $('#saleDropdown-' + orderID).val();
                            console.log('Selected sale for order ' + orderID + ': ' + selectedSale);
                            // Submit the data to the server or handle further as needed
                        });
                    },
                    error: function (xhr, status, error) {
                        console.error('Error fetching sales:', error);
                    }
                });
            }

            $(document).on('submit', 'form[id^="editSaleForm-"]', function (e) {
                e.preventDefault();
                var formID = $(this).attr('id');
                var orderID = formID.replace('editSaleForm-', '');
                var newSale = $('#saleDropdown-' + orderID).val();
                $.ajax({
                    url: 'updatesale',
                    method: 'POST',
                    data: {
                        orderID: orderID,
                        newSale: newSale
                    },
                    success: function (response) {
                        alert('Cập nhật người bán của đơn hàng thành công!');
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        alert('Lỗi khi cập nhật người bán của đơn hàng: ' + error);
                    }
                });
            });
            function fetchOrderStatuses(statusOrderid, orderID) {
                $.ajax({
                    url: 'orderstatus', // Adjust the URL to your API endpoint
                    method: 'GET',
                    data: {statusOrderid: statusOrderid}, // Include the statusOrderid as a parameter
                    dataType: 'json',
                    success: function (statuses) {
                        var dropdown = '<select id="statusDropdown-' + statusOrderid + '" class="form-control">';
                        $.each(statuses, function (index, status) {
                            dropdown += '<option value="' + status.status_orderid + '">' + status.status_name + '</option>';
                        });
                        dropdown += '</select>';
                        var form = '<form id="editStatusForm-' + statusOrderid + '" style="display: inline-block;" onsubmit="return updateOrderStatus(event, \'' + orderID + '\', \'' + statusOrderid + '\')">' +
                                dropdown +
                                '<button type="submit" class="btn btn-primary">Save</button>' +
                                '</form>';
                        $('#statusContainer-' + statusOrderid).html(form);
                    },
                    error: function (xhr, status, error) {
                        console.error('Error fetching statuses:', error);
                    }
                });
            }

            function updateOrderStatus(event, orderID, statusOrderid) {
                event.preventDefault();
                var newStatus = $('#statusDropdown-' + statusOrderid).val();
                console.log("Updating Order ID:", orderID, "with Status:", newStatus);

                $.ajax({
                    url: 'updatestatusorder',
                    method: 'POST',
                    data: {
                        orderID: orderID,
                        newStatus: newStatus
                    },
                    success: function (response) {
                        alert('Cập nhật trạng thái đơn hàng thành công!');
                        location.reload(); // Tải lại trang sau khi cập nhật thành công
                    },
                    error: function (xhr, status, error) {
                        alert('Lỗi khi cập nhật trạng thái đơn hàng: ' + error);
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="container order-details">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-2">
                        <h2>Order <b>Details</b></h2>
                    </div>
                </div>
            </div>

            <!-- Basic Order Information -->
            <div class="card">
                <div class="card-header">Basic Order Information</div>
                <div class="card-body">
                    <div class="row">
                        <c:set value="${requestScope.list1}" var="item"></c:set>
                            <div class="col-md-6">
                                <p><strong>Order ID:</strong> ${item.order.orderID}</p>
                            <p><strong>Customer Name:</strong> ${item.order.customer.first_name}</p>
                            <p><strong>Email:</strong> ${item.order.customer.email}</p>
                            <p><strong>Phone:</strong> ${item.order.customer.phone}</p>
                        </div>
                        <div class="col-md-6">
                            <p><strong>Order Date:</strong> ${item.order.order_date}</p>
                            <p><strong>Total Cost:</strong> ${item.list_price}</p>
                            <c:if test="${sessionScope.staff.getRole().getRoleID() == 3}">
                                <p><strong>Sale Name:</strong> 
                                    <span id="saleContainer-${item.order.orderID}">
                                        ${item.order.staff.first_name}
                                        <i class="fas fa-edit edit-sale-icon" 
                                           data-order-id="${item.order.orderID}" 
                                           style="cursor:pointer; color: #007bff;"></i>
                                    </span>
                                </p>
                            </c:if>
                            <c:if test="${sessionScope.staff.getRole().getRoleID() != 3}">
                                <p><strong>Sale Name:</strong>  ${item.order.staff.first_name}</p>
                            </c:if>
                            <p>
                                <strong>Status:</strong> 
                                <span id="statusContainer-${item.order.status.getStatus_orderid()}">
                                    ${item.order.status.status_name}
                                    <c:if test="${((item.order.status.getStatus_orderid() == 4 || item.order.status.getStatus_orderid() == 3) && sessionScope.staff.role.getRoleID() == 4 ) || 
                                                  (item.order.status.getStatus_orderid() != 6 && item.order.status.getStatus_orderid() != 7 && item.order.status.getStatus_orderid() != 4 && item.order.status.getStatus_orderid() != 3 && item.order.status.getStatus_orderid() != 2 && sessionScope.staff.role.getRoleID() == 2)}">
                                          <i class="fas fa-edit edit-status-icon" 
                                             data-order-id="${item.order.getOrderID()}" 
                                             data-status-orderid="${item.order.status.getStatus_orderid()}" 
                                             style="cursor:pointer; color: #007bff;"></i>
                                    </c:if>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Receiver Information -->
            <div class="card">
                <div class="card-header">Receiver Information</div>
                <div class="card-body">
                    <c:forEach items="${requestScope.list2}" var="item">
                        <p><strong>Full Name:</strong> ${item.receivers.getReceiverFullName()}</p>
                        <p><strong>Mobile:</strong> ${item.receivers.getReceiverMobile()}</p>
                        <p><strong>Address:</strong> ${item.receivers.getReceiverAddress()}</p>
                    </c:forEach>
                </div>
            </div>

            <!-- List of Ordered Products -->
            <div class="card">
                <div class="card-header">Ordered Products</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Thumbnail</th>
                                    <th>Name</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total Cost</th>
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
                                <input type="hidden" name="pid" value="${item.product.productID}" />
                                <input type="hidden" name="quantity" value="${item.quantity}" />
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <a href="orderlist" class="button-field">Back To Order List</a>
        </div>
    </body>
</html>
