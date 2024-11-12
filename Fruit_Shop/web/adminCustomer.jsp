<%-- 
    Document   : adminCustomer
    Created on : Jul 5, 2024, 12:56:39 AM
    Author     : tuong
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div id="app">
            <jsp:include page="AdminMenu.jsp"></jsp:include>
            
            <div id="main">
                <div class="main-content container-fluid">
                    <div class="page-title">
                        <div class="row">
                            <div class="col-12 col-md-6 order-md-1 order-last">
                                <h3>CUSTOMER</h3>
                            </div>
                            <div class="col-12 col-md-6 order-md-2 order-first">
                                <nav aria-label="breadcrumb" class='breadcrumb-header'>
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Table</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <div class="row" id="table-head">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Table head options</h4>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <p>Similar to tables and dark tables, use the modifier classes <code
                                                    class="highlighter-rouge">.thead-light</code> or <code class="highlighter-rouge">.thead-dark</code> to
                                                make <code class="highlighter-rouge">&lt;thead&gt;</code>s appear light or dark gray.</p>
                                        </div>
                                        <div class="row ">
                                            <div class="col-4 d-flex justify-content-center" >
                                                <form action="customer" method="post" class="input-group ">
                                                    <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="search" />
                                                    <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>search</button>
                                                </form>
                                            </div>

                                        </div>
                                        <!-- table head dark -->
                                        <div class="table-responsive">
                                            <table class="table mb-0">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Name</th>
                                                        <th>Username</th>
                                                        <th>Password</th>
                                                        <th>Role</th>
                                                        <th>Gmail</th>
                                                        <th>ACTION</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.list}" var="c">
                                                        <tr>
                                                            <td>${c.id}</td>
                                                            <td>${c.name}</td>
                                                            <td>${c.username}</td>
                                                            <td>${c.password}</td>
                                                            <td>${c.role}</td>
                                                            <td>${c.gmail}</td>
                                                            <td><a href="#"><i
                                                                        class="badge-circle badge-circle-light-secondary font-medium-1" data-feather="settings"></i></a>
                                                                <a href="#"><i
                                                                        class="badge-circle badge-circle-light-secondary font-medium-1" data-feather="trash-2"></i></a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
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
