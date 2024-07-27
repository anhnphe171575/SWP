<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa loại sản phẩm</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
            background: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Chỉnh sửa loại sản phẩm</h2>
        <form id="sliderForm" action="CategoryServletURL" method="post" enctype="multipart/form-data">
            <input type="hidden" name="category_productID" value="${Slider.category_productID}">
            
            <div class="form-group">
                <label for="title">Loại sản phẩm</label>
                <input type="text" id="title" name="category_name" value="${Slider.category_name}" class="form-control" required maxlength="50">
            </div>

            <div class="form-group">
                <label for="image">Ảnh:</label>
                <img src="${Slider.image}" width="300px" alt="Current Image"> <br/>
                <input type="file" name="file" id="file" accept="image/*">
                <input type="hidden" name="existingImage" value="${Slider.image}">
            </div>

            <div class="form-group">
                <label for="link">Thông tin loại sản phẩm</label>
                <input type="text" id="link" name="category_description" value="${Slider.category_description}" class="form-control" required maxlength="100">
            </div>

            <input type="submit" value="Cập nhật" name="submit" class="btn btn-primary">
            <input type="hidden" value="updateCategory" name="service">
            <a href="CategoryServletURL" class="btn btn-secondary">Quay lại danh sách loại sản phẩm</a>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">
                    ${error}
                </div>
            </c:if>
        </form>
    </div>
    <script>
        function validateForm() {
            var title = document.getElementById('title').value;
            var link = document.getElementById('link').value;
    
            if (title.length > 50) {
                alert('Tiêu đề không được dài hơn 50 ký tự.');
                return false;
            }
            if (link.length > 100) {
                alert('Thông tin loại sản phẩm không được dài hơn 100 ký tự.');
                return false;
            }
    
            return true;
        }
    
        function checkDuplicateName(callback) {
            var categoryName = $('#title').val();
            $.ajax({
                url: 'CategoryServletURL?service=checkDuplicateCategory',
                type: 'POST',
                data: { category_name: categoryName },
                success: function (response) {
                    callback(response.isDuplicate);
                },
                error: function () {
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi',
                        text: 'Đã xảy ra lỗi khi kiểm tra tên loại sản phẩm.',
                        confirmButtonText: 'OK'
                    });
                    callback(false);
                }
            });
        }
    
        $(document).ready(function () {
            $('#sliderForm').submit(function (e) {
                e.preventDefault();
    
                // Validate form
                if (!validateForm()) {
                    return;
                }
    
                checkDuplicateName(function (isDuplicate) {
                    if (isDuplicate) {
                        $('#title').after('<div class="alert alert-danger mt-3" id="duplicateError">Tên loại sản phẩm đã tồn tại.</div>');
                    } else {
                        // Remove previous error message if it exists
                        $('#duplicateError').remove();
    
                        var formData = new FormData($('#sliderForm')[0]);
                        formData.append("submit", "Update");
    
                        $.ajax({
                            url: 'CategoryServletURL',
                            type: 'POST',
                            data: formData,
                            success: function (response) {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Thành công',
                                    text: 'Loại sản phẩm đã được cập nhật thành công!',
                                    confirmButtonText: 'OK'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = 'CategoryServletURL';
                                    }
                                });
                            },
                            error: function () {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Lỗi',
                                    text: 'Đã xảy ra lỗi khi cập nhật loại sản phẩm.',
                                    confirmButtonText: 'OK'
                                });
                            },
                            cache: false,
                            contentType: false,
                            processData: false
                        });
                    }
                });
            });
        });
    </script>
    
</body>
</html>