package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping
    public List<Product> getProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return productService.findAll();
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "addProduct";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/details")
    public String getProductDetails(@PathVariable int id, Model model) {
        Product product = productService.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "details";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable int id, Model model) {
        Product product = productService.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "editProduct";
    }



    @PostMapping("/{id}/edit")
    public String updateProduct(@ModelAttribute("product") Product updatedProduct) {
        productService.update(updatedProduct);
        return "redirect:/products";
    }



    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        try {
            productService.deleteById(id);
        } catch (Exception ignored) {
            System.out.println("Error deleting product " + id);
        }
        return "redirect:/products";
    }



}