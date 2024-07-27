<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <!-- Include Bootstrap CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!-- Internal CSS -->
    <style>
        /* Resetting some default styles */
        body, h2, label, input, select {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-color: white;
            font-family: 'Arial', sans-serif;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 30px;
            max-width: 400px;
            width: 100%;
        }

        h2 {
            margin-bottom: 30px;
            color: #333;
            font-size: 24px;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: 600;
            color: #555;
        }

        input[type="password"], input[type="text"], select {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 20px;
            font-size: 16px;
        }

        input[type="password"] {
            padding-right: 40px; /* Space for the 'Show/Hide' text */
        }

        .pass_show {
            position: relative;
        }

        .pass_show .ptxt {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            color: #007bff;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .pass_show .ptxt:hover {
            color: #0056b3;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        h3 {
            color: #f36c01;
            text-align: center;
            margin-bottom: 20px;
        }
        
        .sidebar {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 250px;
            background-color: #f8f9fa;
            padding: 15px;
        }

        .main-content {
            margin-left: 270px; /* Sidebar width + padding */
            padding: 15px;
        }
    </style>
    <!-- Internal JavaScript -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.pass_show').append('<span class="ptxt">Show</span>');
        });

        $(document).on('click', '.pass_show .ptxt', function () {
            $(this).text($(this).text() == "Show" ? "Hide" : "Show");
            $(this).prev().attr('type', function (index, attr) {
                return attr == 'password' ? 'text' : 'password';
            });
        });
    </script>
</head>
<body>
    <!-- Sidebar Inclusion -->
    <jsp:include page="SidebarUser.jsp"></jsp:include>
    
    <!-- Main Content Area -->
    <div class="main-content">
        <div class="container">
            <div class="form-container">
                <h2>Thay đổi mật khẩu</h2>
                <form action="ChangePassword" method="post">
                    <label for="opassword">Mật khẩu cũ</label>
                    <div class="form-group pass_show">
                        <input id="opassword" type="password" name="opassword" class="form-control" placeholder="">
                    </div>
                    
                    <label for="npassword">Mật khẩu mới</label>
                    <div class="form-group pass_show">
                        <input id="npassword" type="password" name="npassword" class="form-control" placeholder="">
                    </div>
                    
                    <label for="rpassword">Nhập lại mật khẩu mới</label>
                    <div class="form-group pass_show">
                        <input id="rpassword" type="password" name="rpassword" class="form-control" placeholder="">
                    </div>
                    
                    <label for="security_question">Câu hỏi bảo mật</label>
                    <div class="form-group">
                        <select id="security_question" name="security_question" class="form-control">
                            <c:forEach items="${security_question}" var="sq">
                                <option value="${sq.securityID}">${sq.security_question}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <label for="securityAnswer">Trả lời</label>
                    <div class="form-group">
                        <input id="securityAnswer" type="text" name="securityAnswer" class="form-control" placeholder="">
                    </div>
                    
                    <h3>${requestScope.ms}</h3>
                    
                    <input type="submit" name="submit" value="Thay đổi">
                    <input type="hidden" name="username" value="${sessionScope.cus.username}">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
