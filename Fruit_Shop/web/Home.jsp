<%-- 
    Document   : Home
    Created on : Jul 9, 2024, 9:44:43 AM
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
        <style>
            .pagination a.active {
                font-weight: bold;
                color: red; /* or any color you prefer */
            }

        </style>
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <section class="hero">
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
                                    <c:forEach items="${requestScope.liCa}" var="ca">
                                    <li><a href="homepage?caId=${ca.id}">${ca.name}</a></li>
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
                        <div class="hero__item set-bg" data-setbg="img/hero/banner.jpg">
                            <div class="hero__text">
                                <span>FRUIT FRESH</span>
                                <h2>Vegetable <br />100% Organic</h2>
                                <p>Free Pickup and Delivery Available</p>
                                <a href="#" class="primary-btn">SHOP NOW</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Header Section End -->
        <section class="categories">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Sale Product!!!!</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="categories__slider owl-carousel">
                        <c:forEach items="${requestScope.li5sale}" var="lisal">
                            <div class="col-lg-3">
                                <div class="categories__item set-bg" data-setbg="${lisal.img}" style="border-radius: 30px">
                                    <h5><a href="productBuy?proId=${lisal.id}">${lisal.name}</a></h5>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </section>
        <!-- Hero Section Begin -->

        <!-- Hero Section End -->

        <!-- Featured Section Begin -->
        <section class="featured spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Featured Product</h2>
                        </div>
                    </div>
                </div>
                <div class="row featured__filter">
                    <c:forEach items="${requestScope.listPr}" var="product">
                        <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                            <div class="featured__item">
                                <div style="border-radius: 20px" class="featured__item__pic set-bg" data-setbg="${product.img}">
                                    <ul class="featured__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="addProCart?addPrId=${product.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="featured__item__text">
                                    <h6><a href="productBuy?proId=${product.id}">${product.name}</a></h6>
                                    <h5>$${product.price}</h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="homepage?page=${currentPage - 1}">Previous</a>
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="page">
                        <a href="homepage?page=${page}" class="${page == currentPage ? 'active' : ''}">${page}</a>
                    </c:forEach>
                    <c:if test="${currentPage < noOfPages}">
                        <a href="homepage?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- Featured Section End -->


        <!-- Banner End -->

        <!-- Latest Product Section Begin -->
        <section class="latest-product spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="latest-product__text">
                            <h4>Latest Products</h4>
                            <div class="latest-product__slider owl-carousel">
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach begin="0" end="2" items="${requestScope.li5new}" var="li5n">
                                        <a href="productBuy?proId=${li5n.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${li5n.img}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${li5n.name}</h6>
                                                <span>$${li5n.price}</span>
                                            </div>
                                        </a>
                                    </c:forEach> 
                                </div>
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach begin="3" end="4" items="${requestScope.li5new}" var="li5n">
                                        <a href="productBuy?proId=${li5n.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${li5n.img}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${li5n.name}</h6>
                                                <span>$${li5n.price}</span>
                                            </div>
                                        </a>
                                    </c:forEach> 

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">

                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="latest-product__text">
                            <h4>Most sale product</h4>
                            <div class="latest-product__slider owl-carousel">
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach begin="0" end="2" items="${requestScope.liMoSale}" var="li5n">
                                        <a href="productBuy?proId=${li5n.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${li5n.img}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${li5n.name}</h6>
                                                <span>$${li5n.price}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach begin="3" end="4" items="${requestScope.liMoSale}" var="li5n">
                                        <a href="productBuy?proId=${li5n.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${li5n.img}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${li5n.name}</h6>
                                                <span>$${li5n.price}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">

                    </div>
                </div>
            </div>
        </section>
        <!-- Latest Product Section End -->

        
        <!-- Footer Section End -->

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