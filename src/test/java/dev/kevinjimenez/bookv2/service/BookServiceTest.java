package dev.kevinjimenez.bookv2.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.kevinjimenez.bookv2.dao.book.BookDao;
import dev.kevinjimenez.bookv2.dto.AuthorDTO;
import dev.kevinjimenez.bookv2.dto.BookDTO;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.model.Book;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import dev.kevinjimenez.bookv2.service.book.BookService;
import dev.kevinjimenez.bookv2.service.book.implementation.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Mock
    BookDao bookDao;
    @Mock
    AuthorService authorService;

    @InjectMocks
    BookService bookService = new BookServiceImpl();

    @Test
    public void test_FindBooksReturnAllBooks(){
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookDTOS.add(new BookDTO(1, "Cats", 1, LocalDate.of(1996, 8,29),
                0.0, 0, null, null));
        bookDTOS.add(new BookDTO(2, "Dogs", 2, LocalDate.of(1996, 8,29),
                0.0, 0, null, null));

        Mockito.when(bookDao.find()).thenReturn(bookDTOS);

        List<Book> books = bookService.find();

        Assert.assertTrue(books.contains(new Book(1, 1,"Cats", LocalDate.of(1996, 8,
                29), 0.0)));
        Assert.assertTrue(books.contains(new Book(2, 2,"Dogs", LocalDate.of(1996, 8,
                29), 0.0)));
    }

    @Test
    public void test_FindBookReturnBook(){
        BookDTO bookDTO = new BookDTO(1, "Cats", 1, LocalDate.of(1996, 8,29),
                0.0, 0, null, null);

        Mockito.when(bookDao.findById(1)).thenReturn(bookDTO);

        try{
            Book book = bookService.findById(1);
            Assert.assertEquals(book.getIsbn(), 1);
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_FindNonExistingBookFails(){
        Mockito.when(bookDao.findById(1)).thenReturn(null);

        try{
            Book book = bookService.findById(1);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_InsertBookSuccess(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);
        Mockito.when(bookDao.findById(1)).thenReturn(null);
        Mockito.when(authorService.findById(book.getAuthorId())).thenReturn(author);

        try {
            bookService.insert(book);
            Mockito.verify(bookDao, Mockito.times(1)).insert(Mockito.any(BookDTO.class));
        } catch (ExistingValueException e) {
            Assert.fail();
        }
    }

    @Test
    public void test_InsertExistingBookFails(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(new BookDTO(book));

        try {
            bookService.insert(book);
            Assert.fail();
        } catch (ExistingValueException e) {
            Assert.assertTrue(true);
        }
        Mockito.verify(bookDao, Mockito.times(0)).insert(Mockito.any(BookDTO.class));
    }

    @Test
    public void test_UpdateBookSuccess(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(new BookDTO(book));

        try {
            bookService.update(book);
            Mockito.verify(bookDao, Mockito.times(1)).update(Mockito.any(BookDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_UpdateNonExistingBookFails(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(null);

        try {
            bookService.update(book);
            Assert.fail();
        } catch (ValueNotFoundException e){
           Assert.assertTrue(true);
        }
        Mockito.verify(bookDao, Mockito.times(0)).update(Mockito.any(BookDTO.class));
    }

    @Test
    public void test_DeleteBookSuccess(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(new BookDTO(book));

        try {
            bookService.delete(book);
            Mockito.verify(bookDao, Mockito.times(1)).delete(Mockito.any(BookDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteNonExistingBookSuccess(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(null);

        try {
            bookService.delete(book);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(bookDao, Mockito.times(0)).delete(Mockito.any(BookDTO.class));
    }

    @Test
    public void test_DeleteBookWithIdSuccess(){
        Book book = new Book(1, 1,"Cats", LocalDate.of(1996, 8, 29), 0.0);

        Mockito.when(bookDao.findById(1)).thenReturn(new BookDTO(book));

        try {
            bookService.delete(1);
            Mockito.verify(bookDao, Mockito.times(1)).delete(Mockito.any(BookDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteNonExistingBookWithIdSuccess(){
        Mockito.when(bookDao.findById(1)).thenReturn(null);

        try {
            bookService.delete(1);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(bookDao, Mockito.times(0)).delete(Mockito.any(BookDTO.class));
    }
}
