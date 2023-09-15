<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>에러 페이지</title>

    <!-- 스타일 -->
    <link rel="stylesheet" href="assets/css/errorPage.css">

</head>

<body>

<div class="error-container">
    <div class="error-title">
        <c:out value="${exception}"/>
    </div>
    <div class="error-message">
        <c:out value="${exception.message}"/>
    </div>
    <img
            alt="에러 이미지"
            src="assets/img/errorTest02.jpg"
            style="max-width: 100%; height: auto; display: block; margin: 20px auto;"
    >
    <a class="error-link" href="main.do">이미지 예쁘게 바꿔 주기 (다운 누나 ^ㅠ^)</a> <!-- 메인 페이지로 돌아가기 -->
</div>

</body>

</html>
