package product_shop.service;

import product_shop.domain.entity.Category;

public interface CategoryService {
    Category getRandomCategory();

    void createMultipleCategories();

//    List<CategoryProductCountDTO> getAllByProductCount();
}
