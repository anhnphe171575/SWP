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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

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
                                <h3>SẢN PHẨM</h3>
                                <span class="material-icons-outlined">inventory_2</span>
                            </div>
                            <h1>${total_product}</h1>
                    </div>

                    <div class="card">
                        <div class="card-inner">
                            <h3>BÀI VIẾT</h3>
                            <span class="material-icons-outlined">article</span>
                        </div>
                        <h1>${total_post}</h1>
                    </div>

                    <div class="card">
                        <div class="card-inner">
                            <h3>KHÁCH HÀNG</h3>
                            <span class="material-icons-outlined">groups</span>
                        </div>
                        <h1>${total_customer}</h1>
                    </div>
                    <div class="card">
                        <div class="card-inner">
                            <h3>PHẢN HỒI</h3>
                            <span class="material-icons-outlined">comment</span>
                        </div>
                        <h1>${total_feedback}</h1>
                    </div>

                </div>
<!--                <form action="MKTDashboard">
                    <h3 style="color: black">Select date:
                        <input type="date" name="dateinput" id="pastDatePicker" class="date-input" max="${date}"  >
                        <button type="submit" value="ok" name="submit" class="submit-button">Submit</button></h3>
                    <input type="hidden" name="service" value="date">
                </form>

                <form action="MKTDashboard">
                    <label for="start-date" style="color: black">Start Date:</label>
                    <input type="date" id="start-date" name="start_date" max="${date}">

                    <label for="end-date" style="color: black">End Date:</label>
                    <input type="date" id="end-date" name="end_date">

                    <button type="submit" value="ok" name="submit" class="submit-button">Submit</button>
                    <input type="hidden" name="service" value="2date">

                </form>-->

                <script>
                    const startDateInput = document.getElementById('start-date');
                    const endDateInput = document.getElementById('end-date');
                    const maxDate = new Date().toISOString().split('T')[0];

                    // Set the max date attribute to today's date for start-date
                    startDateInput.max = maxDate;

                    function updateEndDateRange() {
                        if (startDateInput.value) {
                            const startDate = new Date(startDateInput.value);
                            const minEndDate = new Date(startDate);
                            minEndDate.setDate(startDate.getDate() - 7);
                            const maxEndDate = new Date(startDate);
                            maxEndDate.setDate(startDate.getDate() - 1);

                            // Format the dates to YYYY-MM-DD
                            const minEndDateString = minEndDate.toISOString().split('T')[0];
                            const maxEndDateString = maxEndDate.toISOString().split('T')[0];

                            endDateInput.min = minEndDateString;
                            endDateInput.max = maxEndDateString;

                            if (new Date(endDateInput.value) < minEndDate || new Date(endDateInput.value) > maxEndDate) {
                                endDateInput.value = '';
                            }
                        } else {
                            endDateInput.min = '';
                            endDateInput.max = '';
                            endDateInput.value = '';
                        }
                    }

                    startDateInput.addEventListener('change', updateEndDateRange);

                    // Initialize the date range when the page loads
                    window.addEventListener('load', updateEndDateRange);
                </script>
                <div class="charts">  
                    <!--                    <div class="charts-card" >
                                            <h1 class="chart-title">Newly Cus Statistics</h1>
                    
                                            <div class="charts" id="orderChart"></div>
                                        </div>-->
                    <div class="col-lg-3 charts-card">
                        <h2 class="chart-title">Thông kê khách hàng mới</h2>
                        <h2 style="text-align: center;color: #263043"> a</h2>
                        <h2 style="text-align: center;color: #263043"> a</h2>

                        <div id="statsChartC"></div>
                    </div>
                    <div class="charts-card" >
                        <h2 class="chart-title">Thống kê số sao phản hồi</h2>
                        <h2 style="text-align: center">Trung Bình: ${fb_aver} <i class="fas fa-star"></i></h2>
                        <div class="charts" id="statsChart1"></div>
                    </div>

                </div>
                <div class="charts">
                    <div class="charts-card">
                        <h2 class="chart-title">Sản phẩm mới trong 7 ngày qua</h2>
                        <div id="bar-chart1"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Bài viết mới trong 7 ngày qua</h2>
                        <div id="bar-chart2"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Khách hàng mới trong 7 ngày qua</h2>
                        <div id="bar-chart3"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Phản hồi mới trong 7 ngày qua</h2>
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
                                text: 'SL',
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
                                text: 'SL',
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
                                name: 'SL',
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
                                text: 'SL',
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
                                text: 'SL',
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
                    // feedback
                    var cat = [];
                    var star = [];

            <c:forEach items="${fb}" var="entry">
                    star.push(${entry.value});
                    cat.push('${entry.key}');
            </c:forEach>
                    const statsData1 = {
                        series: [{
                                name: 'Customers',
                                data: cat.map((category, index) => ({
                                        x: category,
                                        y: star[index],
                                        fillColor: '#FFA500' // Orange color for bars
                                    }))
                            }],
                        categories: cat
                    };
                    const statsChartOptions1 = {
                        chart: {
                            type: 'bar',
                            height: 350,
                            toolbar: {
                                show: false // Hide the toolbar (optional)
                            }
                        },
                        series: statsData1.series,
                        xaxis: {
                            categories: statsData1.categories,
                            labels: {
                                style: {
                                    colors: '#f5f7ff' // X-axis label color
                                }
                            }
                        },
                        plotOptions: {
                            bar: {
                                horizontal: false,
                                columnWidth: '55%',
                                endingShape: 'rounded'
                            }
                        },
                        dataLabels: {
                            enabled: false
                        },
                        stroke: {
                            show: true,
                            width: 2,
                            colors: ['#ffffff'] // Stroke color
                        },
                        yaxis: {
                            title: {
                                text: 'Sao phản hồi', // Y-axis title
                                style: {
                                    color: '#f5f7ff' // Y-axis title color
                                }
                            },
                            labels: {
                                style: {
                                    colors: '#f5f7ff' // Y-axis label color
                                },
                                formatter: function (val) {
                                    return Math.round(val); // Formats y-axis labels as integers
                                }
                            },
                            min: 0, // Minimum value of y-axis
                            max: 5, // Maximum value of y-axis
                            tickAmount: 5 // Number of ticks on the y-axis (0, 1, 2, 3, 4, 5)
                        },
                        fill: {
                            opacity: 1
                        },
                        tooltip: {
                            y: {
                                formatter: function (val) {
                                    return val.toFixed(2) + " Stars"; // Formats tooltip value as a double with two decimal places
                                }
                            },
                            theme: 'dark',
                            style: {
                                fontSize: '14px',
                                fontFamily: 'Helvetica, Arial, sans-serif',
                                color: '#f5f7ff' // Tooltip text color
                            }
                        },
                        legend: {
                            show: true,
                            position: 'top',
                            horizontalAlign: 'right',
                            labels: {
                                colors: '#ffffff' // Legend label color
                            }
                        }
                    };


                    const statsChart1 = new ApexCharts(document.querySelector("#statsChart1"), statsChartOptions1);
                    statsChart1.render();

                    // newly customer

                    var new_res = [];
                    var new_order = [];
            <c:forEach items="${newCus}" var="entry">
                    new_res.push('${entry.key}');
                    new_order.push(<c:out value="${entry.value}"/>);
            </c:forEach>
                    const statsData = {
                        series: [{
                                name: 'Customers',
                                data: [new_res[new_res.length - 1], new_order[new_order.length - 1]], // Example data: [new customers, customers with orders today]
                                colors: ['#f5f7ff', '#d50000', '#00ff00', '#0000ff'] // Specify colors for each column
                            }],
                        categories: ['Hoạt động gần đây', 'Khách hàng có đơn hôm nay']
                    };
                    const statsChartOptions = {
                        chart: {
                            type: 'bar',
                            height: 350,
                            toolbar: {
                                show: false // Hide the toolbar (optional)
                            }
                        },
                        series: statsData.series,
                        xaxis: {
                            categories: statsData.categories,
                            labels: {
                                style: {
                                    colors: '#f5f7ff',
                                }
                            }
                        },
                        plotOptions: {
                            bar: {
                                horizontal: false,
                                columnWidth: '55%',
                                endingShape: 'rounded'
                            }
                        },
                        dataLabels: {
                            enabled: false
                        },
                        stroke: {
                            show: true,
                            width: 2,
                            colors: ['#ffffff'] // Stroke color
                        },
                        yaxis: {
                            title: {
                                text: 'Số Lượng',
                                style: {
                                    color: '#f5f7ff'
                                }
                            },
                            labels: {
                                style: {
                                    colors: '#f5f7ff'
                                },
                                formatter: function (val) {
                                    return Math.round(val); // Ensures y-axis labels are integers
                                }
                            },
                            min: 0, // Minimum value of y-axis
                            max: Math.max(...statsData.series[0].data), // Maximum value of y-axis
                            tickAmount: Math.max(...statsData.series[0].data) // Number of ticks on the y-axis
                        },
                        fill: {
                            opacity: 1
                        },
                        tooltip: {
                            y: {
                                formatter: function (val) {
                                    return Math.round(val) + " Customers"; // Formats tooltip value as integer
                                }
                            },
                            theme: 'dark',
                            style: {
                                fontSize: '14px',
                                fontFamily: 'Helvetica, Arial, sans-serif',
                                color: '#f5f7ff'
                            }
                        },
                        legend: {
                            show: true,
                            position: 'top',
                            horizontalAlign: 'right',
                            labels: {
                                colors: '#ffffff' // Legend label color
                            }
                        }
                    };

                    const statsChart = new ApexCharts(document.querySelector("#statsChartC"), statsChartOptions);
                    statsChart.render();

        </script>
        <!-- Custom JS -->
        <!--        <script src="./mktjs/scripts.js"></script>-->
    </body>
</html>
