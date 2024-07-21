<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Slider</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
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
        <h2>Chỉnh sửa Thanh trượt </h2>
            <form action="SliderServletURL" method="post" id="editSliderForm">
                <input type="hidden" name="sliderID" value="${Slider.sliderID}">

                <div class="form-group">
                    <label for="title">Tiêu đề:</label>
                    <input type="text" id="title" name="title" value="${Slider.title}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="image">Ảnh</label>
                    <input type="text" id="image" name="image" value="${Slider.image}" class="form-control" required>
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

                <input type="submit" value="Cập nhập" name="submit" class="btn btn-primary">
                <input type="hidden" value="updateSlider" name="service">
                ${error}
            </form>
        </div>
        <!-- Bootstrap JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
