<%-- 
    Document   : viewOrder.jsp
    Created on : Jul 6, 2024, 4:26:35 PM
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
                                        <li class="breadcrumb-item"><a href="Admin.jsp">Dashboard</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Table</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <div class="row" id="table-head">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Order Detail</h4>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <p>Status: </p>
                                            <p class="btn btn-info">${requestScope.curOd.status}</p>
                                        </div>

                                        <div class="row">
                                            <div class="col-4 d-flex justify-content-center">
                                                <form action="viewOrder?odId=${requestScope.curOd.id}" method="post" class="input-group">
                                                    <input type="search" class="form-control rounded" placeholder="Search item by id" aria-label="Search" aria-describedby="search-addon" name="search" />
                                                    <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>Search</button>
                                                </form>
                                            </div>

                                            <div class="col-4 d-flex justify-content-center">
                                                <form action="viewOrder?odId=${requestScope.curOd.id}" method="post">
                                                    <label>Edit Status: </label>
                                                    <select name="newSta">
                                                        <option value="Wait accept" ${requestScope.status == 'Wait accept' ? 'selected' : ''}>Wait accept</option>
                                                        <option value="Finish" ${requestScope.status == 'Finish' ? 'selected' : ''}>Finish</option>
                                                        <option value="Delivery" ${requestScope.status == 'Delivery' ? 'selected' : ''}>Delivery</option>
                                                        <option value="Cancel" ${requestScope.status == 'Cancel' ? 'selected' : ''}>Cancel</option>
                                                    </select>
                                                    <input class="btn btn-info" type="submit" value="Save">
                                                </form>
                                            </div>

                                            <div class="col-4 d-flex justify-content-center">
                                                <a href="userInfor?userId=${requestScope.curOd.userId}" class="btn btn-primary">View customer Info</a>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="table-responsive">
                                        <table class="table mb-0">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Item Name</th>
                                                    <th>Quantity</th>
                                                    <th>Price</th>
                                                    <th>Discount percent</th>
                                                    <th>Total</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.od}" var="od">
                                                    <tr>
                                                        <td>${od.id}</td>
                                                        <td>
                                                            <c:forEach items="${requestScope.pro}" var="pro">
                                                                <c:if test="${pro.id == od.proId}">
                                                                    <c:set var="cur" value="${pro}" />
                                                                    ${cur.name}
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                        <td>${od.quantity}</td>
                                                        <td>
                                                            ${cur.price}
                                                        </td>
                                                        <td>
                                                            <c:forEach items="${requestScope.dis}" var="dis">
                                                                <c:if test="${dis.proId == cur.id}">
                                                                    <c:set var="curDis" value="${dis}" />
                                                                    ${curDis.percent}
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                        <td>${od.quantity * cur.price * (1 - curDis.percent / 100)} $</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td colspan="5" style="text-align: right; color: red; font-weight: bold;">Total:</td>
                                                    <td style="color: red; font-weight: bold;">${requestScope.total} $</td>
                                                </tr>                                                </tbody>
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
