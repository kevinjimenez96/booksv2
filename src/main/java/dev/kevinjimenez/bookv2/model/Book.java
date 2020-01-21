package dev.kevinjimenez.bookv2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kevinjimenez.bookv2.dto.BookDTO;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int isbn;
    private int authorId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publication;
    private double score;

    public Book(){

    }

    public Book(int isbn, int authorId, String title, LocalDate publication, double score) {
        this.isbn = isbn;
        this.authorId = authorId;
        this.title = title;
        this.publication = publication;
        this.score = score;
    }

    public Book(BookDTO bookDTO) {
        this.authorId = bookDTO.getAuthorId();
        this.title = bookDTO.getTitle();
        this.isbn = bookDTO.getIsbn();
        this.publication = bookDTO.getPublication();
        this.score = bookDTO.getScore();
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double calification) {
        this.score = calification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getIsbn() == book.getIsbn() &&
                getAuthorId() == book.getAuthorId() &&
                Double.compare(book.getScore(), getScore()) == 0 &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublication(), book.getPublication());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getAuthorId(), getTitle(), getPublication(), getScore());
    }
}
