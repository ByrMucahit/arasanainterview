package com.example.arasanainterview.api.resource.visiting;

import com.example.arasanainterview.api.resource.examination.ListExaminationResource;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ListVisitorResource {

    private LocalDate localDate;
    private String patientUserName;
    private String patientUserSurname;
    private List<ListExaminationResource> listExaminationResourceList;
}
