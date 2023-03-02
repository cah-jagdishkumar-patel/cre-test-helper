package com.cardinalhealth.outcomes.cre.testhelper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicationDispense {
    private BigDecimal qty;
    private BigDecimal supply;
    private LocalDate rxDate;
    private String ndc;
}