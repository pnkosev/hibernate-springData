package pn.service;

import pn.domain.dto.ReducedBook;
import pn.domain.entities.AgeRestriction;
import pn.domain.entities.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> getAllGoldenWithLessThan5000Copies();

    List<String> getAllWithPriceBetween5And40Desc();

    List<String> getAllWithPriceLessThan5AndGreaterThan40();

    List<String> getAllNotReleasedInGivenYear(int year);

    List<String> getAllBeforeGivenDate(LocalDate date);

    List<String> getAllByAuthorFirstNameEndingWith(String end);

    List<String> getAllWithTitleContaining(String str);

    List<String> getAllWithAuthorLastNameStartingWith(String start);

    int getCountOfAllWithGivenTitleLength(int length);

    String getReducedBookByTitle(String title);

    int increaseNumberOfBooksWithReleasedDateAfter(int qty, LocalDate date);

    int deleteAllWithCopiesCountLowerThan(int bound);
}
