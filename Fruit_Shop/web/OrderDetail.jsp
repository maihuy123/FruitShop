<%-- 
    Document   : OrderDetail
    Created on : Jul 5, 2024, 12:36:01 PM
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
        <div id="app">
            <jsp:include page="AdminMenu.jsp"></jsp:include>
            <div id="main">
               
                <div class="main-content container-fluid">
                    <div class="page-title">
                        <div class="row">
                            <div class="col-12 col-md-6 order-md-1 order-last">
                                <h3>User Management</h3>
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
                                        <div class="row">
                                            <div class="row">
                                                <div class="col-4 d-flex justify-content-center align-content-center">
                                                    <form action="oderDetail" method="post">
                                                        <label>Status: </label>
                                                        <select name="status" onchange="this.form.submit()">
                                                            <option value="all" ${status == 'all' ? 'selected' : ''}>Select All</option>
                                                            <option value="Wait accept" ${status == 'Wait accept' ? 'selected' : ''}>Wait accept</option>
                                                            <option value="Finish" ${status == 'Finish' ? 'selected' : ''}>Finish</option>
                                                            <option value="Delivery" ${status == 'Delivery' ? 'selected' : ''}>Delivery</option>
                                                            <option value="Cancel" ${status == 'Cancel' ? 'selected' : ''}>Cancel</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <div class="col-4 d-flex justify-content-center">
                                                    <form action="oderDetail" method="post" class="input-group">
                                                        <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="search" />
                                                        <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>Search by id</button>
                                                    </form>
                                                </div>
                                               
                                            </div>
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table mb-0">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th>Order Id</th>
                                                        <th>Customer</th>
                                                        <th>Create Time</th>
                                                        <th>Status</th>
                                                        <th>Address</th>
                                                        <th>Total</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.listOd}" var="od">
                                                        <tr>
                                                            <td>${od.id}</td>
                                                            <td>
                                                                <c:forEach items="${requestScope.listCus}" var="cus">
                                                                    <c:if test="${cus.id == od.userId}">${cus.name}</c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td>${od.createAt}</td>
                                                            <td>${od.status}</td>
                                                            <td>${od.address}</td>
                                                            <td>
                                                                <c:forEach items="${requestScope.total}" var="to">
                                                                    <c:if test="${to.key == od.id}">${to.value} $</c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td><a href="viewOrder?odId=${od.id}"><i class="badge-circle badge-circle-light-secondary font-medium-1" data-feather="eye"></i></a></td>
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
