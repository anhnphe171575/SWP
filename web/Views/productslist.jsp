<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Product List</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="./mktcss/styles.css">
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
            $(document).ready(function () {
                // Edit Price Modal
                $('.edit-price-icon').click(function () {
                    var productId = $(this).data('product-id');
                    var price = $(this).data('price');

                    $('#productIdPrice').val(productId);
                    $('#price').val(price);

                    $('#editPriceModal').modal('show');
                });

                $('#savePriceBtn').click(function () {
                    var productId = $('#productIdPrice').val();
                    var price = $('#price').val();

                    $.ajax({
                        url: 'updatePrice',
                        type: 'POST',
                        data: {
                            productId: productId,
                            price: price
                        },
                        success: function (response) {
                            $('#editPriceModal').modal('hide');
                            alert('Price updated successfully');
                            location.reload();
                        },
                        error: function (xhr, status, error) {
                            alert('Error updating price');
                        }
                    });
                });

                // Edit Sale Price Modal
                $('.edit-sale-price-icon').click(function () {
                    var productId = $(this).data('product-id');
                    var salePrice = $(this).data('sale-price');
                    var originalPrice = $(this).data('original-price');

                    $('#productIdSalePrice').val(productId);
                    $('#salePrice').val(salePrice);
                    $('#originalPrice').val(originalPrice); // Lưu giá gốc vào thẻ đầu vào ẩn
                    $('#editSalePriceModal').modal('show');
                });

                $('#saveSalePriceBtn').click(function () {
                    var productId = $('#productIdSalePrice').val();
                    var salePrice = $('#salePrice').val();
                    var originalPrice = $('#originalPrice').val(); 

                    if (parseFloat(salePrice) >= parseFloat(originalPrice)) {
                        alert('Sale Price must be less than Price');
                        return;
                    }

                    $.ajax({
                        url: 'updateSalePrice',
                        type: 'POST',
                        data: {
                            productId: productId,
                            salePrice: salePrice
                        },
                        success: function (response) {
                            $('#editSalePriceModal').modal('hide');
                            alert('Sale Price updated successfully');
                            location.reload(); // Reload lại trang sau khi cập nhật thành công
                        },
                        error: function (xhr, status, error) {
                            alert('Error updating sale price');
                        }
                    });
                });

                // Other existing JavaScript code
                $('[data-toggle="tooltip"]').tooltip();

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

                $('.table').DataTable({
                    "paging": true,
                    "searching": false,
                    "ordering": false,
                    "info": false,
                    "pageLength": 10
                });

                $('.edit-quantity-icon').click(function () {
                    var productId = $(this).data('product-id');
                    var quantity = $(this).data('quantity');

                    $('#productId').val(productId);
                    $('#quantity').val(quantity);

                    $('#editQuantityModal').modal('show');
                });

                $('#saveQuantityBtn').click(function () {
                    var productId = $('#productId').val();
                    var quantity = $('#quantity').val();

                    $.ajax({
                        url: 'updateQuantity', // Endpoint để cập nhật số lượng
                        type: 'POST',
                        data: {
                            productId: productId,
                            quantity: quantity
                        },
                        success: function (response) {
                            // Cập nhật số lượng trong bảng
                            $('#quantityContainer-' + productId).html(quantity + ' <i class="fas fa-edit edit-quantity-icon" data-product-id="' + productId + '" data-quantity="' + quantity + '" style="cursor:pointer; color: #007bff;"></i>');
                            $('#editQuantityModal').modal('hide');
                            alert('Update successful');
                        },
                        error: function (xhr, status, error) {
                            alert('Error updating quantity');
                        }
                    });
                });

                function validateForm() {
                    var title = document.forms["searchForm"]["title"].value;
                    var brief = document.forms["searchForm"]["brief"].value;

                    if (title && brief) {
                        alert("Please enter only one search criteria.");
                        return false;
                    }
                    if (!title && !brief) {
                        alert("Please enter a search criteria.");
                        return false;
                    }
                    return true;
                }
            });
        </script>
    </head>
    <body>
        <div class="grid-container">
            <jsp:include page="header.jsp"></jsp:include>
                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>

                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <a href="productslist" style="color: white"><h2>Manage <b>Product</b></h2></a>
                                    </div>
                                    
                                    <div style="text-align: right" class="col-sm-3">
                                        <form name="searchForm" action="productslist" method="post" onsubmit="return validateForm();">
                                            <div><input type="text" id="searchTitle" name="title" placeholder="Title"></div>
                                            <div style="margin: 5px 0px 5px 0px;"><input type="text" id="searchBrief" name="brief" placeholder="Brief Information"></div>                     
                                            <input type="submit" name="submit" value="Search">
                                            <input type="hidden" name="service" value="search">                                                                      
                                        </form>                    
                                    </div>
                                    <div class="col-sm-6">
                                        <a href="#Sort" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xe164;</i> <span>Sort</span></a>
                                        <a href="addp" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add new Product</span></a>
                                    </div>
                                </div>
                                
                                <div>
                                <c:if test="${not empty msg}">
                                    <div style="color: red; font-size: 20px;">${msg}</div>
                                </c:if>
                                <c:if test="${not empty msgUpdate}">
                                    <div style="color: red; font-size: 20px;">${msgUpdate}</div>
                                </c:if> 
                            </div>
                            </div>
                            
                        </div>
                        <form action="productslist" method="post">
                            <div class="filter-container d-flex flex-wrap align-items-center">
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Category:</span>
                                        </div>
                                        <select id="product-filter" name="category" class="form-control">
                                        <option value="all">All</option>
                                        <c:forEach items="${requestScope.category}" var="p">
                                            <option value="${c}">${c}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> Status:</span>
                                        </div>
                                        <select class="custom-select" id="status" name="status">
                                            <option value="all">All</option>
                                            <option value="0">Hide</option>
                                        <option value="1">Show</option>
                                        </select>
                                    </div>
                                </div>

                                <input type="hidden" value="filter" name="service">
                                <div class="col-md-3" >
                                    <input type="submit" value="Filter" class="btn btn-primary" style="margin-top: 0px">
                                </div>
                                
                            </div>
                        </form>
                        <c:if test="${sessionScope.user.getRole().getRoleID() != 4}">
                            
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Thumbnail</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Sale Price</th>
                                        <th>Featured</th>
                                        <th>Status</th>
                                        <th class="actions">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="product" >
                                        <tr>                                  
                                            <td>${product.productID}</td>
                                            <td>${product.product_name}</td>
                                            <td><img src="${product.thumbnail}" class="img"></td>
                                            <td>${product.quantity}</td>
                                            <td>${product.original_price}</td>
                                            <td>${product.sale_price}</td>
                                            <td>${product.featured == 1 ? 'Yes' : 'No'}</td>
                                            <td>${product.status ? 'Show' : 'Hide'}</td>
                                            <td class="actions">
                                                <c:if test="${product.status == true}">
                                                    <a title="Hide" onclick="location.href = 'update?action=hide&id=${product.productID}'"><i class="fas fa-eye-slash" style="color: red;"></i></a>
                                                    </c:if>
                                                    <c:if test="${product.status == false}">
                                                    <a title="Show" onclick="location.href = 'update?action=show&id=${product.productID}'"><i class="fas fa-eye" style="color: red;"></i></a>
                                                    </c:if>
                                                <a title="View" onclick="location.href = 'view?vid=${product.productID}'"><i class="fas fa-search"></i></a>
                                                <a title="Edit" onclick="location.href = 'editp?eid=${product.productID}'"><i class="fas fa-edit"></i></a>
                                                    <c:if test="${product.quantity == 0}">
                                                    <a title="Delete" onclick="if (confirm('Are you sure you want to delete this product?'))
                                                                location.href = 'deleteProduct?did=${product.productID}'"><i class="fas fa-trash-alt"></i></a>
                                                    </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${sessionScope.user.getRole().getRoleID() == 4}">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Thumbnail</th>
                                        <th>Quantity</th>
                                        <th>Quantity Hold</th>
                                        <th>Price</th>
                                        <th>Sale Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="product">
                                        <tr>
                                            <td>${product.productID}</td>
                                            <td>${product.product_name}</td>
                                            <td><img src="${product.thumbnail}" class="img"></td>
                                            <td>
                                                <span id="quantityContainer-${product.productID}">
                                                    ${product.quantity}
                                                    <i class="fas fa-edit edit-quantity-icon" data-product-id="${product.productID}" data-quantity="${product.quantity}" style="cursor:pointer; color: #007bff;"></i>
                                                </span>
                                            </td>
                                            <td>${product.quantity_hold}</td>
                                            <td>${product.original_price}
                                                <i class="fas fa-edit edit-price-icon" data-product-id="${product.productID}" data-price="${product.original_price}" style="cursor:pointer; color: #007bff;"></i>
                                            </td>
                                            <td>${product.sale_price}
                                                <i class="fas fa-edit edit-sale-price-icon" data-product-id="${product.productID}" data-sale-price="${product.sale_price}" data-original-price="${product.original_price}" style="cursor:pointer; color: #007bff;"></i>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>        
                </div>
                <!-- Edit Price Modal -->
                <div id="editPriceModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Edit Price</h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="productIdPrice">
                                <div class="form-group">
                                    <label for="price">Price:</label>
                                    <input type="number" id="price" class="form-control" min="0">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="savePriceBtn">Save</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Edit Sale Price Modal -->
                <div id="editSalePriceModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Edit Sale Price</h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="productIdSalePrice">
                                <input type="hidden" id="originalPrice"> 
                                <div class="form-group">
                                    <label for="salePrice">Sale Price:</label>
                                    <input type="number" id="salePrice" class="form-control" min="0">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="saveSalePriceBtn">Save</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Edit quantity -->    
                <div id="editQuantityModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Edit Quantity</h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="productId">
                                <div class="form-group">
                                    <label for="quantity">Quantity:</label>
                                    <input type="number" id="quantity" class="form-control" min="0">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="saveQuantityBtn">Save</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Filter Modal HTML -->
                
                <!-- Sort Modal HTML -->
                <div id="Sort" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Sort By</h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <form action="productslist" method="post">
                                <div class="modal-body">
                                    <!-- Sort Options -->
                                    <div class="form-group">
                                        <label for="sort-select">Sort By:</label>
                                        <select id="sort-select" class="form-control" name="sort">
                                            <option value="title">Title</option>
                                            <option value="category">Category</option>
                                            <option value="price">List Price</option>
                                            <option value="saleprice">Sale Price</option>
                                            <option value="featured">Featured</option>
                                            <option value="status">Status</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Sort</button>
                                    <input type="hidden" name="service" value="sort">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    </body>
</html>
