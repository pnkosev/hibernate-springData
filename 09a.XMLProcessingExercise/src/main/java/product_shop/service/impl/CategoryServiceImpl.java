package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.dto.view.CategoryProductCountDTO;
import pn.domain.entity.Category;
import pn.repository.CategoryRepository;
import pn.service.CategoryService;
import pn.utils.ValidatorUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtils validator;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository, ValidatorUtils validator) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public void createMultipleCategories(Collection<CategoryDTO> categoryDTOs) {
        if (this.categoryRepository.count() == 0) {
            List<CategoryDTO> validDTOs = new ArrayList<>();
            for (CategoryDTO categoryDTO : categoryDTOs) {
                if (!this.validator.isValid(categoryDTO)) {
                    this.validator.getViolations(categoryDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                validDTOs.add(categoryDTO);
            }

            Category[] categories = this.mapper.map(validDTOs, Category[].class);

            this.categoryRepository.saveAll(Arrays.asList(categories));
        }
    }

    @Override
    public Category getRandomCategory() {
        return this.categoryRepository.getRandomCategory();
    }

    @Override
    public List<CategoryProductCountDTO> getAllByProductCount() {
        return this.categoryRepository.getAllOrderedByProductCount();
    }
}
