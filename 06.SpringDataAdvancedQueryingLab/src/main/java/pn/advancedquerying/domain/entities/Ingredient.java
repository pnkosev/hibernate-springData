package pn.advancedquerying.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@NamedQueries({
        @NamedQuery(name = "Ingredient.deleteIngredientByNameNamedQuery",
                query = "DELETE FROM Ingredient AS b WHERE b.name = :name"),
        @NamedQuery(name = "Ingredient.updateAllPricesByTenPercentNamedQuery",
                query = "update Ingredient set price = price * 1.10"),
        @NamedQuery(name = "Ingredient.updatePriceOfIngredientsInGivenListNamedQuery",
                query = "update Ingredient set price = price * 1.10 where name in (:ingredients)")
})
public class Ingredient extends BaseEntity {

    private String name;
    private BigDecimal price;
    private Set<Shampoo> shampoos;

    public Ingredient() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(mappedBy = "ingredients",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    public Set<Shampoo> getShampoos() {
        return this.shampoos;
    }

    public void setShampoos(Set<Shampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
