package pn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pn.domain.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByFirstNameEndingWith(String end);

    @Query(value =
            "select concat(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) " +
                    "from Author as a " +
                    "join a.books as b " +
                    "group by a.id " +
                    "order by sum(b.copies) desc ")
    List<Object> findAllByBooksCopiesDesc();

    @Procedure(name = "usp_get_book_count_by_author")
    int getCountOfBooksWritten(@Param(value = "first_name") String first_name, @Param(value = "last_name") String last_name);
}
