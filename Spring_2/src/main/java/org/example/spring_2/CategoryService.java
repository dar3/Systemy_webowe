package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }


    public Category add(Category category) {
        if (category.getCode() == null || category.getCode().isEmpty()) {
            category.setCode(generateUniqueCode());
        }
        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Category with the same name or code already exists.");
        }
    }

    private String generateUniqueCode() {

        Optional<String> maxCode = categoryRepository.findMaxCode();
        if (maxCode.isPresent()) {
            int nextNumber = Integer.parseInt(maxCode.get().substring(1)) + 1;
            return "K" + nextNumber;
        }
        return "K1"; // if there are no records we start from K1
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CategoryService)) return false;
        final CategoryService other = (CategoryService) o;
        if (!other.canEqual(this)) return false;
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
