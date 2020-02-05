package car_dealer.service.impl;

import car_dealer.domain.dto.exportDTO.CarFromMakeDTO;
import car_dealer.domain.dto.exportDTO.CarFromMakeRootDTO;
import car_dealer.domain.dto.importDTO.CarDTO;
import car_dealer.domain.dto.importDTO.CarRootDTO;
import car_dealer.domain.entity.Car;
import car_dealer.domain.entity.Part;
import car_dealer.repository.CarRepository;
import car_dealer.repository.PartRepository;
import car_dealer.service.api.CarService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_XML_INPUT_PATH = "src/main/resources/xml/input/cars.xml";
    private static final String CARS_BY_MAKE_XML_EXPORT_PATH = "src/main/resources/xml/output/cars-by-make.xml";

    private final XMLParser xmlParser;
    private final ModelMapper mapper;
    private final ValidatorUtil validator;

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(XMLParser xmlParser, ModelMapper mapper, ValidatorUtil validator, CarRepository carRepository, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validator = validator;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void seedMultipleCarsFromXML() {
        if (this.carRepository.count() == 0) {
            CarRootDTO carRootDTO = this.xmlParser.fromXML(CarRootDTO.class, CARS_XML_INPUT_PATH);

            List<Part> allParts = this.partRepository.findAll();
            Random random = new Random();

            for (CarDTO carDTO : carRootDTO.getCars()) {
                if (!this.validator.isValid(carDTO)) {
                    this.validator.getViolations(carDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Car car = this.mapper.map(carDTO, Car.class);
                Set<Part> carParts = car.getParts();

                do {
                    carParts.add(allParts.get(random.nextInt(allParts.size())));

                    if (random.nextInt(5) == 0 && carParts.size() >= 10) {
                        break;
                    }

                } while (carParts.size() <= 20);

                this.carRepository.save(car);
            }
        }
    }

    @Override
    public CarFromMakeRootDTO getCarsByMake(String make) {
        CarFromMakeRootDTO carFromMakeRootDTO = new CarFromMakeRootDTO();

        List<CarFromMakeDTO> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(c -> this.mapper.map(c, CarFromMakeDTO.class))
                .collect(Collectors.toList());

        carFromMakeRootDTO.setCars(cars);

        return carFromMakeRootDTO;
    }

    @Override
    public void exportCarsByMake(String make) {
        this.xmlParser.toXML(getCarsByMake(make), CARS_BY_MAKE_XML_EXPORT_PATH);
    }
}
