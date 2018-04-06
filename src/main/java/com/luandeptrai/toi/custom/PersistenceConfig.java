package com.luandeptrai.toi.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfig {

  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
