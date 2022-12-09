package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.examination.SaveExaminationRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.service.examination.ExaminationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ExaminationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExaminationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExaminationService service;

    @Test
    void givenValidRequestParameters_whenCreateExaminationsDetail_thenShouldOk() throws Exception {
        // given
        SaveExaminationRequest request = SaveExaminationRequest.builder().nameOfTheDoctor("ahmet").treatment("headache").build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/examination/create")
                        .content(new ObjectMapper().writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private List<Examination> prepareExaminations() {
        Examination examination = new Examination();
        examination.setTreatment("headache");
        examination.setVisit(prepareVisit(Collections.singletonList(examination)));

        return Collections.singletonList(examination);
    }

    private PatientUser preparePatientUser(List<Visit> visits) {
        PatientUser patientUser = new PatientUser();
        patientUser.setList(visits);
        patientUser.setName("mucahit");
        patientUser.setSurname("bayar");
        patientUser.setId(1);

        return patientUser;
    }

    private Visit prepareVisit(List<Examination> examinations) {
        Visit visit = new Visit();
        visit.setVisitingDate(LocalDate.of(2022, 12, 9));
        visit.setExaminations(examinations);
        visit.setId(10);
        visit.setPatientUser(preparePatientUser(Collections.singletonList(visit)));

        return visit;
    }

    @Test
    void test_whenFindAllExaminationsDetail_thenReturnResource() throws Exception {
        // given
        given(this.service.findAll()).willReturn(prepareExaminations());

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/examination/list")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].treatment").value("headache"));
    }
}
