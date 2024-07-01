<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Customer Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            /* Your CSS styles */
        </style>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Details <b>Customer</b></h2>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Customer Attribute</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>customerID</td>
                                <td>${listAllCustomer.customerID}</td>
                            </tr>
                            <tr>
                                <td>first_name</td>
                                <td>${listAllCustomer.first_name}</td>
                            </tr>
                            <tr>
                                <td>last_name</td>
                                <td>${listAllCustomer.last_name}</td>
                            </tr>
                            <tr>
                                <td>phone</td>
                                <td>${listAllCustomer.phone}</td>
                            </tr>
                            <tr>
                                <td>email</td>
                                <td>${listAllCustomer.email}</td>
                            </tr>
                            <tr>
                                <td>address</td>
                                <td>${listAllCustomer.address}</td>
                            </tr>
                            <tr>
                                <td>username</td>
                                <td>${listAllCustomer.username}</td>
                            </tr>
                            <tr>
                                <td>password</td>
                                <td>${listAllCustomer.password}</td>
                            </tr>
                            <tr>
                                <td>dob</td>
                                <td>${listAllCustomer.dob}</td>
                            </tr>
                            <tr>
                                <td>gender</td>
                                <td>${listAllCustomer.gender ? 'Male' : 'Female'}</td>
                            </tr>
                            <tr>
                                <td>activity_history</td>
                                <td>${listAllCustomer.activity_history}</td>
                            </tr>
                            <tr>
                                <td>security_question</td>
                                <td>${listAllCustomer.security.security_question}</td>
                            </tr>
                            <tr>
                                <td>securityAnswer</td>
                                <td>${listAllCustomer.secutityAnswer}</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <a href="CustomerServletURL?service=updateCustomer&customerid=${listAllCustomer.customerID}" class="btn btn-primary" title="update" data-toggle="tooltip">Update</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
