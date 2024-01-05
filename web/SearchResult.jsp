<%-- 
    Document   : SearchResult
    Created on : Oct 16, 2023, 3:38:53 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <c:set var="list" value="${requestScope.list}" />
        <%--<c:set var="account" value="${sessionScope.account}" />--%>

        <div class="container" style="padding-top: 100px">

            <form oninput="searchByChange()" 
                  onchange="searchByChange()" >
                <div class="search-bar-container">
                    <div class="input-group mb-3 bothOfThem">
                        <div class="input-group-prepend">
                            <label class="input-group-text">Tìm Kiếm</label>
                        </div>
                        <input 
                            class="form-control result-searchBar"
                            id ="txtSearch"
                            type="text" 
                            name="txt" 
                            value="${txt}"
                            placeholder="Nhập..."
                            >
                    </div>
                    <div class="input-group mb-3 select">
                        <div class="input-group-prepend">
                            <label class="input-group-text">Theo Giá</label>
                        </div>
                        <select 
                            class="custom-select"
                            id="priceInput"
                            name="price_txt"
                            >
                            <option value="0">Mặc Định</option>
                            <option value="1">Dưới 10.000đ</option>
                            <option value="2">10.000đ - 20.000đ</option>
                            <option value="3">20.000đ - 50.000đ</option>
                            <option value="4">50.000đ - 100.000đ</option>
                            <option value="5">Trên 100.000đ</option>
                        </select>
                    </div>
                    <div class="input-group mb-3 select">
                        <div class="input-group-prepend">
                            <label class="input-group-text">Thứ Tự</label>
                        </div>
                        <select 
                            class="custom-select"
                            id="orderInput"
                            name="order_txt"
                            >
                            <option value="0">Mặc Định</option>
                            <option value="ASC">Từ Thấp Đến Cao</option>
                            <option value="DESC">Từ Cao Đến Thấp</option>
                            <option value="BestSeller">Best Seller</option>
                        </select>
                    </div>
                </div>
            </form>

            <div class="row" id="content">
                <c:forEach items="${list}" var="p">
                    <div class="product col-2">
                        <form action="DispatchController" method="post">
                            <input type="hidden" name="productID" value="${p.id}">
                            <img src="${p.imgLocation}" alt="${p.name}"/>
                            <p>${p.name}  ${p.description}</p>
                            <p>${p.price}đ</p> 
                            <input class="btn btn-success products-button" type="submit" name="action" value="Thêm Vào Giỏ Hàng">
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                      function searchByName(param) {
                          var txtSearch = param.value;

                          $.ajax({
                              url: "/Project_PRJ301/SearchServletByAJAX",
                              type: "get", //send it through get method
                              data: {
                                  txt: txtSearch
                              },
                              success: function (data) {
                                  var row = document.getElementById("content");
                                  row.innerHTML = data;
                              },
                              error: function (xhr) {
                                  //Do Something to handle error
                              }
                          });
                      }
                      function searchByChange() {
                          var txtSearch = document.getElementById("txtSearch").value;
                          var price = document.getElementById("priceInput").value;
                          var order = document.getElementById("orderInput").value;
                          $.ajax({
                              url: "/Project_PRJ301/SearchServletByAJAX",
                              type: "get", //send it through get method
                              data: {
                                  txt: txtSearch,
                                  price_raw: price,
                                  order_raw: order
                              },
                              success: function (data) {
                                  var row = document.getElementById("content");
                                  row.innerHTML = data;
                              },
                              error: function (xhr) {
                                  //Do Something to handle error
                              }
                          });
                      }
        </script>    
    </body>
</html>
