package com.example.arasanainterview.api.request.examination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveExaminationRequest {
    private String treatment;
    private String nameOfTheDoctor;
}
