package dev.kevinjimenez.bookv2.dao.book.implementation;

import dev.kevinjimenez.bookv2.dao.book.BookDao;
import dev.kevinjimenez.bookv2.dto.BookDTO;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookDaoImpl implements BookDao {
    private HashMap<Integer, BookDTO> books;

    public BookDaoImpl(){
        this.books = new HashMap<Integer, BookDTO>();
    }

    @Override
    public List<BookDTO> find() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public BookDTO findById(int isbn) {
        return this.books.get(isbn);
    }

    @Override
    public void update(BookDTO bookDTO) throws ValueNotFoundException {
        if (this.books.replace(bookDTO.getIsbn(), bookDTO) == null){
            throw new ValueNotFoundException("The book doesn't exists.");
        }
    }

    @Override
    public void inset(BookDTO bookDTO) {
        this.books.put(bookDTO.getIsbn(), bookDTO);
    }

    @Override
    public void delete(BookDTO bookDTO) {
        this.books.remove(bookDTO.getIsbn());
    }
}
