package com.example.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * // TODO Comment
 */
@Getter
@NoArgsConstructor
@ToString
public class Paper {
  private String bookId;
  private String title;
  private String content;
  private Integer pageNumber;
}
