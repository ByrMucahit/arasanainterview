package com.example.arasanainterview.api.request;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePatientRequest {
    private String name;
    private String surName;
}
