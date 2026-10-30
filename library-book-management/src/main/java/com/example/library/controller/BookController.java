package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) { this.service = service; }
    @GetMapping
    public List<Book> getAll() { return service.getAll(); }
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id) { return service.getById(id); }
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(book));
    }
    @PutMapping("/{id}")
    public Book update(@PathVariable("id") Long id, @Valid @RequestBody Book book) {
        return service.update(id, book);
    }
    @PatchMapping("/{id}/availability")
    public Book updateAvailability(@PathVariable("id") Long id,
                                  @RequestParam("available") Boolean available) {
        return service.updateAvailability(id, available);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
