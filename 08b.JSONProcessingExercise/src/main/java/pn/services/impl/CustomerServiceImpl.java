package pn.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.dtos.bindings.CustomerDTO;
import pn.models.dtos.views.CustomerByBirthDateDTO;
import pn.models.dtos.views.CustomerPurchaseViewDTO;
import pn.models.entities.Customer;
import pn.models.entities.Part;
import pn.repositories.CustomerRepository;
import pn.services.api.CustomerService;
import pn.utils.api.Parser;
import pn.utils.api.ValidatorUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CustomerByBirthDateDTO> getAllCustomersOrderedByBirthDate() {
        return this.customerRepository.findAllOrderedByBirthDateAscAndYoungDriver()
                .stream()
                .map(c -> mapper.map(c, CustomerByBirthDateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerPurchaseViewDTO> getAllCustomersWithAtLeastOnePurchase() {
        return this.customerRepository.findAllCustomersWithAtLeastOnePurchase();
    }

    @Override
    public List<CustomerPurchaseViewDTO> getCustomersPurchases() {
        return this.customerRepository
                .findAll()
                .stream()
                .filter(c -> c.getPurchases().size() > 0)
                .map(customer -> {
                    CustomerPurchaseViewDTO customerDto = new CustomerPurchaseViewDTO();
                    customerDto.setFullName(customer.getName());
                    customerDto.setBoughtCars(customer.getPurchases().size());

                    BigDecimal moneySpent = customer.getPurchases()
                            .stream()
                            .map(sale -> sale.getCar()
                                    .getParts()
                                    .stream()
                                    .map(p -> p.getPrice().subtract(p.getPrice().multiply(BigDecimal.valueOf(sale.getDiscount()))))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                            )
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    customerDto.setSpentMoney(moneySpent);
                    return customerDto;
                })
                .sorted((c1, c2) -> {
                    int cmp = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if (cmp == 0) {
                        Integer cp2 = c2.getBoughtCars();
                        Integer cp1 = c2.getBoughtCars();
                        cmp = cp2.compareTo(cp1);
                        // cmp = Integer.compare(c2.getBoughtCars(), c1.getBoughtCars()); // another solution
                    }
                    return cmp;
                })
                .collect(Collectors.toList());
    }
}
