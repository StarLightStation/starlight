<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


   <!--PreLoader-->
    <div class="loader">
        <div class="loader-inner">
            <div class="circle"></div>
        </div>
    </div>
    <!--PreLoader Ends-->

	<c:if test="${not empty mID}">
	   <!-- header -->
	   <div class="top-header-area" id="sticker">
	      <div class="container">
	         <div class="row">
	            <div class="col-lg-12 col-sm-12 text-center">
	               <div class="main-menu-wrap">
	                  <!-- logo -->
	                  <div class="site-logo">
	                     <a href="main.do">
	                        <img src="assets/img/logo.png" alt="">
	                     </a>
	                  </div>
	                  <!-- logo -->
	                  
	   
	   <!-- menu start -->
	                  <nav class="main-menu">
	                     <ul>
	                        <li><a href="subscription.do">구독</a></li>
	                        <li><a href="store.do">스토어</a></li>
	                        <li class="login-menu">
	                           <div class="header-icons">
	                           <span class="login">
	                           <a href="mySubscription.do"> ${mName}님 </a>
	                           <a href="logout.do"> 로그아웃 </a>
	                           </span>
	                              <a class="mobile-hide search-bar-icon" href="search.do"><i class="fas fa-search"></i></a>
	                              <a class="shopping-cart" href="cart.do"><i class="fas fa-shopping-cart"></i></a>
	                           </div>
	                        </li>
	                     </ul>
	                  </nav>
	                  <!-- 모바일용 아이콘 -->
	                  <a class="mobile-show search-bar-icon" href="search.do"><i class="fas fa-search"></i></a>
	                  <div class="mobile-menu"></div>
	                  <!-- menu end -->
	               </div>
	            </div>
	         </div>
	      </div>
	   </div>
	   <!-- end header -->
	</c:if>
	
	<c:if test="${empty mID}">
		   <!-- header -->
	   <div class="top-header-area" id="sticker">
	      <div class="container">
	         <div class="row">
	            <div class="col-lg-12 col-sm-12 text-center">
	               <div class="main-menu-wrap">
	                  <!-- logo -->
	                  <div class="site-logo">
	                     <a href="main.do">
	                        <img src="assets/img/logo.png" alt="">
	                     </a>
	                  </div>
	                  <!-- logo -->
	
	
	                  <!-- menu start -->
	                  <nav class="main-menu">
	                     <ul>
	                        <li><a href="subscription.do">구독</a></li>
	                        <li><a href="store.do">스토어</a></li>
	                        <li class="login-menu">
	                           <div class="header-icons">
	                           <span class="login">
	                           <a href="login.do">로그인/회원가입</a>
	                           </span>
	                              <a class="mobile-hide search-bar-icon" href="search.do"><i class="fas fa-search"></i></a>
	                              <a class="shopping-cart" href="cart.do"><i class="fas fa-shopping-cart"></i></a>
	                           </div>
	                        </li>
	                     </ul>
	                  </nav>
	                  <!-- 모바일용 아이콘 -->
	                  <a class="mobile-show search-bar-icon" href="search.do"><i class="fas fa-search"></i></a>
	                  <div class="mobile-menu"></div>
	                  <!-- menu end -->
	               </div>
	            </div>
	         </div>
	      </div>
	   </div>
	   <!-- end header -->
	</c:if>
