package com.example.bookstore.repository;

import com.example.bookstore.model.Bookline;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * // TODO Comment
 */
public interface BooklineRepository extends ReactiveCrudRepository<Bookline, String> {
  Flux<Bookline> findByBookId(String bookId);
}
