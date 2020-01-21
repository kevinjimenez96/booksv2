package dev.kevinjimenez.bookv2.dao.comment;

import dev.kevinjimenez.bookv2.dto.CommentDTO;
import dev.kevinjimenez.bookv2.model.Comment;

import java.util.List;

public interface CommentDao {

    public List<CommentDTO> find();

    public CommentDTO findById(String email, int isbn);

    public void insert(CommentDTO comment);

    public void update(CommentDTO comment);

    public void delete(String email, int isbn);
}
