package entities.ingredients.natural;

import entities.ingredients.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "MT")
public class Mint extends BasicIngredient {
    private static final String NAME = "MINT";
    private static final BigDecimal PRICE = new BigDecimal("1.25");

    public Mint() {
        super(NAME, PRICE);
    }
}
