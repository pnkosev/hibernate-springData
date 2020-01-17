package entities.ingredients.natural;

import entities.ingredients.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient {
    private static final String NAME = "STRAWBERRY";
    private static final BigDecimal PRICE = new BigDecimal("4.85");

    public Strawberry() {
        super(NAME, PRICE);
    }
}
