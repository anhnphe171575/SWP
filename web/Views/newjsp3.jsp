<%-- 
    Document   : newjsp3
    Created on : Jun 6, 2024, 11:35:19 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">

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
        <title>Order Summary</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
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
                        <input type="text" class="form-control" placeholder="Search for products" name = "search">
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

                <a href="" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge">${sessionScope.cart.size()}</span>
                </a>
            </div>
        </div>
        <!-- Topbar End -->
        <!-- Navbar Start -->
        <div class="container-fluid">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                        <h6 class="m-0">Categories</h6>
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
                                <a href="HomePage" class="nav-item nav-link">Home</a>
                                <a href="ProductsListPublic" class="nav-item nav-link">Shop</a>

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
        <!-- Navbar End -->
        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="HomePage">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Shopping Cart</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->
        <h2 style="text-align: center">Order Summary</h2>

        <h3>Selected Products</h3>
        <!-- Cart Start -->
        <div class="container-fluid pt-5">

            <div class="row px-xl-5">

                <div class="col-lg-8 table-responsive mb-5">
                    <table class="table table-bordered text-center mb-0">
                        <thead class="bg-secondary text-dark">
                            <tr>
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <form id="cartForm" action="CartContact" method="Get">
                            <tbody class="align-middle">

                                <c:forEach items="${requestScope.list}" var="l" varStatus="status">
                                    <tr>

                                        <td class="align-middle"><img src="${l.product.thumbnail}" alt="" style="width: 50px;">${l.product.product_name}</td>
                                            <c:choose>
                                                <c:when test="${l.product.sale_price != 0}">
                                                <td class="align-middle" id="price-${status.index}">${l.product.sale_price}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="align-middle" id="price-${status.index}">${l.product.original_price}</td>
                                            </c:otherwise>
                                        </c:choose>


                                        <td class="align-middle">
                                            <form class="quantityForm" action="CartDetails" method="post">
                                                <input type="hidden" name="cartid" value="${l.cart.getCartID()}">
                                                <input type="hidden" name="pid" value="${l.product.productID}">
                                                <div class="input-group quantity mx-auto" style="width: 100px;">

                                                    <input type="text" class="form-control form-control-sm bg-secondary text-center quantity-input" id="quantity-${status.index}" value="${l.quantity}" name ="quantity" readonly="">

                                                </div>
                                            </form>
                                        </td>


                                        <td class="align-middle" id="total-${status.index}">
                                            ${l.product.sale_price != 0 ? l.product.sale_price * l.quantity : l.product.original_price * l.quantity}
                                        </td>      
                                        <c:set var="itemPrice" value="${l.product.sale_price != 0 ? l.product.sale_price : l.product.original_price}" />
                                        <c:set var="subtotal" value="${itemPrice * l.quantity}" />
                                        <c:set var="totalOrderPrice" value="${totalOrderPrice + subtotal}" />
                                <form action="CartDetails" method="post">

                                    <td class="align-middle">
                                        <button class="btn btn-sm btn-primary" type="submit" name="delete" value="delete">
                                            <i class="fa fa-times"></i>
                                        </button>
                                    </td>       
                                    <input type="hidden" name="cartid" value="${l.cart.getCartID()}">
                                    <input type="hidden" name="cartitemid" value="${l.getCarItemID()}">
                                </form>
                                </tr>

                            </c:forEach>

                            </tbody>
                    </table>
                </div>
                <div class="col-lg-4">

                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                        </div>                    
                        <div class="card-footer border-secondary bg-transparent">
                            <div class="d-flex justify-content-between mt-2">
                                <h5 class="font-weight-bold">Total</h5>
                                <h5 class="font-weight-bold">${totalOrderPrice}</h5>
                            </div>
                            <button class="btn btn-block btn-primary my-3 py-3" id="proceedToCheckout">Proceed To Checkout</button>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <!-- Cart End -->



        <div class="container">
            <h2>Shipping Address Selection</h2>
            <form id="selectAddressForm" action="CartContact" method="GET">
                <c:forEach items="${requestScope.list1}" var="c" varStatus="status">
                    <label>
                        <input type="radio" id="address${status.index}" name="selectedAddress" value="${c.getReceiverID()}" class="addressCheckbox"
                               ${c.getReceiverID() == requestScope.check ? 'checked' : ''} onchange="submitForm()">
                        <label for="address${status.index}">${c.getReceiverFullName()}</label>
                    </label>
                </c:forEach>  
                <c:forEach items="${requestScope.carItemIDs}" var="a">
                    <input type="hidden" name="carItemIDs" value="${a}">

                </c:forEach>
                <c:forEach items="${requestScope.cartIDs}" var="b">
                    <input type="hidden" name="cartIDs" value="${b}">
                </c:forEach>
            </form>
        </div>

        <c:set value="${info}" var="cus1"></c:set>
            <h3>Receiver Information</h3>

            <table>


                <tr>
                    <th>Full Name</th>
                    <td>${cus1.getReceiverFullName()}</td>
            </tr>
            <tr>
                <th>Gender</th>
                <td>
                    <c:choose>
                        <c:when test="${cus1.customer.gender == true}">
                            Female
                        </c:when>
                        <c:otherwise>
                            Male
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${cus1.customer.email}</td>
            </tr>
            <tr>
                <th>Mobile</th>
                <td>${cus1.getReceiverMobile()}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${cus1.getReceiverAddress()}</td>
            </tr>
            <tr>
                <th>Notes</th>
                <td><textarea></textarea></td>
            </tr>
        </table>

    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>



    <script type="text/javascript">
                                   window.onload = function () {
        <c:if test="${not empty requestScope.scroll}">
                                       var searchboxElement = document.getElementById("ListPro");
                                       if (searchboxElement) {
                                           searchboxElement.scrollIntoView({
                                               behavior: 'smooth', // Thêm thuộc tính behavior để làm mềm cuộn
                                               block: 'start' // Chỉ định vị trí cuộn tới của phần tử
                                           });
                                       }
        </c:if>
                                   };
    </script>
    <script>
        function submitForm() {
            document.getElementById("selectAddressForm").submit();
        }
    </script>
</html>

