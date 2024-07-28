<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chỉnh sửa sản phẩm</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="./mktcss/styles.css">
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
                    event.preventDefault(); // Ngăn không cho form gửi theo cách truyền thống

                    var formData = new FormData(this);

                    $.ajax({
                        type: "POST",
                        url: $(this).attr("action"),
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (response) {
                            // Giả sử server trả về "success" nếu thành công
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công!',
                                text: 'Sản phẩm đã được chỉnh sửa thành công.',
                            }).then(function () {
                                window.location.href = "productslist"; // Điều hướng đến trang danh sách sản phẩm
                            });
                        },
                        error: function () {
                            Swal.fire({
                                icon: 'error',
                                title: 'Lỗi!',
                                text: 'Có lỗi xảy ra khi chỉnh sửa sản phẩm.',
                            });
                        }
                    });
                });
            });
        </script>

    </head>
    <body>
        <div class="grid-container" style="background-color: whitesmoke; ">
            <div class="container-xl" style="width: 1200px;background-color: white; margin: 10px 0px 15px 180px">

                <h2>Chỉnh sửa sản phẩm</h2>
                <form id="editProductForm" action="editp" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>ID:</label>
                        <input type="text" name="productID" value="${product.productID}" readonly class="form-control-plaintext">
                    </div>

                    <div class="form-group">
                        <label>Tên:</label>
                        <input type="text" id="productName" name="productName" value="${product.product_name}" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Số lượng:</label>
                        <input type="number" name="quantity" value="${product.quantity}" class="form-control" readonly required>
                    </div>

                    <div class="form-group">
                        <label>Năm:</label>
                        <select name="year" class="form-control" required>
                            <c:forEach var="i" begin="2000" end="2024">
                                <option value="${i}" ${product.year == i ? 'selected' : ''}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Thể loại:</label>
                        <select name="category" class="form-control" required>
                            <c:forEach var="category" items="${category}">
                                <option value="${category.category_productID}" ${category.category_productID == product.categoryProduct.category_productID ? 'selected' : ''}>
                                    ${category.category_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Mô tả:</label>
                        <textarea name="description" class="form-control" required>${product.product_description}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Nổi bật:</label>
                        <select id="featured" name="featured" class="form-control" required>
                            <option value="0" ${product.featured == 0 ? 'selected' : ''}>No</option>
                            <option value="1" ${product.featured == 1 ? 'selected' : ''}>Yes</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Ảnh</label><br>
                        <img class="" width="300px" src="${product.thumbnail}"> <br/>
                        <input type="file" name="file" id="file" accept="image/*" > 

                        <input type="hidden" name="existingImage" value="${product.thumbnail}">
                    </div>

                    <div class="form-group">
                        <label>Mô tả tóm tắt:</label>
                        <textarea name="briefInfo" class="form-control" required>${product.brief_information}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Giá gốc:</label>
                        <input type="number" id="originalPrice" name="originalPrice" value="${product.original_price}" class="form-control"readonly="" required>
                    </div>

                    <div class="form-group">
                        <label>Giá giảm:</label>
                        <input type="number" id="salePrice" name="salePrice" value="${product.sale_price}" class="form-control" readonly="" required>
                    </div>

                    <div class="form-group">
                        <label>Ngày cập nhật:</label>
                        <input type="date" id="updateDate" name="updateDate" value="${product.update_date}" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label for="brand">Thương hiệu:</label>
                        <input type="text" name="brand" id="brand" value="${product.brand}" class="form-control" required>

                    </div>

                    <div class="form-group">
                        <label>Trạng thái:</label>
                        <select name="status" class="form-control" required>
                            <option value="true" ${product.status ? 'selected' : ''}>Hiện</option>
                            <option value="false" ${!product.status ? 'selected' : ''}>Ẩn</option>
                        </select>
                    </div>

                    <div class="form-group d-flex justify-content-between">
                        <a href="productslist" class="btn btn-secondary">Trở về</a>
                        <input type="submit" class="btn btn-success" value="Lưu">
                    </div>
                </form>
            </div>

        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
