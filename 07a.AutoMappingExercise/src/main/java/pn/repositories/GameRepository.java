package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
