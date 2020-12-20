package com.builders.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ValidationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ValidationApplication.class, args);
  }

}
