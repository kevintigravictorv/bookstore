package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Bookline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.r2dbc.dsl.R2dbc;
import org.springframework.integration.r2dbc.outbound.R2dbcMessageHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * // TODO Comment
 */
@Component
public class BooklineRepository {

  @Autowired
  private R2dbcEntityTemplate r2dbcEntityTemplate;

  @Bean
  public IntegrationFlow insertBookline() {
    return IntegrationFlows.from("papers_rc")
        .handle(R2dbc.outboundChannelAdapter(r2dbcEntityTemplate)
            .queryType(R2dbcMessageHandler.Type.INSERT)
            .tableName("book_line")
        .values(message -> {
          Bookline bookline = (Bookline) message.getPayload();
          return Map.ofEntries(
              Map.entry("book_line_no", bookline.getBookLineNo()),
              Map.entry("book_id", bookline.getBookId()),
              Map.entry("title", bookline.getTitle()),
              Map.entry("contents", bookline.getContents()),
              Map.entry("line_number", bookline.getLineNumber()),
              Map.entry("chapter", bookline.getChapter())
          );
        }))
        .get();
  }
}
