package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
