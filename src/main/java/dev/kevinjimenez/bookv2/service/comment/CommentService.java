package dev.kevinjimenez.bookv2.service.comment;

import dev.kevinjimenez.bookv2.model.Comment;
import dev.kevinjimenez.bookv2.model.CommentId;

import java.util.List;

public interface CommentService {

    public List<Comment> find();

    public Comment findById(CommentId commentId);

    public void insert(Comment comment);

    public void update (Comment comment);

    public void delete(CommentId commentId);
}
