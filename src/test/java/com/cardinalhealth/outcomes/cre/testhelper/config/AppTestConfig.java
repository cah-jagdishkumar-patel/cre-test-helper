package com.cardinalhealth.outcomes.cre.testhelper.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AppTestConfig {
    @Bean
    public SqsTemplate sqsTemplate() {
        return mock(SqsTemplate.class);
    }
}