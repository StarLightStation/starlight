<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

   <!-- title -->
   <title>아이디 찾기</title>

   <!-- favicon -->
   <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
   <!-- google font -->
   <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
   <!-- fontawesome -->
   <link rel="stylesheet" href="assets/css/all.min.css">
   <!-- bootstrap -->
   <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
   
   
   <!-- animate css -->
   <link rel="stylesheet" href="assets/css/animate.css">
   <!-- mean menu css -->
   <link rel="stylesheet" href="assets/css/meanmenu.min.css">
   <!-- searchIDPage style -->
   <link rel="stylesheet" href="assets/css/searchID.css">
   <!-- responsive -->
   <link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>
   
   <starlight:login/>


   
   
   <div class="checkout-section mt-150 mb-150">
   <div id="findid-box">
      <div class="container">
         <div id="sign"><!-- css 803 -->
            <div class="signHeader">
               <h4>아이디가 생각나지 않으신다면? 😢</h4>
            </div>
            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
               <div id="billing-address-form">
                  <form id="searchForm" action="searchID.do" method="post" onsubmit="return checkBrn();">
                     <div id="name4">핸드폰 번호</div>
                     <input type="tel" name="mphone" id="phoneNumberInput" placeholder="(-)빼고 입력하세요." required>
                     <input type="submit" value="아이디 찾기">
                  </form>
                  <div id="phoneNumberValidationResult"></div>
               </div>
                  <p id="checkBrnMessage"></p>
            </div>
         </div>
      </div>
   </div>
</div>
<!-- end check out section -->

<script type="text/javascript">
   function checkBrn() {
      var brn = document.getElementById("phoneNumberInput").value;
      // 하이픈을 제거하지 않고 유효성 검사를 수행합니다.
      if (!/^[0-9]{11}$/.test(brn)) {
         document.getElementById("checkBrnMessage").innerText = "번호가 유효하지 않습니다.";
         // 유효하지 않은 경우 입력 필드에 포커스를 설정합니다.
         document.getElementById("phoneNumberInput").focus();
         // 또는 경고 메시지를 띄우고 포커스를 설정할 수도 있습니다.
         alert("유효하지 않은 휴대폰 번호입니다. 다시 입력해주세요.");
         return false;
      } 
   }
</script>
   
   <starlight:footer/>
   
    <!-- 여기에 검증할 핸드폰 번호를 입력하세요.--> 
                                  
                              
   
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