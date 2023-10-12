<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">
    <!-- title -->
    <title>시크릿 주주 결제 페이지</title>

    <!-- favicon -->
    <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
    <!-- google font -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Crimson+Text:ital,wght@0,400;0,600;0,700;1,400;1,600;1,700&display=swap"
          rel="stylesheet">
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
    <!-- 구매 페이지 추가 CSS -->
    <link rel="stylesheet" href="assets/css/buyPageExpansion.css">
    <!-- buyPage style -->
    <link rel="stylesheet" href="assets/css/buyPage.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">

    <script src="https://code.jquery.com/jquery-3.7.0.min.js"
            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous">

    </script>

</head>

<body>

<starlight:login/>

<!-- check out section -->
<div class="quickmenu d-flex text-right"
     style="position: absolute; width: 100px; flex-direction: column; top: 250px; right: 50px; color: #fff; z-index: 999;">
    <ul class="list-unstyled text-center">
        <li>
            <p class="text-dark">
                <strong>최근 본 상품</strong>
            </p>
        </li>
    </ul>
    <c:forEach var="v" items="${recentList}">
        <div class="card mb-2 product-wap rounded-0">
            <div class="card mb-0 rounded-0">
                <a href="detail.do?pNum=${v.pNum }"> <img class="card-img rounded-0 img-fluid w-100" alt="recentProduct"
                                                          src="assets/img/products/${v.pImage }">
                </a>
            </div>
        </div>
    </c:forEach>
</div>
<div class="checkout-section mt-150 mb-150">
    <div class="container">
        <h4>[ 주문 / 결제 ]</h4>
        <div class="row">
            <div class="col-lg-8">
                <div class="checkout-accordion-wrap">
                    <div class="accordion" id="accordionExample">
                        <div class="card single-accordion">
                            <div class="card-header" id="headingThree">
                                <h5 class="mb-0">
                                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                            data-target="#collapseThree" aria-expanded="false"
                                            aria-controls="collapseThree">사용 가능한 쿠폰
                                    </button>
                                </h5>
                            </div>
                            <!-- collapse show (처음에 들어 올 때 아코디언이 내려와 있다) -->
                            <div id="collapseThree" class="collapse show" aria-labelledby="headingOne"
                                 data-parent="#accordionExample">
                                <div class="card-body">
                                    <div class="billing-address-form">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>쿠폰 이름</th>
                                                <th>할인율</th>
                                                <th>유효 기간</th>
                                                <th>쿠폰 적용 여부</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="coupon" items="${ucdatas}">
                                                <!-- data-rowID 으로 쿠폰 정보에 대한 각 행에 ID값 부여 하기. -->
                                                <!-- data-ucNum 으로 속성을 정의 하기. -->
                                                <!-- data-cDiscount 으로 속성을 정의 하기. -->
                                                <!-- data-cName 으로 속성을 정의 하기. -->
                                                <tr data-rowID="${coupon.ucNum}">
                                                    <td><strong> <a href="#" class="couponInfo"
                                                                    data-ucNum="${coupon.ucNum}"
                                                                    data-cDiscount="${coupon.cDiscount}"
                                                                    data-cName="${coupon.cName}"> [ ${coupon.cName}
                                                        ] </a>
                                                    </strong></td>
                                                    <td><strong> <c:choose>
                                                        <c:when test="${coupon.cDiscount eq 0.9}">[ 10 % ]</c:when>
                                                        <c:when test="${coupon.cDiscount eq 0.85}">[ 15 % ]</c:when>
                                                        <c:when test="${coupon.cDiscount eq 0.8}">[ 20 % ]</c:when>
                                                    </c:choose>
                                                    </strong></td>
                                                    <td>[ ${coupon.ucFdate} ]</td>
                                                    <td class="couponStatus">미적용</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                        <!-- 사용 가능한 쿠폰 적용 하기 모달창 -->
                                        <div id="couponModal" class="modal">
                                            <div class="modal-content">
                                                <span class="close"></span>
                                                <!-- &times; -->
                                                <h3>안내 문구는 자바스크립트를 사용해서, 동적으로 처리.</h3>
                                                <button id="applyCoupon">사용</button>
                                                <button id="cancelCoupon">사용 안 함</button>
                                            </div>
                                        </div>

                                        <!-- 쿠폰이 적용 되면 적용 됬다고 안내 해주는 모달창 -->
                                        <div id="couponAppliedModal" class="modal">
                                            <div class="modal-content">
                                                <span class="close"></span>
                                                <!-- &times; -->
                                                <h3>쿠폰이 적용 되었습니다 !</h3>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- ======================================================================================= -->

                        <div class="card single-accordion">
                            <div class="card-header" id="headingTwo">
                                <h5 class="mb-0">
                                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                            data-target="#collapseTwo" aria-expanded="false"
                                            aria-controls="collapseTwo">주문 예정 상품
                                    </button>
                                </h5>
                            </div>
                            <!-- collapse show (처음에 들어 올 때 아코디언이 내려와 있다) -->
                            <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo"
                                 data-parent="#accordionExample">
                                <div class="card-body">
                                    <div class="shipping-address-form"></div>
                                    <c:set var="data" value="${pdata}"/>
                                    <c:set var="subdata" value="${subdata}"/>

                                    <!-- 구독 구매 일 때 -->
                                    <c:if test="${empty cartFlag}">
                                        <c:set var="orderName" value="${subdata.subName}"/>
                                        <div>
                                            <img src="assets/img/subscription/subdrink${subdata.subNum}.png"
                                                 style="width: 30%;">
                                            <hr>
                                            <p>상품 이름 : [ ${subdata.subName} ]</p>
                                            <fmt:formatNumber var="subPrice" value="${subdata.subPrice}"/>
                                            <p>가격 : [ ${subPrice} 원 ] / 수량 : [ 1 개 ]</p>
                                            <hr>
                                        </div>
                                    </c:if>

                                    <!-- 장바구니 구매 일 때 -->
                                    <c:if test="${cartFlag == 'cart'}">
                                        <c:forEach items="${cart}" var="cartItem" varStatus="status">
                                            <c:set var="orderName" value="${cartItem.pName } 외 ${status.index } 개"/>
                                            <div>
                                                <img src="assets/img/products/${cartItem.pImage}" style="width: 30%;">
                                                <hr>
                                                <p>상품 이름 : [ ${cartItem.pName} ]</p>
                                                <fmt:formatNumber var="pPrice" value="${cartItem.pPrice}"/>
                                                <p>가격 : [ ${pPrice} 원 ] / 수량 : [ ${cartItem.tmpCnt} 개 ]</p>
                                                <hr>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <!-- 상품 상세 페이지 에서 상품 구매 일 때 -->
                                    <c:if test="${cartFlag == 'detail'}">
                                        <c:set var="orderName" value="${pdata.pName}"/>
                                        <div>
                                            <img src="assets/img/products/${pdata.pImage}" style="width: 30%;">
                                            <hr>
                                            <p>상품 이름 : [ ${pdata.pName} ]</p>
                                            <fmt:formatNumber var="pPrice" value="${pdata.pPrice}"/>
                                            <p>가격 : [ ${pPrice} 원 ] / 수량 : [ ${pdata.tmpCnt} 개 ]</p>
                                            <hr>
                                        </div>
                                    </c:if>
                                    <fmt:formatNumber var="totalprice" value="${total}"/>
                                    <h4 id="productTotalPrice">예상 주문 총액 : ${totalprice} 원</h4>
                                </div>
                            </div>
                        </div>

                        <!-- ======================================================================================= -->

                        <div class="card single-accordion">
                            <div class="card-header" id="headingOne">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse"
                                            data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        배송지
                                    </button>
                                </h5>
                            </div>

                            <!-- collapse (처음에 들어 올 때 아코디언이 접혀 있다) -->
                            <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                                 data-parent="#accordionExample">
                                <div class="card-body">
                                    <div class="billing-address-form">
                                        <form id="buyActionForm" action="buy.do">
                                            <p>
                                                <input type="text" placeholder="Name" value="${mName}">
                                            </p>
                                            <p>
                                                <input type="email" value="${mID}" placeholder="Email">
                                            </p>
                                            <p>
                                                <starlight:address></starlight:address>
                                            </p>
                                            <p>
                                                <input type="tel" placeholder="${mPhone}">
                                            </p>
                                            <p>
                                                <textarea name="bill" id="bill" cols="30" rows="10"
                                                          placeholder="주문시 요청사항을 입력 하세요."></textarea>
                                            </p>
                                            <!-- 상품 상세 페이지에서 구매시, 상품의 개수를 hidden 값으로 전달 하기 -->
                                            <!-- 쿠폰의 PK 프로퍼티와 쿠폰의 할인율 프로퍼티를 hidden 값으로 전달 하기 -->
                                            <!-- 쿠폰을 사용 하지 않은 경우에는 ucNum과 cDiscount의 디폴트값을 0으로 세팅 -->
                                            <input type="hidden" name="orderName" value="${orderName}">
                                            <input type="hidden" name="oAddress" value="${address}">
                                            <input
                                                    type="hidden"
                                                    name="tmpCnt"
                                                    id="tmpCnt"
                                                    data-tmpcnt="${pdata.tmpCnt}"
                                                    value="${pdata.tmpCnt}">
                                            <input type="hidden" name="ucNum" value="0">
                                            <input type="hidden" name="cDiscount" value="0">
                                            <input type="submit" value="결제하기">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>

        </div>
    </div>

</div>

<!-- end check out section -->

<starlight:footer/>

<script>
    //  서버에서 가져온 세션값을 세션 스토리지에 저장 하기.
    let total = ${total}
        sessionStorage.setItem('total', total);
</script>

<!-- 사용 가능한 쿠폰 적용 하기 모달창 + 쿠폰 적용 여부를 동적 으로 처리 하기 위한 Ajax() 메서드 + .. -->
<script src="assets/js/buyPageJavascript.js"></script>
<!-- jquery -->
<script src="assets/js/jquery-1.11.3.min.js"></script>
<!-- bootstrap -->
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<!-- scroll -->
<script type="text/javascript">
    $(document).ready(function () {
        var currentPosition = parseInt($(".quickmenu").css("top"));
        $(window).scroll(function () {
            var position = $(window).scrollTop();
            $(".quickmenu").stop().animate({
                "top": position + currentPosition + "px"
            }, 1000);
        });
    });
</script>
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
<!-- 토스 페이 API -->
<script src="https://js.tosspayments.com/v1/payment-widget"></script>

</body>

</html>