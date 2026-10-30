package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) { this.repository = repository; }
    public List<Book> getAll() { return repository.findAll(); }
    public Book getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Book not found with ID: " + id));
    }
    public Book create(Book book) {
        book.setBookId(null);
        return repository.save(book);
    }
    public Book update(Long id, Book book) {
        getById(id);
        book.setBookId(id);
        return repository.save(book);
    }
    public Book updateAvailability(Long id, Boolean available) {
        Book book = getById(id);
        book.setAvailable(available);
        return repository.save(book);
    }
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }
}
