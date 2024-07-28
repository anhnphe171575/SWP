<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.Vector,Entity.Staff"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Thông tin cá nhân</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
            }
            .profile-container {
                background-color: #ffffff;
                border-radius: 15px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                padding: 30px;
                margin-top: 50px;
            }
            .profile-picture {
                position: relative;
                width: 150px;
                height: 150px;
                margin: 0 auto 20px;
            }
            .profile-picture img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: 50%;
                border: 5px solid #f8f9fa;
            }
            .profile-picture input[type="file"] {
                position: absolute;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
                width: 100%;
                opacity: 0;
                cursor: pointer;
            }
            .profile-picture .upload-icon {
                position: absolute;
                bottom: 0;
                right: 0;
                background-color: #007bff;
                color: #ffffff;
                border-radius: 50%;
                padding: 8px;
                font-size: 18px;
            }
            .form-group label {
                font-weight: 500;
                color: #495057;
            }
            .form-control {
                border-radius: 10px;
            }
            .btn-save {
                background-color: #28a745;
                color: #ffffff;
                border-radius: 10px;
                padding: 10px 30px;
                font-weight: 500;
            }
            .btn-home {
                background-color: #007bff;
                color: #ffffff;
                border-radius: 10px;
                padding: 10px 30px;
                font-weight: 500;
                text-decoration: none;
            }
            .gender-options {
                display: flex;
                gap: 20px;
            }
            .main-content {
                margin-left: 400px; /* Điều chỉnh theo chiều rộng của sidebar */
                padding: 20px;
            }
            @media (max-width: 768px) {
                .main-content {
                    margin-left: 0;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <!-- Sidebar -->

            <div class="main-content">
                <div class="container profile-container">
                    <form id="sliderForm" action="editProfileUserURL" method="post" enctype="multipart/form-data" onsubmit="return validatePhoneNumber()">
                        <% 
                           Vector<Staff> vector = (Vector<Staff>) request.getAttribute("vector");
                           Staff obj = vector.get(0);
                        %>
                        <div class="row">
                            <h3>${requestScope.status}</h3>
                            <div class="col-md-4">
                                <div class="profile-picture">
                                    <img src="<%=obj.getImage()%>" alt="Profile Picture" id="profile-image">
                                    <input type="file" name="file" id="file" accept="image/*" onchange="previewImage(event)">
                                    <i class="fas fa-camera upload-icon"></i>
                                </div>
                                <input type="hidden" name="existingImage" value="${vector.get(0).getImage()}">
                            </div>
                            <div class="col-md-8">
                                <h2 class="mb-4">Thông tin cá nhân</h2>
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                        <label>ID</label>
                                        <input type="text" class="form-control" name="UserID" value="<%=obj.getStaffID()%>"readonly>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Tài khoản</label>
                                        <input type="text" class="form-control" name="username" value="<%=obj.getUsername()%>" readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                        <label>Họ</label>
                                        <input type="text" class="form-control" name="first_name" value="<%=obj.getFirst_name()%>">
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Tên</label>
                                        <input type="text" class="form-control" name="last_name" value="<%=obj.getLast_name()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="email" class="form-control" name="email" value="<%=obj.getEmail()%>" readonly>
                                </div>
                                <div class="form-group">
                                    <label>Số điện thoại</label>
                                    <input type="number" class="form-control" name="phone" value="<%=obj.getPhone()%>">
                                </div>
                                <div class="form-group">
                                    <label>Địa chỉ</label>
                                    <input type="text" class="form-control" name="address" value="<%=obj.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <label>Ngày sinh</label>
                                    <input type="date" class="form-control" name="dob" value="<%=obj.getDob()%>">
                                </div>
                                <div class="form-group">
                                    <label>Giới tính</label>
                                    <div class="gender-options">
                                        <div class="custom-control custom-radio">
                                            <input type="radio" id="male" name="gender" value="true" class="custom-control-input" <%= obj.isGender() ? "checked" : "" %>>
                                            <label class="custom-control-label" for="male">Nam</label>
                                        </div>
                                        <div class="custom-control custom-radio">
                                            <input type="radio" id="female" name="gender" value="false" class="custom-control-input" <%= !obj.isGender() ? "checked" : "" %>>
                                            <label class="custom-control-label" for="female">Nữ</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">

                                    <input type="hidden" class="form-control" name="status" value="<%=obj.getStatus()%>" readonly>
                                </div>
                                <div class="col-md-12"><label class="labels"></label><input type="hidden" class="form-control" name="RoleID" value="<%= obj.getRole().getRoleID()%> " readonly ></div> 
                                <div class="form-group"><label class="labels">Vai trò</label><input type="text" class="form-control" name="role" value="<%= obj.getRole().getRole_Name()%> " readonly ></div> 

                                <div class="form-group">
                                    <label>Câu hỏi bảo mật</label>
                                    <input type="hidden" class="form-control" name="security" value="<%=obj.getSecurity().getSecurityID()%>" readonly>
                                    <input type="text" class="form-control" name="security_question" value="<%=obj.getSecurity().getSecurity_question()%>" readonly>

                                </div>
                                <div class="form-group" >
                                    <label>Câu trả lời</label>
                                    <input type="text" class="form-control" name="securityAnswer" value="<%=obj.getSecurityAnswer()%>" readonly id="security-answer" style="display: none;">
                                    <button type="button" id="show-answer-btn" class="btn btn-secondary mt-2" onclick="showSecurityAnswer('<%=obj.getPassword()%>')">Hiện Câu trả lời</button>
                                    <button type="button" id="hide-answer-btn" class="btn btn-secondary mt-2" onclick="hideSecurityAnswer()" style="display: none;">Ẩn Câu trả lời</button>
                                </div>
                                <div class="mt-4">
                                    <button type="submit" class="btn btn-save mr-2">Lưu thay đổi</button>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.role.getRoleID() == 1}">
                                            <button onclick="location.href = 'MKTDashboard';" class="btn btn-save mr-2">Trở lại trang chủ</button>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role.getRoleID() == 2}">
                                            <button onclick="location.href = 'orderlist';" class="btn btn-save mr-2">Trở lại trang chủ</button>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role.getRoleID() == 3}">
                                            <button onclick="location.href = 'SaleDashboardURL';" class="btn btn-save mr-2">Trở lại trang chủ</button>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role.getRoleID() == 4}">
                                            <button onclick="location.href = 'orderlist';" class="btn btn-save mr-2">Trở lại trang chủ</button>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role.getRoleID() == 5}">
                                            <button onclick="location.href = 'AdminSettingURL';" class="btn btn-save mr-2">Trở lại trang chủ</button>
                                        </c:when>
                                     
                                    </c:choose>

                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="service" value="editProfileCustomer">
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script>
                                                function validatePhoneNumber() {
                                                    const phoneInput = document.querySelector('input[name="phone"]');
                                                    const phonePattern = /^[0-9]{10}$/;
                                                    if (!phonePattern.test(phoneInput.value)) {
                                                        alert("Vui lòng nhập số điện thoại hợp lệ (10 chữ số).");
                                                        return false;
                                                    }
                                                    return true;
                                                }

                                                function showSecurityAnswer(storedPassword) {
                                                    const password = prompt("Vui lòng nhập mật khẩu:");
                                                    if (password === storedPassword) {
                                                        document.getElementById('security-answer').style.display = 'block';
                                                        document.getElementById('show-answer-btn').style.display = 'none';
                                                        document.getElementById('hide-answer-btn').style.display = 'inline';
                                                    } else {
                                                        alert("Mật khẩu không đúng!");
                                                    }
                                                }

                                                function hideSecurityAnswer() {
                                                    document.getElementById('security-answer').style.display = 'none';
                                                    document.getElementById('show-answer-btn').style.display = 'inline';
                                                    document.getElementById('hide-answer-btn').style.display = 'none';
                                                }

                                                function previewImage(event) {
                                                    var reader = new FileReader();
                                                    reader.onload = function () {
                                                        var output = document.getElementById('profile-image');
                                                        output.src = reader.result;
                                                    };
                                                    reader.readAsDataURL(event.target.files[0]);
                                                }
        </script>
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
                        url: 'editProfileUserURL',
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
                                    window.location.href = 'editProfileUserURL';
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