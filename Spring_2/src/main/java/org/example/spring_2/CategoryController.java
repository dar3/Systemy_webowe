package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller

@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
//    private final List<Category> categories = new ArrayList<>();
//    private int counter = 0;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }


    @GetMapping
    public List<Category> getCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
//        model.addAttribute("categories", categories);
//        return categories;
        return categoryService.findAll();
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("category") Category category) {
//        category.setId(++counter);
//        category.setCode("K" + ++counter);
//        categories.add(category);
        categoryService.add(category);
        return "redirect:/categories";
    }


    @GetMapping("/{id}/edit")
    public String editCategoryPage(@PathVariable int id, Model model) {
        Category category = categoryService.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

//        Category category = categories.stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .orElse(null);

        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") Category updatedCategory) {
        categoryService.update(updatedCategory);
        Category existingCategory = categoryService.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

//        Category existingCategory = categories.stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName());
        }
        return "redirect:/categories";
    }


    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable int id) {
        try {
            categoryService.deleteById(id);
        } catch (Exception ignored) {
            System.out.println("Error deleting category " + id);
        }
        return "redirect:/categories";
    }

}
