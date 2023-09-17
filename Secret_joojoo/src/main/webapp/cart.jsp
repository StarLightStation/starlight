<!DOCTYPE html>
<html lang="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>시크릿주주 - 장바구니</title>

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

</head>
<body>

<starlight:login/>

<!-- breadcrumb-section -->
<div class="breadcrumb-section breadcrumb-bg">
    <!--<div class="container">
     <div class="row">
        <div class="col-lg-8 offset-lg-2 text-center">
           <div class="breadcrumb-text">
              <p>Fresh and Organic</p>
              <h1>Cart</h1>
           </div>
        </div>
     </div>
  </div> -->
</div>
<!-- end breadcrumb section -->

<!-- cart -->
<div class="cart-section mt-150 mb-150">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-12">
                <div class="cart-table-wrap">
                    <table class="cart-table">
                        <thead class="cart-table-head">
                        <tr class="table-head-row">
                            <th class="product-remove"></th>
                            <th class="product-image"><strong>상품 이미지</strong></th>
                            <th class="product-name">상품 이름</th>
                            <th class="product-price">상품 가격</th>
                            <th class="product-quantity">상품 개수</th>
                            <th class="product-total">총 가격</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${cart}" var="cartItem" varStatus="status">
                            <tr class="table-body-row" id="P${status.index}">
                                <td class="product-remove"><a
                                        href="javascript:DeleteProduct('P${status.index}' ,'${cartItem.pNum}');">
                                    <i class="far fa-window-close"></i>
                                </a></td>
                                <td class="product-image"><img
                                        src="assets/img/products/${cartItem.pImage}" alt=""></td>
                                <td class="product-name">${cartItem.pName}</td>
                                <fmt:formatNumber var="pPrice" value="${cartItem.pPrice}"/>                                 
                                <td class="product-price">${pPrice} 원</td>
                                <td class="product-quantity"><input type="number"
                                                                    value="${cartItem.tmpCnt}" id="pQTY${cartItem.pNum}"
                                                                    min="1"
                                                                    data-key="${cartItem.pNum}"></td>
                                <td class="product-total">${totalPrice}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <script>
                // onLoad 함수란  DOM이 실행되고  함수가 실행 되도록 하는 함수 (HTML BODY 영역이 모두 로드 된 후 실행)
                window.onload = UnitPriceSummary;

                // 처음 페이지가 로딩 될떄  존재하는 상품의 가격 갯수를 지정하는 초기값 함수   //UnitPriceSummary(); <-- 해당 함수호출은 시작되지만  계산을 못함

                
                function formatPrice(price) {
                	 // 1000원 단위로 포맷팅
                    return new Intl.NumberFormat('ko-KR').format(price) + ' 원';
				}
                
                function UnitPriceSummary() {

                    // table-body-row 영역을 가지고옴
                    const rows = document
                        .querySelectorAll(".table-body-row");
                    var sSubTotal = 0;

                    // 반복문으로 table-body-row 영역을  돌면서(i)  td가 2번쨰(이름), 3번쨰(가격) ,4번째 (갯수)를 가져옴
                    for (var i = 0; i < rows.length; i++) {
                        var productName = rows[i].querySelectorAll("td")[2].innerText; // 이름
                        var sPrice = rows[i].querySelectorAll("td")[3].innerText
                            .replace(/[^\w]/g, ''); // 가격    // .replace(/[^\w]/g, '') 특수문자 제거 정규식으로  숫자만 남김
                        var quantityInputs = document
                            .querySelectorAll('.product-quantity input[type="number"]');
                        var sQty = quantityInputs[i].value;

                        // 위에 가져온 값을  넣어주는 역활
                        rows[i].querySelectorAll("td")[5].innerText = formatPrice(sPrice * sQty);
                        sSubTotal = sSubTotal + (sPrice * sQty);
                    }

                    document.getElementById("subTotalTD").innerText = formatPrice(sSubTotal); // 숫자이기 때문에 .toString(); 로 String 형변환
                    //document.getElementById("shippingTD").innerText = "$"+sSubTotal.toString(); // 배달료 , 일단 $5로 고정

                    var shippingTDValue = document
                        .getElementById("shippingTD").innerText
                        .replace(/[^\d.-]/g, ''); // 숫자, 점(.), 음수(-)만 남깁니다.
                    var shippingValue = parseFloat(shippingTDValue); // 문자열을 숫자로 변환합니다.

                    document.getElementById("totalTD").innerText = formatPrice(sSubTotal + shippingValue);

                }

                // 상품의 삭제버튼을 클릭시  해당 상품 삭제
                let originalRowCount = document
                    .querySelectorAll('.table-body-row').length;

                function DeleteProduct(product, key) {
                    //console.log(originalRowCount);

                    if (originalRowCount <= 1) {
                        alert("last proudct ");
                        return;
                    }

                    var row = document.getElementById(product);
                    //console.log("row out"+key);
                    if (row) {
                        console.log("key : " + key);
                        row.parentNode.removeChild(row);
                        originalRowCount = originalRowCount - 1;
                        //이부분에서 ajax 때려보기

                        $.ajax({
                            url: 'cartDelete.do',
                            method: 'POST', // or 'POST', 'PUT', 'DELETE', etc.
                            data: {pNum: key},
                            success: function (response) {
                                // Code to execute when the request is successful
                                console.log(response);
                            },
                            error: function (xhr, status, error) {
                                // Code to execute when the request encounters an error
                                console.error(error);
                            }
                        });

                    } else {
                        alert("Product with ID " + product + " not found.");
                    }

                    UnitPriceSummary();
                }

                // 갯수 증가 함수
                document
                    .addEventListener(
                        'click',
                        function (event) {
                            console.log('확인1');
                            if (event.target
                                && event.target
                                    .matches('.product-quantity input[type="number"]')) {
                                console.log('확인2');
                                const input = event.target;
                                const key = input.dataset.key;
                                const count = input.value;
                                console.log(input.value);
                                console.log(key);

                                var updateData = {pNum: key, tmpCnt: count}; // JSON 데이터
                                //const input = event.target;
                                //const key = input.dataset.key; // Access the 'key' value from the data attribute

                                $.ajax({
                                    url: 'update.do',
                                    data: updateData,

                                    method: 'POST',
                                    success: function (response) {
                                        // Code to execute when the request is successful
                                        console.log(response);
                                    },
                                    error: function (xhr, status,
                                                     error) {
                                        // Code to execute when the request encounters an error
                                        console.error(error);
                                    }
                                });

                                UnitPriceSummary();

                            }
                        });

                // Rest of your code...
            </script>
            <div class="col-lg-4">
                <div class="total-section">
                    <table class="total-table">
                        <thead class="total-table-head">
                        <tr class="table-total-row">
                            <th>전체</th>
                            <th>가격</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="total-data">
                            <td><strong>총 가격 : </strong></td>
                            <td id="subTotalTD">￦0</td>
                        </tr>
                        <tr class="total-data">
                            <td><strong>그 외 : </strong></td>
                            <td id="shippingTD">0원</td>
                        </tr>
                        <tr class="total-data">
                            <td><strong>전체 가격 : </strong></td>
                            <td id="totalTD">￦0</td>
                        </tr>
                        <tr class="total-data">
                            <td colspan="2" style="text-align: center;">
                                <c:if
                                        test="${not empty mID}">
                                <a href="buyPage.do?cartFlag=cart" class="boxed-btn black">구매하기</a></td>
                            </c:if>
                            <c:if test="${empty mID}">
                                <a href="login.do" class="boxed-btn black">구매하기</a>
                                </td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
                    <!--
              <div class="cart-buttons">
                 <a href="cart.html" class="boxed-btn">Update Cart</a>
                 <a href="checkout.html" class="boxed-btn black">Check Out</a>
              </div>
              -->
                </div>
                <!--
           <div class="coupon-section">
              <h3>Apply Coupon</h3>
              <div class="coupon-form-wrap">
                 <form action="index.html">
                    <p><input type="text" placeholder="Coupon"></p>
                    <p><input type="submit" value="Apply"></p>
                 </form>
              </div>
           </div>
           -->
            </div>
        </div>
    </div>
</div>
<!-- end cart -->

<!-- logo carousel
<div class="logo-carousel-section">
  <div class="container">
     <div class="row">
        <div class="col-lg-12">
           <div class="logo-carousel-inner">
              <div class="single-logo-item">
                 <img src="assets/img/company-logos/1.png" alt="">
              </div>
              <div class="single-logo-item">
                 <img src="assets/img/company-logos/2.png" alt="">
              </div>
              <div class="single-logo-item">
                 <img src="assets/img/company-logos/3.png" alt="">
              </div>
              <div class="single-logo-item">
                 <img src="assets/img/company-logos/4.png" alt="">
              </div>
              <div class="single-logo-item">
                 <img src="assets/img/company-logos/5.png" alt="">
              </div>
           </div>
        </div>
     </div>
  </div>
</div>
end logo carousel -->

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
<!-- ajax -->
<script
        src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
        crossorigin="anonymous"
/>

</body>

</html>