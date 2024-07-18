<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="./qcss/style.css">
        <link rel="stylesheet" href="./mktcss/styles.css">
        <title>Feedback Detail</title>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
            }
            .container {
                max-width: 800px;
                margin: auto;
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .feedback {
                margin-bottom: 20px;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #fafafa;
            }
            .feedback-header {
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 15px;
                color: #333;
            }
            .feedback-image {
                text-align: center;
                margin-bottom: 20px;
            }
            .feedback-image img {
                max-width: 100%;
                max-height: 300px;
                border-radius: 8px;
                box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);
            }
            .feedback-details {
                margin-bottom: 15px;
            }
            .feedback-details label {
                font-weight: bold;
                color: #555;
            }
            .feedback-details p {
                margin: 5px 0;
                color: #666;
            }
            .back-link {
                margin-top: 20px;
                text-align: center;
            }
            .back-link a {
                text-decoration: none;
                color: #4CAF50;
                font-size: 16px;
            }
            .back-link a:hover {
                text-decoration: underline;
            }
            .status-link {
                font-size: 18px;
                color: #007bff;
                cursor: pointer;
                text-decoration: none;
            }
            .status-link:hover {
                text-decoration: underline;
            }
            .container{
                margin: 10px 0px 10px 350px;
            }
        </style>
    </head>
    <body>
        <div class="grid-container">
            <jsp:include page="header.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="container">
                    <div class="feedback">
                        <div class="feedback-header">Customer Feedback</div>
                        <div class="feedback-image">
                            <img src="${feedback.image}" alt="Feedback Image">
                    </div>
                    <div class="feedback-details">
                        <label>Name:</label>
                        <h2>${feedback.customer.first_name} ${feedback.customer.last_name}</h2>
                    </div>
                    <div class="feedback-details">
                        <label>Email:</label>
                        <p>${feedback.customer.email}</p>
                    </div>
                    <div class="feedback-details">
                        <label>Phone:</label>
                        <p>${feedback.customer.phone}</p>
                    </div>
                    <div class="feedback-details">
                        <label>Rate Star:</label>
                        <p>${feedback.rate_star}</p>
                    </div>
                    <div class="feedback-details">
                        <label>Feedback:</label>
                        <p>${feedback.comment}</p>
                    </div>
                    <div class="feedback-details">
                        <label>Status</label><br/>
                        <c:if test="${feedback.status == '1'}">
                            Hide: <a href="StatusFeedBack?FID=${feedback.feedbackID}&status=0&service=detail" class="status-link fa fa-eye"></a>
                        </c:if>
                        <c:if test="${feedback.status != '1'}">
                            Show: <a href="StatusFeedBack?FID=${feedback.feedbackID}&status=1&service=detail" class="status-link fa fa-eye-slash"></a>
                        </c:if>
                    </div>
                </div>
                <div class="back-link">
                    <a href="FeedBackList">Back to Feedback List</a>
                </div>
            </div>
        </div>
    </body>
</html>
