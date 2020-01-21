package pn.user_system.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    private int id;
    private String name;
    private Set<Town> towns;

    public Country() {
        this.towns = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country",
            targetEntity = Town.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    public Set<Town> getTowns() {
        return this.towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
