<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Slider</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <style>
            .container {
                max-width: 600px;
                margin-top: 50px;
                background: #fff;
                padding: 30px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            h2 {
                margin-bottom: 20px;
            }
            .form-group label {
                font-weight: bold;
            }
            .btn-primary {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 10px;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Edit Slider</h2>
            <form action="SliderServletURL" method="post" id="editSliderForm" enctype="multipart/form-data">
                <input type="hidden" name="sliderID" value="${Slider.sliderID}">

                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" id="title" name="title" value="${Slider.title}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="image">Image:</label>
                    <img class="" width="300px" src="${Slider.image}"> <br/>
                        <input type="file" name="file" id="file" accept="image/*" > 
                        
                    <input type="hidden" name="existingImage" value="${Slider.image}">
                </div>

                <div class="form-group">
                    <label for="link">Link:</label>
                    <input type="text" id="link" name="link" value="${Slider.link}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="notes">Notes:</label>
                    <input type="text" id="notes" name="notes" value="${Slider.notes}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="1" ${Slider.status == 1 ? 'selected' : ''}>Show</option>
                        <option value="0" ${Slider.status != 1 ? 'selected' : ''}>Hide</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="page_order">Page Order:</label>
                    <input type="number" id="page_order" name="page_order" value="${Slider.page_order}" class="form-control" required>
                </div>

                <input type="submit" value="Submit" name="submit" class="btn btn-primary">
                <input type="hidden" value="updateSlider" name="service">
                ${error}
            </form>
        </div>
        <!-- Bootstrap JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
