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

    <title>회원 결제누적 순위</title>

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
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    
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
		<button type="button" class="btn btn-primarymenu" onclick="location.href='productRank.do'">많이 팔린 상품 </button> <br>

               <!-- Basic Bootstrap Table -->
               <div class="card">
               
                <h5 class="card-header"> 회원 결제 누적 순위</h5>
                <div class="table-responsive text-nowrap">
                   
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th>회원 이름</th>
                        
                        <th>총 결제 금액</th>
                        
                      </tr>
                    </thead>
                    <c:forEach var="v" items="${memberTotalDatas}">
                    <tbody class="table-border-bottom-0">
                      <tr>
                        <td>
                        <c:if test="${ v.mID eq null}">
                        <button   type="button"  id="myButton"  class="btn btn-primarymid" disabled="disabled">
                        탈퇴한 회원
                        </button>
                        </c:if>
                        <c:if test="${v.mID ne null}">
                        <button   type="button"  id="myButton"  class="btn btn-primarymid" data-bs-toggle="modal"  data-bs-target="#modalCenter" data-mid="${v.mID}">
                        ${v.mID}
                        </button>
                        <input type="hidden" id="hiddenInput" style="border: none; background: none;" value=${ v.mID }>
                        </c:if>
                        </td>
                        <td>${v.total_price} 원</td>
                        <td></td>
                        <td></td>
                      </tr>
                    </tbody>
                  </c:forEach>
                  </table>
                  
                </div>
              
              </div>
                            <!--/ Hoverable Table rows -->              
<br>

<!-- 모달창 -->

                        <div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
                          <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="modalCenterTitle">회원 상세 결제 누적 금액</h5>
                                <button
                                  type="button"
                                  class="btn-close"
                                  data-bs-dismiss="modal"
                                  aria-label="Close"
                                ></button>
                              </div>
                              
                              <div class="modal-body">
                                <div class="row">
                                  <div class="col mb-3"></div>
                                </div>
                                <div class="row g-2">
                                  <div class="col mb-0">
                                    <label for="emailWithTitle" class="form-label">회원</label>
                                    <p id="memberID"> </p>
                                  </div>
                                 
                                </div>
                                <br>
                                <div class="row g-2">
                               
                                  <div class="col mb-0">
                                    <label for="emailWithTitle" class="form-label">연도</label>
                                    <p id="memberYear"></p>
                                  </div>
                                  <div class="col mb-0">
                                    <label for="dobWithTitle" class="form-label">분기</label>
                                    <p id="memberQuarter"></p>
                                  </div>
                                  <div class="col mb-0">
                                    <label for="dobWithTitle" class="form-label">상품결제누적</label>
                                    <p id="memberProduct"></p>
                                  </div>
                                  <div class="col mb-0">
                                    <label for="dobWithTitle" class="form-label">구독결제누적</label>
                                    <p id="memberSubs"></p>
                                  </div>
                                    <div class="col mb-0">
                                    <label for="dobWithTitle" class="form-label">총 결제 금액</label>
                                    <p id="memberTotal"></p>
                                  </div>
                                </div>
                              </div>
                              
                              <div class="modal-footer">
                                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                  Close
                                </button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
               <!--/ 모달창 -->
               
               <script type="text/javascript">
               
                      $(document).ready(function() {
                           $(".btn-primarymid").on("click",function(){
                              var valueMid = $(this).data("mid");
                              console.log("보내주는 mID값  : " + valueMid); 
                  
                           var midData={ mID: valueMid }; // JSON 데이터
                  
                           $.ajax({
                              url : "saleRankModal.do",
                              type : "POST",
                              data : midData,
                              dataType: "json",
                              success : function(result){
                              console.log("로그 : 성공: "+result);
                              console.log("로그 : 일로 들어온 값 : " + result.mID);
                              
                              var modalMid = "";
                              var modalYear = "";
                              var modalQuarter = "";
                              var modalProduct = "";
                              var modalSubs = "";
                              var modalTotal="";
                              
                              for(var i = 0; i <result.length; i++){
                                 var order = result;
                                 var orderMid = order[i].mID;
                                 var orderYear = order[i].year;
                                 var orderQuarter = order[i].quarter;
                                 var orderProduct = order[i].product_price;
                                 var orderSubs = order[i].subs_price;
                                 var orderTotal = order[i].total_price;
                                 console.log("로그 122222222222222222 : " + order);
                                 console.log("로그 333333333333333333 : " + order[i].mID);
                                 console.log("로그 555555555555555555 : " + orderTotal);
                                 
                                 modalYear += "<p>  " + orderYear + "</p>";
                                 modalQuarter += "<p> " + orderQuarter + "</p>";
                                 modalProduct += "<p> " + orderProduct + "원</p>";
                                 modalSubs += "<p> " + orderSubs + "원</p>";
                                 modalTotal += "<p> " + orderTotal + "원</p>";
                              }
                                 modalMid += "<p>  " + orderMid + "</p>";
                              
                              $('#memberID').html(modalMid);
                              $('#memberYear').html(modalYear);
                              $('#memberQuarter').html(modalQuarter);
                              $('#memberProduct').html(modalProduct);
                              $('#memberSubs').html(modalSubs);
                              $('#memberTotal').html(modalTotal);
                              
                               //showModalWithData(order);
                              },
                              error : function(){
                                 console.log("로그 : 에러발생...");
                              }
                           });
                        });
                     });
               
                   // 모달창을 열 때 JSON 데이터 전달하는 함수
                  //    function showModalWithData(data) {
                  //        // JSON 데이터를 모달창 필드에 설정
                  //        $("#memberID").text(data.mID);
                  //        // ... 다른 필드들도 필요에 따라 설정 ...
                        //
                  //        // 모달창 열기
                  //        $("#modalCenter").modal("show");
                  //    }    
                      
               </script>

            <!-- Footer -->
           <starlight:admin_saleFooter/>
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
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