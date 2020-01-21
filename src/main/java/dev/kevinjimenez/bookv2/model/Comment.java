package dev.kevinjimenez.bookv2.model;

import dev.kevinjimenez.bookv2.dto.CommentDTO;

public class Comment {
    private CommentId commentId;
    private float score;
    private String comment;

    public Comment(){}

    public Comment(CommentDTO commentDTO){
        this.commentId = new CommentId(commentDTO.getCommentIdDTO());
        this.score = commentDTO.getScore();
        this.comment = commentDTO.getComment();
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public void setCommentId(CommentId commentId) {
        this.commentId = commentId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
