<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,Entity.Staff"%>
<%@page import="Entity.Security"%>
<%@page import="Entity.Role"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bootstrap User Management Data Table</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background: rgb(247, 249, 251)
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #f7f5f7
        }
        .profile-button {
            background: rgb(244, 243, 244);
            box-shadow: none;
            border: none
        }
        .profile-button:hover {
            background: #f7f7f7
        }
        .profile-button:focus {
            background: #faf8fb;
            box-shadow: none
        }
        .profile-button:active {
            background: #f8f5f9;
            box-shadow: none
        }
        .back:hover {
            color: #f8f4f8;
            cursor: pointer
        }
        .labels {
            font-size: 11px
        }
        .add-experience:hover {
            background: #fcfbfc;
            color: #fff;
            cursor: pointer;
            border: solid 1px #f5f1f6
        }
    

            .form-control:focus {
                box-shadow: none;
                border-color: #f7f5f7
            }

            .profile-button {
                background: rgb(244, 243, 244);
                box-shadow: none;
                border: none
            }

            .profile-button:hover {
                background: #f7f7f7
            }

            .profile-button:focus {
                background: #faf8fb;
                box-shadow: none
            }

            .profile-button:active {
                background: #f8f5f9;
                box-shadow: none
            }

            .back:hover {
                color: #f8f4f8;
                cursor: pointer
            }

            .labels {
                font-size: 11px
            }

            .add-experience:hover {
                background: #fcfbfc;
                color: #fff;
                cursor: pointer;
                border: solid 1px #f5f1f6
            }
        </style>
        <script>
            $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });

        function validatePhoneNumber() {
            const phoneInput = document.querySelector('input[name="phone"]');
            const phonePattern = /^[0-9]{10}$/; // Adjust the pattern to fit your phone number format
            if (!phonePattern.test(phoneInput.value)) {
                alert("Please enter a valid phone number (10 digits).");
                return false;
            }
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
         <% 
               Vector<Staff> vector = (Vector<Staff>) request.getAttribute("vector");
               Staff obj = vector.get(0);
                %>
                <form action="editProfileUserURL" method="post" enctype="multipart/form-data">
        <div class="container rounded bg-white mt-5 mb-5"> 
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="<%=obj.getImage()%>">
                        <input type="file" name="file" id="file" accept="image/*">
                        <input type="hidden" name="existingImage" value="<%=obj.getImage()%>">
                    </div>
                </div>
                <div class="col-md-5 border-right">
                    
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Trang cá nhân</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12"><label class="labels">ID</label><input type="text" class="form-control" name="UserID" value="<%=obj.getUserID()%>"readonly></div>

                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6"><label class="labels">Họ</label><input type="text" class="form-control" name="first_name" value="<%=obj.getFirst_name()%>"></div>
                                <div class="col-md-6"><label class="labels">Tên</label><input type="text" class="form-control" name="last_name" value="<%=obj.getLast_name()%>"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Số điện thoại:</label><input type="text" class="form-control" name="phone"  value="<%=obj.getPhone()%>"></div>
                                <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control" name="email" value="<%=obj.getEmail()%>" readonly> </div>
                                <div class="col-md-12"><label class="labels">Địa chỉ</label><input type="text" class="form-control" name="address" value="<%=obj.getAddress()%>"></div>
                                <div class="col-md-12"><label class="labels">Tài khoản</label><input type="text" class="form-control" name="username" value="<%=obj.getUsername()%>" readonly=""></div>
                                <div class="col-md-12"><label class="labels">Mật khẩu</label><input type="text" class="form-control" name="password" value="<%=obj.getPassword()%>"></div>
                                <div class="col-md-12"><label class="labels">Ngày sinh</label><input type="date" class="form-control" name="dob" value="<%=obj.getDob()%>"></div>
                                <div class="col-md-12"><label class="labels">Giới tính</label>
                                    <div class="gender-options">
                                    <label class="radio-container">Nam

                                        <input type="radio" name="gender" value="1" <%= obj.isGender() ? "checked" : "" %>>
                                        <span class="checkmark"></span>
                                    </label>
                                    <label class="radio-container">Nữ
                                        <input type="radio" name="gender" value="0" <%= !obj.isGender() ? "checked" : "" %>>
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                </div>
                                <div class="col-md-12"><label class="labels">Trạng thái</label><input type="text" class="form-control" name="status" value="<%=obj.getStatus()%>"></div>
                                <div class="col-md-12"><label class="labels"></label><input type="hidden" class="form-control" name="RoleID" value="<%= obj.getRole().getRoleID()%> " readonly ></div> 
                                <div class="col-md-12"><label class="labels">Vai trò</label><input type="text" class="form-control" name="role" value="<%= obj.getRole().getRole_Name()%> " readonly ></div> 
                                <div class="col-md-12"><label class="labels"></label><input type="hidden" class="form-control" name="RoleID" value="<%= obj.getRole().getRoleID()%> " readonly ></div> 
                                
                                <div class="col-md-12"><label class="labels">Câu hỏi bảo mật:</label>
                                    <select name="security">
                                        <%Vector<Security> vector1=(Vector<Security>)request.getAttribute("security");
                                    for(Security obj1: vector1){
                                        %>
                                        <option value="<%=obj1.getSecurityID()%>"> <%=obj1.getSecurity_question()%></option>
                                        <% }%>
                                    </select></div>
                                <div class="col-md-12"><label class="labels">Câu trả lời</label><input type="text" class="form-control" name="securityAnswer" value="<%=obj.getSecurityAnswer()%>"></div>

                            </div>

                            <div class="mt-5 text-center">
                               
                                <input type="submit" name="submit" value="Lưu">

                                <input type="hidden" name="service" value="editProfileUser">
                            </div>
                            <div class="col-md-12"><label class="labels">Answer</label><input type="text" class="form-control" name="securityAnswer" value="<%=obj.getSecurityAnswer()%>"></div>
                        </div>
                        <div class="mt-5 text-center">
                            <input type="submit" name="submit" value="Save">
                            <input type="hidden" name="service" value="editProfileUser">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
