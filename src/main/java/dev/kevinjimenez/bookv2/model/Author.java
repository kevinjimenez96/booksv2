package dev.kevinjimenez.bookv2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kevinjimenez.bookv2.dto.AuthorDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Author {
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private LocalDate deathDay;
    private double score;
    private List<Integer> books;

    public Author() {
    }

    public Author(int id, String name, LocalDate birthday, LocalDate deathDay, double score, List<Integer> books) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.deathDay = deathDay;
        this.score = score;
        this.books = books;
    }

    public Author(AuthorDTO authorDTO) {
        this.id = authorDTO.getId();
        this.name = authorDTO.getName();
        this.birthday = authorDTO.getBirthday();
        this.deathDay = authorDTO.getDeathDay();
        this.score = authorDTO.getScore();
        this.books = authorDTO.getBooks();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(LocalDate deadDay) {
        this.deathDay = deadDay;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return getId() == author.getId() &&
                Double.compare(author.getScore(), getScore()) == 0 &&
                getName().equals(author.getName()) &&
                Objects.equals(getBirthday(), author.getBirthday()) &&
                Objects.equals(getDeathDay(), author.getDeathDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthday(), getDeathDay(), getScore());
    }

}
