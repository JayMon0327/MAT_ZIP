
  <!-- 댓글작성관련 -->
	$(function() {
		$('#b1').click(function() {
			content = $('#com').val()
			writer = "${user_id}"

			$.ajax({
				url : "saveReply",
				data : {
					board_id : '${bag.board_id}',
					content : content,
					writer : writer

				},
				success : function(x) {
					$('#result').load(location.href + ' #result')
					/* $('#result').empty();
					$('#result').append(x); */
					/* location.reload(); */
					/* $('#result').append(
						"- " + content + ", " + writer + "<br>") */
				},
				error : function() {
					alert('computer요청 실패!')
				}//error
			})//ajax
		})//b1
	})//$

<!-- 댓글수정,삭제관련 -->
$(function() {
    // 댓글 메뉴 열기/닫기
    $(document).on('click', '.menu-icon', function() {
        var menuOptions = $(this).closest('tr').find('.menu-options');
        menuOptions.toggle();
    });

    // 수정 버튼 클릭 시
    $(document).on('click', '.edit-option', function() {
        var commentContent = $(this).closest('tr').find('.comment-content');
        var editInput = $(this).closest('tr').find('.edit-comment');

        commentContent.hide();
        editInput.val(commentContent.text()).show().focus();
    });

    // 수정된 내용 저장 버튼 클릭 시
    $(document).on('focusout', '.edit-comment', function() {
        var commentContent = $(this).closest('tr').find('.comment-content');
        var editInput = $(this).closest('tr').find('.edit-comment');
        var commentId = $(this).closest('tr').data('comment-id');
        var updatedContent = editInput.val();

        // AJAX 요청을 보내서 수정된 내용 저장 및 적용
        $.ajax({
            url: 'updateReply',
            method: 'POST',
            data: {
                reply_id: commentId,
                content: updatedContent
            },
            success: function(response) {
                commentContent.text(updatedContent).show();
                editInput.hide();
            },
            error: function() {
                alert('Failed to update comment!');
            }
        });
    });

    // 삭제 옵션 클릭 시
    $(document).on('click', '.delete-option', function() {
        var commentId = $(this).closest('tr').data('comment-id');

        // AJAX 요청을 보내서 댓글 삭제
        $.ajax({
            url: 'deleteReply',
            method: 'POST',
            data: {
                reply_id: commentId
            },
            success: function(response) {
                // 삭제된 댓글 표시를 갱신하는 등의 작업 수행
                location.reload();
            },
            error: function() {
                alert('Failed to delete comment!');
            }
        });
    });
});

<!-- 좋아요 -->
$(document).ready(function(){
    $('.like-form').on('submit', function(event){
        event.preventDefault();
        var form = $(this);
        var board_id = form.find('input[name="board_id"]').val();
        var likeButton = form.find('.like-button');
        var heartIcon = likeButton.find('i.bi-heart-fill, i.bi-heart');
        var likeText = likeButton.find('span');

        $.ajax({
            url: form.attr('action'),
            type: 'POST',
            data: {
                board_id: board_id
            },
            success: function(response) {
                console.log(response);
                console.log(heartIcon);
                // '좋아요' 카운트를 업데이트
                likeText.text('좋아요 ' + response.count);
                // '좋아요' 상태를 업데이트
                if (response.isLiked) {
                    heartIcon.removeClass('bi-heart').addClass('bi-heart-fill');
                } else {
                    heartIcon.removeClass('bi-heart-fill').addClass('bi-heart');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error: " + textStatus + ", " + errorThrown);
            }
        });
    });
});

