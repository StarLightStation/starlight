
  // ---- [ 중복검사가 맞지 않을때  ] --------------------------------------------------------------------------------------------------------------------------
      
      function validateForm() {
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (newPassword !== confirmPassword) { // 중복검사가 맞지 않을때 
        alert("비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요");
        return false;
    }

 // ---- [ 휴대폰번호가 맞지 않을때  ] --------------------------------------------------------------------------------------------------------------------------
    
    
    var brn = document.getElementById("signupphoneNumber").value;
   
    // 하이픈을 제거하고 유효성 검사를 수행합니다.
    var cleanedBrn = brn.replace(/-/g, ''); // 하이픈을 제거합니다.

    if (!/^[0-9]{11}$/.test(cleanedBrn)) {
        alert("유효하지 않은 휴대폰 번호입니다. 다시 입력해주세요.");
        return false;
    }
    return true;
}

//  =============================================================================================

//  회원가입 페이지 추천인 유효성 검사.

console.log("log 11111 : recommendationValue : " + recommendationValue);

$(document).ready(function () {

    var successMsg = "유효한 추천인 입니다.";
    var failMsg = "유효 하지 않은 추천인 입니다.."

    //  추천인 확인 버튼 클릭 이벤트 처리 하기.
    $("#recommendationValueCheck").click(function () {

        const recommendationValue = $("#recommendationValue").val();

        console.log("log 22222 : recommendationValue : " + recommendationValue);

        $.ajax({
            type: "POST",
            url: "recommendationVaildity.do",
            data: {tmpmID: recommendationValue},    //  추천인 정보 전송 하기.
            success: function (jdata) {

                console.log('success OK');
                console.log('jdata : ' + jdata);

                if (jdata === "fail") {  //  유효하지 않은 추천인 일 경우.
                    $('#recommendationResultMessage')
                        .removeClass("vaildity") // 유효한 추천인 일 경우 뜨는 스타일 클래스 제거 하기.
                        .addClass("fail")   //  유효하지 않은 추천인 일 경우 뜨는 스타일 클래스 생성 하기.
                        .text(failMsg);
                } else {  //  유효한 추천인 일 경우.
                    $('#recommendationResultMessage')
                        .removeClass("fail")    // 유효하지 않은 추천인 일 경우 뜨는 스타일 클래스 제거 하기.
                        .addClass("vaildity")    // 유효한 추천인 일 경우 뜨는 스타일 클래스 생성 하기.
                        .text(successMsg);
                    $('#recommendationtmpmID').val(recommendationValue);    //  유효한 추천인 이면 hidden 값으로 submit 하기.
                }

                //  메세지가 있는 경우 active 라는 이름의 클래스를 추가 하고, 메세지가 없는 경우 active 라는 이름의 클래스 제거 하기.
                if ($('#recommendationResultMessage').text().trim() !== "") {
                    $('#recommendationResultMessage').addClass("active");
                } else {
                    $('#recommendationResultMessage').removeClass("active");
                }
            },
            error: function (error) {
                console.log('error [' + error + ']');
            }
        });
    });
});

//  =============================================================================================