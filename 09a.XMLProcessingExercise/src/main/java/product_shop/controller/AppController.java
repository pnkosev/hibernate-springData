package product_shop.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import product_shop.domain.dto.exportDTO.CategoriesByProductRootDTO;
import product_shop.domain.dto.exportDTO.ProductInRangeRootDTO;
import product_shop.domain.dto.exportDTO.UserWithSoldProductRootDTO;
import product_shop.domain.dto.exportDTO.UsersAndProductsRootDTO;
import product_shop.service.CategoryService;
import product_shop.service.ProductService;
import product_shop.service.UserService;

@Controller
public class AppController implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public AppController(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();

        // Query 1
//        this.exportProductsInRange();

        // Query 2
//        this.exportUsersWithProductsSold();

        // Query 3
//        this.exportCategoriesByProductCount();

        // Query 4
//        this.exportUsersAndProductsSold();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();
    }

    private void seedUsers() {
        this.userService.createMultipleUsers();
    }

    private void seedCategories() {
        this.categoryService.createMultipleCategories();
    }

    private void seedProducts() {
        this.productService.createMultipleProducts();
    }

    private void exportProductsInRange() {
        this.productService.exportProductsInRange();
    }

    private void exportUsersWithProductsSold() {
        this.userService.exportUsersWithSoldProducts();
    }

    private void exportCategoriesByProductCount() {
        this.categoryService.exportCategoriesByProductCount();
    }

    private void exportUsersAndProductsSold() {
        this.userService.exportUsersAndProductsSold();
    }
}
