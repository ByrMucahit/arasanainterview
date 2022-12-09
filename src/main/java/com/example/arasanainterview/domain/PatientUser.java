package com.example.arasanainterview.domain;

import com.example.arasanainterview.api.resource.patient.PatientResource;
import com.example.arasanainterview.api.resource.visiting.ListVisitorResource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class PatientUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private long id;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "patientUser")
    private List<Visit> list;

    public List<PatientResource> builder(List<PatientUser> patientUsers) {
        return patientUsers.stream().map(user ->
                        PatientResource
                                .builder()
                                .name(user.getName())
                                .surname(user.getSurname())
                                .visiting(prepareVisitingResource(user.getList()))
                                .build())
                .collect(Collectors.toList());
    }

    private List<ListVisitorResource> prepareVisitingResource(List<Visit> visits) {
        return  visits.stream().map(visit ->
                ListVisitorResource.builder()
                        .patientUserName(visit.getPatientUser().getName())
                        .patientUserSurname(visit.getPatientUser().getSurname())
                        .localDate(visit.getVisitingDate())
                        .listExaminationResourceList(new Examination().builder(visit.getExaminations())).build()).collect(Collectors.toList());
    }


    public PatientResource toDto(PatientUser patientUser) {
        return PatientResource.builder().name(patientUser.getName()).surname(patientUser.getSurname()).build();
    }
}
