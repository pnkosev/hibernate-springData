package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.models.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    @Query(value = "select * from parts order by rand() limit 1", nativeQuery = true)
    Part getRandomPart();
}
