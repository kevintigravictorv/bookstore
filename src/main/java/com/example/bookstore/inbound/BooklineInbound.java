package com.example.bookstore.inbound;

import org.springframework.context.annotation.Bean;
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
public class BooklineInbound {

  @Bean
  public IntegrationFlow getBooklineByBookId() {
    return IntegrationFlows.from(WebFlux.inboundGateway("/bookline/{book_id}")
        .requestMapping(m -> m.methods(HttpMethod.GET))
        .statusCodeFunction(s -> HttpStatus.OK)
        .payloadExpression("#pathVariables.book_id"))
        .channel("bookline_rc")
        .get();
  }
}
