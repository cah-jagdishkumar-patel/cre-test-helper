package com.cardinalhealth.outcomes.cre.testhelper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class AppConfig {

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKeyId;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretAccessKey;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String sqsUrl;

    @Profile("!test")
    @Bean
    @Primary
    public SqsAsyncClient sqsAsync() {
        return  SqsAsyncClient.builder()
            .endpointOverride(URI.create(sqsUrl))
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
            .build();
    }

    @Bean
    public SqsTemplate sqsTemplate() {
        return SqsTemplate
            .builder()
            .sqsAsyncClient(sqsAsync())
            .configureDefaultConverter(converter ->
                converter.setPayloadMessageConverter(
                    messageConverter(objectMapper())))
            .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    protected MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter =
            new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setSerializedPayloadClass(String.class);
        converter.setStrictContentTypeMatch(false);
        return converter;
    }
}
