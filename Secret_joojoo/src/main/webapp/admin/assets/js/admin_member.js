//  adminMemberInfoMain.jsp
//  deleteMember_form
function checkAlert() {
    let checkBox = document.getElementById("deleteMember_input");

    if (checkBox.checked) {	//	체크 박스가 체크 됬다면
        document.getElementById("deleteMember_form").submit();
    } else {	//	체크 박스가 비어 있다면
        alert("체크 박스를 클릭해 주세요.");
    }
}

//  adminMemberInfoMain.jsp
//  deleteMember_form
function checkSweetAlert() {
    let checkBox = document.getElementById("deleteMember_input");

    if (checkBox.checked) {	//	체크 박스가 체크 됬다면
        document.getElementById("deleteMember_form").submit();  //  .then();
    } else {	//	체크 박스가 비어 있다면
        swal('계 정 삭 제 실 패', '체크 박스를 클릭해 주세요.', 'warning');
    }

}

//  adminMemberInfoReview.jsp
//  updateReviewForm
//  document.querySelectorAll(".shortContent").forEach(span => {    //  콜백 함수 코드.
//      const bContent = span.dataset.content;  //  data-content 속성을 사용해서, 데이터 가져오기.
//      span.textContent = shortContentFunction(bContent);
//  })

//  adminMemberInfoReview.jsp
//  updateReviewForm
//  forEach 를 루프해서 나오는 모든 버튼의 각 요소에 ${board.bContent} 를 표현 하기.
const buttons = document.querySelectorAll(".shortContent");
for (let i = 0; i < buttons.length; i++) {    //  for 문을 사용한 코드.
    const button = buttons[i];
    const bContent = button.getAttribute("data-bContent"); //  data-bContent 속성 값 가져오기.
    button.textContent = shortContentFunction(bContent);    //  아래의 함수를 호출.
}

//  adminMemberInfoReview.jsp
//  updateReviewForm
//  리뷰 내용을 일정 길이로 줄이기 위해 작성.
function shortContentFunction(bContent) {
    const maxLength = 30;   //  보이게 할 글자 수
    return bContent.length > maxLength ? bContent.substring(0, maxLength) + ".." : bContent;
}

//  adminMemberInfoReview.jsp
//  ContentModal
//  리뷰 내용이 길면 글을 줄이고, 모달로 처리 하기.

//  리뷰 모달창 열기 함수.
//  ${board.bContent} 를 모달창에 표현 하기 위한 함수.
function openContentModal(bContent) {
    const openModal = document.getElementById("ContentModal");
    const contentModal = document.getElementById("modal_bContent");
    contentModal.textContent = bContent;
    openModal.style.display = "block";
}

//  리뷰 모달창 닫기 함수.
function closeContentModal(bContent) {
    const closeModal = document.getElementById("ContentModal");
    closeModal.style.display = "none";
}

// ESC 키 누를 시 모달 닫기
document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
        closeContentModal();
    }
});

//  이벤트 핸들러.
//  ${board.bContent} 를 모달창에 표현 하기 위한 함수.
const spansModal = document.querySelectorAll(".shortContent");
for (let i = 0; i < spansModal.length; i++) {
    const spanModal = spansModal[i];
    spanModal.addEventListener("click", function () {
        const bContentModal = spanModal.getAttribute("data-bContent");
        openContentModal(bContentModal);
    });
}

//  리뷰 모달창의 닫기 버튼에 이벤트 핸들러 연결.
const closeModalButton = document.querySelector("#ContentModal .close");
if (closeModalButton) {
    closeModalButton.addEventListener("click", closeContentModal);
}
