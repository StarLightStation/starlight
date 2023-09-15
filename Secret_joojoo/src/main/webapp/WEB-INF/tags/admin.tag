<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->
                <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
            <a href="adminMain.do" class="app-brand-link">
              <span class="app-brand-logo demo" >
                <img src="assets/img/logo.png" alt="새 로고" style="width: 118px; height: 30px;" >
              </span>
            </a>
        



            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>

          <ul class="menu-inner py-1">
          
          
          
          
            <!-- 홈 -->
            <li class="menu-item active">
              <a href="adminMain.do" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div data-i18n="Analytics">홈</div>
              </a>
            </li>

         
            <!-- 회원 -->
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">회원정보</span>
            </li>
            
            
            <li class="menu-item">
              <a href="memberList.do" class="menu-link">
                <i class="menu-icon tf-icons bx bx-collection"></i>
                <div data-i18n="Basic">회원관리</div>
              </a>
            </li>
            <!-- 회원 끝-->
            
             <!-- 상품 -->
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">상품정보</span>
            </li>
            
            
            <li class="menu-item">
              <a href="adminProduct.do" class="menu-link">
                <i class="menu-icon tf-icons bx bx-collection"></i>
                <div data-i18n="Basic">상품관리</div>
              </a>
            </li>      
             <!-- 상품 끝-->     
             
             <!-- 매출 -->
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">매출정보</span>
            </li>
            
            
            <li class="menu-item">
              <a href="sale.do" class="menu-link">
                <i class="menu-icon tf-icons bx bx-collection"></i>
                <div data-i18n="Basic">매출관리</div>
              </a>
            </li>      
             <!-- 매출 끝-->     
             
             
             <!-- 리뷰 -->
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">리뷰정보</span>
            </li>
            
            
            <li class="menu-item">
              <a href="adminReview.do" class="menu-link">
                <i class="menu-icon tf-icons bx bx-collection"></i>
                <div data-i18n="Basic">리뷰관리</div>
              </a>
            </li>      
             <!-- 리뷰 끝-->     
             
             
             
 
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">