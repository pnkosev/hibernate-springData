package pn.service;

import org.springframework.data.jpa.repository.Query;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.entity.Category;

import java.util.Collection;

public interface CategoryService {
    void createMultipleCategories(Collection<CategoryDTO> categories);

    Category getRandomCategory();
}
