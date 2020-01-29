package pn.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "travelled_distance")
    private int travelledDistance;

    @OneToMany(mappedBy = "car")
    private List<Sale> sales;

    @ManyToMany(mappedBy = "cars")
    private List<Part> parts;
}
