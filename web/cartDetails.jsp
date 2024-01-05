<%-- 
    Document   : cartDetails
    Created on : Oct 24, 2023, 9:57:05 PM
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
        <c:set var="account" value="${sessionScope.account}" />
        <c:if test="${empty account}" >
            <h1>Từ Chối Truy Cập !</h1>
            <h1>Click <a href="login.jsp">Vào Đây </a> Để Đăng Nhập </h1>
        </c:if>
        <c:if test="${not empty account}" >
            <c:set var="cartItem" value="${requestScope.cartItem}" />
            <c:if test="${not empty cartItem}" >
                
            </c:if>
        </c:if>


    </body>
</html>
