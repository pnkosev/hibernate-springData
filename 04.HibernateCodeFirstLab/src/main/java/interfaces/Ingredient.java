package interfaces;

import entities.shampoo.BasicShampoo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface Ingredient extends Serializable {
    String getName();
    void setName(String name);
    int getId();
    void setId(int id);
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
    List<BasicShampoo> getShampoos();
    void setShampoos(List<BasicShampoo> shampoos);
}
