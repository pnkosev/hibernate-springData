package pn.springdataintroexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.springdataintroexercise.domain.entities.Author;
import pn.springdataintroexercise.service.AuthorService;
import pn.springdataintroexercise.service.BookService;
import pn.springdataintroexercise.service.CategoryService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookShopController implements CommandLineRunner {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookShopController(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

//        this.bookService
//                .getAllByGivenAuthorOrdered("Roger","Porter")
//                .forEach(System.out::println);
//
//        this.bookService
//                .getAllWithReleaseDateBefore(DATE_FORMAT.parse("31/12/2000"))
//                .forEach(System.out::println);
//
//        this.bookService
//                .getAllWithReleaseDateAfter(DATE_FORMAT.parse("31/12/2000"))
//                .forEach(System.out::println);

//        this.authorService
//                .getAllWithBooksReleasedBeforeDate(DATE_FORMAT.parse("31/12/1988"))
//                .forEach(System.out::println);

        this.authorService.getAllByBookCountDesc()
                .forEach(System.out::println);

        System.out.println("yoyo");
    }
}
