<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file = "../layout/header.jsp" %>

    <!-- 메인 컨텐츠 -->
    <div class="col-md-9">
      <div class="container">
        <div class="row">
          <div id="content"></div>
        </div>
      </div>
    </div>
  </div>
</div>
 <!-- 메인 컨텐츠 종료 -->
<% } else if (session.getAttribute("user_id") != null) { %> 
<!-- 여기서부터 블러처리 구독결제 유도 -->
<br>
<div class="container blur parent">
  <!--컨테이너  -->
  <div class="row">
    <!-- 로우설정  -->
    <div id="content"></div>
  </div>
</div>
<div id="payment-container"></div>
<div class="overlay" >
  <!-- 오버레이를 추가합니다 -->
  <a href="boss_member.jsp"><button class="subscribe-btn">구독 결제하기</button></a>
</div>
<div class="container blur">
  <!-- 블러 클래스를 추가합니다 -->
  <!--컨테이너  -->
  <div class="row">
  </div>
</div>
<%
  } else{
%>
<br>
<div class="container blur parent">
  <!--컨테이너  -->
  <div class="row">
    <!-- 로우설정  -->
    <div id="content"></div>
  </div>
</div>
<div id="payment-container"></div>
<div class="overlay" >
  <!-- 오버레이를 추가합니다 -->
  <a href="../mz_member/login"><button class="subscribe-btn">구독 결제하기</button></a>
</div>
<div class="container blur">
  <!-- 블러 클래스를 추가합니다 -->
  <!--컨테이너  -->
  <div class="row">
  </div>
</div>
<%} %>


  <!-- jQery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <!-- popper js -->
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
  </script>
  <!-- bootstrap js -->
  <script src="../resources/js/bootstrap.js"></script>
  <!-- owl slider -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
  </script>
  <!-- isotope js -->
  <script src="https://unpkg.com/isotope-layout@3.0.4/dist/isotope.pkgd.min.js"></script>
  <!-- nice select -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
  <!-- custom js -->
  <script src="../resources/js/custom.js"></script>
  <script src="../resources/js/boss_menu.js?ver=15"></script>
  <!-- 커뮤니티메뉴 js 파일을 추가  -->

<%@ include file="../layout/footer.jsp"%>