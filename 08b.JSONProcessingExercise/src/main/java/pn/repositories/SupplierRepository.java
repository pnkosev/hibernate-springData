package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.models.dtos.views.SupplierViewDTO;
import pn.models.entities.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value = "select * from suppliers as s order by rand() limit 1", nativeQuery = true)
    Supplier getRandomSupplier();

    @Query(value = "select new pn.models.dtos.views.SupplierViewDTO( " +
            "s.id, s.name, s.parts.size) " +
            "from Supplier as s " +
            "join s.parts as p " +
            "where s.isImporter = false " +
            "group by s.id ")
    List<SupplierViewDTO> getAllNonImportingSuppliers();
}
