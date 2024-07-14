<%-- 
    Document   : newjsp1
    Created on : May 30, 2024, 9:34:58 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="myForm" action="NewServlet" method="post">
            <input type="checkbox" name="checkbox" value="value1"> Checkbox 1<br>
            <input type="checkbox" name="checkbox" value="value2"> Checkbox 2<br>
        </form>
   
        <script>
            document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
                checkbox.addEventListener('change', function() {
                    document.getElementById('myForm').submit();
                });
            });
        </script>
       <div id="Filter" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Bộ lọc</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="PostController" method="post">
                        <div class="modal-body">
                            <!-- Category Filter -->
                            <div class="form-group">
                                <label for="category-select">category:</label>
                                <select id="category-select" class="form-control" name="category">
                                    <option value="all">ALL</option>
                                    <c:forEach items="${requestScope.category}" var="c">
                                        <option value="${c}">${c}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Author Filter -->
                            <div class="form-group">
                                <label for="author-select">author:</label>
                                <select id="author-select" class="form-control" name="author">                               
                                    <option value="all">ALL</option>
                                    <c:forEach items="${requestScope.user}" var="u">
                                        <option value="${u.first_name} ${u.last_name}">${u.first_name} ${u.last_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Status Filter -->
                            <div class="form-group">
                                <label for="status-select">status:</label>
                                <select id="status-select" class="form-control" name="status">
                                    <option value="3">ALL</option>
                                    <c:forEach items="${requestScope.status}" var="p">
                                        <c:if test="${p == '1'}">
                                            <option value="${p}">Show</option>
                                        </c:if>
                                        <c:if test="${p != '1'}">

                                            <option value="${p}">Hide</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">OK</button>
                            <input type="hidden" name="service" value="filter">
                        </div>
                    </form>
                </div>
            </div>
        </div>
</html>
