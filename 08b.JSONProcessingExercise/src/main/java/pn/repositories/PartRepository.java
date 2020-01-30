package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
}
