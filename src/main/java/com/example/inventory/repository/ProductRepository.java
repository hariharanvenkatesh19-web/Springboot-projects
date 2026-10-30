package com.example.inventory.repository;

import com.example.inventory.entity.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> items = new ArrayList<>();
    private long nextId = 1;

    public synchronized List<Product> findAll() {
        return new ArrayList<>(items);
    }

    public synchronized Optional<Product> findById(Long id) {
        return items.stream().filter(item -> item.getProductId().equals(id)).findFirst();
    }

    public synchronized Product save(Product item) {
        if (item.getProductId() == null) {
            item.setProductId(nextId++);
        }
        items.removeIf(existing -> existing.getProductId().equals(item.getProductId()));
        items.add(item);
        return item;
    }

    public synchronized void deleteById(Long id) {
        items.removeIf(item -> item.getProductId().equals(id));
    }
}
