package pn.springdataintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pn.springdataintro.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
