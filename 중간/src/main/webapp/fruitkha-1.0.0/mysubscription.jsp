<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>구독관리</title>

<!-- favicon -->
<link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
<!-- google font -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap"
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
<!-- mysubscription style -->
<link rel="stylesheet" href="assets/css/mysubscription.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>

	<starlight:login/>

<starlight:mypage/>

	<!-- cart -->
	
	<div class="cart-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="Sub-table-wrap"><!-- main.css 2596 -->
					<div class="member-info-wrapper">
						<div class="name-header">
							<span class="name-text">구독정보</span>
						</div>
						<hr>
						<div class="side-bar-wrapper">
							<c:set var = "subName" value ="${data.subName}"></c:set>
							<c:set var = "sInfoPeriod" value ="${data.sInfoPeriod}"></c:set>
						
							<h6> 구독이름 : ${subName} /  구독 기간 : ${sInfoPeriod} </h6> 			
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