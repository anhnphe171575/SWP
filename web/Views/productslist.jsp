<%-- 
    Document   : productslist
    Created on : May 18, 2024, 5:35:44 PM
    Author     : MANH VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product List</title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Product List</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Năm</th>
                        <th>Mô tả sản phẩm</th>
                        <th>Nổi bật</th>
                        <th>Hình thu nhỏ</th>
                        <th>Thông tin tóm tắt</th>
                        <th>Giá gốc</th>
                        <th>Giá bán</th>
                        <th>Tên danh mục</th>
                        <th>Mô tả danh mục</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.list}" var="product">
                        <tr>
                            <td>${product.productID}</td>
                            <td>${product.product_name}</td>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                            <td>${product.year}</td>
                            <td>${product.product_description}</td>
                            <td>${product.featured}</td>
                            <td>${product.thumbnail}</td>
                            <td>${product.brief_information}</td>
                            <td>${product.original_price}</td>
                            <td>${product.sale_price}</td>
                            <td>${product.categoryProduct.category_name}</td>
                            <td>${product.categoryProduct.category_description}</td>
                            <td>
                                <a href="hide" title="Hide">
                                    <i class="fas fa-eye-slash"></i>
                                </a>
                                <a href="show" title="Show">
                                    <i class="fas fa-eye"></i>
                                </a>
                                <a href="view" title="View">
                                    <i class="fas fa-search"></i> 
                                </a>
                                <a href="edit" title="Edit">
                                    <i class="fas fa-edit"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="products?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>
            <a href="addProduct.jsp" class="button">Add New Product</a>
        </div>
    </body>
</html>

