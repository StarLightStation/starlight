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
<title>김주문 박결제</title>

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
<!-- buyPage style -->
<link rel="stylesheet" href="assets/css/buyPage.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>


<starlight:login/>



<!-- check out section -->
   <div class="checkout-section mt-150 mb-150">
      <div class="container">
         <h5>주문 / 결제</h5>
         <div class="row">
            <div class="col-lg-8">
               <div class="checkout-accordion-wrap">
                  <div class="accordion" id="accordionExample">
                     <div class="card single-accordion">
                        <div class="card-header" id="headingOne">
                           <h5 class="mb-0">
                              <button class="btn btn-link" type="button"
                                 data-toggle="collapse" data-target="#collapseOne"
                                 aria-expanded="true" aria-controls="collapseOne">
                                 배송지</button>
                           </h5>
                        </div>

                        <div id="collapseOne" class="collapse show"
                           aria-labelledby="headingOne" data-parent="#accordionExample">
                           <div class="card-body">
                              <div class="billing-address-form">
                                 <form action="#">
                                    <p>
                                       <input type="text" placeholder="Name" value="${mname}">
                                    </p>
                                    <p>
                                       <input type="email" value="${mid}" placeholder="Email">
                                    </p>
                                    <p>
                                        <starlight:address></starlight:address>
                                    </p>
                                    <p>
                                       <input type="tel" placeholder="${mphone }">
                                    </p>
                                    <p>
                                       <textarea name="bill" id="bill" cols="30" rows="10"
                                          placeholder="Say Something"></textarea>
                                    </p>
                                 </form>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="card single-accordion">
                        <div class="card-header" id="headingTwo">
                           <h5 class="mb-0">
                              <button class="btn btn-link collapsed" type="button"
                                 data-toggle="collapse" data-target="#collapseTwo"
                                 aria-expanded="false" aria-controls="collapseTwo">
                                 주문 예정 상품</button>
                           </h5>
                        </div>
                        <div id="collapseTwo" class="collapse"
                           aria-labelledby="headingTwo" data-parent="#accordionExample">
                           <div class="card-body">
                              <div class="shipping-address-form"></div>
                              <c:set var="cart" value="${sessionScope.cart}" />
                              <c:set var="cartFlag" value="${sessionScope.cartFlag}" />
                              <c:set var="data" value="${pdata}"/>
                              <c:set var ="isSub" value="${sessionScope.subNum}"/>
                              <c:set var = "subdata" value="${subdata}"/>
                              <c:if test="${cartFlag != null }">
                                 <c:forEach items="${cart}" var="cartItem" varStatus="status">
                                    <c:forEach items="${cartItem}" var="entry">
                                    <c:set var="orderName" value="${entry.value.pName } 외 ${status.index } 개"/>
                                       <div>
										<img src="assets/img/products/${entry.value.pImage}.jpg" style="width: 30%;">
                                          <p>상품 이름 [ ${entry.value.pName} ]</p>
                                          <p>가격 [ ${entry.value.pPrice } ] / 수량 [
                                             ${entry.value.tmpCnt } ]</p>

                                          <p>-----------------------------------------------------</p>


                                       </div>
                                    </c:forEach>
                                 </c:forEach>
                                 <c:set var="total" value="${total}"></c:set>
                              </c:if>
                              <c:if test="${cartFlag == null }">
                              	<c:if test="${isSub==null }">
                             	 <c:set var="orderName" value="${pdata.pName}"/>
                                 <div>
										<img src="assets/img/products/${pdata.pImage}.jpg" style="width: 30%;">
                                          <p>상품 이름 [ ${pdata.pName} ]</p>
                                          <p>가격 [ ${pdata.pPrice } ] / 수량 [
                                             ${pdata.tmpCnt } ]</p>

                                          <p>-----------------------------------------------------</p>
                                 </div>
                              
                              </c:if>
                              
                              <c:if test="${isSub !=null }">
                               <c:set var="orderName" value="${subdata.subName}"/>
                                 <div>
                                          <p>상품 이름 [ ${subdata.subName} ]</p>
                                          <p>가격 [ ${subdata.subPrice} ] / 수량 [
                                             1]</p>

                                          <p>-----------------------------------------------------</p>
                                 </div>
                              </c:if>
                              
                              </c:if>
                              <h3>주문 총액 : ${total} 원</h3>
                           </div>


                        </div>
                     </div>
                  </div>

                  <div id="collapseThree" class="collapse"
                     aria-labelledby="headingThree" data-parent="#accordionExample">
                     <div class="card-body">
                        <div class="card-details">
                           <p>Your card details goes here.</p>
                        </div>
                     </div>
                  </div>

               </div>
               <div id="payment-method"></div>
               <button id="payment-request-button" class="boxed-btn">${total }
                  원 결제하기</button>
            </div>
         </div>

      </div>
   </div>


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

   <script src="https://js.tosspayments.com/v1/payment-widget"></script>



   <script>
      const paymentWidget = PaymentWidget(
        "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq",
        // 비회원 customerKey
        PaymentWidget.ANONYMOUS
      );

      //aa 외 상품으로 뜨게 하기
    //  set으로 값 가져오고 함수로 계산
      
      //반복문
      
      
      /**
       * 결제창을 렌더링합니다.
       * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
       */
      paymentWidget.renderPaymentMethods("#payment-method", { value: ${total} });

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
          successUrl: window.location.origin + "/SECERT_JOOJOO/fruitkha-1.0.0/success.do",
          failUrl: window.location.origin + "/fail.jsp",
        });
      });

      function generateRandomString() {
        return window.btoa(Math.random()).slice(0, 20);
      }
    </script>
</body>
</html>