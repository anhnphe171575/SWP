<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                background-color: #fff;
                border-radius: 8px;
                padding: 30px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }
            h2 {
                margin-bottom: 20px;
                color: #343a40;
            }
            .form-group label {
                font-weight: bold;
                color: #495057;
            }
            .form-control {
                border-radius: 4px;
            }
            .btn-success {
                background-color: #28a745;
                border-color: #28a745;
            }
            .btn-success:hover {
                background-color: #218838;
                border-color: #1e7e34;
            }
            .btn-secondary {
                background-color: #6c757d;
                border-color: #6c757d;
            }
            .btn-secondary:hover {
                background-color: #5a6268;
                border-color: #545b62;
            }
        </style>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var today = new Date().toISOString().split('T')[0];
                document.getElementById("updateDate").setAttribute("max", today);

                document.getElementById("editProductForm").addEventListener("submit", function (event) {
                    var originalPrice = parseFloat(document.getElementById("originalPrice").value);
                    var salePrice = parseFloat(document.getElementById("salePrice").value);
                    var featured = parseInt(document.getElementById("featured").value);

                    if (originalPrice <= salePrice) {
                        event.preventDefault();
                        alert("Original Price must be greater than Sale Price.");
                    }

                    if (featured !== 0 && featured !== 1) {
                        event.preventDefault();
                        alert("Featured must be 0 or 1.");
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Edit Product</h2>
            <form id="editProductForm" action="editp" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Product ID:</label>
                    <input type="text" name="productID" value="${product.productID}" readonly class="form-control-plaintext">
                </div>

                <div class="form-group">
                    <label>Product Name:</label>
                    <input type="text" name="productName" value="${product.product_name}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Quantity:</label>
                    <input type="number" name="quantity" value="${product.quantity}" class="form-control" readonly required>
                </div>

                <div class="form-group">
                    <label>Year:</label>
                    <input type="number" name="year" value="${product.year}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Category:</label>
                    <select name="category" class="form-control" required>
                        <c:forEach var="category" items="${category}">
                            <option value="${category.category_productID}" ${category.category_productID == product.categoryProduct.category_productID ? 'selected' : ''}>
                                ${category.category_name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Product Description:</label>
                    <textarea name="description" class="form-control" required>${product.product_description}</textarea>
                </div>

                <div class="form-group">
                    <label>Featured:</label>
                    <input type="number" id="featured" name="featured" value="${product.featured}" min="0" max="1" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Thumbnail</label><br>
                    <input type="file" name="file" id="file" style="margin-bottom: 10px" required="">
                </div>

                <div class="form-group">
                    <label>Brief Information:</label>
                    <textarea name="briefInfo" class="form-control" required>${product.brief_information}</textarea>
                </div>

                <div class="form-group">
                    <label>Original Price:</label>
                    <input type="number" step="0.01" id="originalPrice" name="originalPrice" value="${product.original_price}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Sale Price:</label>
                    <input type="number" step="0.01" id="salePrice" name="salePrice" value="${product.sale_price}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Update Date:</label>
                    <input type="date" id="updateDate" name="updateDate" value="${product.update_date}" class="form-control" required>
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
                    <label>Status:</label>
                    <select name="status" class="form-control" required>
                        <option value="true" ${product.status ? 'selected' : ''}>Show</option>
                        <option value="false" ${!product.status ? 'selected' : ''}>Hide</option>
                    </select>
                </div>

                <div class="form-group d-flex justify-content-between">
                    <a href="productslist" class="btn btn-secondary">Back to Product List</a>
                    <input type="submit" class="btn btn-success" value="Save">
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
