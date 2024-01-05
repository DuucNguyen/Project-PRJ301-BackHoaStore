<%-- 
    Document   : changeInfor
    Created on : Oct 20, 2023, 10:00:07 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.accountsDTO" %>
<%@page import="models.RegistrationError" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.account}" />
        <c:set var="error" value="${requestScope.error}" />
        <c:set var="msg" value="${requestScope.msg}" />
        <%@include file = "header.jsp" %>
        <div class="container" style="padding-top: 100px;">
            <c:if test="${empty account}" >
                <h1>Access Denied !</h1>
            </c:if>
            <c:if test="${not empty account}" >
                <h2>Thay Đổi Thông Tin Tài Khoản - <span style="color: red">${account.username}</span> - </h2>
                <form class="input-form" action="DispatchController" method="post">
                    <input type="hidden" name="username" value="${account.username}">

                    <div class="input-items">
                        <label for="email">Thay Đổi Email: </label>
                        <input id="email" type="text" name="new_email" value="${account.email}">
                        <c:if test="${not empty error.emailInvalid}" >
                            <p style="color: red">${error.emailInvalid}</p>
                        </c:if>
                        <br>
                    </div>

                    <div class="input-items">
                        <label for="fullname">Thay Đổi Tên Đầy Đủ: </label>
                        <input id="fullname" type="text" name="new_fullName" value="${account.fullName}">
                        <c:if test="${not empty error.fullNameLengthErr}" >
                            <p style="color: red">${error.fullNameLengthErr}</p>
                        </c:if>
                        <br>
                    </div>

                    <div class="input-items">
                        <label for="password">Nhập Mật Khẩu: </label>
                        <input type="password" name="password">
                        <c:if test="${not empty error.passwordLengthErr}" >
                            <p style="color: red">${error.passwordLengthErr}</p>
                        </c:if>
                        <c:if test="${not empty error.confirmNotMatchErr}" >
                            <p style="color: red">${error.confirmNotMatchErr}</p>
                        </c:if>
                        <br>
                    </div>


                    <input class="btn btn-danger" type="submit" name="action" value="Xác Nhận Thay Đổi">

                </form>
                <c:if test="${not empty msg}" >
                    <p style="color: blue">${msg}</p>
                    Vui Lòng Nhấn Vào <a href="login.jsp">Đây</a> Để Đăng Nhập Lại
                </c:if>
            </c:if>
        </div>
    </body>
</html>
