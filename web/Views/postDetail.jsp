<%-- 
    Document   : postDetail
    Created on : May 26, 2024, 10:25:12 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog Layout</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="./mktcss/styles.css">
        <style>
            
            .div{
                color: black;
            }
            .material-icons-outlined {
                vertical-align: middle;
                line-height: 1px;
                font-size: 35px;
            }
            .blog-post {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }
            .blog-post img {
                max-width: 100%;
                border-radius: 5px;
                margin-bottom: 15px;
            }
            .blog-title {
                font-size: 2rem;
                margin-bottom: 10px;
            }
            .blog-meta {
                font-size: 0.9rem;
                color: black;
                margin-bottom: 20px;
            }
            .blog-category {
                font-size: 1rem;
                color: #007bff;
            }
        </style>
    </head>


    <body>
        <div class="grid-container">
            <!-- Header -->
            <jsp:include page="header.jsp"></jsp:include>
                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>
                <!-- End Sidebar -->
                <main class="main-container">
                    <div class="row justify-content-center">
                        <div class="col-xl-10">
                            <div class="blog-post">
                                <div class="row">
                                    <div class="col-xl-4">
                                        <img src="${post.thumbnail}" alt="Blog post image"></div>
                                <div class="col-xl-8">
                                    <a href="#Add" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>New Post</span></a>
                                    <a href="EditPost?postID=${post.postID}" class="btn btn-danger"><i class="material-icons">&#xE3C9;</i> <span>Edit</span></a>
                                </div>
                            </div>
                            <h1 class="blog-title">${post.title}</h1>
                            <div class="blog-meta">
                                <h3>${post.brief_information}</h3>
                                <span class="author">Author: ${post.user.first_name} ${post.user.last_name}</span> | 
                                <span class="updated-date">Updated: ${post.date_create_by}</span> | 
                                <span class="blog-category">Category: ${post.cp.category_product.category_name}</span>
                            </div>
                            <div class="blog-meta">
                                <h5>INFO:</h5>
                                <p> ${post.description}</p>
                                Featured:
                                <c:if test="${post.featured == '1'}">
                                    <a href="Status?service=featured&postID=${post.postID}&featured=0"  class="fa fa-check"></a>
                                </c:if>
                                <c:if test="${post.featured != '1'}">
                                    <a href="Status?service=featured&postID=${post.postID}&featured=1"  class="fa fa-times"></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="Add" class="modal fade">
                    <div class="modal-dialog" style="color: black">
                        <div class="modal-content">
                            <form action="PostController" method="post" enctype="multipart/form-data">
                                <div class="modal-header">						
                                    <h4 class="modal-title">New Post</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>title</label>
                                        <input type="text" class="form-control" name="title" required>
                                    </div>
                                    <div class="form-group">
                                        <label>thumbnail</label>
                                        <input type="text" class="form-control" name="thumbnail" >
                                    </div>
                                    <div class="form-group">
                                        <label>Category Post</label>
                                        <select id="category-select" class="form-control" name="category_post">                                   
                                            <c:forEach items="${requestScope.category_product}" var="c">
                                                <option value="${c.category_productID}">${c.category_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>featured</label>
                                        <input type="text" class="form-control" name="featured" required>
                                    </div>

                                    <div class="form-group">
                                        <label>brief_information</label>
                                        <input type="text" class="form-control" name="brief_information" required>
                                    </div>
                                    <div class="form-group">
                                        <label>description</label>
                                        <input type="text" class="form-control" name="description" required>
                                    </div>
                                </div>
                                <div class="modal-footer">

                                    <input type="submit" class="btn btn-info" value="OK">
                                    <input type="hidden" name="service" value="add">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <!-- Bootstrap JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="./mktjs/scripts.js"></script>
    </body>
</html>


