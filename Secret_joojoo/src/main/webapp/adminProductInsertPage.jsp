<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight"%>
<!DOCTYPE html>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="admin/assets/" data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>관리자 - 상품상세정보</title>

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

   <!-- 관리자 메뉴 -->
   <starlight:admin />


   <!-- Content wrapper -->
   <div class="content-wrapper">

      <!-- Content -->
      <div class="container-xxl flex-grow-1 container-p-y">
         <h4 class="fw-bold py-3 mb-4">
            <span class="text-muted fw-light">상품 관리 /</span> 상품 등록
         </h4>

         <div class="row">
            <div class="col-md-12">
               <div class="card mb-4">
                  <h5 class="card-header">상품 이미지</h5>
                  <!-- Account -->
                  <div class="card-body">

                     <div class="card-body">
                        <form action="adminProductInsert.do" id="formAccountSettings" method="POST" enctype="multipart/form-data" onsubmit="return validateForm(this);">

                           <div class="d-flex align-items-start align-items-sm-center gap-4">

                              <img alt="" src="assets/img/products/defaultDetail.jpg" class="d-block rounded" height="240" width="150" id="uploadedAvatar1"> <img alt="" src="assets/img/products/default.jpg" class="d-block rounded" height="240" width="150" id="uploadedAvatar2">
                           </div>
                           <label for="formFile" class="form-label">상품 이미지 등록</label> <input class="form-control" type="file" id="formFile" name="fileUpload1" onchange="thumbnail1(this);" required /> <input class="form-control" type="file" id="formFile" name="fileUpload2" onchange="thumbnail2(this);" required /> <br>


                           <hr class="my-0" />

                           <div class="row">
									<div class="mb-3 col-md-6">
										<label for="firstName" class="form-label">상품 명</label> <input
											class="form-control" type="text" id="firstName"
											name="pName" value="${pdata.pName }" placeholder="상품명을 입력하세요." autofocus required/>
									</div>
									<div class="mb-3 col-md-6">
										<label for="lastName" class="form-label">상품 가격</label> <input
											class="form-control" type="text" name="pPrice"
											id="lastName" value="${pdata.pPrice }" placeholder="상품 가격을 입력하세요." oninput="validateInputPrice(this)" required/>
											<div id="errorMessage" style="color: red;"></div>
									</div>
									<div class="mb-3 col-md-6">
										<label for="email" class="form-label">상품 재고</label> <input
											class="form-control" type="text" id="email" name="pCnt"
											value="${pdata.pCnt }" placeholder="상품 재고를 입력하세요." oninput="validateInputCnt(this, 'cntError')" required/>
									<div id="cntError" style="color: red;"></div>
									</div>
									<div class="mb-3 col-md-6">
										<label for="organization" class="form-label">도수</label> <input
											type="text" class="form-control" id="organization"
											name="pAlcohol" value="${pdata.pAlcohol }" placeholder="0.1~40.0의 숫자를 입력하세요." oninput="validateInput(this, 'alcoholError')" required />
											<div id="alcoholError" style="color: red;"></div>
									</div>


                              <div class="mb-3 col-md-6">
                                 <label class="form-label" for="country">카테고리</label> <select id="country" name="pCategory" class="select2 form-select">

                                    <option value="과실주">과실주</option>

                                    <option value="증류주">증류주</option>

                                    <option value="청주">청주</option>

                                    <option value="탁주">탁주</option>


                                 </select>
                              </div>
                              <div class="mb-3 col-md-6">
                                 <label for="language" class="form-label">신맛</label> <select id="language" name="pSour" class="select2 form-select">
                                    <option value="약">약</option>
                                    <option value="중">중</option>
                                    <option value="강">강</option>
                                 </select>
                              </div>
                              <div class="mb-3 col-md-6">
                                 <label for="timeZones" class="form-label">단맛</label> <select id="timeZones" name="pSweet" class="select2 form-select">
                                    <option value="약">약</option>
                                    <option value="중">중</option>
                                    <option value="강">강</option>
                                 </select>
                              </div>
                              <div class="mb-3 col-md-6">
                                 <label for="currency" class="form-label">탄산</label> <select id="currency" name="pSparkle" class="select2 form-select">
                                    <option value="약">약</option>
                                    <option value="중">중</option>
                                    <option value="강">강</option>
                                 </select>
                              </div>
                           </div>
                           <div class="mt-2">
                              <button type="submit" class="btn btn-primary me-2">등록 완료</button>
                           </div>
                        </form>
                     </div>
                     <!-- /Account -->
                  </div>
               </div>
            </div>
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
   </div>
   <!-- / Layout page -->
   </div>

   <!-- Overlay -->
   <div class="layout-overlay layout-menu-toggle"></div>
   </div>

   <!--  파일 업로드 -->
   <script type="text/javascript">
      function thumbnail1(input) {
         if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
               document.getElementById('uploadedAvatar1').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]); // Corrected line
         } else {
            document.getElementById('uploadedAvatar1').src = "";
         }
      }

      function thumbnail2(input) {
         if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
               document.getElementById('uploadedAvatar2').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]); // Corrected line
         } else {
            document.getElementById('uploadedAvatar2').src = "";
         }
      }
   </script>

   <!-- 상품 가격 -->
   <script>
      function validateInputPrice(input) {
         // 입력값에서 숫자만 추출
         const inputValue = input.value.replace(/\D/g, '');

         // 0 이상의 숫자인지 확인
         const numberValue = parseInt(inputValue, 10);
         if (isNaN(numberValue) || numberValue < 0) {
            document.getElementById("errorMessage").textContent = "0 이상의 숫자를 입력하세요.";
            input.value = ''; // 잘못된 값이면 입력을 지움
         } else {
            document.getElementById("errorMessage").textContent = ""; // 에러 메시지 제거
            input.value = numberValue; // 올바른 값이면 입력값을 설정
         }
      }
   </script>
   <!-- 상품 재고 -->
   <script>
      function validateInputCnt(input, errorId) {
         const inputValue = input.value.replace(/\D/g, ''); // 숫자만 추출
         const numberValue = parseInt(inputValue, 10);

         if (isNaN(numberValue) || numberValue < 0) {
            document.getElementById(errorId).textContent = "0 이상의 숫자를 입력하세요.";
            input.value = '';
         } else {
            document.getElementById(errorId).textContent = "";
            input.value = numberValue;
         }
      }
   </script>
   <!-- 도수 -->
   <script>
      function validateInput(input, errorId) {
         const inputValue = input.value;

         // 입력값이 빈 문자열일 경우 에러 메시지 제거
         if (inputValue === '') {
            document.getElementById(errorId).textContent = '';
            return;
         }

         // 숫자 또는 소수점만 추출
         const numberValue = parseFloat(inputValue.replace(/[^\d.]/g, ''));

         // 값의 범위 및 소수점 이하 자릿수 체크
         if (isNaN(numberValue) || numberValue < 0 || numberValue > 40 || !/^(\d+\.\d{1})?$/.test(inputValue)) {
            document.getElementById(errorId).textContent = '0~40 범위 내의 소수점 한 자리 숫자를 입력하세요.';
         } else {
            document.getElementById(errorId).textContent = '';
         }

         // 입력값 갱신
         input.value = inputValue;
      }
   </script>
   <script>
   function validateForm(form) {
    // 상품 가격 검증
    const priceInput = form.pPrice;
    const priceError = document.getElementById("errorMessage");
    const priceValue = parseInt(priceInput.value.replace(/\D/g, ''), 10);
    if (isNaN(priceValue) || priceValue < 0) {
        priceError.textContent = "0 이상의 숫자를 입력하세요.";
        return false; // 폼 제출 방지
    } else {
        priceError.textContent = "";
    }

    // 상품 재고 검증
    const cntInput = form.pCnt;
    const cntError = document.getElementById("cntError");
    const cntValue = parseInt(cntInput.value.replace(/\D/g, ''), 10);
    if (isNaN(cntValue) || cntValue < 0) {
        cntError.textContent = "0 이상의 숫자를 입력하세요.";
        return false; // 폼 제출 방지
    } else {
        cntError.textContent = "";
    }

    // 도수 검증
    const alcoholInput = form.pAlcohol;
    const alcoholError = document.getElementById("alcoholError");
    const alcoholValue = parseFloat(alcoholInput.value.replace(/[^\d.]/g, ''));
    if (isNaN(alcoholValue) || alcoholValue < 0 || alcoholValue > 40 || !/^(\d+\.\d{1})?$/.test(alcoholInput.value)) {
        alcoholError.textContent = '0~40 범위 내의 소수점 한 자리 숫자를 입력하세요.';
        return false; // 폼 제출 방지
    } else {
        alcoholError.textContent = "";
    }

    // 추가 검증 로직을 여기에 계속해서 작성할 수 있습니다.

    return true; // 폼 제출 허용
}
   </script>

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