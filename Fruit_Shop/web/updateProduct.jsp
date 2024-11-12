<%-- 
    Document   : updateProduct
    Created on : Jul 4, 2024, 11:03:55 AM
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
        <jsp:include page="AdminMenu.jsp"></jsp:include>
            <div id="app">

                <div id="main">
                    <nav class="navbar navbar-header navbar-expand navbar-light">
                        <a class="sidebar-toggler" href="#"><span class="navbar-toggler-icon"></span></a>
                        <button class="btn navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav d-flex align-items-center navbar-light ms-auto">
                                <li class="dropdown nav-icon">
                                    <a href="#" data-bs-toggle="dropdown" class="nav-link  dropdown-toggle nav-link-lg nav-link-user">
                                        <div class="d-lg-inline-block">
                                            <i data-feather="bell"></i>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end dropdown-menu-large">
                                        <h6 class='py-2 px-4'>Notifications</h6>
                                        <ul class="list-group rounded-none">
                                            <li class="list-group-item border-0 align-items-start">
                                                <div class="avatar bg-success me-3">
                                                    <span class="avatar-content"><i data-feather="shopping-cart"></i></span>
                                                </div>
                                                <div>
                                                    <h6 class='text-bold'>New Order</h6>
                                                    <p class='text-xs'>
                                                        An order made by Ahmad Saugi for product Samsung Galaxy S69
                                                    </p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="dropdown nav-icon me-2">
                                    <a href="#" data-bs-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg nav-link-user">
                                        <div class="d-lg-inline-block">
                                            <i data-feather="mail"></i>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end" >
                                        <a class="dropdown-item" href="#"><i data-feather="user"></i> Account</a>
                                        <a class="dropdown-item active" href="#"><i data-feather="mail"></i> Messages</a>
                                        <a class="dropdown-item" href="#"><i data-feather="settings"></i> Settings</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#"><i data-feather="log-out"></i> Logout</a>
                                    </div>
                                </li>
                                <li class="dropdown">
                                    <a href="#" data-bs-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg nav-link-user">
                                        <div class="avatar me-1">
                                            <img src="assets/images/avatar/avatar-s-1.png" alt="" srcset="">
                                        </div>
                                        <div class="d-none d-md-block d-lg-inline-block">Hi, Saugi</div>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end">
                                        <a class="dropdown-item" href="#"><i data-feather="user"></i> Account</a>
                                        <a class="dropdown-item active" href="#"><i data-feather="mail"></i> Messages</a>
                                        <a class="dropdown-item" href="#"><i data-feather="settings"></i> Settings</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#"><i data-feather="log-out"></i> Logout</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
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
                                            <h4 class="card-title">Update Product</h4>
                                        </div>
                                        <div class="card-content" style="display: flex">
                                            <div class="col-md-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h4 class="card-title">Horizontal Form</h4>
                                                    </div>
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                        <c:set value="${requestScope.detail}" var="pr"></c:set>

                                                            <form class="form form-horizontal" action="updateProduct?pid=${pr.id}" method="post">
                                                                <div class="form-body">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <label>ID</label>
                                                                        </div>
                                                                        <div class="col-md-8 form-group">
                                                                            <input type="text" class="form-control" name="id" value="${pr.id}" readonly>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Name</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <input type="text" class="form-control" name="name" value="${pr.name}">
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Quantity</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <input type="number" class="form-control" name="quantity" value="${pr.quantity}">
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Price</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <input type="text" class="form-control" name="price" value="${pr.price}">
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Category</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <select name="category" class="form-control">
                                                                            <c:forEach items="${requestScope.listCa}" var="ca">
                                                                                <option ${pr.cateId == ca.id ? 'selected' : ''} value="${ca.id}">${ca.name}</option>
                                                                            </c:forEach>
                                                                        </select>                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Description</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <textarea class="form-control" id="description" name="description" rows="5"  required>${pr.decription}</textarea>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label>Image</label>
                                                                    </div>
                                                                    <div class="col-md-8 form-group">
                                                                        <input type="text" class="form-control" name="img" placeholder="Paste Image link here" value="${pr.img}" required>
                                                                    </div>

                                                                    <div class="col-sm-12 d-flex justify-content-end">
                                                                        <button type="submit" class="btn btn-primary me-1 mb-1">Submit</button>
                                                                        <button type="reset" class="btn btn-light-secondary me-1 mb-1">Reset</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h4 class="card-title">Image</h4>
                                                </div>
                                                <div class="card-content">
                                                    <div class="card-body">
                                                        <div><img src="${requestScope.detail.img}" alt="alt" style="width: 400px"/></div>
                                                    </div>
                                                </div>
                                            </div>
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
