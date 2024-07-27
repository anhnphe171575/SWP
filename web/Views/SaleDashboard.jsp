<%-- 
    Document   : AdminDashboard
    Created on : Jun 19, 2024, 4:58:28 PM
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

        <!-- Montserrat Font -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Custom CSS -->
        <link rel="stylesheet" href="./mktcss/styles.css">
        <title>Dashboard</title>
        <style>

            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #1b2635;
                color: #f5f7ff;
            }
            .chart-container {
                max-width: 650px;
                margin: 0 auto;
            }
            .card {
                flex: 1;
                min-width: 200px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                box-shadow: 0 0 10px red;
            }
            .chart {
                width: 100%;
                height: 400px;
            }
            .chats-card1{
                background-color: #f0f0f0;
            }

            .date-picker {
                margin-bottom: 20px;
            }
            .grid-container {
                display: grid;
                color: #9e9ea4;
                height: 100vh;
                width: 100%;
            }
            .select{
                color: black;
                margin-bottom: 20px;
            }
            
            .charts-card{
                    text-shadow: 
        -1px -1px 0 #000, /* Top-left */
        1px -1px 0 #000, /* Top-right */
        -1px 1px 0 #000, /* Bottom-left */
        1px 1px 0 #000; /* Bottom-right */
    font-weight: bold; /* Adjust font weight as needed */
    font-family: Arial, sans-serif; /* Adjust font family as needed */
            }
        </style>
    </head>
    <body>


        <div class="grid-container">

            <!-- Header -->
            <jsp:include page="header.jsp"></jsp:include>

                <!-- End Header -->

                <!-- Sidebar -->
            <jsp:include page="sidebar1.jsp"></jsp:include>
                <!-- End Sidebar -->

                <main class="main-container">
                    <div class="main-title">
                        <h2 style="color: black">Biểu đồ</h2>
                    </div>
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
                    
                <div class="select">
                    <h2>Theo tháng:</h2>
                    <form action="SaleDashboardURL" method="post">
                        <label for="month">Tháng:</label>
                        <input type="number" id="month" name="month" min="1" max="12" value="<%= displayMonth %>" required>
                        <label for="year">Năm:</label>
                        <input type="number" id="year" name="year" min="2000" max="2100" value="<%= displayYear %>" required>
                        <input type="hidden" name="service" value="select">
                        <input type="submit"></input>
                    </form>
                </div>
                <div class="charts-card">
                    <h2 class="chart-title">Đơn hàng thành công</h2>
                    <div id="area-chart"></div>
                </div>
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
                  
                    <div class="col-lg-3 charts-card">
                        <h2 class="chart-title">Trạng thái đơn hàng</h2>
                        <div id="pie-chart1"></div>
                    </div>
<!--                    <div class="charts-card" >
                        <h2 class="chart-title">Feedback Star</h2>
                        <h2>Average:4.25</h2>

                        <div class="charts" id="statsChart1"></div>
                    </div>-->
  <div class="col-lg-3 charts-card">
                        <h2 class="chart-title">Đơn hàng theo thể loại</h2>
                        <h2>Tổng: ${total}</h2>
                        <div id="pie-chart"></div>
                    </div>

                </div>

                <div class="charts">
                  
<!--                    <div class="col-lg-3 charts-card">
                        <h2 class="chart-title">Newly Statistics</h2>
                        <div id="statsChartC"></div>
                    </div>-->
                 
                </div>
        </div>
    </main>
<script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.5/apexcharts.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>

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


                    var dates = [];
                    var totals = [];
                    var datea = [];
                    var totala = [];

                    // chart all and sucess
        <c:forEach items="${sucess}" var="entry">
                    totals.push('${entry.value}');
                    dates.push(<c:out value="${entry.key}"/>);
        </c:forEach>
        <c:forEach items="${all}" var="entry1">
                    totala.push('${entry1.value}');
                    datea.push('${entry1.key}');
        </c:forEach>

                    console.log("All Dates: ", datea);
                    const areaChartOptions = {
                        series: [
                            {
                                name: 'Thành Công',
                                data: totals,
                            },
                            {
                                name: 'Tất Cả',
                                data: totala,
                            },
                        ],
                        chart: {
                            type: 'area',
                            background: 'transparent',
                            height: 350,
                            stacked: false,
                            toolbar: {
                                show: false,
                            },
                        },
                        colors: ['#00ab57', '#d50000'],
                        labels: dates,
                        dataLabels: {
                            enabled: false,
                        },
                        fill: {
                            gradient: {
                                opacityFrom: 0.4,
                                opacityTo: 0.1,
                                shadeIntensity: 1,
                                stops: [0, 100],
                                type: 'vertical',
                            },
                            type: 'gradient',
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
                        markers: {
                            size: 6,
                            strokeColors: '#1b2635',
                            strokeWidth: 3,
                        },
                        stroke: {
                            curve: 'smooth',
                        },
                        xaxis: {
                            axisBorder: {
                                color: '#55596e',
                                show: true,
                            },
                            axisTicks: {
                                color: '#55596e',
                                show: true,
                            },
                            labels: {
                                offsetY: 5,
                                style: {
                                    colors: '#f5f7ff',
                                },
                            },
                            categories: datea, // Use the datea array for x-axis categories
                        },
                        yaxis: {
                            title: {
                                text: 'Đơn hàng',
                                style: {
                                    color: '#f5f7ff',
                                },
                            },
                            labels: {
                                style: {
                                    colors: ['#f5f7ff'],
                                },
                            },
                        },
                        tooltip: {
                            shared: true,
                            intersect: false,
                            theme: 'bright',
                        },
                    };
                    const areaChart = new ApexCharts(
                            document.querySelector('#area-chart'),
                            areaChartOptions
                            );
                    areaChart.render();

// revenues order by cate

                    var total = [];
                    var cat = [];
        <c:forEach items="${revenues}" var="entry">
                    cat.push('${entry.value}');
total.push(<c:out value="${entry.key}"/>);
        </c:forEach>
                    const pieChartOptions = {
                        series: total,
                        chart: {
                            type: 'pie',
                            background: 'transparent',
                            height: 350,
                        },
                        labels: cat,
                        colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'],
                        legend: {
                            labels: {
                                colors: '#f5f7ff',
                            },
                            show: true,
                            position: 'top',
                        },
                        tooltip: {
                            theme: 'dark',
                        },
                    };

                    const pieChart = new ApexCharts(
                            document.querySelector('#pie-chart'),
                            pieChartOptions
                            );
                    pieChart.render();

                    // Order statistics data

                    const orderData = {
                        categories: ["2024-06-13", "2024-06-14", "2024-06-15", "2024-06-16", "2024-06-17", "2024-06-18", "2024-06-19"],
                        series: [
                            {
                                name: 'Success Orders',
                                data: [10, 12, 8, 15, 20, 18, 25],
                                color: '#00ab57'
                            },
                            {
                                name: 'Cancelled Orders',
                                data: [2, 1, 4, 3, 5, 2, 4],
                                color: '#d50000'
                            },
                            {
                                name: 'Submitted Orders',
                                data: [12, 13, 12, 18, 25, 20, 29],
                                color: '#f39c12'
                            }
                        ]
                    };

                    const orderChartOptions = {
                        chart: {
                            type: 'bar',
                            height: 350
                        },
                        series: orderData.series,
                        xaxis: {
                            categories: orderData.categories,
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
                            },
                        },
dataLabels: {
                            enabled: false
                        },
                        stroke: {
                            show: true,
                            width: 2,
                        },
                        yaxis: {
                            title: {
                                text: 'Orders',
                                style: {
                                    color: '#f5f7ff',
                                }
                            },
                            labels: {
                                style: {
                                    colors: ['#f5f7ff']
                                }
                            }
                        },
                        fill: {
                            opacity: 1
                        },
                        tooltip: {
                            y: {
                                formatter: function (val) {
                                    return val + " Orders";
                                }
                            },
                            marker: {
                                show: true,
                                fillColors: ['#00ab57', '#d50000', '#f39c12']
                            },
                            x: {
                                show: true,
                                format: 'dd MMM yyyy'
                            },
                            theme: 'dark',
                            style: {
                                fontSize: '14px',
                                fontFamily: 'Helvetica, Arial, sans-serif',
                                color: '#f5f7ff'
                            },
                        }
                        , legend: {
                            labels: {
                                colors: '#ffffff' // Set legend label color to white
                            }
                        }
                    };

                    const orderChart = new ApexCharts(document.querySelector("#orderChart"), orderChartOptions);
                    orderChart.render();
                    // newly customer

                    var new_res = [];
                    var new_order = [];
        <c:forEach items="${newCus}" var="entry">
                    new_res.push('${entry.value}');
                    new_order.push(<c:out value="${entry.key}"/>);
        </c:forEach>
                    const statsData = {
                        series: [{
                                name: 'Customers',
                                data: [new_res[new_res.length - 1], new_order[new_order.length - 1]], // Example data: [new customers, customers with orders today]
                                colors: ['#f5f7ff', '#d50000', '#00ff00', '#0000ff'] // Specify colors for each column
                            }],
                        categories: ['New Customers', 'Customers with Orders Today']
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
                                text: 'Number of Customers',
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
                                text: 'Feedback Star', // Y-axis title
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
                                    return val.toFixed(2) + " Customers"; // Formats tooltip value as a double with two decimal places
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

                    // order_status piechart

                    var count = [];
                    var s = [];

        <c:forEach items="${order_status}" var="entry">
                    s.push('${entry.key}');
                    count.push(${entry.value}); // Ensure entry.value is correctly handled
        </c:forEach>

                    console.log(status);
                    console.log(count);

                    const pieChartOptions1 = {
                        series: count,
                        chart: {
                            type: 'pie',
                            background: 'transparent',
                            height: 350,
                        },
                        labels: s,
                        colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#D4B996', '#ffff00', '#808080'],
                        legend: {
                            labels: {
                                colors: '#f5f7ff' // Ensure this is a valid value assignment
                            },
                            show: true,
position: 'top',
                        },
                        tooltip: {
                            theme: 'light',
                            background: 'rgba(0, 0, 0, 0.7)'},
                    };

                    const pieChart1 = new ApexCharts(
                            document.querySelector('#pie-chart1'),
                            pieChartOptions1
                            );
                    pieChart1.render();
    </script>
</body>
</html>
