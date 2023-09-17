<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>마이페이지 주문내역</title>

    <!-- favicon -->
    <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
   <!-- google font -->
   <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Crimson+Text:ital,wght@0,400;0,600;0,700;1,400;1,600;1,700&display=swap" rel="stylesheet">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/all.min.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- animate css -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- mean menu css -->
    <link rel="stylesheet" href="assets/css/meanmenu.min.css">
    <!-- 마이페이지 추가 CSS -->
    <link rel="stylesheet" href="assets/css/myPageExpansion.css">
    <!-- orderHistory style -->
    <link rel="stylesheet" href="assets/css/orderHistory.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>

<starlight:login/>

<starlight:mypage/>

<!-- cart -->
<div class="cart-section mt-150 mb-150" style="margin-top: 0px">
    <div class="container">
        <div class="row">
            <div class="Sub-table-wrap"><!-- main.css 2596 -->
                <div class="member-info-wrapper">
                    <div class="name-header">
                        <h5 class="name-text" style="text-align: left; color: #575757">주문 내역</h5>
                    </div>
                    <hr>
                    <div class="side-bar-wrapper2" style="overflow:auto;  width:1020px; height:600px; margin-top: 30px;">
                        <table id="orderHistoryTable">
                            <thead>
                            <tr>
                                <th>결제 날짜</th>
                                <th>상품명</th>
                                <th>상품 가격</th>
                                <th>구매 개수</th>
                                <th>총 결제 금액</th>
                                <th>주문 상태</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="entry" items="${orderDatas}">
                                <c:set var="order" value="${entry.key}"></c:set>
                                <c:set var="oddatas" value="${entry.value}"></c:set>
                                <tr>
                                    <td>${order.oDate}</td>
                                    <td>
                                        <c:forEach var="detail" items="${oddatas}">
                                            ${detail.pName} <br>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="detail" items="${oddatas}">
                                        <fmt:formatNumber var="pPrice" value="${detail.pPrice}" />
                                            ${pPrice} 원 <br>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="detail" items="${oddatas}">
                                            ${detail.odCnt} 개 <br>
                                        </c:forEach>
                                    </td>
                                    <fmt:formatNumber var="oPrice" value="${order.oPrice}" />
                                    <td>${oPrice} 원</td>
                                    <td>${order.oState}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<starlight:footer/>

<!-- jquery -->
<script src="assets/js/jquery-1.11.3.min.js"></script>
<!-- bootstrap -->
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<!-- count down -->
<script src="assets/js/jquery.countdown.js"></script>
<!-- isotope -->
<script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
<!-- waypoints -->
<script src="assets/js/waypoints.js"></script>
<!-- owl carousel -->
<script src="assets/js/owl.carousel.min.js"></script>
<!-- magnific popup -->
<script src="assets/js/jquery.magnific-popup.min.js"></script>
<!-- mean menu -->
<script src="assets/js/jquery.meanmenu.min.js"></script>
<!-- sticker js -->
<script src="assets/js/sticker.js"></script>
<!-- main js -->
<script src="assets/js/main.js"></script>

</body>
</html>