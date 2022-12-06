package com.example.arasanainterview.domain;

import com.example.arasanainterview.api.resource.ListPatientResource;
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

    public List<ListPatientResource> builder(List<PatientUser> patientUsers) {
        return patientUsers.stream().map(user ->
                        ListPatientResource
                                .builder()
                                .name(user.getName())
                                .surname(user.getSurname())
                                .build())
                .collect(Collectors.toList());
    }
}
