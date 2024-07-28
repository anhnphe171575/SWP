<%-- 
    Document   : blogDetail
    Created on : May 26, 2024, 9:11:09 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="HomePage" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm" name="search">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                    <input type="hidden" name="service" value="search">
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">

                <a href="CartDetails" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge">${sessionScope.cart.size()}</span>
                </a>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <div id="stickyBar"class="bar">
            <div class="container-fluid">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                        <h6 class="m-0">Thể Loại</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <c:forEach items="${requestScope.Cate1}" var="a"> 
                                <div class="nav-item dropdown"> 
                                    <a href="#" class="nav-link" data-toggle="dropdown">${a.category_name}<i class="fa fa-angle-down float-right mt-1"></i></a>
                                    <div class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0">
                                        <c:forEach items="${requestScope.CategoryB}" var="c"> 
                                            <c:if test="${a.getCategory_name() == c.categoryProduct.getCategory_name()}">

                                                <a href="ProductsListPublic?cid=${a.category_productID}" class="dropdown-item">${c.brand}</a>

                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>                   
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="HomePage" class="nav-item nav-link active">Trang chủ</a>
                                <a href="ProductsListPublic" class="nav-item nav-link">Sản Phẩm</a>
                                <div class="nav-item dropdown">
                                    <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Khác</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                                                <a href="BlogController" class="nav-item nav-link">Bài Viết</a>
                                    </div>
                                </div>
                                <a href="contact.html" class="nav-item nav-link">Liên Hệ</a>
                            </div>       
                            <c:set  value="${sessionScope.cus}" var="cus1"></c:set>
                            <c:choose>
                                <c:when test="${not empty sessionScope.cus}">

                                    <div class="navbar-nav ml-auto py-0">
                                          <div class="nav-item dropdown">
                                        <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">HI ${cus1.first_name} ${cus1.last_name}</a>
                                        <div class="dropdown-menu ml-auto py-0">
                                            <a href="editProfileCustomerURL?customerid=${cus1.customerID}" class="dropdown-item">Cá Nhân</a>
                                             <a href="MyOrderURL?customerid=${cus1.customerID}" class="dropdown-item">Đơn hàng </a>
                                        </div>
                                        <!--<a href="LogOut">Log out</a>-->
                                        
                                          </div>
                                        <a href="LogOut" class="nav-link ">Đăng Xuất</a>
                                    </div>

                                </c:when>
                                <c:otherwise>
                                    <div class="navbar-nav ml-auto py-0">
                                        <a href="LoginCusController" class="nav-item nav-link">Đăng Nhập</a>
                                        <a href="signup" class="nav-item nav-link">Đăng Ký</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        </div>
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Chi Tiết Bài Viết</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="HomePage">Trang Chủ</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Bài Viết</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Shop Detail Start -->
        <div class="container-fluid py-5">
            <div class="row px-xl-5">
                <div class="col-lg-5 pb-5">
                    <div id="product-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner border">
                            <div class="carousel-item active">
                                <img class="w-100 h-100" src="${blog.thumbnail}" alt="Image">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-7 pb-5">
                    <h3 class="font-weight-semi-bold">${blog.title}</h3>
                    <div class="d-flex mb-3">
                        <small class="pt-1">Ngày đăng: ${blog.date_create_by}</small>
                    </div>
                    <p class="mb-4">${blog.description}</p>
                    <div class="d-flex pt-2">
                        <p class="text-dark font-weight-medium mb-0 mr-2">Chia sẻ bởi: ${blog.staff.username}</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Shop Detail End -->


        <!-- Products Start -->
        <div class="container-fluid py-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">Bài Viết Khác</span></h2>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    
                    <div class="owl-carousel related-carousel">
                        <c:forEach items="${blogc}" var="b">
                        <div class="card product-item border-0">
                            
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <img class="img-fluid w-100" src="${b.thumbnail}" alt="">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3">${b.title}</h6>
                                    <div class="d-flex justify-content-center">
                                    </div>
                                </div>
                               
                        </div>
                                    </c:forEach>
                    </div>
                                    
                </div>
            </div>
        </div>
        <!-- Products End -->


        <!-- Footer Start -->
       <jsp:include page="footter.jsp"/>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>
