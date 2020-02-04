package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.domain.dto.exportDTO.CategoriesByProductRootDTO;
import product_shop.domain.dto.exportDTO.CategoryDetailDTO;
import product_shop.domain.dto.importDTO.CategoryDTO;
import product_shop.domain.dto.importDTO.CategoryRootDTO;
import product_shop.domain.entity.Category;
import product_shop.domain.entity.Product;
import product_shop.repository.CategoryRepository;
import product_shop.service.CategoryService;
import product_shop.util.ValidatorUtil;
import product_shop.util.XMLParser;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORIES_XML_INPUT_PATH = "src/main/resources/xml/input/categories.xml";
    private static final String CATEGORIES_BY_PRODUCT_COUNT_EXPORT_PATH = "src/main/resources/xml/output/categories-by-products.xml";

    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validator;
    private final XMLParser xmlParser;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository, ValidatorUtil validator, XMLParser xmlParser) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public Category getRandomCategory() {
        return this.categoryRepository.getRandomCategory();
    }

    @Override
    public void createMultipleCategories() {
        if (categoryRepository.count() == 0) {
            CategoryRootDTO categoryRootDTO = xmlParser.fromXML(CategoryRootDTO.class, CATEGORIES_XML_INPUT_PATH);

            for (CategoryDTO categoryDTO : categoryRootDTO.getCategory()) {
                if (!this.validator.isValid(categoryDTO)) {
                    this.validator.getViolations(categoryDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Category category = this.mapper.map(categoryDTO, Category.class);

                this.categoryRepository.save(category);
            }
        }
    }

    @Override
    public CategoriesByProductRootDTO getAllByProductCount() {
        List<CategoryDetailDTO> categoryDetailDTOS = this.categoryRepository.getAllOrderedByProductCount();
        CategoriesByProductRootDTO categories = new CategoriesByProductRootDTO();
        categories.setCategories(categoryDetailDTOS);
        return categories;
    }

    @Override
    public void exportCategoriesByProductCount() {
        this.xmlParser.toXML(this.getAllByProductCount(), CATEGORIES_BY_PRODUCT_COUNT_EXPORT_PATH);
    }
}
