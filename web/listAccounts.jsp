<%-- 
    Document   : listAccounts
    Created on : Oct 16, 2023, 10:39:28 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>listAccountPage</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.account}" />
        <c:if test="${empty account}" >
            <h1>Access Denied !</h1>
        </c:if>
        <c:if test="${not empty account}" >
            <%@include file="header.jsp" %>
            <c:set var="list" value="${requestScope.list}" />
            <c:if test="${not empty list}" >
                <div class="container table-account" style="padding-top: 80px">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="a">

                            <form action="DispatchController" method="Post">
                                <c:if test="${a.isRole()}">
                                    <tr class="table-danger">
                                    </c:if>

                                    <c:if test="${!a.isRole()}" >
                                    <tr class="table-success">
                                    </c:if>

                                    <td><input type="hidden" name="username" value="${a.username}">${a.username}</td>
                                    <td>${a.password}</td>
                                    <td>${a.fullName}</td>
                                    <td>${a.email}</td>
                                    <c:if test="${a.isRole()}" >
                                        <td>
                                            ADMIN   <input type="checkbox" name="role" value="ADMIN" checked="checked">
                                        </td>
                                    </c:if>
                                    <c:if test="${!a.isRole()}" >
                                        <td>
                                            ADMIN   <input type="checkbox" name="role" value="ADMIN">
                                        </td>
                                    </c:if>
                                    <td><input type="submit" name="action" value="Delete"></td>
                                    <td><input type="submit" name="action" value="Update"></td>
                                </tr>
                            </form>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </c:if>
    </body>
</html>
