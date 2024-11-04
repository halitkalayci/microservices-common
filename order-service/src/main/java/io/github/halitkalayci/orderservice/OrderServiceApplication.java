package io.github.halitkalayci.orderservice;

import io.github.halitkalayci.core.annotation.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@EnableCore
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

  @Bean
  public Consumer<String> processCustomerEvent() {
    return event -> {
      System.out.println("Received customer created event: " + event);
      // İşleme mantığı burada devam eder
    };
  }
}
