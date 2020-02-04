package car_dealer.service.impl;

import car_dealer.repository.PartRepository;
import car_dealer.service.api.PartService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final XMLParser xmlParser;
    private final ValidatorUtil validator;
    private final ModelMapper mapper;

    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(XMLParser xmlParser, ValidatorUtil validator, ModelMapper mapper, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.mapper = mapper;
        this.partRepository = partRepository;
    }

    @Override
    public void seedMultipleParts(String path) {

    }
}
