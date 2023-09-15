<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/"
    >
    <!-- title -->
    <title>시크릿 주주 결제 페이지</title>

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
    <!-- buyPage style -->
    <link rel="stylesheet" href="assets/css/buyPage.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">

</head>

<body>

<starlight:login/>

<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
    <div class="card-body">
        <div class="card-details">
            <p>Your card details goes here.</p>
        </div>
    </div>
</div>

<div id="payment-method"></div>
<hr>
<button id="payment-request-button" class="boxed-btn">${useCouponDiscountTotal}원 결제 하기</button>
<hr>
<div id="orderName"><strong>예정 주문 상품 : [ ${orderName} ]</strong></div>

<!-- end check out section -->

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
<!-- 토스 페이 API -->
<script src="https://js.tosspayments.com/v1/payment-widget"></script>

<script>
    const paymentWidget = PaymentWidget(
        "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq",
        // 비회원 customerKey
        PaymentWidget.ANONYMOUS
    );

    //   aa 외 상품으로 뜨게 하기
    //  set으로 값 가져오고 함수로 계산
    //   반복문

    /**
     * 결제창을 렌더링합니다.
     * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
     */
    paymentWidget.renderPaymentMethods("#payment-method", {value: ${useCouponDiscountTotal}}); <!-- total -->

    const paymentRequestButton = document.getElementById(
        "payment-request-button"
    );
    paymentRequestButton.addEventListener("click", () => {
        /** 결제 요청
         * @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment%EA%B2%B0%EC%A0%9C-%EC%A0%95%EB%B3%B4
         */
        paymentWidget.requestPayment({
            orderId: generateRandomString(),
            orderName: "${orderName}",
            successUrl: window.location.origin + "/app/success.do",
            failUrl: window.location.origin + "/fail.jsp",
        });
    });

    function generateRandomString() {
        return window.btoa(Math.random()).slice(0, 20);
    }
</script>

</body>

</html>