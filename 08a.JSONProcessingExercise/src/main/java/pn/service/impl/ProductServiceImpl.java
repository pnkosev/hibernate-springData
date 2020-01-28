package pn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.dto.binding.ProductDTO;
import pn.domain.dto.view.ProductSellerDTO;
import pn.domain.entity.Category;
import pn.domain.entity.Product;
import pn.domain.entity.User;
import pn.repository.ProductRepository;
import pn.service.CategoryService;
import pn.service.ProductService;
import pn.service.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void createMultipleProducts(Collection<ProductDTO> productDTOs) {
        if (this.productRepository.count() == 0) {
            Product[] products = this.mapper.map(productDTOs, Product[].class);

            Random random = new Random();
            for (Product product : products) {
                User randomSeller = this.userService.getRandomUser();
                product.setSeller(randomSeller);
                randomSeller.getProductsSold().add(product);

                if (random.nextInt(2) == 0) {
                    User randomBuyer = this.userService.getRandomUser();
                    product.setBuyer(randomBuyer);
                    randomBuyer.getProductsBought().add(product);
                }

                Category randomCategory = this.categoryService.getRandomCategory();
                if (!randomCategory.getProducts().contains(product)) {
                    randomCategory.getProducts().add(product);
                    product.getCategories().add(randomCategory);
                }
            }

            this.productRepository.saveAll(Arrays.asList(products));
        }
    }

    @Override
    public List<ProductSellerDTO> getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice() {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(new BigDecimal("500"), new BigDecimal("1000"))
                .stream()
                .map(p -> mapper.map(p, ProductSellerDTO.class))
                .collect(Collectors.toList());
    }
}
