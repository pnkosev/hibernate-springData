package entities.ingredients;

import entities.shampoo.BasicShampoo;
import interfaces.Ingredient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type",
        discriminatorType = DiscriminatorType.STRING)
public class BasicIngredient implements Ingredient {

    private int id;
    private String name;
    private BigDecimal price;
    private List<BasicShampoo> shampoos;

    protected BasicIngredient() {
    }

    protected BasicIngredient(String name, BigDecimal price) {
        this.setName(name);
        this.setPrice(price);
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    @Override
    public List<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(List<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
