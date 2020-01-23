package pn.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.entities.Author;
import pn.repository.AuthorRepository;
import pn.util.FileUtil;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public List<String> getAllEndingWith(String end) {
        return this.authorRepository
                .findAllByFirstNameEndingWith(end)
                .stream()
                .map(a -> String.format("%s %s",
                        a.getFirstName(),
                        a.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBookCopiesCount() {
        return this.authorRepository
                .findAllByBooksCopiesDesc()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountOfBooksWritten(String firstName, String lastName) {
        return this.authorRepository.getCountOfBooksWritten(firstName, lastName);
    }
}
