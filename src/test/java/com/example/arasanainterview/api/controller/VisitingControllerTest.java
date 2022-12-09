package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.visiting.SaveVisitingRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.service.visiting.VisitorService;
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
@WebMvcTest(controllers = VisitingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VisitingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    @Test
    void givenValidRequest_whenCreateDetail_thenShouldOk() throws Exception {
        // given
        SaveVisitingRequest request = SaveVisitingRequest.builder()
                .name("mucahit")
                .nameOfTheDoctor("ahmet")
                .surname("bayar")
                .treatment("stomachache").build();
        // when
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/visitor/create")
                                .content(new ObjectMapper().writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_whenFindAllVisitors_thenReturnResource() throws Exception {
        // given
        given(visitorService.listAllVisitors()).willReturn(prepareVisitLists());

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/visitor/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].localDate").value("2022-12-09"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].patientUserName").value("mucahit"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].patientUserSurname").value("bayar"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].listExaminationResourceList[0].treatment").value("headache"));
    }

    private List<Visit> prepareVisitLists() {
        Visit visit = new Visit();
        visit.setExaminations(prepareExamination());
        visit.setPatientUser(preparePatientUser(Collections.singletonList(visit)));
        visit.setVisitingDate(LocalDate.of(2022, 12, 9));

        return Collections.singletonList(visit);
    }

    private PatientUser preparePatientUser(List<Visit> list) {
        PatientUser patientUser = new PatientUser();
        patientUser.setName("mucahit");
        patientUser.setSurname("bayar");
        patientUser.setList(list);

        return patientUser;
    }

    private List<Examination> prepareExamination() {
        Examination examination = new Examination();
        examination.setTreatment("headache");
        return Collections.singletonList(examination);
    }

}
