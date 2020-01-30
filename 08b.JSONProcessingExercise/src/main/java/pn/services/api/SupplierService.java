package pn.services.api;

import pn.models.dtos.views.SupplierViewDTO;
import pn.models.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void seedMultipleSuppliersFromJSON(String path);

    Supplier getRandomSupplier();

    List<SupplierViewDTO> getAllNonImportingSuppliers();
}
