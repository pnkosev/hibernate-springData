package pn.springdataintroexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pn.springdataintroexercise.domain.entities.Category;
import pn.springdataintroexercise.repository.CategoryRepository;
import pn.springdataintroexercise.utils.FileUtil;

import java.io.IOException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORY_TXT_PATH = "C:\\Users\\user\\Desktop\\Projects\\Java\\Hibernate\\05a.SpringDataIntroExercise\\src\\main\\resources\\files\\categories.txt";

    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] lines = this.fileUtil.readFile(CATEGORY_TXT_PATH);

        for (String line : lines) {
            String categoryName = line.split("\\s+")[0];

            Category category = new Category();
            category.setName(categoryName);

            this.categoryRepository.save(category);
        }
    }
}
