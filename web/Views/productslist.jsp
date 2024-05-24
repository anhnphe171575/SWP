<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product List</title>
        <link rel="stylesheet" href="vncss/vn1.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Product List</h1>
            <div class="add-button-container">
                <a href="addp" class="button">Add New Product</a>
            </div>
            <c:if test="${not empty msg}">
                <div style="color: red">${msg}</div>
            </c:if>
            <c:if test="${not empty msgUpdate}">
                <div style="color: red">${msgUpdate}</div>
            </c:if>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Thumbnail</th>
                        <th>Price</th>
                        <th>Sale Price</th>
                        <th>Featured</th>
                        <th>Status</th>
                        <th class="actions">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="product">
                        <tr>
                            <td>${product.productID}</td>
                            <td>${product.product_name}</td>
                            <td><img src="${product.thumbnail}" class="img"></td>
                            <td>${product.original_price}</td>
                            <td>${product.sale_price}</td>
                            <td>
                                <c:if test="${product.featured == 1}">Yes</c:if>
                                <c:if test="${product.featured == 0}">No</c:if>  
                                </td>
                                <td>
                                <c:if test="${product.status == true}">Show</c:if>
                                <c:if test="${product.status == false}">Hide</c:if>     
                                </td>
                                <td class="actions">
                                    <a title="Hide" onclick="location.href = 'update?action=hide&id=${product.productID}'"><i
                                        class="fas fa-eye-slash"></i></a>
                                <a title="Show" onclick="location.href = 'update?action=show&id=${product.productID}'"><i
                                        class="fas fa-eye"></i></a>
                                <a title="View" onclick="location.href = 'view?vid=${product.productID}'"><i
                                        class="fas fa-search"></i></a>
                                <a title="Edit" onclick="location.href = 'editp?eid=${product.productID}'"><i
                                        class="fas fa-edit"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
</html>


