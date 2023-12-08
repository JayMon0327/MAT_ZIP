<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

<%-- <p>${paymentResponse.toString()}</p> 전체 결제응답 메시지--%>
<!-- 결제성공 메시지 시작 -->
<br>
<div class="container">
<div class="row">
<!--  결제성공,실패 알림창 -->
<div id="alertS" class="alert alert-warning" role="alert" style="display: none;">
  <strong>성공!</strong> 결제가 성공적으로 이루어졌습니다.
</div>
<div id="alertF" class="alert alert-danger" role="alert" style="display: none;">
  <strong>실패!</strong> 결제가 취소 되었습니다.
</div>
<h3><strong><em><span class="badge rounded-pill text-bg-warning p-3">결제가 완료되었습니다.</span></em></strong></h3>
<p>주문 상품: 사장님 커뮤니티 구독 결제</p>
<p>결제 총액: <span id="totalAmount">${paymentResponse.get("totalAmount").getAsInt()}</span></p>
<% if (session.getAttribute("user_id") != null) { %>
	<h5 style="color: #FF9614;">${user_id}님 사장 회원가입을 축하합니다 !</h5><%} %>
<p style="display:none;">주문 ID: <span id="orderId">${paymentResponse.get("orderId").getAsString()}</span></p>
<p style="display:none;">주문 이름: <span id="orderName">${paymentResponse.get("orderName").getAsString()}</span></p>
</div></div>
<!-- 결제성공 메시지 끝 -->
<div class="container" style="margin-top: 600px;">
<div class="row">
</div></div>

<!-- 결제성공내역 결제내역테이블로 전달 시작 -->
<script>	
    $(document).ready(function() {
        var paymentVO = {
            "orderId": $("#orderId").text(),
            "amount": $("#totalAmount").text(),
            "orderName": $("#orderName").text()
        };

        $.ajax({
            type: "POST",
            url: "payment", // 변경이 필요할 수 있습니다.
            contentType: "application/json",
            data: JSON.stringify(paymentVO),
            success: function() {
            	$("#alertS").show();
            },
            error: function() {
            	$("#alertF").show();
            }
        });
    });
    /* 결제성공내역 결제내역테이블로 전달 종료 */
    
	/* 사장회원등록 시작 */    
    $(document).ready(function() {
        // 결제 성공 후 AJAX 요청
        if (sessionStorage.getItem("storeId")) {
            $.ajax({
                url: "innerJoinAndInsert",
                method: "POST",
                data: { store_id: sessionStorage.getItem("storeId") }  // 세션에서 storeId 값을 읽어서 AJAX 요청에 포함
            }).done(function() {
                console.log("innerJoinAndInsert 성공");
                sessionStorage.removeItem("storeId");  // 요청이 완료된 후 storeId 값을 세션에서 제거
            }).fail(function() {
                console.log("innerJoinAndInsert 실패");
            });
        }
    });
	/* 사장회원등록 종료 */
</script>    

<script src="../resources/js/boss_menu.js"></script>
<script src="../resources/js/boss_reply.js"></script>
<%@ include file = "../layout/footer.jsp" %>