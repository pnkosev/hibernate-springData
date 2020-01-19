package pn.springdataintroexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.springdataintroexercise.domain.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
