package entities.shampoo.concrete_shampoos;

import entities.enums.Size;
import entities.labels.BasicLabel;
import entities.shampoo.BasicShampoo;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panther";

    private static final BigDecimal PRICE = new BigDecimal("8.50");

    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther() {
    }

    public PinkPanther(BasicLabel classicLabel) {
        super(BRAND, PRICE, SIZE, classicLabel);
    }
}
