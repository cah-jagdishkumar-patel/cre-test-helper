package com.cardinalhealth.cre.testhelper.sqs.listener;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.util.StringUtils;

public class NMOutputMessageReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(NMOutputMessageReceiver.class);

    @SqsListener(value="${cloud.aws.queue.nm.output.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void receiveNMOutputMessage(final String message) {
        LOGGER.info("NM Output message received: {}", message);
        if(!StringUtils.hasText(message)) {
            final String errorMessage = "received empty SE Data Ready message";
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        final JSONObject messageObject = new JSONObject(message);
        final String patientId = messageObject.optString("patientId");
        final String programId = messageObject.optString("programId");
        final String serviceId = messageObject.optString("serviceId");
        final boolean eligible = messageObject.optBoolean("eligible");
        LOGGER.info("NM Output message processed for patient: {}, program: {}, service: {}, eligible: {}", patientId, programId, serviceId, eligible);
    }
}
