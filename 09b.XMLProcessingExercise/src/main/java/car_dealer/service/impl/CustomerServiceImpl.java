package car_dealer.service.impl;

import car_dealer.domain.dto.importDTO.CustomerDTO;
import car_dealer.domain.dto.importDTO.CustomerRootDTO;
import car_dealer.domain.entity.Customer;
import car_dealer.repository.CustomerRepository;
import car_dealer.service.api.CustomerService;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_XML_IMPORT_PATH = "src/main/resources/xml/input/customers.xml";

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
    public void seedMultipleCustomersFromXML() {
        if (this.customerRepository.count() == 0) {
            CustomerRootDTO customerRootDTO = this.xmlParser.fromXML(CustomerRootDTO.class, CUSTOMERS_XML_IMPORT_PATH);

            for (CustomerDTO customerDTO : customerRootDTO.getCustomers()) {
                if (!this.validator.isValid(customerDTO)) {
                    this.validator.getViolations(customerDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Customer customer = this.mapper.map(customerDTO, Customer.class);

                this.customerRepository.save(customer);
            }
        }
    }
}
