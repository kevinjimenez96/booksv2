package dev.kevinjimenez.bookv2.service;

import dev.kevinjimenez.bookv2.dao.book.BookDao;
import dev.kevinjimenez.bookv2.service.book.BookService;
import dev.kevinjimenez.bookv2.service.book.implementation.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Mock
    BookDao bookDao;

    @InjectMocks
    BookService bookService = new BookServiceImpl();

    @Test
    public void test_FindBooksReturnAllBooks(){
        
    }

}
