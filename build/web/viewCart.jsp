<%-- 
    Document   : viewCart
    Created on : Oct 21, 2023, 8:52:52 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.CartDTO" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container" style="padding-top: 100px">
            <form action="DispatchController" method="post">
                <c:set var="account" value="${sessionScope.account}" />

                <c:if test="${empty account}" >
                    <h1>Từ Chối Truy Cập !</h1>
                    <h1>Click <a href="login.jsp">Vào Đây </a> Để Đăng Nhập </h1>
                </c:if>

                <c:if test="${not empty account}" >
                    <c:set var="listCart" value="${requestScope.listCart}" />
                    <c:if test="${empty listCart}" >
                        <h1>Giỏ Hàng Của Bạn Trống Không</h1>
                        <h1>Trở Về <a href="DispatchController">Trang Chủ</a> Để Đặt Hàng Nhé !</h1>
                    </c:if>
                    <c:if test="${not empty listCart}" >
                        <table border="1" style="width: 100%" class="table table-bordered table-hover">
                            <thead>
                                <tr class="table-warning">
                                    <th></th>
                                    <th><h5>Ảnh Sản Phẩm</h5></th>
                                    <th><h5>Tên Sản Phẩm</h5></th>
                                    <th><h5>Mô Tả</h5></th>
                                    <th><h5>Giá (1 sản phẩm)</h5></th>
                                    <th><h5>Số Lượng</h5></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <div class="items-cart-container">

                                <c:set var="item" value="0" />
                                <c:forEach items="${listCart}" var="c" step="1">
                                    <tr>
                                        <td><div class="cart-items">
                                                <c:set var="item" value="${item+1}" />
                                                <input type="checkbox" 
                                                       class="checkbox" 
                                                       id="item${item}" 
                                                       name="isChecked" 
                                                       value="${c.id} ${c.numberOfProducts}" >
                                                <!--<input type="hidden" name="numberOfProducts" value="${c.numberOfProducts}">-->
                                                <label hidden for="item${item}">${c.price}</label>
                                            </div></td>
                                        <td><div class="cart-items">
                                                <img src="${c.imgLocation}" alt="${c.name}"/>
                                            </div></td>
                                        <td><div class="cart-items">
                                                <h4>${c.name}</h4>
                                            </div></td>
                                        <td><div class="cart-items">
                                                <h4>${c.description}</h4>
                                            </div></td>
                                        <td><div class="cart-items">
                                                <h4>${c.price}</h4>
                                            </div></td>
                                    <form action="DispatchController">

                                        <td><div class="cart-items">
                                                <h4><input class="detail-input" 
                                                           type="number" 
                                                           name="numberOfProducts" 
                                                           value="${c.numberOfProducts}" 
                                                           min="1" 
                                                           max="${c.storeQuantity}"
                                                           ></h4>
                                            </div></td>
                                        <td>
                                            <div class="cart-items" style="margin-top: 40px;">
                                                <input class="btn btn-danger" type="submit" name="action" value="Sửa">
                                                <input type="hidden" name="pid" value="${c.id}">
                                            </div></td>
                                    </form>
                                    </tr>
                                </c:forEach>
                            </div>
                            </tbody>
                        </table>
                        <div class="cashier">
                            <div>${requestScope.msg}</div>
                            <div id="total">Số Đơn Hàng: </div>
                            <input class="cart-buy-button" type="submit" name="action" value="Mua Hàng">
                        </div>
                    </c:if>
                </c:if>
            </form>
        </div>


        <!-- Add this JavaScript block after your HTML code -->

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const checkboxes = document.querySelectorAll('input[type="checkbox"]');
                const totalAmount = document.getElementById("total");

                checkboxes.forEach(function (checkbox) {
                    checkbox.addEventListener("change", updateTotalAmount);
                });

                function updateTotalAmount() {
                    let total = 0;

                    checkboxes.forEach(function (checkbox) {
                        if (checkbox.checked) {
//                            const label = checkbox.nextElementSibling;
//                            const labelText = label.innerText;
//                            const price = parseFloat(labelText); // Assuming label text is in a format like "1000đ"
                            total += 1;
                        }
                    });
                    totalAmount.textContent = "Số Đơn Hàng: " + total;
//                    console.log(total);
//                    console.log(totalAmount.textContent);
                }
                updateTotalAmount();
            });
        </script>



    </body>
</html>
