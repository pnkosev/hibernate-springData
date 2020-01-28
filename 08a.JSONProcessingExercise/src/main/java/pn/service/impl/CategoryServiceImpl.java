package pn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.entity.Category;
import pn.repository.CategoryRepository;
import pn.service.CategoryService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createMultipleCategories(Collection<CategoryDTO> categoryDTOs) {
        if (this.categoryRepository.count() == 0) {
            Category[] categories = this.mapper.map(categoryDTOs, Category[].class);

            this.categoryRepository.saveAll(Arrays.asList(categories));
        }
    }

    @Override
    public Category getRandomCategory() {
        return this.categoryRepository.getRandomCategory();
    }
}
