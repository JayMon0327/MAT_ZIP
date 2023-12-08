<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

<!-- 테이블내용 -->
<br>
<div class="container">
    <!--컨테이너  -->
    <div class="row">
        <!-- 로우설정  -->
        <br>
        <table class="table" style="text-align: center; border: 1px solid #dddddd">
            <thead style="background:#FFC31E;">
                <tr>
                    <th scope="col">제목</th>
                    <th scope="col">글쓴이</th>
                    <th scope="col">작성시간</th>
                    <th scope="col">조회수</th>
                    <th scope="col">좋아요</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="boardSearch" items="${list}"> <!-- 'bag'을 'list'로 변경 -->
                    <tr>
                        <td><a href="Board_detail?board_id=${boardSearch.board_id}">${boardSearch.title}</a></td>
                        <td>${boardSearch.writer}</td>
                        <td><fmt:formatDate value="${boardSearch.regdate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>${boardSearch.viewscount}</td>
                        <td>${boardSearch.likecount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- 검색창 -->
<br>
<div class="container">
    <!--컨테이너  -->
    <div class="row">
        <!-- 로우설정  -->
        <form action="Board_search" method="get" id="search">
            <input name="keyword" type="text" size="40" placeholder="제목이나 내용을 입력해주세요">
            <button type="submit" class="btn btn-outline-dark">
                검색<i class="bi bi-search"></i>
            </button>
        </form>
    </div>
</div>

<script src="../resources/js/boss_menu.js"></script>
<script src="../resources/js/boss_reply.js"></script>
<%@ include file = "../layout/footer.jsp" %>