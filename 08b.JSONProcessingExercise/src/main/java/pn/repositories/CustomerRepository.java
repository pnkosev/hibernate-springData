package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
