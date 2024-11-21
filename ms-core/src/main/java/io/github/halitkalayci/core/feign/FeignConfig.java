package io.github.halitkalayci.core.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
public class FeignConfig {
  @Bean
  public RequestInterceptor authorizationHeaderInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate requestTemplate) {
        String token = getAuthorizationTokenFromRequest();
        if (token != null) {
          requestTemplate.header("Authorization", token);
        }
      }

      private String getAuthorizationTokenFromRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
          HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
          return request.getHeader("Authorization");
        }
        return null;
      }
    };
  }

}
