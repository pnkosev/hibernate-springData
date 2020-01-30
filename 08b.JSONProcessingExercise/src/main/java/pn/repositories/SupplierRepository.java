package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.models.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value = "select * from suppliers as s order by rand() limit 1", nativeQuery = true)
    Supplier getRandomSupplier();
}
