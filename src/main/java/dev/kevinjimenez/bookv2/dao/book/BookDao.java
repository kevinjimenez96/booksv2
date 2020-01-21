package dev.kevinjimenez.bookv2.dao.book;

import dev.kevinjimenez.bookv2.dto.BookDTO;

import java.util.List;

public interface BookDao {
    public List<BookDTO> find();

    public BookDTO findById(int id);

    public void update(BookDTO bookDTO);

    public void inset(BookDTO bookDTO);

    public void delete(BookDTO bookDTO);
}
