<%-- 
    Document   : ProductListPublic
    Created on : May 29, 2024, 10:38:37 PM
    Author     : phuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <style>
            .disabled {
                pointer-events: none;
                color: grey;
            }
            .nav-item.dropdown:hover {
                background-color: #f0f0f0; /* Màu xám */
            }
            .selected {
                background-color: #C17A74; /* Màu nền cho mục được chọn */
                color: white; /* Màu chữ cho mục được chọn */
            }
            /* Đặt khoảng cách giữa hình ảnh và tiêu đề */
            .carousel-item img {
                margin-bottom: 15px;
            }

            /* Tùy chỉnh tiêu đề sản phẩm */
            .product-title {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                color: #333; /* Màu chữ */
                margin-top: 10px;
            }

            /* Tùy chỉnh liên kết bên trong tiêu đề sản phẩm */
            .product-title a {
                text-decoration: none;
                color: inherit; /* Kế thừa màu chữ từ .product-title */
                transition: color 0.3s ease; /* Hiệu ứng chuyển đổi màu */
            }

            /* Hiệu ứng khi hover chuột vào liên kết */
            .product-title a:hover {
                color: #007bff; /* Màu chữ khi hover */
            }

        </style>
    </head>

    <body>
        <!-- Topbar Start -->
        <div id="cursor"></div>

        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="HomePage" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                </a>

            </div>
            <div class="col-lg-6 col-6 text-left">

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



        <div class="container-fluid">
            <div class="row border-top px-xl-5">

                <div class="col-lg-12">
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


        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Sản Phẩm</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="HomePage">Trang Chủ</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Sản Phẩm</p>

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

                        <a class="btn shadow-none d-flex align-items-center justify-content-center bg-primary text-white w-100" href="ProductsListPublic" style="height: 65px; margin-top: -1px; padding: 0 30px; text-align:center;border: 1px solid black">
                            <h6 class="m-0">Thể Loại</h6>
                        </a>

                        <div style="height: auto; text-align: center; padding: 5px;">
                            <c:forEach items="${requestScope.Cate1}" var="a"> 
                                <div class="nav-item dropdown" style="border: 1px solid black; padding: 5px;">
                                    <a href="ProductsListPublic?cid=${a.category_productID}&feature=${requestScope.feature}" class="nav-link ${requestScope.cid == a.category_productID ? 'selected' : ''}" style="color: black;">${a.category_name}</a>               
                                </div>
                            </c:forEach> 
                        </div>
                    </div> 
                    <div class="border-bottom mb-4 pb-4">
                        <h5 class="font-weight-semi-bold mb-4" style="text-align: center">Đặc Biệt</h5>
                        <form id="select-radio"action="ProductsListPublic" method="get">

                            <input type="hidden" name="cid" value="${requestScope.cid}">  
                            <input type="hidden" name="search" value="${requestScope.search1}"> 
                            <div style="display: flex; justify-content: space-between">
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="price-yes" name="feature" value="yes" onchange="submitForm()"
                                           ${feature eq 'yes' ? 'checked' : ''}>
                                    <label class="custom-control-label" for="price-yes">Có</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="price-no" name="feature" value="no" onchange="submitForm()"
                                           ${feature eq 'no' ? 'checked' : ''}>
                                    <label class="custom-control-label" for="price-no">Không</label>
                                </div>
                            </div>

                        </form>
                    </div>
                    <h5 class="font-weight-semi-bold mb-4" style="text-align: center">SẢN PHẨM ĐẶC BIỆT MỚI RA</h5>
                    <div class="border-bottom mb-4 pb-4">
                        <div id="header-carousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <c:forEach items="${requestScope.LatedProducts}" var="lp" varStatus="status">
                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}" data-bs-interval="10000">
                                        <img src="${lp.thumbnail}" class="d-block w-100" alt="Product image">
                                        <h4 class="product-title"><a href="ProductDetailsPublic?pid=${lp.productID}">${lp.product_name}</a></h4>
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
                <div class="col-lg-9 col-md-12" >
                    <div class="row pb-3">
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <form action="ProductsListPublic" method="get">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="search" placeholder="Tìm theo tên sản phẩm">
                                        <div class="input-group-append">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>

                                    </div>
                                    <input type="hidden" name="feature" value="${requestScope.feature}">  
                                    <input type="hidden" name="cid" value="${requestScope.cid}">                             
                                    <input type="hidden" name="service" value="search">
                                </form>
                            </div>
                        </div>
                        <div id="searchbox">
                        </div>            
                        <c:forEach items="${requestScope.ListProduct}" var="l">
                            <div class="col-lg-4 col-md-6 col-sm-12 pb-1" >

                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <img class="img-fluid w-100" src="${l.thumbnail}" alt="">
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${l.product_name}</h6>
                                        <p>${l.brief_information}</p>
                                        <div class="d-flex justify-content-center">
                                            <h6>${l.original_price}</h6>
                                            <c:if test="${not empty l.sale_price}">
                                                <h6 class="text-muted ml-2"><del>${l.sale_price}</del></h6>
                                                    </c:if>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="ProductDetailsPublic?pid=${l.productID}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Chi Tiết</a>
                                        <a href="javascript:void(0);" class="btn btn-sm text-dark p-0 add-to-cart-btn ${l.quantity - l.quantity_hold == 0 ? 'disabled' : ''}" data-product-id="${l.productID}">
                                            <i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ hàng
                                        </a>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="col-12 pb-1">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center mb-3">
                                    <li class="${requestScope.page==1?"page-item disabled":"page-item"}">
                                        <a class="page-link" href="ProductsListPublic?page=${page-1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <c:choose>
                                        <c:when test="${requestScope.numpage < 7}">
                                            <c:forEach begin="1" end="${requestScope.numpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${requestScope.page == i}">
                                                        <li class="page-item active"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li class="page-item"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${requestScope.page <= 4}">
                                                        <c:forEach begin="1" end="5" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                    <li class="page-item"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${requestScope.numpage}</a></li>
                                                    </c:when>
                                                    <c:when test="${requestScope.page > 4 && requestScope.page < requestScope.numpage - 3}">
                                                    <li class="page-item"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">1</a></li>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                        <c:forEach begin="${requestScope.page - 2}" end="${requestScope.page + 2}" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                    <li class="page-item"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${requestScope.numpage}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">1</a></li>
                                                    <li class="page-item"><span class="page-link">...</span></li>
                                                        <c:forEach begin="${requestScope.numpage - 4}" end="${requestScope.numpage}" var="i">
                                                        <li class="${requestScope.page==i ? 'page-item active' : 'page-item'}">
                                                            <a class="page-link" href="ProductsListPublic?page=${i}&search=${requestScope.search1}&cid=${requestScope.cid}&feature=${requestScope.feature}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>



                                    <li class="${requestScope.page== requestScope.numpage?"page-item disabled":"page-item"}">
                                        <a class="page-link" href="ProductsListPublic?page=${page+1}" aria-label="Next">
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
       <jsp:include page="footter.jsp"/>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script type="text/javascript">
                                        window.onload = function () {
            <c:if test="${not empty requestScope.search1}">
                                            var searchboxElement = document.getElementById("searchbox");
                                            if (searchboxElement) {
                                                searchboxElement.scrollIntoView({
                                                    behavior: 'smooth', // Thêm thuộc tính behavior để làm mềm cuộn
                                                    block: 'start' // Chỉ định vị trí cuộn tới của phần tử
                                                });
                                            }
            </c:if>
                                        };
        </script>
        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>
        <script>
                                        function submitForm() {
                                            document.getElementById("select-radio").submit();
                                        }
        </script>
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

                        // Đặt vị trí ban đầu của hình ảnh bản sao
                       

                        // Di chuyển hình ảnh bản sao đến vị trí của giỏ hàng
                        

                        // Loại bỏ hình ảnh bản sao sau khi di chuyển
                        

                            // Gửi yêu cầu AJAX để thêm sản phẩm vào giỏ hàng
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
        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>