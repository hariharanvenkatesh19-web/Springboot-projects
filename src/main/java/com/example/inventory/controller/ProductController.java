package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<Product> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id) { return service.getById(id); }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(item));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @Valid @RequestBody Product item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
