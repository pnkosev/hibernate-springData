package pn.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "travelled_distance")
    private Long travelledDistance;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Sale> sales;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    private Set<Part> parts;

    public Car() {
        this.parts = new HashSet<>();
    }
}
