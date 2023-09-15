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

    <title>관리자 - 신고리뷰 상세정보</title>

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
    
    <!-- 내가 설정한 CSS -->
    <link rel="stylesheet" href="admin/assets/css/admin-reviewDetail.css" />

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
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">리뷰정보 / 신고관리 /</span> 신고 리뷰 상세정보</h4>

              <!-- Basic Layout -->
              <div class="row">
                <div class="col-xl">
                  <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                      <h5 class="mb-0">신고 리뷰상세</h5>
                    </div>
                      <c:set var="ddata" value="${ddata}"></c:set>
                    <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img
                          src="assets/img/products/${ddata.pImage}"
                          alt="user-avatar"
                          class="d-block rounded"
                          height="120"
                          width="100"
                          id="uploadedAvatar"
                        />
                        </div>
                        <br>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">상품명</label>
                           <input  type="text" class="form-control" name="pName" id="pName" value="${ddata.pName}"  readonly/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-company">별점</label>
                          <input type="text" class="form-control" id="bStar" name="bStar" value="${ddata.bStar}" readonly/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-email">작성자</label>
                          <input  type="text" class="form-control" name="mID" id="mID" value="${ddata.mID}"  readonly/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-phone">작성날짜</label>
                          <input  type="text" class="form-control" name="bDate" id="bDate" value="${ddata.bDate}"  readonly/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-message">리뷰 내용</label>
                          <textarea id="basic-default-message" class="form-control"  readonly >${ddata.bContent}</textarea>
                        </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl">
                  <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                      <h5 class="mb-0">신고자 정보</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                          <label class="form-label" for="basic-icon-default-fullname">신고자</label>
                         <input  type="text" class="form-control" name="dmID" id="dmID" value="${ddata.dmID}"  readonly/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-icon-default-company">신고날짜</label>
                          <input  type="text" class="form-control" name="dDate" id="dDate" value="${ddata.dDate}"  readonly/>
                          </div>
                          <!-- Button trigger modal -->
                          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal">삭제</button>
                          <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#basicModal2">철회</button>


                        <!-- 신고된 리뷰 삭제 Modal -->
                        <div class="modal fade" id="basicModal" tabindex="-1" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel1">해당 리뷰를 삭제하시겠습니까?</h5>
                                <button
                                  type="button"
                                  class="btn-close"
                                  data-bs-dismiss="modal"
                                  aria-label="Close"
                                ></button>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
                               <form action="adminReviewDelete.do" method="post">
	                               <input type="hidden" id="dNum" name="dNum" value="${ddata.dNum}">
	                               <input type="hidden" id="bNum" name="bNum"  value="${ddata.bNum}">
	                                <button type="submit" class="btn btn-primary">확인</button>
                               </form>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <!-- 신고된 리뷰 철회 Modal -->
                        <div class="modal fade" id="basicModal2" tabindex="-1" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel1">해당 리뷰를 철회하시겠습니까? </h5>
                                <button
                                  type="button"
                                  class="btn-close"
                                  data-bs-dismiss="modal"
                                  aria-label="Close"
                                ></button>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
                               <form action="adminReviewWithdraw.do" method="post">
	                               <input type="hidden" id="dNum" name="dNum"  value="${ddata.dNum}">
	                                <button type="submit" class="btn btn-primary">확인</button>
                               </form>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    
                        </div>
                        </div>
                    </div>
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
