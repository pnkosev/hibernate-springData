package pn.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.services.api.SupplierService;

@Controller
public class AppController implements CommandLineRunner {
    private final static String SUPPLIERS_SEED_PATH = "src/main/resources/json/suppliers.json";

    private final SupplierService supplierService;

    public AppController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedSuppliers();
    }

    private void seedSuppliers() {
        this.supplierService.seedMultipleSuppliersFromJSON(SUPPLIERS_SEED_PATH);
    }
}
