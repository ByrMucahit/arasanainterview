package com.example.arasanainterview.domain;

import com.example.arasanainterview.api.resource.PatientResource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public List<PatientResource> builder(List<PatientUser> patientUsers) {
        return patientUsers.stream().map(user ->
                        PatientResource
                                .builder()
                                .name(user.getName())
                                .surname(user.getSurname())
                                .build())
                .collect(Collectors.toList());
    }

    public PatientResource toDto(PatientUser patientUser) {
        return PatientResource.builder().name(patientUser.getName()).surname(patientUser.getSurname()).build();
    }
}
