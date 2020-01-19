package pn.springdataintroexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pn.springdataintroexercise.domain.entities.*;
import pn.springdataintroexercise.repository.AuthorRepository;
import pn.springdataintroexercise.repository.BookRepository;
import pn.springdataintroexercise.repository.CategoryRepository;
import pn.springdataintroexercise.utils.FileUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final String BOOK_TXT_PATH = "C:\\Users\\user\\Desktop\\Projects\\Java\\Hibernate\\05a.SpringDataIntroExercise\\src\\main\\resources\\files\\books.txt";

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedBooks() throws IOException, ParseException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] lines = this.fileUtil.readFile(BOOK_TXT_PATH);

        for (String line : lines) {
            String[] input = line.split("\\s+");

            Book book = new Book();

            Author randomAuthor = this.getRandomAuthor();
            book.setAuthor(randomAuthor);

            EditionType editionType = EditionType.values()[Integer.parseInt(input[0])];
            book.setEditionType(editionType);

//            LocalDate releaseDate = LocalDate.parse(
//                    input[1],
//                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            Date releaseDate = new SimpleDateFormat("d/M/yyyy").parse(input[1]);
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(input[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(input[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(input[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < input.length; i++) {
                titleBuilder.append(input[i]).append(" ");
            }
            book.setTitle(titleBuilder.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomIndex = random.nextInt((int) (this.authorRepository.count() - 1) + 1);

        return this.authorRepository.findById((long) randomIndex).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        Random random = new Random();

        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();
            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1) + 1);

        return this.categoryRepository.findById((long) randomId).orElse(null);
    }
}
