
// 매출차트 내부의 차트 버튼 클릭 시
$("#salesChartBtn, #reorderChartBtn, #emotionChartBtn").click(function() {
  // 버튼에 따라 스크롤 이동 위치 설정
  var scrollPosition;
  if (this.id === 'salesChartBtn') {
    scrollPosition = $("#linechart_material").offset().top - 120;
  } else if (this.id === 'reorderChartBtn') {
    scrollPosition = $("#reorderChart").offset().top - 120;
  } else if (this.id === 'emotionChartBtn') {
    scrollPosition = $("#piechart_3d").offset().top - 120;
  }

  // 스크롤 이동
  $('html, body').animate({
    scrollTop: scrollPosition
  }, 200); // 200ms로 스크롤
});

