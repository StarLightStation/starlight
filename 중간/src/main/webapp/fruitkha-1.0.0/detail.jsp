<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>상품명</title>

<!-- icon -->
<script src="https://kit.fontawesome.com/3df7c6052d.js"
   crossorigin="anonymous"></script>


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
<link r el="stylesheet" href="assets/css/magnific-popup.css">
<!-- animate css -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- mean menu css -->
<link rel="stylesheet" href="assets/css/meanmenu.min.css">
<!-- detail style -->
<link rel="stylesheet" href="assets/css/detail.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">
<!-- starscore css -->
<link rel="stylesheet" href="assets/css/starscore.css">

</head>
<body>

<starlight:login/>



   <script defer src="assets/js/starscore.js">
      
   </script>


   <div id="back-size">
      <!-- single product -->
      <div class="more-products mb-150">
         <div class="container">

            <div class="box">
               <div class="product-image">
                  <img alt="" src="assets/img/products/${pdata.pImage}.jpg">
               </div>
               <c:set var="pdata" value="${pdata}"></c:set>
               <div class="product-content">
                  <p id="product-name">${pdata.pName}</p>
                  <div class="rating_box">
                     <div class="rating">
                        ★★★★★ <span class="rating_star"
                           style="width: ${pdata.pStarAvg * 10}%;">★★★★★</span> <input
                           type="range" name="bStar" value="0" step="0.1" min="1" max="10">
                     </div>
                  </div>
                  <p id="product-review">별점 ${pdata.pStarAvg/2} ｜ 리뷰
                     ${pdata.pStarCnt}</p>
                  <div>
                     <input type="hidden" value="${pdata.pNum}">
                     <p id="product-kind">주종: ${pdata.pCategory}</p>
                     <p id="product-frequency">도수: ${pdata.pAlcohol}</p>
                     <p id="product-sweetness">단맛: ${pdata.pSweet}</p>
                     <p id="product-sour-taste">신맛: ${pdata.pSour}</p>
                     <p id="product-carbonic-acid">탄산: ${pdata.pSparkle}</p>
                  </div>
                  <p id="product-sell">판매가격</p>
                  <p id="product-price">${pdata.pPrice}<span>원</span>
                  </p>
               </div>
               <div class="product-empty"> </div>
               <div class="product-buy">
                  <form action="buyPage.do?cartFlag=detail">
                     <p>수량</p>
                     <div class="count-wrap _count">
                        <button type="button" class="minus" style="font-size: 12px;">
                           <i class="fa-solid fa-minus" style="color: #757575;"></i>
                        </button>
                        <input type="hidden" name="pnum" value="${pdata.pNum}">
                        <input type="text" class="inp" name="count" value="1" />
                        <button type="button" class="plus" style="font-size: 12px;">
                           <i class="fa-solid fa-plus" style="color: #757575;"></i>
                        </button>
                     </div>
                     <a href="#" onclick="addToCart(event)" style="color: #fff;">장바구니</a> 
                     <c:if test="${not empty mid}">
                     	<input type="submit" value="구매하기" id="pay">
                     </c:if>
                     <c:if test="${empty mid}">
                     	<input type="submit" value="구매하기" id="pay" formaction="loginPage.do">
                     </c:if>
                  </form>
               </div>
               <div class="detail">
               		 <img src="assets/img/products/${pdata.pImagedetail}.png">
               </div>
            </div>
         </div>
      </div>
      <!-- end single product -->
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

   <script src="assets/js/single-product.js"></script>
   <!-- 상품 수량 -->
   <script>
      function addToCart(event) {
         event.preventDefault(); // 기본 동작을 막음

         var countValue = document.querySelector(".inp").value;
         var pNumValue = "${pdata.pNum}";
         var url = "cartadd.do?pnum=" + pNumValue + "&count=" + countValue;

         // 새로운 URL로 이동
         window.location.href = url;
      }
   </script>

</body>
</html>