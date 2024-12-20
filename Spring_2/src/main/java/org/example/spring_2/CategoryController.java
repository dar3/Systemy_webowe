package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@Controller

@RequestMapping("/categories")
//@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;


    @GetMapping
    public List<Category> getCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return categoryService.findAll();
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("category") Category category) {
//        if (categoryRepository.findByName(category.getName()) == null)
        if (categoryService.findByName(category.getName()) == null)
            categoryService.add(category);
        return "redirect:/categories";
    }


    @GetMapping("/{id}/edit")
    public String editCategoryPage(@PathVariable int id, Model model) {
        Category category = categoryService.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);



        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(@ModelAttribute("category") Category updatedCategory) {
        Category sameNameCategory = categoryRepository.findByName(updatedCategory.getName());
        Category sameCodeCategory = categoryRepository.findByCode(updatedCategory.getCode());
        if ((sameNameCategory == null || sameNameCategory.getId() ==
                updatedCategory.getId()) && (sameCodeCategory == null || sameCodeCategory.getId() ==
                updatedCategory.getId()))
            categoryService.update(updatedCategory);
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
