<%-- 
    Document   : addProduct
    Created on : May 23, 2024, 9:56:49 AM
    Author     : MANH VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
        <link rel="stylesheet" href="vncss/vn3.css">
    </head>
    <body>
        <div class="container">
            <h1>Add Product</h1>      
            <form action="addp" method="post">
                <div style="color: red">${requestScope.msg}</div>
                <div class="form-group">
                    <div class="label">Product Name:</div>
                    <input type="text" name="productName" required>
                </div>

                <div class="form-group">
                    <div class="label">Price:</div>
                    <input type="number" name="price" min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <div class="label">Quantity:</div>
                    <input type="number" name="quantity" min="0" required>
                </div>
                <div class="form-group">
                    <div class="label">Year:</div>
                    <input type="text" name="year"required>
                </div>

                <div class="form-group">
                    <div class="label">Category ID:</div>
                    <input type="number" name="categoryID" min="0" required>
                </div>

                <div class="form-group">
                    <div class="label">Description:</div>
                    <textarea name="description" rows="4" cols="50" required></textarea>
                </div>

                <div class="form-group">
                    <div class="label">Featured:</div>
                    <select class="form-control" name="featured" required>
                        <option value="0">Yes</option>
                        <option value="1">No</option>
                    </select>
                </div>

                <div class="form-group">
                    <div class="label">Thumbnail URL:</div>
                    <input type="text" name="thumbnail" required>
                </div>

                <div class="form-group">
                    <div class="label">Brief Information:</div>
                    <input type="text" name="briefInfo" required>
                </div>

                <div class="form-group">
                    <div class="label">Original Price:</div>
                    <input type="number" name="originalPrice" min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <div class="label">Sale Price:</div>
                    <input type="number" name="salePrice" min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <div class="label">Brand:</div>
                    <input type="text" name="brand" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
    </body>
</html>
