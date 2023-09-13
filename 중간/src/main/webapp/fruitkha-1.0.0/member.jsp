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
<title>마이페이지 회원정보</title>

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
<!-- member style -->
<link rel="stylesheet" href="assets/css/member.css">
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
					<section style="width: 1000px">
						<div class="name-header">
							<span class="name-text">회원정보</span>
							<span class="name-label"></span>
						</div>
					<hr>
						<div class="side-bar-wrapper">
							<form action="updatemember.do" method="post" onsubmit="return validateForm();">
								이메일｜&nbsp;${mid}<br>
								핸드폰번호｜&nbsp;${data.mPhone}<br>
								<div>
								비밀번호 &nbsp;&nbsp;<input type="password" id="newPassword" name="mpw" value="${data.mPW}" required placeholder="변경할 비밀번호 입력"><br>
								재확인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="confirmPassword" name="confirmPassword" value="${data.mPW}" required placeholder="비밀번호 확인"><br>
								이름 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<input type="text" name="mname" value="${data.mName}" placeholder="변경할 이름 입력"> <br>
								<input type="submit" value="수정완료">
						 	</form>
						</div>
						<div id="updateMember">
					<button id="deleteMember">회원탈퇴</button>
				</div>
					</section>
					</div>							
				</div>
				
			</div>

		</div>
	</div>
	
		 <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h4>회원을 탈퇴 하시겠습니까 ? 😢</h4>
            </div>
            <div class="close-area">X</div>
            <div class="content">
				<a href="deletemember.do" class="true">예</a>
				<button class="false">아니오</button>                
            </div>
        </div>
    </div>
    <script>
    const modal = document.getElementById("modal")
    
    const btnModal1 = document.getElementById("deleteMember")
    modal.style.zIndex = '9999';
    btnModal1.addEventListener("click", e => {
        modal.style.display = "flex"
    })
    
    const closeBtn = modal.querySelector(".close-area")
	closeBtn.addEventListener("click", e => {
    modal.style.display = "none"
	})
    const falseBtn = modal.querySelector(".false")
	falseBtn.addEventListener("click", e => {
    modal.style.display = "none"
	})

	modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modal.style.display = "none"
    }
	})
    </script>
	
	 <script>
    	function validateForm() {
      		var newPassword = document.getElementById("newPassword").value;
      		var confirmPassword = document.getElementById("confirmPassword").value;

      	if (newPassword !== confirmPassword) {
        	alert("변경하실 비밀번호가 일치하지 않습니다. 다시 확인해주세요");
        	return false;
      	}

      	return true;
    }
  </script>

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