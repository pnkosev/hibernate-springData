package pn.springdataintroexercise.service;

import pn.springdataintroexercise.domain.entities.Author;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    List<String> getAllWithBooksReleasedBeforeDate(Date date);

    List<String> getAllByBookCountDesc();
}
