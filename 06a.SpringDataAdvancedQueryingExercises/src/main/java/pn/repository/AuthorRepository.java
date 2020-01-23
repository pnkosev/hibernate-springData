package pn.repository;

import pn.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
  List<Author> findAllByFirstNameEndingWith(String end);

  
}
