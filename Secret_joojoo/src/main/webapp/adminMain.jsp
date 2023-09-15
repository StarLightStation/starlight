<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight"%>
<!DOCTYPE html>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="admin/assets/" data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>관리자</title>

<meta name="description" content="" />

<!-- Favicon -->
<link rel="icon" type="image/x-icon" href="admin/assets/img/favicon/favicon.ico" />

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet" />

<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet" href="admin/assets/vendor/fonts/boxicons.css" />

<!-- Core CSS -->
<link rel="stylesheet" href="admin/assets/vendor/css/core.css" class="template-customizer-core-css" />
<link rel="stylesheet" href="admin/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="admin/assets/css/demo.css" />

<!-- Vendors CSS -->
<link rel="stylesheet" href="admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

<link rel="stylesheet" href="admin/assets/vendor/libs/apex-charts/apex-charts.css" />

<!-- Page CSS -->

<!-- Helpers -->
<script src="admin/assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="admin/assets/js/config.js"></script>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

   <!-- 관리자 메뉴 -->
   <starlight:admin />

   <!-- Navbar -->

   <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
      <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)"> <i class="bx bx-menu bx-sm"></i>
      </a>
   </div>

   <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">


      <ul class="navbar-nav flex-row align-items-center ms-auto">


         <!-- User -->
         <li class="nav-item navbar-dropdown dropdown-user dropdown"><a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
               <div class="avatar avatar-online" style="margin-top: 30px; margin-right: 30px;">
                  <img src="admin/assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
               </div>
         </a>
            <ul class="dropdown-menu dropdown-menu-end">
               <li><a class="dropdown-item" href="#">
                     <div class="d-flex">

                        <div class="flex-shrink-0 me-3">
                           <div class="avatar avatar-online">
                              <img src="admin/assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                           </div>
                        </div>
                        <div class="flex-grow-1">
                           <span class="fw-semibold d-block">관리자 이름</span> <small class="text-muted">관리자</small>
                        </div>
                     </div>
               </a></li>


               <li>
                  <div class="dropdown-divider"></div>
               </li>
               <li><a class="dropdown-item" href="logout.do"> <i class="bx bx-power-off me-2"></i> <span class="align-middle">Log Out</span>
               </a></li>
            </ul></li>
         <!--/ User -->
      </ul>
   </div>


   <!-- / Navbar -->

   <!-- Content wrapper -->
   <div class="content-wrapper">
      <!-- Content -->

      <div class="container-xxl flex-grow-1 container-p-y">
         <div class="row">
            <div class="col-lg-8 mb-4 order-0">
               <div class="card">
                  <div class="d-flex align-items-end row">
                     <div class="col-sm-7">
                        <div class="card-body">
                           <h5 class="card-title text-primary">관리자님 환영합니다!🎉</h5>
                           <p class="mb-4">
                              <span class="fw-bold" id=todayPercent></span>
                             <input type="hidden" id="todayTotal"  value="${todayTotal.total_price}"  />
                        <input type="hidden" id="yesterdayTotal" value="${yesterdayTotal.total_price}" /> 
                           </p>

                           
                        </div>
                     </div>
                     <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-4">
                           <img src="admin/assets/img/illustrations/man-with-laptop-light.png" height="140" alt="View Badge User" data-app-dark-img="illustrations/man-with-laptop-dark.png" data-app-light-img="illustrations/man-with-laptop-light.png" />
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="col-lg-4 col-md-4 order-1">
               <div class="row">
                  <div class="col-lg-6 col-md-12 col-6 mb-4">
                     <div class="card">
                        <div class="card-body">
                           <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                 <img src="admin/assets/img/icons/unicons/chart-success.png" alt="chart success" class="rounded" />
                              </div>
                              <div class="dropdown">
                                 <button class="btn p-0" type="button" id="cardOpt3" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="bx bx-dots-vertical-rounded"></i>
                                 </button>
                                 <div class="dropdown-menu dropdown-menu-end" aria-labelledby="cardOpt3">
                                    <a class="dropdown-item" href="javascript:void(0);">View More</a> <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                                 </div>
                              </div>
                           </div>
                           <span class="fw-semibold d-block mb-1">일 매출</span>
                            <fmt:formatNumber var="todayTotal" type="number" value="${ todayTotal.total_price}" />
                           <h3 class="card-title mb-2">${todayTotal}원</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-6 col-md-12 col-6 mb-4">
                     <div class="card">
                        <div class="card-body">
                           <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                 <img src="admin/assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded" />
                              </div>
                              <div class="dropdown">
                                 <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="bx bx-dots-vertical-rounded"></i>
                                 </button>
                                 <div class="dropdown-menu dropdown-menu-end" aria-labelledby="cardOpt6">
                                    <a class="dropdown-item" href="javascript:void(0);">View More</a> <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                                 </div>
                              </div>
                           </div>
                           <span class="fw-semibold d-block mb-1">현재 회원 수</span>
                           <h3 class="card-title text-nowrap mb-1">${count.cnt}명</h3>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <!-- Total Revenue -->
            <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4">
               <div class="card">
                  <div class="row row-bordered g-0">
                     <div class="col-md-8">
                        <h5 class="card-header m-0 me-2 pb-3">분기 매출 비교</h5>
                        <c:forEach var="v"  items ="${yearQuarterSaleAll}">
                      <input type="hidden" id="dataYear"  value="${v.year}"  />
                      <input type="hidden" id="dataTotal" value="${v.total_price}" />
                  </c:forEach>
                        <div id="totalRevenueChart" class="px-2"></div>
                     </div>
                     <div class="col-md-4">
                        <div class="card-body" style="padding-bottom : 60px;">
                           <div class="text-center">
                             
                           </div>
                        </div>
                        <div id="growthChart" style="padding-top : 20px;"></div>
                     </div>
                  </div>
               </div>
            </div>
            <!--/ Total Revenue -->
            
            <div class="col-12 col-md-8 col-lg-4 order-3 order-md-2">
               <div class="row">
                  <div class="col-6 mb-4">
                     <div class="card">
                        <div class="card-body">
                           <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                 <img src="admin/assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />
                              </div>
                              <div class="dropdown">
                                 <button class="btn p-0" type="button" id="cardOpt4" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="bx bx-dots-vertical-rounded"></i>
                                 </button>
                                 <div class="dropdown-menu dropdown-menu-end" aria-labelledby="cardOpt4">
                                    <a class="dropdown-item" href="javascript:void(0);">View More</a> <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                                 </div>
                              </div>
                           </div>
                           <span class="fw-semibold d-block mb-1">상품 일 매출</span>
                           <fmt:formatNumber var="todayProductprice" type="number" value="${ todayProductprice.total_price}" />
                           <h3 class="card-title text-nowrap mb-2">${todayProductprice}원</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-6 mb-4">
                     <div class="card">
                        <div class="card-body">
                           <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                 <img src="admin/assets/img/icons/unicons/cc-primary.png" alt="Credit Card" class="rounded" />
                              </div>
                              <div class="dropdown">
                                 <button class="btn p-0" type="button" id="cardOpt1" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="bx bx-dots-vertical-rounded"></i>
                                 </button>
                                 <div class="dropdown-menu" aria-labelledby="cardOpt1">
                                    <a class="dropdown-item" href="javascript:void(0);">View More</a> <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                                 </div>
                              </div>
                           </div>
                           <span class="fw-semibold d-block mb-1">구독 일 매출</span>
                           <fmt:formatNumber var="todaySubsprice" type="number" value="${ todaySubsprice.total_price}" />
                           <h3 class="card-title mb-2">${ todaySubsprice}원</h3>
                        </div>
                     </div>
                  </div>
                  <!-- </div>
    <div class="row"> -->
                  <div class="col-12 mb-4">
                     <div class="card">
                        <div class="card-body">
                           <div class="d-flex justify-content-between flex-sm-row flex-column gap-3">
                              <div class="d-flex flex-sm-column flex-row align-items-start justify-content-between">
                                 <div class="card-title">
                                    <h5 class="text-nowrap mb-2">일간 매출</h5>
                                    <span class="badge bg-label-warning rounded-pill">Year 2023</span>
                                 </div>
                                 <div class="mt-sm-auto">
                                    <c:set var="total" value ="0" />
                                    <c:forEach var="t"  items ="${weekSale}">
                                       <c:set var="total" value="${total+t.total_price}" />
                               <input type="hidden" id="dataWeek"  value="${t.total_price}"  />
                               <input type="hidden" id="dataOdate"  value="${t.oDate}"  />
                           </c:forEach>
                           <fmt:formatNumber var="totalprice" type="number" value="${total}" />
                           <h6 class="mb-0">${totalprice} 원</h6>
                                 </div>
                              </div>
                                    
                              <div id="profileReportChart"></div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="row"></div>
      </div>
      <!-- / Content -->

      <!-- Footer -->
      <footer class="content-footer footer bg-footer-theme">
         <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
            <div class="mb-2 mb-md-0">
               ©
               <script>
                  document.write(new Date().getFullYear());
               </script>
               , made with ❤️ by <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder">ThemeSelection</a>
            </div>
            <div>
               <a href="https://themeselection.com/license/" class="footer-link me-4" target="_blank">License</a> <a href="https://themeselection.com/" target="_blank" class="footer-link me-4">More Themes</a> <a href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/" target="_blank" class="footer-link me-4">Documentation</a> <a href="https://github.com/themeselection/sneat-html-admin-template-free/issues" target="_blank" class="footer-link me-4">Support</a>
            </div>
         </div>
      </footer>
      <!-- / Footer -->

      <div class="content-backdrop fade"></div>
   </div>
   <!-- Content wrapper -->
   <!-- / Layout page -->

   <!-- Overlay -->
   <div class="layout-overlay layout-menu-toggle"></div>
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