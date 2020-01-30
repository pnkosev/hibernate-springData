package pn.services.api;

import pn.models.entities.Customer;

import java.util.List;

public interface CustomerService {
    void seedMultipleCustomers(String path);

    List<Customer> getAllCustomers();
}
