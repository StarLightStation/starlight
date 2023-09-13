<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.1">
<meta name="description"
   content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>작성 할 리뷰</title>

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
<!-- writeReview style -->
<link rel="stylesheet" href="assets/css/writeReview.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">



</head>
<body>

  <starlight:login/>
  
  
<starlight:mypage/>


   <!-- 리뷰 main.css 2615 -->
   <div class="cart-section mt-150 mb-150">
    <div class="container">
         <div class="review">
            <a href="writereview.do" class="box1">작성할 리뷰</a>
            <a href="updatereview.do" class="box2">내가 쓴 리뷰</a>
         </div>
            
        <div class="member-info-wrapper">
         
            <div class="name-header">
               <div class="name-text"></div>
               <div class="name-label"></div>
            </div>
         
            <hr>
               
            <div class="side-bar-wrapper">
                <c:forEach var="v" items="${oddatas}">
                 <c:set var="rnum" value="${v.rnum}" />
                 <c:set var="mID" value="${v.mID}" />
                 <c:set var="pName" value="${v.pName}" />
                 <c:set var="pPrice" value="${v.pPrice}" />
                 <c:set var="pNum" value="${v.pNum}" />
                  <c:set var="odNum" value="${v.odNum}" />
   
                    <div class="side-bar-row">
                        ${rnum} | ${pName} | ${pPrice}  
                                             
                        <h1 class="btn">리뷰 작성하기</h1>
 
               			<!-- 첫 번째 Modal -->
             			<div class="modal">
 
             		     <!-- 첫 번째 Modal의 내용 -->
           			    	<div class="modal-content">
           			        <h4>${pName}</h4>
           			    	<span class="close">&times;</span>  
           			    	                       
            		    	<div class="rating_box">
            					<form action="writereviewOK.do" method="post">
	     							<input type="hidden" name="mid" value="${mid}">
					     			<input type="hidden" name="mname" value="${mname}">
					     			<input type="hidden" name="pnum" value="${pNum}">
					     			<input type="hidden" name="odnum" value="${odNum}">
					                <span class="rating">
	                    				★★★★★
	                    				<span class="rating_star">★★★★★</span>
                     					<input type="range" name="bstar" value="0" step="1" min="0" max="10">                  
                					</span>
                  					<br>
                  					<textarea id="myTextarea" name="bcontent" rows="4" cols="50" placeholder="내용을 입력해주세요"></textarea>
                 					<br><br>
                					<input type="submit" id="input2" value="작성 완료">
               					</form>
            				</div>
 						</div>
             	    	</div>
                	</div>
                </c:forEach>
            </div>
        </div>
     </div>
    </div>
   
   <!-- 모달 창 -->

    


	<script src="assets/js/starscore.js"></script>



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