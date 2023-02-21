package com.cardinalhealth.outcomes.cre.testhelper.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AppTestConfig {
    @Bean
    public SqsAsyncClient sqsAsync() {
        return mock(SqsAsyncClient.class);
    }
}