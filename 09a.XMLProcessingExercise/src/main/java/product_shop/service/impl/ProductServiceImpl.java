package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.repository.ProductRepository;
import product_shop.service.CategoryService;
import product_shop.service.ProductService;
import product_shop.service.UserService;
import product_shop.util.ValidatorUtil;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ValidatorUtil validator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper mapper,
                              UserService userService,
                              CategoryService categoryService,
                              ValidatorUtil validator) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.validator = validator;
    }

    @Override
    public void createMultipleProducts() {
    }

    //    @Override
//    public void createMultipleProducts(Collection<ProductDTO> productDTOs) {
//        if (this.productRepository.count() == 0) {
//            List<ProductDTO> validDTOs = new ArrayList<>();
//
//            for (ProductDTO productDTO : productDTOs) {
//                if (!this.validator.isValid(productDTO)) {
//                    this.validator.getViolations(productDTO)
//                            .forEach(v -> System.out.println(v.getMessage()));
//                    continue;
//                }
//
//                validDTOs.add(productDTO);
//            }
//
//            Product[] products = this.mapper.map(validDTOs, Product[].class);
//
//            Random random = new Random();
//            for (Product product : products) {
//                User randomSeller = this.userService.getRandomUser();
//                product.setSeller(randomSeller);
//                randomSeller.getProductsSold().add(product);
//
//                if (random.nextInt(2) == 0) {
//                    User randomBuyer = this.userService.getRandomUser();
//                    product.setBuyer(randomBuyer);
//                    randomBuyer.getProductsBought().add(product);
//                }
//
//                Category randomCategory = this.categoryService.getRandomCategory();
//                if (!randomCategory.getProducts().contains(product)) {
//                    randomCategory.getProducts().add(product);
//                    product.getCategories().add(randomCategory);
//                }
//            }
//
//            this.productRepository.saveAll(Arrays.asList(products));
//        }
//    }
//
//    @Override
//    public List<ProductSellerDTO> getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice() {
//        return this.productRepository
//                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(new BigDecimal("500"), new BigDecimal("1000"))
//                .stream()
//                .map(p -> mapper.map(p, ProductSellerDTO.class))
//                .collect(Collectors.toList());
//    }
}
