<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chỉnh sửa Thanh Trượt</title>
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
            }
            .btn-primary {
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
            <h2>Chỉnh sửa Thanh Trượt</h2>
            <form id="sliderForm" action="SliderServletURL" method="post" enctype="multipart/form-data">
                <input type="hidden" name="sliderID" value="${Slider.sliderID}">
                
                <div class="form-group">
                    <label for="title">Tiêu đề:</label>
                    <input type="text" id="title" name="title" value="${Slider.title}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="image">Ảnh:</label>
                    <img src="${Slider.image}" width="300px" alt="Current Image"> <br/>
                    <input type="file" name="file" id="file" accept="image/*">
                    <input type="hidden" name="existingImage" value="${Slider.image}">
                </div>

                <div class="form-group">
                    <label for="link">Liên kết:</label>
                    <input type="text" id="link" name="link" value="${Slider.link}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="notes">Ghi chú:</label>
                    <input type="text" id="notes" name="notes" value="${Slider.notes}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="status">Trạng thái:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="1" ${Slider.status == 1 ? 'selected' : ''}>Hiện</option>
                        <option value="0" ${Slider.status != 1 ? 'selected' : ''}>Ẩn</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="page_order">Hiện ở Trang:</label>
                    <input type="number" id="page_order" name="page_order" value="${Slider.page_order}" class="form-control" required>
                </div>

                <input type="submit" value="Cập nhật" name="submit" class="btn btn-primary">
                <input type="hidden" value="updateSlider" name="service">
                <a href="SliderServletURL" class="btn btn-secondary">Quay lại danh sách thanh trượt</a>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">
                        ${error}
                    </div>
                </c:if>
            </form>
        </div>
        <script>
            $(document).ready(function () {
                $('#sliderForm').submit(function (e) {
                    e.preventDefault();

                    // Validate form
                    if (!this.checkValidity()) {
                        $(this).addClass('was-validated');
                        return;
                    }

                    var formData = new FormData(this);
                    formData.append("submit", "Update");

                    $.ajax({
                        url: 'SliderServletURL',
                        type: 'POST',
                        data: formData,
                        success: function (response) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công',
                                text: 'Thanh trượt đã được cập nhật thành công!',
                                confirmButtonText: 'OK'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = 'SliderServletURL';
                                }
                            });
                        },
                        error: function () {
                            Swal.fire({
                                icon: 'error',
                                title: 'Lỗi',
                                text: 'Đã xảy ra lỗi khi cập nhật thanh trượt.',
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
