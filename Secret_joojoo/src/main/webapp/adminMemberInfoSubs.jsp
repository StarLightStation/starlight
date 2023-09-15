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

    <title>관리자 - 회원 상세 정보</title>

    <!-- 스타일 -->
    <link rel="stylesheet" href="assets/css/admin_member.css">

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


<!-- Content wrapper -->
<div class="content-wrapper">

    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">시크릿주주 / </span>[ 관리자 페이지 - 회원 관리 ]</h4>
        <h5 class="fw-bold py-1 mb-3"><span class="text-muted fw-light">계정 정보 : </span>${mID} 님</h5>
        <h6 class="fw-bold py-1 mb-3">
            <span>
                <a href="memberList.do" class="goMemberListPage">[ 회원 전체 목록 페이지로 돌아가기 ]
                </a>
            </span>
        </h6>

        <div class="row">
            <div class="col-md-12">

                <!-- 관리자 상세 정보 페이지 -->
                <starlight:adminMemberInfo/>

                <div class="card mb-4">
                    <h5 class="card-header">[ 구 독 정 보 확 인 ]</h5>

                    <!-- Account -->
                    <div class="card-body">
                        <form id="updateSubsForm" action="안쓰는 기능.do" method="POST" onsubmit="return false">


                            <div class="row">

                                <table border="2">

                                    <tr>
                                        <th>구독 상품 이름</th>
                                        <th>구독 상품 가격</th>
                                        <th>구독 만료 기간</th>
                                    </tr>

                                    <c:forEach var="subsinfo" items="${subsinfoDatas}">

                                        <tr>
                                            <td>[ ${subsinfo.subName} ]</td>
                                            <td>${subsinfo.subPrice}(원)</td>
                                            <td>${subsinfo.sInfoPeriod}</td>
                                        </tr>
                                    </c:forEach>

                                </table>

                            </div>

                            <!--
                            <div class="mt-2">
                                <button type="submit" class="btn btn-primary me-2">버튼 이름 정하기</button>
                                <button type="reset" class="btn btn-outline-secondary">버튼 이름 정하기</button>
                            </div>
                            -->

                        </form>

                    </div>
                    <!-- /Account -->

                </div>

            </div>
        </div>
    </div>
    <!-- / Content -->

    <!-- Footer -->
    <starlight:adminMemberFooter/>
    <!-- / Footer -->

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

<div class="buy-now">
    <a
            href="logout.do"
            class="btn btn-danger btn-buy-now"
            target="_blank"
    >
        메인 페이지로 돌아가기
    </a>
</div>

<!-- admin_member JS -->
<script src="admin/assets/js/admin_member.js"></script>

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
