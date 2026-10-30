package com.example.leave.controller;

import com.example.leave.entity.EmployeeLeave;
import com.example.leave.service.EmployeeLeaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class EmployeeLeaveController {
    private final EmployeeLeaveService service;

    public EmployeeLeaveController(EmployeeLeaveService service) { this.service = service; }

    @GetMapping
    public List<EmployeeLeave> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public EmployeeLeave getById(@PathVariable("id") Long id) { return service.getById(id); }

    @PostMapping
    public ResponseEntity<EmployeeLeave> create(@Valid @RequestBody EmployeeLeave item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(item));
    }

    @PutMapping("/{id}")
    public EmployeeLeave update(@PathVariable("id") Long id, @Valid @RequestBody EmployeeLeave item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
