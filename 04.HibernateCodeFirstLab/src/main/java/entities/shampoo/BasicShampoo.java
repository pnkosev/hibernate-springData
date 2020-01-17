package entities.shampoo;

import entities.enums.Size;
import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;
import interfaces.Shampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {
    private long id;
    private String brand;
    private BigDecimal price;
    private Size size;
    private BasicLabel label;
    private Set<BasicIngredient> basicIngredients;


    public BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel basicLabel) {
        this.setPrice(price);
        this.setBrand(brand);
        this.setSize(size);
        this.setLabel(basicLabel);
        this.basicIngredients = new HashSet<>();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "brand")
    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
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

    @Column(name = "size")
    @Enumerated
    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    @Override
    public BasicLabel getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    @Override
    public Set<BasicIngredient> getIngredients() {
        return this.basicIngredients;
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.basicIngredients = ingredients;
    }
}
