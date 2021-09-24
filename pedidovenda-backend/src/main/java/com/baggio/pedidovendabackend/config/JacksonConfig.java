package com.baggio.pedidovendabackend.config;

import com.baggio.pedidovendabackend.domain.PagamentoComBoleto;
import com.baggio.pedidovendabackend.domain.PagamentoComCartao;
import com.baggio.pedidovendabackend.domain.PagamentoComDinheiro;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
  // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PagamentoComCartao.class);
        objectMapper.registerSubtypes(PagamentoComBoleto.class);
        objectMapper.registerSubtypes(PagamentoComDinheiro.class);
        super.configure(objectMapper);
      };
    };
    return builder;
  }
}
