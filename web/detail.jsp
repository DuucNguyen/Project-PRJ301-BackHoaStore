<%-- 
    Document   : detail
    Created on : Oct 18, 2023, 12:41:14 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.productsDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="padding-top: 100px" class="container">
            <c:set var="account" value="${sessionScope.account}" />
            <c:if test="${empty account}" >
                <h1>Từ Chối Truy Cập</h1>
                <h1>Ấn Vào <a href="login.jsp">Đây</a> Để Đăng Nhập</h1>
            </c:if>
            <c:if test="${not empty account}" >
                <c:set var="p" value="${requestScope.product}" />
                <c:if test="${p.storeQuantity == 0 }" >
                    <h1>Mặt Hàng Này Đã Bán Hết</h1>
                    <h1>Vui Lòng Trở Về <a href="DispatchController"> Trang Chủ </a> Để Chọn Mặt Hàng Khác !</h1>
                </c:if>
                <c:if test="${p.storeQuantity > 0 }" >
                    <c:set var="msg" value="${requestScope.msg}" />

                    <h1 class="detail-title" >Thông Tin Chi Tiết Sản Phẩm</h1>
                    <div class="detail-container">
                        <img src="${p.imgLocation}" alt="${p.name}"/>
                        <div class="detail-text">
                            <form action="DispatchController" method="post">
                                <h1>Tên Sản Phẩm : ${p.getName()}</h1>
                                <h3>Mô Tả: ${p.getDescription()}</h3>
                                <h3>Giá Sản Phẩm : <span>${p.getPrice()}đ</span></h3>
                                <input type="hidden" name="username" value="${account.getUsername()}">
                                <input type="hidden" name="productID" value="${p.getId()}"><br>
                                <label class="detail-label">Số Lượng:  </label><input class="detail-input" type="number" name="numberOfProduct" value="1" min="1"><br>
                                <h6>Trong Kho: ${p.getStoreQuantity()}</h6>
                                <input class="btn btn-danger addToCart-button" type="submit" name="action" value="Thêm Sản Phẩm">
                                <c:if test="${not empty msg}" >
                                    <h5 style="margin-top: 15px; color: green">${msg}</h5>  
                                </c:if>
                            </form>
                        </div>
                    </div>
                </c:if>
            </c:if>
        </div>
    </body>
</html>
