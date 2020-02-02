package product_shop.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import product_shop.service.UserService;

@Controller
public class AppController implements CommandLineRunner {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedUsers();
        this.seedProducts();
        this.seedCategories();
    }

    private void seedUsers() {
        this.userService.createMultipleUsers();
    }

    private void seedProducts() {
    }

    private void seedCategories() {
    }
}
