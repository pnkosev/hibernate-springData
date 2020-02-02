package product_shop.service;

import product_shop.domain.entity.Category;

public interface CategoryService {
//    void createMultipleCategories(Collection<CategoryDTO> categories);
//
//    List<CategoryProductCountDTO> getAllByProductCount();

    Category getRandomCategory();
}
