package pn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.models.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
