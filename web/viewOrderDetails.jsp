<%-- 
    Document   : viewOrderDetails
    Created on : Oct 23, 2023, 10:01:25 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>Đăng Nhập Để Tiếp Tục</h1>
                <h1>Click <a href="login.jsp"> Vào Đây </a> Để Đăng Nhập !</h1>
            </c:if>
            <c:if test="${not empty account}" >

                <c:set var="listDetails" value="${requestScope.listDetails}" />
                <c:set var="order" value="${requestScope.order}" />
                <c:set var="msg" value="${requestScope.msg}" />

                <form action="DispatchController" method="post">
                    <c:if test="${account.isRole()}" >  <!-- admin-> do -->
                        <div class="order-details-commands">
                            <c:if test="${order.status ne 'Từ Chối Duyệt' && order.status ne 'Trả Lại Hàng'  && order.status ne 'Hủy'}" > <!--//reject/return/cancel - > do nothing--> 
                                <c:if test="${order.status ne 'Đã Phê Duyệt'}" > <!-- wait  -> approve & reject   -->
                                    <input class="btn btn-success" type="submit" name="action" value="Phê Duyệt Đơn Hàng">
                                    <p style="color: red; font-size: 18px">${msg}</p>
                                    <input class="btn btn-danger" type="submit" name="action" value="Từ Chối Đơn Hàng">
                                </c:if>

                            </c:if>
                            <c:if test="${order.status eq 'Đã Phê Duyệt'}" >
                                <h3 style="color: #28a745">Đơn Hàng Đã Được Duyệt</h3>
                            </c:if>
                            <c:if test="${order.status eq 'Từ Chối Duyệt'}" >
                                <h3 style="color: red">Đơn Hàng Đã Bị Từ Chối Duyệt</h3>
                            </c:if>
                            <c:if test="${order.status eq 'Hủy'}" >
                                <h3 style="color: red">Đơn Hàng Đã Bị Hủy</h3>
                            </c:if>
                        </div>
                    </c:if>
                    <c:if test="${!account.isRole()}" ><!--user-> do -->
                        <div class="order-details-commands">
                            <c:if test="${order.status ne 'Từ Chối Duyệt' && order.status ne 'Trả Lại Hàng' && order.status ne 'Hủy'}" > <!--//reject/return/cancel - > do nothing--> 
                                <c:if test="${order.status eq 'Đã Phê Duyệt'}" >
                                    <input class="btn btn-danger" type="submit" name="action" value="Trả Lại Hàng"> <!-- approved -> return  -->
                                </c:if>
                                <c:if test="${order.status ne 'Đã Phê Duyệt'}" >
                                    <input class="btn btn-danger" type="submit" name="action" value="Hủy Đơn Hàng"> <!-- wait  -> cancel  -->
                                </c:if>
                            </c:if>
                        </div>
                    </c:if>

                    <c:forEach items="${listDetails}" var="d">
                        <table border="1" class="table table-bordered table-hover">
                            <thead>
                                <tr class="table-info">
                                    <th><h5>Mã Đơn Hàng #${d.orderID}
                                            <input type="hidden" name="OrderID" value="${d.orderID}">
                                        </h5></th>
                                    <th><h5>Ảnh Mô Tả</h5></th>
                                    <th><h5>Tên Sản Phẩm</h5></th>
                                    <th><h5>Mô Tả</h5></th>
                                    <th><h5>Giá(1 sản phẩm)</h5></th>
                                    <th><h5>Số Lượng</h5></th>
                                    <th><h5>Tổng Hóa Đơn</h5></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="order-detail-container">
                                    <td><input type="hidden" name="productIDs" value="${d.productID}"></td>
                                    <td><img class="order-detail-img" src="${d.imgLocation}" alt="productPicture"/></td>
                                    <td><h4>${d.productName}</h4></td>
                                    <td><h4>${d.description}</h4></td>
                                    <td><h4>${d.unitPrice}đ</h4></td>
                                    <td><h4><input type="hidden" name="numberOfProducts" value="${d.quantity}">${d.quantity}</h4>
                                            <c:if test="${account.isRole()}" >
                                            <h6> Trong Kho: 
                                                <input type="hidden" name="storeQuantity" value="${d.storeQuantity}">${d.storeQuantity}
                                            </h6>
                                        </c:if>
                                    </td>
                                    <td><h4 style="color: red" >${d.totalPrice}đ</h4></td>
                                </tr>
                            </tbody>
                        </table>

                    </c:forEach>
                </c:if>

            </form>

        </div>
    </body>
</html>
