<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ko">

<head>
    <title>결제 성공</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" type="text/css" href="assets/css/success.css">
</head>

<body>

<section class="payment-success">

    <c:if test="${isSuccess == true}">
       <c:if test="${fail ne '재고부족'}">
           <div class="success-message">
               <h1>결제가 성공적으로 완료되었습니다.</h1>
               <p>주문이 정상적으로 처리되었습니다. 주문 내역을 확인하려면 <a href="orderHistory.do">이곳</a>을 클릭하세요.</p>
           </div>
        </c:if>
    </c:if>

    <c:if test="${isSuccess == false}">
        <div class="failure-message01">
            <h1>결제 실패</h1>
            <p>결제를 실패했습니다. 메인 페이지로 돌아가려면 <a href="main.do">이곳</a>을 클릭하세요.</p>
        </div>
    </c:if>

    <c:if test="${fail == '재고부족'}">
        <div class="failure-message02">
            <h1>결제 실패</h1>
            <p>상품의 재고가 부족 합니다. 메인 페이지로 돌아가려면 <a href="main.do">이곳</a>을 클릭하세요.</p>
        </div>
    </c:if>

</section>

</body>

</html>