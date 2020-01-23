package pn.advancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.advancedquerying.domain.entities.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
}
