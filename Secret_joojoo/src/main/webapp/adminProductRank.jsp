<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>많이 팔린 상품</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="admin/assets/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />

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
  
  <!-- 관리자 메뉴 -->
  <starlight:admin/>

 <!-- Hoverable Table rows -->
 
           <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4">
              </h4>
<button type="button" class="btn btn-primary" onclick="location.href='sale.do'">매출 관리</button> &nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary" onclick="location.href='saleRank.do'">매출 순위</button> <br>
<button type="button" class="btn btn-primarymenu" onclick="location.href='saleRank.do'">회원 결제누적 순위</button> &nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primarymenu" onclick="location.href='productRank.do'">많이 팔린 상품 </button> 
<form action="productRankOption.do" method="post">
    <select name="productRankOption">
        <option value="1" >누적 갯수로 보기</option>
        <option value="2">누적 금액으로 보기</option>
    </select>
    <input type="submit" value="검색">
</form>

               <!-- Basic Bootstrap Table -->
               <div class="card">
                <h5 class="card-header">결제 누적 순위</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table table-hover">
                   
                    <c:if test="${rankOption ne 1 && rankOption ne 2 }">
                    <thead>
                      <tr>
                        <th>상품 이름</th>
                        <th>상품 누적 판매량</th>
                        <th>상품 누적 판매 액수</th>
                        <th>누적 판매 갯수 순위</th>
                      </tr>
                    </thead>
                    <c:forEach var="v" items="${productDatas}">
                    <tbody class="table-border-bottom-0">
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i>${v.pName }</td>
                        <td>${v.cnt }개</td>
                        <td>${v.total }원</td>
                        <td>${v.cnt_rank }</td>
                      </tr>
                     
                    </tbody>
                    </c:forEach>
                    </c:if>
                    
                    <c:if test="${rankOption eq 1}">
                     <thead>
                      <tr>
                        <th>상품 이름</th>
                        <th>상품 누적 판매량</th>
                        <th>상품 누적 판매 액수</th>
                        <th>누적 판매 갯수 순위</th>
                      </tr>
                    </thead>
                    <c:forEach var="c" items="${rankOptionDatas}">
                    <tbody class="table-border-bottom-0">
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i>${c.pName }</td>
                        <td>${c.cnt }개</td>
                        <td>${c.total }원</td>
                        <td>${c.cnt_rank }</td>
                      </tr>
                     
                    </tbody>
                    </c:forEach>
                    </c:if>
                    
                    <c:if test="${rankOption eq 2}">
                    <thead>
                      <tr>
                        <th>상품 이름</th>
                        <th>상품 누적 판매량</th>
                        <th>상품 누적 판매 액수</th>
                        <th>누적 금액 순위</th>
                      </tr>
                    </thead>
                    <c:forEach var="p" items="${rankOptionDatas}">
                    <tbody class="table-border-bottom-0">
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i>${p.pName }</td>
                        <td>${p.cnt }개</td>
                        <td>${p.total }원</td>
                        <td>${p.total_rank }</td>
                      </tr>
                     
                    </tbody>
                    </c:forEach>
                    </c:if>
                    
                    
                    
                    
                  </table>
                </div>
              </div>
              <!--/ Hoverable Table rows -->
<br>
            <!-- Footer -->
            <starlight:admin_saleFooter/>
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