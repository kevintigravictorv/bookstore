package com.example.bookstore.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

/**
 * // TODO Comment
 */
@Configuration
public class BookstoreConfiguration extends AbstractR2dbcConfiguration {

  @Bean
  @Override
  public ConnectionFactory connectionFactory() {
    return ConnectionFactories.get(ConnectionFactoryOptions.builder()
        .option(ConnectionFactoryOptions.DRIVER, "postgresql")
        .option(ConnectionFactoryOptions.HOST, "localhost")
        .option(ConnectionFactoryOptions.PORT, 3306)
        .option(ConnectionFactoryOptions.DATABASE, "book_store")
        .option(ConnectionFactoryOptions.USER, "kevintigravictor")
        .option(ConnectionFactoryOptions.PASSWORD, "ktvktv")
        .build()
    );
  }
}
