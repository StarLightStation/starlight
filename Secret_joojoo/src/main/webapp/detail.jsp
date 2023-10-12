<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>시크릿주주 - ${pdata.pName}</title>

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

   <starlight:login />

   <!-- 신고된 리뷰 삭제 Modal -->
   <div class="modal fade" id="basicModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
            </div>
            <h5 class="modal-title" id="exampleModalLabel1">해당 리뷰를 신고하시겠습니까</h5>
            <div class="modal-footer">
               <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
               <form action="declarationInsert.do" method="post">
                  <input type="hidden" id="bNum" name="bNum" value=""> <input type="hidden" id="pNum" name="pNum" value="">
                  <button type="submit" class="btn btn-primary">확인</button>
               </form>
            </div>
         </div>
      </div>
   </div>
   <!-- 신고된 리뷰 삭제 Modal 끝-->


   <div id="back-size">
      <!-- single product -->
      <div class="more-products mb-150">
         <div class="container" id="contentContainer">

            <div class="box">
               <div class="product-image">
                  <img alt="" src="assets/img/products/${pdata.pImage}">
               </div>
               <c:set var="pdata" value="${pdata}"></c:set>
               <div class="product-content">
                  <p id="product-name">${pdata.pName}</p>
                  <div class="rating_box">
                     <div class="rating">
                        ★★★★★ <span class="rating_star" style="width: ${pdata.pStarAvg * 20}%;">★★★★★</span> <input type="range" name="bStar" value="0" step="0.05" min="1" max="5">
                     </div>
                  </div>
                  <p id="product-review">별점 ${pdata.pStarAvg} ｜ 리뷰 ${pdata.pStarCnt}</p>
                  <div>
                     <input type="hidden" value="${pdata.pNum}">
                     <p id="product-kind">주종: ${pdata.pCategory}</p>
                     <p id="product-frequency">도수: ${pdata.pAlcohol}</p>
                           <p id="product-sweetness">단맛 : ${pdata.pSweet != undefined ? pdata.pSweet : '해당 없음'}</p>
                           <p id="product-sour-taste">신맛 : ${pdata.pSour != undefined ? pdata.pSour : '해당 없음'}</p>
                           <p id="product-carbonic-acid">탄산 : ${pdata.pSparkle != undefined ? pdata.pSparkle : '해당 없음'}</p>
                  </div>
                  <p id="product-sell">판매가격</p>
                  <p id="product-price">
                  <fmt:formatNumber var="pPrice" value="${pdata.pPrice}"/>
                  ${pPrice}<span>원</span>
                  </p>
               </div>
               <div class="product-empty"></div>
               <div class="product-buy">
                  <form action="buyPage.do">
                                    <h5 id="product-count">
                        <c:choose>
                            <c:when test="${pdata.pCnt == 0}">
                                <h5 id="product-count-zero" style="color: orangered">상품 재고가 없습니다.</h5>
                            </c:when>
                            <c:when test="${pdata.pCnt <= 10}">
                                <h5 style="color: midnightblue">상품이 얼마 남지 않았어요. [ ${pdata.pCnt}개 ]</h5>
                            </c:when>
                        </c:choose>
                    </h5>
                  
                     <p>수량</p>
                     <div class="count-wrap _count">
                        <button type="button" class="minus" style="font-size: 12px;">
                           <i class="fa-solid fa-minus" style="color: #757575;"></i>
                        </button>
                        <input type="hidden" name="pCnt" value="${pdata.pCnt}"> 
                        <input type="hidden" name="pNum" value="${pdata.pNum}"> 
                        <input type="hidden" name="cartFlag" value="detail"> 
                        <input type="text" class="inp" name="tmpCnt" value="1" readonly/>
                        <button type="button" class="plus" style="font-size: 12px;">
                           <i class="fa-solid fa-plus" style="color: #757575;"></i>
                        </button>
                     </div>
                     <a href="#" onclick="addToCart(event)" style="color: #fff;">장바구니</a>
                     <c:if test="${not empty mID}">
                        <input type="submit" value="구매하기" id="pay">
                     </c:if>
                     <c:if test="${empty mID}">
                        <input type="submit" value="구매하기" id="pay" formaction="login.do">
                     </c:if>
                  </form>
               </div>
               <div class="detail">
                  <img src="assets/img/products/${pdata.pImagedetail}"> <br> <br> <br> <br> <br>
                  <hr>
                  <br> <br> <br> <br>
                  <h4>REVIEW｜ ${pdata.pStarCnt}</h4>
                  <hr class="reviewLine">
                  <br> <br>
                  
                  <div id="detailbox">
                     <c:forEach var="v" items="${bdatas}">
                        <div class="reviewBox">
                           <p class="name">${v.mName}<span> <fmt:formatDate value="${v.bDate}" pattern="yyyy-MM-dd" /></span>
                           </p>
                           <p>별점: ${v.bStar}</p>
                           <p>${v.bContent}</p>
                           <c:if test="${not empty mID and v.mID ne mID}">
                           <c:if test="${v.check eq false}">
                              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal" onclick="openModalWithBNum(this, ${v.bNum}, ${pdata.pNum} )" id="reportButton_${v.bNum}">신고</button>
                           </c:if>
                           <c:if test="${v.check eq true}">
                              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal" onclick="openModalWithBNum(this, ${v.bNum}, ${pdata.pNum} )" id="reportButton_${v.bNum}" disabled="disabled">신고</button>
                           </c:if>
                           </c:if>
                        </div>
                        <hr>
                     </c:forEach>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- end single product -->

   <starlight:footer />

   </div>

   <!-- JavaScript 함수를 통해 모달 열기 및 bNum 설정 -->
   <script>
         function openModalWithBNum(button, bNum, pNum) {
             // 모달 열기
             $('#basicModal').modal('show');
         
             // bNum 값을 모달 내의 숨겨진 입력 필드에 설정
             $('#bNum').val(bNum);
             
             // pNum 값을 모달 내의 숨겨진 입력 필드에 설정
             $('#pNum').val(pNum);
      }
   </script>
   <!-- JavaScript 함수를 통해 모달 열기 및 bNum 설정 끝 -->
      
   
   <!-- 장바구니 버튼 클릭시, 상품의 PK, 장바구니에 담을 개수, 장바구니 링크를 보내기 -->
   <script>
   function isInventoryEmpty() {
          // pCnt가 0이거나 더 작은 경우 재고가 없다고 가정합니다.
          return ${pdata.pCnt} <= 0; 
      }
       // 상품재고가 있는지 여부를 확인하는 조건 변수 (예: 재고가 없다면 false)
         var isStockAvailable = isInventoryEmpty();
      // 상품을 카트에 추가하는 함수 
       function addToCart(event) {
           event.preventDefault(); //  기본 동작을 막기.

           if (!isStockAvailable) {
              var countValue = document.querySelector(".inp").value;
              var pNumValue = "${pdata.pNum}";
              var url = "cartAdd.do?pNum=" + pNumValue + "&tmpCnt=" + countValue; // URL 생성
           } else {
              alert("상품 재고가 없습니다.");
               var url = ""; // 또는 다른 값을 설정하거나 아무것도 설정하지 않음
           }
           //  새로운 URL로 이동 하기.
           window.location.href = url;
      }   

   </script>
   <!-- 장바구니 버튼 클릭시, 상품의 PK, 장바구니에 담을 개수, 장바구니 링크를 보내기 끝-->

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
   <!-- 상품 개수 (+) / (-) 관련 JS + 상품 재고가 없을시 구매 버튼 막기 -->
   <script src="assets/js/single-product.js"></script>

   <!-- Modal JS -->
   <script src="admin/assets/vendor/js/bootstrap.js"></script>





</body>
</html>