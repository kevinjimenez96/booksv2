package dev.kevinjimenez.bookv2.controller;

import dev.kevinjimenez.bookv2.Util.JWTUtil;
import dev.kevinjimenez.bookv2.exception.IdMismatchException;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.model.Book;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthor(@RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        return this.authorService.find();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        return this.authorService.findById(id);
    }

    @PostMapping
    public Author postAuthor(@RequestBody(required = true) Author author, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.authorService.insert(author);
        return author;
    }

    @PutMapping
    public Author putAuthor(@RequestBody(required = true) Author author, @RequestHeader("Authorization") String token) throws ValueNotFoundException {
        JWTUtil.verifyToken(token);
        this.authorService.update(author);
        return author;
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.authorService.delete(id);
    }

}
