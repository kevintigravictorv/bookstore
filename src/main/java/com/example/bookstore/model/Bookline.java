package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * // TODO Comment
 */
@AllArgsConstructor
@Getter
public class Bookline {
  private String bookLineNo;
  private String bookId;
  private String title;
  private String contents;
  private Integer lineNumber;
  private Integer chapter;
}
