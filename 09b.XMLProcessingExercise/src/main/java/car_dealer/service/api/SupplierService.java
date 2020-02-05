package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.SupplierLocalRootDTO;

public interface SupplierService {
    void seedMultipleSuppliersFromXML();

    SupplierLocalRootDTO getAllLocalSuppliers();

    void exportLocalSuppliers();
}
