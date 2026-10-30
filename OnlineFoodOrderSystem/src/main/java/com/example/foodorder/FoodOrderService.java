package com.example.foodorder;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {
    private final FoodOrderRepository repository;

    public FoodOrderService(FoodOrderRepository repository) {
        this.repository = repository;
    }

    public FoodOrder createOrder(FoodOrder order) {
        order.setStatus("PLACED");
        return repository.save(order);
    }

    public List<FoodOrder> getAllOrders() {
        return repository.findAll();
    }

    public Optional<FoodOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    public Optional<FoodOrder> updateOrder(Long id, FoodOrder updatedOrder) {
        return repository.findById(id).map(existingOrder -> {
            existingOrder.setCustomerName(updatedOrder.getCustomerName());
            existingOrder.setFoodItem(updatedOrder.getFoodItem());
            existingOrder.setQuantity(updatedOrder.getQuantity());
            existingOrder.setPrice(updatedOrder.getPrice());
            return repository.save(existingOrder);
        });
    }

    public Optional<FoodOrder> updateStatus(Long id, String status) {
        return repository.findById(id).map(order -> {
            order.setStatus(status);
            return repository.save(order);
        });
    }

    public Optional<BigDecimal> getTotalPrice(Long id) {
        return repository.findById(id)
            .map(order -> order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
    }

    public boolean deleteOrder(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
