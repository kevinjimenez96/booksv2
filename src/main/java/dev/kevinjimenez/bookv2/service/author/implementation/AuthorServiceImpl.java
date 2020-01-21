package dev.kevinjimenez.bookv2.service.author.implementation;

import dev.kevinjimenez.bookv2.dao.author.AuthorDao;
import dev.kevinjimenez.bookv2.dto.AuthorDTO;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public Author findById(int id) {
        AuthorDTO authorDTO = authorDao.findById(id);
        return new Author(authorDTO);
    }

    @Override
    public List<Author> find() {
        List<AuthorDTO> authorDTOS = authorDao.find();
        List<Author> authors = new ArrayList<Author>();
        for (AuthorDTO authorDTO: authorDTOS) {
            authors.add(new Author(authorDTO));
        }
        return authors;
    }

    @Override
    public void insert(@NotNull Author author) throws ExistingValueException {
        if(this.authorDao.findById(author.getId()) != null){
            throw new ExistingValueException("The author '" + author.getId() + "' already exists.");
        }
        AuthorDTO authorDTO = new AuthorDTO(author);
        authorDTO.setCreationDate(LocalDate.now());
        authorDao.insert(authorDTO);
    }

    @Override
    public void update(@NotNull Author author) throws ValueNotFoundException {
        if(this.authorDao.findById(author.getId()) == null){
            throw new ValueNotFoundException("The author '" + author.getId() + "' doesn't exists.");
        }
        AuthorDTO authorDTO = new AuthorDTO(author);
        authorDTO.setUpdateDate(LocalDate.now());
        authorDao.update(authorDTO);
    }

    @Override
    public void update(Author author, List<Integer> books) {
        if(this.authorDao.findById(author.getId()) == null){
            throw new ValueNotFoundException("The author '" + author.getId() + "' doesn't exists.");
        }
        author.setBooks(books);
        AuthorDTO authorDTO = new AuthorDTO(author);
        authorDTO.setUpdateDate(LocalDate.now());
        authorDao.update(authorDTO);
    }

    @Override
    public void delete(Author author) {
        if(this.authorDao.findById(author.getId()) == null){
            throw new ValueNotFoundException("The author '" + author.getId() + "' doesn't exists.");
        }
        AuthorDTO authorDTO = new AuthorDTO(author);
        authorDao.delete(authorDTO);
    }

    @Override
    public void delete(int id) {
        if(this.authorDao.findById(id) == null){
            throw new ValueNotFoundException("The author '" + id + "' doesn't exists.");
        }
        AuthorDTO authorDTO = authorDao.findById(id);
        authorDao.delete(authorDTO);
    }


}
