package com.cardinalhealth.cre.testhelper.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AppTestConfig {

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        final SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAutoStartup(false);
        factory.setAmazonSqs(amazonSQSAsync());
        final SimpleMessageListenerContainer simpleMessageListenerContainer = factory
                .createSimpleMessageListenerContainer();
        simpleMessageListenerContainer.setMessageHandler(messageHandler());
        return simpleMessageListenerContainer;
    }
    @Bean
    public QueueMessageHandler messageHandler() {
        return mock(QueueMessageHandler.class);
    }

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return mock(AmazonSQSAsync.class);
    }
}