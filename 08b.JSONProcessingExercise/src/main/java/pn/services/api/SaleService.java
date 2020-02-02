package pn.services.api;

import pn.models.dtos.views.SaleDetailedViewDTO;

import java.util.List;

public interface SaleService {
    void seedMultipleSales();

    List<SaleDetailedViewDTO> getAllSalesWithDetails();
}
