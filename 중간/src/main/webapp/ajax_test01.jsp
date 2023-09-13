<!DOCTYPE html>
<html>
<head>
    <title>FilterSearch_Test</title>
</head>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<body>
    <h1>FilterSearch</h1>
    <label for="productName">Product Name:</label>
    <input type="text" id="productName">
    <button onclick="FilterSearch()">FilterSearch</button>

    <div id="searchResults">
        <!-- 검색 결과를 여기에 동적으로 추가할 예정 -->
    </div>

    <script type = "text/javascript">
    
        function FilterSearch() {
            var productName = $("#productName").val();

            // Ajax 요청 보내기
            $.ajax({
                url: "fruitkha-1.0.0/filterSearch.do", // 서블릿 URL
                type: "POST", // 요청 방식
                data: { pName: productName }, // 서버로 보낼 데이터
                success: function (pdatas) {
                	
                	//	console.log(pdatas);
                	
                    var searchResults = $("#searchResults");
                    searchResults.empty(); // 이전 결과를 비우고 새로운 결과를 추가

                    for (var i = 0; i < pdatas.length; i++) {
                        var product = pdatas[i];
                        //	var productName = product.pName;
                        
                        //	console.log(product);
                        	console.log('pName : ' + product.pName);
                    	
                        // 상품 정보를 화면에 표시 (일단, 이름만 ..)
                       	// searchResults.append("<p>" + productName + "</p>");
                        //	searchResults.append("<p>" + product + "</p>");
                        
                        searchResults.append(product);
                        
                        //	console.log(product);
                        //	console.log(productName);
                        
                        //	Ajax가 데이터 받을 때,
                        //	pdatas 자체 데이터를 받는게 아니라,
                        //	pdatas.toString() 이거를 받는듯 ? == 텍스트로 받음.
                    }
                },
                error: function (error) {
                	console.log('error [' + error + ']');
                }
            });
        }
        
    </script>
    
</body>
</html>
