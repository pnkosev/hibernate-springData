package product_shop.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    private String firstName;
    private String lastName;
    private int age;
    private Set<Product> productsSold;
    private Set<Product> productsBought;
    private Set<User> friends;

    public User() {
        this.productsSold = new HashSet<>();
        this.productsBought = new HashSet<>();
        this.friends = new HashSet<>();
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "seller", targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Product> getProductsSold() {
        return this.productsSold;
    }

    public void setProductsSold(Set<Product> productsSold) {
        this.productsSold = productsSold;
    }

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Product> getProductsBought() {
        return this.productsBought;
    }

    public void setProductsBought(Set<Product> productsBought) {
        this.productsBought = productsBought;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User other = (User) o;

        return this.getId() == other.getId();
    }

    @Transient
    public String getFullName() {
        if (this.firstName != null) {
            return String.format("%s %s", this.firstName, this.lastName);
        }

        return this.lastName;
    }
}
