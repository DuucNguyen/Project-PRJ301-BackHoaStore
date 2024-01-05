<%-- 
    Document   : changeProductDetails
    Created on : Oct 28, 2023, 11:05:17 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container" style="padding-top: 100px">
            <c:set var="account" value="${sessionScope.account}" />
            <c:if test="${empty account}" >
                <h1>Từ Chối Truy Cập</h1>
                <h1>Ấn Vào <a href="login.jsp">Đây</a> Để Đăng Nhập</h1>
            </c:if>
            <c:if test="${not empty account}" >

                <c:set var="p" value="${requestScope.product}" />
                <c:set var="error" value="${requestScope.error}" />
                <c:set var="msg" value="${requestScope.msg}" />

                <%--<c:if test="${p.storeQuantity > 0 }" >--%>
                    <c:set var="msg" value="${requestScope.msg}" />

                    <h1 class="detail-title" style="background-color: #17a2b8">Chỉnh Sửa Chi Tiết Sản Phẩm</h1>
                    <div class="detail-container">
                        <img style="border: 10px solid #17a2b8" src="${p.imgLocation}" alt="${p.name}"/>
                        <div class="detail-text">
                            <form action="DispatchController" method="post">
                                <h2>ID Sản Phẩm: #${p.id}</h2>
                                <div class="detail-items">
                                    <label for="name">Tên Sản Phẩm: </label>
                                    <input type="text" name="name" id ="name" value="${p.name}">
                                    <p style="color: red">${error.nameLengthErr}</p>
                                </div>
                                <div class="detail-items">
                                    <label for="description">Mô Tả: </label>
                                    <input type="text" name="description" id ="description" value="${p.description}">
                                    <p style="color: red">${error.descriptionInvalidErr}</p>
                                </div>
                                <div class="detail-items">
                                    <label for="categoryID">Thể Loại: </label>
<!--                                    <input type="text" name="category" id ="categry" value="${p.category}">-->
                                    <select id="categoryID" name="categoryID">
                                        <option value="1">Drink</option>
                                        <option value="2">Food</option>
                                        <option value="3">Season</option>
                                        <option value="4">HouseHold Products</option>
                                        <option value="5">Others</option>
                                    </select>
                                    <p style="color: red">${error.categoryInvalidErr}</p>
                                </div>
                                <div class="detail-items">
                                    <label for="price">Giá(1 sản phẩm): </label>
                                    <input type="number" name="price" id ="price" value="${p.price}">
                                    <p style="color: red"> ${error.priceInvalidErr}</p>
                                </div>
                                <div class="detail-items">
                                    <label for="storeQuantity">Trong Kho: </label>
                                    <input type="number" name="storeQuantity" id ="storeQuantity" value="${p.storeQuantity}">
                                    <p style="color: red">${error.storeQuantiyInvalidErr}</p>
                                </div>
                                <div class="detail-items">
                                    <label for="imgLocation">Đường Dẫn Ảnh: </label>
                                    <input type="text" name="imgLocation" id ="imgLocation" value="${p.imgLocation}">
                                    <p style="color: red">${error.imgLocationinvalidErr}</p>
                                </div>
                                <div class="detail-items">
                                    <input class="btn btn-success change-button" type="submit" name="action" value="Xác Nhận">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <h5 style="color: green; margin-top: 25px;">${msg}</h5> 
                                </div>
                            </form>
                        </div>
                    </div>
                <%--</c:if>--%>

            </c:if>
        </div>
    </body>
</html>
