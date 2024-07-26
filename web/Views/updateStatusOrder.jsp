<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Vector, Entity.Customer" %>
<%@ page import="Entity.Security" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chỉnh sửa trạng thái</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .container {
                max-width: 600px;
                margin-top: 50px;
                background: #fff;
                padding: 30px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            .form-group label {
                font-weight: bold;
            }
            h2 {
                margin-bottom: 20px;
            }
            .btn-primary {
                width: 100%;
                background-color: #007bff;
                border: none;
                padding: 10px;
                border-radius: 5px;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
            .btn-secondary {
                width: 100%;
                background-color: #6c757d;
                border: none;
                padding: 10px;
                border-radius: 5px;
            }
            .btn-secondary:hover {
                background-color: #565e64;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Chỉnh sửa trạng thái</h2>
            <form id="editStatusForm" action="editStatusOrderURL" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="status_orderid">ID:</label>
                    <input type="number" id="status_orderid" name="status_orderid" value="${customer.status_orderid}" class="form-control"  readonly>
                </div>
                <div class="form-group">
                    <label for="status_name">Tên trạng thái</label>
                    <input type="hidden" name="status_nameo" value="${customer.status_name}">
                    <input type="text" id="status_name" name="status_name" value="${customer.status_name}" class="form-control" required>
                    <div class="invalid-feedback">
                        Vui lòng nhập tên trạng thái.
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>

        <!-- Include jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Include SweetAlert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Include Bootstrap JS and Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#editStatusForm').submit(function (e) {
                    e.preventDefault();

                    if (!this.checkValidity()) {
                        e.stopPropagation();
                        $(this).addClass('was-validated');
                        return;
                    }

                    var formData = $(this).serialize();
                    $.ajax({
                        url: $(this).attr('action'),
                        type: 'POST',
                        data: formData,
                        success: function (response) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công',
                                text: 'Trạng thái đã được chỉnh sửa thành công!',
                                confirmButtonText: 'OK'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = 'editStatusOrderURL'; // Adjust this URL
                                }
                            });
                        },
                        error: function (xhr, status, error) {
                            console.log(xhr.responseText); // In ra phản hồi chi tiết từ máy chủ
                            Swal.fire({
                                icon: 'error',
                                title: 'Lỗi',
                                text: 'Đã xảy ra lỗi khi chỉnh sửa trạng thái: ' + error,
                                confirmButtonText: 'OK'
                            });
                        }
                    });
                });
            });
        </script>
    </body>

</html>
