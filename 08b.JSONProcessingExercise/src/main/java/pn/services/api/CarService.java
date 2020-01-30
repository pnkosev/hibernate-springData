package pn.services.api;

import pn.models.dtos.views.CarViewDTO;
import pn.models.entities.Car;

import java.util.List;

public interface CarService {
    void seedMultipleCars(String path);

    List<Car> getALLCars();

    List<CarViewDTO> getCarsByMake(String make);
}
