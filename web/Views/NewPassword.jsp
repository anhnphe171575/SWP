<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta contentType="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Password Form</title>
    <style>
        /* Reset some basic elements for consistent styling */
body, input, button {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
}

/* Style the form container */
.password-form {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Form group to wrap label and input together */
.form-group {
    margin-bottom: 20px;
}

/* Style the labels */
.password-form label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    color: #333;
}

/* Style the input fields */
.password-form input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
}

/* Style the submit button */
.password-form button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

/* Change button color on hover */
.password-form button[type="submit"]:hover {
    background-color: #0056b3;
}
 .error-message {
                color: red;
                text-align: center;
                margin-top: 10px;
            }
    </style>
</head>
<body>
     <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
            <% } %>
         
    <form action="NewPassword" method="post" class="password-form">
        <input type="hidden" name="email" value="${email}">
        <input type="hidden" name="otp" value="${otp}">
          <input type="hidden" name="role" value="${role}">
        <div class="form-group">
            <label for="new-password">New Password:</label>
            <input type="password" id="new-password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirm-password">Confirm new password:</label>
            <input type="password" id="confirm-password" name="retypePassword" required>
        </div>
        <button type="submit">Update password</button>
    </form>
</body>
</html>
