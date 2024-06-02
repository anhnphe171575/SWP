<%-- 
    Document   : insertCustomer
    Created on : Apr 15, 2024, 10:00:31 AM
    Author     : Nguyễn Đăng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Entity.Security"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CustomerServletURL" >
            <table>
               
                <tr>
                    <td>first_name</td>
                    <td><input type="text" name="first_name" id=""></td>
                </tr>
                <tr>
                    <td>last_name</td>
                    <td><input type="text" name="last_name" id=""></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><input type="text" name="phone" id=""></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="email" id=""></td>
                </tr>
                <tr>
                    <td>address</td>
                    <td><input type="text" name="address" id=""></td>
                </tr>
                <tr>
                    <td>username</td>
                    <td><input type="text" name="username" id=""></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="text" name="password" id=""></td>
                </tr>
                <tr>
                    <td>dob</td>
                    <td><input type="date" name="dob" id=""></td>
                </tr>
                <tr>
                    <td>gender</td>
                    <td>
                        <input type="radio" name="gender" value="true" id="male">
                        <label for="male">Male</label>
                        <input type="radio" name="gender" value="false" id="female">
                        <label for="female">Female</label> </td>
                </tr>
                <tr>
                    <td>status</td>
                    <td>
                        <input type="radio" name="status" value="1" >
                        <label for="male">Show</label>
                        <input type="radio" name="status" value="0">
                        <label for="female">Hide</label> </td>
                </tr>
                <tr>
                    <td>
                <select name="security">
                      <%Vector<Security> vector=(Vector<Security>)request.getAttribute("security");
                  for(Security obj: vector){
        %>
                    <option value="<%=obj.getSecurityID()%>"> <%=obj.getSecurity_question()%></option>
                    <% }%>
                </select>
                </td>
                </tr>
                <tr>
                    <td>secutityAnswer</td>
                    <td><input type="text" name="securityAnswer" id=""></td>
                </tr>
                <td>
                <td><input type="submit" name="submit" value="add Customer"></td>
                <td><input type="reset" value="reset"></td>
                <td><input type="hidden" name="service" value="addCustomer"></td>
                </tr>

            </table>

        </form>

    </body>
</html>
