<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="breadcrumb-section">
    <div class="breadcrumb-text">
        <h4 style="font-size:16px; font-weight: 500;">[ ${mGrade} ] ${mName}님</h4>

        <div>
            <a href="mySubscription.do">구독 및 쿠폰 관리</a>
            <img alt="subscription" src="assets/img/sub.png">
        </div>

        <div>
            <a href="orderHistory.do">주문내역</a>
            <img alt="orders" src="assets/img/order.png">
        </div>

        <div>
            <a href="writeReview.do">리뷰</a>
            <img alt="reviews" src="assets/img/review.png">
        </div>

        <div>
            <a href="member.do">회원정보</a>
            <img alt="userInfo" src="assets/img/member.png">
        </div>

    </div>
</div>