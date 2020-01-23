package pn.advancedquerying.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.advancedquerying.domain.entities.Ingredient;
import pn.advancedquerying.repositories.IngredientRepository;
import pn.advancedquerying.services.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllStartingWith(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllContainedInList(List<String> list) {
        return this.ingredientRepository.findAllByNameInOrderByPriceAsc(list)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void increasePricesByTenPercent() {
        this.ingredientRepository.updateAllPricesByTenPercent();
    }

    @Override
    public void increasePricesByTenPercentOfGivenIngredients(List<String> ingredients) {
        this.ingredientRepository.updatePriceOfIngredientsInGivenList(ingredients);
    }
}
