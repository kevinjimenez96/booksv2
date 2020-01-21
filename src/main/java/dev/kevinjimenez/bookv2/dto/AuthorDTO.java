package dev.kevinjimenez.bookv2.dto;

import dev.kevinjimenez.bookv2.model.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AuthorDTO {
    private int id;
    private String name;
    private LocalDate birthday;
    private LocalDate deathDay;
    private double score;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private List<Integer> books;

    public AuthorDTO(int id, String name, LocalDate birthday, LocalDate deathDay, double score, LocalDate creationDate, LocalDate updateDate, List<Integer> books) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.deathDay = deathDay;
        this.score = score;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.books = books;
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthday = author.getBirthday();
        this.deathDay = author.getDeathDay();
        this.score = author.getScore();
        this.books = author.getBooks();
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creation) {
        this.creationDate = creation;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
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
        AuthorDTO authorDTO = (AuthorDTO) o;
        return getId() == authorDTO.getId() &&
                Double.compare(authorDTO.getScore(), getScore()) == 0 &&
                Objects.equals(getName(), authorDTO.getName()) &&
                Objects.equals(getBirthday(), authorDTO.getBirthday()) &&
                Objects.equals(getDeathDay(), authorDTO.getDeathDay()) &&
                Objects.equals(getCreationDate(), authorDTO.getCreationDate()) &&
                Objects.equals(getUpdateDate(), authorDTO.getUpdateDate()) &&
                Objects.equals(getBooks(), authorDTO.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthday(), getDeathDay(), getScore(), getCreationDate(), getUpdateDate(), getBooks());
    }
}
