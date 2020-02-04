package product_shop.service;

import product_shop.domain.dto.exportDTO.CategoriesByProductRootDTO;
import product_shop.domain.entity.Category;

public interface CategoryService {
    Category getRandomCategory();

    void createMultipleCategories();

    CategoriesByProductRootDTO getAllByProductCount();

    void exportCategoriesByProductCount();
}
