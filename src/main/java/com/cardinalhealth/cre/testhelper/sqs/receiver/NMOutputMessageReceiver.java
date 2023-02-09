package com.cardinalhealth.cre.testhelper.sqs.receiver;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NMOutputMessageReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(NMOutputMessageReceiver.class);

    @SqsListener(value="${cloud.aws.queue.nm.output.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void receiveMessage(String message) {
        LOGGER.info("NM Output message received: {}", message);
        if(!StringUtils.hasText(message)) {
            final String errorMessage = "received empty SE Data Ready message";
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        JSONObject messageObject = new JSONObject(message);
        String patientId = messageObject.optString("patientId");
        Integer policyId = messageObject.optInt("policyId");
        Integer serviceTypeId = messageObject.optInt("serviceTypeId");
        Integer serviceId = messageObject.optInt("serviceId");
        boolean eligible = messageObject.optBoolean("eligible");
        LOGGER.info("NM Output message processed for patient: {}, policy: {}, serviceType: {}, service: {}, eligible: {}", patientId, policyId, serviceTypeId, serviceId, eligible);
    }
}
