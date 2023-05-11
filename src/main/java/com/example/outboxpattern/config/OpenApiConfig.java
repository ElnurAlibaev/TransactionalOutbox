package com.example.outboxpattern.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {
    @Bean
    GroupedOpenApi userApi(){
        return GroupedOpenApi.builder().group("user").pathsToMatch("/user/**").build();
    }
    @Bean
    GroupedOpenApi applicationApi(){
        return GroupedOpenApi.builder().group("application").pathsToMatch("/application/**").build();
    }
}
