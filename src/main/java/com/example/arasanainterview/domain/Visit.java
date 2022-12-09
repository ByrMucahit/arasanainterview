package com.example.arasanainterview.domain;

import com.example.arasanainterview.api.resource.examination.ListExaminationResource;
import com.example.arasanainterview.api.resource.visiting.ListVisitorResource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private long id;


    private LocalDate visitingDate;

    @ManyToOne
    @JoinColumn(name = "patientUser_id", nullable = false)
    private PatientUser patientUser;

    @OneToMany(mappedBy = "visit")
    private List<Examination> examinations;

    public List<ListVisitorResource> builder(List<Visit> visits) {
        return visits.stream().map(visit ->
                ListVisitorResource.builder()
                        .localDate(visit.visitingDate)
                        .patientUserName(visit.patientUser.getName()).listExaminationResourceList(prepareListExamination(visit.getExaminations()))
                        .patientUserSurname(visit.patientUser.getSurname()).build()
        ).collect(Collectors.toList());
    }

    private List<ListExaminationResource> prepareListExamination(List<Examination> examinations) {
        return examinations.stream().map(exam -> ListExaminationResource.builder().treatment(exam.getTreatment()).build()).collect(Collectors.toList());
    }
}
