package dev.kevinjimenez.bookv2.controller;

import dev.kevinjimenez.bookv2.Util.JWTUtil;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Book;
import dev.kevinjimenez.bookv2.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks( @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        return bookService.find();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        return bookService.findById(id);
    }

    @PostMapping
    public Book postBook(@RequestBody(required = true) Book book, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.bookService.insert(book);
        return book;
    }

    @PutMapping
    public Book putBook(@RequestBody(required = true) Book book, @RequestHeader("Authorization") String token) throws ValueNotFoundException {
        JWTUtil.verifyToken(token);
        this.bookService.update(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.bookService.delete(id);
    }
}
