package car_dealer.service.impl;

import car_dealer.repository.CustomerRepository;
import car_dealer.service.api.CustomerService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final XMLParser xmlParser;
    private final ModelMapper mapper;
    private final ValidatorUtil validator;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(XMLParser xmlParser, ModelMapper mapper, ValidatorUtil validator, CustomerRepository customerRepository) {
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validator = validator;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedMultipleCustomers(String path) {

    }
}
