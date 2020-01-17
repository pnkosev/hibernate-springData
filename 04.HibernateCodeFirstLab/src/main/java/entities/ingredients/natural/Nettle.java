package entities.ingredients.natural;

import entities.ingredients.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "NT")
public class Nettle extends BasicIngredient {
    private static final String NAME = "NETTLE";
    private static final BigDecimal PRICE = new BigDecimal("3.57");

    public Nettle() {
        super(NAME, PRICE);
    }
}
