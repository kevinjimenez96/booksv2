package dev.kevinjimenez.bookv2.dto;

import dev.kevinjimenez.bookv2.model.Comment;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

public class CommentDTO {
    private CommentIdDTO commentIdDTO;
    private float score;
    private String comment;

    public CommentDTO(Comment comment){
        this.commentIdDTO = new CommentIdDTO(comment.getCommentId());
        this.score = comment.getScore();
        this.comment = comment.getComment();
    }

    public CommentIdDTO getCommentIdDTO() {
        return commentIdDTO;
    }

    public void setCommentIdDTO(CommentIdDTO commentIdDTO) {
        this.commentIdDTO = commentIdDTO;
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
