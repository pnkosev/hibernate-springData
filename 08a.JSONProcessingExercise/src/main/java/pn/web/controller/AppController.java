package pn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.dto.binding.ProductDTO;
import pn.domain.dto.binding.UserDTO;
import pn.domain.dto.view.CategoryProductCountDTO;
import pn.domain.dto.view.ProductSellerDTO;
import pn.domain.dto.view.UserProductSoldDTO;
import pn.domain.entity.Category;
import pn.service.CategoryService;
import pn.service.ProductService;
import pn.service.UserService;
import pn.utils.impl.JsonParser;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private static final String INPUT_PATH = "src/main/resources/input/";
    private static final String OUTPUT_PATH = "src/main/resources/output/";
    private static final String CATEGORIES_PATH = INPUT_PATH + "categories.json";
    private static final String PRODUCTS_PATH = INPUT_PATH + "products.json";
    private static final String USERS_PATH = INPUT_PATH + "users.json";
    private static final String CATEGORIES_PRODUCTS_COUNT_PATH = OUTPUT_PATH + "categories-products-count.json";
    private static final String PRODUCTS_IN_RANGE_PATH = OUTPUT_PATH + "products-in-range.json";
    private static final String USERS_WITH_PRODUCTS_SOLD_PATH = OUTPUT_PATH + "users-with-products-sold.json";

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

        // Query 1
//        this.saveInJsonAllProductsWithPriceBetween500And1000();

        // Query 2
//        this.saveInJsonAllSuccessfulSellers();

        // Query 3
//        this.saveInJsonAllCategoriesByProductCount();

        // Query 4
        

        System.out.println("yoyo");
    }

    private void seedDatabase() {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();
    }

    private void seedUsers() {
        UserDTO[] users = jsonParser.objectFromFile(UserDTO[].class, USERS_PATH);

        this.userService.createMultipleUsers(Arrays.asList(users));
    }

    private void seedProducts() {
        ProductDTO[] products = jsonParser.objectFromFile(ProductDTO[].class, PRODUCTS_PATH);

        this.productService.createMultipleProducts(Arrays.asList(products));
    }

    private void seedCategories() {
        CategoryDTO[] categories = jsonParser.objectFromFile(CategoryDTO[].class, CATEGORIES_PATH);

        this.categoryService.createMultipleCategories(Arrays.asList(categories));
    }

    private void saveInJsonAllProductsWithPriceBetween500And1000() {
        List<ProductSellerDTO> products = this.getAllProductsWithPriceBetween500And1000OrderedByPrice();
        jsonParser.objectToFile(products, PRODUCTS_IN_RANGE_PATH);
    }

    private List<ProductSellerDTO> getAllProductsWithPriceBetween500And1000OrderedByPrice() {
        return this.productService.getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice();
    }

    private void saveInJsonAllSuccessfulSellers() {
        List<UserProductSoldDTO> users = this.getAllUsersWithSoldProducts();

        jsonParser.objectToFile(users, USERS_WITH_PRODUCTS_SOLD_PATH);
    }

    private List<UserProductSoldDTO> getAllUsersWithSoldProducts() {
        return this.userService.getUsersWithSoldProducts();
    }

    private void saveInJsonAllCategoriesByProductCount() {
        List<CategoryProductCountDTO> categoriesByProductCount = this.getAllCategoriesByProductCount();
        this.jsonParser.objectToFile(categoriesByProductCount, CATEGORIES_PRODUCTS_COUNT_PATH);
    }

    private List<CategoryProductCountDTO> getAllCategoriesByProductCount() {
        return this.categoryService.getAllByProductCount();
    }
}
