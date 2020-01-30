package pn.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.services.api.CarService;
import pn.services.api.CustomerService;
import pn.services.api.PartService;
import pn.services.api.SupplierService;

@Controller
public class AppController implements CommandLineRunner {
    private final static String SUPPLIERS_SEED_PATH = "src/main/resources/json/suppliers.json";
    private final static String PARTS_SEED_PATH = "src/main/resources/json/parts.json";
    private final static String CARS_SEED_PATH = "src/main/resources/json/cars.json";
    private final static String CUSTOMERS_SEED_PATH = "src/main/resources/json/customers.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;

    public AppController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
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
}
