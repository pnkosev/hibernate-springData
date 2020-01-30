package pn.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.services.api.*;

@Controller
public class AppController implements CommandLineRunner {
    private final static String SUPPLIERS_SEED_PATH = "src/main/resources/json/input/suppliers.json";
    private final static String PARTS_SEED_PATH = "src/main/resources/json/input/parts.json";
    private final static String CARS_SEED_PATH = "src/main/resources/json/input/cars.json";
    private final static String CUSTOMERS_SEED_PATH = "src/main/resources/json/input/customers.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

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
        this.supplierService.seedMultipleSuppliersFromJSON(SUPPLIERS_SEED_PATH);
    }

    private void seedParts() {
        this.partService.seedMultipleParts(PARTS_SEED_PATH);
    }

    private void seedCars() { this.carService.seedMultipleCars(CARS_SEED_PATH); }

    private void seedCustomers() {
        this.customerService.seedMultipleCustomers(CUSTOMERS_SEED_PATH);
    }

    private void seedSales() { this.saleService.seedMultipleSales(); }
}
