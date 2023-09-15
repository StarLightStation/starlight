
	// onLoad 함수란  DOM이 실행되고  함수가 실행 되도록 하는 함수 (HTML BODY 영역이 모두 로드 된 후 실행)
    window.onload = UnitPriceSummary;
   // 처음 페이지가 로딩 될떄  존재하는 상품의 가격 갯수를 지정하는 초기값 함수   //UnitPriceSummary(); <-- 해당 함수호출은 시작되지만  계산을 못함 
  
	function UnitPriceSummary(){
		
		// table-body-row 영역을 가지고옴 
		const rows = document.querySelectorAll(".table-body-row");
		var sSubTotal = 0;
		
		// 반복문으로 table-body-row 영역을  돌면서(i)  td가 2번쨰(이름), 3번쨰(가격) ,4번째 (갯수)를 가져옴 
		for (var i = 0; i < rows.length; i++) {
			var productName = rows[i].querySelectorAll("td")[2].innerText;                        // 이름
			var sPrice      = rows[i].querySelectorAll("td")[3].innerText.replace(/[^\w]/g, '');  // 가격    // .replace(/[^\w]/g, '') 특수문자 제거 정규식으로  숫자만 남김
			var quantityInputs = document.querySelectorAll('.product-quantity input[type="number"]');
			var sQty           = quantityInputs[i].value;   
			
			// 위에 가져온 값을  넣어주는 역활 
			rows[i].querySelectorAll("td")[5].innerText = (sPrice * sQty).toString()+ " 원"; 
			sSubTotal = sSubTotal + (sPrice * sQty); 
		}
		
		document.getElementById("subTotalTD").innerText = sSubTotal.toString()+" 원"; // 숫자이기 때문에 .toString(); 로 String 형변환
		//document.getElementById("shippingTD").innerText = "$"+sSubTotal.toString(); // 배달료 , 일단 $5로 고정

		var shippingTDValue = document.getElementById("shippingTD").innerText.replace(/[^\d.-]/g, ''); // 숫자, 점(.), 음수(-)만 남깁니다.
		var shippingValue = parseFloat(shippingTDValue); // 문자열을 숫자로 변환합니다.

		document.getElementById("totalTD").innerText = (sSubTotal + shippingValue).toString()+ " 원";
	}


// 상품의 삭제버튼을 클릭시  해당 상품 삭제 
	let originalRowCount = document.querySelectorAll('.table-body-row').length;

	
	function DeleteProduct(product){
		//console.log(originalRowCount);
		if(originalRowCount<= 1){
			alert("마지막 품목 입니다. ");
			return;
		}

		var row = document.getElementById(product);
		if (row) {
    		row.parentNode.removeChild(row);
			originalRowCount = originalRowCount-1;
 		} else {
 		  alert("Product with ID " + product + " not found.");
 		}

		 UnitPriceSummary();
	}

	
// 갯수 증가 함수 
	const tdElements = document.querySelectorAll('.product-quantity');
	tdElements.forEach(function(td) {
		const input = td.querySelector('input');	
		td.addEventListener('click', function() {
			input.value = input.value; 
			UnitPriceSummary();
		});
	});
