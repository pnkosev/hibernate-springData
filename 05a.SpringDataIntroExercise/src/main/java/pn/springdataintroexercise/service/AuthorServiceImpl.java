package pn.springdataintroexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pn.springdataintroexercise.domain.entities.Author;
import pn.springdataintroexercise.repository.AuthorRepository;
import pn.springdataintroexercise.utils.FileUtil;

import java.io.IOException;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final String AUTHOR_TXT_PATH = "C:\\Users\\user\\Desktop\\Projects\\Java\\Hibernate\\05a.SpringDataIntroExercise\\src\\main\\resources\\files\\authors.txt";

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

        for (String line: lines) {
            String[] input = line.split("\\s+");
            String firstName = input[0];
            String lastName = input[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            this.authorRepository.save(author);
        }
    }
}
