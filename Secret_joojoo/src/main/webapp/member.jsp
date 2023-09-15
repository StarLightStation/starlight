<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <!-- 마이페이지 추가 CSS -->
    <link rel="stylesheet" href="assets/css/myPageExpansion.css">
    <!-- member style -->
    <link rel="stylesheet" href="assets/css/member.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">

    <!-- ============================================================== -->

</head>
<body>

<starlight:login/>

<starlight:mypage/>

<!-- 마이페이지 회원 정보 업데이트 -->
<div class="cart-section mt-150 mb-150" >

    <div class="container">

        <div class="row">

            <div class="Sub-table-wrap"><!-- main.css 2596 -->

                <div class="member-info-wrapper">
                <section style="width: 1040px">
                    <div class="name-header">
                         <h5 class="name-text" style="text-align: left; color: #575757">회원 정보 수정</h5>
                    </div>
                    <hr>


                    <c:if test="${mdata.signUpKind eq '구글'}">
                    <!-- Account -->
                    <div class="card-body">
                        <form id="updateMemberForm_Google" action="updateMember.do" method="POST" onsubmit="return validateForm();">

                            <div class="row">

                                <div class="mb-3 col-md-6">
                                    <label for="googlemID" class="form-label">이메일</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="googlemID"
                                            name="mID"
                                            value="${mdata.mID}"
                                            placeholder="이메일을 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="googlemPW" class="form-label">비밀번호</label>
                                    <input
                                            class="form-control"
                                            type="password"
                                            id="googlemPW"
                                            name="tmpMpw"
                                            placeholder="구글 비밀번호는 구글에 문의해 주세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="googlemName" class="form-label">이름 (닉네임)</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="googlemName"
                                            name="tmpMname"
                                            value="${mdata.mName}"
                                            placeholder="이름 (닉네임) 을 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="googlesubscription" class="form-label">구독 여부</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="googlesubscription"
                                            value="${mdata.subscription == 0 ? '구독 ❌' : '구독 중'}"
                                            placeholder="구독 여부 정보를 입력 하세요."
                                            required="required"
                                            min="0"
                                            max="1"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="googlesignUpKind" class="form-label">가입 종류</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="googlesignUpKind"
                                            name="signUpKind"
                                            value="${mdata.signUpKind}"
                                            placeholder="${mdata.signUpKind}"
                                            readonly="readonly"
                                    />
                                </div>

                            </div>

                            <div class="mt-2">
                                <button type="submit" class="btn btn-primary me-2">회원 정보 수정</button>
                                <button type="reset" class="btn btn-outline-secondary">원래 대로</button>
                            </div>

                        </form>

                        </c:if>


                    <c:if test="${mdata.signUpKind eq '주주'}">
                    <!-- Account -->
                    <div class="card-body">
                        <form id="updateMemberForm_JOOJOO" action="updateMember.do" method="POST">

                            <div class="row">

                                <div class="mb-3 col-md-6">
                                    <label for="joojoomID" class="form-label">이메일</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="joojoomID"
                                            name="mID"
                                            value="${mdata.mID}"
                                            placeholder="이메일을 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="joojoomPW" class="form-label">비밀번호</label>
                                    <input
                                            class="form-control"
                                            type="password"
                                            id="joojoomPW"
                                            name="tmpMpw"
                                            value="${mdata.mPW}"
                                            placeholder="비밀번호를 입력 하세요."
                                            required="required"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="joojoomName" class="form-label">이름 (닉네임)</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="joojoomName"
                                            name="tmpMname"
                                            value="${mdata.mName}"
                                            placeholder="이름 (닉네임) 을 입력 하세요."
                                            required="required"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="joojoosubscription" class="form-label">구독 여부</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="joojoosubscription"
                                            value="${mdata.subscription == 0 ? '구독 ❌' : '구독 중'}"
                                            placeholder="구독 여부 정보를 입력 하세요."
                                            required="required"
                                            min="0"
                                            max="1"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="joojoophoneNumber" class="phoneNumber">전화번호</label>
                                    <div class="input-group input-group-merge">
                                        <span class="input-group-text">KO (+82)</span>
                                        <input
                                                class="form-control"
                                                type="number"
                                                id="joojoophoneNumber"
                                                name="tmpMphone"
                                                value="${mdata.mPhone}"
                                                placeholder="${mdata.mPhone}"
                                        />
                                    </div>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="joojoosignUpKind" class="form-label">가입 종류</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="joojoosignUpKind"
                                            name="signUpKind"
                                            value="${mdata.signUpKind}"
                                            placeholder="${mdata.signUpKind}"
                                            readonly="readonly"
                                    />
                                </div>

                            </div>

                            <div class="mt-2">
                                <button type="submit" class="btn btn-primary me-2">회원 정보 수정</button>
                                <button type="reset" class="btn btn-outline-secondary">원래 대로</button>
                            </div>

                        </form>

                        </c:if>
                        <div id="updateMember">
                            <button id="deleteMember">회원탈퇴</button>
                        </div>
				</div>
                </section>
                    </div>
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
            <a href="deleteMember.do" class="true">예</a>
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
        if (evTarget.classList.contains("modal-overlay")) {
            modal.style.display = "none"
        }
    })
</script>

<script>
	function validateForm() {
	    var brn = document.getElementById("joojoophoneNumber").value;
	
	    // 하이픈을 제거하고 유효성 검사를 수행합니다.
	    var cleanedBrn = brn.replace(/-/g, ''); // 하이픈을 제거합니다.
	
	    if (!/^[0-9]{11}$/.test(cleanedBrn)) {
	        alert("유효하지 않은 휴대폰 번호입니다. 다시 입력해주세요.");
	        return false;
	    }
	
	    var newPasswordValue = document.getElementById("newPassword").value;
	    var confirmPasswordValue = document.getElementById("confirmPassword").value;
	
	    if (newPasswordValue !== confirmPasswordValue) {
	        alert("변경하실 비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
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