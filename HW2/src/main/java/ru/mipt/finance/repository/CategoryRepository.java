package ru.mipt.finance.repository;

import org.springframework.stereotype.Repository;
import ru.mipt.finance.model.Category;

import java.util.*;

@Repository
public class CategoryRepository {
    private final Map<Integer, Category> categories = new HashMap<>();

    public void save(Category category) {
        categories.put(category.getId(), category);
    }

    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(categories.get(id));
    }

    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }

    public boolean deleteById(Integer id) {
        return categories.remove(id) != null;
    }
}
