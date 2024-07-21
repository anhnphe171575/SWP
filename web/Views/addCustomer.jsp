<%-- 
    Document   : addProduct
    Created on : May 23, 2024, 9:56:49 AM
    Author     : MANH VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Customer</title>
    </head>
            <link rel="stylesheet" href="vncss/vn3.css">

    <style>
        .input{
            background-color: white;
        }
    </style>
    <body>
        <div class="container">
            <h1>Thêm Khách Hàng</h1>      
            <form action="CustomerServletURL" method="post">
                <div class="form-group">
                    <div class="label">Họ:</div>
                    <input type="text" name="first_name" id="">
                </div>

                <div class="form-group">
                    <div class="label">Tên:</div>
                    <input type="text" name="last_name" id="">
                </div>

                <div class="form-group">
                    <div class="label">Số điện thoại</div>
                    <input type="text" name="phone" id="">
                </div>
                <div class="form-group">
                    <div class="label">Email:</div>
                    <input type="text" name="email" id="">
                </div>

                <div class="form-group">
                    <div class="label">Địa chỉ:</div>
                    <input type="text" name="address" id="">
                </div>

                <div class="form-group">
                    <div class="label">Tài khoản:</div>
                    <input type="text" name="username" id="">
                </div>

                <div class="form-group">
                    <div class="label">Mật khẩu:</div>
                    <input type="text" name="password" id="">
                </div>
                <div class="form-group">
                    <div class="label">Ngày sinh:</div>
                    <input type="date" name="dob" id="">
                </div>

                <div class="form-group">
                    <div class="label">Giới tính </div>
                    <input type="radio" name="gender" value="true" id="male">
                    <label for="male">Nam</label>
                    <input type="radio" name="gender" value="false" id="female">
                    <label for="female">Nữ</label>
                </div>



                <div class="form-group">
                    <div class="label">Câu hỏi bảo mật:</div>
                    <select name="security">
                        <%Vector<Security> vector1=(Vector<Security>)request.getAttribute("security");
                    for(Security obj1: vector1){
                        %>
                        <option value="<%=obj1.getSecurityID()%>"> <%=obj1.getSecurity_question()%></option>
                        <% }%>
                    </select>
                </div>

                <div class="form-group">
                    <div class="label">Trả lời:</div>
                    <input type="text" name="securityAnswer" id="">
                </div>





                <div class="form-group">
                    
                    <input type="submit" name="submit" value="Thêm">
                    <td><input type="hidden" name="service" value="addCustomer">
                </div>
            </form>
        </div>
    </body>
</html>
