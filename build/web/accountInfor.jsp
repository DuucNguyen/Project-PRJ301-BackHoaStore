<%-- 
    Document   : accountInfor
    Created on : Oct 20, 2023, 9:10:53 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.accountsDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:set var="account" value="${sessionScope.account}" />
        <div class="container" style="padding-top: 100px">
            <c:if test="${empty account}" >
                <h1>Access Denied !</h1>
            </c:if>
            <c:if test="${not empty account}" >
                <table border="1" class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Tên Tài Khoản</th>
                            <th>Mật Khẩu</th>
                            <th>Email</th>
                            <th>Tên Đầy Đủ</th>
                            <th>Loại Tài Khoản</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${account.isRole()}" >
                            <tr class="table-danger">
                            </c:if>
                            <c:if test="${account.isRole()==false}" >
                            <tr class="table-success">
                            </c:if>
                            <td>${account.getUsername()}</td>
                            <td>${account.getPassword()}</td>
                            <td>${account.getEmail()}</td>
                            <td>${account.getFullName()}</td>
                            <td>${account.role?'ADMIN':'Khách Hàng'}</td>
                        </tr>
                    </tbody>
                </table>
                <form action="DispatchController" method="post">
                    <input type="submit" class="btn btn-success" name="action" value="Thay Đổi Mật Khẩu">
                    <input type="submit" class="btn btn-info" name="action" value="Cập Nhật Thông Tin">
                </form>
            </div>
        </c:if>

    </body>
</html>
