package pn.advancedquerying.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@NamedQueries({
        @NamedQuery(name = "Shampoo.findAllByIngredientsLessThanNamedQuery",
                query = "select s from Shampoo as s where s.ingredients.size < :numberOfIngredients"),
        @NamedQuery(name = "Shampoo.getTotalPriceOfIngredientsNamedQuery",
                query = "select sum(i.price) from Shampoo as s join s.ingredients as i where s.brand = :brand")
})
public class Shampoo extends BaseEntity {

    private String brand;
    private BigDecimal price;
    private Size size;
    private Label label;
    private Set<Ingredient> ingredients;

    public Shampoo() {
    }

    @Column(name = "brand")
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "size")
    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @ManyToOne(optional = true, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
