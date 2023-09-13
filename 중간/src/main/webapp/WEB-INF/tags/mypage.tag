<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="breadcrumb-section">
               <div class="breadcrumb-text"><!-- main.css 487 -->
                  <h4> ${mname}님 </h4>
                  
                  
                  
                  <div>
                     <a href="mysubscription.do">구독관리</a>
                     <img alt="subscription" src="assets/img/sub.png">
                  </div>
                   
                  <div>
                     <a href="orderHistory.do">주문내역</a>
                     <img alt="orders" src="assets/img/order.png">
                  </div>
                   
                  <div>
                     <a href="writereview.do">리뷰</a>
                     <img alt="reviews" src="assets/img/review.png">
                  </div> 
                  
                  <div>
                     <a href="member.do?mid=${mid}">회원정보</a>
                     <img alt="userInfo" src="assets/img/member.png">
                  </div>
                  
               </div>
   </div>