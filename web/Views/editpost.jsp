<%-- 
    Document   : editpost
    Created on : May 26, 2024, 10:38:41 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
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
        <style>
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group div {
                margin-bottom: 5px;
            }
            input[type="text"], input[type="number"], textarea, select {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                padding: 10px 15px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Edit Post</h2>
            <form action="EditPost" method="post">
                <div class="form-group">
                    <input type="hidden" name="postID" value="${post.postID}" >
                    <div>Title:</div>
                    <input type="text" name="title" value="${post.title}" >
                </div>

                <div class="form-group">
                    <div>Thumbnail::</div>
                    <img class="rounded-circle mt-5" width="150px" src="${post.thumbnail}">
                    <input type="file" name="file" id="file" accept="image/*" >

                </div>

                <div class="form-group">
                    <div>Category</div>
                    <input type="number"  name="category_postID" value="${post.cp.category_postID}">
                </div>
                <div class="form-group">
                    <div>Featured:</div>
                    <select name="featured" required> 
                        <option value="1" ${post.featured == 1 ? 'selected' : ''}>Common</option>
                        <option value="0" ${post.featured != 1 ? 'selected' : ''}>No common</option>
                    </select>
                </div>

                <div class="form-group">
                    <div>Status:</div>
                    <select name="status" required> 
                        <option value="1" ${post.status == 1 ? 'selected' : ''}>Show</option>
                        <option value="0" ${post.status != 1 ? 'selected' : ''}>Hide</option>
                    </select>

                </div>               


                <div class="form-group">
                    <div>Brief-Information</div>
                    <input type="text" name="brief_information" value="${post.brief_information}">
                </div>

                <div class="form-group">
                    <div>Description:</div>
                    <textarea name="description">${post.description}</textarea>
                </div>
                <c:if test="${detail == '0'}">
                    <input type="hidden" name="service" value="editDetail">
                </c:if>
                <c:if test="${detail == '1'}">
                    <input type="hidden" name="service" value="editList">
                </c:if> 
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>

