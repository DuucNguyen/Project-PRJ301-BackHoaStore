<%-- 
    Document   : viewOrders
    Created on : Oct 23, 2023, 8:48:32 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.*" %>

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
                <h1>Từ Chối Truy Cập !</h1>
                <h1>Click <a href="login.jsp">Vào Đây </a> Để Đăng Nhập </h1>
            </c:if>
            <c:if test="${not empty account}" >
                <c:set var="listOrders" value="${requestScope.listOrders}" />
                <c:if test="${not empty listOrders}" >
                    <c:forEach items="${listOrders}" var="o">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr class="table-warning">
                                    <th><h5>Mã Đơn Hàng: #${o.orderID}</h5></th>
                                    <th><h5>Người Đặt</h5></th>
                                    <th><h5>Ngày Đặt</h5></th>
                                    <th><h5>Ngày Phê Duyệt</h5></th>
                                    <th><h5>Trạng Thái Đơn Hàng</h5></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="order-info">
                                    <td></td>
                                    <td><h4>${account.fullName}</h4></td>
                                    <td><h4>${o.orderDate}</h4></td>
                                    <td>
                                        <c:if test="${empty o.approvedDate}" ><h4>Chưa Phê Duyệt</h4></c:if>
                                        <h4>${o.approvedDate}</h4>
                                    </td>
                                    <td> 
                                        <h4
                                            <c:if test="${o.status eq 'Chờ Phê Duyệt'}" >
                                                style="color: orange"
                                            </c:if>
                                            <c:if test="${o.status eq 'Đã Phê Duyệt'}" >
                                                style="color: #28a745"
                                            </c:if>
                                            <c:if test="${o.status eq 'Trả Lại Hàng'}" >
                                                style="color: darkviolet"
                                            </c:if>
                                            <c:if test="${o.status ne 'Đã Phê Duyệt'}" >
                                                style="color: red"
                                            </c:if> 
                                            >${o.status}
                                        </h4>
                                    </td>
                                    <td>
                                        <form action="DispatchController" method="post">
                                            <input type="hidden" name="orderID" value="${o.orderID}">
                                            <input class="btn btn-info" type="submit" name="action" value="Chi Tiết Đơn Hàng">
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </c:forEach>
                </c:if>
                <c:if test="${empty listOrders}" >
                    <h1>Bạn Chưa Có Đơn Hàng Nào</h1>
                    <form action="DispatchController" method="post">
                        <h1>Đi Đến <input style="font-size: 30px" class="btn btn-info" type="submit" name="action" value="Giỏ Hàng"> Để Đặt Hàng Nhé !</h1>
                    </form>
                </c:if>
            </c:if>
        </div>
    </body>
</html>
