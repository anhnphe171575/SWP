<%-- 
    Document   : viewProduct
    Created on : May 24, 2024, 12:00:21 AM
    Author     : MANH VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <link rel="stylesheet" href="vncss/vn2.css">
    </head>
    <body>
        <div class="container">
            <div class="product-details">
                <h2 class="title">Product Detail</h2>
                <p class="detail">Product ID: ${product.productID}</p>
                <p class="detail">Product Name: ${product.product_name}</p>
                <p class="detail">Price: ${product.price}</p>
                <p class="detail">Quantity: ${product.quantity}</p>
                <p class="detail">Year: ${product.year}</p>
              
                <p class="detail">Product Description: ${product.product_description}</p>
                <p class="detail">Featured: ${product.featured}</p>
                <img src="${product.thumbnail}" alt="Thumbnail" class="thumbnail">
                <p class="detail">Brief Information: ${product.brief_information}</p>
                <p class="detail">Original Price: ${product.original_price}</p>
                <p class="detail">Sale Price: ${product.sale_price}</p>
                <p class="detail">Update Date: ${product.update_date}</p>
                <p class="detail">Brand: ${product.brand}</p>
                <p class="detail">Status: ${product.status ? "Show" : "Hide"}</p>
            </div>
        </div>
    </body>
</html>


