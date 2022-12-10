package com.example.arasanainterview.service.examination;


import com.example.arasanainterview.api.request.examination.SaveExaminationRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.repository.ExaminationRepository;
import com.example.arasanainterview.service.examination.impl.ExaminationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExaminationService.class})
public class ExaminationServiceImplTest {

    @InjectMocks
    private ExaminationServiceImpl examinationService;

    @Mock
    private ExaminationRepository examinationRepository;

    private List<Examination> expectedExaminations() {
        Examination examination = new Examination();
        examination.setTreatment("headache");
        examination.setVisit(expectedVisit());
        examination.setId(5);

        return Collections.singletonList(examination);
    }

    private PatientUser expectedPatientUser(Visit visit) {
        PatientUser user = new PatientUser();
        user.setId(2);
        user.setName("mucahit");
        user.setSurname("bayar");
        user.setList(Collections.singletonList(visit));
        return user;
    }

    private Visit expectedVisit() {
        Visit visit = new Visit();
        visit.setId(1);
        visit.setPatientUser(expectedPatientUser(visit));
        visit.setVisitingDate(LocalDate.of(2022, 12, 9));
        return visit;
    }

    @Test
    void givenValidParameters_whenCreateExamination_whenShouldOk() {
        // given
        SaveExaminationRequest request = SaveExaminationRequest.builder().treatment("headache").nameOfTheDoctor("ahmet").build();

        // when
        this.examinationService.createExamination(request);
    }

    @Test
    void test_whenFindAllExaminations_whenReturnResource() {
        // given

        // when
        when(examinationRepository.findAllBy()).thenReturn(expectedExaminations());

        List<Examination> examinations = examinationService.findAll();

        Examination examination = examinations.get(0);

        Assertions.assertEquals("headache", examination.getTreatment());
        Assertions.assertEquals(5, examination.getId());
        Assertions.assertEquals( LocalDate.of(2022, 12, 9), examination.getVisit().getVisitingDate());
        Assertions.assertEquals("bayar", examination.getVisit().getPatientUser().getSurname());
        Assertions.assertEquals("mucahit", examination.getVisit().getPatientUser().getName());
        Assertions.assertEquals(2, examination.getVisit().getPatientUser().getId());
        Assertions.assertEquals(1, examination.getVisit().getId());
    }
}
