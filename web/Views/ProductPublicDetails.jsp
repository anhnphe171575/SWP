<%-- 
    Document   : ProductPublicDetails.jsp
    Created on : Jun 1, 2024, 4:32:25 AM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <style>
            .star-rating {
                direction: rtl;
                display: inline-block;
                font-size: 0;
            }
            .star-rating input {
                display: none;
            }
            .star-rating label {
                display: inline-block;
                font-size: 2rem;
                color: #ccc;
                cursor: pointer;
            }
            .star-rating input:checked ~ label {
                color: #f5b301;
            }
            .star-rating input:checked + label:hover,
            .star-rating input:checked + label:hover ~ label,
            .star-rating label:hover,
            .star-rating label:hover ~ label {
                color: #f5b301;
            }
            .container {
                text-align: center;
            }

            h3 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 1rem;
            }

            button {
                background-color: #007BFF;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 1rem;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #0056b3;
            }
            .star-rating {
                display: inline-block;
                font-size: 1.2rem;
                color: #ccc; /* Default star color */
            }

            .star-rating .filled {
                color: #f5b301; /* Filled star color */
            }
        </style>
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

    </head>

    <body>
        <!-- Topbar Start -->
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for products">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">

                <a href="" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge">0</span>
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
                            <h6 class="m-0">Categories</h6>
                            <i class="fa fa-angle-down text-dark"></i>
                        </a>
                        <nav class="collapse navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                            <div class="navbar-nav w-100 overflow-hidden" style="height: 410px; background-color: #f8f9fa;">
                                <c:forEach items="${requestScope.Cate1}" var="a"> 
                                    <div class="nav-item dropdown"> 
                                        <a href="#" class="nav-link" data-toggle="dropdown">${a.category_name}<i class="fa fa-angle-down float-right mt-1"></i></a>
                                        <div class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0">
                                            <c:forEach items="${requestScope.CategoryB}" var="c"> 
                                                <c:if test="${a.getCategory_name() == c.categoryProduct.getCategory_name()}">
                                                    <a href="" class="dropdown-item">${c.brand}</a>
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
                            <a href="HomePage" class="text-decoration-none d-block d-lg-none">
                                <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                            </a>
                            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0">
                                    <a href="HomePage" class="nav-item nav-link active">Home</a>
                                    <!-- <a href="shop.html" class="nav-item nav-link">Shop</a>
                                    <!--                            <a href="detail.html" class="nav-item nav-link">Shop Detail</a>-->
                                    <div class="nav-item dropdown">
                                        <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <a href="BlogController" class="dropdown-item">Lasted Post</a>
                                        </div>
                                    </div>
                                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                                </div>        
                                <c:set  value="${sessionScope.cus}" var="cus1"></c:set>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.cus}">

                                        <div class="navbar-nav ml-auto py-0">
                                            <a href=""style="margin-right: 10px">HI ${cus1.first_name} ${cus1.last_name}</a>
                                            <a href="LogOut">Log out</a>

                                        </div>

                                    </c:when>
                                    <c:otherwise>
                                        <div class="navbar-nav ml-auto py-0">
                                            <a href="LoginCusController" class="nav-item nav-link">Login</a>
                                            <a href="signup" class="nav-item nav-link">Register</a>
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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Our Shop</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="HomePage">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0" ><a href="ProductsListPublic">Products</a></p>
                     <p class="m-0 px-2">-</p>
                      <p class="m-0">Product Details</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Shop Detail Start -->
        <c:set value="${requestScope.product}" var="p"></c:set>
            <div class="container-fluid py-5">
                <div class="row px-xl-5">
                    <div class="col-lg-5 pb-5">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="${p.thumbnail}" alt="">
                    </div>
                </div>

                <div class="col-lg-7 pb-5">
                    <h3 class="font-weight-semi-bold">${p.product_name}</h3>
                    <div class="d-flex mb-3">
                        <div class="text-primary mr-2">
                            <small class="fas fa-star"></small>
                            <small class="fas fa-star"></small>
                            <small class="fas fa-star"></small>
                            <small class="fas fa-star-half-alt"></small>
                            <small class="far fa-star"></small>
                        </div>
                        <small class="pt-1">(${requestScope.qreview} Reviews)</small>
                    </div>
                    <div style="display: flex; padding-bottom: 5px">
                        <c:if test="${not empty p.sale_price}">
                            <h3 class="font-weight-semi-bold mb-4" style="margin-right: 10px;"><del>$${p.sale_price}</del></h3>
                        </c:if>
                        <h3 class="font-weight-semi-bold mb-4">$150.00</h3>

                    </div>
                    <p class="mb-4">Volup erat ipsum diam elitr rebum et dolor. Est nonumy elitr erat diam stet sit clita ea. Sanc invidunt ipsum et, labore clita lorem magna lorem ut. Erat lorem duo dolor no sea nonumy. Accus labore stet, est lorem sit diam sea et justo, amet at lorem et eirmod ipsum diam et rebum kasd rebum.</p>


                    <div class="d-flex align-items-center mb-4 pt-2">
                        <div class="input-group quantity mr-3" style="width: 130px;">
                            <div class="input-group-btn">
                                <button class="btn btn-primary btn-minus" >
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                            <input type="text" class="form-control bg-secondary text-center" value="1">
                            <div class="input-group-btn">
                                <button class="btn btn-primary btn-plus">
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>
                        <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
                    </div>

                </div>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="nav nav-tabs justify-content-center border-secondary mb-4">

                        <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Reviews (${qreview})</a>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane fade" id="tab-pane-3">
                            <div class="row">
                                <div class="col-md-6">
                                    <h4 class="mb-4">1 review for ${p.product_name}</h4>
                                    <c:forEach items="${requestScope.feedback}" var="fb">
                                        <div class="media mb-4">
                                            <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                            <div class="media-body">
                                                <h6>${fb.customer.first_name} ${fb.customer.getLast_name()}<small> - <i>${fb.update_date_feedback}</i></small></h6>
                                                <div class="star-rating1">
                                                    <c:forEach begin="1" end="${fb.rate_star}" var="star">
                                                        <span class="${star <= fb.rate_star ? 'filled' : 'empty'}">☆</span>
                                                    </c:forEach>
                                                </div>
                                                <p>${fb.comment}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="col-md-6">
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.cus}">
                                            <h4 class="mb-4">Review</h4>
                                            <form action="ProductDetailsPublic" method="post">
                                                <div class="d-flex my-3">
                                                    <p class="mb-0 mr-2 ">Your Rating * :</p>                         
                                                    <div class="star-rating">
                                                        <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 stars" required >☆</label>
                                                        <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 stars" required>☆</label>
                                                        <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 stars" required>☆</label>
                                                        <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 stars" required>☆</label>
                                                        <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star" required>☆</label>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="message">Your Review *</label><br>
                                                    Image: <input type="file" name="image" style="margin-bottom: 10px"><br>
                                                    Content:<textarea id="message" cols="30" rows="5" class="form-control" name="comment" required=""></textarea>
                                                </div>                                 
                                                <div class="form-group mb-0">
                                                    <input type="submit" value="submit" class="btn btn-primary px-3">
                                                </div>
                                                <input type="hidden" value="${p.productID}" name ="pid">
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="container">
                                                <h3>Login to reviews</h3>
                                                <button onclick="location.href = 'LoginCusController'">Login</button>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Shop Detail End -->


        <!-- Products Start -->
        <div class="container-fluid py-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">You May Also Like</span></h2>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="owl-carousel related-carousel">
                        <c:forEach items="${requestScope.relatedP}" var="rp">
                            <div class="card product-item border-0">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <img class="img-fluid w-100" src="${rp.thumbnail}" alt="">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3"><a href="ProductDetailsPublic?pid=${rp.productID}">${rp.product_name}</a></h6>
                                    <div class="d-flex justify-content-center">
                                        <h6>${rp.original_price}</h6>
                                        <c:if test="${not empty l.sale_price}">
                                            <h6 class="text-muted ml-2"><del>${rp.sale_price}</del></h6>
                                                </c:if>
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
        <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="" class="text-decoration-none">
                        <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper</h1>
                    </a>
                    <p></p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Dong Hung,Thai Binh, Viet Nam</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>info@example.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-dark mb-2" href="index.html"><i class="fa fa-angle-right mr-2"></i>Home</a>
                                <!--                            <a class="text-dark mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                                                            <a class="text-dark mb-2" href="detail.html"><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>-->
                                <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                                <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                                <a class="text-dark" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-dark mb-2" href="index.html"><i class="fa fa-angle-right mr-2"></i>Home</a>
                                <!--                            <a class="text-dark mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                                                            <a class="text-dark mb-2" href="detail.html"><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>-->
                                <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                                <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                                <a class="text-dark" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>

                            <div><p>HKD Nguyen Phu Anh </p>
                                <p> Giấy chứng nhận đăng ký HKD số 17A80041952 do Phòng Tài chính - Kế hoạch, Uỷ ban nhân dân thành phố Thái Nguyên cấp ngày 30/5/2019</p>
                                <p> Địa chỉ: Số 235, Đường Quang Trung, Tổ 7, Phường Tân Thịnh, Thành phố Thái Nguyên, Tỉnh Thái Nguyên, Việt Nam</p>
                                <p> Email: teelabvn@gmail.com</p>
                                <p>Điện thoại: 0865539083</p></div>
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
