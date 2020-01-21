package dev.kevinjimenez.bookv2.dto;

import dev.kevinjimenez.bookv2.model.Book;

import java.time.LocalDate;

public class BookDTO {
    private int authorId;
    private String title;
    private int isbn;
    private LocalDate publication;
    private double score;
    private int numScores;
    private LocalDate creationDate;
    private LocalDate updateDate;

    public BookDTO(Book book) {
        this.authorId = book.getAuthorId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.publication = book.getPublication();
        this.score = book.getScore();
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

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumScores() {
        return numScores;
    }

    public void setNumScores(int numCalifications) {
        this.numScores = numScores;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
