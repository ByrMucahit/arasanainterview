package com.example.arasanainterview.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

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
}
