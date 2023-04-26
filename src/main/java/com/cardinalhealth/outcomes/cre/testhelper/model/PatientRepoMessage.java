package com.cardinalhealth.outcomes.cre.testhelper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientRepoMessage {

    private String searchRequestId;
    private JobStatus searchRequestStatus;
    private String searchRequestError;
    private Integer totalRecordsSent;
    private Long messageTimeMillis;
    @ToString.Exclude
    private Patient patient;
}
