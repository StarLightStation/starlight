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
                    <h5 class="card-header">회원정보</h5>

                    <!-- Account -->
                    <div class="card-body">
                        <form id="updateMemberForm" action="안쓰는 기능.do" method="POST" onsubmit="return false">

                            <div class="row">

                                <div class="mb-3 col-md-6">
                                    <label for="mID" class="form-label">이메일</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="mID"
                                            name="mID"
                                            value="${mdata.mID}"
                                            placeholder="이메일을 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="mPW" class="form-label">비밀번호</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="mPW"
                                            name="mPW"
                                            value="${mdata.mPW}"
                                            placeholder="비밀번호를 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="mName" class="form-label">이름 (닉네임)</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="mName"
                                            name="mName"
                                            value="${mdata.mName}"
                                            placeholder="이름 (닉네임) 을 입력 하세요."
                                            required="required"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="subscription" for="phoneNumber">구독 여부</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="subscription"
                                            name="subscription"
                                            value="${mdata.subscription == 0 ? '구독 ❌' : '구독 ⭕'}"
                                            placeholder="구독 여부 정보를 입력 하세요."
                                            required="required"
                                            min="0"
                                            max="1"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="isAdmin" class="form-label">관리자 여부</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="isAdmin"
                                            name="isAdmin"
                                            value="${mdata.isAdmin == 0 ? '일반 회원' : '관리자'}"
                                            placeholder="관리자 여부 정보를 입력 하세요."
                                            required="required"
                                            min="0"
                                            max="1"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="phoneNumber" class="phoneNumber">전화번호</label>
                                    <div class="input-group input-group-merge">
                                        <span class="input-group-text">KO (+82)</span>
                                        <input
                                                class="form-control"
                                                type="text"
                                                id="phoneNumber"
                                                name="phoneNumber"
                                                value="${mdata.mPhone}"
                                                placeholder="${mdata.mPhone}"
                                                readonly="readonly"
                                        />
                                    </div>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="signUpKind" class="form-label">가입 종류</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="signUpKind"
                                            name="signUpKind"
                                            value="${mdata.signUpKind}"
                                            placeholder="${mdata.signUpKind}"
                                            readonly="readonly"
                                    />
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label for="mGrade" class="form-label">회원 등급</label>
                                    <input
                                            class="form-control"
                                            type="text"
                                            id="mGrade"
                                            name="mGrade"
                                            value="${mdata.mGrade}"
                                            placeholder="${mdata.mGrade}"
                                            readonly="readonly"
                                    />
                                </div>

                            </div>

                            <!--
                            <div class="mt-2">
                                <button type="submit" class="btn btn-primary me-2">회원 정보 수정</button>
                                <button type="reset" class="btn btn-outline-secondary">원래 대로</button>
                            </div>
                            -->

                        </form>

                    </div>
                    <!-- /Account -->

                </div>

                <!-- Delete Account -->
                <div class="card">
                    <h5 class="card-header">계정삭제</h5>
                    <div class="card-body">

                        <div class="mb-3 col-12 mb-0">
                            <div class="alert alert-warning">
                                <h6 class="alert-heading fw-bold mb-1">
                                    회원의 계정을 정말 삭제 하시겠습니까 ?
                                </h6>
                                <p class="mb-0">
                                    삭제된 계정은 복구 할 수 없습니다. 신중히 결정해 주세요.
                                </p>
                            </div>
                        </div>

                        <form id="deleteMember_form" action="memberInfoMainDeleteMember.do">

                            <div class="form-check mb-3">
                                <input
                                        class="form-check-input"
                                        type="checkbox"
                                        id="deleteMember_input"
                                        name="mID"
                                />

                                <label class="form-check-label" for="deleteMember_input">
                                    계정 삭제를 원하시면, 체크 버튼을 눌러 주세요.
                                </label>
                            </div>

                            <button
                                    type="button"
                                    class="btn btn-danger deactivate-account"
                                    onclick="checkSweetAlert()">계정삭제
                            </button>

                        </form>

                    </div>
                </div>

                <!-- /Delete Account -->

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


<!-- sweetalert 추가 -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

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
