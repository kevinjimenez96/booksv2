package dev.kevinjimenez.bookv2.service;

import dev.kevinjimenez.bookv2.dao.author.AuthorDao;
import dev.kevinjimenez.bookv2.dto.AuthorDTO;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.ValueNotFoundException;
import dev.kevinjimenez.bookv2.model.Author;
import dev.kevinjimenez.bookv2.service.author.AuthorService;
import dev.kevinjimenez.bookv2.service.author.implementation.AuthorServiceImpl;
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
public class AuthorServiceTest {

    @Mock
    AuthorDao authorDao;

    @InjectMocks
    AuthorService authorService = new AuthorServiceImpl();

    @Test
    public void test_FindAuthorsReturnAllAuthors(){
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorDTOS.add(new AuthorDTO(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null, null, null));
        authorDTOS.add(new AuthorDTO(2 , "Kav", LocalDate.of(2019, 1, 21),
                null, 0.0, null, null, null));

        Mockito.when(authorDao.find()).thenReturn(authorDTOS);

        List<Author> authors = authorService.find();

        Assert.assertTrue(authors.contains(new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null)));
        Assert.assertTrue(authors.contains(new Author(2 , "Kav", LocalDate.of(2019, 1, 21),
                null, 0.0, null)));

    }

    @Test
    public void test_FindAuthorReturnUser(){
        AuthorDTO authorDTO = new AuthorDTO(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null, null, null);

        Mockito.when(authorDao.findById(1)).thenReturn(authorDTO);

        Author author = authorService.findById(1);

        Assert.assertEquals(author.getId(), 1);
    }

    @Test
    public void test_InsertAuthorSuccess(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(null);

        try{
            authorService.insert(author);
            Mockito.verify(authorDao, Mockito.times(1)).insert(Mockito.any(AuthorDTO.class));
        } catch (ExistingValueException e){
            Assert.fail();
        }
    }

    @Test
    public void test_InsertExistingAuthorFails(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        AuthorDTO authorDTO = new AuthorDTO(author);

        Mockito.when(authorDao.findById(1)).thenReturn(authorDTO);

        try{
            authorService.insert(author);
            Assert.fail();
        } catch (ExistingValueException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(authorDao, Mockito.times(0)).insert(Mockito.any(AuthorDTO.class));
    }

    @Test
    public void test_UpdateAuthorSuccess(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(new AuthorDTO(author));

        try{
            authorService.update(author);
            Mockito.verify(authorDao, Mockito.times(1)).update(Mockito.any(AuthorDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_UpdateNonExistingAuthorFails(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(null);

        try{
            authorService.update(author);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(authorDao, Mockito.times(0)).update(Mockito.any(AuthorDTO.class));
    }

    @Test
    public void test_UpdateAuthorBooksSuccess(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(new AuthorDTO(author));

        try{
            authorService.update(author, null);
            Mockito.verify(authorDao, Mockito.times(1)).update(Mockito.any(AuthorDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_UpdateNonExistingAuthorBooksFails(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(null);

        try{
            authorService.update(author, null);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(authorDao, Mockito.times(0)).update(Mockito.any(AuthorDTO.class));
    }

    @Test
    public void test_DeleteAuthorSuccess(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(new AuthorDTO(author));

        try{
            authorService.delete(author);
            Mockito.verify(authorDao, Mockito.times(1)).delete(Mockito.any(AuthorDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteNonExistingAuthorFails(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(null);

        try{
            authorService.delete(author);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(authorDao, Mockito.times(0)).delete(Mockito.any(AuthorDTO.class));
    }

    @Test
    public void test_DeleteAuthorWithIdSuccess(){
        Author author = new Author(1 , "Kev", LocalDate.of(2020, 1, 21),
                null, 0.0, null);

        Mockito.when(authorDao.findById(1)).thenReturn(new AuthorDTO(author));

        try{
            authorService.delete(1);
            Mockito.verify(authorDao, Mockito.times(1)).delete(Mockito.any(AuthorDTO.class));
        } catch (ValueNotFoundException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteNonExistingAuthorWithIdFails(){
        Mockito.when(authorDao.findById(1)).thenReturn(null);

        try{
            authorService.delete(1);
            Assert.fail();
        } catch (ValueNotFoundException e){
            Assert.assertTrue(true);
        }
        Mockito.verify(authorDao, Mockito.times(0)).delete(Mockito.any(AuthorDTO.class));
    }
}
