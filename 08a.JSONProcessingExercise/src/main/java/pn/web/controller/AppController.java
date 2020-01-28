package pn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.dto.binding.ProductDTO;
import pn.domain.dto.binding.UserDTO;
import pn.domain.dto.view.ProductSellerDTO;
import pn.service.CategoryService;
import pn.service.ProductService;
import pn.service.UserService;
import pn.utils.impl.JsonParser;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final JsonParser jsonParser;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public AppController(JsonParser jsonParser, UserService userService, ProductService productService, CategoryService categoryService) {
        this.jsonParser = jsonParser;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        this.seedDatabase();

        this.saveInJsonAllProductsWithPriceBetween500And1000();

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();
    }

    private void seedUsers() {
        UserDTO[] users = jsonParser.objectFromFile(UserDTO[].class, "src/main/resources/input/users.json");

        this.userService.createMultipleUsers(Arrays.asList(users));
    }

    private void seedProducts() {
        ProductDTO[] products = jsonParser.objectFromFile(ProductDTO[].class, "src/main/resources/input/products.json");

        this.productService.createMultipleProducts(Arrays.asList(products));
    }

    private void seedCategories() {
        CategoryDTO[] categories = jsonParser.objectFromFile(CategoryDTO[].class, "src/main/resources/input/categories.json");

        this.categoryService.createMultipleCategories(Arrays.asList(categories));
    }

    private List<ProductSellerDTO> getAllProductsWithPriceBetween500And1000OrderedByPrice() {
        return this.productService.getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice();
    }

    private void saveInJsonAllProductsWithPriceBetween500And1000() {
        List<ProductSellerDTO> products = this.getAllProductsWithPriceBetween500And1000OrderedByPrice();
        jsonParser.objectToFile(products, "src/main/resources/output/products-in-range.json");
    }
}
