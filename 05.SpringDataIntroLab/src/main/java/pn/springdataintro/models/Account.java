package pn.springdataintro.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    private long id;
    private BigDecimal balance;
    private User user;

    public Account() {
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "balance")
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        if (BigDecimal.ZERO.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.balance = balance;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
