<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

	<br>
	<div class="container">
		<!--컨테이너  -->
		<div class="row" class="col-md-9">
			<!-- 로우설정  -->
			<h3>
				<strong><em><span
						class="badge rounded-pill text-bg-warning p-3">삭제하시겠습니까?</span></em></strong>
			</h3>
		</div>
		<br>
		<div class="row" class="col-md-9">
			<!-- 로우설정  -->
			<form action="Board_delete" method="get">
			<input name="board_id" value="${sessionScope.board_id}" type="hidden"><br>
			<hr color="green">
			<button type="submit" class="btn btn-danger" style="margin-bottom: 500px;">삭제</button>
		</form>
			</div>
			</div>

 <script src="../resources/js/boss_menu.js?ver=3"></script>

<%@ include file = "../layout/footer.jsp" %>
