<%-- 
    Document   : Billing.jsp
    Created on : Jul 15, 2024, 9:19:48 PM
    Author     : tuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Ogani | Template</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style>
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
            .fa-2x {
                font-size: 2em;
            }

            .table-billing-history th, .table-billing-history td {
                padding-top: 0.75rem;
                padding-bottom: 0.75rem;
                padding-left: 1.375rem;
                padding-right: 1.375rem;
            }
            .table > :not(caption) > * > *, .dataTable-table > :not(caption) > * > * {
                padding: 0.75rem 0.75rem;
                background-color: var(--bs-table-bg);
                border-bottom-width: 1px;
                box-shadow: inset 0 0 0 9999px var(--bs-table-accent-bg);
            }

            .border-start-primary {
                border-left-color: #0061f2 !important;
            }
            .border-start-secondary {
                border-left-color: #6900c7 !important;
            }
            .border-start-success {
                border-left-color: #00ac69 !important;
            }
            .border-start-lg {
                border-left-width: 0.25rem !important;
            }
            .h-100 {
                height: 100% !important;
            }
        </style>
    </head>
    <body>
        <c:set value="${requestScope.user}" var="us"></c:set>

            <div class="container-xl px-4 mt-4">
                <!-- Account page navigation-->
                <nav class="nav nav-borders">
                    <a class="nav-link ms-0" href="userInfor?userId=${us.id}" target="__blank">Profile</a>
                <a class="nav-link active" href="billing?userId=${us.id}" target="__blank">Billing</a>
                <a class="nav-link" href="homepage" target="__blank">Back to shop</a>

            </nav>
            <hr class="mt-0 mb-4">

            <!-- Payment methods card-->
            <div class="card card-header-actions mb-4">
                <div class="card-header">
                    Payment Methods
                </div>
                <div class="card-body px-0">
                    <!-- Payment method 1-->

                    <!-- Payment method 2-->

                    <!-- Payment method 3-->
                    <div class="d-flex align-items-center justify-content-between px-4">
                        <div class="d-flex align-items-center">
                            <i class="fab fa-cc-amex fa-2x cc-color-amex"></i>
                            <div class="ms-4">
                                <div class="small">American Express ending in 9012</div>
                                <div class="text-xs text-muted">Expires 01/2026</div>
                            </div>
                        </div>
                        <div class="ms-4 small">
                            <a class="text-muted me-3" href="#!">Make Default</a>
                            <a href="#!">Edit</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Billing history card-->
            <div class="card mb-4">
                <div class="card-header">Billing History</div>
                <div class="card-body p-0">
                    <!-- Billing history table-->
                    <div class="table-responsive table-billing-history">
                        <table class="table mb-0">
                            <thead>
                                <tr>
                                    <th class="border-gray-200" scope="col">Transaction ID</th>
                                    <th class="border-gray-200" scope="col">Date</th>
                                    <th class="border-gray-200" scope="col">Amount</th>
                                    <th class="border-gray-200" scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${requestScope.listOd}" var="od">
                                    <tr>
                                        <td>${od.id}</td>
                                        <td>${od.createAt}</td>
                                        <td>
                                            <c:forEach items="${requestScope.total}" var="to">
                                                <c:if test="${to.key == od.id}">${to.value} $</c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${od.status}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
