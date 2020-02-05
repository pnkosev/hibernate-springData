package car_dealer.service.api;

import car_dealer.domain.dto.exportDTO.CustomerOrderedRootDTO;

public interface CustomerService {
    void seedMultipleCustomersFromXML();

    CustomerOrderedRootDTO getAllCustomersOrderedByBirthDate();

    void exportOrderedCustomers();

//    List<CustomerPurchaseViewDTO> getAllCustomersWithAtLeastOnePurchase();
//
//    List<CustomerPurchaseViewDTO> getCustomersPurchases();
}
