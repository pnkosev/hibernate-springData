package car_dealer.controller;

import car_dealer.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public AppController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        // Query 1
        this.exportOrderedCustomers();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();
    }

    private void seedSuppliers() {
        this.supplierService.seedMultipleSuppliersFromXML();
    }

    private void seedParts() {
        this.partService.seedMultiplePartsFromXML();
    }

    private void seedCars() { this.carService.seedMultipleCarsFromXML(); }

    private void seedCustomers() { this.customerService.seedMultipleCustomersFromXML(); }

    private void seedSales() { this.saleService.seedMultipleSales(); }

    private void exportOrderedCustomers() { this.customerService.exportOrderedCustomers(); }
}
