<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">
   
   
   <title>시크릿주주 - 로그인</title>
   <link rel="stylesheet" href="assets/css/login.css"><!-- 쿠팡로그인 따라하기-->
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
   <!-- main style -->
   <link rel="stylesheet" href="assets/css/main.css">
   <!-- responsive -->
   <link rel="stylesheet" href="assets/css/responsive.css">
   
   <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
   
   <script src="javascript/javascript.js"></script><!-- 쿠팡로그인 동작 따라하기 -->
   
   <!-- jQuery 라이브러리 가져오기.  -->
   <script src="https://code.jquery.com/jquery-3.7.0.min.js" 
      integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous">//jQuery</script>
      
<!-- ---------------------------------------------------------------------------------------------------------- -->

   <!-- Google API를 사용하기 위한 스크립트 -->
   <script src="https://accounts.google.com/gsi/client" async defer>//Google api</script>
   
</head>

<body>


<starlight:login/>


<div class="login-box">

   <h2 id="hello" style="margin-top: 120px; font-size: 26px; font-weight: 700;">
   반가워요 ! 🎉</h2> <!-- 로고 -->
   <h4 id="logo-ment" style="font-size: 16px; font-weight: 400;">
   고생한 당신의 소확행, 시크릿주주 입니다.</h4>


	



<div id="login">
   <main>
      <div class="snslogin">
         <!-- 구글 로그인 버튼 -->
         <div class="google_login">
            <div id="g_id_onload"
               data-client_id="233286703693-2iq22q0dsc7s510bl5ue6o3l113okve3.apps.googleusercontent.com"
               data-callback="handleCredentialResponse"></div>
            <div class="g_id_signin" data-type="standard" data-size="large" data-text="signin_with"
               data-shape="rectangular" data-width=300 data-radius=15px></div>
         </div>
         
            <br>
         
      
      
<!--------------------------------- 디자인 영역 : 이메일 로그인 ---------------------------------------------->      
      
      <button class="button" id="emailLogin">이메일 로그인</button>
      	<div id="loginbar" style="display: none;">
         <form  name="form" action="login.do" method="post" id="loginform">
            <fieldset>
               <legend class="skip">로그인 양식</legend>
               <ul>
                  <li>
                     <span><input type="text" name="mid" placeholder="이메일을 입력해주세요"></span>
                  </li>
                  <li class="error id_error">아이디를 입력해주세요.</li>
                  <li>
                     <span><input type="password" name="mpw" placeholder="비밀번호를 입력해주세요"></span>
                  </li>
                  <li class="error pw_error">비밀번호를 입력해주세요.</li>
               </ul>
               
               <button type="submit" id="login_btn">로그인</button>
            </fieldset>
         </form>
        	 <a href="signupPage.do" class="join_link">회원가입</a>
      		<a href="searchIDPage.do" class="join_searchID">아이디 찾기</a>
      	</div>
      </div>
   </main>
</div>

<!----------------------------------------------- 디자인영역 끝 ----------------------------------------------------->
   <footer>  
      &copy; StarlightStation Corp. All rights reserved.
   </footer>

</div>
   

<!--------------------------------- 기능 영역 : 구글 로그인 ---------------------------------------------->      

   <script>
      // ---[ 구글 로그인 ]-------------------------------------------------------------------------------------------------------------------------------------------------
      function handleCredentialResponse(response) {
         // decodeJwtResponse() is a custom function defined by you
         // to decode the credential response.
         const responsePayload = parseJwt(response.credential);

         $('#status').append(responsePayload.name);
         //$('#status').append('<li>ID: ' + responsePayload.sub + '</li>');
         //$('#status').append('<li>Email: ' + responsePayload.email + '</li>');
         //$('#status').append('<li>Name: ' + responsePayload.name + '</li>');
         //$('#status').append('<li>Image: <img src=" ' + responsePayload.picture + '"></li>');
         console.log("구글 ID : " + responsePayload.sub);
         console.log("구글 Email : " + responsePayload.email);
         console.log("구글 Name : " + responsePayload.name);
         console.log("구글 프로필 사진 URL: " + responsePayload.picture);
         
         const params = new URLSearchParams();
         params.append('googleID', responsePayload.sub);
         params.append('googleEmail', responsePayload.email);
         params.append('googleName', responsePayload.name);
         params.append('googlePicture', responsePayload.picture);

         // 새로운 URL로 이동합니다. 여기에 목적지 URL을 입력하세요.
         const destinationURL = 'googleLogin.do?' + params.toString();
         window.location.href = destinationURL;
         
      };

      function parseJwt(token) {
         var base64Url = token.split('.')[1];
         var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
         var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
         }).join(''));
         return JSON.parse(jsonPayload);
      };
      // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
   </script>
<!--------------------------------- 기능 영역 : 구글 로그인 ---------------------------------------------->      



    
   <script type="text/javascript">
    
   
   $(document).ready(function() {
        $("#emailLogin").click(function() {
          var $loginbar = $("#loginbar");
          if ($loginbar.is(":hidden")) {
            $loginbar.show();
          } 
        });
      });
   </script>

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
   
   <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/jquery.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>


</body>

</html>