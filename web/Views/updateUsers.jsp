<%-- 
    Document   : addStaff
    Created on : Apr 19, 2024, 8:01:38 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,Entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="vi">
    
    <head>
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        <style>
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
                        <input type="file" name="file" id="file" accept="image/*" >
                    </td>

                </tr>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="UserID" readonly id="" value="${user.userID}"></td>
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
                    <td> <select name="role">
                            <c:forEach items="${role}" var="r">
                                <option   value="${r.roleID}">${r.role_Name}</option>
                            </c:forEach>
                        </select>                    </td>
                </tr>


                <tr>
                    <td>Câu hỏi bảo mật</td>

                    <td>  <select name="securirtyQuestion">
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
                    <td><input type="submit" name="submit" value="update User"></td>
                    <td><input type="reset" value="reset">
                        <input type="hidden" name="service" value="updateUser">
                    </td>
                </tr>
            </table>

        </form>

    </body>
</html>
