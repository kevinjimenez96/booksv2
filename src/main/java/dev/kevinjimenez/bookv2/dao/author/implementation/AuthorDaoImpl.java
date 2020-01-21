package dev.kevinjimenez.bookv2.dao.author.implementation;

import dev.kevinjimenez.bookv2.dao.author.AuthorDao;
import dev.kevinjimenez.bookv2.dto.AuthorDTO;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthorDaoImpl implements AuthorDao {

    private HashMap<Integer, AuthorDTO> authors;

    public AuthorDaoImpl() {
        this.authors = new HashMap<Integer, AuthorDTO>();
    }

    @Override
    public AuthorDTO findById(int id) {
        return this.authors.get(id);
    }

    @Override
    public List<AuthorDTO> find() {
        return new ArrayList<AuthorDTO>(this.authors.values());
    }

    @Override
    public void insert(AuthorDTO author) {
        this.authors.put(author.getId(), author);
    }

    @Override
    public void update(AuthorDTO author) throws ValueNotFoundException {
       if(this.authors.replace(author.getId(), author) == null) {
           throw new ValueNotFoundException("The author doesn't exists.");
       }
    }

    @Override
    public void delete(AuthorDTO author) {
        this.authors.remove(author.getId());
    }
}
