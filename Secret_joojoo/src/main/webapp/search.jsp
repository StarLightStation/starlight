<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
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
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Crimson+Text:ital,wght@0,400;0,600;0,700;1,400;1,600;1,700&display=swap" rel="stylesheet">
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
<!-- 필터 검색 페이지 추가 CSS -->
<link rel="stylesheet" href="assets/css/searchExpansion.css">
<!-- search style -->
<link rel="stylesheet" href="assets/css/search.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous">
    </script>

</head>

<body>

	<!-- 검색 main.css 740 -->

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="search-bar">
					<div class="search-bar-tablecell">
						<div class="search-bar-box">
							<input type="text" id="search-input" placeholder="검색어를 입력하세요.">
							<button type="button" id="btn_search">검색</button>
							<a href='#' onclick='history.go(-1); return false;' class="close-btn">취소</a>
							<ul id="schoolList"></ul>
						</div>
					</div>


					<div id="searchResultsMsg">상품 이름을 클릭 하면 상품을 볼 수 있습니다.</div>
					<div id="searchResults"></div>
					<script type="text/javascript">

                    function search() {
                        //  입력한 검색어 값을 가져 오기.
                        var searchValue = document.getElementById('search-input').value;
						
                        //  새로운 URL을 생성 하고, 값을 넣어 보내기.
                        var newURL = 'searchFilter.do?pName=' + encodeURIComponent(searchValue);

                        //  생성한 URL로 페이지로 이동 하기.
                        window.location.href = newURL;
                    }

                    //  버튼 클릭 이벤트 처리
                    document.getElementById('btn_search').addEventListener('click', search);

                    //  Enter 키 이벤트 처리
                    document.getElementById('search-input').addEventListener('keydown', function (event) {
                        if (event.key === 'Enter') {
                            search();
                        }
                    });

                    $("#search-input").on("keyup", function FilterSearch() {

                        var productName = $("#search-input").val();

                        console.log(productName);

                        $.ajax({

                            url: "search.do",   //  서블릿 URL
                            type: "POST",   //    요청 방식
                            data: {pName: productName}, //  서버로 보낼 데이터
                            dataType: "json",   //  produces랑 맞추는 부분
                            //  contentType => 화면에서 받는 DOC(String) 데이터 출력시 사용 설정
                            //  페이지에 VIEW에 인코딩하는 것은 xxx

                            success: function (json) {
                            	var maxSize = ${maxSize};
                                var searchResults = $("#searchResults");
                                searchResults.empty();  //  이전 결과를 비우고 새로운 결과를 추가 하기.

                                if (json.length < maxSize) {

                                    for (var i = 0; i < json.length; i++) {

                                        var product = json;
                                        var productName = product[i].pName;
                                        var productID = product[i].pNum;

                                        //  쿼리 매개변수에 productName 을 포함 하기.
                                        var link = "searchFilter.do?pName=" + encodeURIComponent(productName);
                                        searchResults.append
                                        ("<h5><a href='" + link + "' data-product-id='" + productID + "'>" + productName + "</a></h5>");

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

					<hr>

					<!-- 카테고리 이동 -->
					<div class="categoryContainer">
						<div class="category" style="margin-left: auto;">
							<div class="alcohol" style="padding-top: 20px;">
								<a href="searchFilter.do?pCategory=과실주"> <img alt="" src="assets/img/store/icon_gwashilju.png"> <br> <span>과실주</span>
								</a>
							</div>

							<div class="alcohol" style="padding-top: 20px;">
								<a href="searchFilter.do?pCategory=증류주"> <img alt="" src="assets/img/store/icon_jeungryuju.png"> <br> <span>증류주</span>
								</a>
							</div>

							<div class="alcohol" style="padding-top: 20px;">
								<a href="searchFilter.do?pCategory=청주"> <img alt="" src="assets/img/store/icon_cheongju.png"> <br> <span>청주</span>
								</a>
							</div>

							<div class="alcohol" style="padding-top: 20px;">
								<a href="searchFilter.do?pCategory=탁주"> <img alt="" src="assets/img/store/icon_takju.png"> <br> <span>탁주</span>
								</a>
							</div>
						</div>
					</div>
					<!-- 카테고리 이동 -->

				</div>
			</div>
		</div>
	</div>

	<script>
    const searchInput = document.getElementById("search-input");
    const category = document.getElementById("category");

    //  입력란의 길이에 따라 카테고리 아이콘과 텍스트 크기 조절 하기.
    searchInput.addEventListener("input", () => {
        const inputWidth = searchInput.clientWidth;
        category.style.width = inputWidth + "px";
    });
</script>

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
