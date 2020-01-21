package pn.user_system.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    private int id;
    private String name;
    private Country country;
    private Set<User> bornUsers;
    private Set<User> livingUsers;

    public Town() {
        this.bornUsers = new HashSet<>();
        this.livingUsers = new HashSet<>();
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

    @Column(name = "name", nullable = false)
    @Length(min = 2, max = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Country.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornTown",
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<User> getBornUsers() {
        return this.bornUsers;
    }

    public void setBornUsers(Set<User> bornUsers) {
        this.bornUsers = bornUsers;
    }

    @OneToMany(mappedBy = "currentlyLivingTown",
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<User> getLivingUsers() {
        return this.livingUsers;
    }

    public void setLivingUsers(Set<User> livingUsers) {
        this.livingUsers = livingUsers;
    }
}
