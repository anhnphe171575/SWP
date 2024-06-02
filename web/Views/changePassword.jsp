<%-- 
    Document   : newjsp
    Created on : May 15, 2024, 1:09:30 AM
    Author     : Nitro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html>

<html>
    <script>
        $(document).ready(function () {
            $('.pass_show').append('<span class="ptxt">Show</span>');
        });


        $(document).on('click', '.pass_show .ptxt', function () {

            $(this).text($(this).text() == "Show" ? "Hide" : "Show");

            $(this).prev().attr('type', function (index, attr) {
                return attr == 'password' ? 'text' : 'password';
            });

        });
    </script>   

    <style>

        body{

            background-color: pink;
        }
        .pass_show{
            position: relative
        }

        .pass_show .ptxt {

            position: absolute;

            top: 50%;

            right: 10px;

            z-index: 1;

            color: #f36c01;

            margin-top: -10px;

            cursor: pointer;

            transition: .3s ease all;

        }

        .pass_show .ptxt:hover{
            color: #333333;
        }
    </style>
    <body >
        <div class="row">
            <div style="margin: auto;   " class="col-sm-4">
                <form  action="ChangePassword" method="post" >
                    
                    <label>Current Password</label>
                    <div class="form-group pass_show"> 
                        <input type="password" name="opassword"  class="form-control" placeholder="Current Password"> 
                    </div> 
                    <label>New Password</label>
                    <div class="form-group pass_show"> 
                        <input type="password" name="npassword" class="form-control" placeholder="New Password"> 
                    </div> 
                    <label>Confirm Password</label>
                    <div class="form-group pass_show"> 
                        <input type="password" name="rpassword"  class="form-control" placeholder="Confirm Password"> 
                    </div> 
                    <label>Security Question</label>
                    <div class="form-group pass_show"> 
                        
                        <select name="security_question">
                            <c:forEach items="${security_question}" var="sq">
                            <option value="${sq.securityID}">${sq.security_question}</option>
                            </c:forEach>
                        </select >
                    </div>
                    <label>Answer</label>
                    <div class="form-group "> 
                        <input type="text" name="securityAnswer"  class="form-control" placeholder=""> 
                    </div> 
                    <label>
                    <h3>${requestScope.ms}</h3>
                    <input type="submit" name="submit" value="change"> 
                    <input type="hidden" name="username" value="${sessionScope.cus.username}">
                </form>
            </div>  
        </div>
    </div>
</body>
</html>