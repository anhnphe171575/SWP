<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Slider</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

.container {
    max-width: 600px;
    margin: 30px auto;
    background: #fff;
    padding: 30px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h1 {
    color: #333;
    text-align: center;
    margin-bottom: 30px;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
}

.form-control, .form-control-file {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
}

.form-control:focus, .form-control-file:focus {
    border-color: #007bff;
    outline: none;
    box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 5px;
    box-shadow: 0 2px #999;
}

.btn:hover {
    background-color: #0056b3;
}

.btn:active {
    background-color: #0056b3;
    box-shadow: 0 1px #666;
    transform: translateY(1px);
}

.btn-secondary {
    background-color: #6c757d;
}

.btn-secondary:hover {
    background-color: #5a6268;
}

.was-validated .form-control:invalid,
.was-validated .form-control-file:invalid {
    border-color: #dc3545;
}

.was-validated .form-control:invalid:focus,
.was-validated .form-control-file:invalid:focus {
    border-color: #dc3545;
    box-shadow: 0 0 0 0.2rem rgba(220, 53, 69, 0.25);
}

@media screen and (max-width: 600px) {
    .container {
        width: 100%;
        margin: 20px auto;
        padding: 20px;
    }
}
        </style>
    </head>
    <body style="background-color:white">
        <div class="container mt-5">
            <h1>Add Slider</h1>
            <form id="sliderForm" action="SliderServletURL" method="post" enctype="multipart/form-data" novalidate>
                <div class="form-group">
                    <label for="title">Tiêu Đề:</label>
                    <input type="text" name="title" id="title" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="file">Ảnh:</label>
                    <input type="file" id="file" name="file" class="form-control-file" required accept="image/*">
                </div>
                <div class="form-group">
                    <label for="link">Link:</label>
                    <input type="text" name="link" id="link" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="notes">Ghi Chú:</label>
                    <input type="text" name="notes" id="notes" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="page_order">Hiện ở trang:</label>
                    <input type="number" name="page_order" id="page_order" class="form-control" required min="1">
                </div>
                <div class="form-group">
                    <label for="status">Trạng Thái:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="1">Show</option>
                        <option value="0">Hide</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="hidden" name="service" value="addSlider">
                    <input type="submit" name="submit" id="submitBtn" class="btn btn-primary" value="Add Slider">
                    <a href="SliderServletURL" class="btn btn-secondary">Back To Slider List</a>
                </div>
            </form>
        </div>

        <script>
            $(document).ready(function () {
                $('#sliderForm').submit(function (e) {
                    e.preventDefault();

                    // Validate form
                    if (!this.checkValidity()) {
                        e.stopPropagation();
                        $(this).addClass('was-validated');
                        return;
                    }

                    var formData = new FormData(this);
                    formData.append("submit", $("#submitBtn").val()); // Append submit button's value

                    $.ajax({
                        url: 'SliderServletURL',
                        type: 'POST',
                        data: formData,
                        success: function (response) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công',
                                text: 'Slider đã được thêm thành công!',
                                confirmButtonText: 'OK'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // Redirect to slider list page
                                    window.location.href = 'SliderServletURL';
                                }
                            });
                        },
                        error: function () {
                            Swal.fire({
                                icon: 'error',
                                title: 'Lỗi',
                                text: 'Đã xảy ra lỗi khi thêm slider!',
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
