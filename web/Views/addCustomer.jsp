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
        <style>
            .input{
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
                    <div class="label">Số điện thoại</div>
                    <input type="tel" name="phone" id="phone" required>
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
                    <div class="label">Giới tính </div>
                    <input type="radio" name="gender" value="true" id="male" required>
                    <label for="male">Nam</label>
                    <input type="radio" name="gender" value="false" id="female" required>
                    <label for="female">Nữ</label>
                    <span id="gender_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Câu hỏi bảo mật:</div>
                    <select name="security" id="security" required>
                        <%Vector<Security> vector1=(Vector<Security>)request.getAttribute("security");
                        for(Security obj1: vector1){
                        %>
                        <option value="<%=obj1.getSecurityID()%>"> <%=obj1.getSecurity_question()%></option>
                        <% }%>
                    </select>
                    <span id="security_error" class="error"></span>
                </div>
                <div class="form-group">
                    <div class="label">Trả lời:</div>
                    <input type="text" name="securityAnswer" id="securityAnswer" required>
                    <span id="securityAnswer_error" class="error"></span>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" value="Thêm">
                    <input type="hidden" name="service" value="addCustomer">
                </div>
            </form>
        </div>

        <script>
            document.getElementById('customerForm').addEventListener('submit', function(event) {
                let isValid = true;

                // Validate First Name
                const firstName = document.getElementById('first_name');
                if (firstName.value.trim() === '') {
                    document.getElementById('first_name_error').textContent = 'Vui lòng nhập họ';
                    isValid = false;
                } else {
                    document.getElementById('first_name_error').textContent = '';
                }

                // Validate Last Name
                const lastName = document.getElementById('last_name');
                if (lastName.value.trim() === '') {
                    document.getElementById('last_name_error').textContent = 'Vui lòng nhập tên';
                    isValid = false;
                } else {
                    document.getElementById('last_name_error').textContent = '';
                }

                // Validate Phone
                const phone = document.getElementById('phone');
                const phoneRegex = /^[0-9]{10}$/;
                if (!phoneRegex.test(phone.value)) {
                    document.getElementById('phone_error').textContent = 'Vui lòng nhập số điện thoại hợp lệ (10 chữ số)';
                    isValid = false;
                } else {
                    document.getElementById('phone_error').textContent = '';
                }

                // Validate Email
                const email = document.getElementById('email');
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(email.value)) {
                    document.getElementById('email_error').textContent = 'Vui lòng nhập email hợp lệ';
                    isValid = false;
                } else {
                    document.getElementById('email_error').textContent = '';
                }

                // Validate Address
                const address = document.getElementById('address');
                if (address.value.trim() === '') {
                    document.getElementById('address_error').textContent = 'Vui lòng nhập địa chỉ';
                    isValid = false;
                } else {
                    document.getElementById('address_error').textContent = '';
                }

                // Validate Username
                const username = document.getElementById('username');
                if (username.value.trim() === '') {
                    document.getElementById('username_error').textContent = 'Vui lòng nhập tên đăng nhập';
                    isValid = false;
                } else {
                    document.getElementById('username_error').textContent = '';
                }

                // Validate Password
                const password = document.getElementById('password');
                if (password.value.length < 6) {
                    document.getElementById('password_error').textContent = 'Mật khẩu phải có ít nhất 6 ký tự';
                    isValid = false;
                } else {
                    document.getElementById('password_error').textContent = '';
                }

                // Validate Date of Birth
                const dob = document.getElementById('dob');
                if (dob.value === '') {
                    document.getElementById('dob_error').textContent = 'Vui lòng chọn ngày sinh';
                    isValid = false;
                } else {
                    document.getElementById('dob_error').textContent = '';
                }

                // Validate Gender
                const gender = document.querySelector('input[name="gender"]:checked');
                if (!gender) {
                    document.getElementById('gender_error').textContent = 'Vui lòng chọn giới tính';
                    isValid = false;
                } else {
                    document.getElementById('gender_error').textContent = '';
                }

                // Validate Security Answer
                const securityAnswer = document.getElementById('securityAnswer');
                if (securityAnswer.value.trim() === '') {
                    document.getElementById('securityAnswer_error').textContent = 'Vui lòng nhập câu trả lời bảo mật';
                    isValid = false;
                } else {
                    document.getElementById('securityAnswer_error').textContent = '';
                }

                if (!isValid) {
                    event.preventDefault();
                }
            });
        </script>
    </body>
</html>