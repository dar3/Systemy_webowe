package org.example.spring_2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final List<Category> categories = new ArrayList<>();
    private int counter = 0;


    @GetMapping
    public List<Category> getCategories(Model model) {
        model.addAttribute("categories", categories);
        return categories;
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("category") Category category) {
        category.setId(++counter);
        category.setCode("K" + category.getId());
        categories.add(category);
        return "redirect:/categories";
    }


    @GetMapping("/{id}/edit")
    public String editCategoryPage(@PathVariable int id, Model model) {
        Category category = categories.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") Category updatedCategory) {
        Category existingCategory = categories.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName());
        }
        return "redirect:/categories";
    }


    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable int id) {
        try {
            categories.removeIf(category -> category.getId() == id);
        } catch (Exception ignored) {
            System.out.println("Error deleting category " + id);
        }
        return "redirect:/categories";
    }

}
