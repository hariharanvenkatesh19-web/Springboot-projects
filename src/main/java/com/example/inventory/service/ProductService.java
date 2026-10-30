package com.example.inventory.service;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() { return repository.findAll(); }

    public Product getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    public Product create(Product item) {
        item.setProductId(null);
        return repository.save(item);
    }

    public Product update(Long id, Product item) {
        getById(id);
        item.setProductId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }
}
