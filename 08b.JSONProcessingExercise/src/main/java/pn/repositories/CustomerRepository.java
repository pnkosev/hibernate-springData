package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pn.models.entities.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c from Customer as c " +
            "order by c.birthDate, c.isYoungDriver")
    List<Customer> findAllOrderedByBirthDateAscAndYoungDriver();
}
