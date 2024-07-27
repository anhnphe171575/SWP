<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Customer</title>
        <link rel="stylesheet" href="vncss/vn3.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .input {
                background-color: white;
            }
            .error {
                color: red;
                font-size: 0.9em;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Thêm Khách Hàng</h1>
            <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
            <% } %>
            <form id="customerForm" action="CustomerServletURL" method="post">
                <div class="form-group">
                    <div class="label">Họ:</div>
                    <input type="text" name="first_name" id="first_name" required>
                    <span id="first_name_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Tên:</div>
                    <input type="text" name="last_name" id="last_name" required>
                    <span id="last_name_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Số điện thoại:</div>
                    <input type="number" name="phone" id="phone" required>
                    <span id="phone_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Email:</div>
                    <input type="email" name="email" id="email" required>
                    <span id="email_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Địa chỉ:</div>
                    <input type="text" name="address" id="address" required>
                    <span id="address_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Tài khoản:</div>
                    <input type="text" name="username" id="username" required>
                    <span id="username_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Mật khẩu:</div>
                    <input type="password" name="password" id="password" required>
                    <span id="password_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Ngày sinh:</div>
                    <input type="date" name="dob" id="dob" required>
                    <span id="dob_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Giới tính:</div>
                    <input type="radio" name="gender" value="true" id="male" required>
                    <label for="male">Nam</label>
                    <input type="radio" name="gender" value="false" id="female" required>
                    <label for="female">Nữ</label>
                    <span id="gender_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Câu hỏi bảo mật:</div>
                    <select name="security" id="security" required>
                        <% Vector<Security> vector1 = (Vector<Security>)request.getAttribute("security");
                        for(Security obj1: vector1) { %>
                        <option value="<%=obj1.getSecurityID()%>"><%=obj1.getSecurity_question()%></option>
                        <% } %>
                    </select>
                    <span id="security_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Trả lời:</div>
                    <input type="text" name="secutityAnswer" id="securityAnswer" required>
                    <span id="securityAnswer_error" class="error"></span>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" value="Thêm" id="submitBtn">
                    <input type="hidden" name="service" value="addCustomer">
                    <button type="button" style="margin-top: 10px; color: white; background-color: whitesmoke; border: 0px" onclick="window.location.href='CustomerServletURL'">
                        Back To Customer List
                    </button>
                </div>
            </form>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#phone').blur(function () {
                    let phone = $(this).val();
                    if (phone.length !== 10 || phone[0] !== '0') {
                        $('#phone_error').text('Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.');
                    } else {
                        $('#phone_error').text('');
                        checkDuplicate('phone', phone);
                    }
                });

                $('#email').blur(function () {
                    checkDuplicate('email', $(this).val());
                });

                $('#username').blur(function () {
                    checkDuplicate('username', $(this).val());
                });

                function checkDuplicate(field, value) {
                    $.ajax({
                        url: 'CustomerServletURL',
                        type: 'POST',
                        data: {
                            service: 'checkDuplicate',
                            field: field,
                            value: value
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

                $('#customerForm').submit(function (e) {
                    e.preventDefault();

                    let isValid = true;

                    let phone = $('#phone').val();
                    if (phone.length !== 10 || phone[0] !== '0') {
                        $('#phone_error').text('Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.');
                        isValid = false;
                    } else {
                        $('#phone_error').text('');
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
                    formData += "&submit=" + $("#submitBtn").val() + "&service=addCustomer";

                    $.ajax({
                        url: 'CustomerServletURL',
                        type: 'POST',
                        data: formData,
                        success: function (response) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Thành công',
                                text: 'Khách hàng đã được thêm thành công!',
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
                                text: 'Đã xảy ra lỗi khi thêm khách hàng!',
                                confirmButtonText: 'OK'
                            });
                        }
                    });
                });
            });
        </script>
    </body>
</html>