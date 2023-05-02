package com.cardinalhealth.outcomes.cre.testhelper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

    private String uuid;
    private Integer connectPatientId;
    private Integer policyId;
    private String outcomesEligibiltyDate;
    private String outcomesTermDate;
    private String mtmTermDate;
    private String planTermDate;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private List<PatientAddress> addresses;
    private List<MedicationDispense> dispenses;
}
