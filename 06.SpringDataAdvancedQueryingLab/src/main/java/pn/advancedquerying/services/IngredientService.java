package pn.advancedquerying.services;

import java.util.List;

public interface IngredientService {
    List<String> getAllStartingWith(String name);

    List<String> getAllContainedInList(List<String> list);

    void deleteByName(String name);

    void increasePricesByTenPercent();

    void increasePricesByTenPercentOfGivenIngredients(List<String> ingredients);
}
