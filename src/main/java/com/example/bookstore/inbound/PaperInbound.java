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
import reactor.core.publisher.Flux;

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
            .requestPayloadType(ResolvableType.forClassWithGenerics(Flux.class, Paper.class))
            .statusCodeFunction(m -> HttpStatus.ACCEPTED))
        .<Flux<Paper>, Flux<Bookline>>transform(flux ->
          flux.map(paper -> {
            int chapter = BooklineUtils.findChapterNumber(paper.getPageNumber());
            String bookLineNumber = BooklineUtils.findBooklineNumber();
            return new Bookline(bookLineNumber, paper.getBookId(), paper.getTitle(),
                paper.getContent(), paper.getPageNumber(), chapter);
          }))
        .channel("papers_rc")
        .get();
  }
}
