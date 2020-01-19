package pn.springdataintroexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pn.springdataintroexercise.domain.entities.Author;
import pn.springdataintroexercise.repository.AuthorRepository;
import pn.springdataintroexercise.utils.FileUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final String AUTHOR_TXT_PATH = "src\\main\\resources\\files\\authors.txt";

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] lines = this.fileUtil.readFile(AUTHOR_TXT_PATH);

        for (String line : lines) {
            String[] input = line.split("\\s+");
            String firstName = input[0];
            String lastName = input[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            this.authorRepository.save(author);
        }
    }

    @Override
    public List<String> getAllWithBooksReleasedBeforeDate(Date date) {
        return this.authorRepository.findAuthorsWithBooksReleasedBeforeDate(date)
                .stream()
                .map(a -> String.format("%s %s",
                        a.get("firstName"),
                        a.get("lastName")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllByBookCountDesc() {
        return this.authorRepository.findAuthorsByOrderByBooksDesc()
                .stream()
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());

        // Solution with local sort of all authors
/*        return this.authorRepository
                .findAll()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());*/
    }
}
