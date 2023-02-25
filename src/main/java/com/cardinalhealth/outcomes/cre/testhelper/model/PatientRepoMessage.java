package com.cardinalhealth.outcomes.cre.testhelper.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientRepoMessage {
    @JsonAlias("uuid")
    private String patientId;
    @NotNull
    private Integer connectPatientId;
    private Integer policyId;
}
