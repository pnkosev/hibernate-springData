package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.CarFromMakeRootDTO;

public interface CarService {
    void seedMultipleCarsFromXML();

    CarFromMakeRootDTO getCarsByMake(String make);

    void exportCarsByMake(String make);

//    List<CarPartViewDTO> getAllCarsWithTheirParts();
}
