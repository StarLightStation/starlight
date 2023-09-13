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
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- title -->
	<title>시크릿주주 - 필터검색</title>

	<!-- icon -->
    <script src="https://kit.fontawesome.com/3df7c6052d.js" crossorigin="anonymous"></script>
   
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
	<!-- shop style -->
	<link rel="stylesheet" href="assets/css/searchFilter.css">
	<!-- responsive -->
	<link rel="stylesheet" href="assets/css/responsive.css">
	
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	
	
	

</head>
<body>

<starlight:login/>
	

<br><br>
	
	<!-- 필터 검색 -->
	<div class="product-section mt-150 mb-150">
  		<div class="container">
    		<div class="section-container">
    		</div>
		</div>
	</div>
	<!-- 필터 검색 -->


	<!-- 상품 목록 시작 -->
	<div class="product-section mt-150 mb-150">
		<div class="container">
			<p class="search-resultCnt">${datas.size()} 건의 결과가 있어요</p>
			
			<c:forEach var="v" items="${datas}" varStatus="status">
 			 <!-- 매 3번째 상품일 경우, 새로운 row를 시작 -->
 				<c:if test="${status.index % 3 == 0}">
					<div class="row product-lists">
 				</c:if>
	
  				<div class="col-lg-4 col-md-6 text-center ${v.pCategory}">
   			 		<div class="single-product-item">
    		 			<div class="product-image">
     		  				 <a href="detail.do?pnum=${v.pNum}"><img src="assets/img/products/${v.pImage}.jpg" alt=""></a>
     					</div>
     					<div class="product-information">
     		  			<h3>${v.pName}</h3>
     			   		<p class="product-price" style="margin-bottom: 0;">${v.pPrice}<span>원</span></p>
     		  			<p class="product-starscore">별점 ${v.pStarAvg/2} ｜ 리뷰 ${v.pStarCnt}</p>
     					</div>
    				</div>
  				</div>

  				<!-- 매 3번째 상품일 경우, 현재 row를 닫음 -->
  				<c:if test="${status.index % 3 == 2 or status.last}">
    				</div>
  				</c:if>
			</c:forEach>
				
		</div>
	</div>
	<!-- end products -->



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
	
	<!-- 상세페이지 검색필터 js -->
	<script src="assets/js/shopFilterDropdown.js"></script>
	
	<script src="assets/js/jquery-3.6.0.min.js"></script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</body>
</html>