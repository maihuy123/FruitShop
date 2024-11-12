<%-- 
    Document   : Statistic
    Created on : Jul 16, 2024, 3:32:23 PM
    Author     : tuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Voler Admin Dashboard</title>

        <link rel="stylesheet" href="assets/css/bootstrap.css">

        <link rel="stylesheet" href="assets/vendors/chartjs/Chart.min.css">

        <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
        <link rel="stylesheet" href="assets/css/app.css">
        <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    </head>
    <body>
        <jsp:include page="AdminMenu.jsp"></jsp:include>
            <div id="app">
                <div id="main">
                    <nav class="navbar navbar-header navbar-expand navbar-light">
                        <a class="sidebar-toggler" href="#"><span class="navbar-toggler-icon"></span></a>
                        <button class="btn navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                       
                    </nav>
                    <div class="main-content container-fluid">
                        <div class="page-title">
                            <div class="row">
                                <div class="col-12 col-md-6 order-md-1 order-last">
                                    <h3>Product Management</h3>
                                </div>
                                <div class="col-12 col-md-6 order-md-2 order-first">
                                    <nav aria-label="breadcrumb" class='breadcrumb-header'>
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="Admin.jsp">Dashboard</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Product</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                            <div class="row" id="table-head">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Statistic</h4>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body" style="display: flex">
                                                <div class="col-lg-4 col-md-4 col-sm-6">
                                                    <h5>Total number of product: ${requestScope.totalPr}</h5>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-6">
                                                <h5>Total number of Order: ${requestScope.totalOrder}</h5>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-6">
                                                <h5>Average Price: ${requestScope.avgPrice}$</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="main-content" style="display: flex">
                        <div class="col-md-6" style="border: solid">
                            <canvas id="revenueChart" width="400" height="200"></canvas>
                            <script>
                                var ctx = document.getElementById('revenueChart').getContext('2d');
                                var revenueData = ${monthlyIncome};
                                var labels = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
                                var revenueChart = new Chart(ctx, {
                                    type: 'bar',
                                    data: {
                                        labels: labels,
                                        datasets: [{
                                                label: 'Total Revenue',
                                                data: revenueData,
                                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                borderColor: 'rgba(75, 192, 192, 1)',
                                                borderWidth: 1
                                            }]
                                    },
                                    options: {
                                        scales: {
                                            y: {
                                                beginAtZero: true
                                            }
                                        },
                                        plugins: {
                                            title: {
                                                display: true,
                                                text: 'Total Revenue by Month'
                                            }
                                        }
                                    }
                                });
                            </script>
                            <h3 style="text-align: center">Total Revenue by Month</h3>
                        </div>
                        <div class="col-md-6" style="border: solid">
                            <canvas id="incomeChart"></canvas>
                            <script>
                                var ctx = document.getElementById('incomeChart').getContext('2d');
                                var incomeData = [];
                                var labels = [];

                                // Extract data from JSP attribute
                                <c:forEach items="${incomeByCategory}" var="entry">
                                labels.push('${entry.key}');
                                incomeData.push(${entry.value});
                                </c:forEach>

                                var incomeChart = new Chart(ctx, {
                                    type: 'doughnut',
                                    data: {
                                        datasets: [{
                                                data: incomeData,
                                                backgroundColor: [
                                                    'rgb(255, 99, 132)',
                                                    'rgb(255, 159, 64)',
                                                    'rgb(255, 205, 86)',
                                                    'rgb(75, 192, 192)',
                                                    'rgb(54, 162, 235)',
                                                            // Add more colors as needed
                                                ],
                                            }],
                                        labels: labels,
                                    },
                                    options: {
                                        plugins: {
                                            datalabels: {
                                                formatter: (value) => {
                                                    return value + '%';
                                                },
                                            },
                                        },
                                    },
                                });
                            </script>
                             <h3 style="text-align: center">Total Revenue by Category</h3>
                        </div>
                    </div>

                </div>
            </div>    
            <footer>
                <div class="footer clearfix mb-0 text-muted">
                    <div class="float-start">
                        <p>2022 &copy; Voler</p>
                    </div>
                    <div class="float-end">
                        <p>Crafted with <span class='text-danger'><i data-feather="heart"></i></span> by <a href="https://saugi.me">Saugi</a></p>
                    </div>
                </div>
            </footer>
        </div>

    </div>
    <script src="assets/js/feather-icons/feather.min.js"></script>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/app.js"></script>

    <script src="assets/vendors/chartjs/Chart.min.js"></script>
    <script src="assets/vendors/apexcharts/apexcharts.min.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>

    <script src="assets/js/main.js"></script>
</body>
</html>
