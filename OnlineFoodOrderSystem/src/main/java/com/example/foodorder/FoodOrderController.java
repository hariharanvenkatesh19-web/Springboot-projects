package com.example.foodorder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class FoodOrderController {
    private final FoodOrderService service;

    public FoodOrderController(FoodOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FoodOrder> createOrder(@RequestBody FoodOrder order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(order));
    }

    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getOrderById(@PathVariable Long id) {
        return service.getOrderById(id).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodOrder> updateOrder(@PathVariable Long id, @RequestBody FoodOrder order) {
        return service.updateOrder(id, order).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FoodOrder> updateStatus(@PathVariable Long id, @RequestBody StatusRequest request) {
        return service.updateStatus(id, request.getStatus()).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getTotalPrice(@PathVariable Long id) {
        return service.getTotalPrice(id).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (service.deleteOrder(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    public static class StatusRequest {
        private String status;
        public StatusRequest() {}
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
