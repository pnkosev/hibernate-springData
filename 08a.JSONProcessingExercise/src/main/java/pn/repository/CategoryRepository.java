package pn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.domain.dto.view.CategoryProductCountDTO;
import pn.domain.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from categories order by rand() limit 1", nativeQuery = true)
    Category getRandomCategory();

    @Query(
            value = "select new pn.domain.dto.view.CategoryProductCountDTO( " +
                    "c.name, c.products.size, avg(p.price), sum(p.price)) " +
                    "from Category as c join c.products as p group by c.id order by c.products.size desc"
    )
    List<CategoryProductCountDTO> getAllOrderedByProductCount();
}
