<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Vector,Entity.Customer"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Role</title>
    <link rel="stylesheet" href="vncss/vn5.css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            box-sizing: border-box;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group input[readonly] {
            background-color: #f9f9f9;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Chỉnh sửa vai trò</h2>
        <form id="editRoleForm" action="editRoleURL" method="post">
            <div class="form-group">
                <label>ID:</label>
                <input type="text" name="RoleID" value="${customer.getRoleID()}" required readonly class="form-control">
            </div>

            <div class="form-group">
                <label>Vai trò:</label>
                <input type="hidden" name="status_nameo" value="${customer.getRole_Name()}">
                <input type="text" name="Role_Name" value="${customer.getRole_Name()}" class="form-control" required>
            </div>

            <input type="submit" name="submit" value="Lưu" class="btn btn-primary">
            <input type="hidden" name="service" value="updateRole">
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
            $('#editRoleForm').submit(function (e) {
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
                    success: function (response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: 'Vai trò đã được chỉnh sửa thành công!',
                            confirmButtonText: 'OK'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                // Redirect to the specified URL
                                window.location.href = 'editRoleURL'; // Ensure this is the correct URL
                            }
                        });
                    },
                    error: function () {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Đã xảy ra lỗi khi chỉnh sửa vai trò!',
                            confirmButtonText: 'OK'
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>
