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
    
    <script>
        function goBack() {
            window.history.back(); // 브라우저 뒤로 가기
        }
    </script>

</head>

<body>

    <img
            alt="에러 이미지"
            src="assets/img/king404.gif"
            style="width: 50%; height: auto; display: block; margin: 20px auto;"
    >
    <button class="error-link" onclick="goBack()">뒤로 가기</button> <!-- 메인 페이지로 돌아가기 -->


</body>

</html>
