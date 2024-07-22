<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Slider</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color:white">
    <div class="container mt-5">
        <h1>Add Slider</h1>
        <form id="sliderForm" action="SliderServletURL" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="file">Image:</label>
                <input type="file" id="file" name="file" class="form-control-file" required accept="image/*">
            </div>
            <div class="form-group">
                <label for="link">Link:</label>
                <input type="url" name="link" id="link" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <input type="text" name="notes" id="notes" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="page_order">Page Order:</label>
                <input type="number" name="page_order" id="page_order" class="form-control" required min="1">
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status" class="form-control" required>
                    <option value="1">Show</option>
                    <option value="0">Hide</option>
                </select>
            </div>
            <div class="form-group">
                <input type="hidden" name="service" value="addSlider">
                <input type="submit" name="submit" class="btn btn-primary" value="Add Slider">
                <a href="SliderServletURL" class="btn btn-secondary">Back To Slider List</a>
            </div>
        </form>
    </div>

    <script>
        document.getElementById('sliderForm').addEventListener('submit', function(event) {
            var title = document.getElementById('title').value.trim();
            var file = document.getElementById('file').value;
            var link = document.getElementById('link').value.trim();
            var notes = document.getElementById('notes').value.trim();
            var pageOrder = document.getElementById('page_order').value;

            if (title === '') {
                alert('Please enter a title');
                event.preventDefault();
                return;
            }

            if (file === '') {
                alert('Please select an image file');
                event.preventDefault();
                return;
            }

            if (link === '' || !isValidURL(link)) {
                alert('Please enter a valid URL for the link');
                event.preventDefault();
                return;
            }

            if (notes === '') {
                alert('Please enter notes');
                event.preventDefault();
                return;
            }

            if (pageOrder === '' || parseInt(pageOrder) < 1) {
                alert('Please enter a valid page order (minimum 1)');
                event.preventDefault();
                return;
            }
        });

        function isValidURL(string) {
            try {
                new URL(string);
                return true;
            } catch (_) {
                return false;
            }
        }
    </script>
</body>
</html>