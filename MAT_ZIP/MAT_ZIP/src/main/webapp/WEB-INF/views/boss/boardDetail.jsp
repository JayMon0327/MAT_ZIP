<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

	<% if (session.getAttribute("boss_id") != null) { %>
	<br>
	<div class="container">
		<!--컨테이너  -->
		<div class="row" class="col-md-9">
			<!-- 로우설정  -->
			<h3>
				<strong><em><span
						class="badge rounded-pill text-bg-warning p-3">자유게시판</span></em></strong>
			</h3>
		</div>
		<br>
		<div class="row" class="col-md-9">
			<!-- 로우설정  -->
			<h3>
				<strong><em>${bag.title}</em></strong>
			</h3>
		</div>
		<div class="row" class="col-md-9">
			<h5>${bag.writer}</h5>
		</div>
		<div class="row" class="col-md-9">
			<h6>${bag.content}</h6>
		</div>
		
			<!--게시글 로드시 좋아요 여부에 따라 빈하트,꽉찬하트 - 현재 likes DB 더미데이터부족으로 구현불가 , 
			더미데이터 확보한 이후 위의 form action대신 사용하면됨  -->
			<%-- <form action="bosslike" method="post" class="like-form">
		    <input type="hidden" name="board_id" value="${board.board_id}">
		    <button type="submit" class="like-button">
		        <i class="${isLiked ? 'bi bi-heart-fill' : 'bi bi-heart'}"></i> <span>좋아요 ${board.likecount}</span>
		    </button>
			</form> --%>
			<div style="display: flex; align-items: center;">
			  <form action="bosslike" method="post" class="like-form">
			    <input type="hidden" name="board_id" value="${bag.board_id}">
			    <button type="submit" class="like-button btn btn-outline-dark">
			      <i class="${isLiked ? 'bi bi-heart-fill' : 'bi bi-heart'}" style="color:red;"></i> 
			      <span>좋아요 ${bag.likecount} </span>
			    </button>
			  </form>
			  <div style="width:50; font:bold;" class="btn btn-outline-dark">  댓글 개수: ${commentCount}</div>
			</div>
			</div>
			
			
	<hr style="border: solid 3px gray;">
	<div id="result">
		<div class="container">
			<div class="row">
				<h4>
					<strong>댓글</strong>
				</h4>
				<table>
					<c:forEach items="${Com_list}" var="bag">
						<tr>
							<em><span style="border-bottom: 2px solid green;">${bag.writer}<br></span></em>
						</tr>
						<tr>${bag.content}<br>
						</tr>
						<tr>
							<fmt:formatDate value="${bag.regdate}" pattern="yyyy-MM-dd HH:mm" />
							<hr>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 댓글 -->
	<div class="container">
		<% if (session.getAttribute("boss_id") != null) { %>
		<h5 style="color: green;">회원:${user_id}</h5>

		<input id="com">
		<button id="b1" class="btn btn-outline-success">작성</button>
		<br>

		<%
			} else {
		%>로그인시 작성가능<%
			}
		%>
		<!--댓글끝 -->

<!-- 글수정  -->
		<hr color="green">
		<%
		//세션에서 값을 꺼내는 방법
		String id = (String) session.getAttribute("user_id");
		//모델에서 값을 꺼내는 방법
		//model.addAttribute("bag",bag)
		BoardVO bag = (BoardVO) request.getAttribute("bag");
		String writer = bag.getWriter();
		
		// 접속중인 id와 writer가 같으면 수정 , 삭제버튼 출력
		if (id != null) {
			if (id.equals(writer)) {
				// 세션값으로 아래 내용4개를 저장해서 넘김
		session.setAttribute("board_id", bag.getBoard_id());
		session.setAttribute("title", bag.getTitle());
		session.setAttribute("content", bag.getContent());
		session.setAttribute("writer", bag.getWriter());
		%>
		<a href="boardUpdate.jsp">
			<button class="btn btn-outline-success">수정</button>
		</a> <a href="boardDelete.jsp">
			<button class="btn btn-outline-danger">삭제</button>
		</a>
		
		<%
			}
		}
		%>
	</div>
	<br>
	
	<div class="container">
    <!--컨테이너  -->
    <div class="row">
        <!-- 로우설정  -->
        <form action="Board_search" method="get" id="search">
            <input name="keyword" type="text" size="40" placeholder="제목이나 내용을 입력해주세요">
            <button type="submit" class="btn btn-outline-dark">
                검색
            </button>
        </form>
    </div>
</div>
	
	<!-- 여기까지가 사장세션 접속시 보이는 리얼값 -->
	
	<%
		} else {
	%>
	<!-- 여기서부터 블러처리 구독결제 유도 -->
	<div class="container blur parent">
		<!-- 블러 클래스를 추가합니다 -->
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
		}
	%>


<script src="../resources/js/boss_menu.js"></script>
<script src="../resources/js/boss_reply.js"></script>
<%@ include file = "../layout/footer.jsp" %>