package car_dealer.service.impl;

import car_dealer.domain.dto.exportDTO.SupplierLocalDTO;
import car_dealer.domain.dto.exportDTO.SupplierLocalRootDTO;
import car_dealer.domain.dto.importDTO.SupplierDTO;
import car_dealer.domain.dto.importDTO.SupplierRootDTO;
import car_dealer.domain.entity.Supplier;
import car_dealer.repository.SupplierRepository;
import car_dealer.service.api.SupplierService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final String SUPPLIERS_XML_INPUT_PATH = "src/main/resources/xml/input/suppliers.xml";
    private static final String LOCAL_SUPPLIERS_XML_EXPORT_PATH = "src/main/resources/xml/output/local-suppliers.xml";

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
    public void seedMultipleSuppliersFromXML() {
        if (this.supplierRepository.count() == 0) {
            SupplierRootDTO suppliers = this.xmlParser.fromXML(SupplierRootDTO.class, SUPPLIERS_XML_INPUT_PATH);

            for (SupplierDTO supplierDTO : suppliers.getSuppliers()) {
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
    public SupplierLocalRootDTO getAllLocalSuppliers() {
        SupplierLocalRootDTO supplierLocalRootDTO = new SupplierLocalRootDTO();

        List<SupplierLocalDTO> localSuppliers = this.supplierRepository.getAllLocalSuppliers()
                .stream()
                .map(s -> this.mapper.map(s, SupplierLocalDTO.class))
                .collect(Collectors.toList());

        supplierLocalRootDTO.setSuppliers(localSuppliers);

        return supplierLocalRootDTO;
    }

    @Override
    public void exportLocalSuppliers() {
        this.xmlParser.toXML(getAllLocalSuppliers(), LOCAL_SUPPLIERS_XML_EXPORT_PATH);
    }
}
