package pn.services.impl;

import org.springframework.stereotype.Service;
import pn.models.entities.Supplier;
import pn.repositories.SupplierRepository;
import pn.services.api.SupplierService;
import pn.utils.api.Parser;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final Parser parser;
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(Parser parser, SupplierRepository supplierRepository) {
        this.parser = parser;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedMultipleSuppliersFromJSON(String path) {
        if (this.supplierRepository.count() == 0) {
            Supplier[] suppliers = this.parser.objectFromJSON(path, Supplier[].class);

            System.out.println();
        }
    }
}
