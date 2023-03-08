package com.cardinalhealth.outcomes.cre.testhelper.controller;

import com.cardinalhealth.outcomes.cre.testhelper.sqs.sender.PRMessageSender;
import com.cardinalhealth.outcomes.cre.testhelper.model.PatientRepoMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
public class TestHelperController {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(TestHelperController.class);
    @Autowired
    private PRMessageSender prMessageSender;

    @PostMapping("/sendPRMessage")
    public void sendPRMessage(@RequestBody PatientRepoMessage patientRepoMessage) {
        prMessageSender.sendMessage(patientRepoMessage);
    }

    @PostMapping("/fakePriorServiceData")
    public Map<String, Object> fakePriorServiceData(String patientId) {
        String data = "{}";
        try {
            Resource resource = new ClassPathResource(String.format("static" +
                "/%s_FakePriorServiceData.json", patientId));
            data = Files.readString(Path.of(resource.getURL().getPath()));
        } catch (IOException e) {
            String errorMessage = String.format("error reading fake prior " +
                "service data for %s", patientId);
            LOGGER.error(errorMessage);
        }
        return new JSONObject(data).toMap();
    }

    @PostMapping("/fakeServiceData")
    public String fakeServiceData(String patientId, String valueList) {
        return "0.5";
    }

    @PostMapping("/programs")
    public String programs(Map<String,Object> input) {
        return "{ \"id\" : \"programs/123\"}";
    }

    @PutMapping("/programs/{programId}/status")
    public void status(@PathVariable String programId, Map<String,Object> status) {

    }

    @PostMapping("/programs/{programId}/rulesets")
    public void rulesets(@PathVariable String programId, Map<String,Object> input) {
    }

    @PostMapping("/clients")
    public String clients(Map<String,Object> input) {
        return "{ \"id\" : \"clients/123\"}";
    }

    @PostMapping("/clients/{clientId}/programs")
    public void clientProgram(@PathVariable String clientId, Map<String,Object> input) {
    }

}
