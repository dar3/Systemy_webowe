package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private  final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product add(Product product) {
        try {
//            Product product2 = new Product();
//            product2.setName("Example Product");
//            product2.setWeight(1.2);
//            product2.setPrice(100.0);
//            product2.setCategory(categoryService.findAll().get(1)); // Obiekt Category z istniejącym ID w bazie danych
//            productRepository.save(product2);

            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Product with the same name already exists.");
        }
    }

}
