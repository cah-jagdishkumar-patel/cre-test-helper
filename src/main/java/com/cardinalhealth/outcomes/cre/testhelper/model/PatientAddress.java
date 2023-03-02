package com.cardinalhealth.outcomes.cre.testhelper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientAddress {
    private String state;
}