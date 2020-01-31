package pn.services.api;

import pn.models.dtos.views.CustomerByBirthDateDTO;
import pn.models.dtos.views.CustomerPurchaseViewDTO;
import pn.models.entities.Customer;

import java.util.List;

public interface CustomerService {
    void seedMultipleCustomers(String path);

    List<Customer> getAllCustomers();

    List<CustomerByBirthDateDTO> getAllCustomersOrderedByBirthDate();

    List<CustomerPurchaseViewDTO> getAllCustomersWithAtLeastOnePurchase();

    List<CustomerPurchaseViewDTO> getCustomersPurchases();
}
