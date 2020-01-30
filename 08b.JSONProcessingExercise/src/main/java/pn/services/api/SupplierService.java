package pn.services.api;

import pn.models.entities.Supplier;

public interface SupplierService {
    void seedMultipleSuppliersFromJSON(String path);

    Supplier getRandomSupplier();
}
