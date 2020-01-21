package dev.kevinjimenez.bookv2.service.author;

import dev.kevinjimenez.bookv2.model.Author;

import java.util.List;

public interface AuthorService {

    public Author findById(int id);

    public List<Author> find();

    public void insert(Author author);

    public void update(Author author);

    public void update(Author author, List<Integer> books);

    public void delete(Author author);

    public void delete(int id);
}
