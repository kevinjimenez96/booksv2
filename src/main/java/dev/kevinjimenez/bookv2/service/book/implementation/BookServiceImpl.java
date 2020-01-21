package dev.kevinjimenez.bookv2.service.book.implementation;

import dev.kevinjimenez.bookv2.dao.book.BookDao;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.model.Book;
import dev.kevinjimenez.bookv2.dto.BookDTO;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import dev.kevinjimenez.bookv2.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService = authorService;
    }

    @Autowired
    public void setBookDao(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public Book findById(int id) {
        BookDTO bookDTO = this.bookDao.findById(id);
        Book book = new Book(bookDTO);
        return book;
    }

    @Override
    public List<Book> find() {
        List<BookDTO> bookDTOS = this.bookDao.find();
        List<Book> books = new ArrayList<Book>();
        for (BookDTO bookDTO: bookDTOS) {
            Book book = new Book(bookDTO);
            books.add(book);
        }
        return books;
    }

    @Override
    public void insert(Book book) {
        BookDTO bookDTO = new BookDTO(book);
        Author author = this.authorService.findById(book.getAuthorId());
        if(author.getBooks() == null){
            author.setBooks(new ArrayList<Integer>());
        }
        author.getBooks().add(book.getIsbn());
        this.authorService.update(author);
        this.bookDao.inset(bookDTO);
    }

    @Override
    public void update(Book book) throws ValueNotFoundException {
        BookDTO bookDTO = new BookDTO(book);
        this.bookDao.update(bookDTO);
    }

    @Override
    public void delete(Book book) {
        BookDTO bookDTO = new BookDTO(book);
        this.bookDao.delete(bookDTO);
    }

    @Override
    public void delete(int id) {
        BookDTO bookDTO = this.bookDao.findById(id);
        this.bookDao.delete(bookDTO);
    }
}
