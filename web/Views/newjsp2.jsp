<%-- 
    Document   : newjsp2
    Created on : Jun 1, 2024, 2:10:56 PM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Star Rating</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
    font-family: Arial, sans-serif;
}

.star-rating {
    direction: rtl;
    display: inline-block;
    font-size: 0;
}

.star-rating input {
    display: none;
}

.star-rating label {
    font-size: 2em;
    padding: 0.1em;
    cursor: pointer;
    color: #ddd;
    text-shadow: 1px 1px 0 #a2a2a2;
}

.star-rating input:checked ~ label {
    color: #f5b301;
    text-shadow: 1px 1px 0 #b66d00;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
    color: #f5b301;
    text-shadow: 1px 1px 0 #b66d00;
}
    </style>
</head>
<body>
    <div class="star-rating">
        <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 stars">☆</label>
        <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 stars">☆</label>
        <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 stars">☆</label>
        <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 stars">☆</label>
        <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star">☆</label>
    </div>
</body>
</html>