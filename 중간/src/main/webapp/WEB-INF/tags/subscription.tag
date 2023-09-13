	<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    
	<c:if test="${empty mid}">
		   <button class="sub-openBtn" onclick="window.location.href='loginPage.do'" style="border: none; border-radius: 5px;" >
	지금 신청하기</button>
	</c:if>
	
	<c:if test="${not empty mid}">
		<button class="sub-openBtn" style="border: none; border-radius: 5px;" >지금 신청하기</button>
   
   <div class="modal hidden">
       <div class="bg"></div>
       <div class="modalBox">
  
          <div class="checkbox-all">
          <div class="subBuy-title-box">
             <p class="subModal-title">구독하실 상품을 선택해주세요!</p>
             <button class="closeBtn">✖</button>
          </div>
          <form action="subscriptionupdate.do">
            <div class="checkbox-container">
                <input type="hidden" name="mid" value = "${mid}">
                <input type="radio" id="customCheckbox" name="subNum" value="1">
                <label for="customCheckbox">
                   <div class="sub-bg-1"></div>
                   <div class="sub-buy-text">   
                     <h3>종합 주주박스<br><span>₩ 39,000/월</span></h3>
                     <p class="excerpt">여러명의 소물리에들이 선택한 주종</p>   
                   </div>
                </label>
              </div>            
            <div class="checkbox-container">
               <input type="hidden" name="mid" value = "${mid}">
                <input type="radio" id="customCheckbox2" name="subNum" value="2">
                <label for="customCheckbox2">
                   <div class="sub-bg-1"></div>
                   <div class="sub-buy-text">   
                     <h3>증류주 주주박스<br><span>₩ 49,000/월</span></h3>
                     <p class="excerpt">고도수 애호가들이 선택한 주종</p>   
                   </div>
                </label>
             </div>
            <div class="checkbox-container">
               <input type="hidden" name="mid" value = "${mid}">
                <input type="radio" id="customCheckbox3" name="subNum" value="3">
                <label for="customCheckbox3">
                   <div class="sub-bg-1"></div>
                   <div class="sub-buy-text">   
                     <h3>과실주 주주박스<br><span>₩ 52,000/월</span></h3>
                     <p class="excerpt">와인 소물리에들이 선택한 주종</p>   
                   </div>
                </label>
            </div>
            
            <br>
            <input type="submit" id="sub-buy" value="이걸로 할게요">
          
          </form>
          </div>
        </div>
   </div>
	</c:if>