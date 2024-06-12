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
            <jsp:include page="sidebar1.jsp"></jsp:include>
                <!-- End Sidebar -->

                <!-- Main -->
                <main class="main-container">
                    <div class="main-title">
                        <h2>DASHBOARD</h2>
                    </div>


                    <p style="color: black">Select date:</p>
                    <form action="SaleDashboardURL">
                        <input type="date" name="dateinput" id="pastDatePicker" class="date-input" max="${date}"  >

                    <button type="submit" value="ok" name="submit" class="submit-button">Submit</button>
                    <input type="hidden" name="service" value="date">

                </form>
                <div class="charts">
                    <div class="charts-card">
                        <h2 class="chart-title">Total-Revenue</h2>
                        <div id="bar-chart1"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Total ${total} Order</h2>
                        <div id="pie-chart"></div>
                    </div>
                </div>
                <form action="SaleDashboardURL">
                    <select name="sale">
                        <c:forEach items="${requestScope.sale}" var="s">
                            <option value="${s.getUserID()}">${s.first_name} ${s.last_name}</option>
                        </c:forEach>
                    </select>
                    From:
                    <input type="date" name="startdate">
                    To:
                    <input type="date" name="enddate">
                    <input type="submit" name="submit" >
                    <input type="hidden" name="service" value="sale">
                </form>
                <div class="charts">
                    <div class="charts-card">
                        <h2 class="chart-title">Total-Revenue of Sale</h2>
                        <div id="bar-chart2"></div>
                    </div>
                    <div class="charts-card">
                        <h2 class="chart-title">Total ${total1} Order of Sale</h2>
                        <div id="pie-chart1"></div>
                    </div>
                </div>
                <!--          <div class="charts-card">
                            <h2 class="chart-title">Purchase and Sales Orders</h2>
                            <div id="area-chart"></div>
                          </div>-->


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
            var dataord1 = [];
            var order1 = [];
            var dataord2 = [];
            var order2 = [];
            // Using JSP to populate the JavaScript arrays
            <c:forEach items="${dataOrder}" var="entry">
            dataord1.push(${entry.value});
            order1.push('<c:out value="${entry.key}"/>');
            </c:forEach>
            <c:forEach items="${dataOrder1}" var="entry">
            dataord2.push(${entry.value});
            order2.push('<c:out value="${entry.key}"/>');
            </c:forEach>



// BAR CHART
            const barChartOptions1 = {
                series: [
                    {
                        name: 'Total',
                        data: dataord1
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
                    categories: order1,
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
                    max: Math.max(...dataord1) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...dataord1) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };
            const barChart1 = new ApexCharts(
                    document.querySelector('#bar-chart1'),
                    barChartOptions1
                    );
            barChart1.render();
// BAR CHAR 2
            const totalSuccess = ${dataSuccess}; // Example value for successful orders
            const totalReject = ${dataReject}; // Example value for canceled orders
            const totalPacking = ${dataPacking};
            const totalDelivering = ${dataDelivering};
            const totalSubmit = ${dataSubmit};
            const totalFail = ${dataFail};
            const totalDone = ${dataDone};
            const pieChartOptions = {
                series: [totalSuccess, totalReject, totalPacking, totalDelivering, totalSubmit, totalFail, totalDone], // Replace these variables with your actual data
                chart: {
                    type: 'pie',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                labels: ['Success', 'Reject', 'Packing', 'Delivering', 'Submit', 'Fail', 'Done'], // Update these labels to match your data categories
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'], // Update these colors as needed
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                dataLabels: {
                    enabled: true,
                    style: {
                        colors: ['#f5f7ff'],
                    },
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
// BAR CHART3
            const barChartOptions2 = {
                series: [
                    {
                        name: 'Total',
                        data: dataord2
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
                    categories: order2,
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
                    max: Math.max(...dataord1) + 1, // Giá trị lớn nhất của y-axis (tự động tính toán dựa trên dữ liệu)
                    tickAmount: Math.max(...dataord1) + 1 // Số lượng tick trên trục y-axis, đảm bảo các giá trị hiển thị là số nguyên
                },
            };
            const barChart2 = new ApexCharts(
                    document.querySelector('#bar-chart2'),
                    barChartOptions2
                    );
            barChart2.render();

            const totalSuccess1 = ${dataSuccess1}; // Example value for successful orders
            const totalReject1 = ${dataReject1}; // Example value for canceled orders
            const totalPacking1 = ${dataPacking1};
            const totalDelivering1 = ${dataDelivering1};
            const totalSubmit1 = ${dataSubmit1};
            const totalFail1 = ${dataFail1};
            const totalDone1 = ${dataDone1};
            const pieChartOptions1 = {
                series: [totalSuccess1, totalReject1, totalPacking1, totalDelivering1, totalSubmit1, totalFail1, totalDone1], // Replace these variables with your actual data
                chart: {
                    type: 'pie',
                    background: 'transparent',
                    height: 350,
                    toolbar: {
                        show: false,
                    },
                },
                labels: ['Success', 'Reject', 'Packing', 'Delivering', 'Submit', 'Fail', 'Done'], // Update these labels to match your data categories
                colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3', '#ffffff', '#ffff00'], // Update these colors as needed
                legend: {
                    labels: {
                        colors: '#f5f7ff',
                    },
                    show: true,
                    position: 'top',
                },
                dataLabels: {
                    enabled: true,
                    style: {
                        colors: ['#f5f7ff'],
                    },
                },
                tooltip: {
                    theme: 'dark',
                },
            };
            const pieChart1 = new ApexCharts(
                    document.querySelector('#pie-chart1'),
                    pieChartOptions1
                    );
            pieChart1.render();


        </script>
        <!-- Custom JS -->
        <!--        <script src="./mktjs/scripts.js"></script>-->
    </body>
</html>
