package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.domain.entity.Category;
import product_shop.repository.CategoryRepository;
import product_shop.service.CategoryService;
import product_shop.util.ValidatorUtil;

import javax.transaction.Transactional;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validator;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository, ValidatorUtil validator) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

//    @Override
//    public void createMultipleCategories(Collection<CategoryDTO> categoryDTOs) {
//        if (this.categoryRepository.count() == 0) {
//            List<CategoryDTO> validDTOs = new ArrayList<>();
//            for (CategoryDTO categoryDTO : categoryDTOs) {
//                if (!this.validator.isValid(categoryDTO)) {
//                    this.validator.getViolations(categoryDTO)
//                            .forEach(v -> System.out.println(v.getMessage()));
//                    continue;
//                }
//                validDTOs.add(categoryDTO);
//            }
//
//            Category[] categories = this.mapper.map(validDTOs, Category[].class);
//
//            this.categoryRepository.saveAll(Arrays.asList(categories));
//        }
//    }
//
//    @Override
//    public List<CategoryProductCountDTO> getAllByProductCount() {
//        return this.categoryRepository.getAllOrderedByProductCount();
//    }

    @Override
    public Category getRandomCategory() {
        return this.categoryRepository.getRandomCategory();
    }
}
