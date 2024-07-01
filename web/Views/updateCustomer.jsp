<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Vector,Entity.Customer"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer</title>
    <link rel="stylesheet" href="vncss/vn5.css"/>
</head>
<body>
    <div class="container">
        <h2>Edit Customer</h2>
        <form action="CustomerServletURL" method="post">
            <div class="form-group">
                <label>Customer ID:</label>
                <input type="text" name="customerID" value="${customer.customerID}" required readonly>
            </div>

            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="first_name" value="${customer.first_name}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="last_name" value="${customer.last_name}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Phone:</label>
                <input type="text" name="phone" value="${customer.phone}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="text" name="email" value="${customer.email}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Address:</label>
                <input type="text" name="address" value="${customer.address}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" value="${customer.username}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="text" name="password" value="${customer.password}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Date of birth:</label>
                <input type="date" name="dob" value="${customer.dob}" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Gender</label>
                <div class="gender-options">
                    <label class="radio-container">Male
                        <input type="radio" name="gender" value="1" ${customer.gender ? "checked" : ""}>
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-container">Female
                        <input type="radio" name="gender" value="0" ${!customer.gender ? "checked" : ""}>
                        <span class="checkmark"></span>
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label>Activity history:</label>
                <input type="date" name="activity_history" value="${customer.activity_history}" class="form-control" readonly>
            </div>

            <div class="form-group">
                <label>Security Question:</label>
                <select name="security">
                    <% 
                        Vector<Security> securityQuestions = (Vector<Security>) request.getAttribute("security");
                        for (Security question : securityQuestions) {
                    %>
                        <option value="<%= question.getSecurityID() %>"> <%= question.getSecurity_question() %></option>
                    <% } %>
                </select>
            </div>

             <div class="form-group">
                <label>Security Answer:</label>
                <input type="text" name="securityAnswer" value="${customer.secutityAnswer}" class="form-control" required>
            </div>
            <input type="submit" name="submit" value="Save">
            <input type="hidden" name="service" value="updateCustomer">
        </form>
    </div>
</body>
</html>
