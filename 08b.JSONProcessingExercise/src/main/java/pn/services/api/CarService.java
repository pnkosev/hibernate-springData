package pn.services.api;

import pn.models.entities.Car;

import java.util.List;

public interface CarService {
    void seedMultipleCars(String path);

    List<Car> getALLCars();
}
