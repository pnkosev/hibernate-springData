package pn.springdataintroexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.springdataintroexercise.domain.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
