<%-- 
    Document   : AdminMenu
    Created on : Jul 15, 2024, 11:50:43 PM
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
    </head>
    <body>
        <div id="app">   <div id="sidebar" class='active'>
                <div class="sidebar-wrapper active">
                    <div class="sidebar-header">
                        <img src="assets/images/logo.svg" alt="" srcset="">
                    </div>
                    <div class="sidebar-menu">
                        <ul class="menu">

                            <li class='sidebar-title'>Main Menu</li>
                            <li class="sidebar-item  ">

                                <a href="AdminMenu.jsp" class='sidebar-link'>
                                    <i data-feather="home" width="20"></i> 
                                    <span>Dashboard</span>
                                </a>


                            </li>

                            <li class="sidebar-item  ">

                                <a href="siteuser" class='sidebar-link'>
                                    <i data-feather="user" width="20"></i> 
                                    <span>User</span>
                                </a>

                            </li>
                            <li class="sidebar-item ">

                                <a href="product" class='sidebar-link '>
                                    <i data-feather="briefcase" width="20"></i> 
                                    <span>Product</span>
                                </a>


                            </li>


                            <li class="sidebar-item  ">

                                <a href="orderDetail" class='sidebar-link'>
                                    <i data-feather="file-text" width="20"></i> 
                                    <span>Order Detail</span>
                                </a>


                            </li>
                            <li class="sidebar-item  ">

                                <a href="statistic" class='sidebar-link'>
                                    <i data-feather="layers" width="20"></i> 
                                    <span>Statistic</span>
                                </a>


                            </li>

                            <li class="sidebar-item  ">

                                <a href="homepage" class='sidebar-link'>
                                    <i data-feather="file-plus" width="20"></i> 
                                    <span>Back to shop</span>
                                </a>
                            </li>

                            <li class='sidebar-title'>Extra UI</li>
                            <li class="sidebar-item  has-sub">
                                <a href="userInfor?userId=${sessionScope.user.id}" class='sidebar-link'>
                                    <i data-feather="user" width="20"></i> 
                                    <span>${sessionScope.user.name}</span>
                                </a>
                            </li>
                            <li class="sidebar-item  has-sub">
                                <a href="logout" class='sidebar-link'>
                                    <i data-feather="user" width="20"></i> 
                                    <span>Logout</span>
                                </a>
                            </li>
                            <li class="sidebar-item  has-sub">

                                <a href="#" class='sidebar-link'>
                                    <i data-feather="alert-circle" width="20"></i> 
                                    <span>Errors</span>
                                </a>


                                <ul class="submenu ">

                                    <li>
                                        <a href="error-403.html">403</a>
                                    </li>

                                    <li>
                                        <a href="error-404.html">404</a>
                                    </li>

                                    <li>
                                        <a href="error-500.html">500</a>
                                    </li>

                                </ul>

                            </li>


                        </ul>
                    </div>
                    <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
                </div>
            </div></div>

        <script src="assets/js/feather-icons/feather.min.js"></script>
        <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <script src="assets/js/app.js"></script>

        <script src="assets/vendors/chartjs/Chart.min.js"></script>
        <script src="assets/vendors/apexcharts/apexcharts.min.js"></script>
        <script src="assets/js/pages/dashboard.js"></script>

        <script src="assets/js/main.js"></script>
    </body>
</html>

