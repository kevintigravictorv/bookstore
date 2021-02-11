package com.example.bookstore.service;

import com.example.bookstore.model.Bookline;
import com.example.bookstore.repository.BooklineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import reactor.core.publisher.Flux;

/**
 * // TODO Comment
 */
@Configuration
public class BooklineService {
  private Logger logger = LoggerFactory.getLogger(BooklineService.class);

  @Autowired
  private BooklineRepository booklineRepository;

  @ServiceActivator(inputChannel = "papers_rc")
  public void insertBookline(@Payload Bookline bookline) {
    logger.info("Insert new bookline into database, bookline: {}", bookline);
    booklineRepository.save(bookline)
        .doOnError(s -> logger.error(s.getMessage()))
        .doOnSuccess(s -> logger.info("Success to save message from new data, message: {}", s))
        .doOnCancel(() -> logger.warn("Cancelled"))
        .doFinally(s -> logger.info("Finally done"))
        .subscribe();
  }

  @ServiceActivator(inputChannel = "bookline_rc")
  public Flux<Bookline> getBooklineByBookId(@Payload String bookId) {
    logger.info("Find all bookline by book id: {}", bookId);
    return booklineRepository.findByBookId(bookId);
  }
}
