<%-- 
    Document   : Shop_grid.jsp
    Created on : Jul 9, 2024, 9:33:53 AM
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

        <jsp:include  page="Menu.jsp"></jsp:include>
            <!-- Humberger Begin -->

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
        <!-- Hero Section End -->

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Organi Shop</h2>
                            <div class="breadcrumb__option">
                                <a href="MenuHome.jsp">Home</a>
                                <span>Shop</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Product Section Begin -->
        <section class="product spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-5">
                        <div class="sidebar">
                            <div class="sidebar__item">
                                <h4>Category</h4>
                                <ul>

                                    <li><a href="shopgrid?caId=-1">All Category</a></li> <!-- Correct closing tag -->
                                        <c:forEach items="${requestScope.liCa}" var="Ca">
                                        <li><a href="shopgrid?caId=${Ca.id}">${Ca.name}</a></li>
                                        </c:forEach>

                                </ul>
                            </div>
                            <div class="sidebar__item">
                                <div class="price-range-wrap">
                                    <form action="shopgrid?caId=${requestScope.caId}" method="post">
                                        <h4>Price</h4>
                                        <div class="d-flex align-items-center mt-4 pb-1">
                                            <div class="md-form md-outline my-0">
                                                <input type="number" id="from" name="from" class="form-control mb-0" placeholder="From">
                                            </div>
                                            <p class="px-2 mb-0 text-muted"> - </p>
                                            <div class="md-form md-outline my-0">
                                                <input type="number" id="to" name="to" class="form-control mb-0" placeholder="To">
                                            </div>
                                        </div>
                                        <div class="card-footer bg-dark text-white text-uppercase">
                                            <input type="submit" value="Find" class="btn btn-success">
                                        </div>
                                    </form>
                                </div>

                            </div>
                            <div class="sidebar__item">
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
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-7">
                        <div class="product__discount">
                            <div class="section-title product__discount__title">
                                <h2>Sale Off</h2>
                            </div>
                            <div class="row">
                                <div class="product__discount__slider owl-carousel">
                                    <c:forEach items="${requestScope.li5sale}" var="lisal">
                                        <c:forEach items="${requestScope.liDi}" var="lidi">
                                            <c:if test="${lisal.id == lidi.proId}">
                                                <div class="col-lg-4">
                                                    <div class="product__discount__item">
                                                        <div class="product__discount__item__pic set-bg" data-setbg="${lisal.img}">
                                                            <div class="product__discount__percent">${lidi.percent}%</div>
                                                            <ul class="product__item__pic__hover">
                                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                                <li><a href="addProCart?addPrId=${lisal.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="product__discount__item__text">
                                                            <h5><a href="productBuy?proId=${lisal.id}">${lisal.name}</a></h5>
                                                            <div class="product__item__price">$${lisal.price * (1 - lidi.percent / 100)} <span>$${lisal.price}</span></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                        <div class="filter__item">
                            <div class="row">
                                <div class="col-lg-4 col-md-5">
                                    <div class="filter__sort">
                                        <form action="shopgrid" method="post">
                                            <span>Sort By</span>
                                            <select>
                                                <option value="0">Price</option>
                                                <option value="0">Name</option>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <form action="shopgrid?caId=${requestScope.caId}" method="post">
                                        <input type="text" placeholder="What do yo u need?" name="search">
                                        <input type="submit" value="Seach" class="btn-success">
                                    </form>
                                </div>
                                <div class="col-lg-4 col-md-3">
                                    <div class="filter__option">
                                        <span class="icon_grid-2x2"></span>
                                        <span class="icon_ul"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach items="${requestScope.listPr}" var="product">
                                <div class="col-lg-4 col-md-6 col-sm-6">

                                    <div class="product__item">
                                        <div class="product__item__pic set-bg" data-setbg="${product.img}">
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="addProCart?addPrId=${product.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__item__text">
                                            <h6><a href="productBuy?proId=${product.id}">${product.name}</a></h6>
                                            <h5>$${product.price}</h5>
                                        </div>
                                    </div>

                                </div>
                            </c:forEach> 
                        </div>

                        <div class="pagination">

                            <c:if test="${currentPage > 1}">
                                <a href="shopgrid?page=${currentPage - 1}&caId=${caId}">Previous</a>
                            </c:if>
                            <table border="1">
                                <c:forEach begin="1" end="${noOfPages}" var="page">
                                    <td><a href="shopgrid?page=${page}&caId=${caId}" class="${page == currentPage ? 'active' : ''}">${page}</a>
                                    </c:forEach></td>
                            </table>

                            <c:if test="${currentPage < noOfPages}">
                                <a href="shopgrid?page=${currentPage + 1}&caId=${caId}">Next</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Product Section End -->



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
