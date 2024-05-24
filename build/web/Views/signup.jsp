<%-- 
    Document   : signup
    Created on : May 15, 2024, 1:23:13 AM
    Author     : MANH VINH
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Register Forms</title>

        <!-- Icons font CSS-->
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="vncss/vn4.css" rel="stylesheet" media="all">
    </head>

    <body>
        <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div class="wrapper wrapper--w790">
                <div class="card card-5">
                    <div class="card-heading">
                        <h2 class="title">Registration Form</h2>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="signup">
                            <div class="form-row m-b-55">
                                <div class="name">Name</div>
                                <div class="value">
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="firstName">
                                                <label class="label--desc">First name</label>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="lastName">
                                                <label class="label--desc">Last name</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Phone</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="phone">
                                    </div>
                                    <c:if test="${not empty msgPhone}">
                                        <div class="error-message" style="color: red">${msgPhone}</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Email</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="email" name="email">
                                    </div>
                                    <c:if test="${not empty msgEmail}">
                                        <div class="error-message" style="color: red">${msgEmail}</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Address</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="address">
                                    </div>
                                </div>
                            </div>
                            <!-- Date of Birth -->
                            <div class="form-row">
                                <div class="name">Date of Birth</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="date" name="dob">
                                    </div>
                                </div>
                            </div>
                            <!-- Gender -->
                            <div class="form-row">
                                <div class="name">Gender</div>
                                <div class="gender-options">
                                    <label class="radio-container">Male <input type="radio" name="gender" value="true" required>
                                        <span class="checkmark"></span>
                                    </label>
                                    <label class="radio-container">Female <input type="radio" name="gender" value="false" required>
                                        <span class="checkmark"></span></label>
                                </div>
                            </div>
                            <!-- Username -->
                            <div class="form-row">
                                <div class="name">Username</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="username">
                                    </div>
                                    <c:if test="${not empty msgUser}">
                                        <div class="error-message" style="color: red">${msgUser}</div>
                                    </c:if>
                                </div>
                            </div>
                            <!-- Password -->
                            <div class="form-row">
                                <div class="name">Password</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="password" name="password">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Re-enter password</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="password" name="repass">
                                        <c:if test="${not empty msgRepass}">
                                            <div class="error-message" style="color: red">${msgRepass}</div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- Security Question -->
                            <div class="form-row">
                                <div class="name">Security Question</div>
                                <div class="value">
                                    <div class="input-group">
                                        <div class="rs-select2 js-select-simple select--no-search">
                                            <select class="input--style-5" name="question">
                                                <c:forEach items="${securityQuestions}" var="question">
                                                    <option value="${question}">${question}</option>
                                                </c:forEach>
                                            </select>
                                            <div class="select-dropdown"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Answer</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="answer">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button class="btn btn--radius-2 btn--red" type="submit">Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="vnjs/global.js"></script>
    </body>

</html>
