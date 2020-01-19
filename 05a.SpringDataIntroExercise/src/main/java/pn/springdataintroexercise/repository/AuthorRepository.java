package pn.springdataintroexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pn.springdataintroexercise.domain.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "select a.firstName as firstName, a.lastName as lastName " +
            "from Author as a " +
            "inner join Book as b " +
            "on a.id = b.author.id " +
            "and b.releaseDate < :date")
    List<Map<String, String>> findAuthorsWithBooksReleasedBeforeDate(@Param("date") Date date);

    @Query(value =
            "SELECT\n" +
                    "  authors.id,\n" +
                    "  authors.first_name,\n" +
                    "  authors.last_name,\n" +
                    "  authors.count\n" +
                    "FROM (SELECT\n" +
                    "        a.id,\n" +
                    "        a.first_name,\n" +
                    "        a.last_name,\n" +
                    "        count(b.id) AS count\n" +
                    "      FROM authors AS a\n" +
                    "        LEFT JOIN books AS b\n" +
                    "          ON a.id = b.author_id\n" +
                    "      GROUP BY a.id, a.first_name, a.last_name\n" +
                    "     ) AS authors\n" +
                    "ORDER BY authors.count DESC;", nativeQuery = true)
    List<Author> findAuthorsByOrderByBooksDesc();
}
