package pn.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.dtos.CarDTO;
import pn.models.entities.Car;
import pn.models.entities.Part;
import pn.repositories.CarRepository;
import pn.services.api.CarService;
import pn.services.api.PartService;
import pn.utils.api.Parser;
import pn.utils.api.RandomUtils;
import pn.utils.api.ValidatorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    private final Parser parser;
    private final ModelMapper mapper;
    private final ValidatorUtils validator;
    private final RandomUtils random;

    private final CarRepository carRepository;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(Parser parser, ModelMapper mapper, ValidatorUtils validator, RandomUtils random, CarRepository carRepository, PartService partService) {
        this.parser = parser;
        this.mapper = mapper;
        this.validator = validator;
        this.random = random;
        this.carRepository = carRepository;
        this.partService = partService;
    }

    @Override
    public void seedMultipleCars(String path) {
        if (this.carRepository.count() == 0) {
            CarDTO[] carDTOs = this.parser.objectFromJSON(path, CarDTO[].class);

            for (CarDTO carDTO : carDTOs) {
                if (!this.validator.isValid(carDTO)) {
                    this.validator.getViolations(carDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Car car = mapper.map(carDTO, Car.class);

                List<Part> allParts = this.partService.getAllParts();

                final Random random = new Random();

                do {
                    car.getParts().add(allParts.get(random.nextInt(allParts.size())));
                    if (random.nextInt(5) == 0 && car.getParts().size() >= 10) {
                        break;
                    }
                } while (car.getParts().size() <= 20);

                this.carRepository.save(car);
            }

        }
    }
}
