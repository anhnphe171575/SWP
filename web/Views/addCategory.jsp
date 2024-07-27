<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm loại sản phẩm</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body style="background-color:white">
    <div class="container mt-5">
        <h1>Thêm loại sản phẩm</h1>
        <form id="sliderForm" action="CategoryServletURL" method="post" enctype="multipart/form-data" novalidate>
            <div class="form-group">
                <label for="category_name">Loại sản phẩm</label>
                <input type="text" name="category_name" id="category_name" class="form-control" required maxlength="50">
                <small class="form-text text-muted">Maximum 50 characters</small>
            </div>
            <div class="form-group">
                <label for="file">Ảnh</label>
                <input type="file" id="file" name="file" class="form-control-file" required accept="image/*">
            </div>
            
            <div class="form-group">
                <label for="category_description">Thông tin loại sản phẩm</label>
                <input type="text" name="category_description" id="category_description" class="form-control" required maxlength="100">
                <small class="form-text text-muted">Maximum 100 characters</small>
            </div>
            
            <div class="form-group">
                <input type="hidden" name="service" value="addCategory">
                <input type="submit" name="submit" id="submitBtn" class="btn btn-primary" value="Thêm">
                <a href="CategoryServletURL" class="btn btn-secondary">Quay lại danh sách loại sản phẩm</a>
            </div>
        </form>
    </div>

    <script>
    $(document).ready(function() {
        function validateField(field, maxLength) {
            if (field.val().length > maxLength) {
                field.addClass('is-invalid');
                field.next('.invalid-feedback').remove();
                field.after('<div class="invalid-feedback">Maximum ' + maxLength + ' characters allowed</div>');
                return false;
            } else {
                field.removeClass('is-invalid');
                field.next('.invalid-feedback').remove();
                return true;
            }
        }

        function checkDuplicateCategoryName(categoryName, callback) {
            $.ajax({
                url: 'CategoryServletURL?service=checkDuplicateCategory',
                type: 'POST',
                data: { category_name: categoryName },
                success: function(response) {
                    callback(response.isDuplicate);
                },
                error: function() {
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi',
                        text: 'Đã xảy ra lỗi khi kiểm tra tên loại sản phẩm.',
                        confirmButtonText: 'OK'
                    });
                    callback(false);
                }
            });
        }

        $('#category_name, #category_description').on('input', function() {
            var maxLength = $(this).attr('maxlength');
            validateField($(this), maxLength);
        });

        $('#sliderForm').submit(function(e) {
            e.preventDefault();

            // Validate form
            if (!this.checkValidity()) {
                e.stopPropagation();
                $(this).addClass('was-validated');
                return;
            }

            // Additional character limit validations
            var categoryNameValid = validateField($('#category_name'), 50);
            var categoryDescriptionValid = validateField($('#category_description'), 100);

            if (!categoryNameValid || !categoryDescriptionValid) {
                return;
            }
            
            var categoryName = $('#category_name').val();

            // Check for duplicate category name
            checkDuplicateCategoryName(categoryName, function(isDuplicate) {
                if (isDuplicate) {
                    $('#category_name').addClass('is-invalid');
                    $('#category_name').after('<div class="invalid-feedback">Tên loại sản phẩm đã tồn tại.</div>');
                    return;
                }

                // Clear previous error message if exists
                $('#category_name').removeClass('is-invalid');
                $('#category_name').next('.invalid-feedback').remove();

                var formData = new FormData($('#sliderForm')[0]);
                formData.append("submit", $("#submitBtn").val()); // Append submit button's value

                $.ajax({
                    url: 'CategoryServletURL',
                    type: 'POST',
                    data: formData,
                    success: function(response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: 'Loại sản phẩm đã được thêm thành công!',
                            confirmButtonText: 'OK'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                // Redirect to slider list page
                                window.location.href = 'CategoryServletURL';
                            }
                        });
                    },
                    error: function() {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Đã xảy ra lỗi khi thêm loại sản phẩm!',
                            confirmButtonText: 'OK'
                        });
                    },
                    cache: false,
                    contentType: false,
                    processData: false
                });
            });
        });
    });
</script>

</body>
</html>
