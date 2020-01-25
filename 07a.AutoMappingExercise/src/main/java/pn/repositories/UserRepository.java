package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
