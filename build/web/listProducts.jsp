<%-- 
    Document   : listProducts
    Created on : Oct 28, 2023, 9:20:03 AM
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
        <div  style="padding-top: 100px; max-width: 1400px; margin: 0 auto;">
            <c:set var="account" value="${sessionScope.account}" />
            <c:if test="${empty account}" >
                <h1>Đăng Nhập Để Tiếp Tục</h1>
                <h1>Click <a href="login.jsp"> Vào Đây </a> Để Đăng Nhập !</h1>
            </c:if>
            <c:if test="${not empty account}" >
                <c:set var="listProducts" value="${requestScope.list}" />
                <c:if test="${not empty listProducts}" >
                    <table border="1" class="table table-bordered table-hover">
                        <thead>
                            <tr class="table-primary">
                                <th><h5>Mã Sản Phẩm</h5></th>
                                <th><h5>Hình Ảnh</h5></th>
                                <th><h5>Tên Sản Phẩm</h5></th>
                                <th><h5>Thể Loại</h5></th>
                                <th><h5>Mô Tả</h5></th>
                                <th><h5>Giá(1 sản phẩm)</h5></th>
                                <th><h5>Trong Kho</h5></th>
                                <th><h5></h5></th>
                                <th><h5></h5></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProducts}" var="p">
                                <tr class="products-detail-container">
                                    <td><h4>#${p.id}</h4></td>
                                    <td class="products-detail-img"><img src="${p.imgLocation}" alt="${p.name}"/></td>
                                    <td><h4>${p.name}</h4></td>
                                    <td><h4>${p.category}</h4></td>
                                    <td><h4>${p.description}</h4></td>
                                    <td><h4 style="color: red">${p.price}đ</h4></td>
                                    <td><h4>${p.storeQuantity}</h4></td>
                                    <td>
                                        <form action="DispatchController" method="post">
                                            <input class="btn btn-success" type="submit" name="action" value="Chỉnh Sửa">
                                            <input type="hidden" name="productID" value="${p.id}">
                                        </form>
                                    </td>
                                    <td>
                                        <form action="DispatchController" method="post">
                                            <input class="btn btn-danger" type="submit" name="action" value="Xóa">
                                            <input type="hidden" name="productID" value="${p.id}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <div style="text-align: center; margin: 20px 0">
                        <form action="DispatchController" method="post">
                            <input class="btn btn-success" type="submit" name="action" value="Sản Phẩm Mới">
                        </form>
                    </div>
                </c:if>
            </c:if>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
