<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

   <!-- title -->
   <title>시크릿주주 - 주류 검색</title>

   <!-- favicon -->
   <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
   <!-- google font -->
   <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
   <!-- fontawesome -->
   <link rel="stylesheet" href="assets/css/all.min.css">
   <!-- bootstrap -->
   <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
   <!-- owl carousel -->
   <link rel="stylesheet" href="assets/css/owl.carousel.css">
   <!-- magnific popup -->
   <link rel="stylesheet" href="assets/css/magnific-popup.css">
   <!-- animate css -->
   <link rel="stylesheet" href="assets/css/animate.css">
   <!-- mean menu css -->
   <link rel="stylesheet" href="assets/css/meanmenu.min.css">
   <!-- search style -->
   <link rel="stylesheet" href="assets/css/search.css">
   <!-- responsive -->
   <link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>
   
   <!-- 검색 main.css 740 -->
   
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
               <div class="search-bar">
                   <div class="search-bar-tablecell">
                       <div class="search-bar-box">
                           <input type = "text" id="search-input" placeholder="검색어를 입력하세요." >
                           <button type="button" id="btn_search" >Search</button>
                           <ul id="schoolList"></ul> <!-- 검색리스트가 나타나는 영역 -->
                           <div class="close-btn"><a href='#' onclick='history.go(-1); return false;'>취소</a></div>
                           
                          
                       </div>
                     </div>
                    <div id="searchResults">

               </div>
               <script>
               function search() {
                   // 입력한 검색어 값을 가져옵니다.
                   var searchValue = document.getElementById('search-input').value;
                   
                   // 검색어 값을 이용하여 새로운 URL을 생성합니다.
                   var newURL = 'search.do?pname=' + encodeURIComponent(searchValue);
               
                   // 생성한 URL로 페이지를 이동합니다.
                   window.location.href = newURL;
               }

               // 버튼 클릭 이벤트 처리
               document.getElementById('btn_search').addEventListener('click', search);

               // Enter 키 이벤트 처리
               document.getElementById('search-input').addEventListener('keydown', function(event) {
                   if (event.key === 'Enter') {
                       // Enter 키가 눌렸을 때 검색 동작을 실행합니다.
                       search();
                   }
               });
               </script>
                <!-- 카테고리 이동 -->
               <div class="category">
               
                  <div class="alcohol">
                     <a href="search.do?pcategory=과실주">
                        <img alt="" src="assets/img/store/icon_gwashilju.png">
                        <br>
                        <span>과실주</span>
                     </a>
                  </div>
                  
                  <div class="alcohol">
                     <a href="search.do?pcategory=증류주">
                        <img alt="" src="assets/img/store/icon_jeungryuju.png">
                        <br>
                        <span>증류주</span>
                     </a>
                  </div>
                  
                  <div class="alcohol">
                     <a href="search.do?pcategory=청주">
                        <img alt="" src="assets/img/store/icon_cheongju.png">
                        <br>
                        <span>청주</span>
                     </a>
                  </div>
                  
                  <div class="alcohol">
                     <a href="search.do?pcategory=탁주">
                        <img alt="" src="assets/img/store/icon_takju.png">
                        <br>
                        <span>탁주</span>
                     </a>
                  </div>
                  
               </div>
               <!-- 카테고리 이동 -->

               </div>
            </div>
        </div>
    </div>

   <!-- end search area -->

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script type = "text/javascript">
    
    $("#search-input").on("keyup", function FilterSearch() {
            var productName = $("#search-input").val();
            
           console.log("ok");
            console.log(productName);
         
            // Ajax 요청 보내기
            $.ajax({
               
                url: "filterSearch.do", // 서블릿 URL
                type: "POST", // 요청 방식
                data: { pName: productName }, // 서버로 보낼 데이터
                dataType: "json",
                success: function (json) {
                    var searchResults = $("#searchResults");
                    searchResults.empty(); // 이전 결과를 비우고 새로운 결과를 추가

                    if(json.length < 24) {
                       
                         for(var i = 0; i < json.length; i++) {
                           var product = json;
                           var productName = product[i].pName;
                           var productID = product[i].pNum;
                           // 상품 정보를 화면에 표시 (일단, 이름만 ..)
                           searchResults.append("<p><a href='#' data-product-id='" + productID + "'>" + productName + "</a></p>");
                           console.log("ok3");
                           console.log(product);
                           console.log(product.length);
                           console.log(searchResults);
                        }
                    }
                },
                error: function (error) {
                   console.log('error [' + error + ']');
                }
            });
        });
        
    </script>


</body>
</html>
   
   <!-- jquery -->
   <script src="assets/js/jquery-1.11.3.min.js"></script>
   <!-- bootstrap -->
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>
   <!-- count down -->
   <script src="assets/js/jquery.countdown.js"></script>
   <!-- isotope -->
   <script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
   <!-- waypoints -->
   <script src="assets/js/waypoints.js"></script>
   <!-- owl carousel -->
   <script src="assets/js/owl.carousel.min.js"></script>
   <!-- magnific popup -->
   <script src="assets/js/jquery.magnific-popup.min.js"></script>
   <!-- mean menu -->
   <script src="assets/js/jquery.meanmenu.min.js"></script>
   <!-- sticker js -->
   <script src="assets/js/sticker.js"></script>
   <!-- main js -->
   <script src="assets/js/main.js"></script>

</body>
</html>