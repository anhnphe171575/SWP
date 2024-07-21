<%-- 
    Document   : updateCustomer
    Created on : May 15, 2024, 1:28:45 AM
    Author     : Nguyễn Đăng
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Chỉnh sửa Thanh trượt </h2>
        <c:if test="${requestScope.list != null}">
            <form action="SliderServletURL"method="post">
                <label> ID
                    <input type="text" name="name" value="${list.getSliderID()}" required>
                </label><br>
                <label> Tiêu đề
                    <input type="text" name="name" value="${list.getTitle()}" required>
                </label><br>
                <label> Ảnh
                    <input type="text" name="name" value="${list.getImage()}" required>
                </label><br>
                <label> Liên kết
                    <input type="text" name="name" value="${list.getLink()}" required>
                </label><br>
                <label> Trạng thái
                    <input type="text" name="name" value="${list.getStatus()}" required>
                </label><br>
                <label> Ghi chú
                    <input type="text" name="name" value="${list.getNotes()}" required>
                </label><br>
                <label> ID người đăng
                    <input type="text" name="name" value="${list.getNotes()}" required>
                </label><br>
                <tr>
                    <td><input type="submit" name="submit" value="Cập nhập"></td>
                    <td><input type="reset" value="reset">
                    <td><input type="hidden" name="service" value="updateSlider">
                    </td>
                </tr>
            </form>  
        </c:if>
    </body>
</html>
