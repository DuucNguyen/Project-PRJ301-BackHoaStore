<%-- 
Document   : header
Created on : Oct 8, 2023, 5:06:29 PM
Author     : admin
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "models.accountsDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>BachHoaStore</title>

        <!-- Add Bootstrap CSS -->
        <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style_1.css"/>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.account}" />

        <nav class="navbar navbar-expand-lg">
            <!-- Container wrapper -->
            <!-- <div class="container"> -->
            <!-- Navbar brand -->
            <a class="navbar-brand" href="DispatchController"
               ><img
                    id="logo"
                    src="image/BackHoa_Logo.png"
                    alt="BackHoalogo"
                    draggable="false"
                    height="50"
                    /></a>

            <!-- Collapsible wrapper -->
            <div
                class="collapse navbar-collapse search-bar"
                id="navbarSupportedContent"
                >
                <form class="form-search" action="DispatchController" method="Post">
                    <div class="input-group">
                        <input class="header-navigator-button" type="submit" name="action" value="Tất Cả Sản Phẩm">
                        <input class="header-navigator-button" type="submit" name="action" value="Về Chúng Tôi">
                    </div>
                </form>
            </div>
            <!--Login-->
            <c:if test="${not empty account}" >
                <div class="dropdown">
                    <c:if test="${account.isRole()}" >
                        <button
                            class="btn btn-outline-danger dropdown-toggle"
                            type="button"
                            data-toggle="dropdown"
                            >
                            ${account.fullName}
                        </button>
                    </c:if>
                    <c:if test="${!account.isRole()}" >
                        <button
                            class="btn btn-outline-success dropdown-toggle"
                            type="button"
                            data-toggle="dropdown"
                            >
                            ${account.fullName}
                        </button>
                    </c:if>

                    <div class="dropdown-menu">
                        <c:if test="${account.isRole()}" >
                            <form action="DispatchController" method="post">
                                <input
                                    class="dropdown-item"
                                    type="submit"
                                    name="action"
                                    value="Quản Lí Tài Khoản"
                                    />
                            </form>

                            <form action="DispatchController" method="post">
                                <input
                                    class="dropdown-item"
                                    type="submit"
                                    name="action"
                                    value="Quản Lí Sản Phẩm"
                                    />
                            </form>

                            <form action="DispatchController" method="post">
                                <input 
                                    class="dropdown-item" 
                                    type="submit" 
                                    name="action" 
                                    value="Quản Lí Đơn Hàng"
                                    />
                            </form>

                        </c:if>

                        <form action="DispatchController" method="post">
                            <input
                                class="dropdown-item"
                                type="submit"
                                name="action"
                                value="Tài Khoản"
                                />
                        </form>

                        <form action="DispatchController" method="post">
                            <input
                                class="dropdown-item"
                                type="submit"
                                name="action"
                                value="Giỏ Hàng"
                                />
                        </form>

                        <form action="DispatchController" method="post">
                            <input 
                                class="dropdown-item" 
                                type="submit" 
                                name="action" 
                                value="Đơn Hàng"
                                />
                        </form>

                        <form action="DispatchController" method="post">
                            <input 
                                class="dropdown-item"
                                type="submit"
                                name="action"
                                value="Đăng Xuất"
                                />
                        </form>
                    </div>

                </div>
            </c:if>
            <!----AuthenticationCheck- end-->

            <c:if test="${empty account}" >
                <a class="header-link" href="login.jsp">Đăng Nhập</a>
                <a class="header-link" href="registration.jsp">Đăng Ký</a>
            </c:if>
            <!-- Collapsible wrapper -->
            <!-- </div> -->
            <!-- Container wrapper -->
        </nav>
        <!-- Navbar -->

        <!-- Your navbar code goes here -->
    </body>
</html>
