<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Vector, Entity.Customer" %>
<%@ page import="Entity.Security" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Customer</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
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
    margin: 50px auto;
    background: #fff;
    padding: 30px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h2 {
    color: #333;
    text-align: center;
    margin-bottom: 30px;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
    color: #555;
}

.form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
}

.btn-primary,
.btn-secondary {
    width: 100%;
    padding: 12px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    margin-bottom: 10px;
}

.btn-primary {
    background-color: #007bff;
    color: white;
}

.btn-primary:hover {
    background-color: #0056b3;
}

.btn-secondary {
    background-color: #6c757d;
    color: white;
}

.btn-secondary:hover {
    background-color: #565e64;
}

.gender-options {
    display: flex;
}

.custom-control {
    margin-right: 20px;
}

.error {
    color: red;
    font-size: 0.9em;
    margin-top: 5px;
}

/* Responsive design */
@media screen and (max-width: 600px) {
    .container {
        width: 100%;
        margin: 20px auto;
        padding: 20px;
    }
}
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Chỉnh sửa khách hàng</h2>
            <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
            <% } %>
            <form id="customerForm" action="CustomerServletURL" method="post">
                <div class="form-group">
                    <label for="customerID">ID:</label>
                    <input type="text" id="customerID" name="customerID" value="${customer.customerID}" class="form-control" required readonly>
                </div>

                <div class="form-group">
                    <label for="first_name">Họ:</label>
                    <input type="text" id="first_name" name="first_name" value="${customer.first_name}" class="form-control" required>
                    <span id="first_name_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="last_name">Tên:</label>
                    <input type="text" id="last_name" name="last_name" value="${customer.last_name}" class="form-control" required>
                    <span id="last_name_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <input type="text" id="phone" name="phone" value="${customer.phone}" class="form-control" required>
                    <span id="phone_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" value="${customer.email}" class="form-control" required>
                    <span id="email_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="address">Địa chỉ:</label>
                    <input type="text" id="address" name="address" value="${customer.address}" class="form-control" required>
                    <span id="address_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="username">Tài khoản:</label>
                    <input type="text" id="username" name="username" value="${customer.username}" class="form-control" required>
                    <span id="username_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="text" id="password" name="password" value="${customer.password}" class="form-control" required>
                    <span id="password_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label for="dob">Ngày sinh:</label>
                    <input type="date" id="dob" name="dob" value="${customer.dob}" class="form-control" required>
                    <span id="dob_error" class="error text-danger"></span>
                </div>

                <div class="form-group">
                    <label>Giới tính</label>
                    <div class="gender-options">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="male" name="gender" value="true" class="custom-control-input" ${customer.gender ? "checked" : ""}>
                            <label class="custom-control-label" for="male">Nam</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="female" name="gender" value="false" class="custom-control-input" ${!customer.gender ? "checked" : ""}>
                            <label class="custom-control-label" for="female">Nữ</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="activity_history">Lịch sử hoạt động:</label>
                    <input type="date" id="activity_history" name="activity_history" value="${customer.activity_history}" class="form-control" readonly>
                </div>

                <div class="form-group">
                    <label for="security">Câu hỏi bảo mật:</label>
                    <select id="security" name="security" class="form-control">
                        <% 
                            Vector<Security> securityQuestions = (Vector<Security>) request.getAttribute("security");
                            for (Security question : securityQuestions) {
                        %>
                        <option value="<%= question.getSecurityID() %>"> <%= question.getSecurity_question() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="securityAnswer">Trả lời:</label>
                    <input type="text" id="securityAnswer" name="securityAnswer" value="${customer.secutityAnswer}" class="form-control" required>
                    <span id="securityAnswer_error" class="error text-danger"></span>
                </div>

                <input type="submit" name="submit" value="Save" class="btn btn-primary">
                <input type="hidden" name="service" value="updateCustomer">
                <button type="button" style="margin-top: 10px; color: white; background-color: green; border: 0px" onclick="window.location.href='CustomerServletURL'">
                    Back To Customer List
                </button>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                function validateField(id, maxLength, errorMessage) {
                    $(id).blur(function () {
                        let value = $(this).val();
                        if (value.length > maxLength) {
                            $(id + '_error').text(errorMessage);
                        } else {
                            $(id + '_error').text('');
                        }
                    });
                }

                function validatePhoneNumber() {
                    $('#phone').blur(function () {
                        let phone = $(this).val();
                        if (phone.length !== 10 || phone[0] !== '0') {
                            $('#phone_error').text('Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.');
                        } else {
                            $('#phone_error').text('');
                            checkDuplicate('phone', phone);
                        }
                    });
                }

                function validateEmail() {
                    $('#email').blur(function () {
                        checkDuplicate('email', $(this).val());
                    });
                }

                function validateUsername() {
                    $('#username').blur(function () {
                        checkDuplicate('username', $(this).val());
                    });
                }

                function validateSecurityAnswer() {
                    $('#securityAnswer').blur(function () {
                        let value = $(this).val();
                        if (value.length > 20) {
                            $('#securityAnswer_error').text('Trả lời câu hỏi bảo mật không được vượt quá 20 ký tự.');
                        } else {
                            $('#securityAnswer_error').text('');
                        }
                    });
                }

                function checkDuplicate(field, value) {
                    $.ajax({
                        url: 'CustomerServletURL',
                        type: 'POST',
                        data: {
                            service: 'checkDuplicateUpdate',
                            field: field,
                            value: value,
                            customerID: $('#customerID').val()
                        },
                        success: function (response) {
                            if (response === 'duplicate') {
                                $('#' + field + '_error').text('This ' + field + ' is already in use.');
                            } else {
                                $('#' + field + '_error').text('');
                            }
                        }
                    });
                }

                // Validate fields
                validateField('#first_name', 20, 'Họ không được vượt quá 20 ký tự.');
                validateField('#last_name', 20, 'Tên không được vượt quá 20 ký tự.');
                validateField('#phone', 10, 'Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.');
                validateField('#email', 50, 'Email không được vượt quá 50 ký tự.');
                validateField('#username', 20, 'Tài khoản không được vượt quá 20 ký tự.');
                validateField('#password', 20, 'Mật khẩu không được vượt quá 20 ký tự.');
                validateSecurityAnswer();
                validateField('#address', 100, 'Địa chỉ không được vượt quá 100 ký tự.');

                validatePhoneNumber();
                validateEmail();
                validateUsername();

                $('#customerForm').submit(function (e) {
                    e.preventDefault();

                    let isValid = true;
                    let phone = $('#phone').val();
                    if (phone.length !== 10 || phone[0] !== '0') {
                        $('#phone_error').text('Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.');
                        isValid = false;
                    }

                    $('.error').each(function () {
                        if ($(this).text() !== '') {
                            isValid = false;
                        }
                    });

                    if (!isValid) {
                        return;
                    }

                    var formData = $(this).serialize();
                    formData += "&submit=Update&service=updateCustomer";

                    $.ajax({
                        url: 'CustomerServletURL',
                        type: 'POST',
                        data: formData,
                        success: function (response) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công',
                                text: 'Khách hàng đã được cập nhật thành công!',
                                confirmButtonText: 'OK'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = 'CustomerServletURL';
                                }
                            });
                        },
                        error: function () {
                            Swal.fire({
                                icon: 'error',
                                title: 'Lỗi',
                                text: 'Đã xảy ra lỗi khi cập nhật khách hàng.',
                                confirmButtonText: 'OK'
                            });
                        }
                    });
                });
            });
        </script>
    </body>
</html>