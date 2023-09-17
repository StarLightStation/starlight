<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>구독 및 쿠폰 관리</title>

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
    <!-- mysubscription style -->
    <link rel="stylesheet" href="assets/css/mysubscription.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">

</head>

<body>

<starlight:login/>

<starlight:mypage/>

<!-- 마이페이지 구독 및 쿠폰 관리 -->

<div class="cart-section mt-150 mb-150">
    <div class="container">
        <div class="row">
            <div class="Sub-table-wrap">
                <div class="member-info-wrapper">
                    <div class="name-header">
                        <h5 class="name-text" style="text-align: left; color: #575757">구독 정보</h5>
                    </div>
                    <hr>
                    <div class="side-bar-wrapper" style="height: 120px;">
                        <ul>
                            <li class="subsinfoText" style="font-size: 16px;"><span>구독 이름 ｜ </span>
                                <c:choose>
                                    <c:when test="${not empty subsinfodata.subName}"> ${subsinfodata.subName} </c:when>
                                    <c:otherwise>구독중이지 않습니다.</c:otherwise>
                                </c:choose>
                            </li>
                            <li class="subsinfoText" style="font-size: 16px;"><span>구독 기간 ｜ </span>
                                <c:choose>
                                    <c:when test="${not empty subsinfodata.sInfoPeriod}"> ${subsinfodata.sInfoPeriod} </c:when>
                                    <c:otherwise>구독중이지 않습니다.</c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            
                <div class="member-info-wrapper2">
                    <div class="name-header">
                        <h5 class="name-text" style="text-align: left; color: #575757">쿠폰 정보</h5>
                    </div>
                    <hr>
                    <div class="side-bar-wrapper2">
                            <c:forEach var="coupon" items="${ucdatas}">
                                <c:choose>
                                       <c:when test="${coupon.ucAble eq 1}">
                                       <div class="coupon-body">
                                            <h5 class="card-title">${coupon.cName} 쿠폰</h5>
                                            <h6 class="card-text">할인율 ｜
                                                <c:choose>
                                                    <c:when test="${coupon.cDiscount eq 0.9}"> 10 % </c:when>
                                                    <c:when test="${coupon.cDiscount eq 0.85}"> 15 % </c:when>
                                                    <c:when test="${coupon.cDiscount eq 0.8}"> 20 % </c:when>
                                                </c:choose>
                                            </h6>
                                            <h6 class="card-text">유효 기간 ｜ ${coupon.ucFdate}</h6>
                                            <h6 class="card-text">사용 여부 ｜사용 가능
                                            </h6>
                                      </div>
                                      </c:when>
                                     <c:when test="${coupon.ucAble eq 0}">
                                       <div class="coupon-body-used">
                                            <h5 class="card-title">${coupon.cName} 쿠폰</h5>
                                            <h6 class="card-text">할인율 ｜
                                                <c:choose>
                                                    <c:when test="${coupon.cDiscount eq 0.9}"> 10 % </c:when>
                                                    <c:when test="${coupon.cDiscount eq 0.85}"> 15 % </c:when>
                                                    <c:when test="${coupon.cDiscount eq 0.8}"> 20 % </c:when>
                                                </c:choose>
                                            </h6>
                                            <h6 class="card-text">유효 기간 ｜ ${coupon.ucFdate}</h6>
                                            <h6 class="card-text">사용 여부 ｜사용 완료
                                            </h6>
                                      </div>
                                      </c:when>
                                      </c:choose>
                            </c:forEach>
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