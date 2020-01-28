package pn.service;

import pn.domain.dto.binding.ProductDTO;
import pn.domain.dto.view.ProductSellerDTO;
import pn.domain.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    void createMultipleProducts(Collection<ProductDTO> products);

    List<ProductSellerDTO> getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice();
}
