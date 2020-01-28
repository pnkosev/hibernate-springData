package pn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.domain.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users ORDER BY RAND () LIMIT 1", nativeQuery = true)
    User getRandomEntity();

    @Query(
            value = "select u " +
                    "from User as u " +
                    "join u.productsSold as p " +
                    "where p.buyer is not null " +
                    "group by u.id " +
                    "order by u.lastName, u.firstName"
    )
    List<User> findAllByProductsSold();
}
