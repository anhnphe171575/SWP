<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm sản phẩm</title>
        <!-- CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                background-color: #f0f2f5;
                font-family: 'Roboto', sans-serif;
            }
            .container-xl {
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                padding: 30px;
                margin-top: 50px;
                margin-bottom: 50px;
            }
            h2 {
                color: #333;
                font-weight: 600;
                margin-bottom: 30px;
                border-bottom: 2px solid #28a745;
                padding-bottom: 10px;
            }
            .form-group label {
                font-weight: 500;
                color: #555;
            }
            .form-control {
                border: 1px solid #ced4da;
                border-radius: 4px;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            .form-control:focus {
                border-color: #80bdff;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }
            .btn {
                border-radius: 4px;
                font-weight: 500;
                padding: 10px 20px;
                transition: all 0.3s;
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
            select.form-control {
                appearance: none;
                -webkit-appearance: none;
                -moz-appearance: none;
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 12 12'%3E%3Cpath fill='%23555' d='M10.293 3.293L6 7.586 1.707 3.293A1 1 0 00.293 4.707l5 5a1 1 0 001.414 0l5-5a1 1 0 10-1.414-1.414z'/%3E%3C/svg%3E");
                background-repeat: no-repeat;
                background-position: right 10px center;
                padding-right: 30px;
            }
            textarea.form-control {
                min-height: 100px;
            }
            input[type="file"] {
                border: 1px solid #ced4da;
                border-radius: 4px;
                padding: 10px;
                width: 100%;
            }
            img {
                max-width: 100%;
                height: auto;
                border-radius: 4px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container-xl">
            <h2>Thêm sản phẩm</h2>
            <form id="addp" action="addp" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="productName">Tên sản phẩm:</label>
                    <input type="text" id="productName" name="productName" class="form-control" required maxlength="30">
                    <small id="productNameError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="quantity">Số lượng:</label>
                    <input type="number" id="quantity" name="quantity" value="0" class="form-control" readonly required>
                </div>

                <div class="form-group">
                    <label for="hold">Sản phẩm đang xử lý:</label>
                    <input type="number" id="hold" name="hold" value="0" class="form-control" readonly required>
                </div>

                <div class="form-group">
                    <label for="year">Năm:</label>
                    <select id="year" name="year" class="form-control" required>
                        <c:forEach var="i" begin="2000" end="2024">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="category">Thể loại:</label>
                    <select id="category" name="categoryID" class="form-control" required>
                        <c:forEach var="category" items="${category}">
                            <option value="${category.category_productID}">${category.category_name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Mô tả:</label>
                    <textarea id="description" name="description" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="featured">Đặc biệt:</label>
                    <select id="featured" name="featured" class="form-control" required>
                        <option value="0">Không</option>
                        <option value="1">Có</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="file">Ảnh sản phẩm:</label>
                    <input type="file" name="file" id="file" accept="image/*" class="form-control-file" required>
                </div>

                <div class="form-group">
                    <label for="briefInfo">Mô tả tóm tắt:</label>
                    <input type="text" id="briefInfo" name="briefInfo" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="originalPrice">Giá gốc:</label>
                    <input type="number" id="originalPrice" name="originalPrice" value="0" class="form-control" readonly required>
                </div>

                <div class="form-group">
                    <label for="salePrice">Giá giảm:</label>
                    <input type="number" id="salePrice" name="salePrice" value="0" class="form-control" readonly required>
                </div>

                <div class="form-group">
                    <label for="brand">Thương hiệu:</label>
                    <input type="text" id="brand" name="brand" class="form-control" maxlength="30" required>
                </div>

                <div class="form-group">
                    <label for="status">Trạng thái:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="0">Ẩn</option>
                        <option value="1">Hiện</option>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Thêm sản phẩm</button>
                    <a href="productslist" class="btn btn-secondary">Hủy</a>
                </div>
            </form>
        </div>
        <script>
            $(document).ready(function () {
                // Xử lý form submit
                $("#addp").submit(async function (event) {
                    event.preventDefault();

                    // Kiểm tra tính hợp lệ của form
                    if (this.checkValidity() === false) {
                        event.stopPropagation();
                        $(this).addClass('was-validated');
                        return;
                    }

                    const productName = $('#productName').val().trim();
                    const brand = $('#brand').val().trim();

                    // Kiểm tra các trường nhập liệu
                    if (productName.length === 0) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Tên sản phẩm không được để trống và không được chỉ chứa khoảng trắng!',
                            confirmButtonText: 'OK'
                        });
                        return;
                    }

                    if (brand.length === 0) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Tên thương hiệu không được để trống và không được chỉ chứa khoảng trắng!',
                            confirmButtonText: 'OK'
                        });
                        return;
                    }

                    // Xử lý việc gửi form nếu không có lỗi
                    const formData = new FormData(this);

                    try {
                        const response = await $.ajax({
                            type: "POST",
                            url: $(this).attr("action"),
                            data: formData,
                            processData: false,
                            contentType: false
                        });

                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công!',
                            text: 'Sản phẩm đã được thêm thành công.',
                        }).then(() => {
                            window.location.href = "productslist"; // Điều hướng đến trang danh sách sản phẩm
                        });
                    } catch (error) {
                        console.error("AJAX Error: ", error);
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi!',
                            text: 'Có lỗi xảy ra khi thêm sản phẩm.',
                        });
                    }
                });
            });
        </script>
    </body>
</html>
