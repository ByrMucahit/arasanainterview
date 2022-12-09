package com.example.arasanainterview.domain;

import com.example.arasanainterview.api.resource.examination.ListExaminationResource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private long id;

    private String treatment;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    public List<ListExaminationResource> builder(List<Examination> examinations) {
        return examinations.stream().map(
                exam -> ListExaminationResource.builder()
                        .treatment(exam.getTreatment()).build()).collect(Collectors.toList());
    }
}
