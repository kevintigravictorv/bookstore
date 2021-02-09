package com.example.bookstore.utils;

import java.util.UUID;

/**
 * // TODO Comment
 */
public class BooklineUtils {
  public static int findChapterNumber(int pageNumber) {
    int chapterNumber = 4;
    if(pageNumber < 100) chapterNumber = 1;
    else if(pageNumber < 200) chapterNumber = 2;
    else if(pageNumber < 300) chapterNumber = 3;
    return chapterNumber;
  }

  public static String findBooklineNumber() {
    return UUID.randomUUID().toString();
  }
}
