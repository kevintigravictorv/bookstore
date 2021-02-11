package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * // TODO Comment
 */
public interface BookRepository extends ReactiveCrudRepository<Book, String> {
}
