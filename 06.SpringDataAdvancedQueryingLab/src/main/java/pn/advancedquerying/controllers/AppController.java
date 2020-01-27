package pn.advancedquerying.controllers;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.advancedquerying.domain.entities.Label;
import pn.advancedquerying.domain.entities.Size;
import pn.advancedquerying.services.IngredientService;
import pn.advancedquerying.services.LabelService;
import pn.advancedquerying.services.ShampooService;
import pn.advancedquerying.utils.ScannerUtil;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final ShampooService shampooService;
    private final LabelService labelService;
    private final IngredientService ingredientService;

    @Autowired
    public AppController(ShampooService shampooService, LabelService labelService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.labelService = labelService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Select Shampoos by Size
//        String sizeString = ScannerUtil.nextLine().toUpperCase();
//        Size size = Size.valueOf(sizeString);
//        printAllShampoosBySize(size);

        // 2. Select Shampoos by Size or Label
//        this.printAllShampoosBySizeOrLabel(Size.MEDIUM, this.labelService.getOne(10L));

        // 3. Select Shampoos by Price
//        this.printAllShampoosWithPriceGreaterThanAndOrderByPriceDesc("5");

        // 4. Select Ingredients by Name
//        this.printAllIngredientsStartingWith("M");

        // 5. Select Ingredients by Names
//        this.printAllIngredientsInListOrderPriceAsc(Arrays.asList("Lavender", "Herbs", "Apple"));

        // 6. Count Shampoos by Price
//        this.printCountOfShampoosWithPriceLowerThan("8.50");

        // 7. Select Shampoos by Ingredients
//        this.printAllShampoosWithIngredients(Arrays.asList("Berry", "Mineral-Collagen"));
        
        // 8. Select Shampoos by Ingredients Count
//        this.printAllShampoosByIngredientCount(2);

        // 9. Select Ingredient Name and Shampoo Brand By Name
//        this.printPriceOfIngredientsOfShampoo("Silk Comb");

        // 10. Delete Ingredients by name
//        this.deleteIngredientsByName("Berry");

        // 11. Update Ingredients by price
//        this.increasePriceOfIngredientsByTenPercent();

        // 12. Update Ingredients by Names
//        this.increasePriceOfGivenIngredientsByTenPercent(Arrays.asList("Apple", "Nettle"));

        System.out.println("yoyo");
    }

    private void printAllShampoosBySize(Size size) {
        this.shampooService.getAllBySize(size)
                .forEach(System.out::println);
    }

    private void printAllShampoosBySizeOrLabel(Size size, Label label) {
        this.shampooService.getAllBySizeOrLabel(size, label)
                .forEach(System.out::println);
    }

    private void printAllShampoosWithPriceGreaterThanAndOrderByPriceDesc(String price) {
        this.shampooService.getAllWithGreaterPriceDescByPrice(price)
                .forEach(System.out::println);
    }

    private void printAllIngredientsStartingWith(String name) {
        this.ingredientService.getAllStartingWith(name)
                .forEach(System.out::println);
    }

    private void printAllIngredientsInListOrderPriceAsc(List<String> list) {
        this.ingredientService.getAllContainedInList(list)
                .forEach(System.out::println);
    }

    private void printCountOfShampoosWithPriceLowerThan(String price) {
        System.out.println(this.shampooService.countAllWithPriceLessThan(price));
    }

    private void printAllShampoosWithIngredients(List<String> ingredients) {
        this.shampooService.getAllWithIngredients(ingredients)
                .forEach(System.out::println);
    }

    private void printAllShampoosByIngredientCount(int numberOfIngredients) {
        this.shampooService.getAllWithIngredientsLessThan(numberOfIngredients)
                .forEach(System.out::println);
    }

    private void printPriceOfIngredientsOfShampoo(String shampooBrand) {
        System.out.println(this.shampooService.getPriceOfIngredientsOfShampoo(shampooBrand));
    }

    private void deleteIngredientsByName(String name) {
        this.ingredientService.deleteByName(name);
    }

    private void increasePriceOfIngredientsByTenPercent() {
        this.ingredientService.increasePricesByTenPercent();
    }

    private void increasePriceOfGivenIngredientsByTenPercent(List<String> ingredients) {
        this.ingredientService.increasePricesByTenPercentOfGivenIngredients(ingredients);
    }
}
