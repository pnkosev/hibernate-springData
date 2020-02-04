package car_dealer.controller;

import car_dealer.service.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    private final SupplierService supplierService;

    @Autowired
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
        this.supplierService.seedMultipleSuppliersFromXML();
    }
}
