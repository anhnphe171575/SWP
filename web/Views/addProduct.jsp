<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vn">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
        <link rel="stylesheet" href="vncss/vn3.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <script>
            $(document).ready(function () {
                $('#productName').on('blur', function () {
                    var productName = $(this).val();
                    $.ajax({
                        url: 'checkproductname',
                        method: 'POST',
                        data: {productName: productName},
                        success: function (response) {
                            if (response.exists) {
                                $('#productNameError').text('Product name already exists.');
                            } else {
                                $('#productNameError').text('');
                            }
                        }
                    });
                });
            });
        </script>
    </head>
    <body style="background-color:lightblue ">
        <div class="container" >
            <h1>Thêm sản phẩm</h1>      
            <form action="addp" method="post" enctype="multipart/form-data">
                <div style="color: red">${requestScope.msg}</div>
                <div class="form-group">
                    <div class="label">Tên sản phẩm:</div>
                    <input type="text" id="productName" name="productName" required>
                    <div id="productNameError" style="color: red;"></div>
                </div>

                <div class="form-group">
<div class="label">Thể loại:</div>
                    <select name="categoryID" class="form-control" required>
                        <c:forEach var="category" items="${category}">
                            <option value="${category.category_productID}" ${category.category_productID == product.categoryProduct.category_productID ? 'selected' : ''}>
                                ${category.category_name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="label">Năm:</div>
                    <select name="year" class="form-control" required>
                        <c:forEach var="i" begin="2000" end="2024">
                            <option value="${i}" ${product.year == i ? 'selected' : ''}>${i}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <div class="label">Số lượng:</div>
                    <input type="number" name="quantity" value="0" readonly required>
                </div>

                <div class="form-group">
                    <div class="label">Đang xử lý:</div>
                    <input type="number" name="hold" value="0" readonly required>
                </div>

                <div class="form-group">
                    <div class="label">Mô tả:</div>
                    <textarea name="description" rows="4" cols="50" required></textarea>
                </div>               

                <div class="form-group">
                    <div class="label">Thông tin tóm tắt:</div>
                    <input type="text" name="briefInfo" required>
                </div>

                <div class="form-group">
                    <div class="label">Giá gốc:</div>
                    <input type="number" name="originalPrice" value ="0" readonly required>
                </div>

                <div class="form-group">
                    <div class="label">Giá giảm:</div>
                    <input type="number" name="salePrice" value ="0" readonly required>
                </div>

                <div class="form-group">
                    <label>Hình ảnh</label><br>
                    <input type="file" name="file" id="file" style="margin-bottom: 10px" required="">
                    <input type="hidden" name="existingImage" >
                </div>

                <div class="form-group">
                    <div class = "label">Thương hiệu:</div>
                    <select name="brand" class="form-control" required>
                        <c:forEach var="brand" items="${brand}">
                            <option value="${brand}" ${brand == product.brand ? 'selected' : ''}>${brand}</option>
                        </c:forEach>
                    </select>
                </div>
<div class="form-group">
                    <div class="label">Nổi bật:</div>
                    <select class="form-control" name="featured" required>
                        <option value="0">Có</option>
                        <option value="1">Không</option>
                    </select>
                </div>

                <div class="form-group">
                    <div class="label">Trạng thái:</div>
                    <select name="status" class="form-control" required>
                        <option value="true" ${product.status ? 'selected' : ''}>Hiện</option>
                        <option value="false" ${!product.status ? 'selected' : ''}>Ẩn</option>
                    </select>
                </div>

                <div class="form-group">
                    <input type="submit" value="Submit">
                    <button style="margin-top: 10px; color: white; background-color: whitesmoke; border: 0px"><a href="productslist">Back To Products List</a></button>
                </div>
            </form>
        </div>
    </body>
</html>
