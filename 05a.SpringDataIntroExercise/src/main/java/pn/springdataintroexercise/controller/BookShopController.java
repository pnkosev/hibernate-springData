package pn.springdataintroexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.springdataintroexercise.service.AuthorService;
import pn.springdataintroexercise.service.BookService;
import pn.springdataintroexercise.service.CategoryService;

@Controller
public class BookShopController implements CommandLineRunner {
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
        System.out.println("yoyo");
    }
}
