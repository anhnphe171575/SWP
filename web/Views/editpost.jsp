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
        <title>Edit Post</title>
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
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }

            .container {
                max-width: 600px;
                margin: 50px auto;
                background: #fff;
                padding: 30px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            h2 {
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group div {
                font-weight: bold;
                margin-bottom: 5px;
                color: #555;
            }

            input[type="text"],
            input[type="number"],
            textarea,
            select,
            input[type="file"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }

            textarea {
                height: 100px;
                resize: vertical;
            }

            input[type="submit"] {
                width: 100%;
                padding: 12px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }

            img {
                max-width: 100%;
                height: auto;
                margin-bottom: 10px;
            }

            /* Responsive design */
            @media screen and (max-width: 600px) {
                .container {
                    width: 100%;
                    margin: 20px auto;
                    padding: 20px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Chỉnh Sửa Bài Viết</h2>
            <form id="editPostForm" action="EditPost" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="hidden" name="postID" value="${post.postID}" >
                    <div>Tiêu đề:</div>
                    <input type="text" name="title" value="${post.title}" >
                </div>

                <div class="form-group">
                    <div>Ảnh:</div>
                    <img class="" width="300px" src="${post.thumbnail}"> <br/>
                    <input type="file" name="file" id="file" accept="image/*" > 

                    <input type="hidden" name="existingImage" value="${post.thumbnail}">

                </div>

                <div class="form-group">
                    <div>Thể loại:</div>
                    <select id="category-select" class="form-control" name="category_postID">
                        <c:forEach items="${requestScope.category_product}" var="c">
                            <option value="${c.category_productID}" ${c.category_productID == post.cp.category_postID ? 'selected' : ''}>
                                ${c.category_name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <div>Nổi bật</div>
                    <select name="featured" required> 
                        <option value="1" ${post.featured == 1 ? 'selected' : ''}>Common</option>
                        <option value="0" ${post.featured != 1 ? 'selected' : ''}>No common</option>
                    </select>
                </div>

                <div class="form-group">
                    <div>Trạng thái</div>
                    <select name="status" required> 
                        <option value="1" ${post.status == 1 ? 'selected' : ''}>Show</option>
                        <option value="0" ${post.status != 1 ? 'selected' : ''}>Hide</option>
                    </select>

                </div>               


                <div class="form-group">
                    <div>Tóm tắt</div>
                    <input type="text" name="brief_information" value="${post.brief_information}">
                </div>

                <div class="form-group">
                    <div>Chi tiết:</div>
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                    $(document).ready(function () {
                        $('#editPostForm').submit(function (e) {
                            e.preventDefault();

                            // Validate form
                            if (!this.checkValidity()) {
                                e.stopPropagation();
                                $(this).addClass('was-validated');
                                return;
                            }

                            var formData = new FormData(this);

                            $.ajax({
                                url: 'EditPost',
                                type: 'POST',
                                data: formData,
                                success: function (response) {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Thành công',
                                        text: 'Bài viết đã được cập nhật thành công!',
                                        confirmButtonText: 'OK'
                                    }).then((result) => {
                                        window.location.href = 'PostController';
                                    });
                                },
                                error: function () {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Lỗi',
                                        text: 'Đã xảy ra lỗi khi cập nhật bài viết!',
                                        confirmButtonText: 'OK'
                                    });
                                },
                                cache: false,
                                contentType: false,
                                processData: false
                            });
                        });
                    });
        </script>
    </body>
</html>

