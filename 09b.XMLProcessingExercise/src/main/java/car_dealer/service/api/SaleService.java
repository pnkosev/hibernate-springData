package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.SaleWithDiscountRootDTO;

public interface SaleService {
    void seedMultipleSales();

    SaleWithDiscountRootDTO getAllSalesWithDetails();

    void exportSalesWithDetails();
}
