<%-- 
    Document   : registration
    Created on : Oct 14, 2023, 10:05:56 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.RegistrationError" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RegistrationPage</title>
        <link rel="stylesheet" href="css/registration_1.css"/>
        <style>
            body{
                background-image: url(image/register.jpg);
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
        </style>
    </head>
    <body>

        <div class="register-form">
            <c:set var="err" value="${requestScope.InsertErr}" />
            <c:set var="msg" value="${requestScope.msg}" />
            <h1 style="color: white">Đăng Ký Tài Khoản</h1>
            <div class="form-container">
                <form action="RegistrationServlet" method="Post">
                    <div class="input-items">
                        <label for="username">Tên Đăng Nhập</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            placeholder="Username"
                            value="${param.username}"
                            />
                    </div>
                    <c:if test="${not empty err.usernameLengthErr}" >
                        <p style="color: red" >${err.usernameLengthErr}</p>
                    </c:if>  
                    <c:if test="${not empty err.usernameExistedErr}" >
                        <p style="color: red" >${err.usernameExistedErr}</p>
                    </c:if>

                    <div class="input-items">
                        <label for="password">Mật Khẩu</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            placeholder="Password"
                            />
                    </div>
                    <c:if test="${not empty err.passwordLengthErr}" >
                        <p style="color: red">${err.passwordLengthErr}</p>
                    </c:if> 


                    <div class="input-items">
                        <label for="confirmPass">Nhập Lại Mật Khẩu</label>
                        <input
                            type="password"
                            id="confirmPass"
                            name="confirmPass"
                            placeholder="reEnter-password"
                            />
                    </div>
                    <c:if test="${not empty err.confirmNotMatchErr}" >
                        <p style="color: red">${err.confirmNotMatchErr}</p>
                    </c:if> 


                    <div class="input-items">
                        <label for="email">Email: </label>
                        <input
                            type="text"
                            id="email"
                            name="email"
                            placeholder="Email"
                            value="${param.email}"
                            />
                    </div>
                    <c:if test="${not empty err.emailInvalid}" >
                        <p style="color: red">${err.emailInvalid}</p>
                    </c:if>


                    <div class="input-items">
                        <label for="fullName">Tên Đầy Đủ</label>
                        <input
                            type="text"
                            id="fullName"
                            name="fullName"
                            placeholder="Full Name"
                            value="${param.fullName}"
                            />
                    </div>
                    <c:if test="${not empty err.fullNameLengthErr}" >
                        <p style="color: red">${err.fullNameLengthErr}</p>
                    </c:if> 

                    <a class="forgetPass-link" href="DispatchController?action=Quên+Mật+Khẩu">Quên Mật Khẩu ?</a>
                    <input
                        class="register-button"
                        type="submit"
                        name="action"
                        value="Đăng Ký Tài Khoản"
                        />
                </form>


            </div>
            <div class="login-form">
                <p><span style="color: white">Bạn Đã Có Tài Khoản ?</span></p>
                <form action="DispatchController">
                    <input
                        class="login-button"
                        type="submit"
                        name="action"
                        value="Đăng Nhập"
                        />
                </form>
                <c:if test="${not empty msg}" >
                    <p style="color: greenyellow">
                        ${msg}
                    </p>
                </c:if>
            </div>
        </div>
    </body>
</html>
