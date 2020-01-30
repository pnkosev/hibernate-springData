package pn.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.dtos.bindings.CustomerDTO;
import pn.models.entities.Customer;
import pn.repositories.CustomerRepository;
import pn.services.api.CustomerService;
import pn.utils.api.Parser;
import pn.utils.api.ValidatorUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Parser parser;
    private final ModelMapper mapper;
    private final ValidatorUtils validator;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(Parser parser, ModelMapper mapper, ValidatorUtils validator, CustomerRepository customerRepository) {
        this.parser = parser;
        this.mapper = mapper;
        this.validator = validator;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedMultipleCustomers(String path) {
        if (this.customerRepository.count() == 0) {
            CustomerDTO[] customerDTOS = this.parser.objectFromJSON(path, CustomerDTO[].class);

            for (CustomerDTO customerDTO : customerDTOS) {
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

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}
