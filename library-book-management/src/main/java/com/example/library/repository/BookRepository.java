package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    private long nextId = 1;
    public synchronized List<Book> findAll() { return new ArrayList<>(books); }
    public synchronized Optional<Book> findById(Long id) {
        return books.stream().filter(book -> book.getBookId().equals(id)).findFirst();
    }
    public synchronized Book save(Book book) {
        if (book.getBookId() == null) { book.setBookId(nextId++); }
        books.removeIf(existing -> existing.getBookId().equals(book.getBookId()));
        books.add(book);
        return book;
    }
    public synchronized void deleteById(Long id) {
        books.removeIf(book -> book.getBookId().equals(id));
    }
}
