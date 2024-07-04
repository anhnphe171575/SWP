<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : MANH VINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <title>Product Details</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <style>
            td img {
                width: 300px;
                height: auto;
                border: 2px solid #ccc;
                border-radius: 5px;
                padding: 5px;
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
            }
            .thumbnail img {
                height: auto;
            }

            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 16px;
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
            /* Custom styles for Edit button */
            .btn-edit {
                background-color: #28a745;
                border-color: #28a745;
                color: white;
            }
            .btn-edit:hover {
                background-color: #218838;
                border-color: #1e7e34;
            }
            .flag-container {
                position: relative;
                display: inline-block;
            }
            .flag-container .fa-flag {
                color: #435d7d;
            }
            .flag-container .fa-ban {
                position: absolute;
                top: 0;
                left: 0;
                color: red;
                font-size: 1.2em;
            </style>
            <script>
                $(document).ready(function () {
                    var currentDate = new Date().toISOString().split('T')[0];
                    var currentYear = new Date().getFullYear();

                    // Set the max attribute for the update_date input to the current date
                    $("#update_date").attr("max", currentDate);

                    // Restrict the year input to the current year
                    $("#year").on("input", function () {
                        var year = parseInt($(this).val());
                        if (year > currentYear) {
                            $(this).val(currentYear);
                        }
                    });

                    // Edit button click event
                    $(document).on("click", ".edit", function () {
                        var productId = "${product.productID}";
                        var productName = "${product.product_name}";
                        var productThumbnail = "${product.thumbnail}";
                        var productPrice = "${product.original_price}";
                        var productCategory = "${product.categoryProduct.category_name}";
                        var briefInformation = "${product.brief_information}";
                        var attachedImages = "${product.categoryProduct.image}";
                        var productDescription = "${product.product_description}";
                        var quantity = "${product.quantity}";
                        var salePrice = "${product.sale_price}";
                        var featured = "${product.featured}";
                        var status = "${product.status}";
                        var year = "${product.year}";
                        var updateDate = "${product.update_date}";

                        // Populate the edit form with the existing product data
                        $("#product_id").val(productId);
                        $("#product_name").val(productName);
                        $("#thumbnail").val(productThumbnail);
                        $("#price").val(productPrice);
                        $("#category").val(productCategory);
                        $("#brief_information").val(briefInformation);
                        $("#attached_images").val(attachedImages);
                        $("#product_description").val(productDescription);
                        $("#quantity").val(quantity);
                        $("#sale_price").val(salePrice);
                        $("#featured").val(featured);
                        $("#status").val(status);
                        $("#year").val(year);
                        $("#update_date").val(updateDate);

                        // Show the edit modal
                        $("#editModal").modal("show");
                    });
                });
            </script>
        </head>
        <body>
            <div class="container">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-8"><h2>Product <b>Details</b></h2></div>
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
                                <c:if test="${not empty product}">
                                    <tr>
                                        <td>Thumbnail:</td>
                                        <td><img src="${product.thumbnail}" alt="Thumbnail" class="thumbnail"></td>
                                    </tr>
                                    <tr>
                                        <td>Category Product:</td>
                                        <td>${product.categoryProduct.category_name}</td>                                    
                                    </tr>
                                    <tr>
                                        <td>Title:</td>
                                        <td>${product.product_name}</td>
                                    </tr>
                                    <tr>
                                        <td>Brief Information:</td>
                                        <td>${product.brief_information}</td>
                                    </tr>
                                    <tr>
                                        <td>Attached images:</td>
                                        <td><img src="${product.categoryProduct.image}" alt="Attached Image"></td>
                                    </tr>
                                    <tr>
                                        <td>Product Description:</td>
                                        <td>${product.product_description}</td>
                                    </tr>
                                    <tr>
                                        <td>Quantity:</td>
                                        <td>${product.quantity}</td>
                                    </tr>
                                    <tr>
                                        <td>Original Price:</td>
                                        <td>${product.original_price}</td>
                                    </tr>
                                    <tr>
                                        <td>Sale Price:</td>
                                        <td>${product.sale_price}</td>
                                    </tr>
                                    <tr>
                                        <td>Featured:</td>
                                        <td>
                                            ${product.featured == 1 ? "Yes" : "No"}
                                            <c:if test="${product.featured == 1}">
                                                <a title="Off" onclick="location.href = 'turnfeatured?action=off&id=${product.productID}'"><i class="fas fa-flag-checkered"></i></a>
                                                </c:if>
                                                <c:if test="${product.featured == 0}">
                                                <a title="On" onclick="location.href = 'turnfeatured?action=on&id=${product.productID}'"><i class="fas fa-flag"></i></a>
                                                </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Status:</td>
                                        <td>${product.status ? "Show" : "Hide"}
                                            <c:if test="${product.status == true}">
                                                <a title="Hide" onclick="location.href = 'update?action=hide&id=${product.productID}'"><i class="fas fa-eye-slash"></i></a>
                                                </c:if>
                                                <c:if test="${product.status == false}">
                                                <a title="Show" onclick="location.href = 'update?action=show&id=${product.productID}'"><i class="fas fa-eye"></i></a>
                                            </c:if></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="text-right">
                                            <button><a href="productslist">Back To Products List</a></button>
                                            <button type="button" class="btn btn-primary edit" data-toggle="modal" data-target="#editProductModal">
                                                <i class="fas fa-edit"></i> Edit
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Edit Modal HTML -->
            <div id="editProductModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editp" method="post" enctype="multipart/form-data">
                            <div class="modal-header">
                                <h4 class="modal-title">Edit Product</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="productID" value="${product.productID}">
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input type="text" name="productName" class="form-control" value="${product.product_name}">
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input type="number" name="quantity" class="form-control" readonly value="${product.quantity}">
                                </div>
                                <div class="form-group">
                                    <label for="year">Year:</label>
                                    <input type="number" class="form-control" id="year" name="year" value="${product.year}" required>
                                </div>
                                <div class="form-group">
                                    <label>Category Product ID</label>
                                    <input type="number" name="category" class="form-control" value="${product.categoryProduct.category_productID}">
                                </div>
                                <div class="form-group">
                                    <label>Product Description</label>
                                    <textarea name="description" class="form-control">${product.product_description}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Thumbnail</label><br>
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <input type="file" name="file" id="file" style="margin-bottom: 10px" required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>Brief Information</label>
                                        <textarea name="briefInfo" class="form-control">${product.brief_information}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Original Price</label>
                                        <input type="number" step="0.01" name="originalPrice" class="form-control" value="${product.original_price}">
                                    </div>
                                    <div class="form-group">
                                        <label>Sale Price</label>
                                        <input type="number" step="0.01" name="salePrice" class="form-control" value="${product.sale_price}">
                                    </div>
                                    <div class="form-group">
                                        <label for="update_date">Update Date:</label>
                                        <input type="date" class="form-control" id="update_date" name="updateDate" value="${product.update_date}" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Brand:</label>
                                        <select name="brand" class="form-control" required>
                                            <c:forEach var="brand" items="${brand}">
                                                <option value="${brand}" ${brand == product.brand ? 'selected' : ''}>${brand}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Featured:</label>
                                        <input type="number" id="featured" name="featured" value="${product.featured}" min="0" max="1" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Status</label>
                                        <select name="status" class="form-control" required>
                                            <option value="0">Hide</option>
                                            <option value="1">Show</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Save Changes">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </body>
        </html>
