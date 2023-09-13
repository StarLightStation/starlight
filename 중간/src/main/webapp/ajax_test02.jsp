<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<h1>FilterSearch</h1>
<label for="productName">Product Name:</label>
<input type="text" id="productName">
<button onclick="filterSearch()">FilterSearch</button>

<div id="searchResults">
    <!-- 검색 결과를 여기에 동적으로 추가할 예정 -->
</div>

<script type="text/javascript">

    function filterSearch() {
        var productName = document.getElementById("productName").value;

        // Ajax 요청 보내기
        $.ajax({
            url: "fruitkha-1.0.0/filterSearch.do", // 서블릿 URL
            type: "POST", // 요청 방식
            data: { pName: productName }, // 서버로 보낼 데이터
            dataType: "json", // 응답 데이터 타입을 JSON으로 지정
            success: function (data) {
                var searchResults = $("#searchResults");
                searchResults.empty(); // 이전 결과를 비우고 새로운 결과를 추가
                
                console.log(data);

                // 동적으로 상품 정보를 화면에 표시
                for (var i = 0; i < data.length; i++) {
                    var productName = data[i].pName;
                    searchResults.append("<p>" + productName + "</p>");
                }
            },
            error: function (error) {
                console.log('error [' + error + ']');
            }
        });
    }

</script>
