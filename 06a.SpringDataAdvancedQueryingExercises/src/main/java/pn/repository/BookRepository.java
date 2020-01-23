package pn.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pn.domain.entities.AgeRestriction;
import pn.domain.entities.Book;
import pn.domain.entities.EditionType;
import pn.domain.dto.ReducedBook;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestrictionIs(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceBetweenOrderByPriceDesc(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    @Query(value = "select b from Book as b where function('year', b.releaseDate) <> :year")
    List<Book> findAllNotReleasedInGivenYear(int year);

    @Query(value = "select b from Book as b where b.author.firstName like %:end")
    List<Book> findAllByAuthorFirstNameEndingWith(String end);

    List<Book> findAllByTitleContaining(String str);

    @Query(value = "select b from Book as b where b.author.lastName like :start%")
    List<Book> findAllByAuthorLastNameStartsWith(String start);

    @Query(value = "select count(b) from Book as b where length(b.title) > :length")
    int countAllWithTitleLengthGreaterThan(int length);

    ReducedBook findBookByTitle(String title);

    @Modifying
    @Query(value = "update Book as b set b.copies = b.copies + :qty where b.releaseDate > :date")
    int increaseCopiesOfBooksReleasedAfterDate(@Param(value = "qty") int qty, @Param(value = "date") LocalDate date);

    @Modifying
    @Query(value = "delete from Book as b where b.copies < :bound")
    int deleteAllByCopiesLessThanCustom(@Param(value = "bound") int bound);
}
