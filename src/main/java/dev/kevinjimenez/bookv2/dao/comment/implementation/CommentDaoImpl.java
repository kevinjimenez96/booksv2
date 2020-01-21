package dev.kevinjimenez.bookv2.dao.comment.implementation;

import dev.kevinjimenez.bookv2.dao.comment.CommentDao;
import dev.kevinjimenez.bookv2.dto.CommentDTO;
import dev.kevinjimenez.bookv2.dto.CommentIdDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentDaoImpl implements CommentDao {
    private HashMap<CommentIdDTO, CommentDTO> comments;

    public CommentDaoImpl() {
        this.comments = new HashMap<>();
    }

    @Override
    public List<CommentDTO> find() {
        return new ArrayList<>(comments.values());
    }

    @Override
    public CommentDTO findById(String email, int isbn) {
        CommentIdDTO commentIdDTO = new CommentIdDTO();
        commentIdDTO.setBookISNB(isbn);
        commentIdDTO.setUserEmail(email);
        return this.comments.get(commentIdDTO);
    }

    @Override
    public void insert(CommentDTO comment) {

    }

    @Override
    public void update(CommentDTO comment) {

    }

    @Override
    public void delete(String email, int isbn) {

    }
}
