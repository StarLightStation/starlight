var modals = document.getElementsByClassName("modal");
// Modal을 띄우는 클래스 이름을 가져옵니다.
var btns = document.getElementsByClassName("btn");
// Modal을 닫는 close 클래스를 가져옵니다.
var spanes = document.getElementsByClassName("close");
var funcs = [];

// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
function Modal(num) {
    return function () {
        // 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
        btns[num].onclick = function () {
            modals[num].style.display = "block";
            console.log(num);
        };

        // <span> 태그(X 버튼)를 클릭하면 Modal이 닫습니다.
        spanes[num].onclick = function () {
            modals[num].style.display = "none";
        };
    };
}

function updateStarRating(input) {
    const ratingValue = input.value;
    const starsContainer = input.parentNode.querySelector('.rating_star');
    const starWidth = `${ratingValue * 20}%`;
    starsContainer.style.width = starWidth;
}

// 모든 별점 input에 이벤트 리스너를 추가하여 변경사항 처리
const ratingInputs = document.querySelectorAll('.rating input');
ratingInputs.forEach(input => {
    updateStarRating(input); // 초기 별점 표시
    input.addEventListener('input', () => updateStarRating(input)); // 별점 입력 변경시 업데이트
});

// Modal을 가져옵니다.
var modals = document.getElementsByClassName("modal");
// Modal을 띄우는 클래스 이름을 가져옵니다.
var btns = document.getElementsByClassName("btn");
// Modal을 닫는 close 클래스를 가져옵니다.
var spanes = document.getElementsByClassName("close");
var funcs = [];

// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
function Modal(num) {
    return function () {
        // 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
        btns[num].onclick = function () {
            modals[num].style.display = "block";
        };

        // <span> 태그(X 버튼)를 클릭하면 Modal이 닫힙니다.
        spanes[num].onclick = function () {
            modals[num].style.display = "none";
        };
    };
}

// 원하는 Modal 수만큼 Modal 함수를 호출하여 funcs 함수에 정의합니다.
for (var i = 0; i < btns.length; i++) {
    funcs[i] = Modal(i);
}

// 원하는 Modal 수만큼 funcs 함수를 호출합니다.
for (var j = 0; j < btns.length; j++) {
    funcs[j]();
}

// Modal 영역 밖을 클릭하면 Modal이 닫힙니다.
window.onclick = function (event) {
    if (event.target.className == "modal") {
        event.target.style.display = "none";
    }
};

//  =============================================================================================

//  마이페이지 -> 내가 쓴 리뷰 -> 삭제 버튼 -> 모달창 아니오 버튼 누를시 모달창 닫기 기능 코드.

document.getElementById("closeButton").addEventListener("click", function () {
    var modal = document.querySelector(".modal");
    modal.style.display = "none";
});

//  =============================================================================================