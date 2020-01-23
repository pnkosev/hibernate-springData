package pn.controller;

import pn.domain.entities.AgeRestriction;
import pn.domain.entities.EditionType;
import pn.service.AuthorService;
import pn.service.BookService;
import pn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BookshopController implements CommandLineRunner {

  private final AuthorService authorService;
  private final CategoryService categoryService;
  private final BookService bookService;

  @Autowired
  public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
    this.authorService = authorService;
    this.categoryService = categoryService;
    this.bookService = bookService;
  }

  @Override
  public void run(String... strings) throws Exception {
    this.authorService.seedAuthors();
    this.categoryService.seedCategories();
    this.bookService.seedBooks();


    // 1. Books Titles by Age Restriction
//        this.bookService
//                .getAllByAgeRestriction(AgeRestriction.valueOf("TEEN"))
//                .forEach(System.out::println);

    // 2. Golden Books
//        this.bookService
//                .getAllGoldenWithLessThan5000Copies()
//                .forEach(System.out::println);

    // 3. Books by Price
//        this.bookService
//                .getAllWithPriceLessThan5AndGreaterThan40()
//                .forEach(System.out::println);

    // 4. Not Released Books
//        this.bookService
//                .getAllNotReleasedInGivenYear(1998)
//                .forEach(System.out::println);

    // 5. Books Released Before Date
//        this.bookService
//                .getAllBeforeGivenDate(LocalDate.parse("12/04/1992", DateTimeFormatter.ofPattern("d/M/yyyy")))
//                .forEach(System.out::println);

    // 6. Authors Search
//        this.authorService
//                .getAllEndingWith("dy")
//                .forEach(System.out::println);

    // 6. Book search by author ending with
//        this.bookService
//                .getAllByAuthorFirstNameEndingWith("dy")
//                .forEach(System.out::println);

    // 7. Books Search
//        this.bookService
//                .getAllWithTitleContaining("WOR")
//                .forEach(System.out::println);

    // 8. Book Titles Search
//        this.bookService
//                .getAllWithAuthorLastNameStartingWith("Ric")
//                .forEach(System.out::println);

    // 9. Count Books
//        System.out.println(this.bookService
//                .getCountOfAllWithGivenTitleLength(40));

    // 10. Total Book Copies

  }
}
