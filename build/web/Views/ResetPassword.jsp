<%-- 
    Document   : ResetPassword.jsp
    Created on : May 19, 2024, 2:10:26 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gửi mật khẩu về email</title>
   <style>
       .container {
  padding: 20px;
  margin: 50px auto;
  width: 300px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
h1 {
  text-align: center;
  color: #333;
  font-family: sans-serif;
  margin-bottom: 20px;
}
label {
  display: block;
  margin-bottom: 5px;
  color: #888;
}
input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type="text"]:focus {
  outline: none;
  border-color: #999;
}
button {
   margin-top: 10px; 
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

button[type="submit"]:active {
  transform: translateY(2px);
}
 .error-message {
                color: red;
                text-align: center;
                margin-top: 10px;
            }
    </style>
</head>
<body>
    <div class="container">
        <h1>Send password to email</h1>
        <form action="ResetPassword" method="post">
            <input type="hidden"  name="role" value="${requestScope.role}">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="Enter your email">
            <button type="submit">Send</button>
           
        </form>
         <button onclick="location.href='LoginCusController'">Back to login</button>
         <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
            <% } %>
    </div>
</body>
</html> 
