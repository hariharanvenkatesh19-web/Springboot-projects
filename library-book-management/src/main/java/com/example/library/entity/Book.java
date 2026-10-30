package com.example.library.entity;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class Book {
    private Long bookId;
    @NotBlank(message = "Book name is required")
    private String bookName;
    @NotBlank(message = "Author name is required")
    private String author;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    @NotNull(message = "Availability is required")
    private Boolean available;

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
