<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : MANH VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Order List</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- JS Libraries -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
        <link rel="stylesheet" href="./mktcss/styles.css">
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
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }

            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }

            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                outline: none !important;
                margin-left: 10px;
            }

            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
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
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
                width: 18px;
                height: 18px;
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

            .grid-container {
                display: grid;
                height: 100vh;
                width: 100%;
            }

            .filter-form .input-group-text,
            .filter-form .form-control,
            .filter-form .custom-select {
                font-size: 12px;
            }

            .filter-form .input-group {
                flex-wrap: nowrap;
            }

            .filter-form .input-group-prepend,
            .filter-form .input-group-text,
            .filter-form .form-control,
            .filter-form .custom-select {
                width: auto;
                min-width: 0;
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
        <script>
            $(document).ready(function () {
                $('.table').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": true,
                    "ordering": true,
                    "info": false,
                    "autoWidth": false,
                    "pageLength": 10
                });
            });
            document.addEventListener('DOMContentLoaded', function () {
                const fromDateInput = document.getElementById('from-date');
                const toDateInput = document.getElementById('to-date');
                const today = new Date().toISOString().split('T')[0];

                // Giới hạn ngày kết thúc tối đa là ngày hiện tại
                toDateInput.setAttribute('max', today);

                // Kiểm tra và đặt giới hạn ngày khi ngày bắt đầu thay đổi
                fromDateInput.addEventListener('change', function () {
                    const fromDate = fromDateInput.value;
                    if (fromDate) {
                        toDateInput.setAttribute('min', fromDate);
                    } else {
                        toDateInput.removeAttribute('min');
                    }
                });

                // Kiểm tra và đặt giới hạn ngày khi ngày kết thúc thay đổi
                toDateInput.addEventListener('change', function () {
                    const toDate = toDateInput.value;
                    if (toDate) {
                        fromDateInput.setAttribute('max', toDate);
                    } else {
                        fromDateInput.setAttribute('max', today);
                    }
                });
            });
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
                    method: 'POST',
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
                    method: 'POST',
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
                var note = "";

                if (newStatus === "7") {
                    note = prompt("Vui lòng nhập ghi chú cho việc cập nhật trạng thái này:", "");
                    if (note === null) {
                        alert('Bạn đã hủy cập nhật trạng thái.');
                        return; // Thoát khỏi hàm nếu người dùng hủy
                    }
                    if (note.length === 0) {
                        alert('Ghi chú không thể để trống khi trạng thái là 5.');
                        return;
                    }
                }
                $.ajax({
                    url: 'updatestatusorder',
                    method: 'POST',
                    data: {
                        orderID: orderID,
                        newStatus: newStatus,
                        note: note
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
    </head>
    <body>
        <div class="grid-container">
            <!-- Header -->
            <jsp:include page="header.jsp"></jsp:include>
                <!-- Sidebar -->
            <jsp:include page="sidebar1.jsp"></jsp:include>
                <div class="container-xl" style="width: 1200px">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h2>Danh sách đơn hàng</h2>
                                    </div>
                                    

                                </div>
                            </div>
                            <div class="container">
                            <c:choose>
                                <c:when test="${sessionScope.user.role.getRoleID() == 2}">
                                    <!-- Form for Role 2 -->
                                    <form action="orderlist" method="post" class="filter-form">
                                        <input type="hidden" value="${saleid}" name="saleid"  >
                                        <div class="row mb-3">
                                            <div class="col-md-5">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Order Date:</span>
                                                    </div>
                                                    <input type="date" class="form-control" id="from-date" name="fromDate" placeholder="From">
                                                    <input type="date" class="form-control" id="to-date" name="toDate" placeholder="To">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Status:</span>
                                                    </div>
                                                    <select class="custom-select" id="status" name="status">
                                                        <option value="all">All</option>
                                                        <c:forEach items="${requestScope.status}" var="c">
                                                            <option value="${c}">${c}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4 ">
                                                <button type="submit" class="btn btn-primary" id="filter-btn">Filter</button>
                                                <input type="hidden" name="service" value="filter">
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:when test="${sessionScope.user.getRole().getRoleID() == 3}">
                                    <!-- Form for Role 3 -->
                                    <form action="orderlist" method="post" class="filter-form">
                                        <div class="row mb-3">
                                            <div class="col-md-5">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Order Date:</span>
                                                    </div>
                                                    <input type="date" class="form-control" id="from-date" name="fromDate" placeholder="From">
                                                    <input type="date" class="form-control" id="to-date" name="toDate" placeholder="To">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Sale Name:</span>
                                                    </div>
                                                    <select class="custom-select" id="salename" name="salename">
                                                        <option value="all">All</option>
                                                        <c:forEach items="${requestScope.sale}" var="c">
                                                            <option value="${c}">${c}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Status:</span>
                                                    </div>
                                                    <select class="custom-select" id="status" name="status">
                                                        <option value="all">All</option>
                                                        <c:forEach items="${requestScope.status}" var="c">
                                                            <option value="${c}">${c}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-1">
                                                <button type="submit" class="btn btn-primary" id="filter-btn">Filter</button>
                                                <input type="hidden" name="service" value="filter">
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:when test="${sessionScope.user.role.getRoleID() == 4}">
                                    <!-- Form for Role 4 -->
                                    <form action="orderlist" method="post" class="filter-form">
                                        <div class="row mb-3">
                                            <div class="col-md-5">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Order Date:</span>
                                                    </div>
                                                    <input type="date" class="form-control" id="from-date" name="fromDate" placeholder="From">
                                                    <input type="date" class="form-control" id="to-date" name="toDate" placeholder="To">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Status:</span>
                                                    </div>
                                                    <select class="custom-select" id="status" name="status">
                                                        <option value="all">All</option>
                                                        <c:forEach items="${requestScope.status}" var="c">
                                                            <option value="${c}">${c}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-12 mt-3">
                                                <button type="submit" class="btn btn-primary" id="filter-btn">Filter</button>
                                                <input type="hidden" name="service" value="filter">
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                            </c:choose>
                        </div>
                        <c:if test="${list1 != null && not empty list1}">
                            <!-- Order list table -->
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Ngày tạo</th>
                                        <th>Người bán</th>
                                        <th>Sản phẩm</th>
                                        <th>Số lượng</th>
                                        <th>Tổng giá</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list1}" var="item" varStatus="status">
                                        <tr>
                                            <td><a href="orderdetails?id=${item.order.orderID}">${item.order.orderID}</a></td>
                                            <td><fmt:formatDate value="${item.order.order_date}" pattern="dd-MM-yyyy"/></td>
                                            <td>${item.order.staff.first_name} ${item.order.staff.last_name}</td>
                                            <td>${fn:split(item.product.product_name, ',')[0]}</td>
                                            <td>${quantity[item.order.orderID]}</td>
                                            <td><fmt:formatNumber value="${item.list_price}"/></td>
                                            <td>${item.order.status.status_name}</td>
                                            <td style="width: 200px">
                                                <c:if test="${((item.order.status.getStatus_orderid() == 4 || item.order.status.getStatus_orderid() == 3 || item.order.status.getStatus_orderid() == 5) && sessionScope.user.role.getRoleID() == 4 ) || 
                                                              (item.order.status.getStatus_orderid() != 6 && item.order.status.getStatus_orderid() != 7 && item.order.status.getStatus_orderid() != 4 && item.order.status.getStatus_orderid() != 3 && item.order.status.getStatus_orderid() != 2 && sessionScope.user.role.getRoleID() == 2)}">                                                          
                                                      <span id="statusContainer-${item.order.status.getStatus_orderid()}">
                                                          <i class="fas fa-edit edit-status-icon" 
                                                             data-order-id="${item.order.getOrderID()}" 
                                                             data-status-orderid="${item.order.status.getStatus_orderid()}" 
                                                             style="cursor:pointer; color: #007bff;"></i>
                                                      </span>
                                                </c:if>
                                                <c:if test="${sessionScope.user.getRole().getRoleID() == 3}">                                       
                                                    <span id="saleContainer-${item.order.orderID}">
                                                        <i class="fas fa-edit edit-sale-icon" 
                                                           data-order-id="${item.order.orderID}" 
                                                           style="cursor:pointer; color: #007bff;"></i>
                                                    </span>
                                                </c:if>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
