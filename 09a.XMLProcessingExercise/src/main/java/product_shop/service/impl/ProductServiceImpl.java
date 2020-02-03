package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.domain.dto.importDTO.ProductDTO;
import product_shop.domain.dto.importDTO.ProductRootDTO;
import product_shop.domain.entity.Category;
import product_shop.domain.entity.Product;
import product_shop.domain.entity.User;
import product_shop.repository.ProductRepository;
import product_shop.service.CategoryService;
import product_shop.service.ProductService;
import product_shop.service.UserService;
import product_shop.util.ValidatorUtil;
import product_shop.util.XMLParser;

import javax.transaction.Transactional;
import java.util.Random;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final static String PRODUCTS_XML_IMPORT_PATH = "src/main/resources/json/input/products.xml";

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ValidatorUtil validator;
    private final XMLParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper mapper,
                              UserService userService,
                              CategoryService categoryService,
                              ValidatorUtil validator, XMLParser xmlParser) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public void createMultipleProducts() {
        if (this.productRepository.count() == 0) {
            Random random = new Random();

            ProductRootDTO productRootDTO = this.xmlParser.fromXML(ProductRootDTO.class, PRODUCTS_XML_IMPORT_PATH);

            for (ProductDTO productDTO : productRootDTO.getProductDTOS()) {
                if (!this.validator.isValid(productDTO)) {
                    this.validator.getViolations(productDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Product product = this.mapper.map(productDTO, Product.class);

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

                this.productRepository.save(product);
            }
        }
    }

//    @Override
//    public List<ProductSellerDTO> getAllByPriceBetween500And1000WithoutBuyerOrderedByPrice() {
//        return this.productRepository
//                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(new BigDecimal("500"), new BigDecimal("1000"))
//                .stream()
//                .map(p -> mapper.map(p, ProductSellerDTO.class))
//                .collect(Collectors.toList());
//    }
}
