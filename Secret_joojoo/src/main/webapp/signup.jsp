<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta
            name="description"
            content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/"
    >

    <!-- title -->
    <title>회원가입 페이지</title>

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
    <!-- animate css -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- mean menu css -->
    <link rel="stylesheet" href="assets/css/meanmenu.min.css">
    <!-- 회원 가입 페이지 추가 CSS -->
    <link rel="stylesheet" href="assets/css/signupExpansion.css">
    <!-- signup style -->
    <link rel="stylesheet" href="assets/css/signup.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">
    <!-- Google API를 사용하기 위한 스크립트 -->
    <script src="https://accounts.google.com/gsi/client" async defer></script>

    <script
            src="https://code.jquery.com/jquery-3.7.0.min.js"
            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
            crossorigin="anonymous">
    </script>

</head>

<body>

<starlight:login/>

<!-- check out section -->
<div class="checkout-section mt-150 mb-150">

    <div class="container">

        <div id="sign">

            <!-- 회원가입 버튼 -->
            <div class="signHeader">
                <h4>안녕하세요 ! 시크릿주주 회원가입을 환영합니다 🎉</h4>
            </div>

            <br>

            <!-- 추천인 쿠폰 관련 코드. -->
            <br>
            <h4>추천인을 입력 하세요 😍</h4>
            <div id="recommendation">
                <input
                        type="text"
                        name="tmpmID"
                        id="recommendationValue"
                        placeholder="등록할 추천인을 입력 해주세요."
                        size="35"
                >
                <button type="button" id="recommendationValueCheck">추천인 확인</button>
            </div>
            <br>
            <h6 id="recommendationResultMessage"></h6> <!-- 추천인 유효성 검사 메세지 띄우기 -->

            <br>
            <br>

            <div id="collapseOne" class="collapse show"
                 aria-labelledby="headingOne" data-parent="#accordionExample">
                <div id="billing-address-form">
                    <form action="signup.do" method="post" onsubmit="return validateForm();">
                        <div id="name1">아이디</div>
                        <input type="email" name="mID" placeholder="이메일을 입력 해주세요." required>
                        <div id="name2">비밀번호</div>
                        <input type="password" name="mPW" id="newPassword" placeholder="비밀번호를 입력 해주세요." required>
                        <input type="password" id="confirmPassword" placeholder="비밀번호를 다시 입력 해주세요." required>
                        <div id="name3">닉네임</div>
                        <input type="text" name="mName" placeholder="이름을 입력 해주세요." required>
                        <div id="name4">휴대폰 번호</div>
                        <input type="tel" name="mPhone" id="signupphoneNumber" placeholder="휴대폰 번호를 입력 해주세요." required>
                        <input type="hidden" name="signUpKind" value="주주">
                        <input type="hidden" name="tmpmID" id="recommendationtmpmID"> <!-- value="" -->
                        <input type="submit" value="회원가입 완료">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end check out section -->

<starlight:footer/>

<!-- 회원 가입 추천인 유효성 검사 비동기 처리 + 회원가입 비밀번호 유효성 검사 -->
<script src="assets/js/signupJavascript.js"></script>
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