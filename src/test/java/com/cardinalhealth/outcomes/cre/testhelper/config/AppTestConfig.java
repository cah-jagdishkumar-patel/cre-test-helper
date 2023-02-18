package com.cardinalhealth.outcomes.cre.testhelper.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.config.SimpleMessageListenerContainerFactory;
import io.awspring.cloud.messaging.listener.QueueMessageHandler;
import io.awspring.cloud.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AppTestConfig {

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainerFactory factory =
            new SimpleMessageListenerContainerFactory();
        factory.setAutoStartup(false);
        factory.setAmazonSqs(amazonSQSAsync());
        SimpleMessageListenerContainer simpleMessageListenerContainer = factory
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