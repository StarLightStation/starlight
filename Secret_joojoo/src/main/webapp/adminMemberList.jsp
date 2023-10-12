<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<!DOCTYPE html>
<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="admin/assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>관리자 - 회원 관리</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="admin/assets/img/favicon/favicon.ico"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="admin/assets/vendor/fonts/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="admin/assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="admin/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="admin/assets/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <link rel="stylesheet" href="admin/assets/vendor/libs/apex-charts/apex-charts.css"/>

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="admin/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="admin/assets/js/config.js"></script>
</head>

<body>

<!-- 관리자 메뉴 -->
<starlight:admin/>

<!-- Hoverable Table rows -->

<!-- Content wrapper -->
<div class="content-wrapper">

    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">회원정보 / </span>회원관리</h4>

        <!-- Basic Bootstrap Table -->
        <div class="card">
            <h5 class="card-header">회원 전체 목록</h5>
            <div class="table-responsive text-nowrap">

                <table class="table table-hover">

                    <thead>
                    <tr>
                        <th>이메일</th>
                        <th>비밀번호</th>
                        <th>이름 (닉네임)</th>
                        <th>구독 여부</th>
                        <th>관리자 여부</th>
                        <th>전화번호</th>
                        <th>가입 종류</th>
                        <th>회원 등급</th>
                    </tr>
                    </thead>

                    <tbody class="table-border-bottom-0">
                    <c:forEach items="${mdatas}" var="member">

                        <tr>
                            <td><a href="memberInfoMain.do?mID=${member.mID}">${member.mID}</a></td>
                            <td>${member.mPW}</td>
                            <td>${member.mName}</td>
                            <td>${member.subscription == 0 ? '구독 X' : '구독 O'}</td>
                            <td>${member.isAdmin == 0 ? '일반 회원' : '관리자'}</td>
                            <td>${member.mPhone}</td>
                            <td>${member.signUpKind}</td>
                            <td>${member.mGrade}</td>
                        </tr>

                    </c:forEach>
                    </tbody>

                </table>

            </div>
        </div>
        <!--/ Hoverable Table rows -->

        <div class="content-backdrop fade"></div>
    </div>
    <!-- Content wrapper -->
</div>
<!-- / Layout page -->
</div>

<!-- Overlay -->
<div class="layout-overlay layout-menu-toggle"></div>
</div>
<!-- / Layout wrapper -->


<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="admin/assets/vendor/libs/jquery/jquery.js"></script>
<script src="admin/assets/vendor/libs/popper/popper.js"></script>
<script src="admin/assets/vendor/js/bootstrap.js"></script>
<script src="admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="admin/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="admin/assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="admin/assets/js/main.js"></script>

<!-- Page JS -->
<script src="admin/assets/js/dashboards-analytics.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
