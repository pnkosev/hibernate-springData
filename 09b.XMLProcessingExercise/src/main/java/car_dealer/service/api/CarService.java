package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.CarFromMakeRootDTO;
import car_dealer.domain.dto.exportDTO.CarWithPartsRootDTO;

public interface CarService {
    void seedMultipleCarsFromXML();

    CarFromMakeRootDTO getCarsByMake(String make);

    void exportCarsByMake(String make);

    CarWithPartsRootDTO getAllCarsWithTheirParts();

    void exportCarsWithTheirParts();
}
