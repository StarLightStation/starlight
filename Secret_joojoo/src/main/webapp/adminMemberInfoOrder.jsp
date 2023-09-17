<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
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
                    <h5 class="card-header">주문 정보 확인</h5>

                    <!-- Account -->
                    <div class="card-body">
                        <form id="updateOrderForm" method="POST" onsubmit="return false">

                            <div class="row" >

                                <!-- 맵 == orderDatas == orderEntry -->
                                <c:forEach var="orderEntry" items="${orderDatas}">
                                <div style="border: 1px solid #ccc; margin-bottom: 30px; background-color: #fdfdfd; border-radius: 5px;">

                                    <!-- 맵 키 == orderEntry.key == key -->
                                    <!-- <c:set var="key" value="orderEntry.key"></c:set> -->

                                    
                                    <br>
                                    <h5 class="mb-0" style="color: #696cff; padding-bottom: 10px;"><strong>주문 정보</strong></h5>
                                    <p class="mb-0">주문 날짜: ${orderEntry.key.oDate}</p>
                                    <p class="mb-0">총 구매 가격: <fmt:formatNumber  type="number" value="${orderEntry.key.oPrice}"/>원</p>
                                    <p class="mb-0">주문 상태: ${orderEntry.key.oState}</p>
                                    <p class="mb-0">주문 주소지: ${orderEntry.key.oAddress}</p>
                                    <hr>

                                    <h5 class="mb-0" style="color: #696cff; padding-bottom: 10px;"><strong>상품 정보</strong></h5>
                                    <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>상품 이미지</th>
                                            <th>상품 이름</th>
                                            <th>상품 가격</th>
                                            <th>상품 개수</th>
                                        </tr>
									</thead>
									<tbody class="table-border-bottom-0">
                                        <!-- 맵 == orderEntry / 맵 키의 값 == orderEntry.value == value -->
                                        <c:forEach var="value" items="${orderEntry.value}">
                                            <tr>
                                                <td class="flexContainer">
                                                    <img
                                                            alt="상품 이미지"
                                                            src="assets/img/products/${value.pImage}"
                                                            class="pImageStyle"
                                                            style="width: 50px"
                                                    >
                                                </td>
                                                <td class="flexProperty">${value.pName}</td>
                                                <td class="flexProperty"><fmt:formatNumber  type="number" value="${value.pPrice}"/>원</td>
                                                <td class="flexProperty"><fmt:formatNumber  type="number" value="${value.odCnt}"/>개</td>
                                            </tr>
                                        </c:forEach>
</tbody>
                                    </table>
                                    <br>

</div>
                                </c:forEach>

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
