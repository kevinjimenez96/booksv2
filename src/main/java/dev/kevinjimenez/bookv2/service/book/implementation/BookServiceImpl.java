package dev.kevinjimenez.bookv2.service.book.implementation;

import dev.kevinjimenez.bookv2.dao.book.BookDao;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.model.Book;
import dev.kevinjimenez.bookv2.dto.BookDTO;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import dev.kevinjimenez.bookv2.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
    public Book findById(int id) throws ValueNotFoundException{
        BookDTO bookDTO = this.bookDao.findById(id);
        if(bookDTO == null){
            throw new ValueNotFoundException("The book '" + id + "' doesn't exists.");
        }
        return new Book(bookDTO);
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
    public void insert(@NotNull Book book) throws ExistingValueException {
        if (this.bookDao.findById(book.getIsbn()) != null){
            throw new ExistingValueException("The book '" + book.getIsbn() + "' already exists.");
        }
        BookDTO bookDTO = new BookDTO(book);
        Author author = this.authorService.findById(book.getAuthorId());
        if(author.getBooks() == null){
            author.setBooks(new ArrayList<Integer>());
        }
        author.getBooks().add(book.getIsbn());
        this.authorService.update(author);
        this.bookDao.insert(bookDTO);
    }

    @Override
    public void update(@NotNull Book book) throws ValueNotFoundException {
        if (this.bookDao.findById(book.getIsbn()) == null){
            throw new ValueNotFoundException("The book '" + book.getIsbn() + "' doesn't exist.");
        }
        BookDTO bookDTO = new BookDTO(book);
        this.bookDao.update(bookDTO);
    }

    @Override
    public void delete(@NotNull Book book) throws ValueNotFoundException{
        if (this.bookDao.findById(book.getIsbn()) == null){
            throw new ValueNotFoundException("The book '" + book.getIsbn() + "' doesn't exist.");
        }
        BookDTO bookDTO = new BookDTO(book);
        this.bookDao.delete(bookDTO);
    }

    @Override
    public void delete(int id) throws ValueNotFoundException {
        if (this.bookDao.findById(id) == null){
            throw new ValueNotFoundException("The book '" + id + "' doesn't exist.");
        }
        BookDTO bookDTO = this.bookDao.findById(id);
        this.bookDao.delete(bookDTO);
    }
}
