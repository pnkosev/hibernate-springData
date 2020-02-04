package car_dealer.repository;

import car_dealer.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    @Query(value = "select c from Car as c")
    List<Car> findAllCarsWithTheirParts();
}
