package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * // TODO Comment
 */
@AllArgsConstructor
@Getter
public class Book {
  private String bookId;
  private String bookName;
  private String author;
  private String genre;
}
