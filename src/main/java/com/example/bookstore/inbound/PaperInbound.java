package com.example.bookstore.inbound;

import com.example.bookstore.model.Bookline;
import com.example.bookstore.model.Paper;
import com.example.bookstore.utils.BooklineUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.webflux.dsl.WebFlux;
import org.springframework.stereotype.Component;

/**
 * // TODO Comment
 */
@Component
public class PaperInbound {
  @Bean
  public IntegrationFlow insertPapers() {
    return IntegrationFlows
        .from(WebFlux.inboundChannelAdapter("/papers")
            .requestMapping(m -> m.methods(HttpMethod.POST))
            .statusCodeFunction(m -> {
              HttpStatus statusCode;
              switch (m.getMethod()) {
                case POST:
                  statusCode = HttpStatus.ACCEPTED;
                  break;
                default:
                  statusCode = HttpStatus.BAD_REQUEST;
                  break;
              }
              return statusCode;
            })
            .requestPayloadType(ResolvableType.forClass(Paper.class))
        ).<Paper, Bookline>transform(paper -> new Bookline(
            BooklineUtils.findBooklineNumber(),
            paper.getBookId(),
            paper.getTitle(),
            paper.getContent(),
            paper.getPageNumber(),
            BooklineUtils.findChapterNumber(paper.getPageNumber())))
        .channel("papers_rc")
        .get();
  }
}
