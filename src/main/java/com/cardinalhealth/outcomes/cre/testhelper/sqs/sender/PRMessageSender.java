package com.cardinalhealth.outcomes.cre.testhelper.sqs.sender;

import com.cardinalhealth.outcomes.cre.testhelper.model.PatientRepoMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PRMessageSender {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(PRMessageSender.class);

    @Value("${spring.cloud.aws.sqs.pr.input.name}")
    private String prQueueName;

    @Autowired
    private SqsTemplate sqsTemplate;

    public void sendMessage(PatientRepoMessage patientRepoMessage) {
        if (patientRepoMessage == null) {
            final String errorMessage = "empty message";
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        sqsTemplate.send(prQueueName, patientRepoMessage);
        LOGGER.info("PR message: {} sent to queue: {}", patientRepoMessage, prQueueName);
    }
}
