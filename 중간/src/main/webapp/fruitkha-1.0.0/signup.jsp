<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
   content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>회원가입 페이지</title>

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


<!-- animate css -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- mean menu css -->
<link rel="stylesheet" href="assets/css/meanmenu.min.css">
<!-- signup style -->
<link rel="stylesheet" href="assets/css/signup.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">
<!-- Google API를 사용하기 위한 스크립트 -->
<script src="https://accounts.google.com/gsi/client" async defer>
   //Google api
</script>

</head>
<body>

   <!--PreLoader-->
   <div class="loader">
      <div class="loader-inner">
         <div class="circle"></div>
      </div>
   </div>
   <!--PreLoader Ends-->

   <!-- header -->
   <div class="top-header-area" id="sticker">
      <div class="container">
         <div class="row">
            <div class="col-lg-12 col-sm-12 text-center">
               <div class="main-menu-wrap">
                  <!-- logo -->
                  <div class="site-logo">
                     <a href="index.html"> <img src="assets/img/logo.png" alt="">
                     </a>
                  </div>
                  <!-- logo -->

                  <!-- menu start -->
                  <nav class="main-menu">
                     <ul>
                        <li><a href="subscription.html">구독</a></li>
                        <li><a href="store.html">스토어</a></li>
                        <li class="login-menu">
                           <div class="header-icons">
                              <span class="login"> <a href="contact.html">로그인/회원가입</a>
                              </span> <a class="mobile-hide search-bar-icon" href="#"><i
                                 class="fas fa-search"></i></a> <a class="shopping-cart"
                                 href="cart.html"><i class="fas fa-shopping-cart"></i></a>
                           </div>
                        </li>
                     </ul>
                  </nav>
                  <!-- 모바일용 아이콘 -->
                  <a class="mobile-show search-bar-icon" href="#"><i
                     class="fas fa-search"></i></a>
                  <div class="mobile-menu"></div>
                  <!-- menu end -->
               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- end header -->

   <!-- search area -->
   <div class="search-area">
      <div class="container">
         <div class="row">
            <div class="col-lg-12">
               <span class="close-btn"><i class="fas fa-window-close"></i></span>
               <div class="search-bar">
                  <div class="search-bar-tablecell">
                     <h3>Search For:</h3>
                     <input type="text" placeholder="Keywords">
                     <button type="submit">
                        Search <i class="fas fa-search"></i>
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- end search arewa -->


   <!-- check out section -->
   <div class="checkout-section mt-150 mb-150">
      <div class="container">

         <div id="sign">
            <!-- css 803 -->
            <div class="signHeader">
               <h4>안녕하세요 ! 시크릿주주 회원가입을 환영합니다 🎉</h4>
            </div>
           
            <br>

            <div id="collapseOne" class="collapse show"
               aria-labelledby="headingOne" data-parent="#accordionExample">
               <div id="billing-address-form">
                  <form action="signup.do" method="post" onsubmit="return validateForm();">
                     <div id="name1">아이디</div>
                     <input type="email" name="mid" placeholder="이메일을 입력해주세요" required>
                     <div id="name2">비밀번호</div>
                     <input type="password" name="mpw" id="newPassword" placeholder="비밀번호를 입력해주세요"required> 
                     <input type="password" id="confirmPassword" placeholder="비밀번호를 다시 입력해주세요" required>
                     <div id="name3">닉네임</div>
                     <input type="text" name="mname" placeholder="이름을 입력해주세요" required>
                     <div id="name4">휴대폰 번호</div>
                     <input type="tel" name="mphone" placeholder="휴대폰 번호를 입력해주세요"
                        required> 
                     <input type = "hidden" name = "signUpKind" value = "주주">   
                        <input type="submit" value="회원가입 완료">
                  </form>
               </div>
            </div>
         </div>
         <!-- 회원가입 버튼 -->
         <!-- signup.css 1090 -->

      </div>
   </div>
   <!-- end check out section -->


 <script>

      // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
      
       function validateForm() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

         if (newPassword !== confirmPassword) {
           alert("비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요");
           return false;
         }

         return true;
    }
   </script>



   <!-- footer -->
   <div class="footer-box">
      <div class="footer-area">
         <div class="container">
            <h2 class="widget-title">starlight 주식회사</h2>
            <div class="info">
               <p>
                  고객센터: 070-6356-6838<br>평일 10:00 - 17:00, 주말 휴무
               </p>
            </div>
            <div class="info2">
               <p>
                  대표: 조재영 사업자등록번호: 620-81-58299 통신판매 신고번호: 2023-서울역삼-0502 <br>주소:
                  서울 강남구 테헤란로26길 12 스타빌딩, 13층(시크릿주주) 정보보호 책임자: 노기봉 <br>대표 전화:
                  070-9309-2176 이메일: secert@joojoo.com
               </p>
            </div>
         </div>
      </div>
   </div>
   <!-- end footer -->

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