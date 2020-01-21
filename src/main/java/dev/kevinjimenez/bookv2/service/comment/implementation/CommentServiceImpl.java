package dev.kevinjimenez.bookv2.service.comment.implementation;

import dev.kevinjimenez.bookv2.dao.comment.CommentDao;
import dev.kevinjimenez.bookv2.dto.CommentDTO;
import dev.kevinjimenez.bookv2.model.Comment;
import dev.kevinjimenez.bookv2.model.CommentId;
import dev.kevinjimenez.bookv2.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    @Autowired
    public void setCommentDao(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> find() {
        List<CommentDTO> commentDTOS = this.commentDao.find();
        List<Comment> comments = new ArrayList<>();
        for (CommentDTO commentDTO: commentDTOS) {
            Comment comment = new Comment(commentDTO);
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Comment findById(CommentId commentId) {
        CommentDTO commentDTO = commentDao.findById(commentId.getUserEmail(), commentId.getBookISNB());
        Comment comment = new Comment(commentDTO);
        return comment;
    }

    @Override
    public void insert(Comment comment) {
        CommentDTO commentDTO = new CommentDTO(comment);
        this.commentDao.insert(commentDTO);
    }

    @Override
    public void update(Comment comment) {
        CommentDTO commentDTO = new CommentDTO(comment);
        this.commentDao.update(commentDTO);
    }

    @Override
    public void delete(CommentId commentId) {
        this.commentDao.delete(commentId.getUserEmail(), commentId.getBookISNB());
    }
}
