package com.cardinalhealth.cre.testhelper.controller;

import com.cardinalhealth.cre.testhelper.sqs.sender.PRMessageSender;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
public class TestHelperController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHelperController.class);
    @Autowired
    private PRMessageSender prMessageSender;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping("/sendPRMessage")
    public void sendPRMessage(@RequestBody final String message) {
        prMessageSender.sendMessage(message);
    }

    @PostMapping("/fakePriorServiceData")
    public Map<String,Object> fakePriorServiceData(final String patientId) {
        String data = "{}";
        try {
            Resource resource = new ClassPathResource(String.format("%s_FakePriorServiceData.json", patientId));
            data = Files.readString(Path.of(resource.getURL().getPath()));
        } catch (IOException e) {
            final String errorMessage = String.format("error reading fake prior service data for %s", patientId);
            LOGGER.error(errorMessage);
        }
        return new JSONObject(data).toMap();
    }

    @PostMapping("/fakeServiceData")
    public String fakeServiceData(final String patientId, final String valueList) {
        return "0.5";
    }
}
