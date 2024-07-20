<%-- 
    Document   : HomePage
    Created on : May 19, 2024, 4:33:17 PM
    Author     : phuan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            .aa {
                width: 200px; /* Đặt chiều rộng mong muốn */
                height: 200px; /* Đặt chiều cao mong muốn */
                object-fit: contain; /* Đảm bảo toàn bộ ảnh hiển thị trong khu vực, có thể để lại khoảng trắng nếu tỷ lệ khung hình không khớp */
                border: 1px solid #ddd; /* Tùy chọn: thêm viền để rõ ràng hơn */
                display: block; /* Đảm bảo ảnh hiển thị như một khối */

            }
            .bbb{
                display: flex;
                justify-content: center;
                align-items: center;

            }
            #moreBtn {
                margin-top: 20px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;

            }
            .product-img1 img {
                width: 100%;
                height: auto;
            }
            .product-img1 {
                height: 200px; /* Set a fixed height for the image container */

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
                <form action="ProductsListPublic" method="get">
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
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                        <h6 class="m-0">Thể Loại</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <c:forEach items="${requestScope.Cate1}" var="a"> 
                                <div class="nav-item dropdown"> 
                                    <a href="ProductsListPublic?cname=${a.category_name}" class="nav-link" >${a.category_name}</a>
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
                                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">HI ${cus1.first_name} ${cus1.last_name}</a>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <a href="editProfileCustomerURL?customerid=${cus1.customerID}" class="dropdown-item">Cá Nhân</a>
                                                <a href="MyOrderURL?customerid=${cus1.customerID}" class="dropdown-item">Đơn Hàng</a>
                                            </div>
                                        </div>
                                        <a href="LogOut" class="nav-link ml-3">Đăng Xuất</a>
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
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach items="${requestScope.slider1}" var="l1">
                                <div class="carousel-item active" style="height: 410px;">
                                    <img class="img-fluid" src="${l1.image}" alt="Image">
                                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                        <div class="p-3" style="max-width: 700px;">
                                            <h4 class="text-light text-uppercase font-weight-medium mb-3">${l1.notes}</h4>
                                            <h3 class="display-4 text-white font-weight-semi-bold mb-4">${l1.title}</h3>
                                            <a href="${l1.link}" class="btn btn-light py-2 px-3">Chi tiết</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>                       
                            <c:forEach items="${requestScope.slider}" var="l">
                                <div class="carousel-item " style="height: 410px;">
                                    <img class="img-fluid" src="${l.image}" alt="Image">
                                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                        <div class="p-3" style="max-width: 700px;">
                                            <h4 class="text-light text-uppercase font-weight-medium mb-3">${l.notes}</h4>
                                            <h3 class="display-4 text-white font-weight-semi-bold mb-4">${l.title}</h3>
                                            <a href="${l.link}" class="btn btn-light py-2 px-3">Tại Đây</a>
                                        </div>
                                    </div>
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
        </div>
        <!-- Navbar End -->


        <!-- Featured Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Chất Lượng</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Giao Hàng Miễn Phí</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Hoàn Trả Trong 7 Ngày</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Hỗ trợ 24/7</h5>
                    </div>
                </div>
            </div>
        </div>
        <!-- Featured End -->


        <!-- Categories Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <c:forEach items="${requestScope.CountP}" var="cp">
                    <div class="col-lg-4 col-md-6 pb-1">
                        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                            <p class="text-right">Số lượng: ${cp.value} </p>
                            <a href="ProductsListPublic?cname=${cp.key}" class="cat-img position-relative overflow-hidden mb-3">
                                <c:forEach items="${requestScope.imageC}" var="ic">
                                    <c:if test="${ic.key == cp.key}">
                                        <img class="img-fluid" src="${ic.value}" alt="">
                                    </c:if>
                                </c:forEach>
                            </a>
                            <h5 class="font-weight-semi-bold m-0">${cp.key}</h5>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- Categories End -->


            <!-- Offer Start -->

            <!-- Offer End -->


            <!-- Products Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Sản Phẩm Phổ Biến</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${requestScope.AllP}"  begin="1" end="8" var="p">
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <img class="img-fluid w-100" src="${p.thumbnail}" alt="">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3">${p.product_name}</h6>
                                    <div class="d-flex justify-content-center">
                                        <h6>${p.original_price}</h6>
                                        <c:if test="${not empty p.sale_price}">
                                            <h6 class="text-muted ml-2"><del>${p.sale_price}</del></h6>
                                                </c:if>                       
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <p>${p.brief_information}</p>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between bg-light border">
                                    <a href="ProductDetailsPublic?pid=${p.productID}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Chi tiết</a>
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.cus}">
                                            <a href="javascript:void(0);" class="btn btn-sm text-dark p-0 add-to-cart-btn ${p.quantity - p.quantity_hold == 0 ? 'disabled' : ''}" data-product-id="${p.productID}">
                                                <i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ hàng
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="LoginCusController" class="btn btn-sm text-dark p-0">
                                                <i class="fas fa-sign-in-alt text-primary mr-1"></i>Đăng nhập để thêm
                                            </a>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </div>   
                    </c:forEach>
                </div>

            </div>
            <div class="bbb">
                <button id="moreBtn" onclick="location.href = 'ProductsListPublic?feature=yes'" class="btn btn-primary">Thêm</button>
            </div>
            <!-- Products End -->





            <!-- Products Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Bài Viết Phổ Biến</span></h2>
                </div>


                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${requestScope.HotPost}" var="hp">
                        <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img1 position-relative overflow-hidden bg-transparent border p-0">
                                    <img src="${hp.thumbnail}" alt="post">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <a href="BlogDetail?postID=${hp.postID}" class="text-truncate mb-3">${hp.title}</a>
                                    <div class="d-flex justify-content-center">
                                        <p>${hp.brief_information}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
            <!-- Products End -->


            <!-- Vendor Start -->

            <!-- Vendor End -->


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
                                <!--                        <form action="">
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
                                                        </form>-->
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

            <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');

                        addToCartButtons.forEach(button => {
                            button.addEventListener('click', function (event) {
                                event.preventDefault(); // Ngăn chặn hành động mặc định của nút

                                // Lấy ID sản phẩm từ thuộc tính data
                                const productId = this.getAttribute('data-product-id');

                                // Tạo hiệu ứng thêm vào giỏ hàng
                                const cartIcon = document.querySelector('.fa-shopping-cart');

                                // Tạo bản sao của hình ảnh sản phẩm
                                ;

                                // Lấy vị trí của hình ảnh sản phẩm và giỏ hàng
                                const cartIconRect = cartIcon.getBoundingClientRect();

                                $.ajax({
                                    url: 'AddToCart',
                                    method: 'GET',
                                    data: {pid: productId},
                                    success: function (response) {
                                        // Xử lý phản hồi thành công, ví dụ cập nhật giỏ hàng
                                        alert('Sản phẩm đã được thêm vào giỏ hàng!');
                                    },
                                    error: function (error) {
                                        // Xử lý lỗi
                                        alert('Có lỗi xảy ra. Vui lòng thử lại.');
                                    }
                                });
                            }, 1100);
                        });
                    });

            </script>
    </body>

</html>
