<%-- 
    Document   : editpost
    Created on : May 26, 2024, 10:38:41 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link rel="stylesheet" href="vncss/vn5.css"/>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy ngày hiện tại
                var today = new Date().toISOString().split('T')[0];
                // Thiết lập giá trị tối đa cho trường ngày
                document.getElementById("updateDate").setAttribute("max", today);

                // Kiểm tra giá trị của Original Price và Sale Price
                document.getElementById("editProductForm").addEventListener("submit", function (event) {
                    var originalPrice = parseFloat(document.getElementById("originalPrice").value);
                    var salePrice = parseFloat(document.getElementById("salePrice").value);
                    var featured = parseInt(document.getElementById("featured").value);

                    if (originalPrice <= salePrice) {
                        event.preventDefault();
                        alert("Original Price must be greater than Sale Price.");
                    }

                    if (featured !== 0 && featured !== 1) {
                        event.preventDefault();
                        alert("Featured must be 0 or 1.");
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <h2>Edit Slider</h2>
            <form action="SliderServletURL" >
               
                <input type="hidden" name="sliderID" value="${Slider.sliderID}">
                <div class="form-group">
                      
                    <div>Title:</div>
                    <input type="text" name="title" value="${Slider.title}" >
                </div>

                <div class="form-group">
                    <div>Image:</div>
                    <input type="text" name="image" value="${Slider.image}">
                </div>

                <div class="form-group">
                    <div>Link</div>
                    <input type="text"  name="link" value="${Slider.link}">
                </div>
                <div class="form-group">
                    <div>Notes:</div>
                     <input type="text"  name="notes" value="${Slider.notes}">
                </div>

                <div class="form-group">
                    <div>Status:</div>
                    <select name="status" required> 
                        <option value="1" ${Slider.status == 1 ? 'selected' : ''}>Show</option>
                        <option value="0" ${Slider.status != 1 ? 'selected' : ''}>Hide</option>
                    </select>

                </div>               


                <div class="form-group">
                    <div>Page Order</div>
                    <input type="number" name="page_order" value="${Slider.page_order}">
                </div>

                      

               

                <input type="submit" value="Submit" name="submit">
                <input type="hidden" value="updateSlider" name="service">
                ${error}
            </form>
        </div>
    </body>
</html>

