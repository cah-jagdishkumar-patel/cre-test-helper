package com.cardinalhealth.outcomes.cre.testhelper.sqs.sender;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PRMessageReceiver {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(PRMessageReceiver.class);

    @SqsListener(value = "${spring.cloud.aws.sqs.pr.input.name}")
    private void receiveMessage(String message) {
        LOGGER.info("inside PR message received {} ", message);
        throw new RuntimeException("no");
        //LOGGER.info("PR message received: {}", message);
    }
}
