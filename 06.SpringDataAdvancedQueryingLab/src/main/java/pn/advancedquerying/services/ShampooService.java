package pn.advancedquerying.services;

import pn.advancedquerying.domain.entities.Label;
import pn.advancedquerying.domain.entities.Shampoo;
import pn.advancedquerying.domain.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<String> getAllBySize(Size size);

    List<String> getAllBySizeOrLabel(Size size, Label label);

    List<String> getAllWithGreaterPriceDescByPrice(String price);

    long countAllWithPriceLessThan(String price);

    List<String> getAllWithIngredients(List<String> ingredients);

    List<String> getAllWithIngredientsLessThan(int numberOfIngredients);

    BigDecimal getPriceOfIngredientsOfShampoo(String brand);
}
