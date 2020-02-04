package car_dealer.controller;

import car_dealer.service.api.CarService;
import car_dealer.service.api.PartService;
import car_dealer.service.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;

    @Autowired
    public AppController(SupplierService supplierService, PartService partService, CarService carService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
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
    }

    private void seedSuppliers() {
        this.supplierService.seedMultipleSuppliersFromXML();
    }

    private void seedParts() {
        this.partService.seedMultiplePartsFromXML();
    }

    private void seedCars() { this.carService.seedMultipleCarsFromXML(); }
}
