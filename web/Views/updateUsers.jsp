<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,Entity.Staff"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật thông tin người dùng</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 20px;
        }

        form {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 10px;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: calc(100% - 20px);
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="file"] {
            margin-top: 10px;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #4cae4c;
        }

        img#userImage {
            max-width: 100px;
            display: block;
            margin-bottom: 10px;
        }

        .error-message {
            color: red;
            font-size: 0.8em;
            margin-top: 5px;
        }
    </style>
</head>

<body>
    <form action="updateUser" method="post" enctype="multipart/form-data">
        <table>
            ${requestScope.error}
            <tr>
                <td>Ảnh</td>
                <td>
                    <img class="rounded-circle mt-5" width="150px" src="${user.image}">
                    <input type="file" name="file" id="file" accept="image/*">
                    <input type="hidden" name="existingImage" value="${user.image}">
                </td>
            </tr>
            <tr>
                <td>ID</td>
                <td><input type="text" name="UserID" readonly id="" value="${user.getStaffID()}"></td>
            </tr>
            <tr>
                <td>Tên</td>
                <td><input type="text" name="fname" id="" value="${user.first_name}"></td>
            </tr>
            <tr>
                <td>Họ</td>
                <td><input type="text" name="lname" id="" value="${user.last_name}"></td>
            </tr>
            <tr>
                <td>Số điện thoại</td>
                <td><input type="text" name="phone" id="" value="${user.phone}"></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input type="email" name="email" id="" value="${user.email}"></td>
            </tr>
            <tr>
                <td>Địa chỉ</td>
                <td><input type="text" name="address" id="" value="${user.address}"></td>
            </tr>
            <tr>
                <td>Tên đăng nhập</td>
                <td><input type="text" name="username" id="" value="${user.username}"></td>
            </tr>
            <tr>
                <td>Mật Khẩu</td>
                <td><input type="text" name="password" id="" value="${user.password}"></td>
            </tr>
            <tr>
                <td>Ngày Sinh</td>
                <td><input type="date" name="dob" id="" value="${user.dob}"></td>
            </tr>
            <tr>
                <td>Giới tính</td>
                <td style="display: flex; justify-content: space-between;">
                    <input type="radio" name="gender" value="true" id="male" ${user.gender ? 'checked' : ''}>
                    <label for="male">Nam</label>
                    <input type="radio" name="gender" value="false" id="female" ${user.gender == false ? 'checked' : ''}>
                    <label for="female">Nữ</label>
                </td>
            </tr>
            <tr>
                <td>Trạng thái</td>
                <td>
                    <select name="status">
                        <option value="1" ${user.status == 1 ? 'selected' : ''}>Hoạt động</option>
                        <option value="0" ${user.status != 1 ? 'selected' : ''}>không hoạt động</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Vai trò</td>
                <td>
                    <select name="role">
                        <c:forEach items="${role}" var="r">
                            <option value="${r.roleID}">${r.role_Name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Câu hỏi bảo mật</td>
                <td>
                    <select name="securirtyQuestion">
                        <c:forEach items="${question}" var="q">
                            <option value="${q.securityID}">${q.security_question}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Câu trả lời</td>
                <td><input type="text" name="securityAnswer" id="" value="${user.securityAnswer}"></td>
            </tr>
            <tr>
                <td>
                    <a href="userList" style="background-color: grey; width: 80px; height: 35px; margin-top: 3px; border-radius: 6px; color: white; display: inline-block; text-align: center; line-height: 35px; text-decoration: none;">Back</a>
                </td>
                <td>
                    <input type="submit" name="submit" value="update User">
                </td>
            </tr>
        </table>
    </form>

    <script>
    document.addEventListener('DOMContentLoaded', function () {
        const form = document.querySelector('form');
        const inputs = form.querySelectorAll('input[type="text"], input[type="email"], input[type="date"], input[type="file"], select');
        const submitButton = form.querySelector('input[type="submit"]');

        // Create error message elements
        inputs.forEach(input => {
            const errorSpan = document.createElement('span');
            errorSpan.className = 'error-message';
            errorSpan.style.display = 'none';
            input.parentNode.insertBefore(errorSpan, input.nextSibling);
        });

        // Validation functions
        function validateRequired(input) {
            if (input.value.trim() === '') {
                showError(input, 'Trường này là bắt buộc');
                return false;
            }
            hideError(input);
            return true;
        }

        function validateLength(input, maxLength) {
            if (input.value.length > maxLength) {
                showError(input, `Không được vượt quá ${maxLength} ký tự`);
                return false;
            }
            hideError(input);
            return true;
        }

        function validatePhone(input) {
            const phoneRegex = /^0\d{9}$/;
            if (!phoneRegex.test(input.value)) {
                showError(input, 'Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số');
                return false;
            }
            hideError(input);
            return true;
        }

        function validateEmail(input) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(input.value)) {
                showError(input, 'Email không hợp lệ');
                return false;
            }
            hideError(input);
            return true;
        }

        function showError(input, message) {
            const errorSpan = input.nextElementSibling;
            errorSpan.textContent = message;
            errorSpan.style.display = 'block';
        }

        function hideError(input) {
            const errorSpan = input.nextElementSibling;
            errorSpan.style.display = 'none';
        }

        // Validate individual field
        function validateField(input) {
            if (input.type !== 'file' && !validateRequired(input)) {
                return false;
            }

            switch (input.name) {
                case 'fname':
                case 'lname':
                case 'securityAnswer':
                    return validateLength(input, 20);
                case 'phone':
                    return validatePhone(input);
                case 'email':
                    return validateEmail(input);
                case 'address':
                    return validateLength(input, 100);
                default:
                    return true;
            }
        }

        // Validate on input
        inputs.forEach(input => {
            input.addEventListener('input', function () {
                validateField(this);
                checkFormValidity();
            });

            // Initial validation
            if (input.type !== 'file') {
                validateField(input);
            }
        });

        function checkFormValidity() {
            let isValid = true;
            inputs.forEach(input => {
                if (input.type !== 'file' && input.nextElementSibling.style.display === 'block') {
                    isValid = false;
                }
            });
            submitButton.disabled = !isValid;
        }

        // Handle form submission
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            let isValid = true;
            inputs.forEach(input => {
                if (input.type !== 'file' && !validateField(input)) {
                    isValid = false;
                }
            });

            if (!isValid) {
                return;
            }

            // If form is valid, submit using fetch
            const formData = new FormData(this);
            fetch(this.action, {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                // Show success message
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Cập nhật thông tin thành công.',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Redirect to userList page
                        window.location.href = 'userList';
                    }
                });
            })
            .catch(error => {
                // Show error message
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi khi cập nhật thông tin.',
                    confirmButtonText: 'OK'
                });
                console.error('Error:', error);
            });
        });

        // Initial form validity check
        checkFormValidity();
    });
</script>

</body>

</html>