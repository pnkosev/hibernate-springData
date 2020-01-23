package pn.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    List<String> getAllEndingWith(String end);

    List<String> getAllBookCopiesCount();

    int getCountOfBooksWritten(String firstName, String lastName);
}
