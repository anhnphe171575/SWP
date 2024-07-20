<%-- 
    Document   : DBpost
    Created on : Jul 2, 2024, 3:08:59 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Simple Line Chart Example</title>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Admin Dashboard</title>

        <!-- Montserrat Font -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="./mktcss/styles.css">

        <style>
            /* General styling for the body */
            .material-icons-outlined {
                vertical-align: middle;
                line-height: 1px;
                font-size: 35px;
            }
            .grid-container {
                display: grid;

                height: 100vh;
                width: 100%;
            }

            /* Styling for the dropdown container */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            /* Styling for the dropdown button */
            .dropdown-button {
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .dropdown-button:hover {
                background-color: #0056b3;
            }
            /* CSS ?? ch?nh kích th??c ban ??u c?a ?nh và phóng to */
            .zoom-img-container {
                position: relative;
                display: inline-block;
                cursor: pointer; /* Con tr? chu?t khi rê vào ?nh */
            }

            .zoom-img {
                max-width: 100%; /* Gi?i h?n chi?u r?ng t?i ?a c?a ?nh */
                transition: transform 0.2s ease-in-out; /* Hi?u ?ng khi phóng to */
            }

            .zoom-img.active {
                position: fixed; /* ?nh phóng to hi?n th? trên c? màn hình */
                top: 0;
                left: 0;
                width: 100%; /* Chi?u r?ng c?a ?nh phóng to là 100% */
                height: 100%; /* Chi?u cao c?a ?nh phóng to là 100% */
                background-color: rgba(0, 0, 0, 0.8); /* Màu n?n c?a ?nh phóng to */
                z-index: 9999; /* ??m b?o ?nh phóng to hi?n th? trên các ph?n t? khác */
            }

            .zoomed-img {
                display: block; /* Hi?n th? ?nh phóng to */
                margin: auto; /* Canh gi?a ?nh */
                max-width: 90%; /* Gi?i h?n chi?u r?ng t?i ?a c?a ?nh phóng to */
                max-height: 90%; /* Gi?i h?n chi?u cao t?i ?a c?a ?nh phóng to */
            }

            .close-btn {
                position: absolute;
                top: 20px;
                right: 20px;
                color: #fff;
                font-size: 24px;
                cursor: pointer;
            }
        </style>

    </head>
    <body>
        <div class="grid-container">

            <jsp:include page="header.jsp"></jsp:include>

                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>
                <main class="main-container" style="color: #000">
                    <div class="select">
                        <h2>THỐNG KÊ</h2>
                    <%
// Retrieve the month and year from the request, if available
String monthParam = request.getParameter("month");
String yearParam = request.getParameter("year");

// Get the current month and year
int currentMonth = LocalDate.now().getMonthValue();
int currentYear = LocalDate.now().getYear();

// Set the month and year to display
int displayMonth = (monthParam != null && !monthParam.isEmpty()) ? Integer.parseInt(monthParam) : currentMonth;
int displayYear = (yearParam != null && !yearParam.isEmpty()) ? Integer.parseInt(yearParam) : currentYear;
                    %>

                    <form action="DBpost" method="post">
                        <label for="month">Tháng:</label>
                        <input type="number" id="month" name="month" min="1" max="12" value="<%= displayMonth %>" required>
                        <label for="year">Năm:</label>
                        <input type="number" id="year" name="year" min="2000" max="2100" value="<%= displayYear %>" required>
                        <input type="hidden" name="service" value="select">
                        <input type="submit">
                    </form>
                    

                </div>
                <div id="line-chart" style="width: 100%; height: 500px;margin-top: 10px"></div>
                <div class="zoom-img-container">
                    <!-- Th? <img> ?? hi?n th? ?nh -->
                    <img src="https://cdn0.fahasa.com/media/catalog/product/d/r/dragon-ball-full-color---phan-bon---frieza-dai-de-_-tap-2.jpg" class="zoom-img" onclick="zoomImage(this)">
                    <!-- D?u X ?? ?óng ?nh -->
                    <span class="close-btn" onclick="zoomImage(this.parentNode.querySelector('.zoom-img'))">&times;</span>
                </div>
            </main>
        </div>
        <script>
            function zoomImage(img) {
                img.classList.toggle('active'); // Toggle the 'active' class to zoom the image

                // Prevent or allow page scrolling when the image is zoomed
                if (img.classList.contains('active')) {
                    document.body.style.overflow = 'hidden';
                } else {
                    document.body.style.overflow = '';
                }
            }

            var post = [];
            var date = [];

            <c:forEach items="${post}" var="entry">
            date.push('${entry.key}');
            post.push(<c:out value="${entry.value}"/>);
            </c:forEach>
            document.addEventListener('DOMContentLoaded', function () {
                const lineChartOptions = {
                    series: [{
                            name: "BÀI",
                            data: post
                        }],
                    chart: {
                        height: 350,
                        type: 'line',
                        zoom: {
                            enabled: false
                        }
                    },
                    dataLabels: {
                        enabled: false
                    },
                    stroke: {
                        curve: 'smooth'
                    },
                    title: {
                        text: 'Bài viết mới theo từng ngày',
                        align: 'left'
                    },
                    grid: {
                        row: {
                            colors: ['#f3f3f3', 'transparent'], // Alternating row colors
                            opacity: 0.5
                        },
                    },
                    xaxis: {
                        categories: date,
                    },
                    yaxis: {
                        title: {
                            text: 'SỐ LƯỢNG'
                        },
                        min: 0, // Minimum value of the y-axis
                        forceNiceScale: true, // Ensures the y-axis scales nicely to the data range
                        labels: {
                            formatter: function (value) {
                                return Math.floor(value); // Formats the y-axis labels as integers
                            }
                        }
                    }
                };

                const lineChart = new ApexCharts(document.querySelector("#line-chart"), lineChartOptions);
                lineChart.render();
            });
        </script>

        <!-- Add this div where you want the chart to be displayed -->
        <div id="line-chart"></div>

    </body>
</html>
