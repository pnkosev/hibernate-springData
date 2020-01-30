package pn.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pn.models.dtos.bindings.SupplierDTO;
import pn.models.entities.Supplier;
import pn.repositories.SupplierRepository;
import pn.services.api.SupplierService;
import pn.utils.api.Parser;
import pn.utils.api.ValidatorUtils;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final Parser parser;
    private final ValidatorUtils validator;
    private final ModelMapper mapper;

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(Parser parser, ValidatorUtils validator, ModelMapper mapper, SupplierRepository supplierRepository) {
        this.parser = parser;
        this.validator = validator;
        this.mapper = mapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedMultipleSuppliersFromJSON(String path) {
        if (this.supplierRepository.count() == 0) {
            SupplierDTO[] supplierDTOs = this.parser.objectFromJSON(path, SupplierDTO[].class);

            for (SupplierDTO supplierDTO : supplierDTOs) {
                if (!this.validator.isValid(supplierDTO)) {
                    this.validator.getViolations(supplierDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Supplier supplier = this.mapper.map(supplierDTO, Supplier.class);

                this.supplierRepository.save(supplier);
            }
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        return this.supplierRepository.getRandomSupplier();
    }
}
