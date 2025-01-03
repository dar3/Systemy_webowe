package org.example.spring_2;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private  final ProductRepository productRepository;


    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product add(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Product with the same name already exists.");
        }
    }

    public void update(Product product) {
        productRepository.save(product);
    }
    public void deleteById(int id)
    {
        productRepository.deleteById(id);
    }

}
