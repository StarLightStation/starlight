//  JavaScript 코드를 추가합니다.
const container = document.querySelector('.container');
const productBuy = document.querySelector('.product-buy');

//  스크롤 위치에 따라 .product-buy 요소의 위치를 조정합니다.
function updateProductBuyPosition() {
    const containerRect = container.getBoundingClientRect();
    const containerBottom = containerRect.top + containerRect.height;
    const windowBottom = window.scrollY + window.innerHeight;

    if (windowBottom >= containerBottom) {
        productBuy.classList.add('sticky');
    } else {
        productBuy.classList.remove('sticky');
    }
}

window.addEventListener('scroll', updateProductBuyPosition);
window.addEventListener('resize', updateProductBuyPosition);

//  페이지 로드 시 초기 위치 설정
updateProductBuyPosition();

//  수량 옵션
$('._count :button').on({
    'click': function (e) {
        e.preventDefault();
        var $count = $(this).parent('._count').find('.inp');
        console.log("뭐임 넌 : " + $count)
        var now = parseInt($count.val());
        console.log("넌또 뭐임 : " + now);
        
     // 해당 <input> 요소를 선택
        var pCntInput = document.querySelector('input[name="pCnt"]');

        // <input> 요소의 value 값을 가져옴
        var pCntValue = pCntInput.value;

        // pCntValue를 사용하여 원하는 작업 수행
        console.log("상품 재고: " + pCntValue);
        
        
        var min = 1;
        var num = now;
        if ($(this).hasClass('minus')) {
            var type = 'm';
        } else {
            var type = 'p';
        }
        if (type == 'm') {
            if (now > min) {
                num = now - 1;
            }
        } else {
            // 이 부분에서 상품의 재고 값을 가져와서 비교합니다.
            var max = pCntValue; // 상품의 재고를 최대 수량으로 설정
            console.log("넌 그래서 뭔데 : " + pCntValue);
            if (now < max) {
                num = now + 1;
            } else {
                alert("상품 재고가 부족합니다.");
            }
        }
        if (num != now) {
            $count.val(num);
        }
    }
});

//  =============================================================================================

//  상품이 0개인 경우, 구매 하기 버튼 + 장바구니 버튼을 비활성화 하기.

const productCountElement = document.getElementById("product-count-zero");   //  HTML 에서 상품 개수 요소 가져 오기.


//  HTML 상품 개수 요소의 내용 (텍스트) 가져 오기. ("상품 재고가 없습니다.")
const productCountText = productCountElement.textContent;

if (productCountText.trim() === "상품 재고가 없습니다.") {  //  상품 개수가 0 이라면,
    const payButton = document.getElementById("pay");
    payButton.disabled = true;  //  구매 하기 버튼을 비활성화.
   
}

//  =============================================================================================