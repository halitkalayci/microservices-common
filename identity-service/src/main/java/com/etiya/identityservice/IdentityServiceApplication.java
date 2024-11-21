package com.etiya.identityservice;

import io.github.halitkalayci.core.annotation.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCore
public class IdentityServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(IdentityServiceApplication.class, args);
  }

}
