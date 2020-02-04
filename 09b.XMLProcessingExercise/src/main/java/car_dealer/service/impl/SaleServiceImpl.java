package car_dealer.service.impl;

import car_dealer.repository.SaleRepository;
import car_dealer.service.api.CarService;
import car_dealer.service.api.CustomerService;
import car_dealer.service.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, ModelMapper mapper) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public void seedMultipleSales() {

    }
}
