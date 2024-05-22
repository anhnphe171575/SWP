<%-- 
    Document   : verification
    Created on : May 21, 2024, 9:55:31 AM
    Author     : MANH VINH
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Account Verification</title>
    </head>
    <body>
        <h2>Account Verification</h2>
        <p>Please click the button below to verify your account:</p>
        <form action="verify" method="POST">
            <input type="text" value="${sessionScope.cus}" name="cus">           
            <input type="hidden" name="token" value="${param.token}" />
            <input type="submit" value="Verify Account" />
        </form>
    </body>
</html>

