<%-- 
    Document   : adminProduct
    Created on : Jul 3, 2024, 5:44:28 PM
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
                                <h3>Product Management</h3>
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
                                        <h4 class="card-title">Table head options</h4>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <p>Similar to tables and dark tables, use the modifier classes <code
                                                    class="highlighter-rouge">.thead-light</code> or <code class="highlighter-rouge">.thead-dark</code> to
                                                make <code class="highlighter-rouge">&lt;thead&gt;</code>s appear light or dark gray.</p>
                                        </div>
                                        <div class="row ">
                                            <div class="col-4 d-flex justify-content-center align-content-center ">
                                                <c:set value="${requestScope.cur}" var="curId"></c:set>
                                                    <form action="product" method="post">
                                                        <label>Category: </label>
                                                        <select name="caId" onchange="this.form.submit()">
                                                            <option value="-1">Select All Products</option>
                                                        <c:forEach items="${requestScope.listCa}" var="Ca">
                                                            <option ${Ca.id == curId?'selected':''} value="${Ca.id}">${Ca.name}</option>
                                                        </c:forEach>

                                                    </select>
                                                </form> 
                                            </div>
                                            <div class="col-4 d-flex justify-content-center" >
                                                <form action="product" method="post" class="input-group ">
                                                    <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="search" />
                                                    <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>search</button>
                                                </form>
                                            </div>
                                            <div class="col-4 d-flex justify-content-center" >
                                                <a href="addproduct" class="btn btn-primary">Add new product</a>
                                            </div>
                                        </div>

                                        <!-- table head dark -->
                                        <div class="table-responsive">
                                            <table class="table mb-0">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Name</th>
                                                        <th>Image</th>
                                                        <th>CATEGORY</th>
                                                        <th>QUANTITY</th>
                                                        <th>PRICE</th>
                                                        <th>ACTION</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach items="${requestScope.listPr}" var="c">
                                                        <tr>
                                                            <td>${c.id}</td>
                                                            <td>${c.name} </td>
                                                            <td><img width="80px" src="${c.img}"></td>
                                                            <td>
                                                                <c:forEach items="${requestScope.listCa}" var="ca">
                                                                    <c:if test="${ca.id== c.cateId}">${ca.name}</c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td>${c.quantity}</td>
                                                            <td>${c.price}d</td>

                                                            <td><a href="updateProduct?pid=${c.id}"><i
                                                                        class="badge-circle badge-circle-light-secondary font-medium-1" data-feather="settings"></i></a>
                                                                <a href="deleteProduct?pid=${c.id}"><i
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