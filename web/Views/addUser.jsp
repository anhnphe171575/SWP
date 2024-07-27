<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }
            form {
                max-width: 600px;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            }
            table {
                width: 100%;
            }
            table td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }
            table td:first-child {
                font-weight: bold;
                width: 30%;
            }
            input[type="text"],
            input[type="email"],
            input[type="date"],
            input[type="radio"],
            input[type="file"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 3px;
                box-sizing: border-box;
                margin-top: 4px;
                margin-bottom: 10px;
            }
            input[type="submit"],
            input[type="reset"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                float: right;
                margin-left: 10px;
            }
            input[type="reset"] {
                background-color: #f44336;
            }
        </style>
    </head>

    <body>

        <form action="AddUser" method="post" >
            <table>
                <p style="color: black">${error}</p>
                <!--                <tr>
                                    <td>Image</td>
                                    <td><input type="file" name="file" id="file" accept="image/*"></td>
                                </tr>-->
                <tr>
                    <td>Tên</td>
                    <td><input type="text" name="fname" id="" value="" required maxlength="20"></td>
                </tr>
                <tr>
                    <td>Họ </td>
                    <td><input type="text" name="lname" id="" value="" required maxlength="20"></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="phone" id="" value="" required maxlength="10"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"  id="email" value="" required maxlength="50"></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="address" id="" value="" required maxlength="100"></td>
                </tr>
                <tr>
                    <td>Tên đăng nhập</td>
                    <td><input type="text" name="username" id="" value="" required maxlength="20"></td>
                </tr>
                <tr>
                    <td>Mật khẩu</td>
                    <td><input type="password" name="password" id="" value="" required maxlength="20"></td>
                </tr>
                <tr>
                    <td>Ngày Sinh</td>
                    <td><input type="date" name="dob" id="" value="" required></td>
                </tr>
                <tr>
                    <td>Giới tính</td>
                    <td style="display: flex; justify-content: space-between;">
                        <input type="radio" name="gender" value="true" id="male" required>
                        <label for="male">Nam</label>
                        <input type="radio" name="gender" value="false" id="female">
                        <label for="female">Nữ</label>
                    </td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="status" required>
                            <option value="1">Hoạt động</option>
                            <option value="0">Không hoạt động</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Vai trò</td>
                    <td> <select name="role" required>
                            <c:forEach items="${role}" var="r">
                                <option value="${r.roleID}">${r.role_Name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Câu hỏi bảo mật</td>
                    <td>
                        <select name="securityQuestion" required>
                            <c:forEach items="${question}" var="q">
                                <option value="${q.securityID}">${q.securityID} -${q.security_question}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Câu trả lời</td>
                    <td><input type="text" name="securityAnswer" id="" value="" required maxlength="20"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Add User"></td>
                    <td><input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>
       
    </body>
</html>
