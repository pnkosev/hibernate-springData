package car_dealer.service.impl;

import car_dealer.repository.SupplierRepository;
import car_dealer.service.api.SupplierService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final XMLParser xmlParser;
    private final ValidatorUtil validator;
    private final ModelMapper mapper;

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(XMLParser xmlParser, ValidatorUtil validator, ModelMapper mapper, SupplierRepository supplierRepository) {
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.mapper = mapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedMultipleSuppliersFromJSON(String path) {

    }
}
