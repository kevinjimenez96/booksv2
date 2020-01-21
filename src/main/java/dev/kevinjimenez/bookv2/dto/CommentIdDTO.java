package dev.kevinjimenez.bookv2.dto;

import dev.kevinjimenez.bookv2.model.CommentId;

import java.util.Objects;

public class CommentIdDTO {
    private String userEmail;
    private int bookISNB;

    public CommentIdDTO(){}

    public CommentIdDTO(CommentId commentId) {
        this.userEmail = commentId.getUserEmail();
        this.bookISNB = commentId.getBookISNB();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getBookISNB() {
        return bookISNB;
    }

    public void setBookISNB(int bookISNB) {
        this.bookISNB = bookISNB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return getBookISNB() == commentId.getBookISNB() &&
                Objects.equals(getUserEmail(), commentId.getUserEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getBookISNB());
    }
}
