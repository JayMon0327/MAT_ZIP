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
						class="badge rounded-pill text-bg-warning p-3">글쓰기</span></em></strong>
			</h3>
		</div>
		<br>
		<div class="row" class="col-md-9">
			<!-- 로우설정  -->
			<form action="saveBoard" method="get">
			<input name="writer" value="${user_id}" type="hidden"
				style="width: 600px;"><br> <input name="title"
				placeholder="제목을 입력해주세요" maxlength="50" style="width: 600px;"><br>
			<br>
			<textarea name="content" placeholder="내용을 입력해주세요" maxlength="1000"
				style="width: 600px; height: 350px;"></textarea>
			<br>
			<hr>
			<button type="submit" class="btn btn-warning" style="margin-bottom: 500px;">등록</button>
			</form>
			</div>
			</div>
			

<script src="../resources/js/boss_menu.js"></script>
<script src="../resources/js/boss_reply.js"></script>
<%@ include file = "../layout/footer.jsp" %>