package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.example.spring_2.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();
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
//        model.addAttribute("kat", categoryService.findAll().getFirst());
//        System.out.println(categoryService.getCategoryRepository().findById(1));
        return "addProduct";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/details")
    public String getProductDetails(@PathVariable int id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "details";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable int id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") Product updatedProduct) {
        Product existingProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setWeight(updatedProduct.getWeight());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
        }
        return "redirect:/products";
    }


    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        try {
            products.removeIf(product -> product.getId() == id);
        } catch (Exception ignored) {
            System.out.println("Error deleting product " + id);
        }
        return "redirect:/products";
    }


}