<%-- 
    Document   : EditSq
    Created on : Jul 3, 2024, 1:43:54 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Security Question</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-container {
                max-width: 400px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                background-color: #f9f9f9;
            }
            .form-container .btn {
                width: 100%;
            }
            a{
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <div class="form-container">
            <h2 class="text-center">Chỉnh sửa câu hỏi bảo mật</h2>
            <form id="editQuestionForm" action="EditSQ" method="post">
                <div class="form-group">
                    <label for="id">Id:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${sq.securityID}" readonly="">
                </div>
                <div class="form-group">
                    <label for="security_question">Câu hỏi:</label>
                    <input type="hidden" name="status_nameo" value="${sq.security_question}">
                    <input type="text" class="form-control" id="security_question" name="security_question" value="${sq.security_question}" required>
                </div>
                <button type="submit" class="btn btn-info">Thay đổi</button>
            </form>
            <a href="SecurityQuestion" class="btn btn-secondary">Quay lại menu</a>
        </div>
    </div>

    <!-- Include jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Include SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- Include Bootstrap and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
    $(document).ready(function() {
        $('#editQuestionForm').submit(function(e) {
            e.preventDefault();

            // Validate form
            if (!this.checkValidity()) {
                e.stopPropagation();
                $(this).addClass('was-validated');
                return;
            }

            var formData = $(this).serialize();

            $.ajax({
                url: $(this).attr('action'), // Use the form's action attribute
                type: 'POST',
                data: formData,
                success: function(response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công',
                        text: 'Câu hỏi đã được chỉnh sửa thành công!',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = 'SecurityQuestion';
                        }
                    });
                },
                error: function() {
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi',
                        text: 'Đã xảy ra lỗi khi chỉnh sửa câu hỏi!',
                        confirmButtonText: 'OK'
                    });
                }
            });
        });
    });
    </script>
</body>
</html>
