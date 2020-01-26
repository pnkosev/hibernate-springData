package pn.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, unique = true)
    @Pattern(regexp = "[A-Z]+.*", message = "Title must start with capitol letter!")
    @Length(min = 3, max = 100, message = "Title must be between 3 and 100 symbols!")
    private String title;

    @Column(name = "trailer")
    @Pattern(regexp = "^[A-z0-9]{11}$", message = "Must be 11 characters!")
    private String trailer;

    @Column(name = "image_url")
    @Pattern(regexp = "^(https)?(http)?://.*$", message = "Must start with http or https")
    private String imageURL;

    @Column(name = "size", precision = 5, scale = 1, nullable = false)
    @Min(0)
    private Double size;

    @Column(name = "price", precision = 19, scale = 2, nullable = false)
    @Min(0)
    private BigDecimal price;

    @Column(name = "description")
    @Length(min = 20, message = "Description must be at least 20 symbols!")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "games_orders",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private Set<Order> orders;

    public Game() {
        this.users = new HashSet<>();
        this.orders = new HashSet<>();
    }
}
