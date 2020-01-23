package pn.advancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pn.advancedquerying.domain.entities.Ingredient;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPriceAsc(Collection<String> list);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient AS b WHERE b.name = :name")
    void deleteIngredientByName(@Param("name") String ingredientName);

    @Modifying
    @Transactional
    void deleteIngredientByNameNamedQuery(@Param("name") String ingredientName);

    @Modifying
    @Transactional
    @Query(value = "update Ingredient set price = price * 1.10")
    void updateAllPricesByTenPercent();

    @Modifying
    @Transactional
    void updateAllPricesByTenPercentNamedQuery();

    @Modifying
    @Transactional
    @Query(value = "update Ingredient set price = price * 1.10 where name in (:ingredients)")
    void updatePriceOfIngredientsInGivenList(@Param(value = "ingredients") Collection<String> ingredients);

    @Modifying
    @Transactional
    void updatePriceOfIngredientsInGivenListNamedQuery(@Param(value = "ingredients") Collection<String> ingredients);
}
