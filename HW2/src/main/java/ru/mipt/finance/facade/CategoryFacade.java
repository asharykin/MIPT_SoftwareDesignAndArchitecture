package ru.mipt.finance.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.finance.factory.CategoryFactory;
import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.OperationType;
import ru.mipt.finance.repository.CategoryRepository;
import ru.mipt.finance.repository.OperationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryFacade {
    private final CategoryFactory categoryFactory;
    private final CategoryRepository categoryRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public CategoryFacade(CategoryFactory categoryFactory, CategoryRepository categoryRepository,
                          OperationRepository operationRepository) {
        this.categoryFactory = categoryFactory;
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
    }

    public Category createCategory(String name, OperationType type) {
        Category category = categoryFactory.createCategory(name, type);
        categoryRepository.save(category);
        return category;
    }

    public boolean updateCategory(Integer categoryId, String newName) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isEmpty()) {
            return false;
        }
        Category category = categoryOpt.get();
        category.setName(newName);
        categoryRepository.save(category);
        return true;
    }

    public boolean deleteCategory(Integer categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            return false;
        }
        operationRepository.deleteByCategoryId(categoryId);
        return categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
