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
            <form action="editp" method="post">
                <div class="form-group">
                    <label>Product ID:</label>
                    <input type="text" name="productID" value="${product.productID}" readonly>
                </div>

                <div class="form-group">
                    <label>Product Name:</label>
                    <input type="text" name="productName" value="${product.product_name}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Quantity:</label>
                    <input type="number" name="quantity" value="${product.quantity}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Year:</label>
                    <input type="number" name="year" value="${product.year}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Category:</label>
                    <input type="number" name="category" value="${product.categoryProduct.category_productID}" class="form-control" required>
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
                    <label>Thumbnail:</label>
                    <input type="text" name="thumbnail" value="${product.thumbnail}" class="form-control" required>
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
                    <input type="text" name="brand" value="${product.brand}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Status:</label>
                    <select name="status" class="form-control" required> 
                        <option value="true" ${product.status ? 'selected' : ''}>Show</option>
                        <option value="false" ${!product.status ? 'selected' : ''}>Hide</option>
                    </select>
                </div>
                    <a href="productslist" class="btn btn-default">Back to Product List</a>
                    <input type="submit" class="btn btn-success" value="Save">
            </form>
        </div>
    </body>
</html>
