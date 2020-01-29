package pn.service;

import org.springframework.data.jpa.repository.Query;
import pn.domain.dto.binding.CategoryDTO;
import pn.domain.dto.view.CategoryProductCountDTO;
import pn.domain.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    void createMultipleCategories(Collection<CategoryDTO> categories);

    Category getRandomCategory();

    List<CategoryProductCountDTO> getAllByProductCount();
}
