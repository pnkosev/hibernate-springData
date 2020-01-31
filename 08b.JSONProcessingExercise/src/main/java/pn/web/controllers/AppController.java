package pn.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.models.dtos.views.*;
import pn.services.api.*;
import pn.utils.api.Parser;

import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private final static String SUPPLIERS_SEED_PATH = "src/main/resources/json/input/suppliers.json";
    private final static String PARTS_SEED_PATH = "src/main/resources/json/input/parts.json";
    private final static String CARS_SEED_PATH = "src/main/resources/json/input/cars.json";
    private final static String CUSTOMERS_SEED_PATH = "src/main/resources/json/input/customers.json";

    private final static String CUSTOMERS_BY_BIRTH_DATE_OUTPUT = "src/main/resources/json/output/customers-by-birth-date.json";
    private final static String CARS_VIEW_OUTPUT = "src/main/resources/json/output/cars-view.json";
    private final static String SUPPLIERS_VIEW_OUTPUT = "src/main/resources/json/output/suppliers-view.json";
    private final static String CARS_PARTS_OUTPUT = "src/main/resources/json/output/cars-parts.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final Parser parser;

    public AppController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, Parser parser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.parser = parser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        // Query 1
//        this.exportCustomersByBirthDate();

        // Query 2
//        this.exportCarsByMake();

        // Query 3
//        this.exportLocalSuppliers();

        // Query 4
//        this.exportCarsWithTheirParts();

        // Query 5
        List<CustomerPurchaseViewDTO> allCustomersWithAtLeastOnePurchase = this.customerService.getAllCustomersWithAtLeastOnePurchase();
        List<CustomerPurchaseViewDTO> customersPurchases = this.customerService.getCustomersPurchases();


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

    private void exportCustomersByBirthDate() {
        List<CustomerByBirthDateDTO> customers = this.customerService.getAllCustomersOrderedByBirthDate();
        this.parser.objectToJSON(CUSTOMERS_BY_BIRTH_DATE_OUTPUT, customers);
    }

    private void exportCarsByMake() {
        List<CarViewDTO> toyotaList = this.carService.getCarsByMake("Toyota");
        this.parser.objectToJSON(CARS_VIEW_OUTPUT, toyotaList);
    }

    private void exportLocalSuppliers() {
        List<SupplierViewDTO> suppliers = this.supplierService.getAllNonImportingSuppliers();
        this.parser.objectToJSON(SUPPLIERS_VIEW_OUTPUT, suppliers);
    }

    private void exportCarsWithTheirParts() {
        List<CarPartViewDTO> allCarsWithTheirParts = carService.getAllCarsWithTheirParts();
        this.parser.objectToJSON(CARS_PARTS_OUTPUT, allCarsWithTheirParts);
    }
}
