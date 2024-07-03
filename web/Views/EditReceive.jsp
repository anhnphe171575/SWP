<%-- 
    Document   : EditReceive
    Created on : Jul 1, 2024, 3:03:59 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .edit-form-container {
                background-color: white;
                padding: 2em;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 400px;
            }

            .edit-form-container h2 {
                color: #D19C97;
                margin-bottom: 1em;
                text-align: center;
            }

            .edit-form-container label {
                display: block;
                margin-bottom: 0.5em;
                color: #333;
            }

            .edit-form-container input[type="text"],
            .edit-form-container input[type="email"],
            .edit-form-container input[type="password"],
            .edit-form-container textarea {
                width: 100%;
                padding: 0.75em;
                margin-bottom: 1em;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .edit-form-container input[type="submit"] {
                background-color: #D19C97;
                color: white;
                padding: 0.75em;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
                font-size: 1em;
            }

            .edit-form-container input[type="submit"]:hover {
                background-color: #c4867d;
            }
        </style>
    </head>
    <body>
        <div class="edit-form-container">
            <h2>Edit Form</h2>
            <form action="EditReceive" method="post">
                <label for="name">ID</label>
                <input type="text" id="name" name="id" value="${receive_cus.getReceiverID()}" readonly="" required>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${receive_cus.getReceiverFullName()}" required>

                <label for="email">Phone</label>
                <input type="text" id="email" name="phone" value="${receive_cus.getReceiverMobile()}" required>

                <label for="password">Receive Address</label>
                <input type="text" id="password" name="address" value="${receive_cus.getReceiverAddress()}" required>
                <input type="submit" value="Update">
            </form>
            <a href="CartContact" class="btn btn-secondary">Quay lại menu</a>

        </div>
    </body>
</html>


