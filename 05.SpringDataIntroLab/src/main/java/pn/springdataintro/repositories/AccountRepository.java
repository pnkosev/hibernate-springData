package pn.springdataintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pn.springdataintro.models.Account;
import pn.springdataintro.models.User;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account accounts set accounts.balance = :balance where accounts.id =:id")
    void update(@Param("balance") BigDecimal balance, @Param("id") Long id);

    Account getByUser(User user);
}
