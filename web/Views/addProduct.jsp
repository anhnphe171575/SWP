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
    // Product name validation
    $('#productName').on('blur', function () {
        var productName = $(this).val();
        if (productName.length > 255) {
            $('#productNameError').text('Product name must not exceed 255 characters.');
            return;
        }
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

    // Description character limit
    $('#description').on('input', function() {
        var maxLength = 1000;
        var currentLength = $(this).val().length;
        if(currentLength > maxLength) {
            $(this).val($(this).val().substring(0, maxLength));
        }
        $('#descriptionCharCount').text(maxLength - currentLength + ' characters remaining');
    });

    // Brief Info character limit
    $('#briefInfo').on('input', function() {
        var maxLength = 255;
        var currentLength = $(this).val().length;
        if(currentLength > maxLength) {
            $(this).val($(this).val().substring(0, maxLength));
        }
        $('#briefInfoCharCount').text(maxLength - currentLength + ' characters remaining');
    });

    // File type and size check
    $('#file').on('change', function() {
        var file = this.files[0];
        var fileType = file.type;
        var fileSize = file.size;
        var maxSize = 5 * 1024 * 1024; // 5MB

        if (!fileType.startsWith('image/')) {
            alert('Please select an image file.');
            this.value = '';
        } else if (fileSize > maxSize) {
            alert('File size must be less than 5MB.');
            this.value = '';
        }
    });

    // Year validation
    $('select[name="year"]').on('change', function() {
        var selectedYear = parseInt($(this).val());
        var currentYear = new Date().getFullYear();
        if (selectedYear < 2000 || selectedYear > currentYear) {
            alert('Please select a valid year between 2000 and ' + currentYear);
            $(this).val(''); // Reset to default
        }
    });

    // Form submission validation
    $('form').on('submit', function(e) {
        var isValid = true;

        // Check if all required fields are filled
        $(this).find('[required]').each(function() {
            if ($(this).val() === '') {
                isValid = false;
                $(this).addClass('error');
            } else {
                $(this).removeClass('error');
            }
        });

        // Specific validations
        var productName = $('#productName').val();
        if (productName.length > 255) {
            isValid = false;
            $('#productNameError').text('Product name must not exceed 255 characters.');
        }

        var description = $('textarea[name="description"]').val();
        if (description.length > 1000) {
            isValid = false;
            alert('Description must not exceed 1000 characters.');
        }

        var briefInfo = $('input[name="briefInfo"]').val();
        if (briefInfo.length > 255) {
            isValid = false;
            alert('Brief info must not exceed 255 characters.');
        }

        // File validation
        var fileInput = $('#file')[0];
        if (fileInput.files.length > 0) {
            var file = fileInput.files[0];
            var fileType = file.type;
            var fileSize = file.size;
            var maxSize = 5 * 1024 * 1024; // 5MB

            if (!fileType.startsWith('image/')) {
                isValid = false;
                alert('Please select an image file.');
            } else if (fileSize > maxSize) {
                isValid = false;
                alert('File size must be less than 5MB.');
            }
        }

        if (!isValid) {
            e.preventDefault();
            alert('Please correct the errors before submitting.');
        }
    });

    // Add visual feedback for required fields
    $('[required]').on('input', function() {
        if ($(this).val() !== '') {
            $(this).removeClass('error');
        } else {
            $(this).addClass('error');
        }
    });

    // Initialize character counters
    $('#description').trigger('input');
    $('#briefInfo').trigger('input');
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
