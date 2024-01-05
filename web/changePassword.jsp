<%-- 
    Document   : changePassword
    Created on : Oct 17, 2023, 8:43:54 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/changePassword_1.css"/>
        <title>JSP Page</title>
        <style>
            body{
                background-image: url(image/changePassword.png);
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
        </style>
    </head>
    <body>
        <div class="input-container">
            <c:set var="error" value="${requestScope.changeError}" />
            <c:set var="msg" value="${requestScope.msg}" />
            <h1 style="color: white">Thay Đổi Mật Khẩu</h1>
            <div class="input-form">
                <form  action="DispatchController" method="post">
                    <div class="input-items">
                        <label for="username">Nhập Vào Tên Tài Khoản<span>*</span></label><br>
                        <input type="text" id="username" name="username" value="${param.username}">
                    </div>
                    <c:if test="${not empty error.usernameExistedErr}" >
                        <p style="color: red">${error.usernameExistedErr}</p>
                    </c:if>
                    <div class="input-items">
                        <label for="newPass">Nhập Vào Mật Khẩu Mới<span>*</span></label><br>
                        <input type="password" id="newPass" name="newPass">
                    </div>
                    <c:if test="${not empty error.passwordLengthErr}" >
                        <p style="color: red">${error.passwordLengthErr}</p>
                    </c:if>
                    <div class="input-items">
                        <label for="reNewPass">Xác Nhận Mật Khẩu Mới<span>*</span></label><br>
                        <input type="password" id="reNewPass" name="reNewPass">
                    </div>
                    <c:if test="${not empty error.confirmNotMatchErr}" >
                        <p style="color: red">${error.confirmNotMatchErr}</p>
                    </c:if>
                    <div class="input-items">
                        <label for="email">Nhập Vào Email Đã Đăng Ký<span>*</span></label><br>
                        <input type="text" id="email" name="email" value="${param.email}">
                    </div>
                    <c:if test="${not empty error.emailInvalid}" >
                        <p style="color: red">${error.emailInvalid}</p>
                    </c:if>

                    <input class="change-button"
                           type="submit"
                           name="action"
                           value="Lưu Thay Đổi">
                </form>
            </div>
            <div class="backToHome-form">
                <p><span style="color: white">Hủy Thay Đổi ?</span></p>
                <form action="DispatchController" method="post">
                    <input
                        class="backToHome-button"
                        type="submit"
                        name="action"
                        value="Trở Về Trang Chủ"
                        />
                </form>
            </div>
            <c:if test="${not empty msg}" >
                <p style="color: greenyellow">${msg}</p>
            </c:if>
        </div>
    </body>
</html>
