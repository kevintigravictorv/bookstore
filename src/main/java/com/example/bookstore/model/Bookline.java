package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * // TODO Comment
 */
@AllArgsConstructor
@Getter
@Table("bookstore.book_line")
@ToString
public class Bookline implements Persistable<String> {
  @Id
  private String bookLineNo;
  private String bookId;
  private String title;
  private String contents;
  private Integer lineNumber;
  private Integer chapter;

  @Override
  public String getId() {
    return bookLineNo;
  }

  @Override
  public boolean isNew() {
    return true;  // TODO impl
  }
}
