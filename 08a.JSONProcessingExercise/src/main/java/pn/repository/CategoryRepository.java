package pn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.domain.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from categories order by rand() limit 1", nativeQuery = true)
    Category getRandomCategory();
}
