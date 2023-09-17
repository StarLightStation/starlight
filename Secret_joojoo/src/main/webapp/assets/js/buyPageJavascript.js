//  쿠폰 적용을 위한 JavaScript 코드.

//  =============================================================================================

const couponModal = document.getElementById("couponModal");
const applyCouponButton = document.getElementById("applyCoupon");
const cancelCouponButton = document.getElementById("cancelCoupon");

//  =============================================================================================

//  =============================================================================================

//  선택한 쿠폰의 데이터를 저장할 변수를 선언 하기.
let selectedCoupon = {
    ucNum: null,
    cDiscount: null,
};

//  로그
let selectedCouponJSONData = JSON.stringify(selectedCoupon);
console.log("log : selectedCouponJSONData : " + selectedCouponJSONData);

//  쿠폰 이름을 클릭할 때 모달창을 표시 하고 (none -> block), 선택한 쿠폰을 식별 할 수 있는 데이터를 전달 하기.
const couponInfo = document.querySelectorAll(".couponInfo");

couponInfo.forEach(link => {
    link.addEventListener("click", (event) => {
        event.preventDefault();

        const cName = link.getAttribute("data-cName"); // 사용자 정의 속성 가져오기
        couponModal.querySelector("h3").innerText = `[ ${cName} ] 쿠폰을 적용 하시겠습니까 ?`;
        couponModal.style.display = "block";

        //  선택한 쿠폰의 데이터를 selectedCoupon 변수에 저장 하기.
        selectedCoupon = {
            ucNum: link.getAttribute("data-ucNum"),
            cDiscount: link.getAttribute("data-cDiscount"),
        };

        //  로그
        console.log("log : ucNum : " + link.getAttribute("data-ucNum") + " (쿠폰 PK)");
        console.log("log : cDiscount : " + link.getAttribute("data-cDiscount") + " (쿠폰 할인율)");

    });
});

//  =============================================================================================

//  =============================================================================================

//  모달창의 "사용" 버튼을 클릭할 때 쿠폰 사용 기능을 수행 하기.
applyCouponButton.addEventListener("click", applyCoupon);

function applyCoupon() {

    //  ⭐ 사용자가 선택한 쿠폰의 정보를 form 태그 내부 해당하는 프로퍼티의 value 값을 설정 하기 위해 작성 (해당하는 요소 (태그)를 가져온다) ⭐
    let ucNumInput = document.querySelector('input[name="ucNum"]');
    let cDiscountInput = document.querySelector('input[name="cDiscount"]');

    //  로그
    console.log("log : ucNumInput : " + ucNumInput);
    console.log("log : cDiscountInput : " + cDiscountInput);

    //  사용자가 선택한 쿠폰의 정보를 ucNumInput 요소의 value 속성 값을 저장 하고, cDiscountInput 요소의 value 속성 값을 저장 하기.
    ucNumInput.value = selectedCoupon.ucNum;
    cDiscountInput.value = selectedCoupon.cDiscount;

    //  로그
    console.log("log : ucNumInput.value : " + ucNumInput.value);
    console.log("log : cDiscountInput.value : " + cDiscountInput.value);
    
    //  사용자가 쿠폰 사용 버튼을 클릭 하면 예상 주문 총액에 할인율을 계산 하고 업데이트 하기.
    calculateAndUpdateTotal_applied();

    //  사용자가 쿠폰 사용 버튼을 클릭 하면 나타나는 모달창. ("쿠폰이 적용 되었습니다")
    openCouponAppliedModal();

}   //  applyCoupon()

//  =============================================================================================

//  =============================================================================================

//  사용자가 쿠폰 사용 버튼을 클릭 하면 예상 주문 총액에 할인율을 계산 하고 업데이트 하기.
function calculateAndUpdateTotal_applied() {

    //  ⭐ 사용자가 선택한 쿠폰의 정보를 form 태그 내부 해당하는 프로퍼티의 value 값을 설정 하기 위해 작성 (해당하는 요소 (태그)를 가져온다) ⭐
    let cDiscountInput = document.querySelector('input[name="cDiscount"]');
    let cDiscount = parseFloat(cDiscountInput.value);   //  쿠폰 할인율.

    let sessionTotal = parseInt(sessionStorage.getItem('total'));    //  세션 스토리지에 있는 예상 주문 총액.

    console.log('log : sessionTotal : ' + sessionTotal);

    //  쿠폰 적용 및 업데이트.
    if (!isNaN(cDiscount) && !isNaN(sessionTotal)) {
        let discountTotal = sessionTotal * cDiscount;  //  쿠폰 할인율을 적용 하기.
        let Total = discountTotal.toLocaleString(); // 숫자 포맷팅
        console.log('log : discountTotal : ' + discountTotal);

        let productTotalPrice = document.querySelector('#productTotalPrice');   //  예상 주문 총액을 보여주는 요소 가져 오기.
        productTotalPrice.textContent = `예상 주문 총액 : ${Total} 원`;
    }

}

//  =============================================================================================

//  =============================================================================================

//  사용자가 쿠폰 사용 버튼을 클릭 하면 나타나는 모달창. ("쿠폰이 적용 되었습니다")
function openCouponAppliedModal() {

    const couponAppliedModal = document.getElementById("couponAppliedModal");
    couponAppliedModal.style.display = "block";

    //  1.5초 후에 모든 쿠폰 관련 모달창을 자동으로 닫도록 설정 하기.
    setTimeout(() => {
        couponAppliedModal.style.display = "none";
        couponModal.style.display = "none";
    }, 1500);   //  1500 밀리초 == 1.5초

    //  모달창 내부에 존재 하는 사용자가 선택한 쿠폰 데이터를 초기화.
    selectedCoupon = null;

}

//  =============================================================================================

//  =============================================================================================

//  모달창의 "사용 안 함" 버튼을 클릭 하면 모달창을 닫기.
cancelCouponButton.addEventListener("click", () => {

    couponModal.style.display = "none";

    //  ⭐ 쿠폰 사용 버튼을 눌렀다가, 다시 쿠폰 사용 안함 버튼을 누르는 경우에 프로퍼티 value 값을 0으로 설정 ⭐
    ucNumInput = document.querySelector('input[name="ucNum"]');
    cDiscountInput = document.querySelector('input[name="cDiscount"]');

    //  ucNumInput 요소의 value 속성 값을 0으로 저장 하고, cDiscountInput 요소의 value 속성 값을 0으로 저장 하기.
    ucNumInput.value = 0;
    cDiscountInput.value = 0;

    //  로그
    console.log("log : ucNumInput.value : " + ucNumInput.value);
    console.log("log : cDiscountInput.value : " + cDiscountInput.value);
    
    //  사용자가 쿠폰 사용 안함 버튼을 클릭 하면 원래의 예상 주문 총액으로 업데이트 하기.
    calculateAndUpdateTotal_disApplied();

});

//  =============================================================================================

//  =============================================================================================

//  사용자가 쿠폰 사용 안함 버튼을 클릭 하면 원래의 예상 주문 총액으로 업데이트 하기.
function calculateAndUpdateTotal_disApplied() {

    let sessionTotal = parseInt(sessionStorage.getItem('total')); //  세션 스토리지에 있는 예상 주문 총액.

    console.log('log : sessionTotal : ' + sessionTotal);

    if (!isNaN(sessionTotal)) {
        let productTotalPrice = document.querySelector('#productTotalPrice');   //  예상 주문 총액을 보여주는 요소 가져 오기.
        let Total = sessionTotal.toLocaleString(); // 숫자 포맷팅
        productTotalPrice.textContent = `예상 주문 총액 : ${Total} 원`;
    }
}

//  =============================================================================================

//  =============================================================================================

//  쿠폰 적용 여부를 동적으로 변경 하기 위해 비동기처리 작성 하기.

$(document).ready(function () {

    $(".couponInfo").click(function () {

        //  선택한 쿠폰의 PK를 가져오기.
        //  이슈 : 사용자 정의속성은 대소문자를 구분 하지 않는다. 오로지 소문자로만 인식 한다.
        let ucnum = $(this).data("ucnum");

        console.log($(this));

        console.log("log : ucnum : " + ucnum);

        //  , (콤마) 를 사용해서 아이디 요소 두개를 한번에 처리 하고,
        //  ⭐ off("click") 을 사용해서, 핸들러가 중복으로 바인딩 되는 이슈를 막기 ⭐
        $("#applyCoupon, #cancelCoupon").off("click").click(function () {

            let buttonID = $(this).attr("id");  //  클릭한 버튼의 ID 값을 가져 오기.

            console.log('log : buttonID : ' + buttonID);

            let couponStatusData;

            if (buttonID === "applyCoupon") {    //  모달창에서 사용 하기 버튼을 클릭 했으면,
                console.log("log : applyCoupon");
                couponStatusData = 1;
            } else if (buttonID === "cancelCoupon") {   //  모달창에서 사용 안함 버튼을 클릭 했으면,
                console.log("log : cancelCoupon");
                couponStatusData = 2;
            }

            $.ajax({

                url: "couponStatus.do",
                type: "POST",
                data: {sendCouponStatusData: couponStatusData},
                success: function (result) {

                    console.log('log : success : OK');
                    console.log('log : result : ' + result);

                    let couponStatus = result;

                    //  새로운 쿠폰을 적용 할 경우, 이전에 적용한 쿠폰에 대한 "적용" 텍스트가 "미적용" 으로 업데이트 되기 위한 코드.
                    $(".couponStatus").text("미적용");

                    $(`tr[data-rowID="${ucnum}"] .couponStatus`).text(result);  //  선택한 쿠폰의 PK에 해당 하는 행의 텍스트를 업데이트.

                },
                error: function (error) {
                    console.log(' log : error [' + JSON.stringify(error) + ']');
                    console.log(' log : error [' + error.responseText + ']');
                }

            });

        });

    });

});

//  =============================================================================================

//  =============================================================================================

//   상품 상세 페이지 에서 결제 페이지로 넘어 오는 경우에만, pdata.tmpCnt 값이 존재 할 수 있다.
//   장바구니 구매 및 구독 상품 구매 시, pdata.tmpCnt 값이 존재 하지 않기 때문에,
//   form 태그 내부에서 hidden 으로 보내는 tmpCnt 값을 동적으로 처리 하기 위해 작성.
//   즉, tmpCnt 값이 있으면 그대로 서버로 값을 보내고, 없으면 해당 요소를 제거하고 나머지 값들만 서버로 보낸다.

const tmpCntElement = document.getElementById('tmpCnt');
const tmpCnt = tmpCntElement.getAttribute('data-tmpcnt');
const form = document.getElementById('buyActionForm'); 

if (tmpCnt.trim() === '') {
    const inputToRemove = form.querySelector('input[name="tmpCnt"]');
    if (inputToRemove) {
        inputToRemove.remove();
    }
}

//  =============================================================================================