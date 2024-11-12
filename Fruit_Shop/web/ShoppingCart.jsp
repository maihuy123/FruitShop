<%-- 
Document   : ShopingCart.jsp
Created on : Jul 9, 2024, 10:46:04 AM
Author     : tuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

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
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <!-- Hero Section Begin -->

            <!-- Hero Section End -->
            <section class="hero hero-normal">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="hero__categories">
                                <div class="hero__categories__all">
                                    <i class="fa fa-bars"></i>
                                    <span>All departments</span>
                                </div>
                                <ul>
                                    <li><a href="homepage?caId=-1">All </a></li>
                                    <c:forEach items="${requestScope.liCa}" var="c">
                                    <li><a href="homepage?caId=${ca.id}">${c.name}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="hero__search">
                            <div class="hero__search__form">
                                <form action="homepage" method="post">
                                    <input type="text" placeholder="What do yo u need?" name="search">
                                    <button type="submit" class="site-btn">SEARCH</button>
                                </form>
                            </div>
                            <div class="hero__search__phone">
                                <div class="hero__search__phone__icon">
                                    <i class="fa fa-phone"></i>
                                </div>
                                <div class="hero__search__phone__text">
                                    <h5>+65 11.188.888</h5>
                                    <span>support 24/7 time</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Shopping Cart</h2>
                            <div class="breadcrumb__option">
                                <a href="homepage">Home</a>
                                <span>Shopping Cart</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
        <section class="shoping-cart spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__table">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="shoping__product">Products</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Discount</th>
                                        <th>Total</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                   
                                    <c:forEach items="${requestScope.liItem}" var="item">
                                        <c:forEach items="${requestScope.liPro}" var="pro">
                                            
                                            <c:if test="${item.prId == pro.id}">
                                                <c:set var="cur" value="${pro}" />

                                                <tr>
                                                    <td class="shoping__cart__item">
                                                        <img src="${cur.img}" alt="" style="width: 80px">
                                                        <h5>${cur.name}</h5>
                                                    </td>
                                                    <td class="shoping__cart__price">
                                                        ${cur.price}
                                                    </td>
                                                    <td class="shoping__cart__quantity">
                                                        <div class="quantity">
                                                            <a href="cart?quanId=${item.id}&quantity=${item.quantity-1}" style="padding: 10px;text-decoration: none;color: black">-</a>
                                                            <input type="text" value="${item.quantity}" name="quantity" style="width: 50px;text-align: center" 
                                                                   onblur="submitQuantityForm(${item.id})">                                                            
                                                            <a href="cart?quanId=${item.id}&quantity=${item.quantity+1}" style="padding: 10px;text-decoration: none;color: black">+</a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <select name="discount" onchange="updateDiscount(${item.id}, this.value)">
                                                            <option value="0" ${requestScope.discount == 0 ? 'selected="selected"' : ''}>None</option>
                                                            <c:forEach items="${requestScope.liDis}" var="dis">
                                                                <c:if test="${cur.id == dis.proId}">
                                                                    <option value="${dis.id}" ${item.disId == dis.id ? 'selected="selected"' : ''}>
                                                                        ${dis.percent}%
                                                                    </option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td class="shoping__cart__total">
                                                        <c:set var="discountPercent" value="0"/>
                                                        <c:forEach items="${requestScope.liDis}" var="dis">
                                                            <c:if test="${cur.id == dis.proId && item.disId == dis.id}">
                                                                <c:set var="discountPercent" value="${dis.percent}"/>
                                                            </c:if>
                                                        </c:forEach>
                                                        $${cur.price * item.quantity * (1 - discountPercent / 100)}
                                                    </td>
                                                    <td class="shoping__cart__item__close">
                                                        <a href="cart?deleteId=${item.id}"><span class="icon_close"></span></a>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>

                               


                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__btns">
                            <a href="#" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                            <a href="#" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                                Upadate Cart</a>
                        </div>
                    </div>
                    <div class="col-lg-6">

                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <h5>Cart Total</h5>
                            <ul>
                                <li>Total <span>$${requestScope.totalCart}</span></li>
                            </ul>
                            <a href="checkout" class="primary-btn">PROCEED TO CHECKOUT</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shoping Cart Section End -->



        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>


    </body>

</html>