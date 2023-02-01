package com.cardinalhealth.cre.testhelper.sqs.sender;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PRMessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(PRMessageSender.class);

    @Value("${cloud.aws.queue.pr.input.name}")
    private String prQueueName;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    public void sendMessage(final String message) {
        if(!StringUtils.hasText(message)) {
            final String errorMessage = "empty message";
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        queueMessagingTemplate.convertAndSend(prQueueName, message);
        LOGGER.info("PR message sent to queue: {}", prQueueName);
    }
}
