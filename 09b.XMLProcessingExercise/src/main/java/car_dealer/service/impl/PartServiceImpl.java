package car_dealer.service.impl;

import car_dealer.domain.dto.importDTO.PartDTO;
import car_dealer.domain.dto.importDTO.PartRootDTO;
import car_dealer.domain.entity.Part;
import car_dealer.domain.entity.Supplier;
import car_dealer.repository.PartRepository;
import car_dealer.repository.SupplierRepository;
import car_dealer.service.api.PartService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {
    private static final String PARTS_XML_INPUT_PATH = "src/main/resources/xml/input/parts.xml";

    private final XMLParser xmlParser;
    private final ValidatorUtil validator;
    private final ModelMapper mapper;

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(XMLParser xmlParser, ValidatorUtil validator, ModelMapper mapper, PartRepository partRepository, SupplierRepository supplierRepository) {
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.mapper = mapper;
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedMultiplePartsFromXML() {
        if (this.partRepository.count() == 0) {
            PartRootDTO partRootDTO = this.xmlParser.fromXML(PartRootDTO.class, PARTS_XML_INPUT_PATH);

            for (PartDTO partDTO : partRootDTO.getParts()) {
                if (!this.validator.isValid(partDTO)) {
                    this.validator.getViolations(partDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Part part = this.mapper.map(partDTO, Part.class);

                Supplier randomSupplier = this.supplierRepository.getRandomSupplier();

                part.setSupplier(randomSupplier);
                randomSupplier.getParts().add(part);

                this.partRepository.save(part);
            }
        }
    }
}
