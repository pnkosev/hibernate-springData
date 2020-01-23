package pn.advancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pn.advancedquerying.domain.entities.Label;
import pn.advancedquerying.domain.entities.Shampoo;
import pn.advancedquerying.domain.entities.Size;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    long countAllByPriceLessThan(BigDecimal price);

    // findAllByIngredientsNameIn
//    @Query(value = "select s from Shampoo as s " +
//            "join s.ingredients as i where i.name in :ingredients")
//    List<Shampoo> findAllByIngredientsNameIn(@Param(value = "ingredients") Collection<String> ingredients);
    List<Shampoo> findAllByIngredientsNameIn(Collection<String> ingredients);

    @Query(value = "select s from Shampoo as s where s.ingredients.size < :numberOfIngredients")
    List<Shampoo> findAllByIngredientsLessThan(@Param(value = "numberOfIngredients") int numberOfIngredients);

    List<Shampoo> findAllByIngredientsLessThanNamedQuery(@Param(value = "numberOfIngredients") int numberOfIngredients);

    @Query(value = "select sum(i.price) from Shampoo as s join s.ingredients as i where s.brand = :brand")
    BigDecimal getTotalPriceOfIngredients(@Param(value = "brand") String brand);

    BigDecimal getTotalPriceOfIngredientsNamedQuery(@Param(value = "brand") String brand);
}
