package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.models.entities.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query(
            value = "select s from Sale as s " +
                    "join s.car as ca " +
                    "join s.customer as cu"
    )
    List<Sale> getAllSalesWithDetails();
}
