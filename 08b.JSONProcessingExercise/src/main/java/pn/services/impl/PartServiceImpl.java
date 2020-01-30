package pn.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.dtos.PartDTO;
import pn.models.entities.Part;
import pn.models.entities.Supplier;
import pn.repositories.PartRepository;
import pn.services.api.PartService;
import pn.services.api.SupplierService;
import pn.utils.api.Parser;
import pn.utils.api.ValidatorUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final Parser parser;
    private final ValidatorUtils validator;
    private final ModelMapper mapper;

    private final PartRepository partRepository;
    private final SupplierService supplierService;

    @Autowired
    public PartServiceImpl(Parser parser, ValidatorUtils validator, ModelMapper mapper, PartRepository partRepository, SupplierService supplierService) {
        this.parser = parser;
        this.validator = validator;
        this.mapper = mapper;
        this.partRepository = partRepository;
        this.supplierService = supplierService;
    }

    @Override
    public void seedMultipleParts(String path) {
        if (this.partRepository.count() == 0) {
            PartDTO[] partDTOS = this.parser.objectFromJSON(path, PartDTO[].class);

            for (PartDTO partDTO : partDTOS) {
                if (!validator.isValid(partDTO)) {
                    validator.getViolations(partDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Part part = mapper.map(partDTO, Part.class);

                Supplier randomSupplier = this.getRandomSupplier();
                randomSupplier.getParts().add(part);

                part.setSupplier(randomSupplier);

                this.partRepository.save(part);
            }
        }
    }

    private Supplier getRandomSupplier() {
        return this.supplierService.getRandomSupplier();
    }
}
