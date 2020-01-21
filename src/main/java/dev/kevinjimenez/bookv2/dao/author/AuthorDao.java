package dev.kevinjimenez.bookv2.dao.author;

import dev.kevinjimenez.bookv2.dto.AuthorDTO;

import java.util.List;

public interface AuthorDao {

    public AuthorDTO findById(int id);

    public List<AuthorDTO> find();

    public void insert(AuthorDTO author);

    public void update(AuthorDTO author);

    public void delete(AuthorDTO author);
}
