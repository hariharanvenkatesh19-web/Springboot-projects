package com.example.leave.service;

import com.example.leave.entity.EmployeeLeave;
import com.example.leave.repository.EmployeeLeaveRepository;
import com.example.leave.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeLeaveService {
    private final EmployeeLeaveRepository repository;

    public EmployeeLeaveService(EmployeeLeaveRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeLeave> getAll() { return repository.findAll(); }

    public EmployeeLeave getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeLeave not found with ID: " + id));
    }

    public EmployeeLeave create(EmployeeLeave item) {
        item.setEmployeeId(null);
        return repository.save(item);
    }

    public EmployeeLeave update(Long id, EmployeeLeave item) {
        getById(id);
        item.setEmployeeId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }
}
