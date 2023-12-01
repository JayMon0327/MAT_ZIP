package com.mat.zip.boss.model;

public class BoardVO {

    private int board_id;
    private String writer;
    private String title;
    private String content;
    private java.sql.Timestamp regdate;
    private java.sql.Timestamp updatedate;
    private java.sql.Timestamp deletedate;
    private int viewscount;
    private int likecount;
    private boolean liked;
    private int commentCount;

    // getters and setters

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardVO [board_id=" + board_id + ", writer=" + writer + ", title=" + title + ", content=" + content
                + ", regdate=" + regdate + ", updatedate=" + updatedate + ", deletedate=" + deletedate + ", viewscount="
                + viewscount + ", likecount=" + likecount + "]";
    }

}
