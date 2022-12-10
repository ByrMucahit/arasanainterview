package com.example.arasanainterview.service.visiting;

import com.example.arasanainterview.api.request.patient.SavePatientRequest;
import com.example.arasanainterview.api.request.visiting.SaveVisitingRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.dto.VisitingExaminationDto;
import com.example.arasanainterview.repository.PatientRepository;
import com.example.arasanainterview.repository.VisitingRepository;
import com.example.arasanainterview.service.examination.ExaminationService;
import com.example.arasanainterview.service.patient.PatientService;
import com.example.arasanainterview.service.visiting.impl.VisitorServiceImpl;
import org.junit.Assert;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {VisitorService.class})
public class VisitorServiceImplTest {

    @InjectMocks
    private VisitorServiceImpl visitorService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientService patientService;

    @Mock
    private VisitingRepository visitingRepository;

    @Mock
    private ExaminationService examinationService;

    private List<Examination> expectedExaminations(Visit visit) {
        Examination examination = new Examination();
        examination.setVisit(visit);
        examination.setTreatment("headache");

        return Collections.singletonList(examination);
    }

    private PatientUser expectedPatientUser() {
        PatientUser patientUser = new PatientUser();
        patientUser.setName("mucahit");
        patientUser.setSurname("bayar");
        patientUser.setId(1);
        patientUser.setList(Collections.singletonList(this.expectedVisits(patientUser)));

        return patientUser;
    }

    private Visit expectedVisits(PatientUser user) {
        Visit visit = new Visit();
        visit.setPatientUser(user);
        visit.setVisitingDate(LocalDate.of(2022, 12, 9));
        visit.setExaminations(expectedExaminations(visit));
        visit.setId(10);

        return visit;
    }


    @Test
    void givenValidParameters_whenCreateDetailCaseNotSavedPatientUserBefore_thenShouldOk() {
        // given
        SaveVisitingRequest request = SaveVisitingRequest.builder()
                .treatment("headache")
                .surname("bayar")
                .name("mucahit")
                .nameOfTheDoctor("ahmet").build();

        when(patientService.createPatientByDto(
                SavePatientRequest
                        .builder()
                        .name(request.getName())
                        .surname(request.getSurname())
                        .build()))
                .thenReturn(expectedPatientUser());
        when(visitingRepository.save(this.expectedVisits(expectedPatientUser()))).thenReturn(null);

        this.visitorService.createDetail(request);
    }

    @Test
    void givenValidParameters_whenCreateDetailCaseSavedPatientUserBefore_thenShouldOk() {
        // given
        SaveVisitingRequest request = SaveVisitingRequest.builder()
                .treatment("headache")
                .surname("bayar")
                .name("mucahit")
                .nameOfTheDoctor("ahmet").build();

        when(patientRepository.findByNameAndSurname(request.getName(), request.getSurname())).thenReturn(Optional.of(expectedPatientUser()));

        when(visitingRepository.save(this.expectedVisits(expectedPatientUser()))).thenReturn(null);

        this.visitorService.createDetail(request);
    }

    private VisitingExaminationDto prepareVisitingExaminationDto(String treatment, PatientUser user) {
        return VisitingExaminationDto.builder().treatment(treatment).visit(expectedVisits(user)).build();
    }

    @Test
    void test_whenFindAllVisitor_thenReturnResource() {
        // given

        //when
        when(visitingRepository.findAllBy()).thenReturn(Collections.singletonList(expectedVisits(expectedPatientUser())));

        List<Visit> response = visitorService.listAllVisitors();

        Visit expectedVisit = response.get(0);

        Assertions.assertEquals(10, expectedVisit.getId());
        Assertions.assertEquals( LocalDate.of(2022, 12, 9), expectedVisit.getVisitingDate());
        Assertions.assertEquals( "mucahit", expectedVisit.getPatientUser().getName());
        Assertions.assertEquals("bayar", expectedVisit.getPatientUser().getSurname());
        Assertions.assertEquals(1, expectedVisit.getPatientUser().getId());
        Assertions.assertEquals( 0, expectedVisit.getExaminations().get(0).getId());
        Assertions.assertEquals("headache", expectedVisit.getExaminations().get(0).getTreatment());
    }
}
