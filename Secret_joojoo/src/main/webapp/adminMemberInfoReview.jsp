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

        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">회원정보 / 회원관리 / </span>회원 상세 정보</h4>
        <h5 class="fw-bold py-1 mb-3"><span class="text-muted fw-light">계정 정보 : </span>${mID} 님</h5>

        <div class="row">
            <div class="col-md-12">

                <!-- 관리자 상세 정보 페이지 -->
                <starlight:adminMemberInfo/>

                <div class="card mb-4">

                    <a href="adminReviewPage.do">
                        <h5 class="card-header">리뷰 정보 확인</h5>
                    </a>

                    <!-- Account -->
                    <div class="card-body">

                        <form id="updateReviewForm" action="안쓰는 기능.do" method="POST" onsubmit="return false"/>

                        <div class="row">

                            <table class="table table-hover">
                               <thead>
                                <tr>
                                    <th>상품 이미지</th>
                                    <th>상품 이름</th>
                                    <th>리뷰 내용</th>
                                    <th>리뷰 별점</th>
                                    <th>작성 날짜</th>
                                </tr>
                                </thead>

                                <c:forEach var="board" items="${bdatas}">
                                    <tr>

                                        <div id="pImageStyleDiv">
                                            <td class="flexContainer">
                                                <img
                                                        alt="상품 이미지"
                                                        src="assets/img/products/${board.pImage}"
                                                        class="pImageStyle"
                                                >
                                            </td>
                                        </div>

                                        <div id="boardDiv">

                                            <td class="flexProperty">${board.pName}</td>
                                            <td class="flexProperty">
                                                <!-- 리뷰 내용이 길면 글을 줄이고, 모달로 처리 하기. -->

                                                <div class="demo-inline-spacing">

                                                    <!-- ModalScrollable 버튼 (기존에 템플릿 요소 사용) -->
                                                    <!-- 사용자 정의 속성 -->
                                                    <!-- 접두사 " data-속성명 " 으로 생성 -->
                                                    <button
                                                            type="button"
                                                            class="shortContent"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#modalScrollable"
                                                            data-bContent="${board.bContent}"
                                                            style="border: none; background: none;"
                                                    >
                                                    </button>
                                                </div>

                                            </td>
                                            <td class="flexProperty">⭐${board.bStar}</td>
                                            <td class="flexProperty">${board.bDate}</td>

                                        </div>

                                    </tr>
                                </c:forEach>

                            </table>

                            <!-- 스크롤 모달창 -->
                            <div class="col-lg-4 col-md-3">

                                <!-- 스크롤 모달창 -->
                                <div class="modal fade" id="modalScrollable" tabindex="-1" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-scrollable" role="document">

                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modalScrollableTitle">[ 회원 리뷰 ]</h5>
                                                <button
                                                        type="button"
                                                        class="btn-close"
                                                        data-bs-dismiss="modal"
                                                        aria-label="Close"
                                                ></button>
                                            </div>

                                            <div class="modal-body">
                                                <p id="modal_bContent"></p>
                                            </div>

                                            <div class="modal-footer">
                                                <button
                                                        type="button"
                                                        class="btn btn-primary"
                                                        data-bs-dismiss="modal">
                                                    닫기
                                                </button>
                                            </div>

                                        </div>

                                    </div>
                                </div>

                            </div>

                            <!-- 리뷰 모달창 (직접 만든 모달창) (현재 사용 안함)
                            <div id="ContentModal" class="modal">
                                <div class="modal-content">
                                    <span class="close">&times;</span>
                                    <div class="section">
                                        <p id="modal_bContent"></p>
                                    </div>
                                </div>
                            </div>
                            -->

                            <!--
                            <div class="mt-2">
                                <button type="submit" class="btn btn-primary me-2">버튼 이름 정하기</button>
                                <button type="reset" class="btn btn-outline-secondary">버튼 이름 정하기</button>
                            </div>
                            -->

                        </div>
                        <!-- /Account -->

                    </div>

                </div>
            </div>
        </div>
        <!-- / Content -->

        <div class="content-backdrop fade"></div>
    </div>
    <!-- Content wrapper -->
</div>
<!-- / Layout page -->
</div>

<!-- Overlay -->
<div class="layout-overlay layout-menu-toggle"></div>
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
