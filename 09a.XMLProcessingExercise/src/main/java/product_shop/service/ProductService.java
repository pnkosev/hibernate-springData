package product_shop.service;

import product_shop.domain.dto.exportDTO.ProductInRangeRootDTO;

public interface ProductService {
    void createMultipleProducts();

    ProductInRangeRootDTO getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice();

    void exportProductsInRange();
}
