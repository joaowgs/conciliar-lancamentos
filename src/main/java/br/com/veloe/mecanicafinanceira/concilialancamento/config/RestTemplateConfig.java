package br.com.veloe.mecanicafinanceira.concilialancamento.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateConfig {

    @Value("${mf-api.user}")
    private String user;
    @Value("${mf-api.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .basicAuthentication(user, password)
                .build();
    }
}
