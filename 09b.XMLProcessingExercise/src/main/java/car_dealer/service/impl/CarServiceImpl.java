package car_dealer.service.impl;

import car_dealer.repository.CarRepository;
import car_dealer.service.api.CarService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CarServiceImpl implements CarService {

    private final XMLParser xmlParser;
    private final ModelMapper mapper;
    private final ValidatorUtil validator;

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(XMLParser xmlParser, ModelMapper mapper, ValidatorUtil validator,  CarRepository carRepository) {
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validator = validator;
        this.carRepository = carRepository;
    }

    @Override
    public void seedMultipleCarsFromXML() {

    }
}
