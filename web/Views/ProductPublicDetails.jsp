<%-- 
    Document   : ProductPublicDetails.jsp
    Created on : Jun 1, 2024, 4:32:25 AM
    Author     : phuan
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Product Public Detail</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->

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
            .product-availability {
                color: #333; /* Text color */
                font-size: 14px; /* Font size */
                font-weight: bold; /* Font weight */
                margin-top: 10px; /* Margin top */
            }

        </style>
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

    </head>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.btn-minus').click(function () {
                var $input = $(this).closest('.quantity').find('input');
                var value = parseInt($input.val(), 10);
                if (value > 1) {
                    $input.val(value + 1);
                }
            });

            $('.btn-plus').click(function () {
                var $input = $(this).closest('.quantity').find('input');
                var value = parseInt($input.val(), 10);
                $input.val(value - 1);
            });

            $('#quantity-input').on('input', function () {
                // Chỉ cho phép số nguyên dương
                this.value = this.value.replace(/[^0-9]/g, '');
                if (this.value === '')
                    this.value = 1; // Đặt giá trị mặc định nếu trống
            });

            $('#quantity-input').on('change', function () {
                // Đảm bảo số lượng tối thiểu là 1
                if (this.value < 1)
                    this.value = 1;
            });
        });

//        function addToCart() {
//            var quantity = $('#quantity-input').val();
//            var productID = '123'; // Thay thế '123' bằng ID sản phẩm thực tế
//            // Thực hiện hành động khi nhấn nút "Add to Cart"
//            alert('Added ' + quantity + ' items to the cart.');
//            // Ví dụ về chuyển hướng với productID và quantity là các tham số truy vấn
//            location.href = 'AddToCart?pid=' + productID + '&quantity=' + quantity;
//        }
    </script>
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
                        <input type="text" class="form-control" placeholder="Search for products" name="search">
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
                                        <a href="ProductsListPublic?cname=${a.category_name}" class="nav-link" >${a.category_name}</a>
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
                                    <a href="HomePage" class="nav-item nav-link ">Trang chủ</a>
                                    <a href="ProductsListPublic" class="nav-item nav-link active">Sản Phẩm</a>
                                    <div class="nav-item dropdown">
                                        <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Khác</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <a href="BlogController" class="nav-item nav-link">Bài Viết</a>
                                        </div>
                                    </div>
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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Chi Tiết Sản Phẩm</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="HomePage">Trang Chủ</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Sản Phẩm</p>
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
                                        <h5 class="font-weight-semi-bold">NXB: ${p.brand}</h5>

                    <div class="d-flex mb-3">
                       
                                            

                        <small class="pt-1">(${requestScope.qreview} Đánh Giá)</small>
                        
                    </div>
                    <div style="display: flex; padding-bottom: 5px">
                        <c:if test="${not empty p.sale_price}">
                            <h3 class="font-weight-semi-bold mb-4" style="margin-right: 10px;">$${p.sale_price}</h3>
                            <del>  <h3 class="font-weight-semi-bold mb-4">$${p.original_price}</h3></del>

                        </c:if>
                        
                             <c:if test="${ empty p.sale_price}">
                              <h3 class="font-weight-semi-bold mb-4">$${p.original_price}</h3>

                        </c:if>
                    </div>
                    <p class="mb-4">${p.product_description}</p>


                    <div class="d-flex align-items-center mb-4 pt-2">
                        <div class="input-group quantity mr-3" style="width: 130px;">
                            <div class="input-group-btn">
                                <button class="btn btn-primary btn-minus">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                            <input type="text" class="form-control bg-secondary text-center" value="1" id="quantity-input" min = "1"  max="${p.quantity - p.quantity_hold}">
                            <div class="input-group-btn">
                                <button class="btn btn-primary btn-plus">
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>

                        <p class="product-availability">${p.quantity - p.quantity_hold} sản phẩm còn khả dụng</p>
                    </div>   
                    <button type="button" class="btn btn-primary px-3 add-to-cart-btn ${p.quantity - p.quantity_hold == 0 ? 'disabled' : ''}" data-product-id="${p.productID}">
                                <i class="fa fa-shopping-cart mr-1"></i> Thêm vào giỏ hàng
                            </button>

                </div>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="nav nav-tabs justify-content-center border-secondary mb-4">

                        <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Đánh Giá (${qreview})</a>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane fade" id="tab-pane-3">
                            <div class="row">
                                <div class="col-md-6">
                                    <h4 class="mb-4">Review for ${p.product_name}</h4>
                                    <c:forEach items="${requestScope.feedback}" var="fb">
                                        <div class="media mb-4">
                                            <div class="media-body">
                                                <h6>${fb.customer.first_name} ${fb.customer.getLast_name()}<small> - <i>${fb.update_date_feedback}</i></small></h6>
                                                <div class="star-rating1">
                                                    <c:forEach begin="1" end="${fb.rate_star}" var="star">
                                                        <span class="${star <= fb.rate_star ? 'filled' : 'empty'}">☆</span>
                                                    </c:forEach>
                                                </div>
                                                <p>${fb.comment}</p>
                                                <img src="${fb.image}" alt="Image" class="img-fluid mr-3 mt-1" style="width: 115px;">
                                                <hr/>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="col-md-6">
                                    <c:choose>
                                        <c:when test="${not empty requestScope.activate}">
                                            <h4 class="mb-4">Đánh Giá</h4>
                                            <form action="ProductDetailsPublic" method="post"  enctype="multipart/form-data">
                                                <div class="d-flex my-3">
                                                    <p class="mb-0 mr-2 ">Đánh Giá Sao * :</p>                         
                                                    <div class="star-rating">
                                                        <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 stars" required >☆</label>
                                                        <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 stars" required>☆</label>
                                                        <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 stars" required>☆</label>
                                                        <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 stars" required>☆</label>
                                                        <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star" required>☆</label>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="message">Đánh Giá của bạn *</label><br>
                                                    Ảnh: <input type="file" name="file" id="file" style="margin-bottom: 10px" required=""><br>
                                                    Nội dung:<textarea id="message" cols="30" rows="5" class="form-control" name="comment" required=""></textarea>
                                                </div>                                 
                                                <div class="form-group mb-0">
                                                    <input type="submit" value="submit" class="btn btn-primary px-3">
                                                </div>
                                                <input type="hidden" value="${p.productID}" name ="pid">
                                                <input type="hidden" value="${orderid}" name ="orderid">
                                            </form>
                                        </c:when>                                      
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
                <h2 class="section-title px-5"><span class="px-2">Sản Phẩm Khác</span></h2>
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
                                        <h6>VND${rp.original_price}</h6>
                                        <c:if test="${not empty l.sale_price}">
                                            <h6 class="text-muted ml-2"><del>VND${rp.sale_price}</del></h6>
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

        <script>
        document.addEventListener('DOMContentLoaded', function () {
            const quantityInput = document.getElementById('quantity-input');
            const btnMinus = document.querySelector('.btn-minus');
            const btnPlus = document.querySelector('.btn-plus');
            const addToCartButton = document.querySelector('.add-to-cart-btn');
            const maxQuantity = ${p.quantity - p.quantity_hold};

            function updateQuantity(value) {
                if (value < 1) {
                    value = 1;
                } else if (value > maxQuantity) {
                    value = maxQuantity;
                }
                quantityInput.value = value;
            }

            btnMinus.addEventListener('click', function () {
                let currentValue = parseInt(quantityInput.value) || 1;
                updateQuantity(currentValue - 1);
            });

            btnPlus.addEventListener('click', function () {
                let currentValue = parseInt(quantityInput.value) || 1;
                updateQuantity(currentValue + 1);
            });

            quantityInput.addEventListener('input', function () {
                let currentValue = parseInt(quantityInput.value) || 1;
                updateQuantity(currentValue);
            });

            addToCartButton.addEventListener('click', function (event) {
                event.preventDefault();

                const productId = this.getAttribute('data-product-id');
                const quantity = parseInt(quantityInput.value);

                if (isNaN(quantity) || quantity < 1 || quantity > maxQuantity) {
                    alert('Vui lòng chọn số lượng hợp lệ.');
                    return;
                }

                $.ajax({
                    url: 'AddToCart',
                    method: 'GET',
                    data: {pid: productId, quantity: quantity},
                    success: function (response) {
                        alert('Sản phẩm đã được thêm vào giỏ hàng!');
                        // Xử lý cập nhật giỏ hàng tại đây (nếu cần)
                    },
                    error: function (error) {
                        alert('Có lỗi xảy ra. Vui lòng thử lại.');
                    }
                });
            });
        });
        </script>

    </body>

</html>
