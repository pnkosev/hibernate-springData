package pn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.entities.Car;
import pn.models.entities.Customer;
import pn.models.entities.Sale;
import pn.repositories.SaleRepository;
import pn.services.api.CarService;
import pn.services.api.CustomerService;
import pn.services.api.SaleService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void seedMultipleSales() {
        if (this.saleRepository.count() == 0) {
            List<Car> allCars = this.carService.getALLCars();
            List<Customer> allCustomers = this.customerService.getAllCustomers();
            List<Double> discounts = Arrays.asList(0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5);

            for (Car aCar : allCars) {
                Sale sale = new Sale();

                sale.setCar(aCar);
                sale.setCustomer(allCustomers.get(ThreadLocalRandom.current().nextInt(0, allCustomers.size() - 1)));
                sale.setDiscount(discounts.get(ThreadLocalRandom.current().nextInt(0, discounts.size() - 1)));

                this.saleRepository.save(sale);
            }
        }
    }
}
