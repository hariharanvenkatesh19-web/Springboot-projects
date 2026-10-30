package com.example.leave.repository;

import com.example.leave.entity.EmployeeLeave;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeLeaveRepository {
    private final List<EmployeeLeave> items = new ArrayList<>();
    private long nextId = 1;

    public synchronized List<EmployeeLeave> findAll() {
        return new ArrayList<>(items);
    }

    public synchronized Optional<EmployeeLeave> findById(Long id) {
        return items.stream().filter(item -> item.getEmployeeId().equals(id)).findFirst();
    }

    public synchronized EmployeeLeave save(EmployeeLeave item) {
        if (item.getEmployeeId() == null) {
            item.setEmployeeId(nextId++);
        }
        items.removeIf(existing -> existing.getEmployeeId().equals(item.getEmployeeId()));
        items.add(item);
        return item;
    }

    public synchronized void deleteById(Long id) {
        items.removeIf(item -> item.getEmployeeId().equals(id));
    }
}
