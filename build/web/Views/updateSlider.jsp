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
        <h2>Edit </h2>
        <c:if test="${requestScope.list != null}">
            <form action="SliderServletURL"method="post">
                <label> SliderID
                    <input type="text" name="name" value="${list.getSliderID()}" required>
                </label><br>
                <label> Title
                    <input type="text" name="name" value="${list.getTitle()}" required>
                </label><br>
                <label> Image
                    <input type="text" name="name" value="${list.getImage()}" required>
                </label><br>
                <label> Link
                    <input type="text" name="name" value="${list.getLink()}" required>
                </label><br>
                <label> Status
                    <input type="text" name="name" value="${list.getStatus()}" required>
                </label><br>
                <label> Notes
                    <input type="text" name="name" value="${list.getNotes()}" required>
                </label><br>
                <label> UserID
                    <input type="text" name="name" value="${list.getNotes()}" required>
                </label><br>
                <tr>
                    <td><input type="submit" name="submit" value="Submit"></td>
                    <td><input type="reset" value="reset">
                    <td><input type="hidden" name="service" value="updateSlider">
                    </td>
                </tr>
            </form>  
        </c:if>
    </body>
</html>
