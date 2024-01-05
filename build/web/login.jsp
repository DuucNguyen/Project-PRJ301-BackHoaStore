<%-- 
    Document   : login
    Created on : Oct 14, 2023, 9:46:49 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css"/>
        <style>
            body{
                background-image: url(image/login_background.jpg);
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
        </style>
    </head>
    <body>

        <div class="login-form">
            <h1>Đăng Nhập</h1>
            <c:set var="msg" value="${requestScope.msg}"/>
            <c:if test="${msg!=null}">
                <p style="color: red">${msg}</p>
            </c:if>


            <div class="form-container">
                <form action="LoginServlet" method="Post">
                    <div class="input-items">
                        <label for="username">Tên Đăng Nhập</label>
                        <input type="text" id="username" name="username" placeholder="username" />
                    </div>

                    <div class="input-items">
                        <label for="password">Mật Khẩu</label>
                        <input type="password" id="password" name="password" placeholder="password" />
                    </div>
                    <a class="forgetPass-link" href="DispatchController?action=Quên+Mật+Khẩu">Quên Mật Khẩu ?</a>
                    <input
                        class="login-button"
                        type="submit"
                        name="action"
                        value="Đăng Nhập"
                        />
                </form>
            </div>
            <div class="register-form">
                <p><span>Bạn Chưa Có Tài Khoản ?</span></p>
                <form action="DispatchController">
                    <input
                        class="register-button"
                        type="submit"
                        name="action"
                        value="Đăng Ký Ngay"
                        />
                </form>
            </div>
        </div>
    </body>
</html>
