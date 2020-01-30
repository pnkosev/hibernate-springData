package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
