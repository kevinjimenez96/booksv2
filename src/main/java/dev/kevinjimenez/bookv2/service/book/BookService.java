package dev.kevinjimenez.bookv2.service.book;

import dev.kevinjimenez.bookv2.model.Book;

import java.util.List;

public interface BookService {
    public Book findById(int id);

    public List<Book> find();

    public void insert(Book book);

    public void update(Book book);

    public void delete(Book book);

    public void delete(int id);
}
