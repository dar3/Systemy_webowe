package org.example.spring_2;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CategoryService)) return false;
        final CategoryService other = (CategoryService) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$categoryRepository = this.getCategoryRepository();
        final Object other$categoryRepository = other.getCategoryRepository();
        if (this$categoryRepository == null ? other$categoryRepository != null : !this$categoryRepository.equals(other$categoryRepository))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CategoryService;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $categoryRepository = this.getCategoryRepository();
        result = result * PRIME + ($categoryRepository == null ? 43 : $categoryRepository.hashCode());
        return result;
    }

    public String toString() {
        return "CategoryService(categoryRepository=" + this.getCategoryRepository() + ")";
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }
}
