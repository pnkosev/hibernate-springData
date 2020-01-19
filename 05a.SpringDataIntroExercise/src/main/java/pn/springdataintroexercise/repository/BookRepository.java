package pn.springdataintroexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.springdataintroexercise.domain.entities.Book;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getAllByReleaseDateBefore(Date date);
    List<Book> getAllByReleaseDateAfter(Date date);
    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateAscTitleAsc(String firstName, String lastName);

}
