package pn.springdataintroexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.springdataintroexercise.domain.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
