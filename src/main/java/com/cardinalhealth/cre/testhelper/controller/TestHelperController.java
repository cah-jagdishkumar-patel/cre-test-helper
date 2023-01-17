package com.cardinalhealth.cre.testhelper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHelperController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHelperController.class);
    @Value("${cloud.aws.queue.pr.input.name}")
    private String prInputQueueName;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping("/sendPRMessage")
    public void sendPRMessage(@RequestBody final String message) {
        queueMessagingTemplate.convertAndSend(prInputQueueName, message);
        LOGGER.info("PR message sent to queue: {}", prInputQueueName);
    }
}
