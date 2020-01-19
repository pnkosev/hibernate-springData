package pn.springdataintroexercise.service;

import pn.springdataintroexercise.domain.entities.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException, ParseException;

    Set<String> getAllWithReleaseDateBefore(Date date);

    Set<String> getAllWithReleaseDateAfter(Date date);

    Set<String> getAllByGivenAuthorOrdered(String fistName, String lastName);
}
