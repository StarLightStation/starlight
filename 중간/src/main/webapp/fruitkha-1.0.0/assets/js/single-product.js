// JavaScript 코드를 추가합니다.
const container = document.querySelector('.container');
const productBuy = document.querySelector('.product-buy');

// 스크롤 위치에 따라 .product-buy 요소의 위치를 조정합니다.
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

// 페이지 로드 시 초기 위치 설정
updateProductBuyPosition();



//수량 옵션
$('._count :button').on({
    'click' : function(e){
        e.preventDefault();
        var $count = $(this).parent('._count').find('.inp');
        var now = parseInt($count.val());
        var min = 1;
        var max = 999;
        var num = now;
        if($(this).hasClass('minus')){
            var type = 'm';
        }else{
            var type = 'p';
        }
        if(type=='m'){
            if(now>min){
                num = now - 1;
            }
        }else{
            if(now<max){
                num = now + 1;
            }
        }
        if(num != now){
            $count.val(num);
        }
    }
});