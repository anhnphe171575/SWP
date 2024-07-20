<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Vector,Entity.Customer"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa trạng thái</title>
    <!-- Bootstrap CSS -->
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
        <form action="editStatusOrderURL" method="post">
            <div class="form-group">
                <label for="status_orderid">ID:</label>
                <input type="text" id="status_orderid" name="status_orderid" value="${customer.status_orderid}" class="form-control" required readonly>
            </div>

            <div class="form-group">
                <label for="status_name">Tên trạng thái</label>
                <input type="hidden" name="status_nameo" value="${customer.status_name}">
                <input type="text" id="status_name" name="status_name" value="${customer.status_name}" class="form-control" required>
            </div>

            <div class="form-group">
                <input type="submit" name="submit" value="Lưu" class="btn btn-primary">
            </div>
            <input type="hidden" name="service" value="updateStatusOrder">
        </form>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
