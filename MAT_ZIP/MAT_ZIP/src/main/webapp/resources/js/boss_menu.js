// 매출차트
$(document).ready(function() {
  $("#bossChartBtn").click(function() {
    $.ajax({
      url: "chart.jsp",
      method: "GET",
      success: function(response) {
        $("#content").html(response);
      },
      error: function(xhr, status, error) {
        console.log("Error: " + error);
      }
    });
  });
// 자유게시판 리스트
  $(document).ready(function() {
  loadBoardList();

  function loadBoardList() {
    $.ajax({
      url: "boardIndex",
      method: "GET",
      success: function(response) {
        $("#content").html(response);
      },
      error: function(xhr, status, error) {
        console.log("Error: " + error);
      }
    });
  }

  // 자유게시판 리스트 갱신
  $("#boardBtn").click(function() {
    loadBoardList(); // AJAX 요청을 보내서 게시판 리스트를 갱신
  });

  // 페이지 버튼 클릭 시
  $(document).on('click', '.page-link', function(e) {
    e.preventDefault(); // 기본 동작인 페이지 이동을 막음

    var page = $(this).attr('href').split('=')[1]; // 페이지 번호 추출
    loadBoardList(); // AJAX 요청을 보내서 게시판 리스트를 갱신
  });
});

