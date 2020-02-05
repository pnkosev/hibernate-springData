package car_dealer.service.impl;

import car_dealer.domain.entity.Car;
import car_dealer.domain.entity.Customer;
import car_dealer.domain.entity.Sale;
import car_dealer.repository.CarRepository;
import car_dealer.repository.CustomerRepository;
import car_dealer.repository.SaleRepository;
import car_dealer.service.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper mapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedMultipleSales() {
        if (this.saleRepository.count() == 0) {
            List<Car> allCars = this.carRepository.findAll();
            List<Customer> allCustomers = this.customerRepository.findAll();
            Double[] discounts = {0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
            Random random = new Random();

            for (Car car : allCars) {
                Sale sale = new Sale();
                sale.setCar(car);
                sale.setCustomer(allCustomers.get(random.nextInt(allCustomers.size())));
                sale.setDiscount(discounts[random.nextInt(discounts.length)]);
                this.saleRepository.save(sale);
            }
        }
    }
}
