<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link rel="stylesheet" href="vncss/vn5.css"/>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy ngày hiện tại
                var today = new Date().toISOString().split('T')[0];
                // Thiết lập giá trị tối đa cho trường ngày
                document.getElementById("updateDate").setAttribute("max", today);

                // Kiểm tra giá trị của Original Price và Sale Price
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
        <div class="container">
            <h2>Edit Product</h2>
            <form id="editProductForm" action="editp" method="post">
                <div class="form-group">
                    <div>Product ID:</div>
                    <input type="text" name="productID" value="${product.productID}" readonly>
                </div>

                <div class="form-group">
                    <div>Product Name:</div>
                    <input type="text" name="productName" value="${product.product_name}">
                </div>

                <div class="form-group">
                    <div>Price:</div>
                    <input type="number" step="0.01" name="price" value="${product.price}">
                </div>

                <div class="form-group">
                    <div>Quantity:</div>
                    <input type="number" name="quantity" value="${product.quantity}">
                </div>

                <div class="form-group">
                    <div>Year:</div>
                    <input type="number" name="year" value="${product.year}">
                </div>

                <div class="form-group">
                    <div>Category:</div>
                    <input type="number" name="category" value="${product.categoryProduct.category_productID}">
                </div>

                <div class="form-group">
                    <div>Product Description:</div>
                    <textarea name="description">${product.product_description}</textarea>
                </div>

                <div class="form-group">
                    <div>Featured:</div>
                    <input type="number" id="featured" name="featured" value="${product.featured}" min="0" max="1">
                </div>

                <div class="form-group">
                    <div>Thumbnail:</div>
                    <input type="text" name="thumbnail" value="${product.thumbnail}">
                </div>

                <div class="form-group">
                    <div>Brief Information:</div>
                    <textarea name="briefInfo">${product.brief_information}</textarea>
                </div>

                <div class="form-group">
                    <div>Original Price:</div>
                    <input type="number" step="0.01" id="originalPrice" name="originalPrice" value="${product.original_price}">
                </div>

                <div class="form-group">
                    <div>Sale Price:</div>
                    <input type="number" step="0.01" id="salePrice" name="salePrice" value="${product.sale_price}">
                </div>

                <div class="form-group">
                    <div>Update Date:</div>
                    <input type="date" id="updateDate" name="updateDate" value="${product.update_date}">
                </div>

                <div class="form-group">
                    <div>Brand:</div>
                    <input type="text" name="brand" value="${product.brand}">
                </div>

                <div class="form-group">
                    <div>Status:</div>
                    <select name="status" required> 
                        <option value="true" ${product.status ? 'selected' : ''}>Show</option>
                        <option value="false" ${!product.status ? 'selected' : ''}>Hide</option>
                    </select>
                </div>

                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
