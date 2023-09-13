<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 


<!DOCTYPE html>
<html lang="ko">
<head>
    <title>결제 성공</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
</head>
<body>
<c:set var="jsonObject" value="${JSONObject}"></c:set>
<section>

    <c:if test="${isSuccess == true}">
        <h1>결제 성공</h1>
        <p>결과 데이터 : ${jsonObject}</p>
        <p>orderName : ${jsonObject.orderName}</p>
        <p>method : ${jsonObject.method}</p>

        <script>
        // 결제 성공 메시지 띄우기
        alert("결제가 성공적으로 완료되었습니다.");

    </script>

    </c:if>

    <c:if test="${isSuccess ==  false}">
    <p>결제 실패</p>
    <script>
        alert("결제를 실패했습니다.");
        window.location.href = "main.do";
    </script>
    </c:if>



</section>
</body>
</html>