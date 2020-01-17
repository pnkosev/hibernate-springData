package entities.shampoo.concrete_shampoos;

import entities.enums.Size;
import entities.labels.BasicLabel;
import entities.shampoo.BasicShampoo;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    private static final String BRAND = "Fresh Nuke";
    private static final BigDecimal PRICE = new BigDecimal("9.33");
    private static final Size SIZE = Size.LARGE;

    public FreshNuke() {
    }

    public FreshNuke(BasicLabel basicLabel) {
        super(BRAND, PRICE, SIZE, basicLabel);
    }
}