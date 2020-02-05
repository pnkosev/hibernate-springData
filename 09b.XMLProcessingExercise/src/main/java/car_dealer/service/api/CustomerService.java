package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.CustomerOrderedRootDTO;
import car_dealer.domain.dto.exportDTO.CustomerPurchaseRootDTO;

public interface CustomerService {
    void seedMultipleCustomersFromXML();

    CustomerOrderedRootDTO getAllCustomersOrderedByBirthDate();

    void exportOrderedCustomers();

    CustomerPurchaseRootDTO getAllCustomersWithAtLeastOnePurchase();

    void exportCustomersWithPurchases();

//    List<CustomerPurchaseViewDTO> getCustomersPurchases();
}
