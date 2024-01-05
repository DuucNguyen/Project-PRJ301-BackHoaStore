<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="models.accountsDTO" %>
<%@ page import="models.productsDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>BachHoaStore</title>
        <!--<link rel="stylesheet" href="css/style_1.css" />-->
    </head>

    <body>

        <%@include file="header.jsp" %> 
        <div class="row">
            <!-- Slideshow code -->
            <!-- <div class="container"> -->
            <div class="row banner-filter" style="padding-top: 80px">
                <div class="col-lg-8">
                    <!-- Add your slideshow code here -->
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ul>

                        <!-- Slides -->
                        <div class="carousel-inner slide-item">
                            <div class="carousel-item active">
                                <img src="image/KhuyenMai1.png" alt="Image 1" />
                            </div>
                            <div class="carousel-item slide-item">
                                <img src="image/KhuyenMai2.png" alt="Image 2" />
                            </div>
                            <div class="carousel-item slide-item">
                                <img src="image/KhuyenMai3.png" alt="Image 3" />
                            </div>
                        </div>

                        <!-- Controls -->
                        <a
                            class="carousel-control-prev"
                            href="#myCarousel"
                            role="button"
                            data-slide="prev"
                            >
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a
                            class="carousel-control-next"
                            href="#myCarousel"
                            role="button"
                            data-slide="next"
                            >
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 navigator_container">
                    <ul >
                        <li class="navigator_items"><a href="#Drink">
                                Đồ Uống <img src="image/drink.png" alt="drink_icon"/>
                            </a></li>
                        <li class="navigator_items"><a href="#Food" >
                                Đồ Ăn <img src="image/snack.png" alt="snack_icon"/>
                            </a></li>
                        <li class="navigator_items"><a href="#Season">
                                Gia Vị <img src="image/seasoning.png" alt="season_icon"/>
                            </a></li>
                        <li class="navigator_items"><a href="#Household">
                                Gia Dụng <img src="image/household.png" alt="household_icon"/>
                            </a></li>
                        <li class="navigator_items"><a href="#Others" >
                                Khác <img src="image/others.png" alt="others_icon"/>
                            </a></li>
                    </ul>
                </div>


            </div>

            <!--</div>   container-->
            <!-- end row banner-filter -->
            <!-- row products begin -->
            <c:set var="ListProduct" value="${requestScope.list}" />

            <div class="container" id="sections-container">
                <h1>Danh Mục Sản Phẩm</h1>

                <c:if test="${not empty ListProduct}" >
                    <div class="row row-product" id="Drink">
                        <h2>Đồ Uống</h2>
                        <div class="section">
                            <c:forEach items="${ListProduct}" var="p">
                                <c:if test="${p.category=='Drink'}" >
                                    <div class="product col-2">
                                        <form action="DispatchController" method="post">
                                            <input type="hidden" name="productID" value="${p.id}">
                                            <img src="${p.imgLocation}" alt="${p.name}"/>
                                            <p>${p.name}  ${p.description}</p>
                                            <p>${p.price}đ</p> 
                                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                                        </form>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row row-product" id="Food">
                        <h2>Đồ Ăn</h2>
                        <div class="section">
                            <c:forEach items="${ListProduct}" var="p">
                                <c:if test="${p.category=='Food'}" >
                                    <div class="product col-2">
                                        <form  action="DispatchController" method="post">
                                            <input type="hidden" name="productID" value="${p.id}">
                                            <img src="${p.imgLocation}" alt="${p.name}"/>
                                            <p>${p.name}  ${p.description}</p>
                                            <p>${p.price}đ</p> 
                                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                                        </form>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row row-product" id="Season">
                        <h2>Gia Vị</h2>
                        <div class="section">
                            <c:forEach items="${ListProduct}" var="p">
                                <c:if test="${p.category=='Season'}" >
                                    <div class="product col-2">
                                        <form  action="DispatchController" method="post">
                                            <input type="hidden" name="productID" value="${p.id}">
                                            <img src="${p.imgLocation}" alt="${p.name}"/>
                                            <p>${p.name}  ${p.description}</p>
                                            <p>${p.price}đ</p> 
                                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                                        </form>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row row-product" id="Household">
                        <h2>Gia Dụng</h2>
                        <div class="section">
                            <c:forEach items="${ListProduct}" var="p">
                                <c:if test="${p.category=='HouseHold Products'}" >
                                    <div class="product col-2">
                                        <form  action="DispatchController" method="post">
                                            <input type="hidden" name="productID" value="${p.id}">
                                            <img src="${p.imgLocation}" alt="${p.name}"/>
                                            <p>${p.name}  ${p.description}</p>
                                            <p>${p.price}đ</p> 
                                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                                        </form>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row row-product" id="Others">
                        <h2>Khác</h2>
                        <div class="section">
                            <c:forEach items="${ListProduct}" var="p">
                                <c:if test="${p.category=='Others'}" >
                                    <div class="product col-2">
                                        <form  action="DispatchController" method="post">
                                            <input type="hidden" name="productID" value="${p.id}">
                                            <img src="${p.imgLocation}" alt="${p.name}"/>
                                            <p>${p.name}  ${p.description}</p>
                                            <p>${p.price}đ</p> 
                                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                                        </form>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                </c:if>
                <a href="#top" class="back-to-top">
                    <img src="image/up.png" alt="up-to-top"/>
                </a>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
