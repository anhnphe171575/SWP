<%-- 
    Document   : MKTDashboard
    Created on : May 31, 2024, 10:48:25 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
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

        </style>
    </head>

    <body>
        <div class="grid-container">

            <!-- Header -->
            <jsp:include page="header.jsp"></jsp:include>

                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>
                <!-- End Sidebar -->

                <!-- Main -->
                <main class="main-container">
                    <div class="main-title">
                        <h2>DASHBOARD</h2>
                    </div>

                    <div class="main-cards">

                        <div class="card">
                            <div class="card-inner">
                                <h3>PRODUCTS</h3>
                                <span class="material-icons-outlined">inventory_2</span>
                            </div>
                            <h1>249</h1>
                        </div>

                        <div class="card">
                            <div class="card-inner">
                                <h3>POST</h3>
                                <span class="material-icons-outlined">article</span>
                            </div>
                            <h1>25</h1>
                        </div>

                        <div class="card">
                            <div class="card-inner">
                                <h3>CUSTOMERS</h3>
                                <span class="material-icons-outlined">groups</span>
                            </div>
                            <h1>1500</h1>
                        </div>
                        <div class="card">
                            <div class="card-inner">
                                <h3>FEEDBACK</h3>
                                <span class="material-icons-outlined">comment</span>
                            </div>
                            <h1>56</h1>
                        </div>

                    </div>
                    <p style="color: black">Select date:</p>
                    <form action="MKTDashboard">
                        <input type="date" name="dateinput" id="pastDatePicker" class="date-input" max="${date}"  >

                    <button type="submit" value="ok" name="submit" class="submit-button">Submit</button>
                    <input type="hidden" name="service" value="date">

                </form>
                <div class="charts">
                    <div class="charts-card">
                        <h2 class="chart-title">Products In 7-Day Before</h2>
                        <div id="bar-chart1"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Post In 7-Day Before</h2>
                        <div id="bar-chart2"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Customer In 7-Day Before</h2>
                        <div id="bar-chart3"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Feedback In 7-Day Before</h2>
                        <div id="bar-chart4"></div>
                    </div>
                    <!--          <div class="charts-card">
                                <h2 class="chart-title">Purchase and Sales Orders</h2>
                                <div id="area-chart"></div>
                              </div>-->
                </div>
            </main>
            <!-- End Main -->

        </div>

        <!-- Scripts -->
        <!-- ApexCharts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.5/apexcharts.min.js"></script>
        <script>
            let sidebarOpen = false;
            const sidebar = document.getElementById('sidebar');
            function openSidebar() {
                if (!sidebarOpen) {
                    sidebar.classList.add('sidebar-responsive');
                    sidebarOpen = true;
                }
            }

            function closeSidebar() {
                if (sidebarOpen) {
                    sidebar.classList.remove('sidebar-responsive');
                    sidebarOpen = false;
                }
            }
            function searchFunction() {
                // Code to handle search icon click
                console.log("Search function triggered");
            }

// ---------- CHARTS ----------
            var datapro = [];
            var datapost = [];
            var datacus = [];
            var datafeed = [];
            var product = [];
            var post = [];
            var cus = [];
            var feedback = [];

            // Using JSP to populate the JavaScript arrays
            <c:forEach items="${dataProduct}" var="entry">
            datapro.push(${entry.value});
            product.push('<c:out value="${entry.key}"/>');
            </c:forEach>
            <c:forEach items="${dataPost}" var="entry">
            datapost.push(${entry.value});
            post.push('<c:out value="${entry.key}"/>');
            </c:forEach>
            <c:forEach items="${dataCus}" var="entry">
            datacus.push(${entry.value});
            cus.push('<c:out value="${entry.key}"/>');
            </c:forEach>
            <c:forEach items="${dataFeedback}" var="entry">
            datafeed.push(${entry.value});
            feedback.push('<c:out value="${entry.key}"/>');
            </c:forEach>
// BAR CHART
            const barChartOptions1 = {
                series: [
                    {
                        name: 'Product',
                        data: datapro
                    },
                ],
                chart: {
                    type: 'bar',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'],
                plotOptions: {
                    bar: {
                        distributed: true,
                        borderRadius: 4,
                        horizontal: false,
                        columnWidth: '40%',
                    },
                },
                dataLabels: {
                    enabled: false,
                },
                fill: {
                    opacity: 1,
                },
                grid: {
                    borderColor: '#55596e',
                    yaxis: {
                        lines: {
                            show: true,
                        },
                    },
                    xaxis: {
                        lines: {
                            show: true,
                        },
                    },
                },
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                stroke: {
                    colors: ['transparent'],
                    show: true,
                    width: 2,
                },
                tooltip: {
                    shared: true,
                    intersect: false,
                    theme: 'dark',
                },
                xaxis: {
                    categories: product,
                    title: {
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        show: true,
                        color: '#55596e',
                    },
                    axisTicks: {
                        show: true,
                        color: '#55596e',
                    },
                    labels: {
                        style: {
                            colors: '#f5f7ff',
                        },
                    },
                },
                yaxis: {
                    title: {
                        text: 'Count',
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        color: '#55596e',
                        show: true,
                    },
                    axisTicks: {
                        color: '#55596e',
                        show: true,
                    },
                    labels: {
                        formatter: function (value) {
                            return Math.round(value); // Đảm bảo giá trị hiển thị là số nguyên
                        },
                        style: {
                            colors: '#f5f7ff'
                        }
                    },
                    min: 0, // Giá trị nhỏ nhất của y-axis
                    max: Math.max(...datapro) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...datapro) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };

            const barChart1 = new ApexCharts(
                    document.querySelector('#bar-chart1'),
                    barChartOptions1
                    );
            barChart1.render();
// BAR CHAR 2
            const barChartOptions2 = {
                series: [
                    {
                        data: datapost,
                        name: 'Post',
                    },
                ],
                chart: {
                    type: 'bar',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'],
                plotOptions: {
                    bar: {
                        distributed: true,
                        borderRadius: 4,
                        horizontal: false,
                        columnWidth: '40%',
                    },
                },
                dataLabels: {
                    enabled: false,
                },
                fill: {
                    opacity: 1,
                },
                grid: {
                    borderColor: '#55596e',
                    yaxis: {
                        lines: {
                            show: true,
                        },
                    },
                    xaxis: {
                        lines: {
                            show: true,
                        },
                    },
                },
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                stroke: {
                    colors: ['transparent'],
                    show: true,
                    width: 2,
                },
                tooltip: {
                    shared: true,
                    intersect: false,
                    theme: 'dark',
                },
                xaxis: {
                    categories: post,
                    title: {
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        show: true,
                        color: '#55596e',
                    },
                    axisTicks: {
                        show: true,
                        color: '#55596e',
                    },
                    labels: {
                        style: {
                            colors: '#f5f7ff',
                        },
                    },
                },
                yaxis: {
                    title: {
                        text: 'Count',
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        color: '#55596e',
                        show: true,
                    },
                    axisTicks: {
                        color: '#55596e',
                        show: true,
                    },
                    labels: {
                        formatter: function (value) {
                            return Math.round(value); // Đảm bảo giá trị hiển thị là số nguyên
                        },
                        style: {
                            colors: '#f5f7ff'
                        }
                    },
                    min: 0, // Giá trị nhỏ nhất của y-axis
                    max: Math.max(...datapost) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...datapost) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };

            const barChart2 = new ApexCharts(
                    document.querySelector('#bar-chart2'),
                    barChartOptions2
                    );
            barChart2.render();

// BAR CHAR 3
            const barChartOptions3 = {
                series: [
                    {
                        data: datacus,
                        name: 'Actived',
                    },
                ],
                chart: {
                    type: 'bar',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'],
                plotOptions: {
                    bar: {
                        distributed: true,
                        borderRadius: 4,
                        horizontal: false,
                        columnWidth: '40%',
                    },
                },
                dataLabels: {
                    enabled: false,
                },
                fill: {
                    opacity: 1,
                },
                grid: {
                    borderColor: '#55596e',
                    yaxis: {
                        lines: {
                            show: true,
                        },
                    },
                    xaxis: {
                        lines: {
                            show: true,
                        },
                    },
                },
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                stroke: {
                    colors: ['transparent'],
                    show: true,
                    width: 2,
                },
                tooltip: {
                    shared: true,
                    intersect: false,
                    theme: 'dark',
                },
                xaxis: {
                    categories: cus,
                    title: {
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        show: true,
                        color: '#55596e',
                    },
                    axisTicks: {
                        show: true,
                        color: '#55596e',
                    },
                    labels: {
                        style: {
                            colors: '#f5f7ff',
                        },
                    },
                },
                yaxis: {
                    title: {
                        text: 'Count',
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        color: '#55596e',
                        show: true,
                    },
                    axisTicks: {
                        color: '#55596e',
                        show: true,
                    },
                    labels: {
                        formatter: function (value) {
                            return Math.round(value); // Đảm bảo giá trị hiển thị là số nguyên
                        },
                        style: {
                            colors: '#f5f7ff'
                        }
                    },
                    min: 0, // Giá trị nhỏ nhất của y-axis
                    max: Math.max(...datacus) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...datacus) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };

            const barChart3 = new ApexCharts(
                    document.querySelector('#bar-chart3'),
                    barChartOptions3
                    );
            barChart3.render();

            const barChartOptions4 = {
                series: [
                    {
                        data: datafeed,
                        name: 'Feedback',
                    },
                ],
                chart: {
                    type: 'bar',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'],
                plotOptions: {
                    bar: {
                        distributed: true,
                        borderRadius: 4,
                        horizontal: false,
                        columnWidth: '40%',
                    },
                },
                dataLabels: {
                    enabled: false,
                },
                fill: {
                    opacity: 1,
                },
                grid: {
                    borderColor: '#55596e',
                    yaxis: {
                        lines: {
                            show: true,
                        },
                    },
                    xaxis: {
                        lines: {
                            show: true,
                        },
                    },
                },
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                stroke: {
                    colors: ['transparent'],
                    show: true,
                    width: 2,
                },
                tooltip: {
                    shared: true,
                    intersect: false,
                    theme: 'dark',
                },
                xaxis: {
                    categories: feedback,
                    title: {
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        show: true,
                        color: '#55596e',
                    },
                    axisTicks: {
                        show: true,
                        color: '#55596e',
                    },
                    labels: {
                        style: {
                            colors: '#f5f7ff',
                        },
                    },
                },
                yaxis: {
                    title: {
                        text: 'Count',
                        style: {
                            color: '#f5f7ff',
                        },
                    },
                    axisBorder: {
                        color: '#55596e',
                        show: true,
                    },
                    axisTicks: {
                        color: '#55596e',
                        show: true,
                    },
                    labels: {
                        formatter: function (value) {
                            return Math.round(value); // Đảm bảo giá trị hiển thị là số nguyên
                        },
                        style: {
                            colors: '#f5f7ff'
                        }
                    },
                    min: 0, // Giá trị nhỏ nhất của y-axis
                    max: Math.max(...datafeed) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...datafeed) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };

            const barChart4 = new ApexCharts(
                    document.querySelector('#bar-chart4'),
                    barChartOptions4
                    );
            barChart4.render();

        </script>
        <!-- Custom JS -->
        <!--        <script src="./mktjs/scripts.js"></script>-->
    </body>
</html>
