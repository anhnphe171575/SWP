<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Vector,Entity.Customer"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
            background: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary, .btn-secondary {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #565e64;
        }
        .gender-options label {
            margin-right: 20px;
        }
        .gender-options .checkmark {
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Customer</h2>
        <form action="CustomerServletURL" method="post">
            <div class="form-group">
                <label for="customerID">Customer ID:</label>
                <input type="text" id="customerID" name="customerID" value="${customer.customerID}" class="form-control" required readonly>
            </div>

            <div class="form-group">
                <label for="first_name">First Name:</label>
                <input type="text" id="first_name" name="first_name" value="${customer.first_name}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="last_name">Last Name:</label>
                <input type="text" id="last_name" name="last_name" value="${customer.last_name}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${customer.phone}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="${customer.email}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${customer.address}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${customer.username}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" id="password" name="password" value="${customer.password}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="${customer.dob}" class="form-control" required>
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
                <label for="activity_history">Activity History:</label>
                <input type="date" id="activity_history" name="activity_history" value="${customer.activity_history}" class="form-control" readonly>
            </div>

            <div class="form-group">
                <label for="security">Security Question:</label>
                <select id="security" name="security" class="form-control">
                    <% 
                        Vector<Security> securityQuestions = (Vector<Security>) request.getAttribute("security");
                        for (Security question : securityQuestions) {
                    %>
                        <option value="<%= question.getSecurityID() %>"> <%= question.getSecurity_question() %></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="securityAnswer">Security Answer:</label>
                
                <input type="text" name="securityAnswer" value="${customer.secutityAnswer}" class="form-control" required>
            </div>
            
            <input type="submit" name="submit" value="Save" class="btn btn-primary">
            <input type="hidden" name="service" value="updateCustomer">
        </form>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
