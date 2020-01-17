package entities.ingredients.natural;

import entities.ingredients.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient {
    private static final String NAME = "LAVENDER";
    private static final BigDecimal PRICE = new BigDecimal("2.99");

    public Lavender() {
        super(NAME, PRICE);
    }
}
