<%-- 
    Document   : listPost
    Created on : May 18, 2024, 5:40:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : blogList
    Created on : May 25, 2024, 11:59:30 PM
    Author     : admin
--%>
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
    <style>
           
            .nav-item.dropdown:hover {
                background-color: #f0f0f0; /* Màu xám */
            }
            .selected {
    background-color: #C17A74; /* Màu nền cho mục được chọn */
    color: white; /* Màu chữ cho mục được chọn */
}
        </style>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">

            <div class="row align-items-center py-3 px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-9 col-6 text-right">

                    <a href="" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
                        <span class="badge">0</span>
                    </a>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <div class="container-fluid">
            <div class="row border-top px-xl-5">

                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="index.html" class="nav-item nav-link">Home</a>
                                <a href="shop.html" class="nav-item nav-link active">Shop</a>
                                <a href="detail.html" class="nav-item nav-link">Shop Detail</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="cart.html" class="dropdown-item">Shopping Cart</a>
                                        <a href="checkout.html" class="dropdown-item">Checkout</a>
                                    </div>
                                </div>
                                <a href="contact.html" class="nav-item nav-link">Contact</a>
                            </div>
                            <div class="navbar-nav ml-auto py-0">
                                <a href="" class="nav-item nav-link">Login</a>
                                <a href="" class="nav-item nav-link">Register</a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Our Blog</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Blog</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Shop Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->        
                <div class="col-lg-3 col-md-12">
                    <!-- Brand Start -->
                    <div class="border-bottom mb-4 pb-4">

                        <a class="btn shadow-none d-flex align-items-center justify-content-center bg-primary text-white w-100" href="BlogController" style="height: 65px; margin-top: -1px; padding: 0 30px; text-align:center;border: 1px solid black">
                            <h6 class="m-0">Categories</h6>
                        </a>

                        <div style="height: auto; text-align: center; padding: 5px;">
                            <c:forEach items="${requestScope.category_product}" var="a"> 
                                <div class="nav-item dropdown" style="border: 1px solid black; padding: 5px;">
                                    <a href="BlogController?cid=${a.category_productID}" class="nav-link ${cid == a.category_productID ? 'selected' : ''}" style="color: black;">${a.category_name}</a>                                                                                     
                                </div>
                            </c:forEach> 
                        </div>
                    </div> 
                    <div class="border-bottom mb-4 pb-4">
                        <div id="header-carousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <c:forEach items="${requestScope.lastPost}" var="lp" varStatus="status">
                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}" data-bs-interval="10000">
                                        <img src="${lp.thumbnail}" class="d-block w-100" alt="Product image">
                                    </div>
                                </c:forEach>
                            </div>
                            <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                                <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                    <span class="carousel-control-prev-icon mb-n2"></span>
                                </div>
                            </a>
                            <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                                <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                    <span class="carousel-control-next-icon mb-n2"></span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            
        
            <!-- Shop Sidebar End -->


            <!-- Shop Product Start -->
            <div class="col-lg-9 col-md-12">
                <div class="row pb-3">
                    <div class="col-12 pb-1">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <form action="BlogController" method="get">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="search" placeholder="Search by name">
                                    <div class="input-group-append">
                                        <span class="input-group-text bg-transparent text-primary">
                                            <i class="fa fa-search"></i>
                                        </span>
                                    </div>
                                </div>
                                <input type="hidden" name="cid" value="${requestScope.cid}">
                                <input type="hidden" name="service" value="search">
                            </form>
                            <!--                                <div class="dropdown ml-4">
                                                                <button class="btn border dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true"
                                                                        aria-expanded="false">
                                                                    Sort by
                                                                </button>
                                                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
                                                                    <a class="dropdown-item" href="#">Latest</a>
                                                                    <a class="dropdown-item" href="#">Popularity</a>
                                                                    <a class="dropdown-item" href="#">Best Rating</a>
                                                                </div>
                                                            </div>-->
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <c:forEach items="${blog}" var="b">
                                <div class="col-lg-6 col-md-6 col-sm-12 pb-1">
                                    <div class="card product-item border-0 mb-4">
                                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                            <img class="img-fluid w-100" src="${b.thumbnail}" alt="">
                                        </div>
                                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                            <h6 class="text-truncate mb-3">${b.title}</h6>
                                            <div class="d-flex justify-content-center">
                                                <p>${b.brief_information}</p>
                                                <h6 class="text-muted ml-2"><del></del></h6>
                                            </div>
                                        </div>
                                        <div class="card-footer d-flex justify-content-between bg-light border">
                                            <a href="BlogController?service=viewDetail&postID=${b.postID}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                     <div class="col-12 pb-1">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center mb-3">
                                    <li class="${requestScope.page==1?"page-item disabled":"page-item"}">
                                        <a class="page-link" href="BlogController?page=${page-1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>

                                    <!--                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                                                <li class="page-item"><a class="page-link" href="#">2</a></li>-->

                                    <c:choose>
                                        <c:when test="${requestScope.numpage < 7}">
                                            <c:forEach begin="1" end="${requestScope.numpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${requestScope.page == i}">
                                                        <li class="page-item active"><a class="page-link" href="BlogController?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li class="page-item"><a class="page-link" href="BlogController?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${requestScope.page <= 4}">
                                                        <c:forEach begin="1" end="5" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="BlogController?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                    <li class="page-item"><a class="page-link" href="BlogController?page=${requestScope.numpage}&search=${requestScope.search1}&cid=${requestScope.cid}">${requestScope.numpage}</a></li>
                                                    </c:when>
                                                    <c:when test="${requestScope.page > 4 && requestScope.page < requestScope.numpage - 3}">
                                                    <li class="page-item"><a class="page-link" href="BlogController?page=1&search=${requestScope.search1}&cid=${requestScope.cid}">1</a></li>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                        <c:forEach begin="${requestScope.page - 2}" end="${requestScope.page + 2}" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="BlogController?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                    <li class="page-item"><a class="page-link" href="BlogController?page=${requestScope.numpage}&search=${requestScope.search1}&cid=${requestScope.cid}">${requestScope.numpage}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="BlogController?page=1&search=${requestScope.search1}&cid=${requestScope.cid}">1</a></li>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                        <c:forEach begin="${requestScope.numpage - 4}" end="${requestScope.numpage}" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="BlogController?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>



                                    <li class="${requestScope.page== requestScope.numpage?"page-item disabled":"page-item"}">
                                        <a class="page-link" href="BlogController?page=${page+1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                </div>
            </div>
            <!-- Shop Product End -->
        </div>
    </div>
    <!-- Shop End -->


    <!-- Footer Start -->
    <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
        <div class="row px-xl-5 pt-5">
            <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                <a href="" class="text-decoration-none">
                    <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper</h1>
                </a>
                <p>Dolore erat dolor sit lorem vero amet. Sed sit lorem magna, ipsum no sit erat lorem et magna ipsum dolore amet erat.</p>
                <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Street, New York, USA</p>
                <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>info@example.com</p>
                <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890</p>
            </div>
            <div class="col-lg-8 col-md-12">
                <div class="row">
                    <div class="col-md-4 mb-5">
                        <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-dark mb-2" href="index.html"><i class="fa fa-angle-right mr-2"></i>Home</a>
                            <a class="text-dark mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                            <a class="text-dark mb-2" href="detail.html"><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                            <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                            <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                            <a class="text-dark" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-dark mb-2" href="index.html"><i class="fa fa-angle-right mr-2"></i>Home</a>
                            <a class="text-dark mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                            <a class="text-dark mb-2" href="detail.html"><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                            <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                            <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                            <a class="text-dark" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>
                        <form action="">
                            <div class="form-group">
                                <input type="text" class="form-control border-0 py-4" placeholder="Your Name" required="required" />
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control border-0 py-4" placeholder="Your Email"
                                       required="required" />
                            </div>
                            <div>
                                <button class="btn btn-primary btn-block border-0 py-3" type="submit">Subscribe Now</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row border-top border-light mx-xl-5 py-4">
            <div class="col-md-6 px-xl-0">
                <p class="mb-md-0 text-center text-md-left text-dark">
                    &copy; <a class="text-dark font-weight-semi-bold" href="#">Your Site Name</a>. All Rights Reserved. Designed
                    by
                    <a class="text-dark font-weight-semi-bold" href="https://htmlcodex.com">HTML Codex</a>
                </p>
            </div>
            <div class="col-md-6 px-xl-0 text-center text-md-right">
                <img class="img-fluid" src="img/payments.png" alt="">
            </div>
        </div>
    </div>
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
